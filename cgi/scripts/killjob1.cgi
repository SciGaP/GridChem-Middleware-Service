#!/usr/bin/perl  -w
#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Read the paramter sent to the script
#############################################################################################

read(STDIN, $buffer, $ENV{'CONTENT_LENGTH'});
@lines = split(/\n/,$buffer);
foreach $line (@lines) 
{
    @pairs = split(/&/, $line);
    foreach $pair (@pairs)
    {
    	($name, $value) = split(/=/, $pair);
    	$value =~ tr/+/ /;
    	$value =~ s/%([a-fA-F0-9][a-fA-F0-9])/pack("C", hex($1))/eg;
	### Stop people from using subshells to execute commands
    	$value =~ s/~!/ ~!/g; 
    	$FORM{$name} = $value;

        # 2006/08/30 temp
        print STDERR "$name: $FORM{$name}";
    }
}


print "Content-type: text/plain\n\n";

# 2006/08/30 skk add error code
unless ( ($jobid = $FORM{"JobID"}) || ($jobid = $FORM{"JoBID"}) )  {  # 2006/11/07 skk - a temp fix for (non-WS) production which is using JoBID not JobID - the JoBID part can be removed when we are done with old non-webservices
  print "kill_job__no_job_id_given_err\n"; 
  die "kill_job__no_job_id_given_err";
}

print STDERR "$0: the jobid we received is $jobid\n";
$jobid =~ s/\r//;
$UserName = $FORM{'Username'};
$UserName =~ s/\r//;
$GridChemUserName = $FORM{'GridChemUsername'};
$GridChemUserName =~ s/\r//;
$IsGridChem = $FORM{'IsGridChem'};
$IsGridChem =~ s/\r//; 
$Sysnam = $FORM{'Sysnm'};
$Sysnam =~ s/\r//;
$Sysnam =~ s/\n//;

$ENV{jobid} = "$jobid";
$ENV{UserName} = "$UserName";
$ENV{GridChemUserName}="$GridChemUserName";
$ENV{IsGridChem}="$IsGridChem";
$ENV{Sysnam} = "$Sysnam"; 


#############################################################################################
##Amr: Add DB support
##
##  Load the resource database 
#############################################################################################

require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory
$securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir ;

$userTmpDirBase = DB::getGMSAttribute("user-temp-directory");
$ENV{userTmpDirBase}=$userTmpDirBase;

#remove old Logfile
if  ($IsGridChem eq "true"){ $TMPDIR = "$userTmpDirBase/internal/$GridChemUserName";}
else                       { $TMPDIR = "$userTmpDirBase/external/$GridChemUserName";}
$ENV{TMPDIR} = "$TMPDIR";

$KjhLog = join("_",$UserName,"KjhLog");
$KjjLog = join("_",$UserName,"KjjLog");

#remove old logfile
$ENV{KjhLog}="$KjhLog";
$ENV{KjjLog}="$KjjLog";

unlink "$TMPDIR/$KjjLog";
unlink "$TMPDIR/$KjhLog";

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Check which security architecture to use : KRB5 or X509
#############################################################################################

$X509 = 0; # this variable says whether or not to use X509

# lixh_add
$X509_USER_PROXY = join('',"$securityTmpDir/","$UserName","_X509");
if  ($IsGridChem eq "true"){ 
  $X509_USER_PROXY = join('',"$securityTmpDir/","$GridChemUserName","CCG_X509");
}
$ENV{X509_USER_PROXY} = "$X509_USER_PROXY";

$X509_USER_CERT = "$X509_USER_PROXY";
$ENV{X509_USER_CERT} = "$X509_USER_CERT";

$GLOBUS_LOCATION = DB::getGMSAttribute("globus-directory-location");
$ENV{GLOBUS_LOCATION} = "$GLOBUS_LOCATION";

$KRB5CCNAME = "$securityTmpDir/krb5cc_$UserName";
$ENV{KRB5CCNAME} = "$KRB5CCNAME";

if ( -e $X509_USER_PROXY)
{
    if ( -e $KRB5CCNAME)
    {
	@x509stat = stat($X509_USER_PROXY);
	@krbstat = stat($KRB5CCNAME);
	if ($x509stat[9] > $krbstat[9])
	{
	    $X509 = 1;
	}
	else
	{
	    $X509 = 0;
	}
    }
    else
    {
	$X509 = 1;
    }
}
else 
{
    $X509 = 0;
}
$ENV{X509} = "$X509";

$ENV{status} = "$?";
unlink "$TMPDIR/testrshstatus";
unlink "$TMPDIR/bsubstatus";

#check if ticket is valid
unlink "$TMPDIR/curtim";
unlink "$TMPDIR/kltim";
if ($X509 == 0)
{
   $checkTickets = DB::getGMSAttribute("krb5-check-ticket-program-path");
   $ENV{checkTickets}= $checkTickets;
   require "chktickets.pl";
   print STDERR "KERBEROS authentication\n";
}
else
{
    print STDERR "GLOBUS authentication\n";
}

#############################################################################################
##  Amr: Factored the old code to use DB Calls
##
##  Load machine specific information
#############################################################################################

$gsissh = DB::getGMSAttribute("globus-gsissh-program-path");
$ENV{gsissh}=$gsissh;

$rsh    = DB::getGMSAttribute("krb5-rsh-program-path");
$ENV{rsh}=$rsh;

$krb5_rsh = DB::getGMSAttribute("krb5-rsh-program-path");
$ENV{krb5_rsh}=$krb5_rsh;

$Pflag= DB::getMachineAttribute($Sysnam,"Pflag");
$ENV{Pflag} = "$Pflag";

$reflag = DB::getMachineAttribute($Sysnam,"reflag");
$ENV{reflag} = "$reflag";

$bkill= DB::getMachineAttribute($Sysnam,"kill-program-path");
$ENV{bkill}=$bkill;

$bhist = DB::getMachineAttribute($Sysnam,"hist-program-path");
$ENV{bhist}=$bhist;

$schedulerType = DB::getMachineAttribute($Sysnam,"scheduler");
$ENV{schedulerType}=$schedulerType;

###########################################################################
# Amr:  a) Factor code to use DB calls and remove hardwired dependencies	  
#       b) Annotate, Comment and Indent Code
#
# First Step: Send a kill comand to the machine 
#             get the history for this job to make sure it was killed correctly
#############################################################################

if ($X509 == 1)
{
    print STDERR "Using X509 based authentication and gsi scp/ssh\n";
    ## First Step is to kill the job	
    print STDERR "just before $gsissh $Pflag $reflag $Sysnam $bkill $jobid > $TMPDIR/$KjjLog ";
    system('$gsissh $Pflag $reflag $Sysnam $bkill $jobid > $TMPDIR/$KjjLog ')==0 or die " ERROR: system command failed: $cmd: $?; \n gsissh_down_err \n ";
    ##second step is to get the history for that job to make sure that the crim was comitted superbly!
    print STDERR "just before $gsissh $Pflag $reflag $Sysnam $bhist $jobid > $TMPDIR/$KjhLog ";
    system('$gsissh $Pflag $reflag $Sysnam $bhist $jobid > $TMPDIR/$KjhLog ')==0 or die " ERROR: system command failed: $cmd: $?; \n gsissh_down_err \n ";
 }
else  #using Kerberos
{
     print STDERR "Using kerberos based authentication and scp/ssh \n";
     ## First Step is to kill the job	
     print STDERR "BEFORE $reflag $Sysnam $bkill $jobid > $TMPDIR/$KjjLog ";
     system('$krb5_rsh  $reflag $Sysnam $bkill $jobid > $TMPDIR/$KjjLog ');
     ##second step is to get the history for that job to make sure that the crim was comitted superbly!
     print STDERR "just before $krb5_rsh  $reflag $Sysnam $bhist $jobid > $TMPDIR/$KjhLog  ";
     system('$krb5_rsh  $reflag $Sysnam $bhist $jobid > $TMPDIR/$KjhLog ');

}
##################################################################
# Amr:  a) Factor code to use DB calls and remove hardwired dependencies	  
#       b) Annotate, Comment and Indent Code
#
# Second Step:  Look at the log file and see what happened
##################################################################
$Status = "Attempt to kill was performed but $Sysnam was not able to confirm!";
open(STF,"$TMPDIR/$KjjLog");
while(<STF>)
{
  s/\n//;
  $Status = $_;
  if($Status eq ""){
      $Status = "Attempt to kill was performed but $Sysnam was not able to confirm!";
  }
  
}
close (STF);
if($bhist eq ""){ # Some systems don't support the qhist for the moement
    print "Job was killed";
}
else{
	print STDERR "$Status";
	print "$Status";
}
exit 0;
