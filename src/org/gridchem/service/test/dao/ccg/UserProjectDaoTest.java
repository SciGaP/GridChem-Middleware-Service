package org.gridchem.service.test.dao.ccg;

import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.test.GMSTestCase;

public class UserProjectDaoTest extends GMSTestCase {
	
//	private UserProject up;
	
	public UserProjectDaoTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
//		
//		UserDao udao = new UserDao();
//		
//		// retrieve the user from the db or create one to use.
//		User user = udao._get(TEST_USERNAME);
//		if (user == null) {
//			user = createUser();
//			udao._add(user);
//		}
//		
//		ProjectDao pdao = new ProjectDao();
//		Project project = new ProjectDao()._get(TEST_PROJECTNAME);
//		if (project == null) {
//			project = createProject();
//			pdao._add(project);
//		}
//		
//		up = new UserProject(user,project);
	}

	public void testAddUserProjectNull() {
		try {
			UserProjectDao.add(null);
			fail("Null UP should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testAddUserProjectNullUser() {
		try {
			up.setUser(null);
			UserProjectDao.add(up);
			fail("Null user should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testAddUserProjectNullProject() {
		try {
			up.setProject(null);
			UserProjectDao.add(up);
			fail("Null user should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testAdd() {
		UserProjectDao.add(up);
	}

	public void testAddUserProjectExists() {
		try {
			up.setProject(null);
			UserProjectDao.add(up);
			fail("Duplicate User Project should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testGetUsernameNull() {
		try {
			UserProjectDao.get(null, up.getProject().getId());
			fail("Null user should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testGetUsernameEmpty() {
		try {
			UserProjectDao.get("", up.getProject().getId());
			fail("Empty username should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testGetUsernameInvalid() {
		try {
			UserProjectDao.get("-1", up.getProject().getId());
			fail("Invalid user should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testGetProjectIdNull() {
		try {
			UserProjectDao.get(TEST_USERNAME, null);
			fail("Null project should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testGetProjectIdInvalid() {
		try {
			UserProjectDao.get(null, new Long(-1));
			fail("Invalid project should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testGet() {
		assertNotNull(UserProjectDao.get(TEST_USERNAME, up.getProject().getId()));
	}
	
	public void testUpdateNull() {
		try {
			UserProjectDao.update(null);
			fail("Null up should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testUpdate() {
		UserProject old = UserProjectDao.get(TEST_USERNAME, up.getProject().getId());
		old.setEnabled(false);
		UserProjectDao.update(old);
		UserProject newup = UserProjectDao.get(TEST_USERNAME, up.getProject().getId());
		
		assertFalse(newup.isEnabled());
	}

	public void testExistsUserProjectNull() {
		try {
			UserProjectDao.exists(null);
			fail("Null up should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testExistsUserProject() {
		assertTrue(UserProjectDao.exists(up));
	}

	public void testExistsStringLongUsernameNull() {
		try {
			UserProjectDao.exists(null,up.getProject().getId());
			fail("Null username should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testExistsStringLongUsernameEmpty() {
		try {
			UserProjectDao.exists("",up.getProject().getId());
			fail("Empty username should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testExistsStringLongUsernameInvalid() {
		try {
			UserProjectDao.exists("-1",up.getProject().getId());
			fail("Invalid username should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testExistsStringLongProjectIdNull() {
		try {
			UserProjectDao.exists(TEST_USERNAME, null);
			fail("Null project id should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testExistsStringLongProjectIdInvalid() {
		try {
			UserProjectDao.exists(TEST_USERNAME, new Long(-1));
			fail("Invalid project id should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testExistsStringLong() {
		assertTrue(UserProjectDao.exists(TEST_USERNAME, up.getProject().getId()));
	}
	
	public void testRemoveNull() {
		try {
			UserProjectDao.remove(null);
			fail("Null project id should throw an exception.");
		} catch (UserException e) {}
	}
	
	public void testRemove() {
		UserProjectDao.remove(up);
		assertFalse(UserProjectDao.exists(up));
	}

//	public void testZCleanUp() {
//		UserDao udao = new UserDao();
//		User user = udao._get(TEST_USERNAME);
//		udao._remove(user);
//		assertNull(udao._get(TEST_USERNAME));
//		
//		ProjectDao projectDao = new ProjectDao();
//		Project project = projectDao._get(TEST_PROJECTNAME);
//		projectDao._remove(project);
//		assertNull(projectDao._get(TEST_PROJECTNAME));
//	}
	

}
