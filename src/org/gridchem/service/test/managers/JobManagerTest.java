package org.gridchem.service.test.managers;

import org.gridchem.service.beans.BlackListBean;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.dao.BlackListDao;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.job.JobManager;
import org.gridchem.service.model.BlackListEntry;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.ProjectStatusType;
import org.gridchem.service.test.GMSTestCase;

public class JobManagerTest extends GMSTestCase {
	
//	private Job job;
//	private ProjectDao projectDao;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;

	public JobManagerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
//		
//		UserDao udao = new UserDao();
//		
//		// retrieve the user from the db or create one to use.
//		User user = udao._get(TEST_USERNAME);
//		if (user == null) {
//			user = createUser();
//			udao._add(user);
//		}
//		
//		projectDao = new ProjectDao();
//		Project project = projectDao._get(TEST_PROJECTNAME);
//		if (project == null) {
//			project = createProject();
//			projectDao._add(project);
//		}
//		
//		// persist the session
//		session = SessionDao.getByToken(TEST_SESSIONTOKEN);
//		
//		if (session == null) {
//			session = new GMSSession();
//			session.setToken(TEST_SESSIONTOKEN);
//			session.setType(AccessType.COMMUNITY);
//			CCGAuthentication auth = new CCGAuthentication(TEST_USERNAME);
//			session.setProxy(ServiceUtil.serializeGlobusCredential(auth.getCredential()));
//			session.setProjectId(project.getId());
//			session.setUserId(user.getId());
//			
//			SessionDao.persist(session);
//		}
//		
//		projectDao = new ProjectDao(session);
//		
//		ResourceDao resourceDao = new ResourceDao(session);
//		
//		ComputeResource hpc = new ResourceDao(session)._getCompute(TEST_SYSTEMNAME);
//		
//		up = UserProjectDao.get(TEST_USERNAME, project.getId());
//		
//		if (up == null) {
//			
//			up = new UserProject(user,project);
//			up.setUserType(UserPermissionType.USER);
//			up.setUsage(project.getUsage());
//			up.setMss(new ResourceDao(session)._getStorage(TEST_STORAGENAME));
//		
//			UserProjectDao.add(up);
//			
//			
//			pr = new ProjectResource(project, hpc, 
//					TEST_ALLOCATIONNAME, true, new Usage());
//			ProjectResourceDao.add(pr);
//			
//			upr = new UserProjectResource(hpc, up, 
//					TEST_LOGINNAME, UserPermissionType.USER, 
//					TEST_ALLOCATIONNAME, new Usage());
//			UserProjectResourceDao.add(upr);
//		} else {
//			pr = ProjectResourceDao.get(project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			upr = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//		}
//		
//		job = JobDao.getByName(TEST_JOBNAME);
//		
//		if (job == null) {
//			job = new Job();
//			job.setName(TEST_JOBNAME);
//			job.setExperimentName(TEST_JOBNAME);
//			job.setRequestedCpus(new Long(4));
//			job.setMaxResubmissions(1);
//			job.setRequestedMemory(new Long(256));
//			job.setResubmittable(true);
//			job.setCreated(new Date());
//			job.setLastUpdated(job.getCreated());	
//			job.setStatus(JobStatusType.SCHEDULED);
//			job.setLocalId(TEST_LOCALJOBID);
//			
//			Calendar cal = Calendar.getInstance();
//			cal.setTimeInMillis(12*60*60*1000);
//			job.setRequestedCpuTime(cal);
//			
//			job.setUser(user);
//			job.setProject(project);
//			
//			String allocation = projectDao.getDefaultAllocation(TEST_SYSTEMNAME);
//			job.setAllocationName(allocation);
//			
//			Software software = new SoftwareDao(session)._get(TEST_SOFTWARENAME);
//			job.setSoftware(software);
//			
//			job.setSystem(hpc);
//			
//			Queue queue = hpc.getDefaultQueue();
//			job.setQueue(queue);
//				
//			StorageResource storage = resourceDao._getStorageResource(TEST_STORAGENAME);
//			job.setStorage(storage);
//			
//			Set<FileBean> inputBeans = new HashSet<FileBean>();
//			job.setInputFiles(inputBeans);
////			
//			JobDao.persist(job);
//		}
		
	}
	

	/*************************************************
	 * 
	 * 		Test Get Jobs Limited Results
	 * 
	 *************************************************/
	
	public void testGetJobsSessionNull() {
		try {
			JobManager.getJobs(null);
			fail("Null session should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testGetJobs() {
		assertFalse(JobManager.getJobs(session).isEmpty());
	}
	
	/*************************************************
	 * 
	 * 		Test Get All Jobs
	 * 
	 *************************************************/
	
	public void testGetAllJobsSessionNull() {
		try {
			JobManager.getAllJobs(null);
			fail("Null session should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testGetAllJobs() {
		assertFalse(JobManager.getAllJobs(session).isEmpty());
	}

	/*************************************************
	 * 
	 * 		Test Update Job Metadata
	 * 
	 *************************************************/
	
	public void testUpdateMetaDataJobBeanNull() {
		try {
			JobManager.updateMetaData(null, "");
			fail("Null JobBean should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testUpdateMetaDataJobBeanInvalid() {
		JobBean bean = new JobBean();
		try {
			JobManager.updateMetaData(bean, "");
			fail("Null JobBean should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testUpdateMetaDataJMetaDataNull() {
		try {
			JobManager.updateMetaData(job.toBean(), null);
			fail("Null JobBean should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testUpdateMetaData() {
		JobManager.updateMetaData(job.toBean(), "junit test data");
	}
	
	/*************************************************
	 * 
	 * 		Test Submit Job
	 * 
	 *************************************************/
	
	public void testSubmitSessionNull() {
		try {
			JobManager.submit(null, job.toBean());
			fail("Null session should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitSessionProjectNull() {
		Long projectId = session.getProjectId();
		try {
			
			session.setProjectId(null);
			JobManager.submit(session, job.toBean());
			fail("Null session project should throw an exception");
		} catch (JobException e) {
			session.setProjectId(projectId);
		}
	}
	
	public void testSubmitJobBeanNull() {
		
		try {
			JobManager.submit(session, null);
			fail("Null job bean should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanNullRequestedCpuTime() {
		try {
			JobBean bean = job.toBean();
			bean.setRequestedCpuTime(null);
			JobManager.submit(session, bean);
			fail("Null requested cpu time should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanNullName() {
		try {
			JobBean bean = job.toBean();
			bean.setName(null);
			JobManager.submit(session, bean);
			fail("Null name should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanInvalidName() {
		JobBean bean = job.toBean();
		bean.setName("");
		try {
			JobManager.submit(session, bean);
			fail("Emtpy name should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanNullExperimentName() {
		
		try {
			JobBean bean = job.toBean();
			bean.setExperimentName(null);
			JobManager.submit(session, bean);
			fail("Null experiment name should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanInvalidExperimentName() {
		JobBean bean = job.toBean();
		bean.setExperimentName("");
		try {
			JobManager.submit(session, bean);
			fail("Empty experiment name should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanNullRequestedCpus() {
		
		try {
			JobBean bean = job.toBean();
			bean.setRequestedCpuTime(null);
			JobManager.submit(session, bean);
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanNullQueue() {
		
		try {
			JobBean bean = job.toBean();
			bean.setQueueName(null);
			JobManager.submit(session, bean);
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanNullAllocationName() {
		
		try {
			JobBean bean = job.toBean();
			bean.setAllocationName(null);
			JobManager.submit(session, bean);
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanInvalidAllocationName() {
		try {
			JobBean bean = job.toBean();
			bean.setAllocationName("");
			JobManager.submit(session, bean);
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanNullProject() {
		
		try {
			JobBean bean = job.toBean();
			bean.setProjectName(null);
			JobManager.submit(session, bean);
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanInvalidProject() {
		
		// set the project to deactivated
		ProjectDao pDao = new ProjectDao(session);
		Project p = pDao._get();
		p.setStatus(ProjectStatusType.DEACTIVATED);
		pDao.persist(p);
		
		try {
			JobManager.submit(session, job.toBean());
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {
			p.setStatus(ProjectStatusType.ACTIVE);
			pDao.persist(p);
		}
	}
	
	public void testSubmitJobBeanInvalidRequestedCpuTime() {
		// set the project to zero allocation
		ProjectDao pDao = new ProjectDao(session);
		Project p = pDao._get();
		p.getUsage().setAllocated(0);
		pDao.persist(p);
		try {
			JobManager.submit(session, job.toBean());
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {
			p.getUsage().setAllocated(10000);
			pDao.persist(p);
		}
	}
	
	public void testSubmitJobBeanNullSoftware() {
		
		try {
			JobBean bean = job.toBean();
			bean.setSoftwareName(null);
			JobManager.submit(session, bean);
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {}
	}
	
	public void testSubmitJobBeanBlackListedSoftware() {
		// add a blacklist entry to trigger an exception
		BlackListBean bl = new BlackListBean();
		bl.setUsername(job.getUser().getUsername());
		bl.setSoftware(job.getSoftware().getName());
		bl.setEnabled(true);
		
		BlackListDao bdao = new BlackListDao();
		bdao.add(bl);
		
		try {
			// session was closed and the job detached after the add...why?
			job = JobDao.getById(job.getId());
			JobManager.submit(session, job.toBean());
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {
			// remove the entry on failure
			BlackListEntry entry = bdao.get(bl.getUsername(), bl.getSoftware());
			bdao._remove(entry);
		}
	}
	
	public void testSubmitJobBeanInvalidSystem() {
		// add a blacklist entry to trigger an exception
		ComputeResource system = new ResourceDao(session)._getComputeResource(TEST_SYSTEMNAME); 
//		job.setSystem(system);
		
		try {
			JobBean bean = job.toBean();
			bean.setSystemName(system.getName());
			JobManager.submit(session, bean);
			fail("Null requested cpus should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testSubmit() throws JobException {
		assertNotNull(JobManager.submit(session, job.toBean()));
	}
	
	/*************************************************
	 * 
	 * 		Test Submit Job
	 * 
	 *************************************************/
	
	public void testKillSessionNull() {
		try {
			JobManager.kill(null, job.getId());
			fail("Null session should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testKillJobNull() {
		try {
			JobManager.kill(session, null);
			fail("Null job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testKillJobInvalid() {
		try {
			JobManager.kill(session, new Long(-1));
			fail("Invalid job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testKillJobNotRunning() {
		
		job.setStatus(JobStatusType.FINISHED);
		JobDao.persist(job);
		
		try {
			JobManager.kill(session, job.getId());
			fail("Killing finished job should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testKill() {
		// return the job status to running for the next test
		job.setStatus(JobStatusType.RUNNING);
		JobDao.persist(job);
		
		JobManager.kill(session, job.getId());
	}

	/*************************************************
	 * 
	 * 		Test Hide Job
	 * 
	 *************************************************/
	
	public void testHideSessionNull() {
		try {
			JobManager.hide(null, job.getId());
			fail("Null session should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testHideJobNull() {
		try {
			JobManager.hide(session, null);
			fail("Null job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testHideJobInvalid() {
		try {
			JobManager.hide(session, new Long(-1));
			fail("Invalid job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testHide() {
		// assure job is unhidden
		job.setHidden(false);
		JobDao.persist(job);
		
		JobManager.hide(session, job.getId());
		
		assertTrue(JobDao.getById(job.getId()).isHidden());
	}

	/*************************************************
	 * 
	 * 		Test Unhide Jobs
	 * 
	 *************************************************/
	
	public void testUnhideSessionNull() {
		try {
			JobManager.unhide(null, job.getId());
			fail("Null session should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testUnhideJobNull() {
		try {
			JobManager.unhide(session, null);
			fail("Null job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testUnhideJobInvalid() {
		try {
			JobManager.unhide(session, new Long(-1));
			fail("Invalid job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testUnhide() {
		// assure job is hidden
		job.setHidden(true);
		JobDao.persist(job);
		
		JobManager.unhide(session, job.getId());
		
		assertFalse(JobDao.getById(job.getId()).isHidden());
	}

	/*************************************************
	 * 
	 * 		Test Unhide All Jobs
	 * 
	 *************************************************/
	
	public void testUnhideAllSessionNull() {
		try {
			JobManager.unhideAll(null);
			fail("Null session should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testUnhideAllSessionProjectNull() {
		Long projectId = session.getProjectId();
		
		try {
			session.setProjectId(null);
			JobManager.unhideAll(session);
			fail("Null session project should throw an exception");
		} catch (JobException e) {
			session.setProjectId(projectId);
		}
	}
	
	public void testUnhideAll() throws JobException {
		
		JobManager.unhideAll(session);
		
//		org.hibernate.Session s = HibernateUtil.getSession();
//		if (!s.isConnected()) {
//			s = HibernateUtil.getSessionFactory().openSession();
//		}
		
		for(Job j: JobDao.getByUser(session.getUserId(), false)) {
			if (j.getProject().getId().equals(session.getProjectId())) {
				assertFalse(j.isHidden());
			}
		}
	}

	/*************************************************
	 * 
	 * 		Test Is Job Running
	 * 
	 *************************************************/
	
	public void testIsRunningJobNull() {
		try {
			JobManager.isRunning(null);
			fail("Null job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testIsRunningJobInvalid() {
		try {
			JobManager.isRunning(new Long(-1));
			fail("Invalid job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testIsRunningStatusFailed() {
		// set the job to the tested status
		job.setStatus(JobStatusType.FAILED);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusFinished() {
		// set the job to the tested status
		job.setStatus(JobStatusType.FINISHED);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusRemoved() {
		// set the job to the tested status
		job.setStatus(JobStatusType.REMOVED);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusStopped() {
		// set the job to the tested status
		job.setStatus(JobStatusType.STOPPED);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusRuntimeError() {
		// set the job to the tested status
		job.setStatus(JobStatusType.RUNTIME_ERROR);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusSubmissionError() {
		// set the job to the tested status
		job.setStatus(JobStatusType.SUBMISSION_ERROR);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusUnknown() {
		// set the job to the tested status
		job.setStatus(JobStatusType.UNKNOWN);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusTimeElapsed() {
		// set the job to the tested status
		job.setStatus(JobStatusType.TIME_ELAPSED);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusNotInQueue() {
		// set the job to the tested status
		job.setStatus(JobStatusType.NOT_IN_QUEUE);
		JobDao.persist(job);
		
		assertFalse(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusRunning() {
		// set the job to the tested status
		job.setStatus(JobStatusType.RUNNING);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusCheckpointing() {
		// set the job to the tested status
		job.setStatus(JobStatusType.CHECKPOINTING);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusExiting() {
		// set the job to the tested status
		job.setStatus(JobStatusType.EXITING);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusInitial() {
		// set the job to the tested status
		job.setStatus(JobStatusType.INITIAL);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusMigrating() {
		// set the job to the tested status
		job.setStatus(JobStatusType.MIGRATING);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusResuming() {
		// set the job to the tested status
		job.setStatus(JobStatusType.RESUMING);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusStarting() {
		// set the job to the tested status
		job.setStatus(JobStatusType.STARTING);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	public void testIsRunningStatusWaiting() {
		// set the job to the tested status
		job.setStatus(JobStatusType.WAITING);
		JobDao.persist(job);
		
		assertTrue(JobManager.isRunning(job.getId()));
	}
	
	/*************************************************
	 * 
	 * 		Test Delete Jobs
	 * 
	 *************************************************/
	
	public void testDeleteSessionNull() {
		try {
			JobManager.delete(null, job.getId());
			fail("Null session should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testDeleteJobNull() {
		try {
			JobManager.delete(session, null);
			fail("Null job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testDeleteJobInvalid() {
		try {
			JobManager.delete(session, new Long(-1));
			fail("Invalid job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testDelete() {
		JobManager.delete(session,job.getId());
		
		assertTrue(JobDao.getById(job.getId()).isDeleted());
	}

	/*************************************************
	 * 
	 * 		Test Search Jobs
	 * 
	 *************************************************/
	
	public void testSearch() {
		fail("Not yet implemented");
	}

	/*************************************************
	 * 
	 * 		Test Check Job Status
	 * 
	 *************************************************/
	
	public void testCheckStatusSessionNull() {
		try {
			JobManager.checkStatus(null, job.getId());
			fail("Null session should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testCheckStatusJobNull() {
		try {
			JobManager.checkStatus(session, null);
			fail("Null job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testCheckStatusJobInvalid() {
		try {
			JobManager.checkStatus(session, new Long(-1));
			fail("Invalid job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testCheckStatus() {
		job.setStatus(JobStatusType.FINISHED);
		JobDao.persist(job);
		
		assertTrue(JobManager.checkStatus(session, job.getId()).equals(JobStatusType.FINISHED));
	}

	/*************************************************
	 * 
	 * 		Test Is Job Owned By Session User
	 * 
	 *************************************************/
	
	public void testIsJobOwnedByUserSessionNull() {
		try {
			JobManager.isJobOwnedByUser(null, job.getId());
			fail("Null session should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testIsJobOwnedByUserJobNull() {
		try {
			JobManager.isJobOwnedByUser(session, null);
			fail("Null job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testIsJobOwnedByUserJobInvalid() {
		try {
			JobManager.isJobOwnedByUser(session, new Long(-1));
			fail("Invalid job id should throw an exception");
		} catch (JobException e) {
		}
	}
	
	public void testIsJobOwnedByUser() {
		assertTrue(JobManager.isJobOwnedByUser(session, job.getId()));
	}
	
	public void testIsJobOwnedByUserWrongSessionUser() {
		session.setUserId(new Long(0));
		assertFalse(JobManager.isJobOwnedByUser(session, job.getId()));
		session.setUserId(job.getUser().getId());
	}
	
	public void testIsJobOwnedByUserWrongJobUser() {
		job.setUser(new UserDao()._get("ccguser"));
		JobDao.persist(job);
		assertFalse(JobManager.isJobOwnedByUser(session, job.getId()));
		job.setUser(new UserDao()._get(TEST_USERNAME));
		JobDao.persist(job);
	}
	
//	public void testCleanup() throws InfrastructureException, ProfileValidationException {
//
//		SessionDao.delete(session);
//		
//		JobDao.delete(job);
//		assertNull(JobDao.getById(job.getId()));
//		
//		UserDao udao = new UserDao();
//		User user = udao._get(TEST_USERNAME);
//		udao._remove(user);
//		assertNull(udao._get(TEST_USERNAME));
//		
//		ProjectDao projectDao = new ProjectDao();
//		Project project = projectDao._get(TEST_PROJECTNAME);
//		
//		UserProjectDao.remove(up);
//		assertNull(UserProjectDao.get(TEST_USERNAME, project.getId()));
//		
//		ProjectResourceDao.remove(pr);
//		assertNull(ProjectResourceDao.get(project.getId(),pr.getResource().getName(),pr.getAllocationName()));
//		
//		UserProjectResourceDao.remove(upr);
//		assertNull(UserProjectResourceDao.get(TEST_USERNAME, project.getId(),pr.getResource().getName(),pr.getAllocationName()));
//		
//		projectDao._remove(project);
//		assertNull(projectDao._get(TEST_PROJECTNAME));
//		
//		
//	}

}
