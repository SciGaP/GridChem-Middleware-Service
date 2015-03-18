/**
 * 
 */
package org.gridchem.service.test.managers;

import org.globus.gsi.GlobusCredential;
import org.globus.gsi.X509Credential;
import org.globus.gsi.GlobusCredentialException;
import org.globus.gsi.CredentialException;
import org.gridchem.service.authentication.ccg.CCGAuthentication;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.User;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

/**
 * @author dooley
 *
 */
public class SessionManagerTest extends GMSTestCase {

//	private GMSSession session;
//	private UserProject up;
	
	public void setUp() throws Exception {
		super.setUp();
		
//		retrieveOrAddSession();
	}
	
	/************************************************ 
	 *
	 * 			SessionManager createSession Test
	 *
	 ************************************************/
	
	public void testCreateSessionTypeNull() throws Exception {
		
		try {
			SessionManager.createSession(null, createAuthenticationBean());
			assertTrue(false);
		} catch (SessionException e) {}
	}
	
	public void testCreateSessionTypeEmpty() throws Exception {
		
		try {
			SessionManager.createSession("", null);
			assertTrue(false);
		} catch (SessionException e) {}
	}
	
	public void testCreateSessionTypeInvalid() throws Exception {
		
		try {
			SessionManager.createSession("-1", null);
			assertTrue(false);
		} catch (SessionException e) {}
	}
	
	public void testCreateSessionAuthenticationBeanNull() throws Exception {
		
		try {
			SessionManager.createSession(AccessType.COMMUNITY.name(), null);
			assertTrue(false);
		} catch (SessionException e) {}
	}
	
	public void testCreateSession() throws Exception {
		GMSSession session = SessionManager.createSession(
				AccessType.COMMUNITY.name(), new CCGAuthentication(TEST_USERNAME));
		assertNotNull(session);
		SessionDao.delete(session);
	}
	
	
	
	/************************************************ 
	 *
	 * 			SessionManager Instantiation Test
	 *
	 ************************************************/
	
	public void testInstantiateTokenNull() {
		try {
			session.setToken(null);
			new SessionManager(TEST_SESSIONTOKEN);
			fail("Null token should throw an exception");
		} catch (SessionException e) {
			session.setToken(TEST_SESSIONTOKEN);
		}
	}
	
	public void testInstantiateProxyNull() {
		String proxy = session.getProxy();
		try {
			session.setProxy(null);
			SessionDao.persist(session);
			
			new SessionManager(TEST_SESSIONTOKEN);
			fail("Null proxy should throw an exception");
		} catch (SessionException e) {
			session.setProxy(proxy);
			SessionDao.persist(session);
		}
	}
	
	public void testInstantiateProxyEmpty() {
		String proxy = session.getProxy();
		try {
			session.setProxy("");
			SessionDao.persist(session);
			
			new SessionManager(TEST_SESSIONTOKEN);
			assertTrue(false);
		} catch (SessionException e) {
			session.setProxy(proxy);
			SessionDao.persist(session);
		}
	}
	
	public void testInstantiateProxyExpired() {
		try {
			GMSSession s = SessionDao.getByToken(TEST_SESSIONTOKEN);
			// load expired credential
			//GlobusCredential cred = new GlobusCredential("test.proxy");
			X509Credential cred = new X509Credential("test.proxy");
			cred.verify();
			//s.setProxy(ServiceUtil.serializeGlobusCredential(cred));
			s.setProxy(ServiceUtil.serializeX509Credential(cred));
			SessionDao.persist(s);
			
			new SessionManager(TEST_SESSIONTOKEN);
			fail("Expired proxy should throw an exception");
		} catch (SessionException e) {
			
		//} catch (GlobusCredentialException e) { e.printStackTrace(); }
		} catch (CredentialException e) { e.printStackTrace(); }
	}
	
	/**
	 * Test a valid session is added to the database propertly.
	 */
	public void testInstantiate() throws Exception {
		
		GMSSession s = SessionDao.getByToken(TEST_SESSIONTOKEN);
		// load expired credential
		CCGAuthentication auth = new CCGAuthentication(TEST_USERNAME);
		
		//s.setProxy(ServiceUtil.serializeGlobusCredential(auth.getCredential()));
		s.setProxy(ServiceUtil.serializeX509Credential(auth.getCredential()));
		SessionDao.persist(s);
		
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		assertNotNull(manager.getSession().getId());
	}
	
	
	/************************************************ 
	 *
	 * 			SessionManager Get Session Test
	 *
	 ************************************************/
	

	public void testGetSession() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertNotNull(manager.getSession());
	}
	
	/************************************************ 
	 *
	 * 			SessionManager Get Remaining Time Test
	 *
	 ************************************************/
	
	public void testGetTimeRemainingNotNull() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		long remainingTime = manager.getRemainingTime();
		
		assertNotNull(remainingTime);
		
	}
	
	public void testGetTimeRemainingValid() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		long remainingTime = manager.getRemainingTime();

		// adjust for time to calculate time remaining
		assertTrue(remainingTime >= (session.getExpires().getTimeInMillis() - System.currentTimeMillis() - 1000));
		
	}
	
	/************************************************ 
	 *
	 * 			SessionManager Renew Test
	 *
	 ************************************************/
	
	public void testRenewSession() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		manager.renew();
		
		// adjust for time to calculate time remaining
		assertTrue(manager.getRemainingTime() >= ((long)2*60*60*1000 - 1000));
	}
	
	/************************************************ 
	 *
	 * 			SessionManager Destroy Test
	 *
	 ************************************************/
	
	public void testDestroySession() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		manager.destroy();
		
		// adjust for time to calculate time remaining
		assertTrue(manager.getRemainingTime() == 0);
		
		manager.renew();
	}
	
	
	
	/************************************************ 
	 *
	 * 			SessionManager Get User Test
	 *
	 ************************************************/
	
	public void testGetSessionUser() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertNotNull(manager.getSessionUser());
		
	}
	
	/************************************************ 
	 *
	 * 			SessionManager Get UserId Test
	 *
	 ************************************************/
	
	public void testGetSessionUserId() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertNotNull(manager.getSessionUserId());
		
	}
	
	/************************************************ 
	 *
	 * 			SessionManager Get/Set Project Test
	 *
	 ************************************************/
	
	public void testGetSessionProject() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertTrue(manager.getSessionProject().getId().equals(session.getProjectId()));
	}
	
	public void testGetSessionProjectId() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertTrue(manager.getSessionProjectId().equals(session.getProjectId()));
	}
	
	public void testSetSessionProjectNull() throws Exception {
		try {
			SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
			manager.setSessionProject(null);
			fail("Null project should throw an exception");
		} catch (PermissionException e) {}
		
	}
	
	public void testSetSessionProjectInvalid() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		try {
			manager.setSessionProject(new Long(-1));
			fail("Invalid project should throw an exception");
		} catch (PermissionException e) {}
		
	}
	
	public void testGetSessionProjectNull() throws Exception {
		Long id = session.getProjectId();
		session.setProjectId(null);
		SessionDao.persist(session);
		
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertNull(manager.getSessionProject());
		
		session.setProjectId(id);
		SessionDao.persist(session);
	}
	
	public void testGetSessionProjectIdNull() throws Exception {
		
		Long id = session.getProjectId();
		session.setProjectId(null);
		SessionDao.persist(session);
		
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertNull(manager.getSessionProjectId());
		
		session.setProjectId(id);
		SessionDao.persist(session);
		
	}
	
	public void testSetSessionProject() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		manager.setSessionProject(new ProjectDao()._get(TEST_PROJECTNAME).getId());
		
	}
	
	
	
	/************************************************ 
	 *
	 * 			SessionManager Is Session User Test
	 *
	 ************************************************/
	
	public void testIsSessionUserNull() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertFalse(manager.isSessionUser(null));
	}
	
	public void testIsSessionUserFalse() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		UserBean bean = new UserBean();
		bean.setId(new Long(-1));
		
		assertFalse(manager.isSessionUser(bean));
	}
	
	public void testIsSessionUser() throws Exception {
		SessionManager manager = new SessionManager(TEST_SESSIONTOKEN);
		
		assertTrue(manager.isSessionUser(up.getUser().toBean()));
	}
	
	
	/************************************************ 
	 *
	 * 			Cleanup Records Test 
	 *
	 ************************************************/
	
	public void testCleanup() {
		
		SessionDao.delete(session);
		assertNull(SessionDao.getByToken(TEST_SESSIONTOKEN));
		
		UserDao udao = new UserDao();
		User user = udao._get(TEST_USERNAME);
		udao._remove(user);
		assertNull(udao._get(TEST_USERNAME));
		
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao._get(TEST_PROJECTNAME);
		
		UserProjectDao.remove(up);
		assertNull(UserProjectDao.get(TEST_USERNAME, project.getId()));
	}
	
	
	/**
	 * @param x
	 */
	public SessionManagerTest(String x) {
		super(x);
	}

	/*************************************************
	 * 
	 * 			Utility Methods
	 * 
	 *************************************************/
	
//	private void retrieveOrAddSession() {
//	
//		// retrieve the user from the db or create one to use.
//		UserDao userDao = new UserDao();
//		User user = userDao._get(TEST_USERNAME);
//		if (user == null) {
//			user = createUser();
//			userDao._add(user);
//		}
//		
//		ProjectDao projectDao = new ProjectDao();
//		Project project = projectDao._get(TEST_PROJECTNAME);
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
//		session = SessionDao.getByToken(TEST_SESSIONTOKEN);
//		
//		if (session == null) {
//				
//			session = new GMSSession();
//			session.setUserId(user.getId());
//			session.setType(AccessType.COMMUNITY);
//			session.setProjectId(project.getId());
//			session.setToken(TEST_SESSIONTOKEN);
//			session.setProxy("junit.session.proxy");
//		}
//			
//		
//		
//	}
	 
	private AuthenticationBean createAuthenticationBean() {
		return new CCGAuthentication(TEST_USERNAME);
	}
}
