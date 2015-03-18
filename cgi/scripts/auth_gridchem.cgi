#! /usr/bin/perl -w

# Does the authorization using the username and password supplied

# written by Xiaohai Li, 7/12/2005
# compare username and password with the ones in DB
# return user type (PI:community,username:community,PI:external)



use lib '.';
use DBAccess;
use Digest::SHA1 qw(sha1_hex);
use strict;

print STDERR "ENTERING auth-gridchem.cgi\n";
my $ts0 = time();


read(STDIN, my $buffer, $ENV{'CONTENT_LENGTH'});
my @lines = split(/\n/,$buffer);
my %FORM;
foreach my $line (@lines)
{
    my @pairs = split(/&/, $line);
    foreach my $pair (@pairs)
    {
        my ($name, $value) = split(/=/, $pair);
        $value =~ tr/+/ /;
        $value =~ s/%([a-fA-F0-9][a-fA-F0-9])/pack("C", hex($1))/eg;
        ### Stop people from using subshells to execute commands
        $value =~ s/~!/ ~!/g;
        $FORM{$name} = $value;
    }
}
print "Content-type: text/plain\n\n";

my $UserName = $FORM{'Username'};
                     $UserName =~ s/\r//;
my $UserPass = $FORM{'Userpass'};
                     $UserPass =~ s/\r//;


# 2006/07/31 skk - Fixing a bug where a user would use uppercase in the username and he would still succeed
#            Now we print an error and exit - the client should pick up the error and let the user know
if ( $UserName =~ m/[A-Z]/){
  print        "gridchem_upper_case_in_username_err";
  print STDERR "gridchem_upper_case_in_username_err";
  exit 1;
};


my $UserType = checkUser($UserName,$UserPass);
if ($dbh) {
    $dbh->disconnect;
}
my $ts1 = time();
my $td  = $ts1-$ts0;

print STDERR " UserType = $UserType \n";
print "$UserType";

print STDERR "LEAVING auth-gridchem.cgi $td\n";
