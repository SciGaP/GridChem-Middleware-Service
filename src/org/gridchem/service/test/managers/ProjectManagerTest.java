package org.gridchem.service.test.managers;

import java.util.Date;
import java.util.HashMap;

import org.gridchem.service.authentication.teragrid.TeraGridLoginProvider;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ProjectValidationException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.ProjectStatusType;
import org.gridchem.service.project.ProjectManager;
import org.gridchem.service.provider.teragrid.dao.TeraGridProjectDao;
import org.gridchem.service.provider.teragrid.dao.TeraGridUserDao;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.user.UserManager;
import org.gridchem.service.util.ServiceUtil;

public class ProjectManagerTest extends GMSTestCase {
	
	public ProjectManagerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/********************************************************************
	 * 
	 * 				UserBean Validation Tests
	 * 
	 ********************************************************************/

	public void testValidateBeanNull() throws Exception {
		try {
			ProjectManager.validate(null);
			fail("Null user bean should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateCommentNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setComment(null);
		try {
			ProjectManager.validate(bean);
			fail("Null comment should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateCommentEmpty() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setComment("");
		try {
			ProjectManager.validate(bean);
			fail("Empty comment should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateDescriptionNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setDescription(null);
		try {
			ProjectManager.validate(bean);
			fail("Null description should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateDescriptionEmpty() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setDescription("");
		try {
			ProjectManager.validate(bean);
			fail("Empty description should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateStartDateNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setStartDate(null);
		try {
			ProjectManager.validate(bean);
			fail("Null start date should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateEndDateNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setStartDate(null);
		try {
			ProjectManager.validate(bean);
			fail("Null end date should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateEndDateBeforeStartDate() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setEndDate(new Date(bean.getStartDate().getTime() - 15));
		try {
			ProjectManager.validate(bean);
			fail("End date prior to start date should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
//	public void testValidateFundedProjectIdNull() throws Exception {
//		ProjectBean bean = createProject().toBean();
//		bean.setFundedProjectId(null);
//		try {
//			ProjectManager.validate(bean);
//			fail("Null funded project id should throw a project validation exception");
//		} catch (ProjectValidationException e) {}
//	}
//	
//	public void testValidateFundedProjectIdEmpty() throws Exception {
//		ProjectBean bean = createProject().toBean();
//		bean.setFundedProjectId("");
//		try {
//			ProjectManager.validate(bean);
//			fail("Empty funded project id should throw a project validation exception");
//		} catch (ProjectValidationException e) {}
//	}
//	
//	public void testValidateInstitutionNull() throws Exception {
//		ProjectBean bean = createProject().toBean();
//		bean.setInstitution(null);
//		try {
//			ProjectManager.validate(bean);
//			fail("Null institution should throw a project validation exception");
//		} catch (ProjectValidationException e) {}
//	}
//	
//	public void testValidateInstitutionEmpty() throws Exception {
//		ProjectBean bean = createProject().toBean();
//		bean.setInstitution("");
//		try {
//			ProjectManager.validate(bean);
//			fail("Empty institution should throw a project validation exception");
//		} catch (ProjectValidationException e) {}
//	}
	
	public void testValidateNameNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setName(null);
		try {
			ProjectManager.validate(bean);
			fail("Null name should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateNameEmpty() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setName("");
		try {
			ProjectManager.validate(bean);
			fail("Empty name should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidatePiNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setPi(null);
		try {
			ProjectManager.validate(bean);
			fail("Null pi should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidatePiEmpty() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setPi("");
		try {
			ProjectManager.validate(bean);
			fail("Empty pi should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidateStatusNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setStatus(null);
		try {
			ProjectManager.validate(bean);
			fail("Empty status should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
//	public void testValidateInstitutionTypeNull() throws Exception {
//		ProjectBean bean = createProject().toBean();
//		bean.setInstitutionType(null);
//		try {
//			ProjectManager.validate(bean);
//			fail("Empty institution type should throw a project validation exception");
//		} catch (ProjectValidationException e) {}
//	}
//	
//	public void testValidateTypeNull() throws Exception {
//		ProjectBean bean = createProject().toBean();
//		bean.setType(null);
//		try {
//			ProjectManager.validate(bean);
//			fail("Empty type should throw a project validation exception");
//		} catch (ProjectValidationException e) {}
//	}
	
	public void testValidateUsageNull() throws Exception {
		ProjectBean bean = createProject().toBean();
		bean.setUsage(null);
		try {
			ProjectManager.validate(bean);
			fail("Empty usage should throw a project validation exception");
		} catch (ProjectValidationException e) {}
	}
	
	public void testValidate() throws ProjectValidationException {
		ProjectManager.validate(createProject().toBean());
	}
	
	/********************************************************************
	 * 
	 * 				UserBean Validation Tests
	 * @throws ProjectValidationException 
	 * 
	 ********************************************************************/
	
	public void testAddProjectNull() throws ProjectValidationException {
		try {
			ProjectManager.addProject(null);
			fail("Null project bean should throw a project validation exception");
		} catch (ProjectException e) {}
	}
	
	
	public void testAddProjectDuplicateFails() throws ProjectValidationException {
		try {
			ProjectBean bean = createProject().toBean();
			ProjectManager.addProject(bean);
			assertNotNull(new ProjectDao()._get(TEST_PROJECTNAME));
			fail("Duplicate project name should throw project exception.");
		} catch (ProjectException e) {
			
		}
	}
	
	/********************************************************************
	 * 
	 * 				UserBean Validation Tests
	 * 
	 ********************************************************************/
	
	public void testUpdateStatusNullProjectId() {
		try {
			ProjectManager.updateStatus(null,ProjectStatusType.DEACTIVATED);
			fail("Null project id should throw a project exception");
		} catch (ProjectException e) {}
	}
	
	public void testUpdateStatusInvalidProjectId() {
		try {
			ProjectManager.updateStatus(Long.valueOf(-1),ProjectStatusType.DEACTIVATED);
			fail("Invalid project id should throw a project exception");
		} catch (ProjectException e) {}
	}
	
	public void testUpdateStatusNullStatus() {
		
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao._get(TEST_PROJECTNAME);
		
		try {
			ProjectManager.updateStatus(project.getId(),null);
			fail("Null project id should throw a project exception");
		} catch (ProjectException e) {}
	}
	
	public void testUpdateStatus() {
		// project was added in the testAddProject() test method.
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao._get(TEST_PROJECTNAME);
		
		ProjectManager.updateStatus(project.getId(),ProjectStatusType.DEACTIVATED);
		
		project = projectDao._get(TEST_PROJECTNAME);
		
		assertTrue(project.getStatus().equals(ProjectStatusType.DEACTIVATED));
		
		project.setStatus(ProjectStatusType.ACTIVE);
		projectDao.persist(project);
	}

	/********************************************************************
	 * 
	 * 				UserBean Validation Tests
	 * 
	 ********************************************************************/
	
	public void testUpdateAllocationNullProjectId() {
		try {
			ProjectManager.updateAllocation(null,TEST_PROJECTALLOCATION);
			fail("Null project id should throw a project exception");
		} catch (ProjectException e) {}
	}
	
	public void testUpdateAllocationInvalidProjectId() {
		try {
			ProjectManager.updateAllocation(Long.valueOf(-1),TEST_PROJECTALLOCATION);
			fail("Invalid project id should throw a project exception");
		} catch (ProjectException e) {}
	}
	
	public void testUpdateAllocationNullAllocation() {
		
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao._get(TEST_PROJECTNAME);
		
		try {
			ProjectManager.updateAllocation(project.getId(),null);
			fail("Null allocation should throw a project exception");
		} catch (ProjectException e) {}
	}
	
	public void testUpdateAllocation() {
		// project was added in the testAddProject() test method.
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao._get(TEST_PROJECTNAME);
		
		double updatedAllocation = project.getUsage().getAllocated() + TEST_PROJECTALLOCATION;
		
		ProjectManager.updateAllocation(project.getId(),Double.valueOf(TEST_PROJECTALLOCATION));
		
		project = projectDao._get(TEST_PROJECTNAME);
		
		assertTrue(project.getUsage().getAllocated() == updatedAllocation);
	}

	public void testImportTeraGridProjectNullSession() {
		try {
			ProjectManager.importTeraGridProject(null, createProject().toBean());
			fail("Null session should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null session should throw a session exception");
		}
	}
	
	public void testImportTeraGridProjectNullProjectBean() {
		try {
			ProjectManager.importTeraGridProject(new GMSSession(), null);
			fail("Null project bean should throw a project exception");
		} catch (ProjectException e) {
		} catch (Exception e) {
			fail("Null session should throw a project exception");
		}
	}
	
	public void testImportTeraGridProject() throws UserException, ProfileValidationException {
		ProjectBean projectBean = null;
		UserBean userBean = null;
		AuthenticationBean authBean = null;
		
		try {
			
			// create a TeraGrid authentication bean
			HashMap<String,String> authMap = new HashMap<String,String>();
			authMap.put("myproxy.server", "myproxy.teragrid.org");
			authMap.put("myproxy.port", "7512");
			
			TeraGridLoginProvider provider = new TeraGridLoginProvider();
			authBean = provider.login(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD, authMap);
			authBean.setMyproxyPassword(TEST_MYPROXY_PASSWORD);
			
			// ingest the user's profile from the teragrid
			userBean = TeraGridUserDao.findByUsername(
					authBean.getMyproxyUsername(), 
					authBean.getMyproxyPassword());
			
			UserManager.addUser(userBean, authBean.getMyproxyPassword() );
			
			// create a dummy session
			GMSSession session = new GMSSession();
			session.setToken(TEST_SESSIONTOKEN);
			session.setType(AccessType.TERAGRID);
			//session.setProxy(ServiceUtil.serializeGlobusCredential(authBean.getCredential()));//JGlobus 2.1.0
			session.setProxy(ServiceUtil.serializeX509Credential(authBean.getCredential()));
			session.setUserId(new UserDao()._get(userBean.getUserName()).getId());
			
			// pull the user's project from the teragrid
			projectBean = new TeraGridProjectDao(session).findForCurrentUser().get(0);
			
			// ingest the project
			ProjectManager.importTeraGridProject(session, projectBean);
		} catch (ProjectException e) {
		} catch (Exception e) {
			fail("Null session should throw a project exception");
		} finally {
			ProjectDao projectDao = new ProjectDao();
			projectDao._scrub(projectBean.getName());
			
			UserDao udao = new UserDao();
			udao.remove(userBean);
			assertNull(udao._get(userBean.getUserName()));
		}
		
		
	}
	
//	public void testCleanUp() {
//		
//		ProjectDao projectDao = new ProjectDao();
//		projectDao._remove(projectDao._get(TEST_PROJECTNAME));
//		
//		
//	}


}
