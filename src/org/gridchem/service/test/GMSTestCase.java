package org.gridchem.service.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.gridchem.service.authentication.ccg.CCGAuthentication;
import org.gridchem.service.beans.Address;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.NotificationDao;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.ProjectResourceDao;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.dao.SoftwareDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.dao.UserProjectResourceDao;
import org.gridchem.service.dao.WorkflowDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.model.BlackListEntry;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.Load;
import org.gridchem.service.model.LogicalFile;
import org.gridchem.service.model.Notification;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.ProjectResource;
import org.gridchem.service.model.Queue;
import org.gridchem.service.model.Site;
import org.gridchem.service.model.Software;
import org.gridchem.service.model.SoftwareInstallation;
import org.gridchem.service.model.StorageResource;
import org.gridchem.service.model.Usage;
import org.gridchem.service.model.User;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.model.UserProjectResource;
import org.gridchem.service.model.Workflow;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.BatchSchedulerType;
import org.gridchem.service.model.enumeration.InstitutionType;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.NotificationType;
import org.gridchem.service.model.enumeration.ProjectStatusType;
import org.gridchem.service.model.enumeration.QueueStatusType;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.model.enumeration.ResourceType;
import org.gridchem.service.model.enumeration.TransferProtocolType;
import org.gridchem.service.model.enumeration.UserClassificationType;
import org.gridchem.service.model.enumeration.UserPermissionType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.crypt.SHA1;

public class GMSTestCase extends TestCase {

	// Constants for all unit tests
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

	protected String TEST_MYPROXY_USERNAME = "rdoole1";
	protected String TEST_MYPROXY_PASSWORD = "dr&mrsd00ley";
	
	// common variables used during unit tests.  
	// These are dynamically injected into the db at startup
	protected User user;
	protected Project project;
	protected UserProject up;
	protected ProjectResource prCompute;
	protected ProjectResource prStorage;
	protected UserProjectResource uprCompute;
	protected UserProjectResource uprStorage;
	protected Software software;
	protected StorageResource storage;
	protected ComputeResource hpc;
	protected Site site;
	protected Queue queue;
	protected SoftwareInstallation si;
	
	protected GMSSession session;
	protected Job job;
	protected Workflow workflow;
	protected Notification notification;
	protected BlackListEntry blackList;
	
	public GMSTestCase(String name) {
		super(name);
	}
	
//	public void testSetUp() {
//		assertNotNull("user is inserted and present",user);
//		assertNotNull("project is inserted and present",project);
//		assertNotNull("software is inserted and present",software);
//		assertNotNull("compute resource is inserted and present",hpc);
//		assertNotNull("compute resource is inserted and present",site);
//		assertNotNull("compute resource is inserted and present",queue);
//		assertNotNull("user project is inserted and present",up);
//		assertNotNull("pr for compute resource is inserted and present",prCompute);
//		assertNotNull("pr for storage is inserted and present",prStorage);
//		assertNotNull("upr for compute resource is inserted and present",uprCompute);
//		assertNotNull("upr for storage is inserted and present",uprStorage);
//		assertNotNull("software is inserted and present",software);
//		assertNotNull("storage resource is inserted and present",storage);
//		assertNotNull("compute resource is inserted and present",hpc);
//		assertNotNull("software install is inserted and present",si);
//		assertNotNull("job is inserted and present",job);
//		assertNotNull("session is inserted and present",session);
//		assertNotNull("notification is inserted and present",notification);
//		assertNotNull("BlackListEntry is inserted and present",blackList);
//		assertNotNull("workflow is inserted and present",workflow);
//	}
	
	public void testTearDown() throws Exception {
		
		HibernateUtil.beginTransaction();
		HibernateUtil.getSession().clear();
		
		UserDao userDao = new UserDao(session);
		ProjectDao projectDao = new ProjectDao(session);
		SoftwareDao softwareDao = new SoftwareDao(session);
		ResourceDao resourceDao = new ResourceDao(session);
		
		
		UserProjectDao.remove(up);
		assertNull(UserProjectDao.get(TEST_USERNAME, project.getId()));
		
		// delete the compute resource associations
		ProjectResourceDao.remove(prCompute);
		assertNull(ProjectResourceDao.get(project.getId(),prCompute.getResource().getName(),prCompute.getAllocationName()));
		
		UserProjectResourceDao.remove(uprCompute);
		assertNull(UserProjectResourceDao.get(TEST_USERNAME, project.getId(),prCompute.getResource().getName(),prCompute.getAllocationName()));
		
		// delete the storage resource associations
		ProjectResourceDao.remove(prStorage);
		assertNull(ProjectResourceDao.get(project.getId(),prStorage.getResource().getName(),prStorage.getAllocationName()));
		
		UserProjectResourceDao.remove(uprStorage);
		assertNull(UserProjectResourceDao.get(TEST_USERNAME, project.getId(),prStorage.getResource().getName(),prStorage.getAllocationName()));
		
		WorkflowDao.delete(workflow);
		assertTrue(WorkflowDao.getByUser(TEST_USERNAME).isEmpty());
		
		new UserDao().delete(user);
		assertNull(userDao._get(TEST_USERNAME));
		
		new ProjectDao()._remove(project);
		assertNull(projectDao._get(TEST_PROJECTNAME));
		
		new SoftwareDao().delete(software);
		assertNull(softwareDao._get(TEST_SOFTWARENAME));
		
		new SoftwareDao().delete(si);
		assertNull(softwareDao._getInstallation(software.getName(), hpc.getName()));
		

		// delete the test resources
		resourceDao.delete(hpc);
//		resourceDao.delete(queue);
		resourceDao.delete(site);
		
		assertNull(resourceDao._getCompute(TEST_SYSTEMNAME));
		
		resourceDao.delete(storage);
		assertNull(resourceDao._getStorage(TEST_STORAGENAME));
		
		SessionDao.delete(session);
		assertNull(SessionDao.getByToken(TEST_SESSIONTOKEN));
		try {
			JobDao.delete(job);
			assertNull(JobDao.getByName(TEST_JOBNAME));
		} catch (JobException e) {}
	}

	protected void setUp() throws Exception {
		UserDao userDao;
		ProjectDao projectDao;
		SoftwareDao softwareDao;
		ResourceDao resourceDao;
		

		userDao = new UserDao();
		user = userDao._get(TEST_USERNAME);
		if (user == null) {
			user = createUser();
			userDao._add(user);
		}
		
		projectDao = new ProjectDao();
		project = projectDao._get(TEST_PROJECTNAME);
		if (project == null) {
			project = createProject();
			projectDao._add(project);
		}
		
		session = SessionDao.getByToken(TEST_SESSIONTOKEN);
		if (session == null) {
			session = createSession(user,project,true);
			SessionDao.persist(session);
		} else {
			GMSSession newSession = createSession(user,project,false);
			if (!session.equals(newSession)) {
				session.setProjectId(project.getId());
				session.setUserId(user.getId());
				session.setType(AccessType.COMMUNITY);
				session.setProxy(newSession.getProxy());
				SessionDao.persist(session);
			}
		}
		
		userDao = new UserDao(session);
		projectDao = new ProjectDao(session);
		softwareDao = new SoftwareDao(session);
		resourceDao = new ResourceDao(session);
			
		software = softwareDao._get(TEST_SOFTWARENAME);
		if (software == null) {
			software = createSoftware();
			softwareDao.persist(software);
		}
		
		hpc = resourceDao._getCompute(TEST_SYSTEMNAME);
		if (hpc == null) {
			
			site = createSite();
			if (true);
			resourceDao.persist(site);
			
			
			
			hpc = createSystem(site);
			hpc.setQueues(Collections.singleton(createQueue(hpc)));
			resourceDao.persist(hpc);
			queue = hpc.getQueues().iterator().next();
		} else {
			if (hpc.getQueues() == null || hpc.getQueues().isEmpty()) {
				queue = createQueue(hpc);
				
				hpc.setQueues(Collections.singleton(queue));
				resourceDao.persist(hpc);
//				hpc.setQueues(Collections.singleton(queue));
//				resourceDao.persist(hpc);
			} else {
				queue = hpc.getQueues().iterator().next();
			}
			
			if (hpc.getSite() == null) {
				site = createSite();
				resourceDao.persist(site);
				
				hpc.setSite(site);
				resourceDao.persist(hpc);
			} else {
				site = hpc.getSite();
			}
		
		}
		
		storage = resourceDao._getStorage(TEST_STORAGENAME);
		if (storage == null) {
			storage = createStorage(site);
			resourceDao.persist(storage);
		}
		
		si = softwareDao._getInstallation(software.getName(), hpc.getName());
		if (si == null) {
			si = createSoftwareInstallation(software,hpc);
			softwareDao.persist(si);
		}
		
		up = UserProjectDao.get(TEST_USERNAME, project.getId());
		if (up == null) {
			up = new UserProject(user,project);
			up.setUserType(UserPermissionType.USER);
			up.setUsage(project.getUsage());
			up.setMss(storage);
		
			UserProjectDao.add(up);
		}
		
		prCompute = ProjectResourceDao.get(project.getId(), hpc.getName(), TEST_ALLOCATIONNAME);
		if (prCompute == null) {
			// assign the resources with the project
			prCompute = new ProjectResource(project, hpc, 
					TEST_ALLOCATIONNAME, true, new Usage());
			ProjectResourceDao.add(prCompute);
		}
		
		uprCompute = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), hpc.getName(), TEST_ALLOCATIONNAME);
		if (uprCompute == null) {
			// now associate the resources with the users
			uprCompute = new UserProjectResource(hpc, up, 
					TEST_LOGINNAME, UserPermissionType.USER, 
					TEST_ALLOCATIONNAME, new Usage());
			UserProjectResourceDao.add(uprCompute);
		}
		
		prStorage = ProjectResourceDao.get(project.getId(), storage.getName(), TEST_ALLOCATIONNAME);
		if (prStorage == null) {
			prStorage = new ProjectResource(project, storage, 
					TEST_ALLOCATIONNAME, true, new Usage());
			ProjectResourceDao.add(prStorage);
		}
		
		uprStorage = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), storage.getName(), TEST_ALLOCATIONNAME);
		if (uprStorage == null) {
			uprStorage = new UserProjectResource(storage, up, 
					TEST_LOGINNAME, UserPermissionType.USER, 
					TEST_ALLOCATIONNAME, new Usage());
			UserProjectResourceDao.add(uprStorage);
		}
			
		List<Workflow> wResults = WorkflowDao.getByUser(TEST_USERNAME);
		if (wResults == null || wResults.isEmpty()) {
			workflow = createWorkflow(user);
			WorkflowDao.persist(workflow);
		} else {
			workflow = wResults.get(0);
		}
		
		job = JobDao.getByName(TEST_JOBNAME);
		if (job == null) {
			job = createJob(user,project,software,hpc,queue,storage,workflow);
			JobDao.persist(job);
		} else {
			if (job.getQueue() == null) {
				job.setQueue(queue);
				JobDao.persist(job);
			}
		}
			
		blackList = createBlackListEntry(user,software);
		
		List<Notification> nResults = NotificationDao.getAllByJobID(job.getId());
		if (nResults == null || nResults.isEmpty()) {
			notification = createNotification(job);
			NotificationDao.add(notification);
		} else {
			notification = nResults.get(0);
		}
			
//		} else {
//			
//			userDao = new UserDao(session);
//			projectDao = new ProjectDao(session);
//			softwareDao = new SoftwareDao(session);
//			resourceDao = new ResourceDao(session);
//			
//			user = userDao._get(TEST_USERNAME);
//			project = projectDao._get(TEST_PROJECTNAME);
//			software = softwareDao._get(TEST_SOFTWARENAME);
//			hpc = resourceDao._getCompute(TEST_SYSTEMNAME);
//			queue = hpc.getQueues().iterator().next();
//			site = hpc.getSite();
//			storage = resourceDao._getStorage(TEST_STORAGENAME);
//			
//			si = softwareDao._getInstallation(software.getName(), hpc.getName());
//		
//			up = UserProjectDao.get(TEST_USERNAME, project.getId());
//			prCompute = ProjectResourceDao.get(project.getId(), hpc.getName(), TEST_ALLOCATIONNAME);
//			uprCompute = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), hpc.getName(), TEST_ALLOCATIONNAME);
//			prStorage = ProjectResourceDao.get(project.getId(), storage.getName(), TEST_ALLOCATIONNAME);
//			uprStorage = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), storage.getName(), TEST_ALLOCATIONNAME);
//			
//			job = JobDao.getByName(TEST_JOBNAME);
//			
//			workflow = WorkflowDao.getByUser(TEST_USERNAME).get(0);
//			
//			notification = NotificationDao.getAllByJobID(job.getId()).get(0);
//			
//			blackList = createBlackListEntry(user,software);
//			
//		}
		
		HibernateUtil.beginTransaction();
		HibernateUtil.getSession().flush();
		
		
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
	
	public GMSSession createSession(User user, Project project, boolean loadProxy) {
		GMSSession session = new GMSSession();
		session.setType(AccessType.COMMUNITY);
		session.setProjectId(project.getId());
		session.setUserId(user.getId());
		session.setToken(TEST_SESSIONTOKEN);
		if (loadProxy) {
			CCGAuthentication auth = new CCGAuthentication(TEST_USERNAME);
			//session.setProxy(ServiceUtil.serializeGlobusCredential(auth.getCredential()));
			session.setProxy(ServiceUtil.serializeX509Credential(auth.getCredential()));
		} else {
			session.setProxy("junit.session.proxy");
		}
		return session;
	}
	
	public Software createSoftware() {
		Software software = new Software();
		software.setName(TEST_SOFTWARENAME);
		software.setAcronym("jsw");
		software.setAuthorEmail("junit.software.author.email");
		software.setAuthorName("junit.software.author.name");
		software.setComment("junit.software.comment");
		software.setHelpWebsite("junit.software.help.website");
		software.setInputFileExtension("jsie");
		software.setLongDescription("junit.software.long.description");
		software.setReleaseVersion("junit.software.release.version");
		software.setShortDescription("junit.software.short.description");
		software.setVersionDate(new Date());
		software.setVersionRelease("junit.software.version.release");
		
		return software;
	}
	
	public StorageResource createStorage(Site site) {
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
		storage.setSite(site);
		storage.setStatus(ResourceStatusType.UP);
		storage.setTotal(10000);
		storage.setType(ResourceType.STORAGE);
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
	
	public ComputeResource createSystem(Site site) {
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
		system.setReFlag("junit.system.pflag");
		system.setPflag("junit.system.pflag");
		system.setSite(site);
		system.setScheduler(BatchSchedulerType.LSF);
		system.setScpFlag("junit.system.scp.flag");
		system.setScratchDirectory("junit.system.scratch.dir");
		system.setScratchDisk("junit.system.scratch.disc");
		system.setSiteAcronym(site.getAcronym());
		system.setStatus(ResourceStatusType.UP);
		system.setSystem("junit.system.system.name");
		system.setTotalCpu(2048);
		system.setTotalDisk(1024);
		system.setTotalMemory(1024);
		system.setTotalNodes(512);
		system.setType(ResourceType.COMPUTE);
		system.setWebSite("http://junit.system.website");
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
	
	public Queue createQueue(ComputeResource system) {
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
		q.setCompute(system);
		
		return q;
	}
	
	public Notification createNotification(Job job) {
		Notification n = new Notification();
		n.setJob(job);
		n.setType(NotificationType.EMAIL);
		n.setStatus(JobStatusType.FINISHED);
		return n;
	}
	
	public Workflow createWorkflow(User user) {
		Workflow workflow = new Workflow();
		workflow.setName(TEST_WORKFLOWNAME);
		workflow.setDescription("empty");
		workflow.setCreated(new Date());
		workflow.setStatus(JobStatusType.INITIAL);
		workflow.setInput("empty");
		workflow.setUser(user);
		
		return workflow;
	}
	
	public Job createJob(User user, Project project, 
			Software software, ComputeResource system, Queue q,
			StorageResource storage, Workflow workflow) {
		
		Job job = new Job();
		job.setName(TEST_JOBNAME);
		job.setExperimentName(TEST_JOBNAME);
		job.setRequestedCpus(new Long(4));
		job.setMaxResubmissions(1);
		job.setRequestedMemory(new Long(256));
		job.setResubmittable(true);
		job.setCreated(new Date());
		job.setLastUpdated(job.getCreated());	
		job.setStatus(JobStatusType.SCHEDULED);
		job.setLocalId(TEST_LOCALJOBID);
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(12*60*60*1000);
		job.setRequestedCpuTime(cal);
		
		job.setUser(user);
		job.setProject(project);
		
		job.setAllocationName(TEST_ALLOCATIONNAME);
		
		job.setSoftware(software);
		
		job.setSystem(system);
		
		job.setQueue(q);
			
		job.setStorage(storage);
		
		job.setWorkflow(workflow);
		
		List<LogicalFile> inputBeans = new ArrayList<LogicalFile>();
		job.setInputFiles(inputBeans);
		
		return job;
		
	}
	
	public BlackListEntry createBlackListEntry(User user, Software software) {
		BlackListEntry blackList = new BlackListEntry();
		blackList.setUser(new UserDao()._get(TEST_USERNAME));
		blackList.setSoftware(new SoftwareDao()._get(TEST_SOFTWARENAME));
		blackList.setId(new BlackListEntry.Id(user.getId(), software.getId()));
		blackList.setCreated(new Date());
		blackList.setEnabled(true);
		blackList.setLastUpdated(new Date());
		return blackList;
	}
	
}
