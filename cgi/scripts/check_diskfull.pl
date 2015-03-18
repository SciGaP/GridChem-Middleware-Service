#!/usr/bin/perl -w

# by lixh 11/24/04
# Simplified for various servers, not just ccg-mw1.  1/24/2006 Kent

use CGI qw/:standard/;
use strict;

my $warn_limit = 95;

my $date = `date`;

my @line=`df -k`;

# gather the output of the command

my $dir;
my $used;	
my $percent_size;

my @Dir;
my @Used;

my $counter = 0;
my $i=1;

while ($i <= $#line){

if( $line[$i] =~ /(\d+)\%\s+(.*$)/ ){
        $percent_size = $1;
        $dir          = $2;
	if ($percent_size >= $warn_limit) {
		$Dir[$counter] = $dir;
		$Used[$counter] = $percent_size;
		$counter ++;
 		print STDERR " DISK WARNING: $percent_size % of $dir is used.\n";
	}
   } #% end

   $i= $i+1;
} 

if ($counter > 0)
{
	$ENV{check_diskfull} = "false";
	$dir = join(", ",@Dir);
	$used = join("%, ",@Used);
	print  " check_diskfull_error: $dir ($used% used) on the middle server are almost full $date\n";
}
else{
	$ENV{check_diskfull} = "true";
}

return 1;
