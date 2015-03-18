#! /usr/bin/perl -w

########################################################
# written by Xiaohai Li, 8/14/2005
# parse mails to get job information
# update DB.job table and DB.projectState
##########################################################

use lib '.';
use DBAccess;
use DateTime::Format::MySQL;
use DateTime::Format::HTTP;
use Date::Manip;
use strict;

##############################################################
# assume mails are saved in /home/ccg-admin/mail_archive.old
#############################################################

my $mailDir = "/home/ccg-admin";
my $mailFil = "$mailDir/mail_archive.old";
my $tempFil = "/tmp/tempfil";  

# make sure that temporary file exists and is writtable
if (! -e $tempFil) {
	`touch $tempFil`;
	`chmod 666 $tempFil`;
}

##################################################################################
# search entrys in DB.job. If status is not equal to "stopped" or "submission 
# error" or "finished" or "removed", try to get job information from $mailFil
##################################################################################

my $sth1 = $dbh->prepare("select * from job") or &dbdie;
$sth1->execute or &dbdie;
while (my $hash_ref1 = $sth1->fetchrow_hashref) {
	my $jobstatus = $hash_ref1->{status};
	if (($jobstatus ne "stop") && ($jobstatus ne "submission error") && ($jobstatus ne "finished") && ($jobstatus ne "removed")) {
		my $jobID = $hash_ref1->{localJobID};
		my $cpuUsage = 0;
		my $jobStartTime="NULL\(\)";
		my $jobEndTime="NULL\(\)";
		my $jobStatus="NULL\(\)";

		# parse $mailFil to get job information
		my $subject;
		if ($jobID =~ /cu12/) {
			my @array1 = split(/\./,$jobID);
			$subject = "cu12.ncsa.uiuc.edu.$array1[1]";
		} elsif ($jobID =~ /longhorn/) {
			my @array2 = split(/\./,$jobID);
			$subject = "longhorn.tacc.utexas.edu.$array2[1]";
		} else {
			$subject = $jobID;
		}
		print STDERR "subject = $subject \n";

################################################################################################
# To parse the sorted mails under /home/ccg-admin/mail_archive, uncomment the following lines #
#
#		if (my $mailFilPath = `find $mailDir/mail_archive -name "*$subject*"`) {
#			$mailFil = "$mailFilPath";
#		} else {
#			next;
#		}
#################################################################################################

		if (`grep $subject $mailFil`) {
			open(MAILFIL,"$mailFil") || die "couldn't open \"$mailFil\": $!\n";
			while (my $line = <MAILFIL>) {
				if ($line =~ /^Date:/) { 
					open(TEMPFIL,">$tempFil") || die "couldn't open \"$tempFil\": $!\n";
					print TEMPFIL "$line";
					while ($line = <MAILFIL>) {
						if (!($line =~ /^From /)) {
							#print STDERR "$line";
							print TEMPFIL "$line";
						} else {
							last;
						}
					} 
					close(TEMPFIL); 
					
					my $scheduler;
					my $walltime;
					if (`grep $subject $tempFil`) {
						if (`grep "PBS" $tempFil`) {
							$scheduler = "PBS";
						} elsif (`grep "lsf" $tempFil`) {
							$scheduler = "lsf";
						} elsif (`grep "LoadLeveler" $tempFil`) {
							$scheduler = "LoadLeveler";
						} else {
							$scheduler = "condor";
						} 
						print STDERR "scheduler = $scheduler \n";
						my $date = `grep "Date:" $tempFil`;
						my @array3 = split(/: /,$date);
						my $tempTime = $array3[1];
						$tempTime =~ s/^ *//;
						$tempTime =~ s/\r//;
						$tempTime =~ s/\n//; 
						print STDERR "tempTime = $tempTime \n";
						my $class = 'DateTime::Format::HTTP'; 
						my $tempdatetime;
						my @tempusage1;
						my @tempusage2;
						if ($scheduler eq "PBS") {
							if ($walltime = `grep "resources_used.walltime" $tempFil`) {
								$jobStatus = "finished";
								$jobEndTime = $tempTime;
								$tempdatetime = $class->parse_datetime($jobEndTime); 
								$jobEndTime = DateTime::Format::MySQL->format_datetime($tempdatetime);
								@tempusage1 = split(/=/,$walltime);
								$tempusage1[1] =~ s/^ *//;
								$tempusage1[1] =~ s/\r//;
								$tempusage1[1] =~ s/\n//;
								@tempusage2 = split(/:/,$tempusage1[1]);
								$cpuUsage = $tempusage2[0]+$tempusage2[1]/60+$tempusage2[2]/3600; 
							} else {
								$jobStatus = "running";
								$jobStartTime = $tempTime;
								$tempdatetime = $class->parse_datetime($jobStartTime); 
								$jobStartTime = DateTime::Format::MySQL->format_datetime($tempdatetime);
							} 
						} elsif ($scheduler eq "lsf") {
							if ($walltime = `grep "CPU time" $tempFil`) {
								$jobStatus = "finished";
								$jobEndTime = $tempTime;
								$tempdatetime = $class->parse_datetime($jobEndTime); 
								$jobEndTime = DateTime::Format::MySQL->format_datetime($tempdatetime);

								### old
								#@tempusage1 = split(/:/,$walltime);
								#$tempusage1[1] =~ s/^ *//;
								#$tempusage1[1] =~ s/\r//;
								#$tempusage1[1] =~ s/\n//;
								#@tempusage2 = split(/ /,$tempusage1[1]);
								#$cpuUsage = $tempusage2[0]/3600; 

								### new
								unless ($jobStartTime=~/NULL/) {
								   my $delta=DateCalc($jobStartTime,$jobEndTime);
								   $cpuUsage=Delta_Format($delta,'5','%ht');
								}

							} else {
								$jobStatus = "running";
								$jobStartTime = $tempTime;
								$tempdatetime = $class->parse_datetime($jobStartTime); 
								$jobStartTime = DateTime::Format::MySQL->format_datetime($tempdatetime);
							}
						} elsif ($scheduler eq "LoadLeveler") {
							if ($walltime = `grep "Real Time" $tempFil`) {
								$jobStatus = "finished";
								$jobEndTime = $tempTime;
								$tempdatetime = $class->parse_datetime($jobEndTime); 
								$jobEndTime = DateTime::Format::MySQL->format_datetime($tempdatetime);
								@tempusage1 = split(/ 0 /,$walltime);
								$tempusage1[1] =~ s/^ *//;
								$tempusage1[1] =~ s/\r//;
								$tempusage1[1] =~ s/\n//;
								@tempusage2 = split(/:/,$tempusage1[1]);
								$cpuUsage = $tempusage2[0]+$tempusage2[1]/60+$tempusage2[2]/3600; 
							} else {
								$jobStatus = "running";
								$jobStartTime = $tempTime;
								$tempdatetime = $class->parse_datetime($jobStartTime); 
								$jobStartTime = DateTime::Format::MySQL->format_datetime($tempdatetime);
							}
						} else {
							$walltime = `grep "Real Time" $tempFil`;
							@tempusage1 = split(/ 0 /,$walltime);
						        $tempusage1[1] =~ s/^ *//;
							$tempusage1[1] =~ s/\r//;
							$tempusage1[1] =~ s/\n//;
							@tempusage2 = split(/:/,$tempusage1[1]);
							$cpuUsage = $tempusage2[0]+$tempusage2[1]/60+$tempusage2[2]/3600; 
							$jobStatus = "finished";
							$date = `grep "Submitted at" $tempFil`;
							@array3 = split(/: /,$date);
							$jobStartTime = $array3[1];
							$jobStartTime =~ s/^ *//;
							$jobStartTime =~ s/\r//;
							$jobStartTime =~ s/\n//;
							$tempdatetime = $class->parse_datetime($jobStartTime); 
							$jobStartTime = DateTime::Format::MySQL->format_datetime($tempdatetime);
							$date = `grep "Completed at" $tempFil`;
							@array3 = split(/: /,$date);
							$jobEndTime = $array3[1];
							$jobEndTime =~ s/^ *//;
							$jobEndTime =~ s/\r//;
							$jobEndTime =~ s/\n//; 
							$tempdatetime = $class->parse_datetime($jobEndTime); 
							$jobEndTime = DateTime::Format::MySQL->format_datetime($tempdatetime);
						}
						print STDERR "jobID=$jobID, jobStartTime=$jobStartTime, jobEndTime=$jobEndTime, cpuUsage=$cpuUsage, jobStatus=$jobStatus \n";
						# update DB.projectState
						updateprojectStateTable($jobID,$jobStartTime,$jobEndTime,$cpuUsage,$jobStatus); 
					} 
				}
			} 
        		close(MAILFIL);	
		}
	}
}
$sth1->finish();

if ($dbh) {
	$dbh->disconnect; 
}





