package org.gridchem.service.test.service;

import java.util.List;

import org.gridchem.service.ProjectService;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.impl.ProjectServiceImpl;
import org.gridchem.service.model.Usage;
import org.gridchem.service.model.User;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.model.enumeration.UserPermissionType;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

public class ProjectServiceTest extends GMSTestCase {

//	private Project project;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;
	private ProjectService service;
	
	public ProjectServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
//		UserDao udao = new UserDao();
		
//		// retrieve the user from the db or create one to use.
//		User user = udao._get(TEST_USERNAME);
//		if (user == null) {
//			user = createUser();
//			udao._add(user);
//		}
//		
//		ProjectDao projectDao = new ProjectDao();
//		project = projectDao._get(TEST_PROJECTNAME);
//		if (project == null) {
//			project = createProject();
//			projectDao._add(project);
//		}
//		
//		// persist the session
//		session = SessionDao.getByToken(TEST_SESSIONTOKEN);
//		
//		if (session == null) {
//			session = new GMSSession();
//			session.setToken(TEST_SESSIONTOKEN);
//			session.setType(AccessType.COMMUNITY);
//			CCGAuthentication auth = new CCGAuthentication(TEST_USERNAME);
//			session.setProxy(ServiceUtil.serializeGlobusCredential(auth.getCredential()));
//			session.setProjectId(project.getId());
//			session.setUserId(user.getId());
//			
//			SessionDao.persist(session);
//		} 
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
		
		service = new ProjectServiceImpl();
	}

	public void testGetProjectsNullSessionToken() throws Exception {
		try {
			service.getProjects(null);
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetProjectsEmptySessionToken() throws Exception {
		try {
			service.getProjects("");
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetProjectsInvalidSessionToken() throws Exception {
		try {
			service.getProjects("-1");
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetProjects() throws Exception {
		String sProjects = service.getProjects(TEST_SESSIONTOKEN);
		assertNotNull(sProjects);
		
		List<ProjectBean> beans = (List<ProjectBean>)ServiceUtil.xstream.fromXML(sProjects);
		
		assertTrue(beans.size() == 1);
		
	}

	public void testGetProjectCollaboratorsNullSessionToken() throws Exception {
		try {
			service.getCollaborators(null);
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetProjectCollaboratorsEmptySessionToken() throws Exception {
		try {
			service.getCollaborators("");
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetProjectCollaboratorsInvalidSessionToken() throws Exception {
		try {
			service.getCollaborators("-1");
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetProjectCollaborators() {
		
		UserDao udao = new UserDao();
		User collab = udao._get(TEST_USERNAME + ".collab");
		if (collab == null) {
			collab = createUser();
			collab.setUsername(TEST_USERNAME + ".collab");
			udao._add(collab);
		}
		
		UserProject collabup = new UserProject(collab, project);
		collabup.setUserType(UserPermissionType.USER);
		collabup.setUsage(new Usage());
		collabup.setMss(new ResourceDao(session)._getStorage(TEST_STORAGENAME));
		UserProjectDao.add(collabup);
		
		String sCollabs = service.getCollaborators(TEST_SESSIONTOKEN);
		assertNotNull(sCollabs);
		
		List<UserBean> beans = (List<UserBean>)ServiceUtil.xstream.fromXML(sCollabs);
		
		assertTrue(beans.size() == 2);
		
		UserProjectDao.remove(collabup);
		
		udao = new UserDao();
		udao._remove(collab);
		
	}

	public void testGetProjectCollaboratorsForProjectIdNullSessionToken() throws Exception {
		try {
			service.getProjectCollaborators(null,project.getId().toString());
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetProjectCollaboratorsForProjectIdEmptySessionToken() throws Exception {
		try {
			service.getProjectCollaborators("",project.getId().toString());
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetProjectCollaboratorsForProjectIdInvalidSessionToken() throws Exception {
		try {
			service.getProjectCollaborators("-1",project.getId().toString());
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	public void testGetProjectCollaboratorsForProjectIdNullProjectId() throws Exception {
		try {
			service.getProjectCollaborators(TEST_SESSIONTOKEN,null);
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetProjectCollaboratorsForProjectIdEmptyProjectId() throws Exception {
		try {
			service.getProjectCollaborators(TEST_SESSIONTOKEN,"");
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetProjectCollaboratorsForProjectIdInvalidProjectId() throws Exception {
		try {
			service.getProjectCollaborators(TEST_SESSIONTOKEN,"-1");
			fail("Invalid session token should throw session exception");
		} catch (PermissionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetProjectCollaboratorsForProjectId() {
		UserDao udao = new UserDao();
		User collab = udao._get(TEST_USERNAME + ".collab");
		if (collab == null) {
			collab = createUser();
			collab.setUsername(TEST_USERNAME + ".collab");
			udao._add(collab);
		}
		UserProject collabup = new UserProject(collab, project);
		collabup.setUserType(UserPermissionType.USER);
		collabup.setUsage(new Usage());
		collabup.setMss(new ResourceDao(session)._getStorage(TEST_STORAGENAME));
		UserProjectDao.add(collabup);
		
		String sCollabs = service.getProjectCollaborators(TEST_SESSIONTOKEN, project.getId().toString());
		assertNotNull(sCollabs);
		
		List<UserBean> beans = (List<UserBean>)ServiceUtil.xstream.fromXML(sCollabs);
		
		assertTrue(beans.size() == 2);
		
		UserProjectDao.remove(collabup);
		udao = new UserDao();
		udao._remove(collab);
		
	}
	
//	public void testCleanUp() {
//		
//		SessionDao.delete(SessionDao.getByToken(TEST_SESSIONTOKEN));
//		assertNull(SessionDao.getByToken(TEST_SESSIONTOKEN));
//		
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

}
