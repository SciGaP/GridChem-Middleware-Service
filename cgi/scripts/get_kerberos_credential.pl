#!/usr/bin/perl5.60 -w

$date = `date`;

print STDERR "today is $date \n"; 

use CGI qw/:standard/;

use Expect;

my $program  = "/usr/kerberos/bin/kinit";
my $username    = "$REMOTE_USER";
$ENV{username}="$username";
my $password    = "$SENTPW";
my $lifetime    = "24";
$outfile1 = join('',"/tmp/",krb5cc_);
my $outfile = join('',$outfile1,"$username");
$ENV{outfile}="$outfile";
unlink "$outfile";
my $args     = "-c $outfile $username";

print STDERR "kerberos credential is in $outfile for $username\n";

# use expect to run the command
my $cmd_filehandle = Expect->spawn("$program $args");

#system('sleep 1');

print $cmd_filehandle "$password\n";

# gather the output into the array
@output = <$cmd_filehandle>;

# close the filehandle to the command
$cmd_filehandle->soft_close();
$cmd_filehandle->hard_close();

# lixh_add
# now you have an array called @outputmsg which has the rest of the output...
# ouput[3] contains the output information 

$outputmsg = join(''," ", $output[0]);
$outputmsg =~ s/^ *//;
if ($outputmsg =~ /^kinit(v5)/) {
    $outputmsg =~ s/(.*):/ERROR:/;
    &printerror($outputmsg);
} else {
    system ('echo "User $username obtained X509 credential good for 2 hours." >> /tmp/krbauth.log ');
    system('chmod g-r $outfile');
    print STDERR " User $username obtained Kerberos credential good for 24 hours\n";
    print "User $username obtained Kerberos ticket good for 24 hours\n";

}

sub printerror
{
    my $errmsg = $_[0];
    print "$errmsg \n";
    system ('echo $errmsg >> /tmp/krbauth.log ');
    system ('echo $REMOTE_USER >> /tmp/krbauth.log ');
    exit;
}

sub printsuccess
{
    system ('echo "User `$username` obtained X509 credential good for 2 hours." >> /tmp/krbauth.log ');
    exit;
}


