package org.gridchem.service.test.dao.ccg;

import java.util.HashSet;

import org.gridchem.service.dao.NotificationDao;
import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.model.Notification;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.NotificationType;
import org.gridchem.service.test.GMSTestCase;


public class NotificationDaoTest extends GMSTestCase {
	
//	private Notification notification;
//	private Job job;
//	private ProjectDao projectDao;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;
	
//	private static Long notificationId;
	
	public NotificationDaoTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();

//		job = createJob();
//		
//		n = new Notification();
//		n.setJob(job);
//		n.setType(NotificationType.EMAIL);
//		n.setStatus(JobStatusType.FINISHED);
//		
//		try {
//			NotificationDao.add(n);
//		} catch (NotificationException e) {}
		
	}
	
	protected void tearDown() throws Exception {
		
		super.tearDown();
		
//		try {
//			File oFile = new File(outputFile);
//			oFile.createNewFile();
//			
//			props.put("test.notification.id", n.getId());
//			props.store(new FileOutputStream(oFile),"Notification ID from NotificationDao Junit Tests");
//			
//			
//		} catch (Exception e) {}
	}
	

	/********************************************************
	 * 
	 * 		Test Add Notifications
	 * 
	 ********************************************************/
	
	public void testAddNotificationNull() {
		try {
			NotificationDao.add((Notification)null);
			fail("Null notification should throw a notification exception.");
		} catch (NotificationException e) {
			// can't insert null notification
		}
	}
	
	public void testAddNotificationNullJob() {
		try {
			notification.setJob(null);
			NotificationDao.add(notification);
			fail("Null job should throw a notification exception.");
		} catch (NotificationException e) {
			// can't insert notification with null job
			notification.setJob(job);
		}
	}
	
	public void testAddNotificationNullType() throws NotificationException {
		try {
			notification.setType(null);
			NotificationDao.add(notification);
			fail("Null type should throw a notification exception.");
		} catch (NotificationException e) {
			// can't insert notification with null job
			notification.setType(NotificationType.EMAIL);
		}
	}
	
	public void testAddNotificationNullStatus() throws NotificationException {
		
		try {
			notification.setStatus(null);
			NotificationDao.add(notification);
			fail("Null status should throw a notification exception.");
		} catch (NotificationException e) {
			// can't insert notification with null job
			notification.setStatus(JobStatusType.FINISHED);
		}
	}
	
	public void testAddNotificationDuplicate() throws NotificationException {
		try {
			Notification notif = new Notification(job.toBean(),notification.getType(),notification.getStatus());
			NotificationDao.add(notif);
			fail("Duplicate notification should throw a notification exception.");
		} catch (NotificationException e) {
			// can't insert notification with null job
		}
	}
	
//	public void testAddNotification() throws NotificationException {
//		
//		NotificationDao.add(n);
//	}

	/********************************************************
	 * 
	 * 		Test Add Set Of Notifications
	 * 
	 ********************************************************/
	
	public void testAddCollectionOfNotificationNull() {
		try {
			NotificationDao.add((HashSet<Notification>)null);
			fail("Null notification collection should throw a notification exception.");
		} catch (NotificationException e) {
			// can't insert null set of notifications
		}
	}
	
	public void testAddCollectionOfNotificationEmpty() {
		try {
			NotificationDao.add(new HashSet<Notification>());
			fail("Empty notification collection should throw a notification exception.");
		} catch (NotificationException e) {
			// can't insert empty set of notifications
		}
	}
	
	public void testAddCollectionOfNotificationNullJob() {
		try {
			HashSet<Notification> notifs = new HashSet<Notification>();
			notification.setJob(null);
			notifs.add(notification);
			
			NotificationDao.add(notifs);
			
			fail("Null job in notification collection should throw a notification exception.");
			
		} catch (NotificationException e) {
			// can't insert notification with null job
			notification.setJob(job);
		}
	}
	
	public void testAddCollectionOfNotification() {
		try {
			HashSet<Notification> notifs = new HashSet<Notification>();
			notifs.add(notification);
			
			NotificationDao.add(notifs);
			
			assertNotNull(notifs.iterator().next().getId());
			
			notification = notifs.iterator().next();
			
		} catch (NotificationException e) {
			// can't insert notification with null job
			
		}
	}
	
	/********************************************************
	 * 
	 * 		Test Get All Notifications By JobId
	 * 
	 ********************************************************/
	
	public void testGetAllByJobIDNull() throws NotificationException {
		assertTrue(NotificationDao.getAllByJobID(null).isEmpty());
	}
	
	public void testGetAllByJobIDInvalid() throws NotificationException {
		assertTrue(NotificationDao.getAllByJobID(new Long(-1)).isEmpty());
	}
	
	public void testGetAllByJobID() throws NotificationException {
		assertFalse(NotificationDao.getAllByJobID(job.getId()).isEmpty());
	}

	/********************************************************
	 * 
	 * 		Test Get Notification By Id
	 * 
	 ********************************************************/
	
	public void testGetNotificationByIdNull() throws NotificationException {
		assertNull(NotificationDao.getNotificationById(null));
	}
	
	public void testGetNotificationByIdInvalid() throws NotificationException {
		assertNull(NotificationDao.getNotificationById(new Long(-1)));
	}
	
	public void testGetNotificationById() throws NotificationException {
		Notification n = NotificationDao.getAllByJobID(job.getId()).get(0);
		assertNotNull(NotificationDao.getNotificationById(n.getId()));
	}
	
	/********************************************************
	 * 
	 * 		Test Remove All Notifications By UserId
	 * 
	 ********************************************************/
	
	public void testDeleteAllForUserNull() {
		try {
			NotificationDao.deleteAllForUser(null);
			fail("Invalid user id should throw a notification exception.");
		} catch (NotificationException e) {
			// 
		}
	}
	
	public void testDeleteAllForUserInvalid() {
		try {
			NotificationDao.deleteAllForUser(new Long(-1));
			fail("Invalid user id should throw a notification exception.");
		} catch (NotificationException e) {
			// 
		}
	}
	
	public void testDeleteAllForUser() throws NotificationException {
		NotificationDao.deleteAllForUser(job.getUser().getId());
		
		assertTrue(NotificationDao.getAllByJobID(job.getId()).isEmpty());
	}
	
	/********************************************************
	 * 
	 * 		Test Remove A Notification
	 * 
	 ********************************************************/

	public void testDeleteNotificationNull() {
		try {
			NotificationDao.delete((Notification)null);
			fail("Null notification should throw a notification exception.");
		} catch (NotificationException e) {
			// 
		}
	}
	
//	public void testDeleteNotificationInvalid() {
//		try {
//			NotificationDao.delete(new Notification(job.toBean(),n.getType(),n.getStatus()));
//			assertTrue(false);
//		} catch (NotificationException e) {
//			// 
//		}
//	}
	
	public void testDeleteNotification() throws NotificationException {
		NotificationDao.delete(notification);
	}
	
	/********************************************************
	 * 
	 * 		Test Remove A Collection of Notifications
	 * 
	 ********************************************************/

	public void testDeleteCollectionOfNotificationNull() {
		try {
			NotificationDao.delete((HashSet<Notification>)null);
			fail("Null notification collection should throw a notification exception.");
		} catch (NotificationException e) {
			// 
		}
	}
	
	public void testDeleteCollectionOfNotificationEmpty() {
		try {
			HashSet<Notification> notifs = new HashSet<Notification>();
			
			NotificationDao.delete(notifs);
			
			fail("Empty notification collection should throw a notification exception.");
		} catch (NotificationException e) {
			// 
		}
	}
	
//	public void testDeleteCollectionOfNotificationInvalid() {
//		try {
//			HashSet<Notification> notifs = new HashSet<Notification>();
//			notifs.add(new Notification(job.toBean(),n.getType(),n.getStatus()));
//			
//			NotificationDao.delete(notifs);
//			
//			assertTrue(false);
//		} catch (NotificationException e) {
//			// 
//		}
//	}
	
	public void testDeleteCollectionOfNotification() throws NotificationException {
		
		HashSet<Notification> notifs = new HashSet<Notification>();
		notifs.add(notification);
		
		NotificationDao.delete(notifs);
		
	}
	
	/********************************************************
	 * 
	 * 		Clean Up Test Data 
	 * 
	 ********************************************************/
	
//	public void testZCleanup() throws NotificationException {
//		
//		NotificationDao.delete(NotificationDao.getAllByJobID(job.getId()));
//		assertTrue(NotificationDao.getAllByJobID(job.getId()).isEmpty());
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
//	}

//	/**
//	 * Create a dummy job for testing and persist in the db.
//	 * 
//	 * @return Job
//	 */
//	private Job createJob() {
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
//		
//		return job;
//	}
}
