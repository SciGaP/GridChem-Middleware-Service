#!/usr/bin/perl  -w

#############################################################################################
##Amr: Annotate, comment and indent code
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
		# Stop people from using subshells to execute commands
    		$value =~ s/~!/ ~!/g;
    		$FORM{$name} = $value;
  	}
}
print "Content-type: text/plain\n\n";

# lixh_add
# check if the disk on the middle server is full
require "check_diskfull.pl";
if ( $check_diskfull eq "false")
{
	print STDERR "disk_full error \n";
	exit;
}

$REMOTE_USER = $FORM{'Rem_User'};
$REMOTE_USER =~ s/\r//;
$ENV{REMOTE_USER} = "$REMOTE_USER";

#############################################################################################
##Amr: Add DB support
##
##  Load the resource database 
##  Change all hardwired resource accesses to DB call
#############################################################################################
require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory
$securityTmp = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmp}= $securityTmp; 

unlink "$securityTmp/testkrbauth.log";

system ('echo $REMOTE_USER > $securityTmp/testkrbauth.log ');
system ('echo >> $securityTmp/testkrbauth.log ');

$SENTPW = $FORM{'Rem_User_Paswd'};
$SENTPW =~ s/\r//;
$ENV{SENTPW} = "$SENTPW";

#remove old authlog
unlink "$securityTmp/krbauth.log";

#remove old credentials
unlink "$securityTmp/krb5cc_$REMOTE_USER";

$krb5 = DB::getGMSAttribute("krb5-auth-program-path");
$ENV{krb5}=$krb5;

require "get_kerberos_credential.pl";

open(FILE,"$securityTmp/krbauth.log");
while(<FILE>)
{
	@flds = split(' ',$_,99);
  	if ($flds[0] ne "ERROR:")
  	{
		### create/check a directory in temp
    		$userTmpBase = DB::getGMSAttribute("user-temp-directory");
    		$ENV{userTmpBase}=$userTmpBase;

                $TMPDIR = "$userTmpBase/$REMOTE_USER";
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
