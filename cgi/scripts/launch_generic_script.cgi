#!/usr/bin/perl  -w
#############################################################################################
# 2005/08/17 stelios I will use this as the common script - and have soft links to this for 
#            gauss_launch2.cgi etc
#
##Amr: Annotate, comment and Indent code.
##
##  Read the parameter sent to the script
#############################################################################################

use lib '.';
use DBAccess;
use English;
use File::Basename;



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
    }
}


print "Content-type: text/plain\n\n";

#lixh_add
# 2006/10/09 skk adding some error handling - todo: do all of them 
$UserName = $FORM{'Username'} or die "ERROR: INPUT TO THE CGI SCRIPT IS INSUFFICIENT - we need a username";
$UserName =~ s/\r//;
$GridChemUserName = $FORM{'GridChemUsername'};
$GridChemUserName =~ s/\r//;
$IsGridChem = $FORM{'IsGridChem'};
$IsGridChem =~ s/\r//;
$Sysnam = $FORM{'Rem_Syst'};
$Sysnam =~ s/\r//;
$computerName = "$Sysnam";
$Accnt = $FORM{'Account'};
$Accnt =~ s/\r//;
$Queue = $FORM{'Queue'};
$Queue =~ s/\r//;
$Time = $FORM{'Time'};
$Time =~ s/\r//;
$NP = $FORM{'NP'};
$NP =~ s/\r//; 
$Resproj = $FORM{'ResProj'};
$Resproj =~ s/\r//;
$Inpfil = $FORM{'InpFil'};
$Inpfil =~ s/\n//;
$Inpfil =~ s/\r//;
$jobName = $Inpfil;



################################################################
# SPECIFIC VARS TO APPLICATIONS
# get the $application from the $PROGRAM_NAME

# 2006/08/14 skk - we want to have just a single launch file rather than 5 - so this block was moved further down to be able to use the new input ApplicationName which should be gamess_launch or gauss_launch or nwchem_launch or molpro_launch or ....
if ( defined $FORM{'ApplicationName'} ) {  
  $ApplicationName = $FORM{'ApplicationName'};
  $ApplicationName =~ s/\r//;
}else{  # DEPRECIATED WAY
  $ApplicationName=basename("$PROGRAM_NAME");
}

$do_condorgfork="";  #empty means dont do condorfork
if ($ApplicationName =~ /gamess_launch/i){
    $application='Gamess';
    $Inpfil_ending=".inp";
    $appname_getMachineApplicationPath="GAMESS";
}elsif ($ApplicationName =~ /gauss_launch/i){
    $application='Gaussian';
    $Inpfil_ending=".inp";
    $appname_GINPFILNAM="Gauss";
    $do_condorgfork=1; 
}elsif ($ApplicationName =~ /nwchem_launch/i){
    $application='NWChem';
    $Inpfil_ending=".nw";
}elsif ($ApplicationName =~ /molpro_launch/i){
    $application='Molpro';
    $Inpfil_ending=".inp";
}elsif ($ApplicationName =~ /amber_launch/i){  # 2006/08/25 adding amber
    $application='Amber';
    $Inpfil_ending=".inp";
#### 2006/09/11 skk - to add qmcpack in the unified system 
}elsif ($ApplicationName =~ /qmcpack_launch/i){   
    $application='QMCPACK'; 
     $Inpfil_ending=".xml"; 
}elsif ($ApplicationName =~ /adf_launch/i){   
    $application='ADF'; 
     $Inpfil_ending=".inp"; 
}elsif ($ApplicationName =~ /wien2k_launch/i){   
    $application='WIEN2K'; 
     $Inpfil_ending=".inp"; 
}else{
    print "The program name $ApplicationName is not in the list of applications\n";
}
$appname_GINPFILNAM="$application" unless ($appname_GINPFILNAM);
$appname_getMachineApplicationPath="$application" unless ($appname_getMachineApplicationPath);
print STDERR "Application is $application\n";

################################################################


$Inpfil = "$Inpfil"."$Inpfil_ending"; 

$InpTxt = $FORM{'Inptxt'};

$ENV{GridChemUserName} = "$GridChemUserName";
$ENV{IsGridChem} = "$IsGridChem";
$ENV{Resproj} = "$Resproj";
$ENV{UserName} = "$UserName";
$ENV{Sysnam} = "$Sysnam";
$ENV{Accnt} = "$Accnt";
$ENV{Queue} = "$Queue";
$ENV{Time} = "$Time";
$ENV{NP} = "$NP";
$ENV{Inpfil} = "$Inpfil";

# 2006/10/20 skk 
###$ENV{InpTxt} = "$InpTxt";
$ENV{jobName} = "$jobName";
$ENV{computerName} = "$computerName";

if ($do_condorgfork){
# lixh_add check for Condor-G launch
    if ($Sysnam eq "Any")
    {
	require "condorgfork_launch2.pl";
	exit;
    }
    
# lixh for ccguser test
#if ($IsGridChem eq "true")
#{
#   if ($Sysnam ne "cu.ncsa.uiuc.edu")
#   {
#      print "gridchemauth_error: $Sysnam is not availabe, please choose cu.ncsa.uiuc.edu";
#      exit;
#   }
#}
}


$SubLog = join("_",$UserName        ,"SubLog");  # TODO 2005/12/29 skk - i assume this is not needed since overwritten by line below?
$SubLog = join("_",$GridChemUserName,"SubLog");
$ENV{SubLog} = "$SubLog";

#############################################################################################
##Amr: Add DB support
##
##  Load the resource database 
#############################################################################################

# 2005/08/18 skk - we should not hardcode the directory name - because these files will become production at every sync we do.
### 2006/07/31 skk - reverted back to no path - 
### 2006/07/31 require("/var/www/cgi-bin/devel/DBAccess.pl");
require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file 

# get the security temp directory
$securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir ;

$userTmpDirBase = DB::getGMSAttribute("user-temp-directory");
$ENV{userTmpDirBase}=$userTmpDirBase;

#remove old Logfile
if ($IsGridChem eq "true")
{
   $TMPDIR = "$userTmpDirBase/internal/$GridChemUserName";
}
else
{
   $TMPDIR = "$userTmpDirBase/external/$GridChemUserName";
}
$ENV{TMPDIR} = "$TMPDIR";

# If the TMPDIR does not exist create one here 
# Sudhakar 11/11/05 

if (!(-e "$TMPDIR")) {
 print STDERR " Creating the $TMPDIR for the User $GridChemUserName\n";
 mkdir ($TMPDIR, 0775);
}

unlink "$TMPDIR/$SubLog";

$GINPFILNAM = join("_",$UserName,"${appname_GINPFILNAM}_input");
$GINPFILNAM = join("_",$GridChemUserName,"${appname_GINPFILNAM}_input");


$ENV{GINPFILNAM} = "$GINPFILNAM";

unlink "$TMPDIR/$GINPFILNAM";

open(INPTXT,">$TMPDIR/$GINPFILNAM");
print INPTXT "$InpTxt";
close(INPTXT);
### 2006/10/18 skk - we dont want to output the text i think - sometimes it is huge and fills up error_log with no reason
###2006/10/20 print STDERR "\$InpTxt is:\n$InpTxt \n";
print STDERR "\$TMPDIR/\$GINPFILNAM =  $TMPDIR/$GINPFILNAM\n";  # 2006/10/18 skk temp
print STDERR `/bin/ls -l $TMPDIR/$GINPFILNAM`;
print STDERR "\n";
# Copy the file to a scratch space

$INPFILNAM = "$TMPDIR/$GINPFILNAM";
$ENV{INPFILNAM} = "$INPFILNAM";

print STDERR "$UserName at ${application}launch\n";
print STDERR `id -g`;
print STDERR `id -u`;
print STDERR `id -ru`;
print STDERR `id -rg`;

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Check which security architecture to use : KRB5 or X509
#############################################################################################

$X509 = 0; # this variable says whether or not to use X509

# lixh_add
$X509_USER_PROXY = join('',"$securityTmpDir/","$UserName","_X509");
if ($IsGridChem eq "true")
{
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
	if ($x509stat[9] > $krbstat[9]) # 2005/12/29 skk - it checks if the modification date is newer 
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
unlink "/$TMPDIR/testrshstatus";
unlink "/$TMPDIR/bsubstatus";

#check if ticket is valid
unlink "$TMPDIR/curtim";
unlink "$TMPDIR/kltim";
if ($X509 == 0)
{
   $checkTickets = DB::getGMSAttribute("krb5-check-ticket-program-path");
   $ENV{checkTickets}= $checkTickets;
   print STDERR "calling $checkTickets\n";
   # 2005/08/18 skk - why NOT "require "$checkTickets";"
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
##  Load machine specific information 1)flags 2) Application path 3) scratch directory
#############################################################################################

$gsissh = DB::getGMSAttribute("globus-gsissh-program-path");
$ENV{gsissh}=$gsissh;

$gsiscp = DB::getGMSAttribute("globus-gsiscp-program-path");
$ENV{gsiscp}=$gsiscp;

$rsh    = DB::getGMSAttribute("krb5-rsh-program-path");
$ENV{rsh}=$rsh;

$krb5_scp = DB::getGMSAttribute("krb5-scp-program-path"); 
$ENV{krb5_scp}=$krb5_scp;

$krb5_rsh = DB::getGMSAttribute("krb5-rsh-program-path"); 
$ENV{krb5_rsh}=$krb5_rsh; 

$Pflag= DB::getMachineAttribute($Sysnam,"Pflag");
$SCPflag = DB::getMachineAttribute($Sysnam,"SCPflag");
$reflag = DB::getMachineAttribute($Sysnam,"reflag");
$LindaIsAvailable=  DB::getMachineAttribute($Sysnam,"LindaIsAvailable");
###print STDERR "\nstelios DEBUG: Sysnam=$Sysnam\n";
$bjobs= DB::getMachineAttribute($Sysnam,"jobs-program-path");
$ENV{bjobs}=$bjobs;

$bhist = DB::getMachineAttribute($Sysnam,"hist-program-path");
$ENV{bhist}=$bhist;

$schedulerType = DB::getMachineAttribute($Sysnam,"scheduler");
$ENV{schedulerType}=$schedulerType;

$qg = DB::getMachineApplicationPath($Sysnam,$appname_getMachineApplicationPath)  or die "ERROR: There is no such application $appname_getMachineApplicationPath in resource $Sysnam. \n";

#$scrDirBase   = DB::getMachineAttribute($Sysnam, "scratch-directory-base");
#$ENV{scrDirBase}=$scrDirBase;
#$scrd ="$scrDirBase/$UserName"; 

#Amr change to get the user name from the machine itself
($UserName,$scrd) = DB::getScratchDirectory($Sysnam,$gsissh,$Pflag,$reflag,$TMPDIR,$rsh,$X509);   # 2006/03/29 skk - scrd is same as the outdir (ouput dir) 

# get machine's globus node name
$Sysnam = DB::getMachineGlobusnodename($Sysnam);
$ENV{Sysnam} = "$Sysnam";

$ENV{qg} = "$qg";
$ENV{scrd} = "$scrd";
$ENV{UserName}=$UserName;

$UsratRH = "$UserName\@$Sysnam";  # USeRname AT RemoteHost 
$ENV{UsratRH} = "$UsratRH";

$ENV{reflag} = "$reflag";
$ENV{Pflag} = "$Pflag";
$ENV{SCPflag} = "$SCPflag";

##################################################################
# lixh_add
# check to see if the project name is valid or not
#print STDERR "before calling psn_check";
#if ($Sysnam eq "cu.ncsa.uiuc.edu" || $Sysnam eq "tun.ncsa.uiuc.edu")
#{
#   require "psn_check.pl";
#   if ($ENV{psn_check} eq "false")
#   {
#      print STDERR "$Accnt is not a valid project for $UserName \n";
#      exit;
#    }
#} 
##################################################################

$date = `date`;
print STDERR "$UserName:$GridChemUserName Authenticated via QCRJM at $date \n";

##################################################################
# Amr:  a) Factor code to use DB calls and remove hardwired dependencies	  
#       b) Annotate, Comment and Indent Code
#
# Step 1: Copy the input file into the remote system
##################################################################


if ($X509 == 1){
    # check if scratch directory scrd exists on the remote machine and create it if not 
    $tempdirs_name="tempdirs";      # 2006/03/31 Added the tempdirs subdir to clean up things a little

    # 2006/10/03 skk - IMPORTANT - IF YOU CHANGE THE CODE BELOW  - THEN CHANGE WEB SERVICES TOO
    if ($IsGridChem eq "true") {
	$scrd = "$scrd/$GridChemUserName/$tempdirs_name";
    }else{
      # (2006/03/31 skk i dont think this comment is valid now>>) for External Users we also need to add a $scrd with username Sudhakar 11/11/05
      $scrd = "$scrd/$tempdirs_name"; 
      print STDERR "Scratch directory for external user $UserName is defined as $scrd\n"; 
    }
    $ENV{scrd} = "$scrd";
    ###2006/03/31 $cmd="$gsissh $Pflag -f $reflag $Sysnam \" /bin/ls -d $scrd 2>&1 >/dev/null || mkdir -p $scrd \" ";
    $cmd="$gsissh $Pflag -f $reflag $Sysnam \"mkdir -p $scrd \" ";   # 2006/03/31 skk - is the -f for speed? is it ok to have it?
    print STDERR "BEFORE cmd: $cmd\n";
    ###2006/03/31 this silly qx was creating a lot of trouble for me -- $tmpout=qx($cmd) or die "ERROR: command \"$cmd\": $!\n";
    system($cmd)==0 or die "ERROR: system command failed: $cmd: $?;  \n gsissh_down_err \n  ";
    # 2005/08/18 skk - create a unique temp dir to put all files there - to avoid possible filename collision with other simultaneous runs
    $TEMP_DIRNAME="$scrd/tempdirXXXXXXX";
    $cmd='$gsissh $Pflag -f $reflag $Sysnam "perl -e \'use File::Temp qw/ :mktemp /; \$tmpdir = mkdtemp( \"' . "$TEMP_DIRNAME" . '\" );print  \$tmpdir\'"';
    print STDERR "BEFORE cmd: $cmd\n";
    # 2005/09/16 
    $temp_dirname=qx($cmd) or die "ERROR: not able to create a temporary directory name using command \"$cmd\": $!\n   \n gsissh_down_err \n ";
    print STDERR "AFTER cmd: $cmd\n\$temp_dirname=$temp_dirname\n";
    $scrd = "$temp_dirname";
    $ENV{scrd} = "$scrd";

    print STDERR "Using X509 based authentication and gsi scp/ssh\n";
    print STDERR "Howdy pardner let's get the show on the road! $INPFILNAM \n";
    print STDERR "just before scp-ing the input file to host: \n   $gsiscp $SCPflag $INPFILNAM $Sysnam:$scrd/$Inpfil\n";
###2006/09/11 skk    system('$gsiscp -v $SCPflag $INPFILNAM $Sysnam:$scrd/$Inpfil')==0 or die "\n gsissh_down_err \n(gsiscp actually) \n";
    system('$gsiscp $SCPflag $INPFILNAM $Sysnam:$scrd/$Inpfil')==0 or die "\n gsissh_down_err \n(gsiscp actually) \n";
    print STDERR "just after scp-ing the input file to host\n";
    print STDERR "right after getting the show on the road: $gsiscp $SCPflag $INPFILNAM $Sysnam:/$scrd/$Inpfil\n";
}else{ 
     #using Kerberos
     print STDERR "Using kerberos based authentication and scp/ssh \n";
     print STDERR "BEFORE $krb5_scp $INPFILNAM $UsratRH:$scrd/$Inpfil >> $TMPDIR/scp.err  \n";
     system('$krb5_scp $INPFILNAM $UsratRH:$scrd/$Inpfil >> $TMPDIR/scp.err');
     print STDERR "AFTER $krb5_scp $INPFILNAM $UsratRH:$scrd/$Inpfil \n";
}

#############################################################################
# Amr:  Annotate, Comment and Indent Code
#        add DB support
#
# Step 2: Check that the input file was indeed copied into the remote system
#############################################################################

$InpPath = "$scrd/$Inpfil";
$ENV{InpPath} = "$InpPath";
print STDERR "InpPath = $InpPath \n";

#Check if the file is really copied to avoid repeat prompts from Application
unlink "$TMPDIR/inpfilchk";

if ($X509 eq 1)
{
    print STDERR "Just before the gsissh\n";
    system('$gsissh $Pflag -f $reflag $Sysnam /bin/ls $InpPath > $TMPDIR/inpfilchk ')==0 or die "ERROR:   \n gsissh_down_err \n ";
    print STDERR "Just after the gsissh $gsissh $Pflag -f $reflag $Sysnam /bin/ls $InpPath > $TMPDIR/inpfilchk\n";
}
else
{
    print STDERR "Just before the rsh\n";
    system('$krb5_rsh -f  $reflag -l $UserName $Sysnam /bin/ls $InpPath > $TMPDIR/inpfilchk ');
    print STDERR "Just after the rsh $krb5_rsh -f $reflag -l $UserName $Sysnam /bin/ls $InpPath > $TMPDIR/inpfilchk\n";
}

sleep 1;
print STDERR "I am here before openining the file";
open(IFC,"$TMPDIR/inpfilchk");
print STDERR "I am here just after opening the file\n";
while(<IFC>)
{
    s/\n//;
    print STDERR "$_";
    if ($_ ne $InpPath)
    {
        print "Error in Kerberos Credentials \n";
        print STDERR "Error in Kerberos Credentials \n";
        system ('echo "Error in rcp for input" >>  /$TMPDIR/testrshstatus ');
        exit;
    }
}
close(IFC);
print STDERR "I am here beore starting to lauch the application script";

$Logfil = "$TMPDIR/$SubLog";
$ENV{Logfil} = "$Logfil";

#Launch the  job 
# 2006/03/08 skk - use a unique name for the glsublog which has the results from the job submission and would be needed for debugging (we dont want it overwritten in the next submission)

$GLSUBLOG="$TMPDIR/glsublog.$PROCESS_ID";
print STDERR "DEBUG: GLSUBLOG IS $GLSUBLOG\n";

###2006/03/08 unlink "$TMPDIR/glsublog";
unlink "$GLSUBLOG";
#############################################################################
# Amr:  Annotate, Comment and Indent Code
#        add DB support--no need for a statement for each site any more
#
# Step 3: Launch the Application on the remote system
#
#############################################################################

print STDERR "DEBUG_20051220 the variable Time is =$Time \n";
@temptime = split(":",$Time);
$hh = $temptime[0];
$mm = $temptime[1];
$Time = "$hh:$mm";
print STDERR "DEBUG_20051220 AFTER TRANSFORM the variable Time is =$Time  \n";

# 2005/12/20 skk - comment the following line since we should correct this in the common scripts
# 2005/12/20 skk - if ($Sysnam eq "cu.ncsa.uiuc.edu"){ $Time="$hh:$mm:59";}
$ENV{Time} = "$Time";
my $cmd1_part1;
my $cmd1_part2=" $reflag $Sysnam $qg -a $Accnt -q $Queue -W $Time -n $NP -g $GridChemUserName -p $Resproj -e ccg-admin\@ccg-mw1.ncsa.uiuc.edu -O $scrd -l $LindaIsAvailable $InpPath 2>&1 | tee  $GLSUBLOG ";
if ($X509 eq 1){   
  $cmd1_part1 ="$gsissh $Pflag ";
}else{
  $cmd1_part1="$krb5_rsh -l $UserName ";
};
my $cmd1="$cmd1_part1  $cmd1_part2";
print STDERR "Before executing: $cmd1 \n"; 
system($cmd1)==0 or die "system command failed: $cmd1: $?; \n gsissh_down_err (or kerberos??) \n  ";
###print STDERR "DEBUG20060927: $?";
###exit 1;




open(SFC,"$GLSUBLOG");
while(<SFC>)
{
    s/\n//;
    if ($_ eq "You have 5 jobs queued or running." )
    {
    		print "Err:Bsub failed \n";
    		system ('echo "Error in bsub: Number of jobs may have exceeded limit(5)" >>  /$TMPDIR/bsubstatus ');
    		exit;
    }
}
close(SFC);
system('echo $status > /$TMPDIR/testrshstatus ');

# FIND JOBID (it is assumed to be a  positive integer
# 2005/12/29 ASSUMPTION: the clusterscript has already put the keyword JOBID_FROM_CLUSTER_SCRIPT inside the file
my $tmp_cmd1="grep JOBID_FROM_CLUSTER_SCRIPT $GLSUBLOG| awk '{print \$2}'";
print STDERR "Before $tmp_cmd1 \n";
my $jobid=`$tmp_cmd1`; 
die "system command failed: $tmp_cmd1: status=$?; output=$jobid " if $?;
chomp $jobid;
$jobid =~ /^\d+$/ or die "jobid should be a positive integer: instead jobid is: $jobid";   # 2005/12/29 sanity check
# 2005/10/24 the wording to identify "JOBID" HAS TO BE UNIQUE - sdx output has  JOBID in its output - so we will use LOCAL_JOBID to be unique
print STDERR "LOCAL_JOBID $jobid \n";
print "LOCAL_JOBID $jobid \n";

# 2006/03/30 skk I AM NOT USING THIS $scrd_new yet because we may switch to just using the gridchem_jobid as the directory name in a few weeks??? 
# 2006/03/30 skk - get the name of the outputdir from the cluster output
# 2005/12/29 ASSUMPTION: the clusterscript has already put the keyword JOBID_FROM_CLUSTER_SCRIPT inside the file
$tmp_cmd1="grep SCRATCHDIR_FROM_CLUSTER_SCRIPT $GLSUBLOG| awk '{print \$2}'";
print STDERR "Before $tmp_cmd1 \n";
my $scrd_new=`$tmp_cmd1`;
die "system command failed: $tmp_cmd1: status=$?; output=$scrd_new " if $?;
chomp $scrd_new;

# 2006/03/30 TODO scrd_new should become scrd ???

### 2006/10/06 (chona)
### Set Jobs.created to the date returned by JOB_SUBMITTED_DATE
###
my $tmp_date=`grep JOB_SUBMITTED_DATE $GLSUBLOG`;
chomp $tmp_date;
$tmp_date=~s/JOB_SUBMITTED_DATE=//g;
# The following line didn't parse the date correctly.  I modified it to do so -- RD 10/8/06
$tmp_date=~/([\d]{2})([\d]{2})([\d]{2})/;
# the dates are going into the db backwards.  i'm trying to flip the day and year to see if that works.
my $tmp_year=$1; my $tmp_month=$2; my $tmp_day=$3;
#$tmp_date="$tmp_year-$tmp_month-$tmp_day";
$tmp_date="$tmp_month-$tmp_day-$tmp_year";
print STDERR "JOB_SUBMITTED_DATE=$tmp_date\n";


# update DB.job
updatejobTable($jobid,$jobName,$Resproj,$scrd,$NP,$InpTxt,$Time,$GridChemUserName,$computerName,$application);
#updatejobTable2($jobid,$jobName,$Resproj,$Accnt,$scrd,$NP,$InpTxt,$Time,$GridChemUserName,$computerName,$application,$Queue);

### Uncomment this later to pass job submission time parameter ($tmp_date) to
### updatejobTable2
updatejobTable2($jobid,$jobName,$Resproj,$Accnt,$scrd,$NP,$InpTxt,$Time,$tmp_date,$GridChemUserName,$computerName,$application,$Queue);
###updatejobTable2($jobid,$jobName,$Resproj,$Accnt,$scrd,$NP,$InpTxt,$Time,$tmp_date,$GridChemUserName,$computerName,$application,$Queue);
if ($dbh) {
  $dbh->disconnect;
} 

