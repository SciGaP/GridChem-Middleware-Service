#!/usr/bin/perl -w

print STDERR "ENTERING get_myproxy_credential.cgi\n";
my $ts0 = time();

$date = `date`;

use CGI qw/:standard/;

use Expect;

use lib "/var/www/gms";
use gms_globus_params;

if($CCG_GMS_GLOBUS_ENV{'ENV'} eq "YES"){
   foreach my $key (keys(%CCG_GMS_GLOBUS_ENV)) {
#     print STDERR "  DBAccess.pl: GLOBENV: $key  $CCG_GMS_GLOBUS_ENV{$key}\n";;
      $ENV{"$key"} = $CCG_GMS_GLOBUS_ENV{$key};
   }
}

##Amr changed all hardwierd resource access with DB call
## load the DBAccess module
require("DBAccess.pl");

DB::loadResources("specifications.cfg");  #load the resource description file

$myProxyDir =  DB::getGMSAttribute("myproxy-directory-path");
$ENV{myProxyDir}=$myProxyDir;

# get the security temp directory
$securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir;

my $program  = "$myProxyDir/myproxy-get-delegation";

my $username    = "$REMOTE_USER";
$ENV{username}="$username";
my $gridchemusername = "$GRIDCHEM_REMOTE_USER";
$ENV{gridchemusername} = "$gridchemusername";
my $isgridchem = "$ISGRIDCHEM";
$ENV{isgridchem} = "$isgridchem"; 
my $password    = "$SENTPW";
# 2006/03/27 this is temporary fix until we calculate how much we need
# 2006/05/31 skk made it 480 (20days)
# 2006/09/05 skk made it 960 (40days)

my $lifetime    = "960"; # in hours 
$outfile1 = join('',"$securityTmpDir/",$username);
if ($isgridchem eq "true"){
$outfile1 = join('',"$securityTmpDir/",$gridchemusername);
$outfile = join('',$outfile1,"CCG_X509");
$type = "Community";
####$ENV{outfile}="$outfile";
}
else
{
$outfile1 = join('',"$securityTmpDir/",$username);
print STDERR " get_myproxygetdelegation.pl: part X509 files for $username is $outfile1\n";
##my $outfile = join('',$outfile1,"_X509");
$outfile = join('',$outfile1,"_X509");
#####$ENV{outfile}="$outfile";
print STDERR " get_myproxygetdelegation.pl: Complete X509 file is $outfile \n";
$type="External";
}
$ENV{outfile}="$outfile";
print STDERR " get_myproxygetdelegation.pl: ************************************************\n";
print STDERR " get_myproxygetdelegation.pl: Complete X509 file is $outfile \n";
print STDERR " get_myproxygetdelegation.pl: ************************************************\n";

#                                          These are not define. For external, must define 
#                                          No clue how this is done here though?
my $myproxy_server;
my $myproxy_tag;
if( defined($MYPROXY_SERVER)){ $myproxy_server = "$MYPROXY_SERVER"; }
else                         { $myproxy_server = ""               ; }
if( defined($MYPROXY_TAG))   { $myproxy_tag    = "$MYPROXY_TAG"   ; }
else                         { $myproxy_tag    = ""               ; }

unlink "$outfile";

# xiaohai: for community user
print STDERR "isgridchem = $isgridchem \n";
if ($isgridchem eq "true")
{
#   if ($password ne "ccgusertest") 
#   {
#      print "ERROR:invalid password";
#      system ('echo ccguser:$errmsg >> $securityTmpDir/krbauth.log ');
#      system ('echo $username:$gridchemusername >> $securityTmpDir/krbauth.log ');
#      exit;
#   } 
#   else
#   {
      $password = "c0mmc#em05"; 
#   }
}

# lixh : for rion stuff
my $args;
if ($isgridchem eq "false")
{
print STDERR "MyproxyCredential.pl: Creating X509 Credential for External User $username in $outfile \n";
   if ($myproxy_server ne "myproxy.ncsa.uiuc.edu")
   {
      if ($myproxy_tag eq "")
      {
        print STDERR "-s $myproxy_server -l $username -o $outfile";
        $args = "-s $myproxy_server -l $username -o $outfile";
      }
      else
      {
        print STDERR "-s $myproxy_server -l $username -k $myproxy_tag -o $outfile";
        $args = "-s $myproxy_server -l $username -k $myproxy_tag -o $outfile";
      }
   }
   else
   {
      $args = "-s myproxy.ncsa.uiuc.edu -l $username -t $lifetime -o $outfile";
      print STDERR " with arguments    $args \n";
   }
}
else
{
   $args = "-s myproxy.ncsa.uiuc.edu -l $username -t $lifetime -o $outfile";
}


# use expect to run the command
#print "program = $program and args = $args \n";
if ($myproxy_server eq "myproxy.cct.lsu.edu")
{
  $ENV{MYPROXY_SERVER_DN} = "/O=Grid/O=GridLab/CN=gridhub.cct.lsu.edu";
}
print STDERR " Expect $program $args\n";
my $cmd_filehandle = Expect->spawn("$program $args");

# this looks for the string "myproxy-server:" for 20 seconds
# and failing that, does the "error" subroutine.
#unless ($cmd_filehandle->expect(5, "Enter MyProxy Pass Phrase:"))
#{
#  printerror("myproxy.ncsa.uiuc.edu did not respond for $username");
#}
#system('sleep 1');

print $cmd_filehandle "$password\n";

# gather the output into the array
@output = <$cmd_filehandle>;


# close the filehandle to the command
$cmd_filehandle->soft_close();
$cmd_filehandle->hard_close();


# lixh_add
# now you have an array called @outputmsg which has the rest of the output...
# ouput[2] contains the output information 

$outputmsg = join(''," ", $output[2]);
$outputmsg =~ s/^ *//;
print STDERR "get_myproxy_getdelegation.pl: Output Message is $outputmsg";
if (($outputmsg =~ /Error/) || ($outputmsg =~ /ERROR/)) {
    $outputmsg =~ s/(.*):/ERROR:/;
    &printerror($outputmsg);
} else {
  # 2006/08/15 09:36skk added the $lifetime hours rather than the incorrect 2 hours 
    system ("echo \"User $username obtained X509 credential good for $lifetime hours.\" >> $securityTmpDir/krbauth.log ");
    system('chmod g-r $outfile');
    if ($isgridchem eq "true"){
          system('/bin/cp $outfile /tmp/ccguser_x509');
          system('chmod g-r  /tmp/ccguser_x509');
    }
    print STDERR " User $username ($type) obtained X509 credential good for $lifetime hours\n";
    print "User $username obtained X509 credential good for $lifetime hours\n";
}

sub printerror
{
    my $errmsg = $_[0];
    #print header;
    #print "<BODY BGCOLOR=#efefef>";
    #print "<TITLE>Error!</TITLE>";
    #print "<H1><FONT FACE=Arial COLOR=Red><STRONG>";
    #print "Error executing myproxy-get-delegation!\n";
    #print "</STRONG></FONT></H1>";
    print "$errmsg \n";
    system ('echo $errmsg >> $securityTmpDir/krbauth.log ');
    system ('echo $REMOTE_USER >> $securityTmpDir/krbauth.log ');
    exit;
}

# 2006/08/15 09:23skk this is not used????
sub printsuccess
{
    #print header;
    #print "<BODY BGCOLOR=#efefef>";
    #print  "<TITLE>Error!</TITLE>";
    #print "<H1><FONT FACE=Arial COLOR=Blue><STRONG>";
    #print "Received a delegated proxy for $username good for $lifetime hours.";
    #print "</STRONG></FONT></H1>";
    system ('echo "User `$username` obtained X509 credential good for `$lifetime`  hours." >> $securityTmpDir/krbauth.log ');
    exit;
}

my $ts1 = time();
my $td  = $ts1-$ts0;
print STDERR "LEAVING get_myproxy_credential.cgi $td\n";
