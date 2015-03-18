#!/usr/bin/perl -w

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Read the paramter sent to the script
#############################################################################################

print STDERR "ENTERING jbhist2.cgi\n";
my $ts0 = time();


 read(STDIN, $buffer, $ENV{'CONTENT_LENGTH'});
 @lines = split(/\n/,$buffer);
 foreach $line (@lines) {
 @pairs = split(/&/, $line);
 foreach $pair (@pairs)
 {
    	($name, $value) = split(/=/, $pair);
    	$value =~ tr/+/ /;
    	$value =~ s/%([a-fA-F0-9][a-fA-F0-9])/pack("C", hex($1))/eg;
 	# Stop people from using subshells to execute commands
    	$value =~ s/~!/ ~!/g; 
    	$FORM{$name} = $value;
 }
}


print "Content-type: text/plain\n\n";

(@keys) = keys(%FORM);
foreach $i (@keys){
  print STDERR "jbhist2.cgi ---: $i $FORM{$i}\n";
}


$jobid = $FORM{'JoBID'};
$_ = $jobid;
$jobid =~ s/\r//;
$jobid =~ s/\n//;

$UserName = $FORM{'Username'};
$UserName =~ s/\r//;
$GridChemUserName = $FORM{'GridChemUsername'};
$GridChemUserName =~ s/\r//;
$IsGridChem = $FORM{'IsGridChem'};
$IsGridChem =~ s/\r//;
$Sysnm = $FORM{'Sysnm'};
$Sysnm =~ s/\r//;
$Sysnm =~ s/\n//;

$ENV{jobid} = "$jobid";
$ENV{Sysnm} = "$Sysnm";
$ENV{UserName}="$UserName";
$ENV{GridChemUserName}="$GridChemUserName";
$ENV{IsGridChem}="$IsGridChem";

##$JbhLog = join("_",$UserName,"JbhLog");
$JbhLog = join("_",$GridChemUserName,"JbhLog");
##$JbjLog = join("_",$UserName,"JbjLog");
$JbjLog = join("_",$GridChemUserName,"JbjLog");

#############################################################################################
##Amr:
##  Load the resource database 
#############################################################################################

##Amr changed all hardwierd resource access with DB call
## load the DBAccess module
require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory
$securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir;
print STDERR "jbhist2.cgi: security temp directory for $GridChemUserName is $securityTmpDir\n";

$userTmpDirBase = DB::getGMSAttribute("user-temp-directory");
$ENV{userTmpDirBase}=$userTmpDirBase;

print STDERR "jbhist2.cgi: b4 set TMPDIR  BASE:$userTmpDirBase GCUName:$GridChemUserName TMPDIR:$TMPDIR\n";
#                                    Changed to new Directory Structure 10/25/05 KFM
if  ($IsGridChem eq "true"){ $TMPDIR = "$userTmpDirBase/internal/$GridChemUserName";}
else                       { $TMPDIR = "$userTmpDirBase/external/$GridChemUserName";}
print STDERR "jbhist2.cgi: 4b set TMPDIR  BASE:$userTmpDirBase GCUName:$GridChemUserName TMPDIR:$TMPDIR\n";

$ENV{TMPDIR}=$TMPDIR;

#remove old logfile
$ENV{JbhLog}="$JbhLog";
$ENV{JbjLog}="$JbjLog";
unlink "$securityTmpDir/$JbjLog";
unlink "$securityTmpDir/$JbhLog";

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Check which security architecture to use : KRB5 or X509
#############################################################################################

$X509_USER_PROXY = join("$UserName","$securityTmpDir/","_X509");
if  ($IsGridChem eq "true"){ $X509_USER_PROXY = join("$GridChemUserName","$securityTmpDir/","CCG_X509");
}
$ENV{X509_USER_PROXY}="$X509_USER_PROXY";
$X509_USER_CERT = join("$UserName","$securityTmpDir/","_X509");
$ENV{X509_USER_CERT}="$X509_USER_CERT";
print STDERR "jbhist111111***** X509: X509_USER_PROXY:$X509_USER_PROXY X509_USER_CERT:$X509_USER_CERT\n";

$GLOBUS_LOCATION = DB::getGMSAttribute("globus-directory-location");
$ENV{GLOBUS_LOCATION} = "$GLOBUS_LOCATION";

$KRB5CCNAME = "$securityTmpDir/krb5cc_$UserName";
$ENV{KRB5CCNAME} = "$KRB5CCNAME";

if (-e $X509_USER_PROXY)
{
    if (-e $KRB5CCNAME)
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

$ENV{status} = "$?";
unlink "$securityTmpDir/testrshstatus";

#check if ticket is valid else send a message back 
unlink "$TMPDIR/curtim";
unlink "$TMPDIR/kltim";

if ($X509 == 0)
{
	$checkTickets = DB::getGMSAttribute("krb5-check-ticket-program-path");
	$ENV{checkTickets}=$checkTickets;
    	require "chktickets.pl";
}

$ENV{jobid} = "$jobid";
$ENV{Sysnm} = "$Sysnm";
$Sysnam = "$Sysnm";
$ENV{Sysnam}="$Sysnam";
$ENV{UserName}="$UserName";
print STDERR "jbhist2: bjobs output for $jobid at $Sysnm \n";
print STDERR " for $UserName with X509= $X509 \n";

#############################################################################################
##  Amr: Factored the old code to use DB Calls
##
##  Now load the machine connection string and the programs available there
##  which can be used to inquire about the jobID 
#############################################################################################
#Amr Factor code March 2nd

$gsissh = DB::getGMSAttribute("globus-gsissh-program-path");
$ENV{gsissh}=$gsissh;

$rsh    = DB::getGMSAttribute("krb5-rsh-program-path");
$ENV{rsh}=$rsh;

#get Machine Specific connection attributes
$pflag= DB::getMachineAttribute($Sysnam,"Pflag");
$ENV{pflag}=$pflag;

$reflag = DB::getMachineAttribute($Sysnam,"reflag");
$ENV{reflag}=$reflag;

$bjobs= DB::getMachineAttribute($Sysnam,"jobs-program-path");
$ENV{bjobs}=$bjobs;

$bhist = DB::getMachineAttribute($Sysnam,"hist-program-path");
$ENV{bhist}=$bhist;

$schedulerType = DB::getMachineAttribute($Sysnam,"scheduler");
$ENV{schedulerType}=$schedulerType;

if ( $X509 eq 1 )
{
   $connection = $gsissh;
   $flag = $pflag;
}
else{
    $connection = $rsh;
    $flag = $reflag;
}

$ENV{connection}=$connection;
$ENV{flag} = "$flag";

#Amr change to get the user name from the machine itself                                                                                                                          
($UserName,$scrd) = DB::getScratchDirectory($Sysnam,$gsissh,$pflag,$reflag,$TMPDIR,$rsh,$X509);
$ENV{UserName}= $UserName;


#############################################################################################
##  Amr:  Factor Old code to use DB calls and make use of Batch System similarities
##
##  now try to write a statment for each scheduler not for each machine
#############################################################################################

if($schedulerType eq "ll"){
      system('$connection $flag -l $UserName $Sysnm $bjobs $jobid | awk \'{if (NR == 3) print $5} \' > $TMPDIR/$JbjLog');
      print STDERR "jbhist2: bjobs output written to  $TMPDIR/$JbjLog \n";
      system('echo $status > $TMPDIR/testrshstatus ');
      print STDERR "before $connection $flag -l $UserName $Sysnm $bhist $jobid > $TMPDIR/$JbhLog  2>&1";
      system('$connection $flag -l $UserName $Sysnm $bhist $jobid > $TMPDIR/$JbhLog  2>&1');
      system('echo $status > $TMPDIR/testrshstatus ');
}
elsif ($schedulerType eq "lsf")
{
       system('$connection $flag -l $UserName  $Sysnm $bjobs -w $jobid | awk \'{if (NR == 2) print $3} \' > $TMPDIR/$JbjLog '); 
       print STDERR "jbhist2: bjobs output written to $TMPDIR/$JbjLog \n";
	 system('echo $status > $TMPDIR/testrshstatus ');
	 system('$connection $flag -l $UserName $Sysnm $bhist -w $jobid > $TMPDIR/$JbhLog  2>&1');
	 system ('echo $status > $TMPDIR/testrshstatus ');
}
elsif ($schedulerType eq "pbs")
{
        system('$connection $flag -l $UserName $Sysnm $bjobs $jobid | awk \'{if (NR == 3) print $5} \' > $TMPDIR/$JbjLog ');
        print STDERR "jbhist2: bjobs output written to  $TMPDIR/$JbjLog \n";
        system('echo $status > $TMPDIR/testrshstatus ');
        system('$connection $flag -l $UserName $Sysnm $bhist $jobid > $TMPDIR/$JbhLog  2>&1');
        system('echo $status > $TMPDIR/testrshstatus ');
}
elsif ($schedulerType eq "condor")
{
        system('$connection $flag -l $UserName $Sysnm $bjobs $jobid | grep $jobid | awk \'{print $6} \' > $TMPDIR/$JbjLog ');
        print STDERR "jbhist2: bjobs output written to  $TMPDIR/$JbjLog \n";
        system('echo $status > $TMPDIR/testrshstatus ');
        system('$connection $flag -l $UserName $Sysnm $bhist $jobid > $TMPDIR/$JbhLog  2>&1');
        system('echo $status > $TMPDIR/testrshstatus ');
}

#############################################################################################
##  Amr:Annotate, comment and Indent code.
##
##  If the job is still running, return its status and exit
#############################################################################################

#$ENV{Sublog} = "$TMPDIR/$Sublog";
$JStatus = "Unknown";
$ENV{JStatus} = "$JStatus";

open(STF,"$TMPDIR/$JbjLog");
while(<STF>)
{
  s/\n//;
 
  if ($_ eq "") 
  {
    $JStatus = "Done";
    $ENV{JStatus} = "$JStatus";
    system(' echo "$JStatus 1" > $TMPDIR/testjbhist ');
  }
  else 
  {
    $JStatus = $_;
    $ENV{JStatus} = "$JStatus";

    system(' echo "$JStatus 2" > $TMPDIR/testjbhist ');
    ## added by Amr Feb 2
    #print "Job Status is $JStatus";
    $msg = getStatusMessage($JStatus);
    print $msg;
    print STDERR "Job status is $JStatus";
    exit 0;
  }
}
close(STF);

#############################################################################################
##  Amr: 1) Annotate, comment and Indent code.
##       2) Factor Old code to make use of batch system similarities.. (not need to write an if
##          statment for each machine!)
##  
## if we reach here it means that the job has finsihed and we need to use the history file associated with it.
## as usual we need to write a statment for each scheduler as we already got the output of the history program,
## we just need to parse it to get the required information 
#############################################################################################

if ($schedulerType eq "ll")
{
   $JStatus=`/bin/grep Status $TMPDIR/$JbhLog `;
   #Amr April,8,2005
   if($JStatus eq ""){ #some thing is wrong so just return what the resource said
       open(STF,"$TMPDIR/$JbhLog");
       @outmsg = <STF>;
       print STDERR "here is what I get @outmsg";
       print "$outmsg[0]";
   }
   else{
       @JSfs = split(" ",$JStatus);
       $JStatus = $JSfs[3]; 
       $JStatus=~ s/\n/ /;
       $CPU =`/bin/grep Cpu $TMPDIR/$JbhLog `;
       @CPUfs=split(" ",$CPU);
       $CPU =$CPUfs[3]; 
       $CPU =~ s/\n/ /;
       $PMem=`/bin/grep "Peak" $TMPDIR/$JbhLog `;
       @PMfs=split(" ",$PMem);
       $PMem=$PMfs[4];
       $PMem=~ s/\n/ /;
       $SUS=`/bin/grep Service $TMPDIR/$JbhLog `;
       @SUfs= split(" ",$SUS);
       $SUS=$SUfs[3];
       $SUS=~ s/\n/ /;
       print "JOB Id $jobid Status: $JStatus CPU_time: $CPU Peak Memory: $PMem Total Sus: $SUS"; 
   }
}
elsif ($schedulerType eq "pbs")
{
   print "The job has finished but history information for $Sysnam is not available"; 
}
elsif ($schedulerType eq "condor")
{
   print "The job has finished but history information for $Sysnam is not available"; 
}
elsif ($schedulerType eq "lsf")  #need to be verified
{
	open(TMPFIL,"$TMPDIR/$JbhLog");
	#$lnc = 0;
	$lnc = 0;
	while(<TMPFIL>) {
		if ($lnc eq 2) 
  		{
    			@Flds = split(' ',$_,20);
    			if ($#Flds eq 9) 
			   {
       				print "JOB Id $jobid Status: $JStatus  Spent Time (in Secs) PEND: $Flds[2] PSUSP: $Flds[3] RUN: $Flds[4] USUSP: $Flds[5] SSUSP: $Flds[6], UNKWN: $Flds[7] Total: $Flds[8]";
			    }
    			elsif ($#Flds eq 0)
			{
				print "Job Id $jobid Status: $Flds[0]";
			 }
    			else 
			     {	 
         				print "JOB Id $jobid Status: $JStatus  Spent Time (in Secs) PEND: $Flds[3] PSUSP: $Flds[4] RUN: $Flds[5] USUSP: $Flds[6] SSUSP: $Flds[7], UNKWN: $Flds[8] Total: $Flds[9]";
         				print STDERR " $#Flds JOB Id $jobid Status: $JStatus  Spent Time (in Secs) PEND: $Flds[3] PSUSP: $Flds[4] RUN: $Flds[5] USUSP: $Flds[6] SSUSP: $Flds[7], UNKWN: $Flds[8] Total: $Flds[9]";
      		}

 		}
 		else
 		{
  			@Flds = split(' ',$_,20);
  			if ($Flds[0] eq "No" && $Flds[1] eq "matching")
  			{
    				print "JOB Id $jobid is DONE";
  			}
 		}
		$lnc++;
	}
	close (TMPFIL);
}
exit 0;

#***********************************************************************************************************************8
sub getStatusMessage{
    my $code = $_[0];
    
    if ( $code eq "C"){
	return "The job was Completed";
    }elsif($code eq "CA"){
	return "The job was Canceled";
    }elsif($code eq "CP"){
	return "The job is Complete Pending";
    }elsif($code eq "D"){
	return "The job was Deferred";
    }elsif($code eq "E"){
	return "The job was Preempted";
    }elsif($code eq "I"){
	return "The job is Idle";
    }elsif($code eq "NR"){
	return "The job is not running";
    }elsif($code eq "P"){
	return "The job is Pending";
    }elsif($code eq "R"){
	return "The job is Runing";
    }elsif($code eq "RM"){
	return "The job was Removed";
    }elsif($code eq "S"){
	return "The job is in System Hold";
    }elsif($code eq "TX"){
	return "The job was Terminated";
    }elsif($code eq "X"){
	return "The job was Rejected";
    }elsif($code eq "SX"){
	return "The job has a Submission Errorc";
    }elsif($code eq "ST"){
	return "The job has been Started";
    }else{
	return "The job status is $code";
    }
}

my $ts1 = time();
my $td  = $ts1-$ts0;
print STDERR "LEAVING jbhist2.cgi $td\n";

