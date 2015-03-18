package org.gridchem.service.test.service;

import java.util.Calendar;

import org.gridchem.service.SessionService;
import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.exceptions.LoginException;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.impl.SessionServiceImpl;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.crypt.SHA1;

public class SessionServiceTest extends GMSTestCase {

	private SessionService service;
//	private User user;
//	private Project project;
//	private UserProject up; 
	
	public SessionServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
//		UserDao userDao = new UserDao();
//		user = userDao._get(TEST_USERNAME);
//		if (user == null) {
//			user = createUser();
//			userDao._add(user);
//		}
//		
//		ProjectDao projectDao = new ProjectDao();
//		project = projectDao._get(TEST_PROJECTNAME);
//		if (project == null) {
//			project = createProject();
//			projectDao._add(project);
//		}
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
//		}
//		
		service = new SessionServiceImpl();
	}

	public void testGetAuthenticationTypes() {
		assertNotNull(service.getAuthenticationTypes());
	}

	public void testCreateSessionCommunityNullUsername() {
		try {
			assertNotNull(service.createSession(null, SHA1.encrypt(TEST_PASSWORD), null, AccessType.COMMUNITY.name()));
			fail("Null username should throw a permission exception");
		} catch (PermissionException e) {
		} catch (Exception e) {
			fail("Null username should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunityEmptyUsername() {
		try {
			assertNotNull(service.createSession("", SHA1.encrypt(TEST_PASSWORD), null, AccessType.COMMUNITY.name()));
			fail("Empty username should throw a permission exception");
		} catch (PermissionException e) {
		} catch (Exception e) {
			fail("Empty username should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunityInvalidUsername() {
		try {
			assertNotNull(service.createSession("-1", SHA1.encrypt(TEST_PASSWORD), null, AccessType.COMMUNITY.name()));
			fail("Invalid username should throw a permission exception");
		} catch (LoginException e) {
		} catch (Exception e) {
			fail("Invalid username should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunityNullPassword() {
		try {
			assertNotNull(service.createSession(TEST_USERNAME, null, null, AccessType.COMMUNITY.name()));
			fail("Null password should throw a permission exception");
		} catch (PermissionException e) {
		} catch (Exception e) {
			fail("Null password should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunityEmptyPassword() {
		try {
			assertNotNull(service.createSession(TEST_USERNAME, "", null, AccessType.COMMUNITY.name()));
			fail("Empty password should throw a permission exception");
		} catch (PermissionException e) {
		} catch (Exception e) {
			fail("Empty password should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunityInvalidPassword() {
		try {
			assertNotNull(service.createSession(TEST_USERNAME, "-1", null, AccessType.COMMUNITY.name()));
			fail("Invalid password should throw a permission exception");
		} catch (LoginException e) {
		} catch (Exception e) {
			fail("Invalid password should throw a permission exception");
		}
	}

	public void testCreateSessionCommunityNullAccessType() {
		try {
			assertNotNull(service.createSession(TEST_USERNAME, SHA1.encrypt(TEST_PASSWORD), null, null));
			fail("Null access type should throw a permission exception");
		} catch (ProviderException e) {
		} catch (Exception e) {
			fail("Null access type should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunityEmptyAccessType() {
		try {
			assertNotNull(service.createSession(TEST_USERNAME, SHA1.encrypt(TEST_PASSWORD), null, ""));
			fail("Empty access type should throw a permission exception");
		} catch (ProviderException e) {
		} catch (Exception e) {
			fail("Empty access type should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunityInvalidAccessType() {
		try {
			assertNotNull(service.createSession(TEST_USERNAME, SHA1.encrypt(TEST_PASSWORD), null, "-1"));
			fail("Invalid access type should throw a permission exception");
		} catch (ProviderException e) {
		} catch (Exception e) {
			fail("Invalid access type should throw a permission exception");
		}
	}
	
	public void testCreateSessionCommunity() throws PermissionException, SessionException, ProviderException, ParameterException {
		String key = service.createSession(TEST_USERNAME, SHA1.encrypt(TEST_PASSWORD), null, AccessType.COMMUNITY.name());
		
		assertTrue(ServiceUtil.isValid(key));
		
		// make sure it was created, then update the token so we can pull it for the rest of the tests.
		GMSSession session = SessionDao.getByToken(key);
		assertNotNull(session);
		SessionDao.delete(session);
	}
	
	public void testRenewSessionNullSessionToken() {
		try {
			service.renewSession(null);
			fail("Null session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session id should throw a session exception");
		}
	}
	
	public void testRenewSessionEmptySessionToken() {
		try {
			service.renewSession("");
			fail("Empty session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session id should throw a session exception");
		}
	}
	
	public void testRenewSessionInvalidSessionToken() {
		try {
			service.renewSession("-1");
			fail("Invalid session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session id should throw a session exception");
		}
	}
	
	public void testRenewSession() {
		
		Calendar cal = SessionDao.getByToken(TEST_SESSIONTOKEN).getExpires();
		
		service.renewSession(TEST_SESSIONTOKEN);
		
		assertTrue(SessionDao.getByToken(TEST_SESSIONTOKEN).getExpires().after(cal));
		
	}

	public void testGetRemainingTimeNullSessionToken() {
		try {
			service.getRemainingTime(null);
			fail("Null session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session id should throw a session exception");
		}
	}
	
	public void testGetRemainingTimeEmptySessionToken() {
		try {
			service.getRemainingTime("");
			fail("Empty session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session id should throw a session exception");
		}
	}
	
	public void testGetRemainingTimeInvalidSessionToken() {
		try {
			service.getRemainingTime("-1");
			fail("Invalid session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session id should throw a session exception");
		}
	}
	
	
	public void testGetRemainingTime() {
		Calendar daoCal = SessionDao.getByToken(TEST_SESSIONTOKEN).getExpires();
		
		long daoRemaining = daoCal.getTimeInMillis() - System.currentTimeMillis();
		long msRemaining = Long.parseLong(service.getRemainingTime(TEST_SESSIONTOKEN));
		
		assertTrue((daoRemaining - msRemaining) < 10000);
	}

	public void testSetSessionProjectNullSessionToken() {
		try {
			service.setSessionProject(null,project.getId().toString());
			fail("Null session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session id should throw a session exception");
		}
	}
	
	public void testSetSessionProjectEmptySessionToken() {
		try {
			service.setSessionProject("",project.getId().toString());
			fail("Empty session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session id should throw a session exception");
		}
	}
	
	public void testSetSessionProjectInvalidSessionToken() {
		try {
			service.setSessionProject("-1",project.getId().toString());
			fail("Invalid session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session id should throw a session exception");
		}
	} 
	
	public void testSetSessionProjectNullProjectId() {
		try {
			service.setSessionProject(TEST_SESSIONTOKEN,null);
			fail("Null project id should throw a session exception");
		} catch (ProjectException e) {
		} catch (Exception e) {
			fail("Null project id should throw a session exception");
		}
	}
	
	public void testSetSessionProjectEmptyProjectId() {
		try {
			service.setSessionProject(TEST_SESSIONTOKEN,"");
			fail("Empty project id should throw a session exception");
		} catch (ProjectException e) {
		} catch (Exception e) {
			fail("Empty project id should throw a session exception");
		}
	}
	
	public void testSetSessionProjectInvalidProjectId() {
		try {
			service.setSessionProject(TEST_SESSIONTOKEN, "-1");
			fail("Invalid project id should throw a session exception");
		} catch (PermissionException e) {
		} catch (Exception e) {
			fail("Invalid project id should throw a session exception");
		}
	} 
	
	public void testSetSessionProject() throws PermissionException, SessionException, ProviderException {
		service.setSessionProject(TEST_SESSIONTOKEN, project.getId().toString());
		
		assertTrue(SessionDao.getByToken(TEST_SESSIONTOKEN).getProjectId().equals(project.getId()));
	}

	public void testDestroySessionNullSessionToken() {
		try {
			service.destroySession(null);
			fail("Null session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session id should throw a session exception");
		}
	}
	
	public void testDestroySessionEmptySessionToken() {
		try {
			service.destroySession("");
			fail("Empty session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session id should throw a session exception");
		}
	}
	
	public void testDestroySessionInvalidSessionToken() {
		try {
			service.destroySession("-1");
			fail("Invalid session id should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session id should throw a session exception");
		}
	}
	
	public void testDestroySession() {
		service.destroySession(TEST_SESSIONTOKEN);
		
		assertNotNull(SessionDao.getByToken(TEST_SESSIONTOKEN).getDestroyed());
	}

}
