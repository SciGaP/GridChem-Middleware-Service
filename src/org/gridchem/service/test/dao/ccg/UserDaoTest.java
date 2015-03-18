/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.model.User;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.crypt.SHA1;

/**
 * @author dooley
 *
 */
public class UserDaoTest extends GMSTestCase {

	private UserDao userDao;	
	
	/**
	 * @param name
	 */
	public UserDaoTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.test.GMSTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		userDao = new UserDao(session);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Test adding null user throws an exception
	 */
	public void testAddNullThrowsException() {
		
		try {
			userDao.add(null);
			assertTrue(false);
		} catch (Exception e) {
			// should not be able to add a null user
		}
	}
	
	/**
	 * Test adding test user succeeds
	 */
	public void testAdd() throws Exception {
		User user = createUser();
		user.setUsername(TEST_USERNAME + ".temp");
		userDao._add(user);
	}

	/**
	 * Test returns null on null username
	 */
	public void testGetNull() {
		assertNull(userDao.get((String)null));
	}
	
	/**
	 * Test returns null on empty username
	 */
	public void testGetEmpty() {
		assertNull(userDao.get(""));
	}
	
	/**
	 * Test returns null on invalid username
	 */
	public void testGetInvalid() {
		assertNull(userDao.get("-1"));
	}
	
	/**
	 * Test returns UserBean on valid username
	 */
	public void testGet() {
		assertNotNull(userDao.get(TEST_USERNAME));
	}
	
	/**
	 * Test returns null on null session user id
	 */
	public void testSessionGetNull() {
		Long id = session.getUserId();
		session.setUserId(null);
		userDao = new UserDao(session);
		assertNull(userDao.get());
		session.setUserId(id);
	}
	
	/**
	 * Test returns null on invalid session user id
	 */
	public void testSessionGetInvalid() {
		Long id = session.getUserId();
		session.setUserId(new Long(-1));
		userDao = new UserDao(session);
		assertNull(userDao.get());
		session.setUserId(id);
	}

	/**
	 * Test returns UserBean on valid session user id
	 */
	public void testSessionGet() {
		assertNotNull(userDao.get());
	}
	
	
//	/**
//	 * Test returns null on null username
//	 */
//	public void testGetByUsername_internalNull() {
//		assertNull(CCGUserDao.getByUsername_internal(null));
//	}
//	
//	/**
//	 * Test returns null on empty username
//	 */
//	public void testGetByUsername_internalEmpty() {
//		assertNull(CCGUserDao.getByUsername_internal(""));
//	}
//	
//	/**
//	 * Test returns null on invalid username
//	 */
//	public void testGetByUsername_internalInvalid() {
//		assertNull(CCGUserDao.getByUsername_internal("-1"));
//	}
//	
//	/**
//	 * Test returns User on valid username
//	 */
//	public void testGetByUsername_internal() {
//		assertNotNull(CCGUserDao.getByUsername_internal(username));
//	}

	
	
	/**
	 * Test update null user throws an exception
	 */
	public void testUpdateNullThrowsException() {
		
		try {
			userDao._update(null);
			assertTrue(false);
		} catch (Exception e) {
			// should not be able to add a null user
		}
	}
	
	/**
	 * Test update test user succeeds
	 */
	public void testUpdate() throws Exception {
		User user = userDao._get(TEST_USERNAME);
		user.setLastName("Updated");
		userDao._update(user);
		assertTrue(userDao.get(TEST_USERNAME).getLastName().equals("Updated"));
	}
	
	/**
	 * Test null password returns false
	 */
	public void testSessionIsPasswordValidUserIdNull() {
		assertFalse(userDao.isPasswordValid(null));
	}
	
	/**
	 * Test empty password returns false
	 */
	public void testSessionIsPasswordValidUserIdInvalid() {
		this.session.setUserId(new Long("-1"));
		userDao = new UserDao(this.session);
		assertFalse(userDao.isPasswordValid(SHA1.encrypt(TEST_PASSWORD)));
		this.session.setUserId(up.getUser().getId());
	}
	
	/**
	 * Test null password returns false
	 */
	public void testSessionIsPasswordValidPasswordNull() {
		assertFalse(userDao.isPasswordValid(null));
	}
	
	/**
	 * Test empty password returns false
	 */
	public void testSessionIsPasswordValidPasswordEmpty() {
		assertFalse(userDao.isPasswordValid(""));
	}
	
	/**
	 * Test invalid password returns false
	 */
	public void testSessionIsPasswordValidPasswordInvalid() {
		assertFalse(userDao.isPasswordValid(SHA1.encrypt("-1")));
	}

	/**
	 * Test valid password returns true
	 */
	public void testSessionIsPasswordValid() {
		assertTrue(userDao.isPasswordValid(SHA1.encrypt(TEST_PASSWORD)));
	}

	
	/**
	 * Test null password fails password test
	 */
	public void testIsPasswordValidPasswordNull() {
		assertFalse(userDao._isPasswordValid(TEST_USERNAME, null));
	}
	
	/**
	 * Test empty password fails password test
	 */
	public void testIsPasswordValidPasswordEmpty() {
		assertFalse(userDao._isPasswordValid(TEST_USERNAME, SHA1.encrypt("")));
	}
	
	/**
	 * Test invalid password fails password test
	 */
	public void testIsPasswordValidPasswordInvalid() {
		assertFalse(userDao._isPasswordValid(TEST_USERNAME, SHA1.encrypt("-1")));
	}

	/**
	 * Test null user fails password test
	 */
	public void testIsPasswordValidUsernameNull() {
		assertFalse(userDao._isPasswordValid(null, SHA1.encrypt(TEST_PASSWORD)));
	}
	
	/**
	 * Test empty user fails password test
	 */
	public void testIsPasswordValidUsernameEmpty() {
		assertFalse(userDao._isPasswordValid("", SHA1.encrypt(TEST_PASSWORD)));
	}
	
	/**
	 * Test invalid user fails password test
	 */
	public void testIsPasswordValidUsernameInvalid() {
		assertFalse(userDao._isPasswordValid("-1",SHA1.encrypt(TEST_PASSWORD)));
	}
	
	/**
	 * Test valid password returns true
	 */
	public void testIsPasswordValid() {
		assertTrue(new UserDao()._isPasswordValid(TEST_USERNAME, SHA1.encrypt(TEST_PASSWORD)));
	}
	
	/**
	 * Test remove null user throws exception
	 */
	public void testRemoveNullThrowsException() {
		
		try {
			userDao.remove(null);
			
			fail("Removing null user should throw exception");
			
		} catch (ProfileValidationException e) {
			// can't remove a null user
		}
	}
	
	/**
	 * Test removing user succeeds
	 */
	public void testRemove() throws Exception {
		
		UserBean bean = userDao.get(TEST_USERNAME + ".temp");
		userDao.remove(bean);
		
		assertNull(userDao.get(TEST_USERNAME + ".temp"));
	}
	
}
