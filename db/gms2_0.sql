# Sequel Pro dump
# Version 1191
# http://code.google.com/p/sequel-pro
#
# Host: 127.0.0.1 (MySQL 5.1.40)
# Database: GMS_WS
# Generation Time: 2009-12-26 21:06:25 -0600
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table BlackList
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BlackList`;

CREATE TABLE `BlackList` (
  `userID` int(11) NOT NULL DEFAULT '0',
  `softwareID` int(11) NOT NULL DEFAULT '0',
  `created` date NOT NULL DEFAULT '0000-00-00',
  `lastUpdated` date NOT NULL DEFAULT '0000-00-00',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userID`,`softwareID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='ACL to prevent certain users from running apps';



# Dump of table ComputeResources
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ComputeResources`;

CREATE TABLE `ComputeResources` (
  `computeResourceID` int(11) NOT NULL AUTO_INCREMENT,
  `diskTotalSpace` bigint(20) NOT NULL,
  `diskAvailableSpace` bigint(20) NOT NULL DEFAULT '0',
  `availableCPU` bigint(20) NOT NULL DEFAULT '0',
  `totalCPU` bigint(20) NOT NULL,
  `supportEmail` varchar(255) DEFAULT 'help@gridchem.org',
  `scratchDirectoryBase` varchar(255) NOT NULL DEFAULT '$HOME',
  `Pflag` varchar(255) DEFAULT NULL,
  `SCPflag` varchar(255) DEFAULT NULL,
  `reflag` varchar(255) DEFAULT NULL,
  `lindaIsAvailable` int(11) NOT NULL DEFAULT '0',
  `jobsProgramPath` varchar(255) DEFAULT NULL,
  `histProgramPath` varchar(255) DEFAULT NULL,
  `killProgramPath` varchar(255) DEFAULT NULL,
  `showstartPath` varchar(255) NOT NULL DEFAULT 'not available',
  `scheduler` enum('LSF','LL','PBS','MOAB','SGE','CONDOR') NOT NULL,
  `siteAcronym` varchar(10) DEFAULT '',
  `description` varchar(255) DEFAULT '',
  `system` varchar(255) NOT NULL,
  `totalNodes` bigint(20) NOT NULL,
  `peakPerformance` double DEFAULT '0',
  `memory` int(11) NOT NULL,
  `scratchDisk` varchar(255) DEFAULT '0',
  `webSite` varchar(255) DEFAULT 'http://www.gridchem.org',
  `CPULoad` int(11) NOT NULL DEFAULT '0',
  `diskLoad` int(11) NOT NULL DEFAULT '0',
  `memoryLoad` int(11) NOT NULL DEFAULT '0',
  `queueLoad` int(11) NOT NULL DEFAULT '0',
  `jobsRunning` int(20) NOT NULL DEFAULT '0',
  `jobsQueued` int(20) NOT NULL DEFAULT '0',
  `jobsOther` int(20) NOT NULL DEFAULT '0',
  `lastUpdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`computeResourceID`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=latin1;



# Dump of table DNs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DNs`;

CREATE TABLE `DNs` (
  `userID` int(11) NOT NULL DEFAULT '0',
  `DN` varchar(255) NOT NULL DEFAULT '',
  `lastUpdated` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`DN`,`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table Files
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Files`;

CREATE TABLE `Files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `localPath` varchar(255) NOT NULL,
  `remotePath` varchar(255) DEFAULT '0',
  `jobId` int(11) NOT NULL,
  `length` int(128) NOT NULL,
  `created` datetime NOT NULL DEFAULT '2007-04-01 12:00:00',
  `lastUpdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=latin1;



# Dump of table JobNotifications
# ------------------------------------------------------------

DROP TABLE IF EXISTS `JobNotifications`;

CREATE TABLE `JobNotifications` (
  `jobID` int(11) NOT NULL,
  `type` enum('EMAIL','IM','TEXT','SMS','FACEBOOK','TWITTER') NOT NULL DEFAULT 'EMAIL',
  `status` enum('INITIAL','SCHEDULED','RUNNING','STOPPED','SUBMISSION_ERROR','FINISHED','REMOVED','SUBMITTING','FAILED','MIGRATING','UNKNOWN','SUSPENDED','RUNTIME_ERROR','WAITING','HOLD','NOT_IN_QUEUE') NOT NULL DEFAULT 'SCHEDULED',
  `subject` varchar(255) DEFAULT NULL,
  `message` text,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `grmsJobStatusNotificationID` varchar(255) DEFAULT '',
  `delivered` int(1) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=latin1;



# Dump of table Jobs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Jobs`;

CREATE TABLE `Jobs` (
  `jobID` int(11) NOT NULL AUTO_INCREMENT,
  `localJobID` varchar(255) DEFAULT NULL,
  `grmsJobID` varchar(255) NOT NULL DEFAULT 'UNKNOWN',
  `name` varchar(255) NOT NULL,
  `researchProjectName` varchar(255) NOT NULL,
  `allocationName` varchar(255) NOT NULL,
  `scratchDir` text,
  `requestedCPUs` int(11) NOT NULL,
  `requestedCpuTime` datetime NOT NULL,
  `requestedMemory` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `queueID` int(11) NOT NULL,
  `projectID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `cost` double DEFAULT NULL,
  `startTime` datetime DEFAULT '3000-01-01 12:00:00',
  `stopTime` datetime DEFAULT '3000-01-01 12:00:00',
  `status` enum('SUBMITTING','SCHEDULED','HOLD','WAITING','INITIAL','STARTING','RUNNING','PREEMPTED','CHECKPOINTING','MIGRATING','RESUMING','EXITING','SUSPENDED','STOPPED','CANCELLING','FAILED','FINISHED','REMOVED','UNKNOWN','SUBMISSION_ERROR','RUNTIME_ERROR','TIME_ELAPSED','NOT_IN_QUEUE') NOT NULL DEFAULT 'INITIAL',
  `usedCPUTime` double NOT NULL DEFAULT '0',
  `usedWallTime` double DEFAULT '0',
  `lastUpdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usedCPUs` int(11) NOT NULL DEFAULT '0',
  `computeResourceID` varchar(11) NOT NULL DEFAULT '',
  `softwareResourceID` varchar(11) NOT NULL DEFAULT '',
  `storageResourceID` int(11) NOT NULL DEFAULT '23',
  `input` mediumtext,
  `isMultiinput` tinyint(1) NOT NULL DEFAULT '0',
  `metaData` mediumtext,
  `cellNotify` int(11) NOT NULL DEFAULT '0',
  `imNotify` int(11) NOT NULL DEFAULT '0',
  `hidden` int(2) NOT NULL DEFAULT '0',
  `deleted` int(2) NOT NULL DEFAULT '0',
  `workflowID` int(11) DEFAULT NULL,
  PRIMARY KEY (`jobID`)
) ENGINE=InnoDB AUTO_INCREMENT=5626 DEFAULT CHARSET=latin1;



# Dump of table NetworkResources
# ------------------------------------------------------------

DROP TABLE IF EXISTS `NetworkResources`;

CREATE TABLE `NetworkResources` (
  `networkResourceID` int(11) NOT NULL DEFAULT '0',
  `mask` varchar(30) DEFAULT NULL,
  `networkName` varchar(255) DEFAULT NULL,
  `bandwidth` float DEFAULT NULL,
  `latency` float DEFAULT NULL,
  `bwMaeForecast` float DEFAULT NULL,
  `bwMseError` float DEFAULT NULL,
  `bwMaeError` float DEFAULT NULL,
  `bwMSeForecast` float DEFAULT NULL,
  `ltMaeForecast` float DEFAULT NULL,
  `ltMaeError` float DEFAULT NULL,
  `ltMseForecast` float DEFAULT NULL,
  `ltMseError` float DEFAULT NULL,
  `toResourceID` int(11) NOT NULL DEFAULT '1',
  `fromResourceID` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`networkResourceID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Nodes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Nodes`;

CREATE TABLE `Nodes` (
  `nodeID` int(11) NOT NULL AUTO_INCREMENT,
  `computeResourceID` int(11) NOT NULL DEFAULT '0',
  `CPUSpeed` bigint(20) DEFAULT NULL,
  `CPUsPerNode` bigint(20) DEFAULT NULL,
  `memoryPerNode` bigint(20) DEFAULT NULL,
  `memoryAvailable` bigint(20) DEFAULT NULL,
  `ramTotal` bigint(20) DEFAULT NULL,
  `ramAvailable` bigint(20) DEFAULT NULL,
  `occurances` bigint(20) DEFAULT NULL,
  `localDiskSpace` bigint(20) DEFAULT NULL,
  `localDiskDir` varchar(255) DEFAULT NULL,
  `accessByQueues` varchar(255) DEFAULT NULL,
  `swapSpace` bigint(20) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`nodeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Preferences
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Preferences`;

CREATE TABLE `Preferences` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `preferences` text,
  `lastUpdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` datetime NOT NULL DEFAULT '2006-04-27 14:24:00',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `user` (`userID`)
) ENGINE=MyISAM AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;



# Dump of table ProjectRenewals
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ProjectRenewals`;

CREATE TABLE `ProjectRenewals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL DEFAULT '0',
  `renewalDate` datetime NOT NULL DEFAULT '2008-03-11 12:00:00',
  `expirationDate` datetime NOT NULL DEFAULT '2008-03-11 12:00:00',
  `created` date NOT NULL DEFAULT '2008-03-11',
  `allocationAmount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table ProjectResource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ProjectResource`;

CREATE TABLE `ProjectResource` (
  `projectID` int(11) NOT NULL DEFAULT '0',
  `resourceID` int(11) NOT NULL DEFAULT '0',
  `allocationName` varchar(255) NOT NULL DEFAULT '',
  `SUsLocalAwarded` double NOT NULL DEFAULT '0',
  `SUsLocalUsed` double NOT NULL DEFAULT '0',
  `SUsLocalBalance` double NOT NULL DEFAULT '0',
  `startDate` datetime NOT NULL DEFAULT '2006-04-01 00:00:00',
  `endDate` datetime NOT NULL DEFAULT '2007-09-30 00:00:00',
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4969 DEFAULT CHARSET=latin1;



# Dump of table Projects
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Projects`;

CREATE TABLE `Projects` (
  `projectID` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(80) NOT NULL DEFAULT 'unknown',
  `projectType` enum('EXTERNAL','COMMUNITY','TERAGRID') NOT NULL DEFAULT 'COMMUNITY',
  `funder` text,
  `projectDescription` text,
  `comment` text,
  `sponsorClass` enum('UNIVERSITY','GOVERNMENT_LAB','MEDICAL_CENTER','K_12','OTHER') DEFAULT 'UNIVERSITY',
  `sponsorName` varchar(255) DEFAULT 'self',
  `projectStatus` enum('PENDING','ACTIVE','DEACTIVATED','EXPIRED','OVERDRAWN') NOT NULL DEFAULT 'PENDING',
  `projectStart` datetime NOT NULL DEFAULT '2007-04-01 00:00:01',
  `projectEnd` datetime NOT NULL DEFAULT '2007-04-01 00:00:01',
  `createdByUserID` int(11) DEFAULT '0',
  `lastUpdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isDefault` int(4) NOT NULL DEFAULT '0',
  `SUsRequested` bigint(20) DEFAULT '0',
  `SUsAwarded` double DEFAULT '0',
  `SUsUsed` double DEFAULT '0',
  `memoryMBperCPU` int(11) DEFAULT '0',
  `SUsPerJob` int(11) DEFAULT '0',
  `diskGBperJob` int(11) DEFAULT '0',
  `CPUsPerJob` int(11) DEFAULT '0',
  `extProjectHostList` text,
  `appsRequested` text,
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`projectID`),
  UNIQUE KEY `name` (`projectName`)
) ENGINE=MyISAM AUTO_INCREMENT=520 DEFAULT CHARSET=latin1;



# Dump of table Queues
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Queues`;

CREATE TABLE `Queues` (
  `queueID` int(11) NOT NULL AUTO_INCREMENT,
  `computeResourceID` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL DEFAULT 'default',
  `comment` varchar(255) DEFAULT NULL,
  `isDefault` int(4) NOT NULL DEFAULT '1',
  `status` enum('UP','DOWN','UNKNOWN') NOT NULL DEFAULT 'UP',
  `assignedCPUNumber` bigint(20) NOT NULL DEFAULT '1',
  `queueLimit` bigint(20) NOT NULL DEFAULT '100',
  `runLimit` bigint(20) NOT NULL DEFAULT '10',
  `wallClockLimit` datetime NOT NULL DEFAULT '1970-01-05 00:00:00',
  `running` int(20) NOT NULL DEFAULT '0',
  `waiting` int(20) NOT NULL DEFAULT '0',
  `other` int(20) NOT NULL DEFAULT '0',
  `cpuTimeLimit` datetime NOT NULL DEFAULT '1970-01-05 00:00:00',
  `maxCPUs` int(20) NOT NULL DEFAULT '1',
  `maxNodes` int(20) NOT NULL DEFAULT '1',
  `lastUpdated` datetime NOT NULL DEFAULT '2007-05-01 12:10:00',
  `memLimitPerCPU` bigint(20) NOT NULL DEFAULT '2000',
  `valid` tinyint(1) NOT NULL DEFAULT '1',
  `canShareNode` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`queueID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;



# Dump of table Resources
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Resources`;

CREATE TABLE `Resources` (
  `resourceID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `comment` text NOT NULL,
  `type` enum('COMPUTE','STORAGE','NETWORK','VISUALIZATION','OTHER','CONDOR','CLOUD') DEFAULT 'COMPUTE',
  `IPAddress` varchar(255) NOT NULL DEFAULT 'not applicable',
  `created` datetime DEFAULT '2006-01-19 11:53:00',
  `lastDownTime` datetime DEFAULT '2006-01-19 11:53:00',
  `lastUpdated` datetime DEFAULT '2006-01-19 11:53:00',
  `status` enum('UP','DOWN','ONLINE','OFFLINE','UNKNOWN') DEFAULT 'UNKNOWN',
  `hostname` varchar(255) NOT NULL DEFAULT 'not applicable',
  `valid` int(1) NOT NULL DEFAULT '1',
  `siteID` int(11) NOT NULL,
  PRIMARY KEY (`resourceID`)
) ENGINE=MyISAM AUTO_INCREMENT=393 DEFAULT CHARSET=latin1;



# Dump of table Sessions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Sessions`;

CREATE TABLE `Sessions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `destroyed` datetime DEFAULT NULL,
  `expires` datetime NOT NULL,
  `created` datetime NOT NULL,
  `projectId` int(11) DEFAULT NULL,
  `macAddress` varchar(255) DEFAULT '',
  `userId` int(11) NOT NULL,
  `proxy` text NOT NULL,
  `type` enum('COMMUNITY','EXTERNAL','TERAGRID') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=814 DEFAULT CHARSET=utf8;



# Dump of table Sites
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Sites`;

CREATE TABLE `Sites` (
  `siteID` int(11) NOT NULL AUTO_INCREMENT,
  `acronym` varchar(10) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `description` text,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`siteID`)
) ENGINE=MyISAM AUTO_INCREMENT=184 DEFAULT CHARSET=latin1;



# Dump of table Software
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Software`;

CREATE TABLE `Software` (
  `softwareResourceID` int(11) NOT NULL AUTO_INCREMENT,
  `moduleID` int(11) NOT NULL DEFAULT '0',
  `authorName` varchar(255) NOT NULL DEFAULT 'set_me',
  `authorEmail` varchar(255) NOT NULL DEFAULT 'set_me',
  `authorStreet` varchar(255) NOT NULL DEFAULT 'set_me',
  `authorCity` varchar(255) DEFAULT NULL,
  `authorState` varchar(100) NOT NULL DEFAULT 'set_me',
  `authorZipCode` varchar(10) NOT NULL DEFAULT 'set_me',
  `acronym` varchar(20) NOT NULL DEFAULT 'set_me',
  `versionRelease` varchar(255) NOT NULL DEFAULT 'set_me',
  `versionDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `shortDescription` varchar(80) DEFAULT '',
  `longDescription` varchar(255) DEFAULT '',
  `name` varchar(255) NOT NULL DEFAULT 'chemistry_app',
  `helpWebSite` varchar(255) DEFAULT 'not available',
  `inputFileExtension` varchar(8) NOT NULL DEFAULT '.inp',
  `comment` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  `lastUpdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`softwareResourceID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;



# Dump of table SoftwareInputFiles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SoftwareInputFiles`;

CREATE TABLE `SoftwareInputFiles` (
  `inputFileID` int(11) NOT NULL AUTO_INCREMENT,
  `gridchemjobID` int(11) unsigned NOT NULL DEFAULT '0',
  `softwareResourceID` int(11) NOT NULL DEFAULT '0',
  `inputfiledescription` enum('molecularStructure','velocity','topology','forceFieldParams','constraints','trajectory','runParameters') NOT NULL DEFAULT 'molecularStructure',
  `suffix` varchar(255) NOT NULL DEFAULT '',
  `pathname` text NOT NULL,
  `lastUpdated` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`inputFileID`,`softwareResourceID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SoftwareInstallation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SoftwareInstallation`;

CREATE TABLE `SoftwareInstallation` (
  `softwareResourceID` int(11) NOT NULL DEFAULT '11',
  `computeResourceID` int(11) NOT NULL DEFAULT '100',
  `compileDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `compilerName` varchar(255) NOT NULL DEFAULT 'set_me',
  `compilerVersion` varchar(255) NOT NULL DEFAULT '-1',
  `homeDir` varchar(255) NOT NULL DEFAULT 'set_me',
  `module` varchar(255) NOT NULL DEFAULT 'set_me',
  `softenv` varchar(255) NOT NULL DEFAULT '',
  `executablePath` varchar(255) NOT NULL DEFAULT 'set_me',
  `scriptPath` varchar(255) NOT NULL DEFAULT 'set_me',
  `arguments` varchar(255) NOT NULL DEFAULT 'set_me',
  `usage` varchar(255) NOT NULL DEFAULT 'set_me',
  `licenseType` varchar(80) NOT NULL DEFAULT 'set_me',
  `totalLicenses` int(11) NOT NULL DEFAULT '0',
  `licenseInfo` varchar(255) NOT NULL DEFAULT '-1',
  `licenseServer` varchar(255) NOT NULL DEFAULT 'set_me',
  `startupEnvironment` varchar(255) NOT NULL DEFAULT 'set_me',
  `argComments` varchar(255) NOT NULL DEFAULT 'set_me',
  `name` varchar(255) NOT NULL DEFAULT 'chemistry_app',
  `lastUpdated` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `valid` int(1) NOT NULL DEFAULT '1',
  KEY `computeResourceID` (`computeResourceID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table SoftwareModules
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SoftwareModules`;

CREATE TABLE `SoftwareModules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `softwareID` int(11) NOT NULL DEFAULT '0',
  `name` varchar(128) NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;



# Dump of table StorageResources
# ------------------------------------------------------------

DROP TABLE IF EXISTS `StorageResources`;

CREATE TABLE `StorageResources` (
  `storageResourceID` int(11) NOT NULL AUTO_INCREMENT,
  `diskTotalSpace` bigint(20) DEFAULT '0',
  `diskFreeSpace` bigint(20) DEFAULT '0',
  `diskSeekTime` bigint(20) DEFAULT '5',
  `diskRpm` bigint(20) DEFAULT '5',
  `access` enum('GSIFTP','FTP','HTTP','SFTP') NOT NULL DEFAULT 'GSIFTP',
  `quota` bigint(255) DEFAULT NULL,
  `purgeData` tinyint(1) NOT NULL DEFAULT '0',
  `backup` tinyint(1) NOT NULL DEFAULT '0',
  `webSite` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`storageResourceID`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=latin1;



# Dump of table UserProject
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserProject`;

CREATE TABLE `UserProject` (
  `userID` int(11) NOT NULL DEFAULT '0',
  `projectID` int(11) NOT NULL DEFAULT '0',
  `userType` enum('PI','ADMIN','USER') NOT NULL DEFAULT 'USER',
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUsUsedUser` double NOT NULL DEFAULT '0',
  `SUsLimitUser` double NOT NULL DEFAULT '0',
  `SUsBalanceUser` double NOT NULL DEFAULT '0',
  `mss` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=756 DEFAULT CHARSET=latin1;



# Dump of table UserProjectResource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserProjectResource`;

CREATE TABLE `UserProjectResource` (
  `userID` int(11) NOT NULL DEFAULT '0',
  `projectID` int(11) NOT NULL DEFAULT '0',
  `resourceID` int(11) NOT NULL DEFAULT '0',
  `loginName` varchar(255) NOT NULL DEFAULT '',
  `allocationName` varchar(255) NOT NULL DEFAULT '',
  `localUserType` enum('PI','ADMIN','USER') NOT NULL DEFAULT 'USER',
  `SUsLocalUserAwarded` double NOT NULL DEFAULT '0',
  `SUsLocalUserUsed` double NOT NULL DEFAULT '0',
  `SUsLocalUserBalance` double NOT NULL DEFAULT '0',
  `banned` tinyint(1) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userID`,`projectID`,`resourceID`,`allocationName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table Users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(80) NOT NULL DEFAULT '',
  `lastName` varchar(80) NOT NULL DEFAULT '',
  `middleInitial` varchar(5) DEFAULT '',
  `userName` varchar(80) NOT NULL DEFAULT '',
  `initialPassword` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `privilege` enum('PI','ADMIN','USER') NOT NULL DEFAULT 'USER',
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `createdByUserID` int(11) NOT NULL DEFAULT '0',
  `active` int(11) NOT NULL DEFAULT '0',
  `researchStatus` enum('FACULTY','GRADUATE','UNDERGRADUATE','POSTDOCTORATE','UNIVERSITY_RESEARCH_STAFF','UNIVERSITY_NON_RESEARCH_STAFF','OTHER') NOT NULL DEFAULT 'FACULTY',
  `institute` varchar(255) NOT NULL DEFAULT '',
  `department` varchar(255) NOT NULL DEFAULT '',
  `addr1` varchar(80) NOT NULL DEFAULT '',
  `addr2` varchar(80) DEFAULT '',
  `city` varchar(255) NOT NULL DEFAULT '',
  `state` varchar(100) NOT NULL DEFAULT '',
  `zipCode` varchar(10) NOT NULL DEFAULT '',
  `country` varchar(80) NOT NULL DEFAULT '',
  `phone` varchar(40) NOT NULL DEFAULT '',
  `cell` varchar(14) DEFAULT 'none provided',
  `imHandle` varchar(50) DEFAULT 'none provided',
  `fax` varchar(40) DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `comment` text,
  `dataVersion` varchar(11) NOT NULL DEFAULT '0.2.0',
  `lastUpdated` datetime NOT NULL DEFAULT '2006-04-01 00:00:00',
  `resetKey` varchar(21) NOT NULL DEFAULT '',
  `enabled` smallint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `uname` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=latin1;



# Dump of table VisualizationResources
# ------------------------------------------------------------

DROP TABLE IF EXISTS `VisualizationResources`;

CREATE TABLE `VisualizationResources` (
  `visualizationResourceID` int(11) NOT NULL DEFAULT '1',
  `numNodes` bigint(20) NOT NULL DEFAULT '1',
  `numProcessors` bigint(20) NOT NULL DEFAULT '1',
  `peakPerformance` float DEFAULT NULL,
  `memory` bigint(20) DEFAULT NULL,
  `scratchDisk` varchar(255) DEFAULT '/u/ac/',
  `peakPolygons` bigint(20) DEFAULT NULL,
  `graphicsHw` varchar(255) DEFAULT 'unknown'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table Workflows
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Workflows`;

CREATE TABLE `Workflows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `stopTime` datetime DEFAULT NULL,
  `status` enum('SUBMITTING','SCHEDULED','HOLD','WAITING','INITIAL','STARTING','RUNNING','PREEMPTED','CHECKPOINTING','MIGRATING','RESUMING','EXITING','SUSPENDED','STOPPED','CANCELLING','FAILED','FINISHED','REMOVED','UNKNOWN','SUBMISSION_ERROR','RUNTIME_ERROR','TIME_ELAPSED','NOT_IN_QUEUE') NOT NULL DEFAULT 'INITIAL',
  `lastUpdated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `hidden` tinyint(1) NOT NULL DEFAULT '0',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=latin1;






/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
