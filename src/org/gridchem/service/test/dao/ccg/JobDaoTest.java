/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import java.util.Calendar;
import java.util.List;

import org.gridchem.service.dao.JobDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.model.Job;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class JobDaoTest extends GMSTestCase {

//	private Job job;
//	private ProjectDao projectDao;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;
	
	public void setUp() throws Exception {
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
//			
//			JobDao.persist(job);
//		}
		
	}
	
	/************************************************ 
	 *
	 * 			Job Persistence Test
	 *
	 ************************************************/
	
	public void testPersistUserNull() {
		try {
			job.setUser(null);
			JobDao.persist(job);
			fail("Null user should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistSoftwareNull() {
		try {
			job.setSoftware(null);
			JobDao.persist(job);
			fail("Null software should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistSystemNull() {
		try {
			job.setSystem(null);
			JobDao.persist(job);
			fail("Null system should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistStorageNull() {
		try {
			job.setStorage(null);
			JobDao.persist(job);
			fail("Null storage should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistQueueNull() {
		try {
			job.setQueue(null);
			JobDao.persist(job);
			fail("Null queue should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistProjectNull() {
		try {
			job.setProject(null);
			JobDao.persist(job);
			fail("Null project should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistNameNull() {
		try {
			job.setName(null);
			JobDao.persist(job);
			fail("Null name should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistExperimentNull() {
		try {
			job.setExperimentName(null);
			JobDao.persist(job);
			fail("Null experiment should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistAllocationNull() {
		try {
			job.setAllocationName(null);
			JobDao.persist(job);
			fail("Null allocation should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistRequestedCpuNull() {
		try {
			job.setRequestedCpus(null);
			JobDao.persist(job);
			fail("Null requested cpu count should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistRequestedCpuTimeNull() {
		try {
			job.setRequestedCpuTime(null);
			JobDao.persist(job);
			fail("Null requested cpu time should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
	public void testPersistRequestedMemoryNull() {
		try {
			job.setRequestedCpus(null);
			JobDao.persist(job);
			fail("Null requested memory should thrown an exception.");
		} catch (JobException e) {
			
		}
	}
	
//	/**
//	 * Test a valid job is added to the database propertly.
//	 */
//	public void testPersist() {
//		JobDao.persist(job);
//		assertNotNull(job.getId());
//	}
	
	/************************************************ 
	 *
	 * 			Get All Jobs Test 
	 *
	 ************************************************/
	
	public void testGetAll() {
		assertFalse(JobDao.getAll().isEmpty());
	}
	
	/************************************************ 
	 *
	 * 			Get Job By Id Test 
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
	
	/************************************************ 
	 *
	 * 			Get Job By Name Test 
	 *
	 ************************************************/
	
	public void testGetByNameNull() {
		assertNull(JobDao.getByName(null));
	}
	
	public void testGetByNameEmpty() {
		assertNull(JobDao.getByName(""));
	}
	
	public void testGetByNameInvalid() {
		assertNull(JobDao.getByName(job.getName()+System.currentTimeMillis()));
	}
	
	public void testGetByName() {
		assertNotNull(JobDao.getByName(job.getName()));
	}
	
	/************************************************ 
	 *
	 * 			Get Jobs By System Test 
	 *
	 ************************************************/
	
	public void testGetBySystemNull() {
		assertTrue(JobDao.getBySystem(null).isEmpty());
	}
	
	public void testGetBySystemEmpty() {
		assertTrue(JobDao.getBySystem("").isEmpty());
	}
	
	public void testGetBySystemInvalid() {
		assertTrue(JobDao.getBySystem("-1").isEmpty());
	}
	
	public void testGetBySystem() {
		assertFalse(JobDao.getBySystem(job.getSystem().getName()).isEmpty());
	}
	
	/************************************************ 
	 *
	 * 			Get Jobs By System LocalId Test 
	 *
	 ************************************************/
	
	public void testGetBySystemLocalIdSystemNull() {
		assertTrue(JobDao.getBySystemLocalId(null,job.getLocalId()).isEmpty());
	}
	
	public void testGetBySystemLocalIdSystemEmpty() {
		assertTrue(JobDao.getBySystemLocalId("",job.getLocalId()).isEmpty());
	}
	
	public void testGetBySystemLocalIdSystemInvalid() {
		assertTrue(JobDao.getBySystemLocalId("-1",job.getLocalId()).isEmpty());
	}
	
	public void testGetBySystemLocalIdLocalIdNull() {
		assertTrue(JobDao.getBySystemLocalId(job.getSystem().getName(),null).isEmpty());
	}
	
	public void testGetBySystemLocalIdLocalIdEmpty() {
		assertTrue(JobDao.getBySystemLocalId(job.getSystem().getName(),"").isEmpty());
	}
	
	public void testGetBySystemLocalIdLocalIdInvalid() {
		assertTrue(JobDao.getBySystemLocalId(job.getSystem().getName(),"-1").isEmpty());
	}
	
	public void testGetBySystemLocalId() {
		assertFalse(JobDao.getBySystemLocalId(job.getSystem().getName(),job.getLocalId()).isEmpty());
	}
	
	/************************************************ 
	 *
	 * 			Get Job By Name Test 
	 *
	 ************************************************/
	
	public void testGetAfterDateNull() {
		assertTrue(JobDao.getAfterDate(null).isEmpty());
	}
	
	/**
	 * Test won't return jobs with same date 
	 */
	public void testGetAfterDateIgnoresSameDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(job.getCreated().getTime());
		assertTrue(JobDao.getAfterDate(cal).isEmpty());
	}
	
	/**
	 * Test won't return jobs before given date
	 */
	public void testGetAfterDateIgnoresBeforeDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(job.getCreated().getTime() + 1000);
		
		assertTrue(JobDao.getAfterDate(cal).isEmpty());
	}
	
	/**
	 * Test returns jobs after given date
	 */
	public void testGetAfterDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(job.getCreated().getTime() - 1);
		List<Job> jobs = JobDao.getAfterDate(cal);
		
		assertNotNull(jobs);
		
		for (Job j: jobs) {
			assertTrue(j.getCreated().after(cal.getTime()));
		}
	}
	
	/************************************************ 
	 *
	 * 			Get Job By User Test 
	 *
	 ************************************************/
	
	public void testGetByUserEmpty() {
		assertTrue(JobDao.getByUser("",true).isEmpty());
	}
	
	public void testGetByUserInvalid() {
		assertTrue(JobDao.getByUser("-1",true).isEmpty());
	}
	
	public void testGetByUser() {
		assertFalse(JobDao.getByUser(TEST_USERNAME,false).isEmpty());
	}
	
//	public void testGetByUserReturnsLimitsResults() {
//		int limit = JobDao.getByUser(TEST_USERNAME,true).size();
//		int full = JobDao.getByUser(TEST_USERNAME,false).size();
//		
//		assertTrue(full > limit);
//	}
	
	/************************************************ 
	 *
	 * 			Update Job Test 
	 *
	 ************************************************/
	
	public void testUpdateVisibility() {
		
		job.setHidden(true);
		JobDao.persist(job);
		
		Job test = JobDao.getById(job.getId());
		
		assertTrue(test.isHidden());
	}
	
	/************************************************ 
	 *
	 * 			Unhide All Job Test 
	 *
	 ************************************************/
	
	public void testUnhideAll() {
		
		JobDao.unhideAll(job.getUser().getId(), job.getProject().getId());
		
		List<Job> jobs = JobDao.getByUser(job.getUser().getUsername(), true);
		
		for (Job j: jobs) {
			if (j.getProject().getId().equals(job.getProject().getId())) {
				assertFalse(j.isHidden());
			}
		}
		
	}
	
	/************************************************ 
	 *
	 * 			Delete Job Test 
	 *
	 ************************************************/
	
	public void testDeleteNull() {
		
		try {
			JobDao.delete(null);
			fail("Deleting a null job should throw an exception");
//			JobDao.persist(job);
		} catch (JobException e) {
			
		}
	}
	
	public void testDelete() {
		JobDao.delete(job);
		assertNull(JobDao.getById(job.getId()));
	}
	
	/************************************************ 
	 *
	 * 			Cleanup test data
	 *
	 ************************************************/
	
//	public void testZCleanUp() {
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
//	}
	
	/**
	 * @param x
	 */
	public JobDaoTest(String x) {
		super(x);
	}

}
