package org.gridchem.service.test.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.gridchem.service.ProjectService;
import org.gridchem.service.SessionService;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.impl.ProjectServiceImpl;
import org.gridchem.service.impl.SessionServiceImpl;
import org.gridchem.service.model.Usage;
import org.gridchem.service.model.User;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.UserPermissionType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

public class TeraGridProjectServiceTest extends GMSTestCase {

	private String TEST_SESSIONTOKEN;
	private ProjectService projectService;
	
	public TeraGridProjectServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		
		try {
			FileInputStream in = new FileInputStream("tg-tests");
		
			Properties props = new Properties();
			props.loadFromXML(in);
			in.close();
			TEST_SESSIONTOKEN = props.getProperty("session.key");
		} catch (Exception e) {
			// file was empty
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dummy.key", "dummy.value");
			String sMap = ServiceUtil.xstream.toXML(map);
		
			SessionService sessionService = new SessionServiceImpl();
			TEST_SESSIONTOKEN = sessionService.createSession(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD, sMap, AccessType.TERAGRID.name());
			
			HibernateUtil.flush();
		}
		
		session = SessionDao.getByToken(TEST_SESSIONTOKEN);
		if (session.getProjectId() == null) {
			project = new ProjectDao(session)._getAll().get(0);
			session.setProjectId(project.getId());
			SessionDao.persist(session);
			HibernateUtil.flush();
		} else {
			project = new ProjectDao(session)._get();
		}
		
		projectService = new ProjectServiceImpl();
	}

	public void testGetTeraGridProjectsNullSessionToken() throws Exception {
		try {
			projectService.getProjects(null);
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectsEmptySessionToken() throws Exception {
		try {
			projectService.getProjects("");
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectsInvalidSessionToken() throws Exception {
		try {
			projectService.getProjects("-1");
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetTeraGridProjects() throws Exception {
		String sProjects = projectService.getProjects(TEST_SESSIONTOKEN);
		assertNotNull(sProjects);
		
		List<ProjectBean> beans = (List<ProjectBean>)ServiceUtil.xstream.fromXML(sProjects);
		
		assertFalse(beans.isEmpty());
		
	}

	public void testGetTeraGridProjectCollaboratorsNullSessionToken() throws Exception {
		try {
			projectService.getCollaborators(null);
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectCollaboratorsEmptySessionToken() throws Exception {
		try {
			projectService.getCollaborators("");
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectCollaboratorsInvalidSessionToken() throws Exception {
		try {
			projectService.getCollaborators("-1");
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetTeraGridProjectCollaborators() {
		
		
		String sCollabs = projectService.getCollaborators(TEST_SESSIONTOKEN);
		assertNotNull(sCollabs);
		
		List<UserBean> beans = (List<UserBean>)ServiceUtil.xstream.fromXML(sCollabs);
		
		assertFalse(beans.isEmpty());
	}

	public void testGetTeraGridProjectCollaboratorsForProjectIdNullSessionToken() throws Exception {
		try {
			projectService.getProjectCollaborators(null,project.getId().toString());
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectCollaboratorsForProjectIdEmptySessionToken() throws Exception {
		try {
			projectService.getProjectCollaborators("",project.getId().toString());
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectCollaboratorsForProjectIdInvalidSessionToken() throws Exception {
		try {
			projectService.getProjectCollaborators("-1",project.getId().toString());
			fail("Invalid session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectCollaboratorsForProjectIdNullProjectId() throws Exception {
		try {
			projectService.getProjectCollaborators(TEST_SESSIONTOKEN,null);
			fail("Null session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectCollaboratorsForProjectIdEmptyProjectId() throws Exception {
		try {
			projectService.getProjectCollaborators(TEST_SESSIONTOKEN,"");
			fail("Empty session token should throw session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty session token should throw session exception");
		}
	}
	
	public void testGetTeraGridProjectCollaboratorsForProjectIdInvalidProjectId() throws Exception {
		try {
			projectService.getProjectCollaborators(TEST_SESSIONTOKEN,"-1");
			fail("Invalid session token should throw session exception");
		} catch (PermissionException e) {
		} catch (Exception e) {
			fail("Invalid session token should throw session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetTeraGridProjectCollaboratorsForProjectId() {
		
		String sCollabs = projectService.getProjectCollaborators(TEST_SESSIONTOKEN, project.getId().toString());
		assertNotNull(sCollabs);
		
		List<UserBean> beans = (List<UserBean>)ServiceUtil.xstream.fromXML(sCollabs);
		
		assertFalse(beans.isEmpty());
		
	}
	
	public void testTearDown() throws Exception {}
	
	public void tearDown() throws Exception {
		if (TEST_SESSIONTOKEN != null) {
			File file = new File("tg-tests");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
		
			Properties props = new Properties();
			props.setProperty("session.key", TEST_SESSIONTOKEN);
			props.storeToXML(out, "Output for teragrid service tests");
			out.close();
		}
		
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
