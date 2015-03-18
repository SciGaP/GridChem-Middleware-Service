/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class SessionDaoTest extends GMSTestCase {
	
	public void setUp() throws Exception {
		super.setUp();
	}
	
	
	/************************************************ 
	 *
	 * 			Session Persistence Test
	 *
	 ************************************************/
	
	public void testPersistTokenNull() {
		GMSSession session = createSession(user, project, false);
		try {
			session.setToken(null);
			SessionDao.persist(session);
			fail("Null session token should throw a session exception.");
		} catch (SessionException e) {
			
		}
	}
	
	public void testPersistUserIdNull() {
		GMSSession session = createSession(user, project, false);
		try {
			session.setUserId(null);
			SessionDao.persist(session);
			fail("Null session user id should throw a session exception.");
		} catch (SessionException e) {
			
		}
	}
	
//	public void testPersistProjectIdNull() {
//		try {
//			session.setProjectId(null);
//			SessionDao.persist(session);
//			fail("Null project id should throw an exception");
//		} catch (SessionException e) {
//			
//		}
//	}
	
	public void testPersistExpiresNull() {
		GMSSession session = createSession(user, project, false);
		try {
			session.setExpires(null);
			SessionDao.persist(session);
			fail("Null expires value should throw a session exception.");
		} catch (SessionException e) {
			
		}
	}
	
	public void testPersistDestroysNullAllowed() {
		session.setDestroyed(null);
		SessionDao.persist(session);
	}
	
	public void testPersistCreatedNull() {
		GMSSession session = createSession(user, project, false);
		try {
			session.setCreated(null);
			SessionDao.persist(session);
			fail("Null created value should throw a session exception.");
		} catch (SessionException e) {
			
		}
	}
	
	/**
	 * Test a valid session is added to the database propertly.
	 */
	public void testPersist() throws Exception {
		SessionDao.persist(session);
		assertNotNull(session.getId());
	}
	
	/************************************************ 
	 *
	 * 			Get Session By Token Test 
	 *
	 ************************************************/
	
	public void testGetByTokenNull() throws Exception {
		assertNull(SessionDao.getByToken(null));
	}
	
	public void testGetByTokenEmpty() throws Exception {
		assertNull(SessionDao.getByToken(""));
	}
	
	public void testGetByTokenInvalid() throws Exception {
		assertNull(SessionDao.getByToken("-1"));
	}
	
	public void testGetByToken() throws Exception {
		assertNotNull(SessionDao.getByToken(session.getToken()));
	}
	
	
	/************************************************ 
	 *
	 * 			Session Uniqueness Test 
	 *
	 ************************************************/
	
	public void testIsUniqueDuplicate() throws Exception {
		
		assertFalse(SessionDao.isUnique(session.getToken()));
	}

	public void testIsUnique() throws Exception {
		
		assertTrue(SessionDao.isUnique("-1"));
				
	}
	
	public void testIsUniqueNull() throws Exception {
		try {
			SessionDao.isUnique(null);
			fail("Null session key value should throw a session exception.");
		} catch (Exception e) {
			
		}
				
	}
	
	/************************************************ 
	 *
	 * 			Delete Job Test 
	 *
	 ************************************************/
	
	public void testDeleteNull() {
		
		try {
			SessionDao.delete(null);
			SessionDao.persist(session);
			fail("Null session key value should throw a session exception.");
		} catch (SessionException e) {
			
		}
	}
	
	public void testDelete() throws Exception {
		SessionDao.delete(session);
	}
	
//	public void testZCleanUp() throws Exception {
//		UserDao udao = new UserDao();
//		User user = udao._get(TEST_USERNAME);
//		udao._remove(user);
//		
//		ProjectDao projectDao = new ProjectDao();
//		Project project = projectDao._get(TEST_PROJECTNAME);
//		projectDao._remove(project);
//	}
	
	/**
	 * @param x
	 */
	public SessionDaoTest(String x) {
		super(x);
	}

//	/*************************************************
//	 * 
//	 * 			Utility Methods
//	 * @throws ProfileValidationException 
//	 * @throws InfrastructureException 
//	 * 
//	 *************************************************/
//	
//	private void createSession() throws InfrastructureException, ProfileValidationException {
//		
//		session = SessionDao.getByToken(TEST_SESSIONTOKEN);
//		
//		if (session == null) {
//			
//			// retrieve the user from the db or create one to use.
//			UserDao userDao = new UserDao();
//			User user = userDao._get(TEST_USERNAME);
//			if (user == null) {
//				user = createUser();
//				userDao._add(user);
//			}
//			
//			ProjectDao projectDao = new ProjectDao();
//			Project project = projectDao._get(TEST_PROJECTNAME);
//			if (project == null) {
//				project = createProject();
//				projectDao._add(project);
//			}
//			
//			session = new GMSSession();
//			session.setUserId(user.getId());
//			session.setType(AccessType.COMMUNITY);
//			session.setProjectId(project.getId());
//			session.setToken(TEST_SESSIONTOKEN);
//			session.setProxy("junit.session.proxy");
//			
//		}
//		
//	}

//	public GMSSession createSession(User user, Project project) {
//		GMSSession session = new GMSSession();
//		session.setType(AccessType.COMMUNITY);
//		session.setProjectId(project.getId());
//		session.setUserId(user.getId());
//		session.setToken(TEST_SESSIONTOKEN);
//		session.setProxy("junit.session.proxy");
//		
//		return session;
//	}
}
