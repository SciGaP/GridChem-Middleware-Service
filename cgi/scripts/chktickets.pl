##Amr changed all hardwierd resource access with DB call
## load the DBAccess module

##require("DBAccess.pl");
##DB::loadResources("specifications.cfg");  #load the resource description file
##DB was already loaded by calling cgi scripts

# get the security temp directory
$securityTmpDir = DB::getGMSAttribute("security-temp-directory");
$ENV{securityTmpDir}=$securityTmpDir;
$userTmpDirBase = DB::getGMSAttribute("user-temp-directory");
$ENV{userTmpDirBase}= $userTmpDirBase;

#            Since this file is "required", use $TMPDIR derived from parent
#            Kent 10/25/05
  $TMPDIR = "$userTmpDirBase/$UserName"; 

$ENV{TMPDIR} = "$TMPDIR";
$KRB5CCNAME = "$securityTmpDir/krb5cc_$UserName";
$ENV{KRB5CCNAME} = "$KRB5CCNAME";

#check if ticket is valid else send a message back
unlink "$TMPDIR/curtim";
unlink "$TMPDIR/kltim";

$cmawk = DB::getGMSAttribute("krb5-cmawk-program-path");
$ENV{cmawk}=$cmawk;
$klist = DB::getGMSAttribute("krb5-check-klist-program-path");
$ENV{klist}=$klist;
$tim1  = DB::getGMSAttribute("krb5-tim1-program-path");
$ENV{tim1}=$tim1;

system('$klist $KRB5CCNAME | awk -f $cmawk  > $TMPDIR/kltim ');
system('$tim1 > $TMPDIR/curtim ');

open(KLT,"$TMPDIR/kltim");
while(<KLT>) {
@tfl = split('[/: ]',$_,99);
}
close(KLT);


open(CST,"$TMPDIR/curtim");
while(<CST>) {
    $_ =~ s/  / /g;
    $_ =~ s/^ //;
    @csf = split('[ :]',$_,99);
}
close(CST);




foreach $i (0 .. 5) 
{
  $tfl[$i] =~ s/0\d/sprintf("%2d",$&)/e;
  $csf[$i] =~ s/0\d/sprintf("%2d",$&)/e;

  if ($tfl[$i] < $csf[$i]) 
  {
    if ($i == 0) 
    {
      &chkyr;
    }
    elsif ($i == 1) 
    {
      &chkmy;
    }
    elsif ($i == 2)
    {
      print "Expired Kerberos Ticket 2 \n";
      system ('echo "Expired Kerberos Ticket 2" > $TMPDIR/ticket.log ');
      exit;
    }
    elsif ($i == 3) 
    {
      &chkdmy;
    }
    elsif ($i == 4) 
    {
      &chkhdmy;
    }
    elsif ($i == 5)
    {
      &chkmhdmy;
    }
  }

} 

return 1;




sub chkyr
{

  if ($tfl[2] <= $csf[2])
  {
    print "Expired Kerberos Ticket 0 \n";
    system ('echo "Expired Kerberos Ticket 2 \n" > $TMPDIR/ticket.log ');
    exit;
  }
}





sub chkmy
{
  if ($tfl[0] <=  $csf[0] )
  {
    &chkyr;
  }
}





sub chkdmy
{
  if ($tfl[1] <= $csf[1] )
  {
    &chkmy;
  }
}





sub chkhdmy
{
  if ($tfl[3] <= $csf[3] )
  {
    &chkdmy;
  }
}





sub chkmhdmy
{
  if ($tfl[4] <= $csf[4] )
  {
    &chkhdmy;
  }
}





sub chksmhdmy
{
  if ($tfl[5] <= $csf[5] )
  {
    &chkmhdmy;
  }

}



