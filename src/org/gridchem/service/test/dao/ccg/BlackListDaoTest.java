/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import org.gridchem.service.beans.BlackListBean;
import org.gridchem.service.dao.BlackListDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.BlackListException;
import org.gridchem.service.model.User;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class BlackListDaoTest extends GMSTestCase {

//	private BlackListEntry blackList;
	private BlackListBean bean;
	private BlackListDao blackListDao;
	
	public void setUp() throws Exception {
		super.setUp();
		
//		UserDao udao = new UserDao();
//		
//		// retrieve the user from the db or create one to use.
//		User user = udao._get(TEST_USERNAME);
//		if (user == null) {
//			user = createUser();
//			udao._add(user);
//		}
//		
//		Date date = new Date();
//		entry = new BlackListEntry();
//		entry.setUser(new UserDao()._get(TEST_USERNAME));
//		entry.setSoftware(new SoftwareDao()._get(TEST_SOFTWARENAME));
//		entry.setId(new BlackListEntry.Id(entry.getUser().getId(), entry.getSoftware().getId()));
//		entry.setCreated(date);
//		entry.setEnabled(true);
//		entry.setLastUpdated(date);
//		
		bean = blackList.toBean();
		
		blackListDao = new BlackListDao();
	}
	
	/** Add Bean **/
	
	public void testAddBeanUsernameNull() {
		
		try {
			bean.setUsername(null);
			blackListDao.add(bean);
			fail("Adding entry with null username should fail.");
		} catch (BlackListException e) {
			
		}
	}
	
	public void testAddBeanUsernameEmpty() {
		try {
			bean.setUsername("");
			blackListDao.add(bean);
			fail("Adding entry with emtpy username should fail.");
		} catch (BlackListException e) {
			
		}
	}
	
	public void testAddBeanUsernameInvalid() {
		try {
			bean.setUsername("-1");
			blackListDao.add(bean);
			fail("Adding entry with invalid software should fail.");
		} catch (BlackListException e) {
			
		}
	}

	public void testAddBeanSoftwareNull() {
		try {
			bean.setSoftware(null);
			blackListDao.add(bean);
			fail("Adding entry with null software should fail.");
		} catch (BlackListException e) {
			
		}
	}
	
	public void testAddBeanSoftwareEmpty() {
		try {
			bean.setSoftware("");
			blackListDao.add(bean);
			fail("Adding entry with emtpy user should fail.");
		} catch (BlackListException e) {
			
		}
	}
	
	public void testAddBeanSoftwareInvalid() {
		try {
			bean.setSoftware("-1");
			blackListDao.add(bean);
			fail("Adding entry with invalid software user should fail.");
		} catch (BlackListException e) {
			
		}
	}
	
	public void testAddBean() {
		blackListDao.add(bean);
	}
	
	/** Add Reference Object **/
	
	public void testAddEntryUserNull() {
		
		try {
			blackList.setUser(null);
			blackListDao._add(blackList);
			fail("Adding entry with null user should fail.");
		} catch (BlackListException e) {
			
		}
	}
	
	public void testAddEntrySoftwareNull() {
		try {
			blackList.setSoftware(null);
			blackListDao._add(blackList);
			fail("Adding entry with null software should fail.");
		} catch (BlackListException e) {
			
		}
	}
	
	public void testAddDuplicateEntry() {
		try {
			blackListDao._add(blackList);
			fail("Adding duplicate entry should fail.");
		} catch (BlackListException e) {}
	}
	
	
	/**
	 * Get BlackList Tests
	 */
	
	/** Get Bean **/
	
	public void testGetBeanUsernameNull() {
		
		assertNull(blackListDao.get(null, bean.getSoftware()));
	}
	
	public void testGetBeanUsernameEmpty() {
		
		assertNull(blackListDao.get("", bean.getSoftware()));
	}
	
	public void testGetBeanUsernameInvalid() {
		
		assertNull(blackListDao.get("-1", bean.getSoftware()));
	}

	public void testGetBeanSoftwareNull() {
		
		assertNull(blackListDao.get(bean.getUsername(), null));
	}
	
	public void testGetBeanSoftwareEmpty() {
		
		assertNull(blackListDao.get(bean.getUsername(), ""));
	}
	
	public void testGetBeanSoftwareInvalid() {
		
		assertNull(blackListDao.get(bean.getUsername(),"-1"));
	}
	
	public void testGetBean() {
		
		assertNotNull(blackListDao.get(bean.getUsername(),bean.getSoftware()));
	}
	
	/** Get Reference Object **/
	
	public void testGetEntryUserNull() {
		
		assertNull(blackListDao._get(null, blackList.getSoftware().getId()));
	}
	
	public void testGetEntrySoftwareNull() {
		
		assertNull(blackListDao._get(blackList.getUser().getId(), null));
	}
	
	public void testGetEntry() {
		assertNotNull(blackListDao._get(blackList.getUser().getId(), blackList.getSoftware().getId()));
	}
	
	/**
	 * Get All BlackList Tests
	 */
	
	/** Get All Bean **/
	
	public void testGetAllBeanUsernameNull() {
		
		assertTrue(blackListDao.get(null).isEmpty());
	}
	
	public void testGetAllBeanUsernameEmpty() {
		
		assertTrue(blackListDao.get("").isEmpty());
	}
	
	public void testGetAllBeanUsernameInvalid() {
		
		assertTrue(blackListDao.get("-1").isEmpty());
	}
	
	public void testGetAllBean() {
		
		assertFalse(blackListDao.get(bean.getUsername()).isEmpty());
	}
	
	/** Get All Reference Object **/
	
	public void testGetAllEntryUserIdNull() {
		
		assertTrue(blackListDao._get(null).isEmpty());
	}
	
	public void testGetAllEntry() {
		assertFalse(blackListDao._get(blackList.getUser().getId()).isEmpty());
	}
	
	/**
	 * User Has Access To BlackList tests
	 */
	
	/** Update Bean **/
	
	public void testUserHasAccessToSoftwareUsernameEmpty() {
		try {
			BlackListDao.userHasAccessToSoftware("", bean.getSoftware());
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUserHasAccessToSoftwareUsernameInvalid() {
		try {
			BlackListDao.userHasAccessToSoftware("-1", bean.getSoftware());
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}

	public void testUserHasAccessToSoftwareSoftwareNull() {
		try {
			BlackListDao.userHasAccessToSoftware(bean.getUsername(), null);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUserHasAccessToSoftwareSoftwareEmpty() {
		try {
			BlackListDao.userHasAccessToSoftware(bean.getUsername(), "");
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUserHasAccessToSoftwareSoftwareInvalid() {
		try {
			BlackListDao.userHasAccessToSoftware(bean.getUsername(), "-1");
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUserHasAccessToSoftwareEnabled() {
		// entry in the table means user does not have access
		assertFalse(BlackListDao.userHasAccessToSoftware(bean.getUsername(), bean.getSoftware()));
	}
	
	/**
	 * Update BlackList tests
	 */
	
	/** Update Bean **/
	
	public void testUpdateBeanUsernameNull() {
		
		try {
			bean.setUsername(null);
			blackListDao.update(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUpdateBeanUsernameEmpty() {
		try {
			bean.setUsername("");
			blackListDao.update(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUpdateBeanUsernameInvalid() {
		try {
			bean.setUsername("-1");
			blackListDao.update(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}

	public void testUpdateBeanSoftwareNull() {
		try {
			bean.setSoftware(null);
			blackListDao.update(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUpdateBeanSoftwareEmpty() {
		try {
			bean.setSoftware("");
			blackListDao.update(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUpdateBeanSoftwareInvalid() {
		try {
			bean.setSoftware("-1");
			blackListDao.update(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUpdateBean() {
		bean.setEnabled(false);
		blackListDao.update(bean);
	}
	
	/** Update Reference Object **/
	
	public void testUpdateEntryUserNull() {
		
		try {
			blackList.setUser(null);
			blackListDao._update(blackList);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUpdateEntrySoftwareNull() {
		try {
			blackList.setSoftware(null);
			blackListDao._update(blackList);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testUpdateEntry() {
		blackList = blackListDao._get(blackList.getUser().getId(), blackList.getSoftware().getId());
		blackList.setEnabled(false);
		
		blackListDao._update(blackList);
	}  
	

	/**
	 * User Has Access To BlackList tests
	 */
	
	public void testUserHasAccessToSoftwareDisabled() {
		// entry is present, but set to disabled.  Access granted
		assertTrue(BlackListDao.userHasAccessToSoftware(bean.getUsername(), bean.getSoftware()));
	}
	
	/**
	 * Remove BlackList Tests
	 */
	
	/** Remove Bean **/
	
	public void testRemoveBeanUsernameNull() {
		
		try {
			bean.setUsername(null);
			blackListDao.remove(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testRemoveBeanUsernameEmpty() {
		try {
			bean.setUsername("");
			blackListDao.remove(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testRemoveBeanUsernameInvalid() {
		try {
			bean.setUsername("-1");
			blackListDao.remove(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}

	public void testRemoveBeanSoftwareNull() {
		try {
			bean.setSoftware(null);
			blackListDao.remove(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testRemoveBeanSoftwareEmpty() {
		try {
			bean.setSoftware("");
			blackListDao.remove(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testRemoveBeanSoftwareInvalid() {
		try {
			bean.setSoftware("-1");
			blackListDao.remove(bean);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testRemoveBean() {
		blackListDao.remove(bean);
	}
	
	/** Remove Reference Object **/
	
	public void testRemoveEntryUserNull() {
		
		try {
			blackList.setUser(null);
			blackListDao._remove(blackList);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testRemoveEntrySoftwareNull() {
		try {
			blackList.setSoftware(null);
			blackListDao._remove(blackList);
			assertTrue(false);
		} catch (BlackListException e) {
			
		}
	}
	
	public void testRemoveEntry() {
		blackListDao._remove(blackList);
	}
	
	/**
	 * User Has Access To BlackList tests
	 */
	
	public void testUserHasAccessToSoftwareNotPresent() {
		// entry is missing.  Access granted by default
		assertTrue(BlackListDao.userHasAccessToSoftware(bean.getUsername(), bean.getSoftware()));
	}
	
	public void testZCleanup() {
		UserDao udao = new UserDao();
		User user = udao._get(TEST_USERNAME);
		udao._remove(user);
		assertNull(udao._get(TEST_USERNAME));
	}
	
	/**
	 * @param x
	 */
	public BlackListDaoTest(String x) {
		super(x);
	}

}
