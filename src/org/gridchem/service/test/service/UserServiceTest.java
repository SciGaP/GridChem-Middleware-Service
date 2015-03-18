package org.gridchem.service.test.service;

import java.util.HashMap;
import java.util.Map;

import org.gridchem.service.UserService;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.PreferencesDao;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.PreferencesException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.impl.UserServiceImpl;
import org.gridchem.service.model.Preferences;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

public class UserServiceTest extends GMSTestCase {

//	private User user;
	private UserService service;
//	private GMSSession session;
	
	public UserServiceTest(String name) {
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
//		// persist the session
//		session = SessionDao.getByToken(TEST_SESSIONTOKEN);
//		
//		if (session == null) {
//			session = new GMSSession();
//			session.setToken(TEST_SESSIONTOKEN);
//			session.setType(AccessType.COMMUNITY);
//			CCGAuthentication auth = new CCGAuthentication(username);
//			session.setProxy(ServiceUtil.serializeGlobusCredential(auth.getCredential()));
//			session.setUserId(user.getId());
//			SessionDao.persist(session);
//		} 
//		
		service = new UserServiceImpl();
	}
	
	public void testGetProfileNullSessionKey() {
		
		try {
			service.getProfile(null);
			fail("Null session key should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetProfileEmptySessionKey() {
		
		try {
			service.getProfile("");
			fail("Empty session key should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetProfileInvalidSessionKey() {
		
		try {
			service.getProfile("-1");
			fail("Invalid session key should throw a session exception");
		} catch (SessionException e) {}
	}

	public void testGetProfile() {
		
		String sProfile = service.getProfile(TEST_SESSIONTOKEN);
		
		assertNotNull(sProfile);
		
		UserBean bean = (UserBean)ServiceUtil.xstream.fromXML(sProfile);
		
		assertNotNull(bean);
	}

	public void testUpdateProfileNullSessionKey() throws Exception {
			
		UserBean bean = user.toBean();
		bean.setEmail("wonder@boy.com");
		try {
			service.updateProfile(null, ServiceUtil.xstream.toXML(bean));
			fail("Null session key should throw a session exception");
		} catch (SessionException e) {}
		
	}
	
	public void testUpdateProfileEmptySessionKey() throws Exception {
		
		UserBean bean = user.toBean();
		bean.setEmail("wonder@boy.com");
		try {
			service.updateProfile("", ServiceUtil.xstream.toXML(bean));
			fail("Empty session key should throw a session exception");
		} catch (SessionException e) {}
		
	}
	
	public void testUpdateProfileInvalidSessionKey() throws Exception {
		
		UserBean bean = user.toBean();
		bean.setEmail("wonder@boy.com");
		try {
			service.updateProfile("-1", ServiceUtil.xstream.toXML(bean));
		} catch (SessionException e) {}
	}
	
	public void testUpdateProfileUnauthorizedUser() throws Exception {
		
		UserBean bean = user.toBean();
		bean.setId(Long.valueOf(5));
		bean.setEmail("wonder@boy.com");
		
		try {
			service.updateProfile(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(bean));
			fail("Unauthorized user should throw a session exception");
		} catch (PermissionException e) {}
		
	}

	public void testUpdateProfile() throws Exception {
		
		UserBean bean = user.toBean();
		bean.setEmail("wonder@boy.com");
		service.updateProfile(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(bean));
	}

	public void testGetPreferencesNullSessionKey() {
		
		try {
			service.getPreferences(null);
			fail("Null session key should throw a session exception");
		} catch (SessionException e) {
		} catch (PreferencesException e) {
			fail("Empty session key should throw a session exception");
		}
	}
	
	public void testGetPreferencesEmptySessionKey() {
		
		try {
			service.getPreferences("");
			fail("Empty session key should throw a session exception");
		} catch (SessionException e) {
		} catch (PreferencesException e) {
			fail("Empty session key should throw a session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetPreferences() throws Exception {
		Preferences prefs = new Preferences();
		prefs.setUser(user);
		Map<String,String> map = new HashMap<String,String>();
		map.put("junit.pref", "junit.pref");
		prefs.setPreferencesMap(map);
		PreferencesDao.persist(prefs);
		
		String sPrefs = service.getPreferences(TEST_SESSIONTOKEN);
		
		Map<String,String> sMap = (Map<String,String>)ServiceUtil.xstream.fromXML(sPrefs);
		assertNotNull(sMap);
		assertTrue(sMap.get("junit.pref").equals("junit.pref"));
		
		PreferencesDao.delete(prefs);
	}
	


}
