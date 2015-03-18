#!/usr/bin/perl  -w
#################################################
#						#
# getzipfile.cgi				#
# Get a binary file from mass storage	 	#
# Rebecca Hartman-Baker				#
# September 20, 2002				#
#						#
#################################################

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
	}
}

print "Content-type: text/plain\n\n";

$UserName = $FORM{'Username'};
$UserName =~ s/\r//;
$Sysnam = $FORM{'System'};
$Sysnam =~ s/\r//;
$Inpfil = $FORM{'fileName'};
$Inpfil =~ s/\n//;
$Inpfil =~ s/\r//;
$GridChemUserName = $FORM{'GridChemUsername'};
$GridChemUserName =~ s/\r//;
$IsGridChem = $FORM{'IsGridChem'};
$IsGridChem =~ s/\r//;

$ENV{GridChemUserName} = "$GridChemUserName";
$ENV{IsGridChem} = "$IsGridChem"; 
print STDERR "IsGridChem = $IsGridChem";
print STDERR "GridChemUserName = $GridChemUserName";
$ENV{UserName} = "$UserName";
$ENV{Sysnam} = "$Sysnam";
$ENV{Inpfil} = "$Inpfil";
$mspath=`/usr/bin/dirname $Inpfil`;
$mspath=~ s/\n//;
$mspath=~ s/\r//;
$ENV{mspath} = "$mspath";
$msfile=`/bin/basename $Inpfil`;
$msfile =~ s/\n//;
$msfile =~ s/\r//;
$ENV{msfile} = "$msfile";
print STDERR " Will retrieve $msfile from $mspath \n";

#############################################################################################
##Amr:  Add DB support
##
##  Load the resource database 
#############################################################################################
##Amr changed all hardwierd resource access with DB call
## load the DBAccess module
require("DBAccess.pl");
DB::loadResources("specifications.cfg");  #load the resource description file

# get the security temp directory
$securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir;

$userTmpDirBase = DB::getGMSAttribute("user-temp-directory");
$ENV{userTmpDirBase}=$userTmpDirBase;

#remove old file
if  ($IsGridChem eq "true"){ $TMPDIR = "$userTmpDirBase/internal/$GridChemUserName";}
else                       { $TMPDIR = "$userTmpDirBase/external/$GridChemUserName";}

$ENV{TMPDIR} = "$TMPDIR";

## this need to be checked
unlink "$TMPDIR/$msfile";

print STDERR "$UserName at getlog\n";
print STDERR `id -g`;
print STDERR `id -u`;
print STDERR `id -ru`;
print STDERR `id -rg`;

#############################################################################################
##Amr: Annotate, comment and Indent code.
##
##  Check which security architecture to use : KRB5 or X509
#############################################################################################

$X509=0;
$User_Cert_File = join("$UserName","$securityTmpDir/","_X509");
$ENV{$User_Cert_File}= "$User_Cert_File";
print STDERR "$User_Cert_File";

if ( -e $User_Cert_File )
{
	$X509_USER_PROXY = join("$UserName","$securityTmpDir/","_X509");
      if  ($IsGridChem eq "true"){
         $X509_USER_PROXY = join("$GridChemUserName,"$securityTmpDir/","CCG_X509");
      }
	$ENV{X509_USER_PROXY}="$X509_USER_PROXY";
	$X509=1;
	print STDERR "getfile: X509 user proxy set $X509_USER_PROXY\n";
}
else
{
	#kerberos
        $KRB5CCNAME = join('', "$securityTmpDir", "/krb5cc","_","$UserName"); 
	$ENV{KRB5CCNAME} = "$KRB5CCNAME";
	print STDERR "krb5 user credentials set\n";
}

$ENV{status} = "$?";
unlink "/$TMPDIR/testrshstatus";
unlink "/$TMPDIR/bsubstatus";

#check if ticket is valid else send a message back
unlink "$TMPDIR/curtim";
unlink "$TMPDIR/kltim";
if (-f $X509_USER_PROXY )
 {
	 $X509=1;
 }
elsif (-f $KRB5CCNAME)
  {
    $checkTickets = DB::getGMSAttribute("krb5-check-ticket-program-path");
    $ENV{checkTickets}=$checkTickets;
    require "chktickets.pl"; 
  }

#############################################################################################
##  Amr: Factor old code to make use of DB calls
#
# now get the reflag of the machine and the scratch directory
#############################################################################################

$reflag = DB::getMachineAttribute($Sysnam, "reflag");
$ENV{reflag}="$reflag";

$scrd   = DB::getMachineAttribute($Sysnam, "scratch-directory-base");
if ($IsGridChem eq "true")
{
   $scrd="$scrd/$GridChemUserName";
} 
$ENV{scrd}=$scrd;

$gsissh = DB::getMachineAttribute($Sysnam,"globus-gsissh-program-path");
$ENV{gsissh}="$gsissh";

#Amr. This is a bizare thing.. Can't really understand why uky is always like that
#if ($Sysnam eq "sdx.uky.edu")
#{
  # we need to go to exechost if the job is running and reset Sysnam to that
  # field 6 of bjobs output

#   $bjobs= DB::getMachineAttribute($Sysnam, "jobs-program-path");
#   $ENV{bjobs}=$bjobs;

#   $sysnmex=`$gsissh -l $UserName $Sysnam $bjobs | /usr/bin/awk '{if (NR ==2) print $6}'`;
#   $Sysnam="$sysnmex.uky.edu";
#   print STDERR "Getting files from the exechost $Sysnam\n";
#   $scrd = "scratch";
#}

$ENV{scrd} = "$scrd";

$UsratRH = "$UserName\@$Sysnam";
$ENV{UsratRH} = "$UsratRH";

#$ENV{reflag} = "$reflag";

#############################################################################################
##  Amr: 1)Annotate, comment and Indent code.
##       2)Add DB support
#
##  Now get the file using the proper security mechanism
#############################################################################################
$date = `date`;
print STDERR "Getfile: $UserName Authenticated via QCRJM at $date \n";
print STDERR "Before if X509 is one\n";
if ($X509 == 1)
{
	print STDERR "Getfile: Using X509 based authentication and gsi scp/ssh for $UserName at $Sysnam \n";
  	# Check if the file is present in scratch and get it and skip mss get
  	if ($Sysnam eq "mss.ncsa.uiuc.edu")
	{
	    $outfe =0;
	}
	else
	{
  		$ssh = DB::getGMSAttribute("globus-ssh-program-path");
                $ENV{ssh}=$ssh;
  		$pflag = DB::getMachineAttribute($Sysnam , "Pflag");
                $ENV{pflag}=$pflag;
	  	system('$ssh -l $UserName $pflag $Sysnam ls $scrd/$Inpfil > $TMPDIR/outfil');
  		open(OFC,"$TMPDIR/outfil");
  		while(<OFC>)
  		{
    			s/\n//;
    			if ($_ eq "$scrd/$Inpfil")
    			{
      			print STDERR "Output file $scrd/$Inpfil exists!\n";
      			$outfe = 1;
    			}
  		}
 	 close(OFC);
	}
     
  	if ( $outfe eq 1) 
  	{
     		$scp = DB::getGMSAttribute("globus-scp-program-path");
		$ENV{scp}=$scp;
     		$SCPflag = DB::getMachineAttribute($Sysnam , "SCPflag");
		$ENV{SCPflag}=$SCPflag;
     		system('$scp $SCPflag $UsratRH:/$scrd/$UserName/$Inpfil $TMPDIR/$Inpfil');
     		print STDERR "before gsincftpget stuff for $UserName for file $SubLog\n";
  	}
  	else
  	{
    		# try mass storage
     		print STDERR "before uberftp stuff for $UserName for file $SubLog\n";
		system('echo "" > $TMPDIR/gsi.passwd');
		chdir "$TMPDIR";
		chdir "$TMPDIR"; unlink "uberftp.err";
		$uberftp = DB::getGMSAttribute("uberftp-program-path");
      		$ENV{uberftp}=$uberftp;
		system('$uberftp -a GSI -P 2811 -H mss.ncsa.uiuc.edu "quote site wait; cd $mspath; get $msfile $TMPDIR/$msfile ">> uberftp.err ');
      	print STDERR "after ";
      		print STDERR "$uberftp -a GSI -P 2811 -H mss.ncsa.uiuc.edu \"quote site wait; cd $mspath; get $msfile $TMPDIR/$msfile \" \n";
   	}
}
else
{
    print STDERR "Using kerberos based authentication and krb5 rcp/rsh\n";
    $gsincftpget = DB::getGMSAttribute("gsincftpget-program-path");
    $ENV{gsincftpget}=$gsincftpget;
    system('$gsincftpget -u $UserName -p gsi.passwd -W "quote site wait" mss.ncsa.uiuc.edu $TMPDIR $Inpfil');
}


@dirs = split(/\//,$Inpfil);
$locName = $dirs[$#dirs];
$FName = "$TMPDIR/$locName";
$ENV{FName} = "$FName";

sleep 5;

print STDERR "before printing out the file\n";

 @dirs = split(/\//,$Inpfil);

$lnc = 0;
if (-e $FName)
{
    open(TMPFIL,"$FName") || die "couldn't open \"$FName\": $!\n";
    while(<TMPFIL>)
    {
    print $_;
    }

    close(TMPFIL);
}
else
{
    print "ERROR";
}





