package org.gridchem.service.user;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.dao.UserProjectDao;
import org.gridchem.service.dao.UserProjectResourceDao;
import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.Usage;
import org.gridchem.service.model.User;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.model.UserProjectResource;
import org.gridchem.service.model.enumeration.UserClassificationType;
import org.gridchem.service.model.enumeration.UserPermissionType;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.crypt.SHA1;

public class UserManager {
	private static Logger log = Logger.getLogger(UserManager.class);
	
//	private GMSSession session;
//	
//	public UserManager(GMSSession session) {
//		this.session = session;
//	}
	
//	public void makeBean(User user) {
//		UserBean bean = new UserBean();
//		bean.setImProvider(IMProviderType.MSN.name());
//		bean.setIm(user.getIm());
//		bean.setEmail(user.getEmail());
//		bean.setFax(user.getFax());
//		bean.setCell(user.getCell());
//		bean.setPhone(user.getPhone());
//		bean.setAddress(user.getAddress());
//		bean.setDepartment(user.getDepartment());
//		bean.setInstitute(user.getInstitution());
//		bean.setClassification(user.getClassification().name());
//		bean.setUserName(user.getUsername());
//		bean.setMiddleInitial(user.getMiddleInitial());
//		bean.setLastName(user.getLastName());
//		bean.setFirstName(user.getFirstName());
//		bean.setId(user.getId());
//	}
			
//    public void validateProject(Long projectID) {
//        boolean typeFound = false;
//        
//        // instantiate the proper project based on requested access type
//        
//        for (Iterator iter = getProjectsBeans().iterator();iter.hasNext();) {
//            UserProject up = ((UserProject)iter.next());
//            Project project = new ProjectDAO().getProjectById(projectID,false);
//            if (project.getProjectId().equals(projectID)) {
//                try {
//                    Hibernate.initialize(project);
//                } catch (HibernateException e) {
//                    // TODO Auto-generated catch block
//                    throw new ProjectException("Could not initialize user project " + project.getProjectName());
//                }
//                typeFound = true;
//                setCurrentProject(project);
//            }
//        }
//    
//        if(!typeFound){
//            throw new UserException("Project " + projectID +
//                    " is not associated with user " + getUserName());
//        } 
//        
//    }
    
    /**************************************************************************
     * 
     * Administration method to add a user. The UserBean is validated 
     * for correctness.
     * 
     * 
     * @throws ProfileValidationException 
     * @throws ProviderException 
     * @throws InfrastructureException
     *  
     **************************************************************************/
    
	public static void addUser(UserBean bean, String password) 
	throws InfrastructureException, ProfileValidationException {
		
		if (bean == null) throw new ProjectException("Cannot add null user");
		if (!ServiceUtil.isValid(password)) throw new UserException("Invalid user password");
		
		log.debug("Request to create local entry for user: " + bean.getUserName());
		
		validate(bean);
		
		User user = new User(bean);
		user.setPassword(SHA1.encrypt(password));
		user.setOriginalPassword(SHA1.encrypt(password));
		new UserDao()._add(user);
		
		log.info("Successfully added new user " + bean.getUserName() + " to the database.");
		
	}
	
	public static void addUserToCommunityProject(String username, Long projectId) {
		
		addUserToProject(username, projectId, "NCSA Mass Storage");
		
	}
	
	public static void addUserToProject(String username, Long projectId, String storageName) {
		
		if (!ServiceUtil.isValid(username)) throw new UserException("Invalid user id");
		
		if (projectId == null) throw new ProjectException("Invalid project id");
		
		
		User user = new UserDao()._get(username);
		if (user == null) throw new UserException("No user with given username found.");
		
		Project project = new ProjectDao()._get(projectId);
		if (project == null) throw new ProjectException("No project with given id found.");
		
		GMSSession session = new GMSSession();
		session.setUserId(user.getId());
		session.setProjectId(project.getId());
		
		// add the user project so we can add the UserProjectResource entries
		UserProject up = new UserProject(user,project);
		up.setUserType(UserPermissionType.USER);
		up.setUsage(project.getUsage());
		up.setMss(new ResourceDao(session)._getStorage(storageName));
		
		if (!UserProjectDao.exists(up)) {
			UserProjectDao.add(up);
		}
		
	}
	
	/**
	 * Disable the user on a specific project by setting the UserProject.enabled 
	 * flag to false.
	 * 
	 * @param username
	 * @param projectId
	 */
	public static void enableUserFromProject(String username, Long projectId) throws UserException {
		
		UserProject up = UserProjectDao.get(username, projectId);
		
		if (up == null) throw new UserException("No match found for user and project");
		
		up.setEnabled(true);
		
		UserProjectDao.update(up);
		
	}
	
	/**
	 * Disable the user on a specific project by setting the UserProject.enabled 
	 * flag to false.
	 * 
	 * @param username
	 * @param projectId
	 */
	public static void disableUserFromProject(String username, Long projectId) throws UserException {
		
		UserProject up = UserProjectDao.get(username, projectId);
		
		if (up == null) throw new UserException("No match found for user and project");
		
		up.setEnabled(false);
		
		UserProjectDao.update(up);
		
	}
	
	/**
	 * Enable the user on a specific project by setting the UserProject.enabled 
	 * flag to false.
	 * 
	 * @param username
	 * @param projectId
	 */
	public static void enableUserFromProjectResource(String username, Long projectId, String resource) throws UserException {
		
		List<UserProjectResource> uprs = UserProjectResourceDao.getAllForUserProjectResource(username, projectId, resource);
		
		if (uprs.isEmpty()) throw new UserException("No match found for user, project, and resource");
		
		for (UserProjectResource upr: uprs) {
			upr.setEnabled(true);
			UserProjectResourceDao.update(upr);
		}
		
	}
	
	/**
	 * Disable the user on a specific project by setting the UserProject.enabled 
	 * flag to false.
	 * 
	 * @param username
	 * @param projectId
	 */
	public static void disableUserFromProjectResource(String username, Long projectId, String resource) throws UserException {
		
		List<UserProjectResource> uprs = UserProjectResourceDao.getAllForUserProjectResource(username, projectId, resource);
		
		if (uprs.isEmpty()) throw new UserException("No match found for user, project, and resource");
		
		for (UserProjectResource upr: uprs) {
			upr.setEnabled(false);
			UserProjectResourceDao.update(upr);
		}
		
	}
	
	
	
	/**
	 * Creates UserProjectResource entries based on the parameters given.  This method
	 * is used once a UserProject entry has been created.  
	 * 
	 * @param username
	 * @param projectId
	 * @param resource
	 * @throws UserException
	 */
	public static void addResourceToCommunityUserProject(String username, Long projectId, String resource, String allocationName) throws UserException {
		
		if (!ServiceUtil.isValid(resource)) throw new ResourceException("Invalid resource name");
		
		String loginName = UserProjectResourceDao.getCommunityLoginName(resource);
		
		if (loginName == null) throw new ResourceException("No community login name found for resource");
		
		addResourceToUserProject(username, projectId, resource, allocationName, loginName);
		
	}	
	
	
	
	/**
	 * Creates UserProjectResource entries for non-community users based on the 
	 * parameters given.  This method is used once a UserProject entry has been 
	 * created.  
	 * 
	 * @param username
	 * @param projectId
	 * @param resource
	 * @throws UserException
	 */
	public static void addResourceToUserProject(String username, Long projectId, String resource, String allocationName, String loginName) throws UserException {
		
		if (!ServiceUtil.isValid(username)) throw new UserException("Invalid user id");
		
		if (!ServiceUtil.isValid(resource)) throw new ResourceException("Invalid resource name");
		
		if (!ServiceUtil.isValid(allocationName)) throw new UserException("Invalid allocation name");
		
		if (!ServiceUtil.isValid(loginName)) throw new UserException("Invalid login name");
		
		if (projectId == null) throw new ProjectException("Invalid project id");
		
		User user = new UserDao()._get(username);
		if (user == null) throw new UserException("No user with given username found.");
		
		Project project = new ProjectDao()._get(projectId);
		if (project == null) throw new ProjectException("No project with given id found.");
		
		ComputeResource system = new ResourceDao(null)._getCompute(resource);
		if (system == null) throw new ResourceException("No project with given id found.");
		
		UserProject up = new UserProject(user, project);
		
		if (!UserProjectDao.exists(up)) {
			throw new UserException("User is not assigned to the given project");
		}
		// now add all the resources associated with the given project in the
		UserProjectResource upr = new UserProjectResource(system, up, loginName, user.getPermission(), allocationName, new Usage());
		UserProjectResourceDao.add(upr);
	}
	/**
	 * Update user information.  A call to validate() will be made to ensure user 
	 * info is valid.  Username and ID cannot be updated.
	 * 
	 * @param session
	 * @param bean
	 * @throws ProfileValidationException 
	 */
	public static void updateProfile(GMSSession session, UserBean bean) throws ProfileValidationException {
		
		log.debug("Request to update local entry for user: " + bean.getUserName());
		
		if (session == null) throw new UserException("No active user session defined");
		if (bean == null) throw new UserException("User cannot be null");
		
		validate(bean);
		
		UserDao dao = new UserDao(session);
		User user = dao._get(bean.getUserName());

		user.setAddress(bean.getAddress());
		user.setDepartment(bean.getDepartment());
		user.setCell(bean.getCell());
		user.setPhone(bean.getPhone());
		user.setEmail(bean.getEmail());
		user.setIm(bean.getIm());
		user.setInstitution(bean.getInstitute());
		user.setLastUpdated(new Date());
		
		dao._update(user);
		
		log.info("Successfully updated profile information for user " + bean.getUserName() + " to the database.");
	}
	
	/**
	 * Ensure valid values for user profile are present.
	 * 
	 * @param bean
	 * @throws ProfileValidationException 
	 */
	public static void validate(UserBean bean) throws ProfileValidationException {
		
		if (bean == null) throw new ProfileValidationException("Cannot add a null user");
		
//		if (!ServiceUtil.isValid(bean.getImProvider()) || IMProviderType.valueOf(bean.getImProvider()) == null) {
//			throw new ProfileValidationException("Invalid IM provider type");
//		}
//		
//		if (!ServiceUtil.isValid(bean.getIm())) {
//			throw new ProfileValidationException("Invalid IM handle");
//		}
		
		if (!ServiceUtil.isValidEmailAddress(bean.getEmail())) {
			throw new ProfileValidationException("Invalid email address");
		}
		
		if (!ServiceUtil.isValidPhoneNumber(bean.getFax())) {
			throw new ProfileValidationException("Invalid fax number");
		}
		
//		if (!ServiceUtil.isValidPhoneNumber(bean.getCell())) {
//			throw new ProfileValidationException("Invalid cell number");
//		}
		
		if (!ServiceUtil.isValidPhoneNumber(bean.getPhone())) {
			throw new ProfileValidationException("Invalid phone number");
		}
		
		if (!ServiceUtil.isValid(bean.getAddress())) {
			throw new ProfileValidationException("Invalid address");
		}
		
		if (!ServiceUtil.isValid(bean.getDepartment())) {
			throw new ProfileValidationException("Invalid department");
		}
		
		if (!ServiceUtil.isValid(bean.getInstitute())) {
			throw new ProfileValidationException("Invalid institution");
		}
		
		if (ServiceUtil.isValid(bean.getClassification())) {
			try {
				UserClassificationType.valueOf(bean.getClassification());
			} catch (Exception e) {
				throw new ProfileValidationException("Invalid user classification type");
			}
		} else {
			throw new ProfileValidationException("Invalid user classification type");
		}
		
		if (!ServiceUtil.isValid(bean.getUserName())) {
			throw new ProfileValidationException("Invalid username");
		}
		
		if (!ServiceUtil.isValid(bean.getLastName())) {
			throw new ProfileValidationException("Invalid last name");
		}
		
		if (!ServiceUtil.isValid(bean.getFirstName())) {
			throw new ProfileValidationException("Invalid first name");
		}
		
	}
}
