/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.WorkflowDao;
import org.gridchem.service.exceptions.WorkflowException;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.Workflow;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class WorkflowDaoTest extends GMSTestCase {

//	private Job job;
//	private Workflow workflow;
//	private ProjectDao projectDao;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;
	
	public void setUp() throws Exception {
		super.setUp();
//	
//		job = createJob();
//		
//		if (job.getWorkflow() == null) {
//			
//			// create workflow
//			workflow = createWorkflow();
//			
//			// save workflow
//			WorkflowDao.persist(workflow);
//			
//			// add workflow ref to job
//			job.setWorkflow(workflow);
//			
//			// save job
//			JobDao.persist(job);
//		} else {
//			workflow = job.getWorkflow();
//		}
		
	}
	
	
	/************************************************ 
	 *
	 * 			Workflow Persistence Test
	 *
	 ************************************************/
	
	public void testPersistNameNull() {
		try {
			workflow.setName(null);
			WorkflowDao.persist(workflow);
			assertTrue(false);
		} catch (WorkflowException e) {
			
		}
	}
	
	public void testPersistUserNull() {
		try {
			workflow.setUser(null);
			WorkflowDao.persist(workflow);
			assertTrue(false);
		} catch (WorkflowException e) {
			
		}
	}
	
	public void testPersistCreatedNull() {
		try {
			workflow.setCreated(null);
			WorkflowDao.persist(workflow);
			assertTrue(false);
		} catch (WorkflowException e) {
			
		}
	}
	
	/**
	 * Test a valid workflow is added to the database propertly.
	 */
	public void testPersist() throws Exception {
		WorkflowDao.persist(workflow);
		assertNotNull(workflow.getId());
	}
	
	/************************************************ 
	 *
	 * 			Get All Jobs Test 
	 *
	 ************************************************/
	
	public void testGetAll() throws Exception {
		assertFalse(WorkflowDao.getAll().isEmpty());
	}
	
	/************************************************ 
	 *
	 * 			Get Workflow By Id Test 
	 *
	 ************************************************/
	
	public void testGetByIdNull() {
		assertNull(JobDao.getById(null));
	}
	
	public void testGetByIdInvalid() {
		assertNull(JobDao.getById(new Long(-1)));
	}
	
	public void testGetById() {
		assertNotNull(JobDao.getById(job.getId()));
	}
	
//	/************************************************ 
//	 *
//	 * 			Get Jobs By Project Test 
//	 *
//	 ************************************************/
//	
//	public void testGetByProjectNull() throws Exception {
//		assertTrue(WorkflowDao.getByProject(null).isEmpty());
//	}
//	
//	public void testGetByProjectInvalid() throws Exception {
//		assertTrue(WorkflowDao.getByProject(new Long("-1")).isEmpty());
//	}
//	
//	public void testGetByProject() throws Exception {
//		assertFalse(WorkflowDao.getByProject(job.getProject().getId()).isEmpty());
//	}
	
	/************************************************ 
	 *
	 * 			Get Job By User Test 
	 *
	 ************************************************/
	
	public void testGetByUserInvalid() throws Exception {
		assertTrue(WorkflowDao.getByUser(new Long("-1")).isEmpty());
	}
	
	public void testGetByUser() throws Exception {
		assertFalse(WorkflowDao.getByUser(job.getUser().getId()).isEmpty());
	}
	
	
	/************************************************ 
	 *
	 * 			Get Job By Username Test 
	 *
	 ************************************************/
	
	public void testGetByUsernameEmpty() throws Exception {
		assertTrue(WorkflowDao.getByUser("").isEmpty());
	}
	
	public void testGetByUsernameInvalid() throws Exception {
		assertTrue(WorkflowDao.getByUser("-1").isEmpty());
	}
	
	public void testGetByUsername() throws Exception {
		assertFalse(WorkflowDao.getByUser(TEST_USERNAME).isEmpty());
	}
	
	
	/************************************************ 
	 *
	 * 			Update Workflow Test 
	 *
	 ************************************************/
	
	public void testUpdateVisibility() throws Exception {
		
		workflow.setStatus(JobStatusType.HOLD);
		
		WorkflowDao.persist(workflow);
		
		Workflow test = WorkflowDao.getById(workflow.getId());
		
		assertTrue(test.getStatus().equals(JobStatusType.HOLD));
	}
	
	/************************************************ 
	 *
	 * 			Unhide All Job Test 
	 *
	 ************************************************/
	
	public void testHideNull() {
		
		try {
			WorkflowDao.setHidden(null,true);
			assertTrue(false);
		} catch (WorkflowException e) {
			
		}
		
	}
	
	public void testHide() throws Exception {
		
		WorkflowDao.setHidden(workflow.getId(),true);
		
		Workflow test = WorkflowDao.getById(workflow.getId());
		
		assertTrue(test.isHidden());
		
		for (Job j: test.getJobs()) {
			assertTrue(j.isHidden());
		}
		
	}
	
	public void testUnhide() throws Exception {
		
		WorkflowDao.setHidden(workflow.getId(),false);
		
		Workflow test = WorkflowDao.getById(workflow.getId());
		
		assertFalse(test.isHidden());
		
		for (Job j: test.getJobs()) {
			assertFalse(j.isHidden());
		}
		
	}
	
	/************************************************ 
	 *
	 * 			Delete Job Test 
	 *
	 ************************************************/
	
	public void testDeleteNull() {
		
		try {
			WorkflowDao.delete(null);
			WorkflowDao.persist(workflow);
			assertTrue(false);
		} catch (WorkflowException e) {
			
		}
	}
	
	public void testDelete() throws Exception {
		WorkflowDao.delete(workflow);
	}
	
	/************************************************ 
	 *
	 * 			Cleanup test data
	 *
	 ************************************************/
	
//	public void testZCleanUp() {
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
//		JobDao.delete(job);
//		assertNull(JobDao.getById(job.getId()));
//	}
	
	/**
	 * @param x
	 */
	public WorkflowDaoTest(String x) {
		super(x);
	}

	/*************************************************
	 * 
	 * 			Utility Methods
	 * 
	 *************************************************/
	
//	private Workflow createWorkflow() {
//		Workflow workflow = new Workflow();
//		workflow.setName(TEST_WORKFLOWNAME);
//		workflow.setDescription("empty");
//		workflow.setCreated(new Date());
//		workflow.setStatus(JobStatusType.INITIAL);
//		workflow.setInput("empty");
//		workflow.setUser(new UserDao()._get(TEST_USERNAME));
//		
//		return workflow;
//	}
//	
//	private Job createJob() {
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
//		session = new GMSSession();
//		session.setType(AccessType.COMMUNITY);
//		session.setProjectId(project.getId());
//		session.setUserId(user.getId());
//		
//		projectDao = new ProjectDao(session);
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
//		}
//		
//		return job;
//		
//	}
	 
}
