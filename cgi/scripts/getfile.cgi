#!/usr/bin/perl -w
#################################################
#						#
# getfile.cgi					#
# Get file from mass storage or local scratch	#
# Rebecca Hartman-Baker				#
# September 20, 2002				#
#						#
#################################################

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Read the paramter sent to the script
#############################################################################################

use File::Path;
use File::Basename;  # provides fileparse
use Cwd;

print STDERR "ENTERING getfile.cgi\n";
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
		### Stop people from using subshells to execute commands
    		$value =~ s/~!/ ~!/g; 
    		$FORM{$name} = $value;
 	}
 }


print "Content-type: text/plain\n\n";

my    $qcrjmFile =       "qcrjm.hist";
my $preferenceFile = "preferences.hist";
my         $getCmd;
my        $cdToDir;

$UserName = $FORM{'Username'};
$UserName =~ s/\r//;
$Sysnam   = $FORM{'System'};
$Sysnam   =~ s/\r//;
if( ! defined($FORM{'HPCsys'}) ){print STDERR "  HPCsys Not defined\n"; $HPCsys=""; }
else                            { $HPCsys   = $FORM{'HPCsys'}; }
$HPCsys   =~ s/\r//;
$Inpfil   = $FORM{'fileName'};
$Inpfil   =~ s/\n//;
$Inpfil   =~ s/\r//;
$Inpfil   =~ s/\\/\//g;
$GridChemUserName = $FORM{'GridChemUsername'};
$GridChemUserName =~ s/\r//;
$IsGridChem       = $FORM{'IsGridChem'};
$IsGridChem       =~ s/\r//;
$AuthType	  = $FORM{'AuthType'};
$AuthType         =~ s/\r//;

$UsratRH       = "$Sysnam";
$UsratHPC      = "$UserName\@$HPCsys";



#############################################################################################
##Amr: Add DB support
##
##  Load the resource database 
#############################################################################################
##Amr changed all hardwired resource access with DB call
## load the DBAccess module

require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory

$securityTmpDir  = DB::getGMSAttribute("security-temp-directory");
$userTmpDirBase  = DB::getGMSAttribute("user-temp-directory");
$GLOBUS_LOCATION = DB::getGMSAttribute("globus-directory-location");

if  ($IsGridChem eq "true"){ $TMPDIR = "$userTmpDirBase/internal/$GridChemUserName";}
else                       { $TMPDIR = "$userTmpDirBase/external/$GridChemUserName";}

$ENV{TMPDIR}          = "$TMPDIR";
$ENV{GLOBUS_LOCATION} = "$GLOBUS_LOCATION";

# 2006/01/20 Since now we are not getting preferences.hist and qcrjm.hist from MSS - let us NOT unlink
if ($Inpfil ne $preferenceFile && $Inpfil ne $qcrjmFile) {
  #remove old file
  unlink "$TMPDIR/$Inpfil";
  print STDERR " getfile:DEBUG20060120D: $0: we are unlinking file $TMPDIR/$Inpfil\n";
}else{
  print STDERR " getfile:DEBUG20060120C: $0: we skip unlinking file $TMPDIR/$Inpfil\n";
}

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Check which security architecture to use : KRB5 or X509
#############################################################################################

$X509 = 0;          # this variable says whether or not to use X509
if ($IsGridChem eq "true") {
   $X509_USER_PROXY = join('',"$securityTmpDir/","$GridChemUserName", "CCG_X509");
}
else
{
 # External users may use ssh 
 if ($AuthType ne "ssh")
 {
    $X509_USER_PROXY = join('',"$securityTmpDir/","$GridChemUserName", "_X509");
 }
 else 
 {
 # This is an External User with SSH authentication

  print STDERR "User $UserName is an External User using ssh authentication on System $SshSystem \n";
}

}

$X509_USER_CERT  = join('',"$securityTmpDir/","$UserName",               "_X509");
$KRB5CCNAME      =         "$securityTmpDir/krb5cc_$UserName";

$ENV{X509_USER_PROXY} = "$X509_USER_PROXY";
$ENV{X509_USER_CERT}  =  "X509_USER_CERT";
$ENV{KRB5CCNAME}      = "$KRB5CCNAME";
print STDERR "Getfile:*****X509:UserName:$UserName GCUN:$GridChemUserName X509_USER_PROXY:$X509_USER_PROXY X509_USER_CERT:$X509_USER_CERT\n";

if ( -e $X509_USER_PROXY)
{
  print STDERR "Checking if Kerberos credential is to be used! \n";
    if ( -e $KRB5CCNAME)
    {
	@x509stat = stat($X509_USER_PROXY);
	@krbstat  = stat($KRB5CCNAME);
	if ($x509stat[9] > $krbstat[9]) { 
      print STDERR " Globus Credential will be used as $x509stat[9] is > $krbstat[9]  \n";
              $X509 = 1; }
	else {
            print STDERR " Kerberos is more recent as $krbstat[9] > $x509stat[9] \n";
          $X509 = 0; }
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
unlink  "$TMPDIR/curtim";
unlink  "$TMPDIR/kltim";

#check if ticket is valid
if ($X509 == 0)
{
    $checkTickets = DB::getGMSAttribute("krb5-check-ticket-program-path");

    require "chktickets.pl";
    print STDERR "KERBEROS authentication\n";
}
else
{
    $checkTickets = "";  #Keep perl -w happy
    print STDERR "GLOBUS authentication\n";
}

#############################################################################################
##  Amr: Factor code to use DB support
##
## now add a factored statment to get ScrDr base and reflag
#############################################################################################

print STDERR "Debug --I want to get $Inpfil from $Sysnam";

$uberftp = DB::getGMSAttribute("uberftp-program-path");

if( $Inpfil eq $qcrjmFile  || $Inpfil eq $preferenceFile ){

   if ($IsGridChem eq "true") {
     $scrd    = "internal";
   }
   else
   {
     $scrd    = "external";
   }
                          #Don't need any of the machinery in else block here.
                          #But make assignments happy with "junk"
   $reflag = "-x";
   $pflag  = "22";
   $gsissh = "gsissh";
   $gsiscp = "gsiscp";
   $uberftp= "uberftp";
   $SCPflag= "junk"  # skk 2006/02/20 

}

else {


   $reflag  = DB::getMachineAttribute($Sysnam, "reflag");
   $pflag   = DB::getMachineAttribute($Sysnam,"Pflag");
   $gsissh  = DB::getGMSAttribute("globus-gsissh-program-path");
   $gsiscp  = DB::getGMSAttribute("globus-gsiscp-program-path");
   $uberftp = DB::getGMSAttribute("uberftp-program-path");
   $SCPflag = DB::getMachineAttribute($Sysnam,"SCPflag");


   #mss.ncsa.uiuc.edu,/usr/local/globus/bin/gsissh,     ,       ,/var/www/html/temp/internal/milfeld,   ,1
   #   Sysnam,                              gsissh,pflag, reflag, TMPDIR                            ,rsh, X509\n";
   print STDERR " getfile.cgi: geScratchDirectory:   Sysnam, gsissh, pflag, reflag, TMPDIR, rsh, X509\n";
   print STDERR " getfile.cgi: geScratchDirectory:  $Sysnam,$gsissh,$pflag,$reflag,$TMPDIR,$rsh,$X509\n";
   $scrd    = DB::getScratchDirectory($Sysnam,$gsissh,$pflag,$reflag,$TMPDIR,$rsh,$X509);
}

print STDERR "getfile: scrd: $scrd\n";

if   ($IsGridChem eq "true") { $SCRD="$scrd/$GridChemUserName"; }
else                         { $SCRD="$scrd"; }
print STDERR "getfile: SCRD: $SCRD\n";
# 2005/10/26 skk - this was not needed and was screwing up things: $SCRD    = $scrd;

$ENV{checkTickets}=$checkTickets;
$ENV{userTmpDirBase}  = $userTmpDirBase;   #take this out


$ENV{UserName}         = "$UserName";
$ENV{Sysnam}           = "$Sysnam";
$ENV{HPCsys}           = "$HPCsys";
$ENV{Inpfil}           = "$Inpfil";
$ENV{GridChemUserName} = "$GridChemUserName";
$ENV{IsGridChem}       = "$IsGridChem";


$ENV{reflag} =$reflag;
$ENV{pflag}  =$pflag;
$ENV{gsissh} =$gsissh;
$ENV{gsiscp} =$gsiscp;
$ENV{uberftp}=$uberftp;
$ENV{SCRD}   ="$SCRD";

$ENV{UsratRH}  = "$UsratRH";
$ENV{UsratHPC} = "$UsratHPC";

$date = `date`;


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
   ##    I'll clean up the non-mss x509 methods once this section has been thoroughly tests
   ##                                                                      KFM  10/23/2005
   ##
   ###########################################################################################

  	print STDERR "Getfile: Using X509 based authentication and gsi scp/ssh for $UserName at $Sysnam \n";
  	# Check if the file is present in scratch and get it and skip mss get
  	if ($Sysnam eq "mss.ncsa.uiuc.edu")
  	{
	    $outfe = 0;
  	}
  	else
  	{

          print STDERR "MW1:getfile: sec tempdir $securityTmpDir SCRD $SCRD and file to get $Inpfil > $TMPDIR/outfil\n";
          ###2006/02/20 skk           print STDERR "getfile: cmd1: $gsissh $Sysnam ls $SCRD/$Inpfil > $TMPDIR/outfil\n";
          $cmd1="$gsissh $pflag $Sysnam ls $SCRD/$Inpfil > $TMPDIR/outfil";
          print STDERR "getfile: cmd1: $cmd1\n";
          ####2006/05/01 system("$cmd1")==0 or die "system command: $cmd1  failed with error: $?; $!";
          system("$cmd1")==0 or die "system command: $cmd1  failed with error: $?;  \n gsissh_down_err \n    ";
          
          open(OFC,"$TMPDIR/outfil");
          while(<OFC>)
          {
            s/\n//;
            if ($_ eq "$SCRD/$Inpfil" || $_ eq "$Inpfil" || $_ eq "Inpfil@" || $_ eq "$SCRD/$Inpfil@")
            {
              print STDERR "Output file $SCRD/$Inpfil exists!\n";
              $outfe = 1;
            }
          }
          close(OFC);
	}
  
  	if ( $outfe eq 1) 
  	{

          # 2005/10/27 skk create the dir first for gsiscp to work
          $file11="$TMPDIR/$Inpfil";
          ($junk_base11,$path11) = fileparse("$file11");
          print STDERR "Creating all dirs in path11 = $path11 (junkbase:$junk_base11)\n";
          mkpath("$path11", 1, 0755);


          # 2005/10/27 skk make it stop if it fails
          $cmd2="$gsiscp $SCPflag $UsratRH:/$SCRD/$Inpfil $file11";
          print STDERR "getfile.cgi:cmd2: $cmd2\n";
          system("$cmd2")==0 or die "system command: $cmd2  failed with error: $?;  \n gsissh_down_err \n (actually $gsiscp ) \n   ";
  	}

                      #IF mss.ncsa.uiuc.edu is storage system ==> will get history files
                      # ?? But if not found in ls at alternate ?? do we do this????

# 1 Must be preference qcrjm or preference file.  User uberftp to get from MSS.

  	else
  	{
                         #Now Determine if there is a pathname associated with the file.
                         #In this case it is neither a qcrjm or preferences files (or
                         #any other that go into the top level directory 
                         #(internal or external/GridChemUserName)
                         #Must make sure to change both TMPDIR and cdToDIR in mss

                         #Separate path and file name.  Make user path begins with "/".
            $_=$Inpfil;
              print STDERR "getfile.cgi: Checking for file path on file $_\n";
            if ( /\// ) {  /(.*\/)(.*)/; $pathf=$1; $fileName=$2; 
                           if($pathf =~ /^[^\/]/){$pathf = "/".$pathf; }  # Put / at beginning if none there.
                          #$hasPath = true;
                           $hasPath = 1;
                           mkpath("$TMPDIR". $pathf, 1, 0750);
                           $TMPDIR .= $pathf;
                           print STDERR "MW1:getfile.cgi: path is $pathf and file is $fileName\n";

                        }
            else        { $fileName=$Inpfil; 
                          #$hasPath = false;
                           $hasPath = 0;
                           $pathf = "";                                 # Set to nothing; make perl -w happy.
                           print STDERR "getfile.cgi: File has no path! \n";
                        }


                            # If getting preference file, get & cache history file

            if($fileName  eq $preferenceFile ){
               $getCmd = "get $fileName; get $qcrjmFile ${qcrjmFile}_cache" ;   
            }
            else{ 
               $getCmd = "get $fileName";
            }

                            # GridChem User has $GridChemUserName prefix in mss.

            if  ($IsGridChem eq "true") { $cdToDir = "cd internal/$GridChemUserName" . $pathf . ";" ; }
            else                        { $cdToDir = "cd external/$GridChemUserName" . $pathf . ";" ; }

            $retrieveFile = 1;

                           # Now, if history file requested, check for cached version
#                            Took out.  Kent 1/24/2006
#            if($fileName eq $qcrjmFile ){
#
#               my $nowTime = time();
#
#               # positional file status information
#               # dev,...,size,atime,mtime,ctime,blksize,blocks
#               #  0       7                10
#
#               my @statInfo       = stat("$TMPDIR/${qcrjmFile}_cache");
#               my $fileSize       = $statInfo[ 7];
#               my $fileCreateTime = $statInfo[10];
#
#               if( $fileCreateTime >= $nowTime - 4 ){
#
#                  ###2006/01/20 OUTDATED CODE -- rename("$TMPDIR/${qcrjmFile}_cache", "$TMPDIR/$qcrjmFile");
#                  $retrieveFile = 0;
#               }
#               else{
#
#                  $retrieveFile = 1;
#               }
#
#            }



            if($retrieveFile){

               # try mass storage

               system('echo "" > $TMPDIR/gsi.passwd');
   
               chdir "$TMPDIR"; unlink "uberftp.err";

#                               We don't need this now.
#                             open( GETFILE,">>$TMPDIR/getfile.log") or die;
#                             print GETFILE "CDTODIR:$cdToDir GETCMD:$getCmd \n";
#                             open( GETFILE );

              # 2005/12/19 skk Temp fix for the preferences bug - removing unneeded files from mss 

              if ($fileName ne $preferenceFile && $fileName ne $qcrjmFile) {
                system("$uberftp -a GSI -P 2811 -H mss.ncsa.uiuc.edu " .
                       "\"quote site wait; $cdToDir  $getCmd\">> uberftp.err ");
              }else{
                # temp 
                my $mypwd = getcwd();
                print STDERR " getfile:DEBUG20060120B: $0: we skip going to mss for the file $fileName\n";
                print STDERR " getfile:DEBUG20060120A: $0: The current dir is $mypwd  \n";
              }
            }
	}
}
else
# we are dealing with kerberos here
{
	print STDERR "Using kerberos based authentication and krb5 rcp/rsh\n";
	print STDERR "First get the file from mss to a local hpc (cu.ncsa.uiuc.edu) \n";

	# lixh:for debug
        #Amr: add db support

        $krb5_scp = DB::getGMSAttribute("krb5-scp-program-path");
        $krb5_rsh = DB::getGMSAttribute("krb5-rsh-program-path");

        $ENV{krb5_scp}=$krb5_scp;
        $ENV{krb5_rsh}=$krb5_rsh;

	system('$krb5_rsh -l $UserName $HPCsys msscmd get $Inpfil');
        print STDERR "Then get the file from local hpc to proxy server \n";

        print STDERR "$krb5_scp $UsratHPC:$Inpfil $TMPDIR/$Inpfil \n";
        system('$krb5_scp $UsratHPC:$Inpfil $TMPDIR/$Inpfil');	
}

chdir "$TMPDIR"; 
if($hasPath){ chdir "$pathf"; }

$FName = "$Inpfil";

if($hasPath){ $FName="$fileName"; }

$ENV{FName} = "$FName";


#sleep 5;

print STDERR " getfile: before printing out the file $FName \n";

if (-e $FName)
{
	open(TMPFIL,"$FName") || die "couldn't open \"$FName\": $!\n";
        print STDERR "File $FName is being transferred to the client by $GridChemUserName at $date\n";
	while(<TMPFIL>)
	{
    		chop $_;
    		$_ =~ s/\n/ /;
    		print $_,"\n";
	}

	close(TMPFIL);
}
else
{
	print "ERROR";
        print STDERR "getfile.cgi ERROR: File $FName not sent \n";
}

my $ts1 = time();
my $td  = $ts1-$ts0;
print STDERR "LEAVING getfile.cgi $td\n"
