/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import org.gridchem.service.dao.SoftwareDao;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class SoftwareDaoTest extends GMSTestCase {

	private SoftwareDao softwareDao;
	
	/**
	 * Test null returned for null software name
	 */
	public void testGetSoftwareNull() {
		assertNull(softwareDao.get(null));
	}
	
	/**
	 * Test null returned for emtpy software name
	 */
	public void testGetSoftwareEmpty() {
		assertNull(softwareDao.get(""));
	}
	
	/**
	 * Test null returned for invalid software name
	 */
	public void testGetSoftwareInvalid() {
		assertNull(softwareDao.get("-1"));
	}
	
	/**
	 * Test valid softwarebean returned for valid software name
	 */
	public void testGetSoftware() {
		assertNotNull(softwareDao.get(TEST_SOFTWARENAME));
	}
	
	/**
	 * Test empty softwarebean list returned for invalid session project
	 */
	public void testSessionGetSoftwareProjectNull() {
		Long id = session.getProjectId();
		session.setProjectId(null);
		softwareDao = new SoftwareDao(session);
		assertTrue(softwareDao.get().isEmpty());
		session.setProjectId(id);
	}
	
	/**
	 * Test null returned for emtpy software name
	 */
	public void testSessionGetSoftwareProjectInvalid() {
		Long id = session.getProjectId();
		session.setProjectId(new Long(-1));
		softwareDao = new SoftwareDao(session);
		assertTrue(softwareDao.get().isEmpty());
		session.setProjectId(id);
	}
	
	/**
	 * Test a nonempty list of softwarebeans is returned for the session project
	 */
	public void testSessionGetSoftware() {
		assertFalse(softwareDao.get().isEmpty());
	}
	
	/**
	 * Test an empty list of softwarebeans is returned for a null session project
	 */
	public void testSessionGetInstallationsProjectNull() {
		Long id = session.getProjectId();
		session.setProjectId(null);
		softwareDao = new SoftwareDao(session);
		assertTrue(softwareDao.getInstallations().isEmpty());
		session.setProjectId(id);
	}
	
	/**
	 * Test an empty list of softwarebeans is returned for an invalid session project
	 */
	public void testSessionGetInstallationsProjectInvalid() {
		Long id = session.getProjectId();
		session.setProjectId(new Long(-1));
		softwareDao = new SoftwareDao(session);
		assertTrue(softwareDao.getInstallations().isEmpty());
		session.setProjectId(id);
	}
	
	/**
	 * Test a nonempty list of softwarebeans is returned for the session project
	 */
	public void testSessionGetInstallations() {
		assertFalse(softwareDao.getInstallations().isEmpty());
	}
	
	/**
	 * Test returns an empty list of softwareinstallationbeans is returned for a null resource name
	 */
	public void testSessionGetInstallationsForResourceNull() {
		assertTrue(softwareDao.getInstallationsForResource(null).isEmpty());
	}
	
	/**
	 * Test returns an empty list of softwareinstallationbeans is returned for an empty resource name
	 */
	public void testSessionGetInstallationsForResourceEmtpy() {
		assertTrue(softwareDao.getInstallationsForResource(null).isEmpty());
	}
	
	/**
	 * Test returns an empty list of softwareinstallationbeans is returned for an invalid resource name
	 */
	public void testSessionGetInstallationsForResourceInvalid() {
		assertTrue(softwareDao.getInstallationsForResource(null).isEmpty());
	}
	
	/**
	 * Test returns a non-empty list of softwareinstallationbeans is returned for a valid resource name
	 */
	public void testSessionGetInstallationsForResource() {
		assertFalse(softwareDao.getInstallationsForResource(TEST_SYSTEMNAME).isEmpty());
	}
	
	/**
	 * Test returns an empty list of softwareinstallationbeans is returned for a null software name
	 */
	public void testSessionGetInstallationsForSoftwareNull() {
		assertTrue(softwareDao.getInstallationsForSoftware(null).isEmpty());
	}
	
	/**
	 * Test returns an empty list of softwareinstallationbeans is returned for an empty software name
	 */
	public void testSessionGetInstallationsForSoftwareEmtpy() {
		assertTrue(softwareDao.getInstallationsForSoftware(null).isEmpty());
	}
	
	/**
	 * Test returns an empty list of softwareinstallationbeans is returned for an invalid software name
	 */
	public void testSessionGetInstallationsForSoftwareInvalid() {
		assertTrue(softwareDao.getInstallationsForSoftware(null).isEmpty());
	}
	
	/**
	 * Test returns a non-empty list of softwareinstallationbeans is returned for a valid software name
	 */
	public void testSessionGetInstallationsForSoftware() {
		assertFalse(softwareDao.getInstallationsForSoftware(TEST_SOFTWARENAME).isEmpty());
	}
	
	/**
	 * Test false for null software package
	 */
	public void testUserHasAccessNull() {
		assertFalse(softwareDao.userHasAccess(null));
	}
	
	/**
	 * Test false for empty software package
	 */
	public void testUserHasAccessEmtpy() {
		assertFalse(softwareDao.userHasAccess(""));
	}
	
	/**
	 * Test false for invalid software package
	 */
	public void testUserHasAccessInvalid() {
		assertFalse(softwareDao.userHasAccess("-1"));
	}
	
	/**
	 * Test true for a software package to which the user has access
	 */
	public void testUserHasAccess() {
		assertTrue(softwareDao.userHasAccess(TEST_SOFTWARENAME));
	}
	
//	public void testCleanUp() throws SoftwareException, NotificationException, WorkflowException {
//		super.testTearDown();
////		
////		UserDao udao = new UserDao();
////		User user = udao._get(TEST_USERNAME);
////		udao._remove(user);
////		assertNull(udao._get(TEST_USERNAME));
////		
////		SoftwareDao softwareDao = new SoftwareDao(session);
////		softwareDao.delete(software);
////		softwareDao.delete(si);
////		
////		// delete the test resources
////		ResourceDao resourceDao = new ResourceDao(session);
////		resourceDao.delete(hpc);
////		resourceDao.delete(storage);
////		
////		// 
////		ProjectDao projectDao = new ProjectDao();
////		project = projectDao._get(TEST_PROJECTNAME);
////		
////		UserProjectDao.remove(up);
////		assertNull(UserProjectDao.get(TEST_USERNAME, project.getId()));
////		
////		// delete the compute resource associations
////		ProjectResourceDao.remove(prCompute);
////		assertNull(ProjectResourceDao.get(project.getId(),prCompute.getResource().getName(),prCompute.getAllocationName()));
////		
////		UserProjectResourceDao.remove(uprCompute);
////		assertNull(UserProjectResourceDao.get(TEST_USERNAME, project.getId(),prCompute.getResource().getName(),prCompute.getAllocationName()));
////		
////		// delete the storage resource associations
////		ProjectResourceDao.remove(prStorage);
////		assertNull(ProjectResourceDao.get(project.getId(),prStorage.getResource().getName(),prStorage.getAllocationName()));
////		
////		UserProjectResourceDao.remove(uprStorage);
////		assertNull(UserProjectResourceDao.get(TEST_USERNAME, project.getId(),prStorage.getResource().getName(),prStorage.getAllocationName()));
////		
////		projectDao._remove(project);
////		assertNull(projectDao._get(TEST_PROJECTNAME));
//	}
	
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.test.GMSTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		softwareDao = new SoftwareDao(session);
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
//		projectDao = new ProjectDao();
//		project = projectDao._get(TEST_PROJECTNAME);
//		if (project == null) {
//			project = createProject();
//			projectDao._add(project);
//		}
//		
//		session = new GMSSession();
//		session.setType(AccessType.COMMUNITY);
//		session.setProjectId(project.getId());
//		session.setUserId(user.getId());
//		
//		projectDao = new ProjectDao(session);
//		softwareDao = new SoftwareDao(session);
//		ResourceDao resourceDao = new ResourceDao(session);
//		
//		up = UserProjectDao.get(TEST_USERNAME, project.getId());
//		
//		if (up == null) {
//			
//			// create storage
//			storage = createStorage();
//			resourceDao.persist(storage);
//			
//			// create a compute resource
//			hpc = createSystem();
//			resourceDao.persist(hpc);
//			
////			hpc = resourceDao._getCompute(TEST_SYSTEMNAME);
////			StorageResource storage = resourceDao._getStorage(TEST_STORAGENAME);
////			
//			// associate the user and project
//			up = new UserProject(user,project);
//			up.setUserType(UserPermissionType.USER);
//			up.setUsage(project.getUsage());
//			up.setMss(storage);
//		
//			UserProjectDao.add(up);
//			
//			// assign the resources with the project
//			prCompute = new ProjectResource(project, hpc, 
//					TEST_ALLOCATIONNAME, true, new Usage());
//			ProjectResourceDao.add(prCompute);
//			
//			prStorage = new ProjectResource(project, storage, 
//					TEST_ALLOCATIONNAME, true, new Usage());
//			ProjectResourceDao.add(prStorage);
//			
//			// now associate the resources with the users
//			uprCompute = new UserProjectResource(hpc, up, 
//					TEST_LOGINNAME, UserPermissionType.USER, 
//					TEST_ALLOCATIONNAME, new Usage());
//			UserProjectResourceDao.add(uprCompute);
//			
//			uprStorage = new UserProjectResource(storage, up, 
//					TEST_LOGINNAME, UserPermissionType.USER, 
//					TEST_ALLOCATIONNAME, new Usage());
//			UserProjectResourceDao.add(uprStorage);
//			
//		} else {
//			storage = resourceDao._getStorage(TEST_STORAGENAME);
//			hpc = resourceDao._getCompute(TEST_SYSTEMNAME);
//			uprCompute = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), hpc.getName(), TEST_ALLOCATIONNAME);
//			prCompute = ProjectResourceDao.get(project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			uprCompute = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//		}
//		
//		software = createSoftware();
//		
//		softwareDao.persist(software);
//		
//		si = createSoftwareInstallation(software,hpc);
//		
//		softwareDao.persist(si);
//		
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * @param x
	 */
	public SoftwareDaoTest(String x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

}
