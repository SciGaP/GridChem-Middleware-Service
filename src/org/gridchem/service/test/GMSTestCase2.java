/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Feb 6, 2006
 * 
 * Developed by: CCT, Center for Computation and Technology, 
 *              NCSA, University of Illinois at Urbana-Champaign
 *              OSC, Ohio Supercomputing Center
 *              TACC, Texas Advanced Computing Center
 *              UKy, University of Kentucky
 * 
 * https://www.gridchem.org/
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal with the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom 
 * the Software is furnished to do so, subject to the following conditions:
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimers.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimers in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the names of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the names of its contributors 
 *    may be used to endorse or promote products derived from this Software without 
 *    specific prior written permission.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS WITH THE SOFTWARE.
*/

package org.gridchem.service.test;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import junit.framework.TestCase;

import org.gridchem.service.beans.Address;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Load;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.Queue;
import org.gridchem.service.model.Site;
import org.gridchem.service.model.Software;
import org.gridchem.service.model.SoftwareInstallation;
import org.gridchem.service.model.StorageResource;
import org.gridchem.service.model.Usage;
import org.gridchem.service.model.User;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.InstitutionType;
import org.gridchem.service.model.enumeration.ProjectStatusType;
import org.gridchem.service.model.enumeration.QueueStatusType;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.model.enumeration.TransferProtocolType;
import org.gridchem.service.model.enumeration.UserClassificationType;
import org.gridchem.service.model.enumeration.UserPermissionType;
import org.gridchem.service.util.crypt.SHA1;

/**
 * No actual test, but only test data initialization.  Sensitive parameters are
 * read from the properties file included in the checkout (GMS_HOME/gms.properties).
 *
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 */
public abstract class GMSTestCase2 extends TestCase {
	
	protected String TEST_SESSIONTOKEN = "junit.session.token";
	protected String TEST_USERNAME = "junit.username";
	protected String TEST_PROJECTNAME = "junit.project.name";
	protected double TEST_PROJECTALLOCATION = 125000;
	protected String TEST_ALLOCATIONNAME = "junit.allocation.name";
	protected String TEST_LOGINNAME = "junit.login.name";
	protected String TEST_PASSWORD = "default";
	protected String TEST_SYSTEMNAME = "junit.system.name";
	protected String TEST_QUEUENAME = "junit.queue.name";
	protected String TEST_SITENAME = "junit.site.name";
	protected String TEST_STORAGENAME = "junit.storage.name";
	protected String TEST_SOFTWARENAME = "junit.software.name";
	protected String TEST_JOBNAME = "junit.job.name";
	protected String TEST_LOCALJOBID = "junit.local.job.id";
	protected String TEST_WORKFLOWNAME = "junit.workflow.name";

	// Keep references to domain objects
//	protected static ComputeResource system;
//	
//	protected static SoftwareInstallation installation;
//	
//	protected static SoftwareResource software;
	
//	protected User user;
//	
//	protected static Job job;
//	
	protected static Properties props = new Properties();
    
	protected String username;
	protected String password;
	
	protected String projectName;
	
	protected String allocationName;
	
	protected Long userId;
	protected Long projectId;
	protected String accessType;
	protected String myproxyHostname;
	protected String myproxyUsername;
	protected String myproxyPassword;
	
	protected String softwareName;
	protected String softwareSystemName;
	
	protected String blackListUsername;
	protected String blackListSoftware;
	
	protected String systemName;
	
	protected String storageName;
	
	protected String jobUserName;
	protected String jobSystemName;
	protected String jobSoftwareName;
	protected String jobStorageName;
	protected String jobProjectName;
	
	protected String authTeraGridUsername;
	protected String authTeraGridPassword;
	
	protected GMSSession session;
	
	// ********************************************************** //
	
	/**
	 * Create test data for our domain model.
	 *
	 * @throws Exception
	 */
	protected void initData() throws Exception {
       
	}
    
    protected void setUp() throws Exception {
        
        props.load(new FileInputStream("test.properties"));
        
        // ccg user auth parameters
        username = props.getProperty("test.auth.ccg.username");
        
        password = props.getProperty("test.auth.ccg.password");
        
        // external user auth parameters
//        extUsername = props.getProperty("test.auth.external.username");
//        
//        extPassword = props.getProperty("test.auth.external.password");
//        
        myproxyHostname = props.getProperty("test.auth.external.myproxy.host");
        
        myproxyUsername = props.getProperty("test.auth.external.myproxy.username");
        
        myproxyPassword = props.getProperty("test.auth.external.myproxy.password");
        
        // teragrid user auth parameters
//        tgUsername = props.getProperty("test.auth.teragrid.username");
//        
//        tgPassword = props.getProperty("test.auth.teragrid.password");
//        
        // ccg project params
        projectName = props.getProperty("test.project.name");
        
        allocationName = props.getProperty("test.allocation.name");
        
//        extProjectName = props.getProperty("test.auth.external.username");
        
        softwareName = props.getProperty("test.software.name");
        softwareSystemName = props.getProperty("test.software.system.name");
        
        blackListUsername = props.getProperty("test.blacklist.username");
        blackListSoftware = props.getProperty("test.blacklist.software");
        
        // tg project params
        
        // resource params
        systemName = props.getProperty("test.resource.system.name");
        
        storageName = props.getProperty("test.resource.storage.name");
        
        // user job test default parameters
        jobUserName = props.getProperty("test.job.user.username");
        jobSystemName = props.getProperty("test.job.system.name");
        jobSoftwareName = props.getProperty("test.job.software.name");
        jobStorageName = props.getProperty("test.job.storage.name");
        jobProjectName = props.getProperty("test.job.project.name");
        
        authTeraGridUsername = props.getProperty("test.auth.teragrid.username");
    	authTeraGridPassword = props.getProperty("test.auth.teragrid.password");
         
    }

	// ********************************************************** //

	public GMSTestCase2(String x) {
        super(x);
	}
	
	/**
	 * Create dummy user
	 * 
	 * @return
	 */
	public User createUser() {
		User user = new User();
		
		user.setUsername(TEST_USERNAME);
		user.setCell("555-555-5555");
		user.setPhone("555-555-5555");
		user.setFax("555-555-5555");
		user.setEmail("test@example.com");
		user.setAppsRequested("none");
		user.setClassification(UserClassificationType.GRADUATE);
		user.setDepartment("Chemistry");
		user.setInstitution("The Job Factory");
		user.setIm("junit.test.handle");
		user.setFirstName("Test");
		user.setMiddleInitial("C");
		user.setLastName("User");
		user.setOriginalPassword(SHA1.encrypt(TEST_PASSWORD));
		user.setPassword(SHA1.encrypt(TEST_PASSWORD));
		user.setPermission(UserPermissionType.USER);
		user.setDiskGBPerJob(1);
		user.setProcessorsPerJob(64);
		user.setMemoryMBPerCPU(1);
		user.setSusPerJob(256);
		user.setComment("This is a test user entry.Delete asap.");
		
		Address address = new Address("junit.street1","junit.street2","junit.city","junit.state","12345","junit.country");
		user.setAddress(address);
		
		return user;
	}
	
	/**
	 * Create dummy project
	 * 
	 * @return
	 */
	public Project createProject() {
		Project project = new Project();
		project.setAppsRequested("none");
		project.setComment("This is a test project. Delete after running tests.");
		project.setCpusPerJob(64);
		project.setCreated(new Date());
		project.setDescription("Test project for junit tests");
		project.setDiskGBPerJob(1);
		// project expires a day from now.
		project.setEndDate(new Date(System.currentTimeMillis() + 1000*60*60*24));
		project.setExtProjectHostList("none");
		project.setFundedProjectId("unfunded");
		project.setFundingOrganization("none");
		project.setInstitution("The Job Factory");
		project.setInstitutionType(InstitutionType.UNIVERSITY);
		project.setMemoryMBPerCPU(1);
		project.setName(TEST_PROJECTNAME);
		project.setPi("Dr. Science");
		project.setStartDate(new Date());
		project.setStatus(ProjectStatusType.ACTIVE);
		project.setSusPerJob(64);
		project.setTheDefault(true);
		project.setType(AccessType.COMMUNITY);
		
		// new projects get a 10k allocation by default.
		project.setUsage(new Usage(10000, 0));
		
		return project;
	}
	
	public Software createSoftware() {
		Software software = new Software();
		software.setName(TEST_SOFTWARENAME);
		software.setAcronym("jsw");
		software.setAuthorEmail("junit.software.author.email");
		software.setAuthorName("junit.software.author.name");
		software.setComment("junit.software.comment");
		software.setHelpWebsite("junit.software.help.website");
		software.setInputFileExtension("junit.software.input.extension");
		software.setLongDescription("junit.software.long.description");
		software.setReleaseVersion("junit.software.release.version");
		software.setShortDescription("junit.software.short.description");
		software.setVersionDate(new Date());
		software.setVersionRelease("junit.software.version.release");
		
		return software;
	}
	
	public StorageResource createStorage() {
		StorageResource storage = new StorageResource();
		storage.setBackup(true);
		storage.setComment("junit.storage.comment");
		storage.setCreated(new Date());
		storage.setHostname("localhost");
		storage.setIpAddress("127.0.0.1");
		storage.setFree(1000);
		storage.setLastDownTime(new Date());
		storage.setLastUpdated(new Date());
		storage.setName(TEST_STORAGENAME);
		storage.setProtocol(TransferProtocolType.GSIFTP);
		storage.setPurge(true);
		storage.setQuota(100);
		storage.setRpm(5);
		storage.setSeekTime(1);
		storage.setSite(createSite());
		storage.setStatus(ResourceStatusType.UP);
		storage.setTotal(10000);
		storage.setWebsite("http://junit.storage.site");
		
		return storage;
	}
	
	public Site createSite() {
		Site site = new Site();
		site.setAcronym("JTS");
		site.setCreated(new Date());
		site.setDescription("junit.site.description");
		site.setLastUpdated(new Date());
		site.setName(TEST_SITENAME);
		
		return site;
	}
	
	public ComputeResource createSystem() {
		ComputeResource system = new ComputeResource();
		system.setAdminEmail("test@junit.system.email");
		system.setAvailableCpu(1024);
		system.setAvailableDisk(10000);
		system.setComment("junit.system.comment");
		system.setCreated(new Date());
		system.setHistProgramPath("junit.system.program.path");
		system.setHostname("localhost");
		system.setIpAddress("127.0.0.1");
		system.setJobsProgramPath("junit.system.program.path");
		system.setKillProgramPath("junit.system.kill.path");
		system.setLastDownTime(new Date());
		system.setLastUpdated(new Date());
		system.setLindaIsAvailable(false);
		system.setLoad(new Load(100,200,10000,100,10,10,10));
		system.setName(TEST_SYSTEMNAME);
		system.setPeakPerformance(2048);
		system.setPflag("junit.system.pflag");
		system.setQueues(Collections.singleton(createQueue()));
		system.setReFlag("junit.system.pflag");
		system.setPflag("junit.system.pflag");
		
		return system;
	}
	
	public SoftwareInstallation createSoftwareInstallation(Software software, ComputeResource system) {
		SoftwareInstallation si = new SoftwareInstallation(software, system);
		si.setArguments("junit.si.arguments");
		si.setCompileDate(new Date());
		si.setCompilerName("junit.si.compiler.name");
		si.setCompilerVersion("junit.si.compiler.version");
		si.setExecutablePath("junit.si.exe.path");
		si.setHomeDir("junit.si.home.dir");
		si.setLastUpdated(new Date());
		si.setLicenseInfo("junit.si.license.info");
		si.setLicenseServer("junit.si.license.server");
		si.setLicenseType("junit.si.license.type");
		si.setModule("junit.si.module");
		si.setScriptPath("junit.si.script.path");
		si.setSoftEnv("junit.si.softenv");
		si.setSoftwareName(software.getName());
		si.setStartupEnvironment("junit.si.startup.env");
		si.setTotalLicenses(Integer.valueOf(1));
		si.setValid(true);
		
		return si;
	}
	
	public Queue createQueue() {
		Queue q = new Queue();
		q.setAssignedCpuNumber(Long.valueOf(50));
		q.setComment("junit.queue.comment");
		q.setLastUpdate(new Date());
		q.setMaxCpuMem(Long.valueOf(100));
		q.setMaxCpus(100);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(3*24*60*60*1000);
		q.setMaxCpuTime(cal);
		q.setMaxQueuedJobs(Long.valueOf(25));
		q.setMaxRunningJobs(Long.valueOf(25));
		q.setMaxWallClockTime(cal);
		q.setName(TEST_QUEUENAME);
		q.setOther(Long.valueOf(0));
		q.setRunning(Long.valueOf(30));
		q.setStatus(QueueStatusType.UP);
		q.setTheDefault(true);
		q.setWaiting(Long.valueOf(40));
		
		return q;
	}
	

}
