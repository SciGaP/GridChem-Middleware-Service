#!/usr/bin/perl  -w

#############################################################################################
##Amr: Annotate, comment and indent code
##  
##  Read the paramter sent to the script
#############################################################################################
#print "Content-type: text/plain\n\n";
print "Content-type: text/html\n\n";
print "hello world \n";
#$r = mkdir ( "/var/www/html/temp/test");
#print "\n NEW code is $r and error is $!";

$r= test();

print $r,"  ", $t, "\n";


sub test{

return (4,3)
}
