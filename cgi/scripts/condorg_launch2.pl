#!/usr/bin/perl -w 

# by Xiaohai Li 6/12/05
# create a condorg submit file, and submit it. Currently condor pool
# only contains cu.ncsa.uiuc.edu, tunb.ncsa.uiuc.edu, and ccg-login.ncsa.uiuc.edu

use CGI qw/:standard/;
use strict;

my $UserName = $ENV{UserName};
my $Sysnam = $ENV{Sysnam};
my $Time = $ENV{Time};
my @temptime = split(":",$Time);
my $hh = $temptime[0];
my $mm = $temptime[1];
$Time = "$hh:$mm";
$ENV{Time} = "$Time";
my $NP = $ENV{NP};
my $Inpfil = $ENV{Inpfil};
$Inpfil = "$Inpfil.inp";
$ENV{Inpfil} = "$Inpfil";
my $InpTxt = $ENV{InpTxt};

#############################################################################################
##Amr: Add DB support
##
##  Load the resource database
#############################################################################################

require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory
my $securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir ;

my $userTmpDirBase = DB::getGMSAttribute("user-temp-directory");
$ENV{userTmpDirBase}=$userTmpDirBase; 

#remove old Logfile
my $SubLog = join("_",$UserName,"SubLog");
$ENV{SubLog} = "$SubLog";
my $TMPDIR = "$userTmpDirBase/$UserName";
$ENV{TMPDIR} = "$TMPDIR";
unlink "$TMPDIR/$SubLog";

print STDERR "$UserName on condorgfork_launch2 \n";

my $X509_USER_PROXY = join('',"/$securityTmpDir/","$UserName","_X509");
$ENV{X509_USER_PROXY} = "$X509_USER_PROXY";
my $GLOBUS_LOCATION = DB::getGMSAttribute("globus-directory-location"); 
$ENV{GLOBUS_LOCATION} = "$GLOBUS_LOCATION";

my $LIBPATH = "/usr/local/globus/lib:/usr/lib:/lib";
$ENV{LIBPATH} = "$LIBPATH";
my $GLOBUS_PATH = "/usr/local/globus";
$ENV{GLOBUS_PATH} = "$GLOBUS_PATH";
my $LD_LIBRARY_PATH = "/usr/local/globus/lib";
$ENV{LD_LIBRARY_PATH} = "$LD_LIBRARY_PATH";
my $LM_LICENSE_FILE = "tuncm\@7496:/usr/local/pgi/license.dat";
$ENV{LM_LICENSE_FILE} = "$LM_LICENSE_FILE";
my $SHLIB_PATH = "/usr/local/globus/lib";
$ENV{SHLIB_PATH} = "$SHLIB_PATH";
my $GLOBUS_TCP_PORT_RANGE = "62500,64500";
$ENV{GLOBUS_TCP_PORT_RANGE} = "$GLOBUS_TCP_PORT_RANGE";


#my $Sysnam_tun = "tunb.ncsa.uiuc.edu";
#$ENV{Sysnam_tun} = "$Sysnam_tun";
#unlink "$TMPDIR/HOME.out";
#system('/swarna2/gt2/bin/gsissh -x -p 222 $Sysnam_tun whoami >> $TMPDIR/HOME.out ');
#open(OFC,"$TMPDIR/HOME.out");
#my $UserName_tun = <OFC>;
#close(OFC);
#$UserName_tun =~ s/\r//g;
#$UserName_tun =~ s/\n//g;
#$ENV{UserName_tun} = "$UserName_tun";
#my $Scrd_tun = "/cfs/scratch/users/$UserName_tun";
#$ENV{Scrd_tun} = "$Scrd_tun";
#my $Pflag_tun = "-p 222";
#$ENV{Pflag_tun} = "$Pflag_tun";
#my $SCPflag_tun = "-P 222";
#$ENV{SCPflag_tun} = "$SCPflag_tun";

#my $Sysnam_cu = "cu.ncsa.uiuc.edu";
#$ENV{Sysnam_cu} = "$Sysnam_cu";
#unlink "$TMPDIR/HOME.out";
#system('/swarna2/gt2/bin/gsissh -x -p 22 $Sysnam_cu whoami >> $TMPDIR/HOME.out');
#open(OFC,"$TMPDIR/HOME.out");
#my $UserName_cu = <OFC>;
#close(OFC);
#$UserName_cu =~ s/\r//g;
#$UserName_cu =~ s/\n//g;
#$ENV{UserName_cu} = "$UserName_cu";
#my $Scrd_cu = "/scratch/users/$UserName_cu";
#$ENV{Scrd_cu} = "$Scrd_cu";
#my $Pflag_cu = "-p 22";
#$ENV{Pflag_cu} = "$Pflag_cu";
#my $SCPflag_cu = "-P 22";
#$ENV{SCPflag_cu} = "$SCPflag_cu";

#my $Sysnam_ccg = "ccg-login.ncsa.uiuc.edu";
#$ENV{Sysnam_ccg} = "$Sysnam_ccg";
#unlink "$TMPDIR/HOME.out";
#system('/swarna2/gt2/bin/gsissh -x -p 22 $Sysnam_ccg whoami >> $TMPDIR/HOME.out');
#open(OFC,"$TMPDIR/HOME.out");
#my $UserName_ccg = <OFC>;
#close(OFC);
#$UserName_ccg =~ s/\r//g;
#$UserName_ccg =~ s/\n//g;
#$ENV{UserName_ccg} = "$UserName_ccg";
#my $Scrd_ccg = "/home/$UserName_ccg/storage";
#$ENV{Scrd_ccg} = "$Scrd_ccg";
#my $Pflag_ccg = "-p 22";
#$ENV{Pflag_ccg} = "$Pflag_ccg";
#my $SCPflag_ccg = "-P 22";
#$ENV{SCPflag_ccg} = "$SCPflag_ccg";


#print STDERR "********************************$UserName_cu, $UserName_ccg********\n";


# application input files
my $GINPFILNAM = join("_",$UserName,"gauss_input");
$ENV{GINPFILNAM} = "$GINPFILNAM";
unlink "$TMPDIR/$GINPFILNAM";

open(INPTXT,">$TMPDIR/$GINPFILNAM");
print INPTXT "$InpTxt";
close(INPTXT);

my $INPFILNAM = "$TMPDIR/$GINPFILNAM";
$ENV{INPFILNAM} = "$INPFILNAM";

# copy application input file to remoste systems
# print STDERR "before /swarna2/gt2/bin/gsiscp $SCPflag_tun $INPFILNAM $Sysnam_tun:$Scrd_tun/$Inpfil \n";
# system('/swarna2/gt2/bin/gsiscp  $SCPflag_tun  $INPFILNAM $Sysnam_tun:$Scrd_tun/$Inpfil');
# print STDERR "before /swarna2/gt2/bin/gsiscp $SCPflag_cu $INPFILNAM $Sysnam_cu:$Scrd_cu/$Inpfil \n";
# system('/swarna2/gt2/bin/gsiscp  $SCPflag_cu  $INPFILNAM $Sysnam_cu:$Scrd_cu/$Inpfil');
# print STDERR "before /swarna2/gt2/bin/gsiscp $SCPflag_ccg $INPFILNAM $Sysnam_ccg:$Scrd_ccg/$Inpfil \n";
# system('/swarna2/gt2/bin/gsiscp  $SCPflag_ccg  $INPFILNAM $Sysnam_ccg:$Scrd_ccg/$Inpfil');

# copy files for getting jobid to remote systems
#my $getjobid = "globus-get-batch-jobid";
#$ENV{getjobid} = "$getjobid";
#print STDERR "before /swarna2/gt2/bin/gsiscp  $SCPflag_cu $getjobid.cu $Sysnam_cu:$Scrd_cu/$getjobid \n";
#system('/swarna2/gt2/bin/gsiscp  $SCPflag_tun $getjobid.tun $Sysnam_tun:$Scrd_tun/$getjobid');
#system('/swarna2/gt2/bin/gsiscp $SCPflag_cu $getjobid.cu $Sysnam_cu:$Scrd_cu/$getjobid');
#system('/swarna2/gt2/bin/gsiscp $SCPflag_ccg $getjobid.ccg $Sysnam_ccg:$Scrd_ccg/$getjobid');
#print STDERR "after /swarna2/gt2/bin/gsiscp  $SCPflag_cu $getjobid.cu $Sysnam_cu:$Scrd_cu/$getjobid \n";


# set the place that holds condor-G submit file
my $condorgsub = "$TMPDIR/g03.condorg-match";
$ENV{condorgsub} = "$condorgsub";
unlink "$condorgsub";

# write condor-G submit file

open(MYSCRIPT, ">$condorgsub") or
    die ("Error: cannot open file 'g03.condorg-match'\n");

# print the condorg script

print MYSCRIPT <<'EOF1';
universe = grid
grid_type = gt2
###globusscheduler = ccg-login.ncsa.uiuc.edu/jobmanager-pbs
globusscheduler = $$(gatekeeper_url)
EOF1

print MYSCRIPT "requirements = (TARGET.gatekeeper_url =!= UNDEFINED) && (Cpus == $NP)\n\n";

print MYSCRIPT <<'EOF2';
# full path to executable (in this case on the remote system)
##executable = /home/spamidig/condorgtest/gauss.sh
executable = $$(RunG03)

# executable is on the remote system
transfer_executable = false

# transfer input files, transfer output files when job is done
should_transfer_files = yes
WhenToTransferOutput = ON_EXIT

EOF2

print MYSCRIPT "# standard output, standard error, condor log of job\n";
print MYSCRIPT "# named with condor job id\n";
print MYSCRIPT "output = /tmp/Matchg03.\$\(Cluster\).\$\(Process\).out\n";
print MYSCRIPT "error = /tmp/Matchg03.\$\(Cluster\).\$\(Process\).err\n";
print MYSCRIPT "log = /tmp/Matchg03.\$\(Cluster\).\$\(Process\).log\n\n";


print MYSCRIPT "# always jobtype=single cause do not want to ssh executable to all nodes\n";
print MYSCRIPT "# maxwalltime is in minutes\n";
print MYSCRIPT "# host_count = number of nodes\n";
print MYSCRIPT "globusrsl = (jobtype=single)(maxwalltime=$Time)(count=4)\n";
print MYSCRIPT "# globusrsl = (jobtype=single)(maxwalltime=$Time)(host_count=4)\n\n";

print MYSCRIPT << 'EOF3';
# number of processes (when job runs may or may not be 1 proc per node)
+count = 4

# the remote_initialdir must exist before _submitting_ this job
##remote_initialdir = /home/spamidig/storage/testg03Condor
remote_initialdir = $$(RemInDir)

EOF3

print MYSCRIPT "# comma-delimited list of input files transfered to\n";
print MYSCRIPT "# remote_initialdir at SUBMIT time\n";
print MYSCRIPT "transfer_input_files = $INPFILNAM\n\n";

print MYSCRIPT "# comma-delimited list of output files to transfer back after job\n";
print MYSCRIPT "# must list these for grid job (unlike normal condor jobs)\n";
print MYSCRIPT "# transfer_output_files = g03_$GINPFILNAM.tgz\n\n";

print MYSCRIPT "# arguments to pass to executable (in this case gauss.sh)\n";
print MYSCRIPT "# remote_initialdir input_file_name output_file_name\n";
print MYSCRIPT "##arguments = /home/spamidig/storage/testg03Condor test3.com test3.out\n";
print MYSCRIPT "#arguments =  -a \$\$(Myproj) -q \$\$(MyQueue) -c $Time -n 4 -O \$\$(RemInDir) \$\$(RemInDir)/$GINPFILNAM \n";
print MYSCRIPT "arguments = \$\$(RemInDir) \$\$(RemInDir)/$GINPFILNAM \$\$(RemInDir)/$GINPFILNAM.out  \n\n";

print MYSCRIPT "# Define GridChem user\n";
print MYSCRIPT "+GridChemUser = $UserName\n\n";

print MYSCRIPT "# for renewing proxy\n";
print MYSCRIPT "#MyProxyHost   = myproxy.ncsa.uiuc.edu:7512 \n";
print MYSCRIPT "#MyProxyNewProxyLifetime = 2\n";
print MYSCRIPT "#MyProxyPassword = con01dor\n\n";


print MYSCRIPT << 'EOF4';
# this is what tells condor to actually submit this job
# must be after the statements describing the job
queue
EOF4

close MYSCRIPT;


#lauch condor-G job

# submit job and get Condor jobid
print STDERR "before /usr/local/condor/bin/condor_submit $condorgsub\n";
my $condor_out = `/usr/local/condor/bin/condor_submit $condorgsub 2>&1`;
print STDERR "after /usr/lcoal/condor/bin/condor_submit $condorgsub\n";
my $condor_jobid;
if ($condor_out =~ /to cluster (\d+)/)
{
  $condor_jobid = $1;
  print STDERR "condor_jobid = $condor_jobid\n";
}
else
{
  print STDERR "Error when submitting job.  Here's what Condor said:\n";
  print STDERR "$condor_out\n";
  print "Condor-G_Error: Error when submitting job. \n";
  exit;
}
print STDERR "after submitting job and getting Condor jobid\n";


# check GlobusContactString
my $num_try = 0;
my $globus_jobid = "X";
my $condor_q;
while (($globus_jobid =~ /X/) && ($num_try <= 3))
{
   sleep(5);
   $num_try = $num_try + 1;
   $condor_q = `/usr/local/condor/bin/condor_q -l $condor_jobid 2>&1`;
   if ($condor_q =~ /GlobusContactString\s=\s"([^"]+)"/)
   {
     $globus_jobid = $1;
   }
}
if ($globus_jobid =~ /X/)
{ 
   print STDERR "Condor-G_Error: no match \n";
   print "Condor-G_Error: no match \n";
   my $condor_rm_output = `/usr/local/condor/bin/condor_rm $condor_jobid`;
   print STDERR "condor_rm : $condor_rm_output";
   $condor_rm_output = `/usr/local/condor/bin/condor_rm -forcex $condor_jobid`;
   print STDERR "condor_rm -forcex : $condor_rm_output";
      
   exit;
}
else
{
   print STDERR "globus_jobid = $globus_jobid\n";
}
print STDERR "after checking GlobusContactString\n";

# grab hostname from Globus job contact string
my $job_host;
if ($globus_jobid =~ /https:\/\/([^:]+):/)
{
  $job_host = $1;
  print STDERR "job_host = $job_host\n";
}
else
{
   print STDERR "Condor-G_Error: cannot get hostname \n";
   print "Condor-G_Error: cannot get hostname \n";
   my $condor_rm_output = `/usr/local/condor/bin/condor_rm $condor_jobid`;
   print STDERR "condor_rm : $condor_rm_output";
   $condor_rm_output = `/usr/local/condor/bin/condor_rm -forcex $condor_jobid`;
   print STDERR "condor_rm -forcex : $condor_rm_output";
   exit;
}
print STDERR "after grabbing hostname from Globus job contact string \n";

# run fork job to get batch jobid
my $loc;
if ($job_host =~ /ccg/)
{
  $loc = "/home/lixh/storage";
}
else
{
  $loc = "/scratch/users/lixh";
}

my $globus_out = `/usr/local/globus/bin/globusrun -o -r $job_host '&(executable=$loc/globus-get-batch-jobid)(arguments="$globus_jobid")' 2>&1`;

print STDERR "globus_out = $globus_out\n";
if (length($globus_out) > 24) 
{
   $globus_out = "NA";
}
print STDERR "Condor-G_JOBID $job_host:$globus_out \n";
print "Condor-G_JOBID $job_host:$globus_out \n";

