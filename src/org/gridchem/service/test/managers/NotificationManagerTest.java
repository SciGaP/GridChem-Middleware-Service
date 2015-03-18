/**
 * 
 */
package org.gridchem.service.test.managers;

import java.util.HashSet;
import java.util.List;

import org.gridchem.service.dao.NotificationDao;
import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.model.Notification;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.NotificationType;
import org.gridchem.service.notification.NotificationManager;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

/**
 * @author dooley
 *
 */
public class NotificationManagerTest extends GMSTestCase {
	
//	private Job job;
	private List<Notification> notifs;
//	private ProjectDao projectDao;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;
	
	/**
	 * @param name
	 */
	public NotificationManagerTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.test.GMSTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
//		job = createJob();
		
		// ensure there is one over each notification type for a given job
		notifs = NotificationDao.getAllByJobID(job.getId());
		for(NotificationType type: NotificationType.values()) {
			Notification n = new Notification(job.toBean(),type,JobStatusType.FINISHED);
			if (!notifs.contains(n)) {
				NotificationDao.add(n);
				notifs.add(n);
			}
		}
	}

	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#sendChangeOfStatusMessage(org.gridchem.service.beans.UserBean, org.gridchem.service.model.Job)}.
	 */
	public void testSendChangeOfStatusMessage() {
		fail("Not yet implemented");
	}
	
	/********************************************************
	 * 
	 * 		Test Get All Notifications
	 * 
	 ********************************************************/

	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#getAll(java.lang.Long)}.
	 */
	public void testGetAllNull() throws Exception {
		assertTrue(NotificationManager.getAll(null).isEmpty());
	}

	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#getAll(java.lang.Long)}.
	 */
	public void testGetAllInvalid() throws Exception {
		assertTrue(NotificationManager.getAll(new Long(-1)).isEmpty());
	}
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#getAll(java.lang.Long)}.
	 */
	public void testGetAll() throws NotificationException {
		List<Notification> notifications = NotificationManager.getAll(job.getId());
		assertTrue(ServiceUtil.isValid(notifs));
		for (Notification n: notifications) {
			assertTrue(notifs.contains(n));
		}
	}
	
	/********************************************************
	 * 
	 * 		Test Add Notification By JobID
	 * 
	 ********************************************************/
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#add(java.lang.Long)}.
	 */
	public void testAddLongNull() {
		try {
			NotificationManager.add(null);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#add(java.lang.Long)}.
	 */
	public void testAddLongInvalid() {
		try {
			NotificationManager.add(new Long(-1));
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testAddLongDuplicate() throws Exception {
		try {
			NotificationManager.add(job.getId());
			assertTrue(false);
		} catch(NotificationException e) {
			// an email notificaiton is already present. This should throw an exception
		}
	}
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#add(java.lang.Long)}.
	 */
	public void testAddLong() throws Exception {
		// remove the email notification before attempting to add
		Notification n = notifs.get(notifs.indexOf(new Notification(job.toBean(),NotificationType.EMAIL,JobStatusType.FINISHED)));
		NotificationDao.delete(n);
		assertNotNull(NotificationManager.add(job.getId()));
	}
	
	/********************************************************
	 * 
	 * 		Test Add Notification By JobID and type 
	 * 
	 ********************************************************/

	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#add(java.lang.Long, org.gridchem.service.model.enumeration.NotificationType)}.
	 */
	public void testAddLongNotificationTypeNullJobId() {
		try {
			NotificationManager.add(null,NotificationType.EMAIL);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testAddLongNotificationTypeInvalidJobId() {
		try {
			NotificationManager.add(new Long(-1),NotificationType.EMAIL);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testAddLongNotificationTypeNullType() {
		try {
			NotificationManager.add(job.getId(),null);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testAddLongNotificationTypeDuplicateType() {
		try {
			NotificationManager.add(job.getId(),NotificationType.EMAIL);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testAddLongNotificationTypeEmail() throws Exception {
		Notification n = notifs.get(notifs.indexOf(new Notification(job.toBean(),NotificationType.EMAIL,JobStatusType.FINISHED)));
		NotificationDao.delete(n);
		assertNotNull(NotificationManager.add(job.getId(),NotificationType.EMAIL));
	}
	
	public void testAddLongNotificationTypeSMS() throws Exception {
		Notification n = notifs.get(notifs.indexOf(new Notification(job.toBean(),NotificationType.SMS,JobStatusType.FINISHED)));
		NotificationDao.delete(n);
		assertNotNull(NotificationManager.add(job.getId(),NotificationType.SMS));
	}
	
	public void testAddLongNotificationTypeIM() throws Exception {
		Notification n = notifs.get(notifs.indexOf(new Notification(job.toBean(),NotificationType.IM,JobStatusType.FINISHED)));
		NotificationDao.delete(n);
		assertNotNull(NotificationManager.add(job.getId(),NotificationType.IM));
	}
	
	public void testAddLongNotificationTypeTwitter() throws Exception {
		Notification n = notifs.get(notifs.indexOf(new Notification(job.toBean(),NotificationType.TWITTER,JobStatusType.FINISHED)));
		NotificationDao.delete(n);
		assertNotNull(NotificationManager.add(job.getId(),NotificationType.TWITTER));
	}

	/********************************************************
	 * 
	 * 		Test Remove Notification By JobID
	 * 
	 ********************************************************/
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#remove(java.lang.Long)}.
	 */
	public void testRemoveLongNull() {
		try {
			NotificationManager.remove((Long)null);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testRemoveLongInvalid() {
		try {
			NotificationManager.remove(new Long(-1));
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testRemoveLong() throws NotificationException {
		
		// remove all test notifications for the test job
		NotificationManager.remove(job.getId());
		assertTrue(NotificationManager.getAll(job.getId()).isEmpty());
	}
	
	public void testRemoveLongNoNotifications() throws NotificationException {
		// delete all the notifications
		NotificationDao.delete(notifs);
		
		try {
			NotificationManager.remove(job.getId());
			assertTrue(false);
		} catch (NotificationException e) {
			// all notifications for the test job are gone. Should throw an exception.
		}
	}

	/********************************************************
	 * 
	 * 		Test Remove Notification By JobID and type
	 * 
	 ********************************************************/
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#remove(java.lang.Long, org.gridchem.service.model.enumeration.NotificationType)}.
	 */
	public void testRemoveLongNotificationTypeNullJobId() {
		try {
			NotificationManager.remove(null,NotificationType.EMAIL);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testRemoveLongNotificationTypeInvalidJobId() {
		try {
			NotificationManager.remove(new Long(-1),NotificationType.EMAIL);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testRemoveLongNotificationTypeNullType() {
		try {
			NotificationManager.remove(job.getId(),null);
			assertTrue(false);
		} catch(NotificationException e) {}
	}
	
	public void testRemoveLongNotificationTypeDoesNotExist() throws NotificationException{
		NotificationManager.remove(job.getId(),NotificationType.EMAIL);
	}
	
	public void testRemoveLongNotificationTypeDoesNotExistSms() throws NotificationException{
		NotificationManager.remove(job.getId(),NotificationType.SMS);
	}
	
	public void testRemoveLongNotificationTypeDoesNotExistIm() throws NotificationException{
		NotificationManager.remove(job.getId(),NotificationType.IM);
	}
	
	public void testRemoveLongNotificationTypeDoesNotExistTwitter() throws NotificationException{
		NotificationManager.remove(job.getId(),NotificationType.TWITTER);
	}
	
	/********************************************************
	 * 
	 * 		Test Remove Set Of Notifications
	 * 
	 ********************************************************/
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#remove(java.util.HashSet)}.
	 */
	public void testRemoveHashSetOfNotificationNull() throws NotificationException {
		
		try {
			NotificationManager.remove((HashSet<Notification>)null);
			assertTrue(false);
		} catch (Exception e) {}
		
	}
	
	public void testRemoveHashSetOfNotificationInvalidJobId() throws NotificationException {
		
		try {
			
			HashSet<Notification> notifications = new HashSet<Notification>();
			Notification n = NotificationManager.getDefaultNotification();
			// default has no job associated with it
			notifications.add(n);
			
			NotificationManager.remove(notifications);
			
			assertTrue(false);
			
		} catch (Exception e) {
			// should fail deleting a job with no id and no job associated with it
		}
		
	}
	
	public void testRemoveHashSetOfNotification() throws NotificationException {
		
		HashSet<Notification> notifications = new HashSet<Notification>();
		Long[] ids = new Long[notifs.size()];
		int i = 0;
		for(Notification n: notifs) {
			notifications.add(n);
			ids[i++] = n.getId();
		}
		
		NotificationManager.remove(notifications);
		
		assertTrue(NotificationDao.getAllByJobID(job.getId()).isEmpty());
				
	}

	/********************************************************
	 * 
	 * 		Test Clear All User Notifications
	 * 
	 ********************************************************/
	
	/**
	 * Test method for {@link org.gridchem.service.notification.NotificationManager#clear(java.lang.Long)}.
	 */
	public void testClear() throws NotificationException {
		NotificationManager.clear(job.getUser().getId());
		
		assertTrue(NotificationManager.getAll(job.getId()).isEmpty());
	}

	/********************************************************
	 * 
	 * 		Clean up the test data
	 * 
	 ********************************************************/
	
	
//	public void testCleanUp() throws NotificationException {
//		NotificationManager.remove(job.getId());
//		assertTrue(NotificationManager.getAll(job.getId()).isEmpty());
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
//	}
//	
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
