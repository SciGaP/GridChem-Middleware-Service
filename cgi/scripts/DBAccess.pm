# The purpose of this module is to regulate accesses to the flat file/database
# It has the neccessary functionalities to read the specification file and database
# and store it in a hash table and then responds to various queries oringinated from other modules.
# Written by Amr Ahmed, modified by Xiaohai Li 

##############################################################################################
# everythings is crated within this package
##############################################################################################

package DBAccess;
use strict; 
use base qw(Exporter);
our @EXPORT = qw($dbh $dbh2 checkUser updatejobTable updatejobTable2 updateprojectStateTable);
#our @EXPORT = qw($dbh checkUser updatejobTable updatejobTable2 updatejobTable3 updateprojectStateTable);
our @EXPORT_OK = qw();

use lib '/var/www/gms';
use gms_db_params;
use DBI; 
use Date::Manip;
use Date::Format;
use DateTime;
use DateTime::Format::MySQL;
use DateTime::Format::HTTP;
use Digest::SHA1 qw( sha1_hex);

my %hashTable; #this hash table holds all resource descriptions and is private to this package
my $applications="";
my $machines="";
my $useMySQL = 1;    # if $useMySQL = 1, use DB; else, user flat file.

my $devel_version=0;  # 2006/10/20 skk 
print STDERR "DEBUG: this file was called as $0\n";   # 2006/10/20 temp
$devel_version=1 if ($0 =~ /devel/) ; 

our $dbh;
our $dbh2;

### csg 10/30/2006 removing user name and password from script
my $user=$GMS_WS_DB_DEVEL_USER;
my $passwd=$GMS_WS_DB_DEVEL_PASS;

#our $dbh3;
if ($useMySQL == 1) {

   print STDERR "DEBUG: DBAccess.pm: i will be calling mysql as gms_db_manager \n";

   # commented -nik
	#$dbh = DBI->connect ("dbi:mysql:database=GMS;host=$GMS_WS_DB_DEVEL_HOST;port=$GMS_WS_DB_DEVEL_PORT",$GMS_WS_DB_DEVEL_USER,$GMS_WS_DB_DEVEL_PASS,{RaiseError => 1, AutoCommit =>1 }) or &dienice("Can't connect to database: $DBI::errstr");
	$dbh = DBI->connect ("dbi:mysql:$GMS2_0_DB_DATABASE:host=$GMS2_0_DB_HOST:port=$GMS2_0_DB_PORT",$GMS2_0_DB_USER,$GMS2_0_DB_PASS,{RaiseError => 1, AutoCommit =>1 }) or &dienice("Can't connect to database: $DBI::errstr");

   print STDERR "DEBUG: DBAccess.pm: Connecting to GMS_WS on derrick \n";

   
#    if ($devel_version) {
#       my $host="derrick.tacc.utexas.edu";
#       my $port="3307";
#    } else {
#       my $host="ccg-mw1.tacc.utexas.edu";
#       my $port="3306";
#    }
###
### Uncomment this and delete previous line to remove reference to DB password
###
      print STDERR "GMS_WS_DB_DEVEL_HOST=$GMS_WS_DB_DEVEL_HOST\n";
      print STDERR "GMS_WS_DB_DEVEL_PORT=$GMS_WS_DB_DEVEL_PORT\n";
   
      my $dsn = "dbi:mysql:$GMS2_0_DB_DATABASE:host=$GMS2_0_DB_HOST:port=$GMS2_0_DB_PORT";
      #$dbh2 = DBI->connect ($dsn,$GMS_WS_DB_DEVEL_USER,$GMS_WS_DB_DEVEL_PASS,{RaiseError => 1, AutoCommit =>1 }) or &dienice("Can't connect to database: $DBI::errstr") ;
      $dbh2 = DBI->connect ("dbi:mysql:$GMS2_0_DB_DATABASE:host=$GMS2_0_DB_HOST:port=$GMS2_0_DB_PORT",$GMS2_0_DB_USER,$GMS2_0_DB_PASS,{RaiseError => 1, AutoCommit =>1 }) or &dienice("Can't connect to database: $DBI::errstr") ;
      

      #commented -nik
      #my $dsn = "DBI:mysql:database=GMS_WS;host=$GMS_WS_DB_DEVEL_HOST;port=$GMS_WS_DB_DEVEL_PORT";
      #$dbh2 = DBI->connect ($dsn,$GMS_WS_DB_DEVEL_USER,$GMS_WS_DB_DEVEL_PASS,{RaiseError => 1, AutoCommit =>1 }) or &dienice("Can't connect to database: $DBI::errstr") ;
}

#############################################################################################
# print out error message
# Xiaohai Li, 7/12/2005
############################################################################################
sub dienice {
        my($msg) = @_;
	print STDERR "$msg";
	print "DATABASE_IS_NOT_RESPONDING_err:$msg";
        exit;
}

sub dbdie {
	my($package, $filename, $line) = caller;
        my($errmsg) = "Database error: $DBI::errstr called from $package $filename line $line";
        &dienice($errmsg);
}

#############################################################################################
# 1. check username and password in DB.user
#    Assume that loginName in DB.user is unique
#    Xiaohai Li, 7/12/2005
# 2. check accounting information from DB.user, DB.project, DB.projectState
#    Xiaohai Li, 8/11/2005
#############################################################################################

sub checkUser {
        use Digest::SHA1 qw( sha1_hex);
        my($userName,$userPass) = @_;
        # Encrypt password
        $userPass = sha1_hex($userPass);

        my $userType       = "No:Invalid username";
        my $user           = "No:" ;
	my $communityValid =   0   ;
	my $externalValid  =   0   ;
	my $gridChemProject=   0   ;
	my $adminType      = "user";
	my $projectType            ; 
	my $projectID      =   0   ;
	my $projectName    = "NA"  ;
	my $SUsAwarded     = "NA"  ;
	my $SUsUsed        = "NA"  ;

        my $userID;

	my $sth;
        my $sth1;
        my $sth2;
        my $hash_ref;
        my $hash_ref1;
        my $hash_ref2;

   if ($useMySQL == 1) {

      $userType = "No: Invalid username";

      ###2006/07/11 $sth      = $dbh->prepare("select * from user where loginName= binary \"?\"") or &dbdie;
      ###2006/07/11 $sth      = $dbh->prepare('select * from user where loginName= binary "?";') or &dbdie;
      $sth      = $dbh->prepare('select * from user where loginName=?') or &dbdie;

      $sth->execute($userName) or &dbdie;

      if ($hash_ref = $sth->fetchrow_hashref) { 

         $user   = "Yes:";
         $userID = $hash_ref->{ID};

         if ($hash_ref->{password} ne $userPass) { $userType = "No:Invalid password"; } 
       # if ("true" ne "true"){ $userType = "No:Invalid password"; } 
         else {
##                   # determine user type, defaults to not valid (0)

            if ($hash_ref->{communityValid}) { $communityValid =  $hash_ref->{communityValid}   ;}				
            if ($hash_ref->{externalValid} ) { $externalValid  =  $hash_ref->{externalValid}    ;}
            if ($hash_ref->{adminType}     ) { $adminType      = "$hash_ref->{adminType}" . ":" ;}
 
            if    ( $communityValid  &&   $externalValid ) { $projectType = "both"     ; } 
            elsif ( $communityValid  && ! $externalValid ) { $projectType = "community"; } 
            elsif (!$communityValid  &&   $externalValid ) { $projectType = "external";  } 
            else                                           { $projectType = "No valid project"; $adminType = "";}
 
            $userType =  $user . $adminType . $projectType ;

               # check accounting information

            if ($communityValid) {

               if( $gridChemProject = $hash_ref->{gridChemProject} ){
                  $projectName = "CCGCOMM".$gridChemProject;
               }
               else{
                  $projectName = "Invalid_gridChemProject";
               }

               $sth2=$dbh->prepare("select * from projectState where gridChemProject=?") or &dbdie;
               $sth2->execute($gridChemProject) or &dbdie; 

               if ($hash_ref2 = $sth2->fetchrow_hashref) {
                  if( $hash_ref2->{SUsAwarded}){ $SUsAwarded = $hash_ref2->{SUsAwarded} ;}
                  else                         { $SUsAwarded = 0                        ;}
                  if( $hash_ref2->{SUsUsed   }){ $SUsUsed    = $hash_ref2->{SUsUsed }   ;}
                  else                         { $SUsUsed    = 0                        ;}
               }

               $sth2->finish();

               $userType = "$userType:$projectName:$SUsAwarded:$SUsUsed";
            }

            if ($externalValid) {

               $sth1 = $dbh->prepare("select * from userResources as ur join project as p on ".
                                     "ur.projectID=p.ID where (ur.userID=? AND p.type='external')") or &dbdie;
               $sth1->execute($userID) or &dbdie;

               while( $hash_ref1= $sth1->fetchrow_hashref) {
                  if( $hash_ref1->{name}){ $projectName = $hash_ref1->{name} ;}
                  else                   { $projectName = "default"          ;}
                  if( $hash_ref1->{ID}  ){ 

                     $projectID   = $hash_ref1->{ID};
                     $sth2 = $dbh->prepare("select * from projectState where projectID=?") or &dbdie;
                     $sth2->execute($projectID) or &dbdie; 
                     if ($hash_ref2 = $sth2->fetchrow_hashref) {
                        if( $hash_ref2->{SUsAwarded}){ $SUsAwarded = $hash_ref2->{SUsAwarded} ;}
                        else                         { $SUsAwarded = "NA"                     ;}
                        if( $hash_ref2->{SUsUsed   }){ $SUsUsed    = $hash_ref2->{SUsUsed }   ;}
                        else                         { $SUsUsed    = 0                        ;}
                     }

                     $userType = "$userType:$projectName:$SUsAwarded:$SUsUsed";
                     $sth2->finish();	
                 }

               }
               $sth1->finish();

            }
				
#  				my $allocStart = -1;
#				my $allocEnd = -1;
#                                if ($hash_ref->{allocationStart}) {
#					my $dt1 = DateTime::Format::MySQL->parse_datetime($hash_ref->{allocationStart});
#                    			$allocStart = $dt1->strftime("%s");
#				}
#				if ($hash_ref->{allocationEnd}) {
#					my $dt2 = DateTime::Format::MySQL->parse_datetime($hash_ref->{allocationEnd});
#                                	$allocEnd = $dt2->strftime("%s");
#				}
#                                my $now = time2str("%s",time);
#                                if ($now >= $allocEnd) {
#					$userType = "No:Allocation is expired";
#				} else {
#					$userType = "Yes:$hash_ref->{adminType}:$hash_ref->{projectType}";
#				}	
#				if ($hash_ref->{SUsRequested}) {
#					$SUsRequested = $hash_ref->{SUsRequested};
#				}
#				if ($hash_ref->{SUsAwarded}) {
#					$SUsAwarded = $hash_ref->{SUsAwarded};
#				}

         } #Has Valid Password 

      } # Found in DB

      $sth->finish();

   } # Using DB 

   else { $userType = "No:No active project"; }

   return $userType;
}

############################################################################################
# insert an entry into job table in DB
# Xiaohai Li, 08/12/2005
############################################################################################

sub updatejobTable {
	my ($jobid,$jobname,$researchProjectName,$scrd,$np,$inpTxt,$time,$userName,$system,$application) = @_;
        if ($devel_version) {
           print STDERR "Inside updateJobTable in devel: application=$application\n";
        } else {
           print STDERR "Inside updateJobTable: application=$application\n";
	}
	# check DB.user to get userID and projectID
        my $sth1 = $dbh->prepare("select * from user where loginName=?") or &dbdie;
        $sth1->execute($userName) or &dbdie; 
        my $hash_ref1 = $sth1->fetchrow_hashref;
	my $userID = $hash_ref1->{ID};
	my $projectID = $hash_ref1->{gridChemProject};
	$sth1->finish();

	# check DB.softwareResource, DB.computeResource, and DB.softwareInstallations to get SoftwareInstallationID
        my $sth4 = $dbh->prepare("select * from computeResource where name=?") or &dbdie;
        $sth4->execute($system) or &dbdie; 
        my $hash_ref4 = $sth4->fetchrow_hashref;
	my $computerResourceID = $hash_ref4->{ID};
	$sth4->finish();
#        print STDERR "updatejobTable: select * from softwareResource where acronym=$application\n";
#        my $sth5 = $dbh->prepare("select * from softwareResource where acronym=$application") or &dbdie;
#        $sth5->execute() or &dbdie; 
#        my $hash_ref5 = $sth5->fetchrow_hashref;
#	my $softwareResourceID = $hash_ref5->{ID};
#	$sth5->finish();
	
	print STDERR "updatejobTable: loginName = $userName, userID = $userID, projectID = $projectID, computerResourceID = $computerResourceID \n";

	# update DB.job
        #my $sth2 = $dbh->prepare("select COUNT(*) from job") or &dbdie;
        #$sth2->execute or &dbdie;
	#my @row_array = $sth2->fetchrow_array;
	#my $ID = $row_array[0];
	#$ID = $ID + 1;
        
        # 2006/08/30 skk TEMP?? for rion
        #print "GMS_JOBID $ID\n";
        #print "GMS_WS_JOBID $ID\n";

	#$sth2->finish();
        
       # my $sth3 = $dbh->prepare("insert into job (ID,localJobID,name,researchProjectName,scratchDir,processorCount,input,requestedCPUTime,created,projectID,userID,cost,status,usedCpuTime,usedWallTime,Updated) values(?,?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?,NOW())") or &dbdie;
#	$sth3->execute($ID,$jobid,$jobname,$researchProjectName,$scrd,$np,$inpTxt,$time,$projectID,$userID,0.00,"scheduled",0,0) or &dbdie;
        my $sth3 = $dbh->prepare("insert into job (ID,localJobID,name,researchProjectName,scratchDir,processorCount,input,requestedCPUTime,created,projectID,userID,cost,status,usedCpuTime,usedWallTime,Updated,computeResource,softwareName) values(LAST_INSERT_ID(),?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?,NOW(),?,?)") or &dbdie;
	$sth3->execute($jobid,$jobname,$researchProjectName,$scrd,$np,$inpTxt,$time,$projectID,$userID,0.00,"scheduled",0,0,$system,$application) or &dbdie;
	$sth3->finish(); 
}


sub updatejobTable2 {
###  Uncomment this later to set Jobs.created to $job_submit_time
	my ($localJobID,$jobName,$researchProjectName,$projName,$scratchDir,$np,$input,$time,$job_submit_time,$userName,$system,$application,$queue) = @_;
###	my ($localJobID,$jobName,$researchProjectName,$projName,$scratchDir,$np,$input,$time,$userName,$system,$application,$queue) = @_;
	###
	### Need:
	###	userID from userName
	###	projectID from projName and userID
	###	computeResourceID from system		
	###	softwareResourceID from application
	###	queueID from queue
	###

	###
	### Get userID corresponding to loginName ###
	###
	print STDERR "select userID from Users where userName=$userName\n";
        my $sth1 = $dbh2->prepare("select userID from Users where userName=?") or &dbdie;
        $sth1->execute($userName) or &dbdie;
        my $hash_ref1 = $sth1->fetchrow_hashref;
	my $userID=$hash_ref1->{userID};
	print STDERR "userID=$userID for loginName=$userName\n";

        my $sth_test = $dbh2->prepare("select comment from Users where userName='milfeld'") or &dbdie;
        $sth_test->execute() or &dbdie;
        my $hash_test = $sth_test->fetchrow_hashref;
	my $comment=$hash_test->{comment};
	print STDERR "comment=$comment for loginName=milfeld\n";
        $sth_test->finish();

	###
	### Get computeResourceID from system
	###
 ## changed hostname to name -nik
        print STDERR "select resourceID from Resources where name like '%$system%';";
	$sth1=$dbh2->prepare("select resourceID from Resources where name like '%$system%';");
	$sth1->execute() or &dbdie;
        $hash_ref1 = $sth1->fetchrow_hashref;
	my $computeResourceID=$hash_ref1->{resourceID};
	print STDERR "CRID=$computeResourceID for system=$system\n";

	###
	### Get projectID corresponding to userID, projName and resourceID
	###
	$projName=~s/\s+//g;

	### Get all projectIDs associated with user on the resource
	### Then pick the one with the same allocation name as $projName
	###
	### Need to change this so that the lookup is done on ProjectResource
	### instead, to pick out only the one valid project
	###$sth1=$dbh2->prepare("select projectID from UserProjectResource where userID=$userID and resourceID=$computeResourceID and allocationName like '%$projName%';");
	print STDERR "select pr.projectID from UserProject up, ProjectResource pr where up.userID=$userID and up.projectID=pr.projectID and pr.resourceID=$computeResourceID and pr.enabled=1 and pr.allocationName like '%$projName%';";
	$sth1=$dbh2->prepare("select pr.projectID from UserProject up, ProjectResource pr where up.userID=$userID and up.projectID=pr.projectID and pr.resourceID=$computeResourceID and pr.enabled=1 and pr.allocationName like '%$projName%';");
	$sth1->execute() or &dbdie;
	$hash_ref1 = $sth1->fetchrow_hashref;
	my $projectID=$hash_ref1->{projectID};
	print STDERR "projectID=$projectID for projName=$projName\n";

	###
	### Get softwareResourceID from application
	###
	#my $app="Gaussian" if $application=~/gaussian/i;
	#$app="GAMESS" if $application=~/gamess/i;
	#$app="NWChem" if $application=~/nwchem/i; 
        #print STDERR "select resourceID from Resources where name='$application';\n";
	#$sth1=$dbh2->prepare("select resourceID from Resources where name='$application';");
	#$sth1->execute() or &dbdie;
        #$hash_ref1 = $sth1->fetchrow_hashref;
	#my $softwareResourceID=$hash_ref1->{resourceID};
	#print STDERR "SRID=$softwareResourceID for application=$application\n";

	#added -nik for GMS2_0
        my $app="Gaussian" if $application=~/gaussian/i;
        $app="GAMESS" if $application=~/gamess/i;
        $app="NWChem" if $application=~/nwchem/i;
        print STDERR "select softwareResourceID from Software where name='$application';\n";
        $sth1=$dbh2->prepare("select softwareResourceID from Software where name='$application';");
        $sth1->execute() or &dbdie;
        $hash_ref1 = $sth1->fetchrow_hashref;
        my $softwareResourceID=$hash_ref1->{softwareResourceID};
        print STDERR "SRID=$softwareResourceID for application=$application\n";




	###
	### Get queueID from queue
	###
	$queue=~s/\s+//g;
	print STDERR "select queueID from Queues where name like '%$queue%' and  computeResourceID=$computeResourceID\n";
	$sth1=$dbh2->prepare("select queueID from Queues where name like '%$queue%' and  computeResourceID=$computeResourceID;");
	$sth1->execute() or &dbdie;
        $hash_ref1 = $sth1->fetchrow_hashref;
	my $queueID=$hash_ref1->{queueID};
	print STDERR "queueID=$queueID for queue=$queue\n";

### Uncomment this later to set Jobs.created to $job_submit_time
	# all dates were getting inserted with a created date of  midnight of the submit day.  i'm appending the current time to them.  it will not hurt anything.
#	my $tsecond;
#	my $tminute;
#	my $thour;
#	my $tdayOfMonth;
#	my $tmonth;
#	my $tyearOffset;
#	my $tdayOfWeek;
#	my $tdayOfYear;
#	my $tdaylightSavings;
#
#	($tsecond, $tminute, $thour, $tdayOfMonth, $tmonth, $tyearOffset, $tdayOfWeek, $tdayOfYear, $tdaylightSavings) = localtime();
#	my $ttime;
#	if ($thour < 12) {
#        	if ($thour < 10) {
#                	$thour="0$thour";
#        	}
#       		$ttime="AM";
#	} else {
#        	$thour=$thour-12;
#       		if ($thour < 10) {
#                	$thour="0$thour";
#		}
#        	$ttime="PM";
#	}
#
#	if ($tminute < 10) {
#        	$tminute="0$tminute";
#	}
#
#	$ttime="$thour:$tminute$ttime";
	
	# this will put the date in "dd-mm-yy hh:mm[AM|PM]" format
        ### using Date::Manip to set the above format 
        ### csg 10/09/2006
        my $ttime=Date::Manip::UnixDate("today","%I:%M%p");
	my $dt="$job_submit_time $ttime";
        print STDERR "Job submit time is $dt\n";
	#my $dt="$job_submit_time ";
###	my $dt=localtime();
	my $class = 'DateTime::Format::HTTP';
	my $Date = $class->parse_datetime($dt);
        print STDERR "Job submit time is $Date\n";
	
	$dt=DateTime::Format::MySQL->format_datetime($Date);
       ###	$sth1=$dbh2->prepare("Insert into Jobs set localJobID=?,jobName=?,researchProjectName=?,scratchDir=?,requestedCPUs=?,requestedCPUTime=?,created=?,queueID=?,projectID=?,allocationName=?,userID=?,status=?,computeResourceID=?,softwareResourceID=?,input=?");
## added nik for GMS2_0
        $sth1=$dbh2->prepare("Insert into Jobs set localJobID=?,name=?,researchProjectName=?,scratchDir=?,requestedCPUs=?,requestedCPUTime=?,created=?,queueID=?,projectID=?,allocationName=?,userID=?,status=?,computeResourceID=?,softwareResourceID=?,input=?");

	$sth1->execute($localJobID,$jobName,$researchProjectName,$scratchDir,$np,$time,$dt,$queueID,$projectID,$projName,$userID,'SCHEDULED',$computeResourceID,$softwareResourceID,$input) or &dbdie;

        ###
        ### Get jobID from GMS_WS.Jobs
        ###
	print STDERR "select * from Jobs where localJobID=$localJobID and computeResourceID=$computeResourceID and softwareResourceID=$softwareResourceID order by created\n";
	$sth1=$dbh2->prepare("select * from Jobs where localJobID=$localJobID and computeResourceID=$computeResourceID and softwareResourceID=$softwareResourceID order by created;");
	$sth1->execute();
	my $tmp_hash=$sth1->fetchrow_hashref;
	my $jobID=$tmp_hash->{jobID};
	$sth1->finish();
        print "GMS_WS_JOBID $jobID\n";
	print STDERR "GMS_WS JobID for this job is $jobID\n";
	print STDERR "Finished updating Jobs table on derrick\n";

}


#sub updatejobTable3 {
#	my ($localJobID,$jobName,$researchProjectName,$projName,$scratchDir,$np,$input,$time,$userName,$system,$application,$queue) = @_;
#	###
#	### Need:
#	###	userID from userName
#	###	projectID from projName and userID
#	###	computeResourceID from system		
#	###	softwareResourceID from application
#	###	queueID from queue
#	###
#
#	###
#	### Get userID corresponding to loginName ###
#	###
#        my $sth1 = $dbh3->prepare("select userID from Users where userName=?") or &dbdie;
#        $sth1->execute($userName) or &dbdie;
#        my $hash_ref1 = $sth1->fetchrow_hashref;
#	my $userID=$hash_ref1->{userID};
#	print STDERR "userID=$userID for loginName=$userName\n";
#
#	###
#	### Get computeResourceID from system
#	###
#       print STDERR "select resourceID from Resources where hostname like '%$system%';";
#	$sth1=$dbh3->prepare("select resourceID from Resources where hostname like '%$system%';");
#	$sth1->execute() or &dbdie;
#        $hash_ref1 = $sth1->fetchrow_hashref;
#	my $computeResourceID=$hash_ref1->{resourceID};
#	print STDERR "CRID=$computeResourceID for system=$system\n";
#
#	###
#	### Get projectID corresponding to userID, projName and resourceID
#	###
#	$projName=~s/\s+//g;
#
#	### Get all projectIDs associated with user on the resource
#	### Then pick the one with the same allocation name as $projName
#        print STDERR "select projectID from UserProjectResource where userID=$userID and resourceID=$computeResourceID and allocationName like '%$projName%';";
#	$sth1=$dbh3->prepare("select projectID from UserProjectResource where userID=$userID and resourceID=$computeResourceID and allocationName like '%$projName%';");
#	$sth1->execute() or &dbdie;
#	$hash_ref1 = $sth1->fetchrow_hashref;
#	my $projectID=$hash_ref1->{projectID};
#	print STDERR "projectID=$projectID for projName=$projName\n";
#
#	###
#	### Get softwareResourceID from application
#	###
#	my $app="Gaussian" if $application=~/gaussian/i;
#	$app="GAMESS" if $application=~/gamess/i;
#	$app="NWChem" if $application=~/nwchem/i; 
#        print STDERR "select softwareResourceID from SoftwareResources where name='$application';\n";
#	$sth1=$dbh3->prepare("select softwareResourceID from SoftwareResources where name='$application';");
#	$sth1->execute() or &dbdie;
#        $hash_ref1 = $sth1->fetchrow_hashref;
#	my $softwareResourceID=$hash_ref1->{softwareResourceID};
#	print STDERR "SRID=$softwareResourceID for application=$application\n";
#
#	###
#	### Get queueID from queue
#	###
#	$queue=~s/\s+//g;
#	$sth1=$dbh3->prepare("select queueID from Queues where name like '%$queue%' and  computeResourceID=$computeResourceID;");
#	$sth1->execute() or &dbdie;
#        $hash_ref1 = $sth1->fetchrow_hashref;
#	my $queueID=$hash_ref1->{queueID};
#	print STDERR "queueID=$queueID for queue=$queue\n";
#
#	my $dt=localtime();
#	my $class = 'DateTime::Format::HTTP';
#	my $Date = $class->parse_datetime($dt);
#	$dt=DateTime::Format::MySQL->format_datetime($Date);
#	$sth1=$dbh3->prepare("Insert into Jobs set
#	localJobID=?,jobName=?,researchProjectName=?,scratchDir=?,requestedCPUs=?,requestedCPUTime=?,created=?,queueID=?,projectID=?,allocationName=?,userID=?,status=?,computeResourceID=?,softwareResourceID=?,input=?");
#	$sth1->execute($localJobID,$jobName,$researchProjectName,$scratchDir,$np,$time,$dt,$queueID,$projectID,$projName,$userID,'SCHEDULED',$computeResourceID,$softwareResourceID,$input) or &dbdie;
#
#        ###
#        ### Get jobID from GMS_WS.Jobs
#        ###
#	$sth1=$dbh3->prepare("select * from Jobs where localJobID=$localJobID and computeResourceID=$computeResourceID and softwareResourceID=$softwareResourceID order by created;");
#	$sth1->execute();
#	my $tmp_hash=$sth1->fetchrow_hashref;
#	my $jobID=$tmp_hash->{jobID};
#	$sth1->finish();
#        print "GMS_WS_JOBID $jobID\n";
#	print STDERR "GMS_WS JobID for this job is $jobID\n";
#	print STDERR "Finished updating Jobs table on derrick\n";
#
#}

#############################################################################################
# update job and projectState table in DB
# Xiaohai Li, 08/12/2005
#############################################################################################

sub updateprojectStateTable {

	my($jobid,$jobstartTime,$jobendTime,$cpuUsage,$jobStatus) = @_;	
	my $cost = $cpuUsage*1;    #compute SUs
	
	#update DB.job
	if ($jobStatus eq "running") {
        	my $sth1 = $dbh->prepare("update job set startTime=?, status=?, Updated=NOW() where localJobID=?") or &dbdie;
		$sth1->execute($jobstartTime,$jobStatus,$jobid) or &dbdie;
		$sth1->finish();
	} else {
        	my $sth1 = $dbh->prepare("update job set cost=?, startTime=?, stopTime=?, status=?, usedWallTime=?, Updated=NOW() where localJobID=?") or &dbdie;
		$sth1->execute($cost,$jobstartTime,$jobendTime,$jobStatus,$cpuUsage,$jobid) or &dbdie;
		$sth1->finish();
	} 

	if (($jobStatus eq "stopped") || ($jobStatus eq "finished")) {
	
		# get projectID from job table
        	my $sth2 = $dbh->prepare("select * from job where localJobID=?") or &dbdie;
        	$sth2->execute($jobid) or &dbdie;
       		my $hash_ref2 = $sth2->fetchrow_hashref;
		my $projectID = $hash_ref2->{projectID};
		$sth2->finish(); 

		# get projectType from project table
        	my $sth3 = $dbh->prepare("select * from project where ID=?") or &dbdie;
        	$sth3->execute($projectID) or &dbdie;
        	my $hash_ref3 = $sth3->fetchrow_hashref;
		my $projectType = $hash_ref3->{type};
		$sth3->finish(); 

		# update projectState table
		my $sth4;
		my $sth5;
		if ($projectType eq "community") {
        		my $sth5 = $dbh->prepare("select * from projectState where gridchemProject=?") or &dbdie;
        		$sth5->execute($projectID) or &dbdie;
        		my $hash_ref5 = $sth5->fetchrow_hashref; 
			$cost = $hash_ref5->{SUsUsed} + $cost;
			$sth5->finish();
        		$sth4 = $dbh->prepare("update projectState set SUsUsed=?, updated=NOW()  where gridchemProject=?") or &dbdie;
			$sth4->execute($cost,$projectID) or &dbdie;
			$sth4->finish(); 
		} else { 
        		my $sth5 = $dbh->prepare("select * from projectState where projectID=?") or &dbdie;
        		$sth5->execute($projectID) or &dbdie;
        		my $hash_ref5 = $sth5->fetchrow_hashref; 
			$cost = $hash_ref5->{SUsUsed} + $cost;
			$sth5->finish();
        		$sth4 = $dbh->prepare("update projectState set SUsUsed=?, updated=NOW()  where projectID=?") or &dbdie;
			$sth4->execute($cost,$projectID) or &dbdie;
			$sth4->finish(); 
		} 
	}
}

##############################################################################################
sub loadResources{
	# the flat file is the first paramater
	my $file = $_[0];
	open(RESOURCES,"< $file");
	my  $line;
	my @temp;
	
     	
	#first stage is to read the GMS specifications
	
	LOOP: while($line = <RESOURCES>){
	       
	    #skip empty lines and lines that start with ";"
	    if (($line =~ /^;/) or ($line =~ /^\s*$/)){
		next LOOP;
	    }
	    #check to see if we reached the end of the GMS which
	    #is determined by the appearance of machine-name line
	    if($line =~ /^machine-name/){
		last LOOP;
	    }
            #othewise the line is of the following format
	    #attribute=value
	    #first remove end of lines
	    $line =~ s/\r//;  $line =~ s/\n//;
	    @temp = split("=",$line);
	    $temp[1]=~s/\"//g;
	    $hashTable{$temp[0]}= $temp[1];
        }
	
	
	#now we reached a machine name section. Note that there is something still in $line!
	# first we need to check if MYSQL is being used if not then we can continue read resource info
	# from the file but if it is in used then we should read it from the DB
	if($useMySQL){
		loadResourcesFromTables();
		return;
	}
	
	$line =~ s/\r//;  $line =~ s/\n//;
        @temp = split("=",$line);
	$temp[1]=~s/\"//g;
	my $machineName = $temp[1];
        $machines = addObjectToList($machineName, $machines);
	my $applicationName;
	my $key;
	#now go into a loop to read all machien specificatons
	LOOP2: while($line=<RESOURCES>){
		#skip empty lines and lines that start with ";"
		next LOOP2 if (($line =~ /^;/) or ($line =~ /^\s*$/));
		$line =~ s/\r//;  $line =~ s/\n//;
		@temp = split("=",$line);
		$temp[1]=~s/\"//g;
		$temp[0]=~s/\s//;  $temp[1]=~s/\s//;
		#check to see if we reached the end of this machine-specification
		if($temp[0] eq "machine-name"){
		        $machineName= $temp[1];
			$machines = addObjectToList($machineName, $machines);
		}elsif ($temp[0] eq "application-name"){ #this is an application attribute
                        $applicationName= $temp[1];
                        addMachineToApplicationList($machineName,$applicationName);
			$applications = addObjectToList($applicationName, $applications);
		}elsif ($temp[0] eq "application-path"){
		        $key = join("_", ($machineName,$applicationName,"path"));
			$hashTable{"$key"}=$temp[1];
		}elsif ($temp[0] eq "available-queues"){
		        registerMachineQueues($machineName,$temp[1]);
		}elsif($temp[0] eq "available-projects"){
                        registerMachineProjects($machineName,$temp[1]);        
                }else{ #this is a regular machien attribute
			$key = join("_", ($machineName, $temp[0]));
			$hashTable{"$key"}=$temp[1];
		}
	}

	$hashTable{"machines"}=$machines;
	$hashTable{"applications"}=$applications;

} #end LoadResources
#*******************************************************************************************
sub addObjectToList{
    my $object = $_[0];
    my $list  = $_[1];
    #print "called with @_ \n"; 
    if(!($list =~ /$object/)){   
         #object is not in the list so add it
	if( $list eq ""){
	    $list = $object;
	}else{
	    $list = join (",", $list, $object);
        }
    }
    return $list;
}
#*********************************************************************************************
# This function adds a machine to the list of machine that have this application
#**********************************************************************************************
sub addMachineToApplicationList{
   my $machineName = $_[0];
   my $applicationName= $_[1];
   
   #check if this application has an entry in the hash table
   my $list = $hashTable{"$applicationName"};
   if($list eq ""){
   	$list = "$machineName";
   }else{
       if (!($list =~ /$machineName/)){
   	    $list = join(",", $list,$machineName);
       }
   }
   
   # finally update its entry in the hastable
   $hashTable{"$applicationName"}=$list;
   return 1;
}

#*********************************************************************************************
# This function parses the available queues for a given machine and stores in the hastable 
# an a comma seperated list for this machien where the first queue is the default one.
#**********************************************************************************************
sub registerMachineQueues{
    my $machineName =$_[0];
    my $queues = $_[1];  
    my $key;
    my $defaultQueue = "";
    my $i;
    #queues is a comma separated list like "normal,*debug*" where the * is around the default queue
    # we just need to remove the * from the default queue and move it to the first entry in the list
    
    my @allQueues = split(",",$queues);
    $i=0;
    my $list = "";
    while($i <= $#allQueues){
    	if($allQueues[$i] =~ /\*/ ){ #this is the default queue
    		$defaultQueue = $allQueues[$i];
    		$defaultQueue =~ s/\*//g;
    	}else{
	    if($list eq ""){
		$list ="$allQueues[$i]";
	    }else{
    		$list = join (",", $list, $allQueues[$i]);
    	    }
	}
    	$i = $i + 1;
    }
    
    # add the default queue if it exists to the front of the list
    if( $defaultQueue ne ""){
	if($list ne ""){
	    $list = join (",", $defaultQueue, $list );
	}else{
	    $list = $defaultQueue;
	}
    }
    
    #That is it. now store the list in the hash table
    $key = join("_", $machineName, "queues");
    $hashTable{$key}=$list;
    
    return 1;
}


#*********************************************************************************************                                                                                  
# This function parses the available queues for a given machine and stores in the hastable                                                                                       
# an a comma seperated list for this machien where the first queue is the default one.                                                                                          
#**********************************************************************************************                                                                                 
 sub registerMachineProjects{
    my $machineName =$_[0];
    my $projects = $_[1];
    my $key;
    my $defaultProject = "";
    my $i;

    my @allProjects = split(",",$projects);
    $i=0;
    my $list = "";
    while($i <= $#allProjects){
        if($allProjects[$i] =~ /\*/ ){ #this is the default queue                                                                                                               
  	    $defaultProject = $allProjects[$i];
	    $defaultProject =~ s/\*//g;
        }else{
            if($list eq ""){
                $list ="$allProjects[$i]";
            }else{
                $list = join (",", $list, $allProjects[$i]);
            }
        }
        $i = $i + 1;
    }

    # add the default Project if it exists to the front of the list                                                                                                             
  
    if( $defaultProject ne ""){
        if($list ne ""){
            $list = join (",", $defaultProject, $list );
        }else{
            $list = $defaultProject;
        }
    }

    #That is it. now store the list in the hash table                                                                                                                           
  
    $key = join("_", $machineName, "projects");
    $hashTable{$key}=$list;

    return 1;
}

##############################################################################################
# The following are a set of retreival functions 				
##############################################################################################
sub getGMSAttribute{
	my $attributeName = $_[0];
	return $hashTable{$attributeName};
}

#********************************************************************************************
sub getMachineAttribute{
	my $machineName = $_[0];
	my $attributeName = $_[1];
        my $key = join("_",@_);
	return $hashTable{"$key"};
}
		
#********************************************************************************************			
sub getMachineApplicationPath{
	my $machineName = $_[0];
	my $applicationName = $_[1];
        my $key= join("_",($machineName, $applicationName, "path"));
	return $hashTable{$key};
}
#********************************************************************************************
sub getMachineQueues{
      my $machineName = $_[0];
      my $key = join("_",$machineName, "queues");
      return $hashTable{"$key"};

}

#********************************************************************************************                                                                                   
 sub getMachineProjects{
    my $machineName = $_[0];
    my $key = join("_",$machineName, "projects");
    return $hashTable{"$key"};
}
#********************************************************************************************
sub getMachinesThatHaveApplication{
      my $key = $_[0];
      return $hashTable{"$key"};
}
#********************************************************************************************
sub printAllKeyValuePairs{
   
    while ( my ($key, $value) = each(%hashTable) ) {
	print "$key => $value\n";
    }
}
#********************************************************************************************
sub getMachines{
    return $hashTable{"machines"};
}
#********************************************************************************************
sub getApplications{
    return $hashTable{"applications"};
}
#*********************************************************************************************
# This function takes a machien name and a user name, and returns its scratch directory on this
# machine
#**********************************************************************************************
sub getScratchDirectory{
   my $machine = $_[0];
   my $ssh     =$_[1];
   my $pf   =$_[2];
   my $rf    =$_[3];
   my $TMP  =$_[4]; 
   my $rsh  =$_[5];
   my $x509_ =$_[6]; 
  

   #******************************************************************
   #first step: is to get scratch directory base from the hash table
   #******************************************************************
   my $base = getMachineAttribute($machine,"scratch-directory-base"); 
  
   #*************************************************************************************
   #Second step: Get the necessary paramaters from the machine itself
   #these paramters can be either: 
   #                                 _USER_  = loginID 
   #                                 _WORK_  = working directory 
   #************************************************************************************* 
   print STDERR "DBACCESS!! --------- $base is the base i got from the psecs file";
   $ENV{machine}=$machine;
   $ENV{pf}=$pf;   $ENV{rf}=$rf;  $ENV{ssh}=$ssh;  $ENV{TMP}=$TMP;
   
   my $Work;
   my $Home;
   #if($base =~ /_USER_/){ 
   # we should get the user anyhow
   unlink "$TMP/USER.out";
   print STDERR " $x509_ old before calling $ssh $rf $pf $machine whoami >> $TMP/USER.out\n";
   if ($x509_ eq 1){
     system('$ssh $rf $pf $machine whoami >> $TMP/USER.out'); 
   } else {
     system('$rsh $rf $machine whoami >> $TMP/USER.out');
   }
   # 2006/09/18 skk - Let us check if we got anything else other than just 1 word 
   #   Sometimes .bashrc or even .softenv can spit out things - let us also WARN if 
   #   that is the case - so as if there is another call where we are not so careful, it will give us a heads up 
   open(OFC,"$TMP/USER.out"); 
   my @User = <OFC>;
   close(OFC);
   my $nlines=$#User+1;
   print STDERR "WARNING: The command \"ssh/rsh ... $machine whoami \"  has given more than one lines of output: 
\"@User\" \nNUMLINES= $nlines \nWe will only consider the last line. \n"  if ( $nlines  > 1 );
   my $User=$User[-1];  # last line we care about 
   $User =~ s/\r//g;
   $User =~ s/\n//g;
   if ( $User ne "" ){
     print STDERR "I got user from $machine = \"$User\" \n";
   }else{
     die "whoami_command_failed_err" ;
   }



   if($base =~ /_USER_/){ 
     $base =~ s/_USER_/$User/;
   }
   
   if($base =~ /_WORK_/){
       # we should get the user's working directory
       $ENV{User}=$User;
       print STDERR "before calling $ssh $rf $pf $User $machine /bin/env WORK >> $TMP/WORK.out";
       unlink "$TMP/WORK.out";
       if($x509_ eq 1){
	   system('$ssh $rf $pf $machine /bin/env WORK > $TMP/WORK.out  2>&1');
       }else{
	   system('$rsh $rf  $machine /bin/env WORK > $TMP/WORK.out  2>&1');
       }
       open(OFC,"$TMP/WORK.out");
       $Work = <OFC>;
       close(OFC);
       $Work =~ s/^ *//;
       $Work =~ s/\n//;
       $base =~ s/_WORK_/$Work/;
       print STDERR "DBACCESS:: I got $Work from $machine and base is $base"; 
   }

   if($base =~ /_HOME_/){
       # we should get the user's home directory
       $ENV{User}=$User;
       print STDERR "before calling $ssh $rf $pf $User $machine echo \$HOME >> $TMP/HOME.out";
       unlink "$TMP/HOME.out";
       if($x509_ eq 1){
	   system('$ssh $rf $pf $machine echo \$HOME > $TMP/HOME.out  2>&1');
       }else{
	   print STDERR "/usr/kerberos/bin/rsh $rf $machine echo \$HOME > $TMP/HOME.out\n";
	   system('/usr/kerberos/bin/rsh $rf  $machine echo \$HOME > $TMP/HOME.out  2>&1');
       }
       open(OFC,"$TMP/HOME.out");
       $Home = <OFC>;
       close(OFC);
       $Home =~ s/^ *//;
       $Home =~ s/\n//;
       $base =~ s/_HOME_/$Home/;
       print STDERR "DBACCESS:: I got $Home from $machine and base is $base"; 
   }
  
   
   print STDERR "DBAccess I am returning $User and $base \n";
   return ($User,$base);

}
 		
#*************************************************************************************************			
sub loadResourcesFromTables{
	#now read the list of machines
	my ($machineName, $field, $value, $key);
	my $sth2 = $dbh->prepare ("SELECT softwareInstallations.scriptPath, softwareResource.acronym 
                                    from softwareInstallations, softwareResource 
                                    where softwareInstallations.softwareResourceID=softwareResource.ID and softwareInstallations.computeResourceID=?") or &dbdie;
	my $sth = $dbh->prepare ("SELECT computeResource.*,localResourceManagementSystem.type as scheduler FROM computeResource,localResourceManagementSystem where localResourceManagementSystem.ID = computeResource.localManagerID") or &dbdie; 
	$sth->execute () or &dbdie; 
	while (my $ref = $sth->fetchrow_hashref ()){
		#first load its attributes into the hash table
		$machineName = $ref->{name};
		
		#add this into the hash table
		$field =  "scratch-directory-base"; 
		$value = $ref->{scratchDirectoryBase};
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;

		#add this into the hash table
		$field =  "Pflag"; 
		$value = $ref->{Pflag};
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;
		
		
		#add this into the hash table
		$field =  "SCPflag"; 
		$value = $ref->{SCPflag};
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;
		
		#add this into the hash table
		$field =  "reflag"; 
		$value = $ref->{reflag};
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;
		
		#add this into the hash table
		$field =  "jobs-program-path"; 
		$value = $ref->{jobsProgramPath};
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;

		#add this into the hash table
		$field =  "hist-program-path"; 
		$value = $ref->{histProgramPath};
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;


		#add this into the hash table
		$field =  "kill-program-path"; 
		$value = $ref->{killProgramPath};
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;


		#add this into the hash table
		$field =  "scheduler"; 
		$value = $ref->{scheduler};
		#the following to ensure backward comtability with the flat file since all the scripts are based on its conventions
		if($value eq "pbs_pro") { $value ="pbs";}
		if($value eq "loadleveler")  {$value ="ll";}
		if($value eq "lsf")  {$value ="lsf";}  # funndy, eh?
		$key = join("_", ($machineName, $field));
		$hashTable{"$key"}=$value;

		#for each machine read its applications and load the script path
		my $ID = $ref->{ID}; #the machine ID
		$sth2->execute($ID) or &dbdie;
		my $ref2 = $sth2->fetchrow_hashref ();
		#now get software path
		my $applicationName = $ref2->{acronym};
		$value = $ref2->{scriptPath};
        	$key = join("_", ($machineName,$applicationName,"path"));
		$hashTable{"$key"}=$value;		
		$sth2->finish (); 
                
	} 
	$sth->finish (); 
}


#*************************************************************************************************
#we must return to the main package...				
package main;		

#we must return 1 at the end of this file
return 1;


## the folloiwng are just  a simple test
#$useMySQL =1;
#DB::loadResources("specifications.cfg");
#DB::printAllKeyValuePairs;
#print DB::getGMSAttribute("security-temp-directory"), " \n";
#print " program path is ", DB::getMachineAttribute("tun.ncsa.uiuc.edu","jobs-program-path"), " \n" ;
#print "gauss on tun is on  ", DB::getMachineApplicationPath("tun.ncsa.uiuc.edu","Gaussian"), " \n";
#print "queues at tunb are ",DB::getMachineQueues("tunb.ncsa.uiuc.edu"), "\n";
#print "queues at longhorn are ",DB::getMachineQueues("longhorn.tacc.utexas.edu"),"\n";
#print "machines that can be used to run Gaussian applications are ",DB::getMachinesThatHaveApplication("Gaussian"),"\n";
#print "machines are ", DB::getMachines(), "\n";
#print "applications are ", DB::getApplications(),"\n";
