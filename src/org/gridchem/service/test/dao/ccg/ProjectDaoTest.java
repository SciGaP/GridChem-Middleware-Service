/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.model.Usage;
import org.gridchem.service.model.User;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.model.enumeration.UserPermissionType;
import org.gridchem.service.test.GMSTestCase;

/**
 * @author dooley
 *
 */
public class ProjectDaoTest extends GMSTestCase {

	private ProjectDao projectDao;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;
	
	public ProjectDaoTest(String x) {
		super(x);
		
	}

	/**
	 * Test exception thrown for adding null project
	 */
	public void testAddNull() {
		try {
			projectDao = new ProjectDao();
			projectDao._add(null);
			fail("Adding null project should thrown an exception");
		} catch (ProjectException e) {}
	}
	
	/**
	 * Test exception thrown for adding null project
	 */
	public void testAddDuplicate() {
		try {
			projectDao = new ProjectDao();
			projectDao._add(createProject());
			fail("Adding duplicate project should thrown an exception");
		} catch (ProjectException e) {}
	}
	
	/**
	 * Test null returned for null session project id
	 */
	public void testSessionGetProjectNull() {
		Long id = session.getProjectId();
		session.setProjectId(null);
		projectDao = new ProjectDao(session);
		assertNull(projectDao.get());
		session.setProjectId(id);
	}
	
	/**
	 * Test null returned for invalid session project id
	 */
	public void testSessionGetProjectInvalid() {
		Long id = session.getProjectId();
		session.setProjectId(new Long(-1));
		projectDao = new ProjectDao(session);
		assertNull(projectDao.get());
		session.setProjectId(id);
	}
	
	/**
	 * Test projectbean is returned from call to get session project
	 */
	public void testSessionGetProject() {
		assertNotNull(projectDao.get());
	}
	
	/**
	 * Test null returned for invalid project id
	 */
	public void testGetProjectIdInvalid() {
		assertNull(projectDao.get(new Long(-1)));
	}

	/**
	 * Test valid projectbean returned for valid project name
	 */
	public void testGetProjectId() {
		assertNotNull(projectDao.get(session.getProjectId()));
	}
	
	/**
	 * Test null returned for emtpy project name
	 */
	public void testGetProjectNameEmpty() {
		assertNull(projectDao.get(""));
	}
	
	/**
	 * Test null returned for invalid project idname
	 */
	public void testGetProjectNameInvalid() {
		assertNull(projectDao.get("-1"));
	}

	/**
	 * Test valid projectbean returned for valid project name
	 */
	public void testGetProjectName() {
		assertNotNull(projectDao.get(TEST_PROJECTNAME));
	}
	
	/**
	 * Test empty list returned for null project
	 */
	public void testGetCollaboratorsNull() {
		
		assertTrue(projectDao.getCollaborators(null).isEmpty());
	}
	
	/**
	 * Test empty list returned for invalid project
	 */
	public void testGetCollaboratorsInvalid() {
		assertTrue(projectDao.getCollaborators(new Long(-1)).isEmpty());
	}
	
	/**
	 * Test collaboraors returned for valid project name
	 * 
	 * @throws ProfileValidationException 
	 * @throws InfrastructureException 
	 */
	public void testGetCollaborators() throws InfrastructureException, ProfileValidationException {
		// add a collaborator
		UserDao udao = new UserDao();
		User collab = udao._get(TEST_USERNAME + ".collab");
		if (collab == null) {
			collab = createUser();
			collab.setUsername(TEST_USERNAME + ".collab");
			udao._add(collab);
		}
		UserProject collabup = new UserProject(collab, up.getProject());
		collabup.setUserType(UserPermissionType.USER);
		collabup.setUsage(new Usage());
		collabup.setMss(new ResourceDao(session)._getStorage(TEST_STORAGENAME));
		UserProjectDao.add(collabup);
		
		assertFalse(projectDao.getCollaborators(session.getProjectId()).isEmpty());
		
		UserProjectDao.remove(collabup);
		udao = new UserDao();
		udao._remove(collab);
	}

	/**
	 * Test empty list returned for null project
	 */
	public void testSessionGetCollaboratorsNull() {
		Long id = session.getProjectId();
		session.setProjectId(null);
		projectDao = new ProjectDao(session);
		assertTrue(projectDao.getCollaborators().isEmpty());
		session.setProjectId(id);
	}
	
	/**
	 * Test empty list returned for invalid project
	 */
	public void testSessionGetCollaboratorsInvalid() {
		Long id = session.getProjectId();
		session.setProjectId(new Long(-1));
		projectDao = new ProjectDao(session);
		assertTrue(projectDao.getCollaborators().isEmpty());
		session.setProjectId(id);
	}
	
	/**
	 * Test collaboraors returned for valid project name
	 */
	public void testSessionGetCollaborators() {
		assertFalse(projectDao.getCollaborators().isEmpty());
	}
	
	/**
	 * Test false returned on null project 
	 */
	public void testIsUserProjectNull() {
		assertFalse(projectDao.isUserProject(null));
	}

	/**
	 * Test false returned on invalid project 
	 */
	public void testIsUserProjectInvalid() {
		assertFalse(projectDao.isUserProject(new Long(-1)));
	}
	
	/**
	 * Test true returned on valid user project 
	 */
	public void testIsUserProject() {
		assertTrue(projectDao.isUserProject(session.getProjectId()));
	}
	
	/**
	 * Test false returned on null project 
	 */
	public void testIsProjectValidNull() {
		assertFalse(projectDao.isProjectValid(null));
	}

	/**
	 * Test false returned on invalid project 
	 */
	public void testIsProjectValidInvalid() {
		assertFalse(projectDao.isProjectValid(new Long(-1)));
	}
	
	/**
	 * Test true returned on valid project 
	 */
	public void testIsProjectValid() {
		assertTrue(projectDao.isProjectValid(session.getProjectId()));
	}
	
	/**
	 * Test false returned on null session project 
	 */
	public void testSessionIsProjectValidNull() {
		Long id = session.getProjectId();
		session.setProjectId(null);
		projectDao = new ProjectDao(session);
		assertFalse(projectDao.isProjectValid());
		session.setProjectId(id);
	}

	/**
	 * Test false returned on invalid session project 
	 */
	public void testSessionIsProjectValidInvalid() {
		Long id = session.getProjectId();
		session.setProjectId(new Long(-1));
		projectDao = new ProjectDao(session);
		assertFalse(projectDao.isProjectValid());
		session.setProjectId(id);
	}
	
	/**
	 * Test true returned on valid session project 
	 */
	public void testSessionIsProjectValid() {
		assertTrue(projectDao.isProjectValid());
	}
	
	/**
	 * Test null returned on as default allocation for project on resource
	 */
	public void testSessionGetDefaultAllocationNull() {
		assertNull(projectDao.getDefaultAllocation(null));
	}
	
	/**
	 * Test null returned on as default allocation for project on resource
	 */
	public void testSessionGetDefaultAllocationEmpty() {
		assertNull(projectDao.getDefaultAllocation(""));
	}
	
	/**
	 * Test null returned on as default allocation for project on resource
	 */
	public void testSessionGetDefaultAllocationInvalid() {
		assertNull(projectDao.getDefaultAllocation("-1"));
	}
	
	/**
	 * Test valid allocation returned on as default allocation for project on resource
	 */
	public void testSessionGetDefaultAllocation() {
		assertNotNull(projectDao.getDefaultAllocation(TEST_SYSTEMNAME));
	}
	
	/**
	 * Test null returned on as default allocation for null project on resource
	 */
	public void testGetDefaultAllocationNull() {
		assertNull(projectDao.getDefaultAllocation(null,TEST_SYSTEMNAME));
	}
	
	/**
	 * Test null returned on as default allocation for invalid project on resource
	 */
	public void testGetDefaultAllocationInvalid() {
		assertNull(projectDao.getDefaultAllocation(new Long("-1"),TEST_SYSTEMNAME));
	}
	
	/**
	 * Test valid allocation returned on as default allocation for valid project on resource
	 */
	public void testGetDefaultAllocation() {
		assertNotNull(projectDao.getDefaultAllocation(session.getProjectId(),TEST_SYSTEMNAME));
	}
	
	/**
	 * Test false returned on null session project 
	 */
	public void testSessionIsAllocationValidNull() {
		Long id = session.getProjectId();
		session.setProjectId(null);
		projectDao = new ProjectDao(session);
		assertFalse(projectDao.isProjectValid());
		session.setProjectId(id);
	}

	/**
	 * Test false returned on invalid session project 
	 */
	public void testSessionIsAllocationValidInvalid() {
		Long id = session.getProjectId();
		session.setProjectId(new Long(-1));
		projectDao = new ProjectDao(session);
		assertFalse(projectDao.isProjectValid());
		session.setProjectId(id);
	}
	
	/**
	 * Test true returned on valid session project 
	 */
	public void testSessionAllocationIsProjectValid() {
		assertTrue(projectDao.isProjectValid());
	}
	
	/**
	 * Test false returned on null allocation 
	 */
	public void testIsAllocationValidNull() {
		assertFalse(projectDao.isAllocationValid(session.getProjectId(),null));
	}

	/**
	 * Test false returned on invalid Allocation 
	 */
	public void testIsAllocationValidInvalid() {
		assertFalse(projectDao.isAllocationValid(session.getProjectId(),"-1"));
	}
	
	/**
	 * Test true returned on valid Allocation 
	 */
	public void testSessionIsAllocationValid() {
		assertTrue(projectDao.isAllocationValid(session.getProjectId(),TEST_ALLOCATIONNAME));
	}
//	
//	/**
//	 * Test method for {@link org.gridchem.service.provider.ccg.dao.CCGProjectDao#findSessionProject()}.
//	 */
//	public void testFindSessionProject() {
//		assertNotNull(projectDao.findSessionProject());
//	}
//
//	/**
//	 * Test method for {@link org.gridchem.service.provider.ccg.dao.CCGProjectDao#findDefaultAllocationForProject(java.lang.Long, java.lang.String)}.
//	 */
//	public void testFindDefaultAllocationForProject() {
//		assertNotNull(projectDao.findDefaultAllocationForProject(session.getProjectId(), TEST_SYSTEMNAME));
//	}
//
//	/**
//	 * Test method for {@link org.gridchem.service.provider.ccg.dao.CCGProjectDao#isProjectAllocationValid(java.lang.Long, java.lang.String)}.
//	 */
//	public void testIsProjectAllocationValid() {
//		String allocationName = projectDao.findDefaultAllocationForProject(session.getProjectId(), TEST_SYSTEMNAME);
//		assertNotNull(allocationName);
//		assertTrue(projectDao.isProjectAllocationValid(session.getProjectId(), allocationName));
//	}
//
//	/**
//	 * Test method for {@link org.gridchem.service.provider.ccg.dao.CCGProjectDao#isProjectValid(java.lang.Long)}.
//	 */
//	public void testIsProjectValid() {
//		assertTrue(projectDao.isProjectValid(session.getProjectId()));
//	}
//
//	/**
//	 * Test method for {@link org.gridchem.service.provider.ccg.dao.CCGProjectDao#isUserProject(java.lang.Long)}.
//	 */
//	public void testIsUserProject() {
//		assertTrue(projectDao.isUserProject(session.getProjectId()));
//	}

	
//	/**
//	 * Test method for {@link org.gridchem.service.provider.ccg.dao.CCGProjectDao#findById_internal()}.
//	 */
//	public void testFindById_internal() {
//		assertNotNull(projectDao.findById_internal());
//	}
	
	public void testScrub() {
		ProjectDao projectDao = new ProjectDao();
		projectDao._scrub(TEST_PROJECTNAME);
		
	}
	
//	public void testZCleanUp() {
//		UserDao udao = new UserDao();
//		User user = udao._get(TEST_USERNAME);
//		udao._remove(user);
//		assertNull(udao._get(TEST_USERNAME));
//		
//		ProjectDao projectDao = new ProjectDao();
//		Project project = projectDao._get(TEST_PROJECTNAME);
//		
//		UserProjectDao.remove(up);
//		assertNull(UserProjectDao.get(TEST_USERNAME, project.getId()));
//		
//		ProjectResourceDao.remove(pr);
//		assertNull(ProjectResourceDao.get(project.getId(),pr.getResource().getName(),pr.getAllocationName()));
//		
//		UserProjectResourceDao.remove(upr);
//		assertNull(UserProjectResourceDao.get(TEST_USERNAME, project.getId(),pr.getResource().getName(),pr.getAllocationName()));
//		
//		projectDao._remove(project);
//		assertNull(projectDao._get(TEST_PROJECTNAME));
//	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.test.GMSTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		projectDao = new ProjectDao(session);
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
//		Project project = projectDao._get(TEST_PROJECTNAME);
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
//			
//			ComputeResource hpc = new ResourceDao(session)._getCompute(TEST_SYSTEMNAME);
//			pr = new ProjectResource(project, hpc, 
//					TEST_ALLOCATIONNAME, true, new Usage());
//			ProjectResourceDao.add(pr);
//			
//			upr = new UserProjectResource(hpc, up, 
//					TEST_LOGINNAME, UserPermissionType.USER, 
//					TEST_ALLOCATIONNAME, new Usage());
//			UserProjectResourceDao.add(upr);
//		} else {
//			pr = ProjectResourceDao.get(project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			upr = UserProjectResourceDao.get(TEST_USERNAME, project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//		}
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
