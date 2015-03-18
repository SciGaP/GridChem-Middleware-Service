/**
 * 
 */
package org.gridchem.service.test.authentication;

import java.util.HashMap;

import org.gridchem.service.authentication.ccg.CCGLoginProvider;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.LoginException;
import org.gridchem.service.model.User;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.crypt.SHA1;

/**
 * @author dooley
 *
 */
public class CCGLoginProviderTest extends GMSTestCase {

	public void setUp() throws Exception {
		super.setUp();
		
//		UserDao userDao = new UserDao();
//		
//		// retrieve the user from the db or create one to use.
//		User user = userDao._get(TEST_USERNAME);
//		if (user == null) {
//			user = createUser();
//			userDao._add(user);
//		}
	}
	
	public void testLoginUsernameNull() {
		try {
			CCGLoginProvider provider = new CCGLoginProvider();
			provider.login(null, TEST_PASSWORD, new HashMap<String,String>());
			
			fail("Null username should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginUsernameEmpty() {
		try {
			CCGLoginProvider provider = new CCGLoginProvider();
			provider.login("", TEST_PASSWORD, new HashMap<String,String>());
			
			fail("Empty username should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginUsernameInvalid() {
		try {
			CCGLoginProvider provider = new CCGLoginProvider();
			provider.login("-1", TEST_PASSWORD, new HashMap<String,String>());
			
			fail("Invalid username should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginPasswordNull() {
		try {
			CCGLoginProvider provider = new CCGLoginProvider();
			provider.login(TEST_USERNAME, null, new HashMap<String,String>());
			
			fail("Null password should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginPasswordEmpty() {
		try {
			CCGLoginProvider provider = new CCGLoginProvider();
			provider.login(TEST_USERNAME, "", new HashMap<String,String>());
			
			fail("Empty password should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginPasswordInvalid() {
		try {
			CCGLoginProvider provider = new CCGLoginProvider();
			provider.login(TEST_USERNAME, TEST_PASSWORD+"-1", new HashMap<String,String>());
			
			fail("Invalid password should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginAuthMapNull() {
		
		SHA1 sha = new SHA1();
		sha.init();
		sha.updateASCII(TEST_PASSWORD);
		sha.finish();
		
		CCGLoginProvider provider = new CCGLoginProvider();
		AuthenticationBean bean = provider.login(TEST_USERNAME, sha.digout(), null);
		
		assertNotNull(bean);
	}
	
	public void testLoginSucceeds() {
		
		SHA1 sha = new SHA1();
		sha.init();
		sha.updateASCII(TEST_PASSWORD);
		sha.finish();
		
		CCGLoginProvider provider = new CCGLoginProvider();
		AuthenticationBean bean = provider.login(TEST_USERNAME, sha.digout(), new HashMap<String,String>());
		
		assertNotNull(bean);
	}
	
	public void testZCleanUp() {
		UserDao udao = new UserDao();
		User user = udao._get(TEST_USERNAME);
		udao._remove(user);
		assertNull(udao._get(TEST_USERNAME));
	}
	
	/**
	 * 
	 */
	public CCGLoginProviderTest(String x) {
		super(x);
	}

}
