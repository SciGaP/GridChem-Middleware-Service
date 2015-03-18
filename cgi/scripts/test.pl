#!/usr/local/bin/perl
$X509_USER_PROXY ="/tmp/dooleyCCG_X509";
#$X509_USER_PROXY ="/tmp/x509up_u521";
$ENV{X509_USER_PROXY}="$X509_USER_PROXY";
print STDERR "ENV{X509_USER_PROXY} $ENV{X509_USER_PROXY}  $X509_USER_PROXY\n";
#$X509_USER_CERT = "$X509_USER_PROXY";
#$ENV{X509_USER_CERT} = "$X509_USER_CERT";
$cmd_tmp="/usr/local/NMI/bin/gsissh -v -x  -p 22 cobalt whoami";

#system("$cmd_tmp");
$cmd_tmp = "/usr/local/NMI/bin/gsissh -p 22 -f -x Cobalt \"mkdir -p /scratch/users/ccguser/dooley/tempdirs\" ";
system("$cmd_tmp");
$TEMP_DIRNAME = "/scratch/users/ccguser/dooley/tempdirs/tempdirXXXXXXX";


$gsissh="/usr/local/NMI/bin/gsissh";
$Pflag="-v -p 22";
$reflag="-x";
$Sysnam="Cobalt";

$cmd="$gsissh $Pflag -f $reflag $Sysnam ".'"perl -e \'use File::Temp qw/ :mktemp /; \$tmpdir = mkdtemp( \"' . "$TEMP_DIRNAME" . '\" );print  \$tmpdir\'"';
#$cmd='$gsissh $Pflag -f $reflag $Sysnam "perl -e \'use File::Temp qw/ :mktemp /; \$tmpdir = mkdtemp( \"' . "$TEMP_DIRNAME" . '\" );print  \$tmpdir\'"';

#$cmd='/usr/local/NMI/bin/gsissh -v -p 22 -f -x Cobalt "perl -e \'use File::Temp qw/ :mktemp /; \$tmpdir = mkdtemp( \"' . "$TEMP_DIRNAME" . '\" );print  \$tmpdir\'"';

print STDERR "BEFORE cmd: $cmd\n";

$temp_dirname=qx($cmd) or die "ERROR: not able to create a temporary directory name using command \"$cmd\": $!\n   \n gsissh_down_err \n ";



#$gshcmdo=$cmd_tmp;
exit

