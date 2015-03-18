/**
 * 
 */
package org.gridchem.service.test.notification;

import org.gridchem.service.authentication.ccg.CCGAuthentication;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class NotificationManagerTest extends GMSTestCase {

//	private GMSSession session;

	public void setUp() throws Exception {
		super.setUp();
		
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
	
	
	
	
	/************************************************ 
	 *
	 * 			Cleanup Records Test 
	 *
	 ************************************************/
	
//	public void testCleanup() {
//		
//		SessionManager manager = new SessionManager(session.getToken());
//		
//		SessionDao.delete(manager.getSession());
//		
//		assertNull(SessionDao.getByToken(session.getToken()));
//	}
//	
	
	/**
	 * @param x
	 */
	public NotificationManagerTest(String x) {
		super(x);
	}

	/*************************************************
	 * 
	 * 			Utility Methods
	 * 
	 *************************************************/
	 
	private AuthenticationBean createAuthenticationBean() {
		return new CCGAuthentication(TEST_USERNAME);
	}
}
