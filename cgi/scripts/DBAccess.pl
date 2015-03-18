#!/usr/bin/perl
##!/usr/bin/perl -w
# The purpose of this module is to regulate accesses to the flat file incarnation of the resources descriptions
# this module in included in all ohter scripts via a require statment
# it has the neccessary functionalities to read the specification file and store it in a has table
# and then responds to various queries oringinated from whitin other modules.
# Written by Amr Ahmed 
# Last Modified March 11th

##############################################################################################
# every thing is created withing this package
##############################################################################################

package DB;
use strict; 
use DBI; 

use lib "/var/www/gms";
use gms_db_params;
use gms_globus_params;


if($CCG_GMS_GLOBUS_ENV{'ENV'} eq "YES"){
   foreach my $key (keys(%CCG_GMS_GLOBUS_ENV)) {
     #print STDERR "  DBAccess.pl: GLOBENV: $key  $CCG_GMS_GLOBUS_ENV{$key}\n";;
      $ENV{"$key"} = $CCG_GMS_GLOBUS_ENV{$key};
   }
}



   foreach my $key (keys(%ENV)) {
     print STDERR "  DBAccess.pl: GLOBENV: $key  $ENV{$key}\n";;
   }





#$ENV{'GPT_LOCATION'}     ="/usr/local/globus";
#$ENV{'GLOBUS_LOCATION'}  ="/usr/local/globus";
#$ENV{'LD_LIBRARY_PATH'}  ="/usr/local/globus/lib";
#$ENV{'DYLD_LIBRARY_PATH'}="/usr/local/globus/lib";
#$ENV{'LIBPATH'}          ="/usr/local/globus/lib:/usr/lib:/lib";
#$ENV{'SHLIB_PATH'}       ="/usr/local/globus/lib";
#$ENV{'SASL_PATH'}        ="/usr/local/globus/lib/sasl";
#$ENV{'GLOBUS_PATH'}      ="/usr/local/globus";
#$ENV{'GLOBUS_TCP_PORT_RANGE'}="62500,64500";



my %hashTable; #this hash table holds all resource descriptions and is private to this package
my $applications="";
my $machines="";
### 2006/10/19 skk - my $useMySQL = 0;
my $useMySQL = 0;
my $MySQLInitialized = 0;
my $dbh; #data base handle
#my $dbhws; #data base handle
my $devel_version=0;  # 2006/10/20 skk
print STDERR "DEBUG: this file was called as $0\n";   # 2006/10/20 temp
$devel_version=1 if ($0 =~ /devel/) ;
if ($devel_version) {
   print STDERR "ENTERING DBAccess.pl in devel\n";
} else {
   print STDERR "ENTERING DBAccess.pl\n";
}
my $ts0 = time();


#sub loadResources2{
#### Form comma-delimited machine list
#   my $sthws=$dbhws->prepare("select name from Resources where type='compute';");
#   $sthws->execute() or &dbdie;
#   while ($hashws=$sthws->fetchrow_hashref()) {
#      my $hostName=$hashws->{name};
#      $machines=addObjectToList($hostName,$machines);
#      ### List queues available on this machine
#      my $sthwsq=$dbhws->prepare("select cr.computeResourceID, q.name, q.queueID from ComputeResources cr, Queues q where cr.system='$hostName' and cr.computeResourceID=q.computeResourceID;");
#      my $queueList='';
#      while (my $hashq=$sthwsq->fetchrow_hashref()) {
#         $queueList=$queueList.",".$hashq->{name};
#      }
#      $sthwsq->finish();
#      $hashTable{$hostName_queues}=$queueList;
#      ### List projects available on this machine
#      my $sthwsp=$dbhws->prepare("select upr.allocationName from Projects p, UserProjectResource upr, ComputeResources cr where cr.hostname='$hostName' and cr.computeResourceID=upr.resourceID and upr.projectID=p.projectID and p.projectType='community' and upr.userID=2;");
#      my $projectList='';
#      while (my $hashp=$sthwsp->fetchrow_hashref()) {
#          $projectList=$projectList.",".$hashp->{allocationName};
#      }
#      $sthwsp->finish();
#      $hashTable{$hostName_projects}=$projectList;
#      ### Obtain parameters for each machine
#      my $sthws2=$dbhws->prepare("select * from ComputeResources where system='$hostName';");
#      $sthws2->execute() or &dbdie;
#      while (my $hashws2=$sthws2->fetchrow_hashref()) {
#	 my $applicationPath=$hashws2->{executablePath};
#	 $applicationPath=$applicationPath." ".$hashws2->{arguments};
#	 $hashTable{$hostName_$application_path}=$applicationPath;
#         $hashTable{machine-name_$hostName}=$hashws2->{system};
#         $hashTable{globus-node-name_$hostName}=$hashws2->{globusNodeName};
#         $hashTable{scratch-directory-base_$hostName}=$hashws2->{scratchDirectoryBase};
#         $hashTable{Pflag_$hostName}=$hashws2->{Pflag};
#         $hashTable{SCPflag_$hostName}=$hashws2->{SCPflag};
#         $hashTable{reflag_$hostName}=$hashws2->{reflag};
#         $hashTable{LindaIsAvailable_$hostName}=$hashws2->{lindaIsAvailable};
#         $hashTable{jobs-program-path_$hostName}=$hashws2->{jobsProgramPath};
#         $hashTable{hist-program-path_$hostName}=$hashws2->{histProgramPath};
#         $hashTable{kill-program-path_$hostName}=$hashws2->{killProgramPath};
#      }
#   }
#### Form comma-delimited application list, and for each application the list 
#### of compute resources where the application is installed
#   $sthws=$dbhws->prepare("select name from Resources where type='software';");
#   $sthws->execute() or &dbdie;
#   while ($hashws=$sthws->fetchrow_hashref()) {
#      my $application=$hashws->{name};
#      $applications=addObjectToList($applications,$application);
#      my $sthws2=$dbhws->prepare("select sr.name, cr.system, sr.softwareResourceID, cr.computeResourceID from  ComputeResources cr, SoftwareResources sr, SoftwareInstallation si where sr.name='gamess' and sr.softwareResourceID=si.softwareResourceID and si.computeResourceID=cr.computeResourceID;");
#      $sthws2->execute() or &dbdie;
#      while ($hashws2=$sthws2->fetchrow_hashref()) {
#	 my $hostName=$hashws2->{system};
#         addMachineToApplicationList($system,$application);
#      }
#      $sthws2->finish();
#   }
#   $sthws->finish();
#} #end LoadResources2

##############################################################################################
sub loadResources{
	# the flat file is the first paramater
	my $file = $_[0];
	open(RESOURCES,"< $file");
	my  $line;
	my @temp;
# 1/23/06
        my $temp;
	
     	
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
if( ! defined($temp[1]) ){ print STDERR " DBAccess.pl: UNDEFINED temp @temp\n"; }
		$temp[1]=~s/\"//g;
		$temp[0]=~s/\s//;  
#		$temp[1]=~s/\s//;
		$temp[1]=~s/\s*$//;
		#check to see if we reached the end of this machine-specification
		if($temp[0] eq "machine-name"){
		        $machineName= $temp[1];
			$machines = addObjectToList($machineName, $machines);
		}elsif ($temp[0] eq "globus-node-name") {
			$key = join("_",($machineName,"globusnodename"));
			$hashTable{"$key"}=$temp[1];
		} elsif ($temp[0] eq "application-name"){ #this is an application attribute
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
        ### test, remove later
        ### from CSG
        print STDERR "hashTable=$hashTable{'mike.lsu.cct.edu_Gaussian_path'}\n";
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

#  if($list eq ""){                    #Check for existence  Kent 1/24/2006
   if(! defined($list)){
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
# my $k;
# my(@ks)=keys %hashTable;
# foreach $k (@ks){
#     my $val = $hashTable{"$k"};
#     print STDERR  "  *******$k = $val *******\n";
# }
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
#*******************************************************************************************
sub getMachineGlobusnodename{
      my $machineName = $_[0];
      my $key = join("_",$machineName,"globusnodename");
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
###
### CSG
### Should be deprecated. No sense in executing whoami in remote resource if service
### passes external user name as a parameter to the job launch scripts 
###
sub getScratchDirectory{
   my $machine = $_[0];
   my $ssh     =$_[1];
   my $pf   =$_[2];
   my $rf    =$_[3];
   my $TMP  =$_[4]; 
   my $rsh  =$_[5];
   my $x509_ =$_[6]; 
   my $base=$_[7];


 
   #**************************************************************************************
   #first step: is to get scratch directory base and globus node name from the hash table
   #**************************************************************************************
   print STDERR " DBAccess.pl: getScratchDirectory: args: machine=$machine argument:$_[0]\n";
   ###my $base = getMachineAttribute($machine,"scratch-directory-base"); 
   ###$machine = getMachineGlobusnodename($machine);
  
   #*************************************************************************************
   #Second step: Get the necessary paramaters from the machine itself
   #these paramters can be either: 
   #                                 _USER_  = loginID 
   #                                 _WORK_  = working directory 
   #************************************************************************************* 
   print STDERR "DBACCESS!! --------- $base is the base i got for $machine \n";
   print STDERR "DBACCESS!! --------- ssh:pf:rf:TMP:rsh:x509_: $ssh $pf $rf $TMP $rsh $x509_  \n";

   ## 2006/10/10 skk to avoid warnings about uninitialized vars in error_log
     print STDERR "DBACCESS!! --------- ENVX509_USER_PROXY: $ENV{'X509_USER_PROXY'} \n";
  print STDERR "DBACCESS!! --------- ENVX509_USER_PROXY: $ENV{'509_USER_PROXY'} \n" if ($ENV{'509_USER_PROXY'} );
   print STDERR "DBACCESS!! --------- ENVX509_USER_CERT: $ENV{'509_USER_CERT'} \n"   if ($ENV{'509_USER_CERT'} );

   $ENV{machine}=$machine;
   $ENV{pf}=$pf;   $ENV{rf}=$rf;  $ENV{ssh}=$ssh;  $ENV{TMP}=$TMP;
  
   my $Work;
   my $Home;
   #if($base =~ /_USER_/){ 
   # we should get the user anyhow
   ### Changing USER.out to $gridchemuser  5July07 pvs unlink "$TMP/USER.out";
   ##$USER="USER";
   unlink "$TMP/USER.out";
   # 2006/04/12 skk - removed the 2>&1 in the two lines below to fix the problem with mike4 security banner
   my $cmd_tmp;
   if ($x509_ eq 1){
     ####2006/08/11 $cmd_tmp="$ssh -v $rf $pf $machine whoami >> $TMP/USER.out";
     ##print STDERR "Using Globus credential at $X509_USER_PROXY for whoami \n";
     $cmd_tmp="$ssh -v $rf $pf $machine whoami >> $TMP/USER.out";
   }else{
     $cmd_tmp="$rsh $rf     $machine whoami >> $TMP/USER.out";
   }
   print STDERR "DBAccess.pl: \$x509_=$x509_ old before calling $cmd_tmp \n";
   system("$cmd_tmp"); 

   # 2006/09/18 skk - Let us check if we got anything else other than just 1 word
   #   Sometimes .bashrc or even .softenv can spit out things - let us also WARN if
   #   that is the case - so as if there is another call where we are not so careful, it will give us a heads up
   open(OFC,"$TMP/USER.out"); 
   my @User = <OFC>;
   close(OFC);
   my $nlines=$#User+1;
   print STDERR "WARNING: The command \"$cmd_tmp\"  has given more than one lines of output: 
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
       print STDERR "before calling $ssh $rf $pf $machine echo \$WORK >> $TMP/WORK.out \n";
       unlink "$TMP/WORK.out";
       if($x509_ eq 1){
	   system('$ssh $rf $pf $machine echo \$WORK > $TMP/WORK.out 2>&1');
       }else{
	   system('$rsh $rf  $machine echo \$WORK > $TMP/WORK.out  2>&1');
       }
       open(OFC,"$TMP/WORK.out");
       $Work = <OFC>;
       close(OFC);
       $Work =~ s/^ *//;
       $Work =~ s/\n//;
       $base =~ s/_WORK_/$Work/;
       print STDERR "DBACCESS:: I got $Work from $machine and base is $base\n"; 
   }

   if($base =~ /_HOME_/){
       # we should get the user's home directory
       $ENV{User}=$User;
       print STDERR "before calling $ssh $rf $pf $User $machine echo \$HOME >> $TMP/HOME.out \n";
       unlink "$TMP/HOME.out";
       if($x509_ eq 1){
           system('$ssh $rf $pf $machine echo \$HOME > $TMP/HOME.out  2>&1');
       }else{
           print STDERR "$rsh $rf $machine echo \$HOME > $TMP/HOME.out\n";
           system('$rsh $rf  $machine echo \$HOME > $TMP/HOME.out  2>&1');
       }
       open(OFC,"$TMP/HOME.out");
       $Home = <OFC>;
       close(OFC);
       $Home =~ s/^ *//;
       $Home =~ s/\n//;
       $base =~ s/_HOME_/$Home/;
       print STDERR "DBACCESS:: I got $Home from $machine and base is $base \n";
   }

 
   print STDERR "DBAccess I am returning $User and $base \n";
   return ($User,$base);

} 		
#*************************************************************************************************			
sub initDB{

#	$dbh = DBI->connect ("DBI:mysql:host=localhost;database=GMS", 
#                    "web", "web2005NCSA", 
#                    {PrintError => 0, RaiseError => 1}); 
	print STDERR "Connecting to database on derrick\n";
	#$dbh = DBI->connect ("DBI:mysql:host=derrick.tacc.utexas.edu;port=3307:database=GMS", "$GMS_WS_DB_USER", "$GMS_WS_DB_PASS", {PrintError => 1, RaiseError => 1}); 
          $dbh = DBI->connect ("dbi:mysql:$GMS2_0_DB_DATABASE:host=$GMS2_0_DB_HOST:port=$GMS2_0_DB_PORT",$GMS2_0_DB_USER,$GMS2_0_DB_PASS,{RaiseError => 1, AutoCommit =>1 }) or &dienice("Can't connect to database: $DBI::errstr");
### Connect to GMS_WS
#	$dbhws = DBI->connect ("DBI:mysql:host=derrick.tacc.utexas.edu;port=3307:database=GMS_WS", "$GMS_WS_DB_USER", "$GMS_WS_DB_PASS", {PrintError => 1, RaiseError => 1}); 
        $MySQLInitialized = 1;          
}
#*************************************************************************************************			
sub loadResourcesFromTables{
	if($MySQLInitialized == 0){
		initDB;
	}
	#now read the list of machines
	my ($machineName, $field, $value, $key);
	my $sth2 = $dbh->prepare ("SELECT softwareInstallations.scriptPath, softwareResource.acronym from softwareInstallations, softwareResource where softwareInstallations.softwareResourceID=softwareResource.ID and softwareInstallations.computeResourceID=?");
	my $sth = $dbh->prepare ("SELECT computeResource.*,localResourceManagementSystem.type as scheduler FROM computeResource,localResourceManagementSystem where localResourceManagementSystem.ID = computeResource.localManagerID"); 
	$sth->execute (); 
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
		$sth2->execute($ID);
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
my $ts1 = time();
my $td  = $ts1-$ts0;
if ($devel_version) {
   print STDERR "LEAVING DBAccess.pl $td in devel\n";
} else {
   print STDERR "LEAVING DBAccess.pl $td\n";
}
return 1;

#system("echo here >/tmp/test.log");
## the folloiwng are just  a simple test
#$useMySQL =1;
#DB::loadResources("specifications.cfg");
#DB::printAllKeyValuePairs;
print DB::getGMSAttribute("security-temp-directory"), " \n";
print " program path is ", DB::getMachineAttribute("tun.ncsa.uiuc.edu","jobs-program-path"), " \n" ;
#print "gauss on tun is on  ", DB::getMachineApplicationPath("tun.ncsa.uiuc.edu","Gaussian"), " \n";
#print "queues at tunb are ",DB::getMachineQueues("tunb.ncsa.uiuc.edu"), "\n";
#print "queues at longhorn are ",DB::getMachineQueues("longhorn.tacc.utexas.edu"),"\n";
#print "machines that can be used to run Gaussian applications are ",DB::getMachinesThatHaveApplication("Gaussian"),"\n";
#print "machines are ", DB::getMachines(), "\n";
#print "applications are ", DB::getApplications(),"\n";

