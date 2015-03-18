#!/usr/bin/perl -wT

# by lixh 11/28/04
# check if the project is valid


use CGI qw/:standard/;
use Expect;
use strict;

my $sysnam = $ENV{Sysnam};
my $username = $ENV{UserName};
my $accnt = $ENV{Accnt};
my $pflag = $ENV{Pflag};
my $reflag = $ENV{reflag};
my $X509 = $ENV{X509};
my $gsissh = $ENV{gsissh};
my $rsh    = $ENV{krb5_rsh};

#############################################################################################
##Amr:
##  Load the resource database 
#############################################################################################

# run "usage" to get the available project name
my $cmd_filehandle;
if ($sysnam eq "cu.ncsa.uiuc.edu" ) {
	if ($X509 == 1){
                print STDERR "try $gsissh $pflag $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m copper";
    		$cmd_filehandle = Expect->spawn("$gsissh $pflag $reflag $sysnam /usr/local/bin/usage -u $username -m copper");
	}
	else {
                print STDERR "Before $rsh $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m copper";
    		$cmd_filehandle = Expect->spawn("$rsh $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m copper");
                print STDERR "After $rsh $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m copper";
	}
}
elsif ($sysnam eq "tun.ncsa.uiuc.edu") {
	if ($X509 == 1){
                 print STDERR "try $gsissh $pflag $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m tungsten";
	        $cmd_filehandle = Expect->spawn("$gsissh $pflag $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m tungsten");
	}
	else {
	        print STDERR "Before $rsh $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m tungsten\n";
	        $cmd_filehandle = Expect->spawn("$rsh $reflag -l $username $sysnam /usr/local/bin/usage -u $username -m tungsten");
	}
}
else {
	print STDERR "$sysnam is not a valid server \n";
	exit;
}

print STDERR "after making project inquiry\n";
# check if $Accnt is a valid project name 
my @output = <$cmd_filehandle>;

close ($cmd_filehandle);
print STDERR "after making project inquiry  i am about to read the output \n";

my @results = grep(/$username/,@output);
my $length = scalar(@results);
my @proj;
my $Proj;
for (my $i = 0; $i < $length; $i++) {
	my $line = $results[$i];
	$line =~ s/  */ /g;
	$line =~ s/^ *//g;
	my @items = split(/ /,$line);
        my $lenitems = scalar(@items);
	$proj[$i] = $items[0];
}
if (grep(/$accnt/,@proj)) {
	print STDERR "proj = @proj \n";
	$ENV{psn_check} = "true";
	print STDERR "psn checking true... \n";
}
else {
	print STDERR "$accnt is not availabe project for $username \n";
	$ENV{psn_check} = "false";
	print STDERR "psn checking false... \n";
	$Proj = join(", ",@proj);
	print "psn_checking_error $accnt is not a valid project, the available project for $username is $Proj \n";
}

