#!/usr/bin/perl -w
#################################################
#						#
# putfile.cgi					#
# Put a file into mass storage			#
# Rebecca Hartman-Baker				#
# September 18, 2002				#
#						#
#################################################

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Read the paramter sent to the script //we need to get rid of this deprecated code segment
#############################################################################################

use File::Path;
use Cwd;

my $qcrjmFile = "qcrjm.hist";
my $preferenceFile = "preferences.hist";

print STDERR "ENTERING D putfile.cgi\n";
my $ts0 = time();


read(STDIN, $buffer, $ENV{'CONTENT_LENGTH'});
 @lines = split(/\n/,$buffer);
print STDERR "number of lines = $#lines\n";
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
    if( $name ne "fileText"){
       print STDERR " - name: $name value: $value - \n";
    }
 }
}

$true  = 1;
$false = 0;
$DEBUG = $false;

print "Content-type: text/plain\n\n";

$UserName         = $FORM{'Username'};
                          $UserName =~ s/\r//;
$GridChemUserName = $FORM{'GridChemUsername'};
                          $GridChemUserName =~ s/\r//;
$IsGridChem       = $FORM{'IsGridChem'};
                          $IsGridChem =~ s/\r//;
$HPCsys           = $FORM{'HPCsys'};
                          $HPCsys =~ s/\r//;
$ENV{HPCsys}= $HPCsys;

$Inpfil           = $FORM{'fileName'};
                          $Inpfil =~ s/\n//;
                          $Inpfil =~ s/\r//;

$InpTxt = $FORM{'fileText'};
                             open( PUTFILE1,">/tmp/putfile1.log");
                             print PUTFILE1 "INSIDE1\n";
                             close(PUTFILE1);


$ENV{UserName} = "$UserName";
$ENV{GridChemUserName} = "$GridChemUserName";
$ENV{IsGridChem} = "$IsGridChem";
$ENV{Inpfil} = "$Inpfil";
# 2006/10/20 skk We dont want to save huge files in env vars because shell commands get confused
# $ENV{InpTxt} = "$InpTxt";

#############################################################################################
##Amr: Add DB support
##
##  Load the resource database 
#############################################################################################
##Amr changed all hardwierd resource access with DB call
## load the DBAccess module

print STDERR " putfile.cgi: requiring DBAccess.pl\n";
require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory
$securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir;

$userTmpDirBase = DB::getGMSAttribute("user-temp-directory");
$ENV{userTmpDirBase}=$userTmpDirBase;

#remove old Logfile
print STDERR "IsGridChem = $IsGridChem \n";
print STDERR "GridChemUserName = $GridChemUserName \n";
if  ($IsGridChem eq "true") { $TMPDIR = "$userTmpDirBase/internal/$GridChemUserName"; }
else                        { $TMPDIR = "$userTmpDirBase/external/$GridChemUserName"; }

$ENV{TMPDIR} = "$TMPDIR";

unlink "$TMPDIR/$Inpfil";

############################################################################################
#  Put text into file
############################################################################################

#open(INPTXT,">$TMPDIR/$Inpfil");
#print INPTXT "$InpTxt";
#close(INPTXT);


print STDERR "$UserName at putfile\n";
print STDERR `id -g`;
print STDERR `id -u`;
print STDERR `id -ru`;
print STDERR `id -rg`;


#############################################################################################
##Amr:
##  Check which security architecture to use : KRB5 or X509
#############################################################################################
$X509 = 0; # this variable says whether or not to use X509

# lixh_add
$X509_USER_PROXY = join('',"$securityTmpDir/","$UserName","_X509");
if  ($IsGridChem eq "true") { 
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

    if ( ! -e $KRB5CCNAME) {   #do the most reasonable thing, X509
      $X509 = 1;
      print STDERR " ERROR: getfile: No X509 or Kerberos credentials.\n";
    } 

}

$ENV{status} = "$?";
unlink "/$TMPDIR/testrshstatus";
unlink "/$TMPDIR/bsubstatus";

#check if ticket is valid else send a message back
unlink "$TMPDIR/curtim";
unlink "$TMPDIR/kltim";
if ($X509 == 0)
{
    $checkTickets = DB::getGMSAttribute("krb5-check-ticket-program-path");
    $ENV{checkTickets}= $checkTickets ;
    print STDERR " putfile.cgi: requiring chktickets.pl\n";
    require "chktickets.pl";
    print STDERR "KERBEROS authentication\n";
}
else
{
    print STDERR "GLOBUS authentication\n";
}

#############################################################################################
##  Amr:  DB support
##
##  Now load the machine connection string
#############################################################################################

##$reflag = DB::getMachineAttribute($HPCsys, "reflag");
$UsratRH = "$UserName\@$HPCsys";
$ENV{UsratRH} = "$UsratRH";

if ($X509 == 1)
{

   ###########################################################################################
   ##  Amr: Annotate, comment and Indent code.
   ##
   ##  It is X509 over here
   ##  Revised by Kent Milfeld 10/23/2005
   ##    1.) Home mss and server temp (userTmpDirBase) directory structures are now identical.
   ##    2.) internal/external layer added to file structure hierarchy
   ##    3.) If file has path, then directory path is created in server temp.
   ##    4.) If file has path, then directory path is created in mss.
   ##    5.) UberFTP command cleaned up.  Now has: $mkDir and $cdToDir values:
   ##             mkDir    (makes directory path, empty if not used.)
   ##             cdToDir  (cd to mss directory,  empty if not used.)
   ##    I'll clean up the kerberos methods once this section has been thoroughly tests
   ##                                                                      KFM  10/23/2005
   ##                          
   ###########################################################################################

   if($DEBUG) { print STDERR "Using X509 based authentication and uberftp\n"; }

   system('echo "" > $TMPDIR/gsi.passwd');
   chdir "$TMPDIR"; unlink "uberftp.err"; 
   $uberftp = DB::getGMSAttribute("uberftp-program-path");

    if  ($IsGridChem eq "true") { $mssBase = "internal/$GridChemUserName"; }
    else                        { $mssBase = "external/$GridChemUserName"; }
 
 
                         #Now Determine if there is a pathname associated with the file.
                         #In this case it is neither a qcrjm or preferences files (or
                         #any other that go into the top level directory
                         #(internal or external/GridChemUserName)
                         #Must make sure to change both TMPDIR and cdToDIR in mss

                         #Separate path and file name.  Make user path begins with "/".

    $_=$Inpfil;
    if ( /\// ) { 
                   /(.*\/)(.*)/; $path=$1; $fileName=$2;

                   if($path =~ /^[^\/]/){$path = "/".$path;}   #make sure path has "/" at beginning.
 
                         #Create the mkdir sequence for mss: If path is a/b/c/d
                         #Then use "mkdir $mssBase/a; mkdir $mssBase/a/b; 
                         #mkdir $mssBase/a/b/c; mkdir $mssBase/a/b/c/d;

                   $mkDir = "";
                   $tmp =$path;
                   $tmp =~ s/^\///;                            #Take of beginning "/".
                   @paths = split('/',$tmp);
                   foreach $key (@paths){
                      if( defined ($all) ){ $all .= "/". $key; }
                      else                { $all  =      $key; }
                      $mkDir .= "mkdir $mssBase/$all;";
                   }
 
                   $cdToDir = "cd $mssBase$path;";
 
                   $hasPath = $true;

                }

    else        { 
                               #Note, if no path, then $mssBase should already exist--
                               #created in first getFile command.

                   $path    = "";
                   $fileName=$Inpfil;
                   $hasPath = $false;
                   $mkDir   = "";
                   $cdToDir = "cd $mssBase;";

                }

 
                               #Make sure path exists.

    mkpath("$TMPDIR". $path, 1, 0750);

                               #Now redefine the temporary directory to include path now.

    $TMPDIR .= $path;


                               # PUTTING INPUT in appropriate directory

    open( INPTXT,">$TMPDIR/$fileName");
    print INPTXT "$InpTxt";
    close(INPTXT);
                               # Go to directory with file.
 
    chdir "$TMPDIR"; unlink "uberftp.err"; 
 
    # 20 Jan 2006 Saving qcrjm.hist and preferences.hist to mss is stopped for 
    # now because of problems we had and because we will be moving this functionality to the database!

    if ($fileName ne $preferenceFile && $fileName ne $qcrjmFile) {

      $cmd = "$uberftp -a GSI -P 2811 -H mss.ncsa.uiuc.edu \"$mkDir $cdToDir put $fileName\"";

      system("$cmd" . " >> $TMPDIR/uberftp.err ");

      open(OFC,"$TMPDIR/uberftp.err");
      @outputmsg = <OFC>;

      if (grep(/Failed/,@outputmsg))   
      {
         print STDERR " putfile: UberFTP failed.     cmd = $cmd\n";
      }else
      {        
         print STDERR " putfile: UberFTP Successful. cmd = $cmd \n";
      }
      close(OFC);
    }else{
      # temp 
      my $mypwd = getcwd();
      print STDERR " putfile: DEBUG20060120B:  We skip going to mss for the file $fileName\n";
      print STDERR " putfile: DEBUG20060120A:  The current dir is $mypwd\n";
    }
}
else
{
   #######################################################################################
   ##  Amr:  Annotate, comment and Indent code.
   ##
   ##  It is KRB5 over here
   #######################################################################################


#                        Do the same for Kerberos, i.e. do not put if *.hist file.
#   if ($HPCsys ne "null") 

    if ($fileName ne $preferenceFile && $fileName ne $qcrjmFile && $HPCsys ne "null") 
   {
	print STDERR "Using kerberos based authentication and krb5 scp/rsh\n";
	# This requires two steps
	# Step1 Copy file  to local HPC system
        
	$krb5_scp = DB::getGMSAttribute("krb5-scp-program-path");
	$ENV{krb5_scp}=$krb5_scp;
	$krb5_rsh = DB::getGMSAttribute("krb5-rsh-program-path");
	$ENV{krb5_rsh}=$krb5_rsh;

	print STDERR "BEFORE $krb5_scp $TMPDIR/$Inpfil $UsratRH:$Inpfil \n";
   	$scpstatus = system('$krb5_scp $TMPDIR/$Inpfil $UsratRH:$Inpfil');
   	if ($scpstatus != 0)
   	{
	    print STDERR "ERROR scpstatus = $scpstatus \n";
	    exit;
   	}
   	
	print STDERR "AFTER $krb5_scp $TMPDIR/$Inpfil $UsratRH:$Inpfil \n";

	#Step2 then move it to mss

   	print STDERR "BEFORE $krb5_rsh -l $UserName $HPCsys msscmd put $Inpfil \n";
   	$rshstatus = system('$krb5_rsh -l $UserName $HPCsys msscmd put $Inpfil >> $TMPDIR/rsh.err');
   	if ($rshstatus != 0) 
   	{
	    print STDERR "ERROR rshstatus = $rshstatus \n";
	    exit;
   	}
   	print STDERR "AFTER $krb5_rsh -l $UserName $HPCsys msscmd put $Inpfil \n";
  }
}

##$FName = "$TMPDIR/$SubLog";
##$ENV{FName} = "$FName";

#   Shouldn't have to sleep at all.  This is legacy?  Kent 1/22/2006
if ($fileName ne $preferenceFile && $fileName ne $qcrjmFile) {
   sleep 2;
}

my $ts1 = time();
my $td  = $ts1-$ts0;
print STDERR "LEAVING putfile.cgi $td\n";

