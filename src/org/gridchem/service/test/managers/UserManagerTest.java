package org.gridchem.service.test.managers;

import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.user.UserManager;

public class UserManagerTest extends GMSTestCase {
	
//	private User user;
//	private Project project;
	
	public UserManagerTest(String name) {
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
//			session.setProxy("");
//			session.setProjectId(project.getId());
//			session.setUserId(user.getId());
//		} 
		
	}

	/********************************************************************
	 * 
	 * 				UserBean Validation Tests
	 * 
	 ********************************************************************/

	public void testValidateBeanNull() throws Exception {
		try {
			UserManager.validate(null);
			fail("Null user bean should throw a validation exception");
		} catch (ProfileValidationException e) {}
	}

//	public void testValidateIMProviderNull() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setImProvider(null);
//		try {
//			UserManager.validate(bean);
//			fail("Null IM provider should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//		
//	}
//	
//	public void testValidateIMProviderEmpty() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setImProvider("");
//		try {
//			UserManager.validate(bean);
//			fail("Empty IM provider should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//	}
//	
//	public void testValidateIMProviderInvalid() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setImProvider(null);
//		try {
//			UserManager.validate(bean);
//			fail("Null IM provider should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//	}
//	
//	public void testValidateIMNull() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setIm(null);
//		try {
//			UserManager.validate(bean);
//			fail("Null IM handle should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//	}
//	
//	public void testValidateIMEmpty() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setIm("");
//		try {
//			UserManager.validate(bean);
//			fail("Empty IM handle should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//	}
	
	public void testValidateEmailNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setEmail(null);
		try {
			UserManager.validate(bean);
			fail("Null email should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateEmailEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setEmail("");
		try {
			UserManager.validate(bean);
			fail("Empty email should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateEmailInvalid() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setEmail("example.com");
		try {
			UserManager.validate(bean);
			fail("Invalid email should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidatePhoneNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setPhone(null);
		try {
			UserManager.validate(bean);
			fail("Null phone should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidatePhoneEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setPhone("");
		try {
			UserManager.validate(bean);
			fail("Null phone should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidatePhoneInvalid() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setPhone("a35-222-1111");
		try {
			UserManager.validate(bean);
			fail("Invalid phone should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateFaxNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setFax(null);
		try {
			UserManager.validate(bean);
			fail("Null fax should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateFaxEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setFax(null);
		try {
			UserManager.validate(bean);
			fail("Empty fax should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateFaxInvalid() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setFax("a22-222-1111");
		try {
			UserManager.validate(bean);
			fail("Invalid fax should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
//	public void testValidateCellNull() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setCell(null);
//		try {
//			UserManager.validate(bean);
//			fail("Null cell should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//	}
//	
//	public void testValidateCellEmpty() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setCell("");
//		try {
//			UserManager.validate(bean);
//			fail("Empty should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//	}
//	
//	public void testValidateCellInvalid() throws Exception {
//		UserBean bean = createUser().toBean();
//		bean.setCell("2222-111-1111");
//		try {
//			UserManager.validate(bean);
//			fail("Invalid cell should throw a profile validation exception");
//		} catch (ProfileValidationException e) {}
//	}
	
	public void testValidateAddressNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setAddress(null);
		try {
			UserManager.validate(bean);
			fail("Null address should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressStreet1Null() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setStreet1(null);
		try {
			UserManager.validate(bean);
			fail("Null address street1 should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressStreet1Empty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setStreet1("");
		try {
			UserManager.validate(bean);
			fail("Empty address street1 should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressCityNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setCity(null);
		try {
			UserManager.validate(bean);
			fail("Null address city should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressCityEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setCity("");
		try {
			UserManager.validate(bean);
			fail("Empty address city should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressStateNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setState(null);
		try {
			UserManager.validate(bean);
			fail("Null address state should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressStateEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setState("");
		try {
			UserManager.validate(bean);
			fail("Empty address state should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressZipCodeNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setZipCode(null);
		try {
			UserManager.validate(bean);
			fail("Null address zip code should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressZipCodeEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setZipCode("");
		try {
			UserManager.validate(bean);
			fail("Empty address zip code should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateAddressZipCodeInvalid() throws Exception {
		UserBean bean = createUser().toBean();
		bean.getAddress().setZipCode("a");
		try {
			UserManager.validate(bean);
			fail("Invalid address zip code should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateDepartmentNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setDepartment(null);
		try {
			UserManager.validate(bean);
			fail("Null department should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateDepartmentEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setDepartment("");
		try {
			UserManager.validate(bean);
			fail("Empty department should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateInstituteNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setInstitute(null);
		try {
			UserManager.validate(bean);
			fail("Null institute should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateInstitutueEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setInstitute("");
		try {
			UserManager.validate(bean);
			fail("Emptpy institute should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserClassificationTypeNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setClassification(null);
		try {
			UserManager.validate(bean);
			fail("Null user classification type should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserClassificationTypeEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setClassification("");
		try {
			UserManager.validate(bean);
			fail("Emtpy user classification type should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserClassificationTypeInvalid() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setClassification("unknown");
		try {
			UserManager.validate(bean);
			fail("Invalid user classification type should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserUsernameNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setUserName(null);
		try {
			UserManager.validate(bean);
			fail("Null username should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserUsernameEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setUserName("");
		try {
			UserManager.validate(bean);
			fail("Empty username should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserLastNameNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setLastName(null);
		try {
			UserManager.validate(bean);
			fail("Null last name should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserLastNameEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setLastName("");
		try {
			UserManager.validate(bean);
			fail("Empty last name should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserFirstNameNull() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setFirstName(null);
		try {
			UserManager.validate(bean);
			fail("Null first name should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	public void testValidateUserFirstNameEmpty() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setFirstName(null);
		try {
			UserManager.validate(bean);
			fail("Emtpy last name should throw a profile validation exception");
		} catch (ProfileValidationException e) {}
	}
	
	
	/********************************************************************
	 * 
	 * 				Test Add User
	 * 
	 ********************************************************************/

	public void testAddUser() throws Exception {
		UserBean bean = createUser().toBean();
		bean.setUserName(TEST_USERNAME+".temp");
		UserManager.addUser(bean, TEST_PASSWORD);
		// delete that user
		new UserDao().remove(bean);
	}
	
	/********************************************************************
	 * 
	 * 				Test Add User To Project
	 * 
	 ********************************************************************/

	public void testAddUserToProjectNullUsername() throws Exception {
		try {
			UserManager.addUserToCommunityProject(null, project.getId());
			fail("Null project id should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddUserToProjectEmptyUsername() throws Exception {
		try {
			UserManager.addUserToCommunityProject("", project.getId());
			fail("Empty username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddUserToProjectInvalidUsername() throws Exception {
		try {
			UserManager.addUserToCommunityProject("-1", project.getId());
			fail("Invalid username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddUserToProjectNullProjectId() throws Exception {
		try {
			UserManager.addUserToCommunityProject(user.getUsername(), null);
			fail("Null project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testAddUserToProjectInvalidProjectId() throws Exception {
		try {
			UserManager.addUserToCommunityProject(user.getUsername(), new Long("-1"));
			fail("Invalid project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testAddUserToProject() throws Exception {
		UserManager.addUserToCommunityProject(user.getUsername(), project.getId());
	}
	
	/********************************************************************
	 * 
	 * 				Enable UserProject Tests
	 * 
	 ********************************************************************/
	
	public void testEnableUserFromProjectNullUsername() throws Exception {
		try {
			UserManager.enableUserFromProject(null, project.getId());
			fail("Null username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectEmptyUsername() throws Exception {
		try {
			UserManager.enableUserFromProject("", project.getId());
			fail("Empty username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectInvalidUsername() throws Exception {
		try {
			UserManager.enableUserFromProject("-1", project.getId());
			fail("Invalid username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectNullProjectId() throws Exception {
		try {
			UserManager.enableUserFromProject(user.getUsername(), null);
			fail("Null project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testEnableUserFromProjectInvalidProjectId() throws Exception {
		try {
			UserManager.enableUserFromProject(user.getUsername(), new Long("-1"));
			fail("Invalid project id should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProject() throws Exception {
		UserManager.enableUserFromProject(user.getUsername(), project.getId());
		assertTrue(UserProjectDao.get(user.getUsername(), project.getId()).isEnabled());
	}

	
	/********************************************************************
	 * 
	 * 				Disable UserProject Tests
	 * 
	 ********************************************************************/
	
	public void testDisableUserFromProjectNullUsername() throws Exception {
		try {
			UserManager.disableUserFromProject(null, project.getId());
			fail("Null username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProjectEmptyUsername() throws Exception {
		try {
			UserManager.disableUserFromProject("", project.getId());
			fail("Empty username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProjectInvalidUsername() throws Exception {
		try {
			UserManager.disableUserFromProject("-1", project.getId());
			fail("Invalid username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProjectNullProjectId() throws Exception {
		try {
			UserManager.disableUserFromProject(user.getUsername(), null);
			fail("Null project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testDisableUserFromProjectInvalidProjectId() throws Exception {
		try {
			UserManager.disableUserFromProject(user.getUsername(), new Long("-1"));
			fail("Invalid project id should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProject() throws Exception {
		UserManager.disableUserFromProject(user.getUsername(), project.getId());
		assertFalse(UserProjectDao.get(user.getUsername(), project.getId()).isEnabled());
	}
	
	/********************************************************************
	 * 
	 * 				Add Resource to Community UserProject Tests
	 * 
	 ********************************************************************/
	
//	public void testAddResourceToCommunityUserProjectNullUsername() throws Exception {
//		try {
//			UserManager.addResourceToCommunityUserProject(null, project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			fail("Null username should throw an exception");
//		} catch(UserException e) {}
//	}
//	
//	public void testAddResourceToCommunityUserProjectEmptyUsername() throws Exception {
//		try {
//			UserManager.addResourceToCommunityUserProject("", project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			fail("Empty username should throw an exception");
//		} catch(UserException e) {}
//	}
//	
//	public void testAddResourceToCommunityUserProjectInvalidUsername() throws Exception {
//		try {
//			UserManager.addResourceToCommunityUserProject("-1", project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			fail("Invalid username should throw an exception");
//		} catch(UserException e) {}
//	}
//	
//	public void testAddResourceToCommunityUserProjectNullProjectId() throws Exception {
//		try {
//			UserManager.addResourceToCommunityUserProject(user.getUsername(), null, TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			fail("Null project id should throw an exception");
//		} catch(ProjectException e) {}
//	}
//	
//	public void testAddResourceToCommunityUserProjectInvalidProjectId() throws Exception {
//		try {
//			UserManager.addResourceToCommunityUserProject(user.getUsername(), new Long(-1), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//			fail("Invalid project id should throw an exception");
//		} catch(ProjectException e) {}
//	}
	
	public void testAddResourceToCommunityUserProjectNullResource() throws Exception {
		try {
			UserManager.addResourceToCommunityUserProject(user.getUsername(), project.getId(), null, TEST_ALLOCATIONNAME);
			fail("Null resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testAddResourceToCommunityUserProjectEmptyResource() throws Exception {
		try {
			UserManager.addResourceToCommunityUserProject(user.getUsername(), project.getId(), "", TEST_ALLOCATIONNAME);
			fail("Empty resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testAddResourceToCommunityUserProjectInvalidResource() throws Exception {
		try {
			UserManager.addResourceToCommunityUserProject(user.getUsername(), project.getId(), "-1", TEST_ALLOCATIONNAME);
			fail("Invalid resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
//	public void testAddResourceToCommunityUserProjectNullAllocation() throws Exception {
//		try {
//			UserManager.addResourceToCommunityUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, null);
//			fail("Null allocation should throw an exception");
//		} catch(UserException e) {}
//	}
//	
//	public void testAddResourceToCommunityUserProjectEmptyAllocation() throws Exception {
//		try {
//			UserManager.addResourceToCommunityUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, "");
//			fail("Invalid allocation should throw an exception");
//		} catch(UserException e) {}
//	}
//	
//	public void testAddResourceToCommunityUserProject() throws Exception {
//		UserManager.addResourceToCommunityUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//		
//		assertTrue(new ResourceDao(session).userHasAccessToComputeResource(TEST_SYSTEMNAME));
//		
//		UserProjectResourceDao.remove(UserProjectResourceDao.get(user.getUsername(),project.getId(),TEST_SYSTEMNAME, TEST_ALLOCATIONNAME));
//	}

	/********************************************************************
	 * 
	 * 				Add Resource to UserProject Tests
	 * 
	 ****************************** **************************************/
	
	public void testAddResourceToUserProjectNullUsername() throws Exception {
		try {
			UserManager.addResourceToUserProject(null, project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Null username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddResourceToUserProjectEmptyUsername() throws Exception {
		try {
			UserManager.addResourceToUserProject("", project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Empty username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddResourceToUserProjectInvalidUsername() throws Exception {
		try {
			UserManager.addResourceToUserProject("-1", project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Invalid username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddResourceToUserProjectNullProjectId() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), null, TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Null project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testAddResourceToUserProjectInvalidProjectId() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), new Long(-1), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Invalid project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testAddResourceToUserProjectNullResource() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), project.getId(), null, TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Null resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testAddResourceToUserProjectEmptyResource() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), project.getId(), "", TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Empty resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testAddResourceToUserProjectInvalidResource() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), project.getId(), "-1", TEST_ALLOCATIONNAME, TEST_LOGINNAME);
			fail("Invalid resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testAddResourceToUserProjectNullAllocation() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, null, TEST_LOGINNAME);
			fail("Null allocation should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddResourceToUserProjectEmptyAllocation() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, "", TEST_LOGINNAME);
			fail("Invalid allocation should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddResourceToUserProjectNullLogin() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, null);
			fail("Null login name should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testAddResourceToUserProjectEmptyLogin() throws Exception {
		try {
			UserManager.addResourceToUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, "");
			fail("Empty login name should throw an exception");
		} catch(UserException e) {}
	}
	
	// already added in setup. don't need to test
//	public void testAddResourceToUserProject() throws Exception {
//		UserManager.addResourceToUserProject(user.getUsername(), project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME, TEST_LOGINNAME);
//		
//		assertTrue(new ResourceDao(session).userHasAccessToComputeResource(TEST_SYSTEMNAME));
//	}

	/********************************************************************
	 * 
	 * 				Disable UserProjectResource Tests
	 * 
	 ********************************************************************/
	
	public void testDisableUserFromProjectResourceNullUsername() throws Exception {
		try {
			UserManager.disableUserFromProjectResource(null, project.getId(), TEST_SYSTEMNAME);
			fail("Null username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProjectResourceEmptyUsername() throws Exception {
		try {
			UserManager.disableUserFromProjectResource("", project.getId(), TEST_SYSTEMNAME);
			fail("Empty username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProjectResourceInvalidUsername() throws Exception {
		try {
			UserManager.disableUserFromProjectResource("-1", project.getId(), TEST_SYSTEMNAME);
			fail("Invalid username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProjectResourceNullProjectId() throws Exception {
		try {
			UserManager.disableUserFromProjectResource(user.getUsername(), null, TEST_SYSTEMNAME);
			fail("Null project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testDisableUserFromProjectResourceInvalidProjectId() throws Exception {
		try {
			UserManager.disableUserFromProjectResource(user.getUsername(), new Long(-1), TEST_SYSTEMNAME);
			fail("Invalid project id should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testDisableUserFromProjectResourceNullResource() throws Exception {
		try {
			UserManager.disableUserFromProjectResource(user.getUsername(), project.getId(), null);
			fail("Null resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testDisableUserFromProjectResourceEmptyResource() throws Exception {
		try {
			UserManager.disableUserFromProjectResource(user.getUsername(), project.getId(), "");
			fail("Empty resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testDisableUserFromProjectResourceInvalidResource() throws Exception {
		try {
			UserManager.disableUserFromProjectResource(user.getUsername(), project.getId(), "-1");
			fail("Invalid resource should throw an exception");
		} catch(UserException e) {}
	}

	public void testDisableUserFromProjectResource() throws Exception {
		UserManager.disableUserFromProjectResource(user.getUsername(), project.getId(), TEST_SYSTEMNAME);
		
		assertFalse(new ResourceDao(session).userHasAccessToComputeResource(TEST_SYSTEMNAME));
	}
	
	/********************************************************************
	 * 
	 * 				Enable UserProjectResource Tests
	 * 
	 ********************************************************************/
	
	public void testEnableUserFromProjectResourceNullUsername() throws Exception {
		try {
			UserManager.enableUserFromProjectResource(null, project.getId(), TEST_SYSTEMNAME);
			fail("Null username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectResourceEmptyUsername() throws Exception {
		try {
			UserManager.enableUserFromProjectResource("", project.getId(), TEST_SYSTEMNAME);
			fail("Empty username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectResourceInvalidUsername() throws Exception {
		try {
			UserManager.enableUserFromProjectResource("-1", project.getId(), TEST_SYSTEMNAME);
			fail("Invalid username should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectResourceNullProjectId() throws Exception {
		try {
			UserManager.enableUserFromProjectResource(user.getUsername(), null, TEST_SYSTEMNAME);
			fail("Null project id should throw an exception");
		} catch(ProjectException e) {}
	}
	
	public void testEnableUserFromProjectResourceInvalidProjectId() throws Exception {
		try {
			UserManager.enableUserFromProjectResource(user.getUsername(), new Long(-1), TEST_SYSTEMNAME);
			fail("Invalid project id should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectResourceNullResource() throws Exception {
		try {
			UserManager.enableUserFromProjectResource(user.getUsername(), project.getId(), null);
			fail("Null resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testEnableUserFromProjectResourceEmptyResource() throws Exception {
		try {
			UserManager.enableUserFromProjectResource(user.getUsername(), project.getId(), "");
			fail("Empty resource should throw an exception");
		} catch(ResourceException e) {}
	}
	
	public void testEnableUserFromProjectResourceInvalidResource() throws Exception {
		try {
			UserManager.enableUserFromProjectResource(user.getUsername(), project.getId(), "-1");
			fail("Invalid resource should throw an exception");
		} catch(UserException e) {}
	}
	
	public void testEnableUserFromProjectResource() throws Exception {
		UserManager.enableUserFromProjectResource(user.getUsername(), project.getId(), TEST_SYSTEMNAME);
		
		assertTrue(new ResourceDao(session).userHasAccessToComputeResource(TEST_SYSTEMNAME));
	}
	
	/********************************************************************
	 * 
	 * 				Update Profile Tests
	 * 
	 ********************************************************************/

	public void testUpdateProfile() throws Exception {
		UserBean bean = user.toBean();
		bean.setPhone("999-999-9999");
		UserManager.updateProfile(session, bean);
		
		assertTrue(new UserDao(session)._get().getPhone().equals("999-999-9999"));
	}
	
//	public void testCleanUp() {
//		
//		UserDao udao = new UserDao();
//		User user = udao._get(TEST_USERNAME);
//		
//		ProjectDao projectDao = new ProjectDao();
//		Project project = projectDao._get(TEST_PROJECTNAME);
//		
//		UserProjectResource upr = UserProjectResourceDao.get(user.getUsername(), project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME);
//		UserProjectResourceDao.remove(upr);
//		assertNull(UserProjectResourceDao.get(user.getUsername(), project.getId(), TEST_SYSTEMNAME, TEST_ALLOCATIONNAME));
//		
//		UserProject up = UserProjectDao.get(user.getUsername(), project.getId());
//		UserProjectDao.remove(up);
//		assertNull(UserProjectDao.get(TEST_USERNAME, project.getId()));
//		
//		projectDao._remove(project);
//		assertNull(projectDao._get(TEST_PROJECTNAME));
//		
//		udao._remove(user);
//		assertNull(udao._get(TEST_USERNAME));
//	}
	
}
