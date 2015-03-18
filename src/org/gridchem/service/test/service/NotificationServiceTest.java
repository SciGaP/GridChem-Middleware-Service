package org.gridchem.service.test.service;

import org.gridchem.service.NotificationService;
import org.gridchem.service.dao.NotificationDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.impl.NotificationServiceImpl;
import org.gridchem.service.model.Notification;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.NotificationType;
import org.gridchem.service.test.GMSTestCase;

public class NotificationServiceTest extends GMSTestCase {

	private NotificationService service;
	
	private Notification notif;
	
	public NotificationServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		notif = new Notification(job.toBean(),NotificationType.EMAIL,JobStatusType.FINISHED);
		
		service = new NotificationServiceImpl();
		
	}

	public void testRegisterStringStringNullSessionId() {
		try {
			service.registerDefault(null, job.getId().toString());
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testRegisterStringStringEmptySessionId() {
		try {
			service.registerDefault("", job.getId().toString());
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testRegisterStringStringInvalidSessionId() {
		try {
			service.registerDefault("-1", job.getId().toString());
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	public void testRegisterStringStringNullJobId() {
		try {
			service.registerDefault(TEST_SESSIONTOKEN, null);
			fail("Null job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null job id should throw job exception");
		}
	}
	
	public void testRegisterStringStringEmptyJobId() {
		try {
			service.registerDefault(TEST_SESSIONTOKEN, "-");
			fail("Empty job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty job id should throw job exception");
		}
	}
	
	public void testRegisterStringStringInvalidJobId() {
		try {
			service.registerDefault(TEST_SESSIONTOKEN, "-1");
			fail("Invalid job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid job id should throw job exception");
		}
	}
	
	public void testRegisterStringString() throws Exception {
		
		NotificationDao.deleteAllForUser(user.getId());
		
		assertFalse(NotificationDao.exists(notif));
		
		service.registerDefault(TEST_SESSIONTOKEN, job.getId().toString());
		
		assertTrue(NotificationDao.exists(notif));
	}

	public void testRegisterStringStringStringNullSessionId() {
		try {
			service.register(null, job.getId().toString(), NotificationType.EMAIL.name());
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testRegisterStringStringStringEmptySessionId() {
		try {
			service.register("", job.getId().toString(), NotificationType.EMAIL.name());
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testRegisterStringStringStringInvalidSessionId() {
		try {
			service.register("-1", job.getId().toString(), NotificationType.EMAIL.name());
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	public void testRegisterStringStringStringNullJobId() {
		try {
			service.register(TEST_SESSIONTOKEN, null, NotificationType.EMAIL.name());
			fail("Null job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null job id should throw job exception");
		}
	}
	
	public void testRegisterStringStringStringEmptyJobId() {
		try {
			service.register(TEST_SESSIONTOKEN, "", NotificationType.EMAIL.name());
			fail("Empty job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty job id should throw job exception");
		}
	}
	
	public void testRegisterStringStringStringInvalidJobId() {
		try {
			service.register(TEST_SESSIONTOKEN, "-1", NotificationType.EMAIL.name());
			fail("Invalid job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid job id should throw job exception");
		}
	}
	
	public void testRegisterStringStringStringNullType() {
		try {
			service.register(TEST_SESSIONTOKEN, job.getId().toString(), null);
			fail("Null notification type id should throw job exception");
		} catch (NotificationException e) {
		} catch (Exception e) {
			fail("Null notification type id should throw job exception");
		}
	}
	
	public void testRegisterStringStringStringEmptyType() {
		try {
			service.register(TEST_SESSIONTOKEN, job.getId().toString(), "");
			fail("Empty notification type id should throw job exception");
		} catch (NotificationException e) {
		} catch (Exception e) {
			fail("Empty notification type id should throw job exception");
		}
	}
	
	public void testRegisterStringStringStringInvalidType() {
		try {
			service.register(TEST_SESSIONTOKEN, job.getId().toString(), "-1");
			fail("Invalid notification type id should throw job exception");
		} catch (NotificationException e) {
		} catch (Exception e) {
			fail("Invalid notification type id should throw job exception");
		}
	}
	
	public void testRegisterStringStringString() throws Exception {
		
		NotificationDao.deleteAllForUser(user.getId());
		
		assertFalse(NotificationDao.exists(notif));
		
		service.register(TEST_SESSIONTOKEN, job.getId().toString(), NotificationType.EMAIL.name());
		
		assertTrue(NotificationDao.exists(notif));
	}

	
	public void testRemoveStringStringStringNullSessionId() {
		try {
			service.remove(null, job.getId().toString(), NotificationType.EMAIL.name());
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testRemoveStringStringStringEmptySessionId() {
		try {
			service.remove("", job.getId().toString(), NotificationType.EMAIL.name());
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testRemoveStringStringStringInvalidSessionId() {
		try {
			service.remove("-1", job.getId().toString(), NotificationType.EMAIL.name());
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	public void testRemoveStringStringStringNullJobId() {
		try {
			service.remove(TEST_SESSIONTOKEN, null, NotificationType.EMAIL.name());
			fail("Null job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null job id should throw job exception");
		}
	}
	
	public void testRemoveStringStringStringEmptyJobId() {
		try {
			service.remove(TEST_SESSIONTOKEN, "", NotificationType.EMAIL.name());
			fail("Empty job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty job id should throw job exception");
		}
	}
	
	public void testRemoveStringStringStringInvalidJobId() {
		try {
			service.remove(TEST_SESSIONTOKEN, "-1", NotificationType.EMAIL.name());
			fail("Invalid job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid job id should throw job exception");
		}
	}
	
	public void testRemoveStringStringStringNullType() {
		try {
			service.remove(TEST_SESSIONTOKEN, job.getId().toString(), null);
			fail("Null notification type id should throw job exception");
		} catch (NotificationException e) {
		} catch (Exception e) {
			fail("Null notification type id should throw job exception");
		}
	}
	
	public void testRemoveStringStringStringEmptyType() {
		try {
			service.remove(TEST_SESSIONTOKEN, job.getId().toString(), "");
			fail("Empty notification type id should throw job exception");
		} catch (NotificationException e) {
		} catch (Exception e) {
			fail("Empty notification type id should throw job exception");
		}
	}
	
	public void testRemoveStringStringStringInvalidType() {
		try {
			service.remove(TEST_SESSIONTOKEN, job.getId().toString(), "-1");
			fail("Invalid notification type id should throw job exception");
		} catch (NotificationException e) {
		} catch (Exception e) {
			fail("Invalid notification type id should throw job exception");
		}
	}
	
	public void testRemoveStringStringString() throws NumberFormatException, Exception {
		service.remove(TEST_SESSIONTOKEN, job.getId().toString(), NotificationType.EMAIL.name());
		
		Notification notif = new Notification(job.toBean(),NotificationType.EMAIL,JobStatusType.FINISHED);
		assertFalse(NotificationDao.exists(notif));
	}

	
	public void testRemoveStringStringNullSessionId() {
		try {
			service.removeForJob(null, job.getId().toString());
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testRemoveStringStringEmptySessionId() {
		try {
			service.removeForJob("", job.getId().toString());
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testRemoveStringStringInvalidSessionId() {
		try {
			service.removeForJob("-1", job.getId().toString());
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	public void testRemoveStringStringNullJobId() {
		try {
			service.removeForJob(TEST_SESSIONTOKEN, null);
			fail("Null job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null job id should throw job exception");
		}
	}
	
	public void testRemoveStringStringEmptyJobId() {
		try {
			service.removeForJob(TEST_SESSIONTOKEN, "");
			fail("Empty job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty job id should throw job exception");
		}
	}
	
	public void testRemoveStringStringInvalidJobId() {
		try {
			service.removeForJob(TEST_SESSIONTOKEN, "-1");
			fail("Invalid job id should throw job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid job id should throw job exception");
		}
	}
	
	public void testRemoveStringString() throws Exception {
		service.removeForJob(TEST_SESSIONTOKEN, job.getId().toString());
		
		Notification notif = new Notification(job.toBean(),NotificationType.EMAIL,JobStatusType.FINISHED);
		assertFalse(NotificationDao.exists(notif));
	}

	
	public void testRemoveStringNullSessionId() {
		try {
			service.removeAll(null);
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testRemoveStringEmptySessionId() {
		try {
			service.removeAll("");
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testRemoveStringInvalidSessionId() {
		try {
			service.removeAll("-1");
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	public void testRemoveString() throws NotificationException {
		service.removeAll(TEST_SESSIONTOKEN);
		
		assertTrue(NotificationDao.getAllByJobID(job.getId()).isEmpty());
	}

}
