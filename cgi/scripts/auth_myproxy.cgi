#!/usr/bin/perl -w

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Read the paramter sent to the script
#############################################################################################

print STDERR "ENTERING auth_myproxy.cgi\n";
my $ts0 = time();


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
		# Stop people from using subshells to execute commands
    		$value =~ s/~!/ ~!/g;
    		$FORM{$name} = $value;
  	}
}

print "Content-type: text/plain\n\n";

# lixh_add
# check if the disk on the middle server is full or not
print STDERR " auth_myproxy.cgi: requiring check_diskfull.pl\n";
require "check_diskfull.pl";
my $check_full;

#if ($check_full eq "false")    #THIS DOESN'T WORK  Kent 1/24/2006
#{
#	print STDERR "disk_full error \n";
#	exit;
#}

$REMOTE_USER = $FORM{'Rem_User'};
$REMOTE_USER =~ s/\r//;
$ENV{REMOTE_USER} = "$REMOTE_USER";
$GRIDCHEM_REMOTE_USER = $FORM{'GridChem_Rem_User'};
$GRIDCHEM_REMOTE_USER =~ s/\r//;
$ENV{GRIDCHEM_REMOTE_USER} = "$GRIDCHEM_REMOTE_USER";
$ISGRIDCHEM = $FORM{'IsGridChem'};
$ISGRIDCHEM =~ s/\r//;
$ENV{ISGRIDCHEM} = "$ISGRIDCHEM";
if( defined($FORM{'Rem_MyProxy_Server'}) ){
   $MYPROXY_SERVER = $FORM{'Rem_MyProxy_Server'};
   $MYPROXY_SERVER =~ s/\r//;
   $ENV{MYPROXY_SERVER} = "$MYPROXY_SERVER";
   $MYPROXY_TAG = $FORM{'Rem_MyProxy_Tag'};
   $MYPROXY_TAG =~ s/\r//;
   $ENV{MYPROXY_TAG} = "$MYPROXY_TAG";
}

#############################################################################################
##Amr: Add DB support
##
##  Load the resource database 
##  change all hardwired resource accesses to DB call
#############################################################################################

print STDERR " auth_myproxy.cgi: requiring DBAccess.pl\n";
require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory
$securityTmp = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmp}= $securityTmp;

unlink "$securityTmp/testkrbauth.log";

system ('echo $REMOTE_USER:$GRIDCHEM_REMOTE_USER > $securityTmp/testkrbauth.log ');
system ('echo >> $securityTmp/testkrbauth.log ');

$SENTPW = $FORM{'Rem_User_Paswd'};
$SENTPW =~ s/\r//;
$ENV{SENTPW} = "$SENTPW";


#remove old authlog
unlink "$securityTmp/krbauth.log";
print STDERR "Auth_Myproxy.cgi: Calling get_myproxy_credential.pl for $REMOTE_USER \n";

#remove old credentials
unlink "$securityTmp/krb5cc_$REMOTE_USER";
print STDERR " auth_myproxy.cgi: requiring get_myproxy_credential.pl for $GRIDCHEM_REMOTE_USER since $ISGRIDCHEM \n";
require "get_myproxy_credential.pl";

system ("echo $REMOTE_USER >> $securityTmp/krbauth.log ");

open(FILE," $securityTmp/krbauth.log") || die "could not open  $securityTmp/krbauth.log: $!\n";
while(<FILE>)
{
	@flds = split(' ',$_,99);
   	print STDERR "$flds[0] from  $securityTmp/krbauth.log at `date` \n";
  	if ($flds[0] ne "ERROR:")
  	{
		### create/check a directory in temp
    		$userTmpBase = DB::getGMSAttribute("user-temp-directory");
    		$ENV{userTmpBase}=$userTmpBase;

		if ($ISGRIDCHEM eq "true")
                {
                   $TMPDIR = "$userTmpBase/$REMOTE_USER/$GRIDCHEM_REMOTE_USER";
                }
                else
                {
                   $TMPDIR = "$userTmpBase/$REMOTE_USER";
                }
		$ENV{TMPDIR}="$TMPDIR";
    		if (!(-e "$TMPDIR"))
    		{ 
		    print STDERR " Creating a directory for $REMOTE_USER in $TMPDIR\n";
		    mkdir ( "$TMPDIR", 0775); 
    		}
  	}
 }
close(FILE);

#******************************************************
#Amr:
#
# Last Step: return resource information to the client
#*******************************************************

$allApplications = DB::getApplications();

#return the list of available applications
print (DB::getApplications(),"\n");

#for each application return the list of machines that can be used to run it

@applications = split(",",$allApplications);
foreach $app(@applications){
    print $app,",",DB::getMachinesThatHaveApplication($app),"\n";
    #print STDERR $app,",",DB::getMachinesThatHaveApplication($app),"\n";

}

# for each machine return the list of availabe queues on it
$allMachines = DB::getMachines();
print $allMachines,"\n";
print STDERR $allMachines, "\n";

@machines = split(",",$allMachines);
foreach $mach(@machines){
    print $mach,",",DB::getMachineQueues($mach),"\n";
    print DB::getMachineProjects($mach),"\n";
    #print STDERR $mach,",",DB::getMachineQueues($mach),"\n";
    #print STDERR DB::getMachineProjects($mach),"\n";

}
my $ts1 = time();
my $td  = $ts1-$ts0;
print STDERR "LEAVING auth_myproxy.cgi $td\n";
