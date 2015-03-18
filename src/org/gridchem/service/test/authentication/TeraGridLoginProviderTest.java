/**
 * 
 */
package org.gridchem.service.test.authentication;

import java.util.HashMap;

import org.gridchem.service.authentication.teragrid.TeraGridLoginProvider;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.exceptions.LoginException;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class TeraGridLoginProviderTest extends GMSTestCase {

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
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			provider.login(null, TEST_MYPROXY_PASSWORD, new HashMap<String,String>());
			
			fail("Null username should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginUsernameEmpty() {
		try {
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			provider.login("", TEST_MYPROXY_PASSWORD, new HashMap<String,String>());
			
			fail("Empty username should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginUsernameInvalid() {
		try {
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			provider.login(TEST_USERNAME+"-1", TEST_MYPROXY_PASSWORD, new HashMap<String,String>());
			
			fail("Invalid username should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginPasswordNull() {
		try {
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			provider.login(TEST_MYPROXY_USERNAME, null, new HashMap<String,String>());
			
			fail("Null password should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginPasswordEmpty() {
		try {
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			provider.login(TEST_MYPROXY_USERNAME, "", new HashMap<String,String>());
			
			fail("Empty password should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginPasswordInvalid() {
		try {
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			provider.login(TEST_MYPROXY_USERNAME, TEST_PASSWORD+"-1", new HashMap<String,String>());
			
			fail("Invalid password should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginAuthMapNull() {
		
		try {
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			provider.login(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD, null);
		
			fail("Null auth map should throw an exception");
		} catch (LoginException e) {}
	}
	
	public void testLoginAuthMapEmpty() {
		
		HashMap<String,String> authMap = new HashMap<String,String>();
		
		TeraGridLoginProvider provider = new TeraGridLoginProvider();
		try {
			provider.login(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD, authMap);
			fail("Empty auth map should throw an exception");
		} catch (LoginException e) {}
		
	}

	public void testLoginMyProxyServerInvalid() {
		
		HashMap<String,String> authMap = new HashMap<String,String>();
		authMap.put("myproxy.server", "example.com");
		authMap.put("myproxy.port", "7511");
		
		TeraGridLoginProvider provider = new TeraGridLoginProvider();
		try {
			provider.login(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD, authMap);
			fail("Invalid myproxy server should throw an exception");
		} catch (LoginException e) {}
		
	}
	
	public void testLoginMyProxyPortInvalid() {
		
		HashMap<String,String> authMap = new HashMap<String,String>();
		authMap.put("myproxy.server", "myproxy.teragrid.org");
		authMap.put("myproxy.port", "7510");
		
		TeraGridLoginProvider provider = new TeraGridLoginProvider();
		
		try {
			provider.login(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD, authMap);
			fail("Invalid myproxy port should throw an exception");
		} catch (LoginException e) {}
		
	}
	
	public void testLoginSucceeds() {
		
		HashMap<String,String> authMap = new HashMap<String,String>();
		authMap.put("myproxy.server", "myproxy.teragrid.org");
		authMap.put("myproxy.port", "7512");
		
		TeraGridLoginProvider provider = new TeraGridLoginProvider();
		AuthenticationBean bean = provider.login(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD, authMap);
		
		assertNotNull(bean);
	}

//	public void testZCleanUp() {
//		UserDao udao = new UserDao();
//		User user = udao._get(TEST_USERNAME);
//		udao._remove(user);
//		assertNull(udao._get(TEST_USERNAME));
//	}
	
	/**
	 * 
	 */
	public TeraGridLoginProviderTest(String x) {
		super(x);
	}

}
