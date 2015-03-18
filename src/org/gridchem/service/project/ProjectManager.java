package org.gridchem.service.project;

import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ProjectValidationException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.User;
import org.gridchem.service.model.enumeration.ProjectStatusType;
import org.gridchem.service.provider.teragrid.dao.TeraGridResourceDao;
import org.gridchem.service.sync.iis.beans.SystemDTO;
import org.gridchem.service.user.UserManager;
import org.gridchem.service.util.ServiceUtil;

public class ProjectManager {
	private static Logger log = Logger.getLogger(ProjectManager.class);
	
	public static void addProject(ProjectBean bean) throws ProjectException, ProjectValidationException {
		
		if (bean == null) throw new ProjectException("Cannot add null project");
		
		log.debug("Request to create project: " + bean.getName());
		
		Project project = new Project(bean);
		
		validate(bean);
		
		new ProjectDao()._add(project);
	}
	
	public static void updateStatus(Long projectId, ProjectStatusType status) {
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao._get(projectId);
		
		if (status == null)  throw new ProjectException("Project status cannot be null");
		if (project == null) throw new ProjectException("No project with matching id found");
		
		project.setStatus(status);
		projectDao.persist(project);
	}
	
	public static void updateAllocation(Long projectId, Double newAllocation) {
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao._get(projectId);
		
		if (newAllocation == null) throw new ProjectException("Allocation increase cannot be null"); 
		if (project == null) throw new ProjectException("No project with matching id found");
		
		project.getUsage().setAllocated(project.getUsage().getAllocated() + newAllocation);
		projectDao.persist(project);
	}
	
	public static void importTeraGridProject(GMSSession session, ProjectBean bean) throws Exception {
		
		if (session == null) throw new SessionException("Session cannot be null");
		if (bean == null) throw new ProjectException("Imported project bean cannot be null");
		
		log.debug("Request to create local entry for project: " + bean.getName());
		
		// add user to project
		ProjectDao projectDao = new ProjectDao(session);
		UserDao userDao = new UserDao(session);
		ResourceDao resourceDao = new ResourceDao(session);
		
		// first we need to create a project record based on the bean
		// add project
		validate(bean);
		
		Project project = new Project(bean);
		projectDao._add(project);
		
		// get the current user
		User user = userDao._get();
		
		// add the session user to the new project. default the archive to mss
		UserManager.addUserToProject(user.getUsername(),project.getId(), "NCSA Mass Storage");
		
		// finally, for each user resource, a userprojectresource record must
		// be created. We add upr for each resource of the external user.
		// this is a sloooow loop
		List<SystemDTO> systems = new TeraGridResourceDao(session)._getComputeResources();
		for (ComputeResource resource: resourceDao._getAllCompute()) {
			for (SystemDTO system: systems) {
				if (resource.getName().contains(system.getName()) ||
						system.getName().contains(resource.getName())) {
					UserManager.addResourceToUserProject(user.getUsername(),
							project.getId(), 
							resource.getName(), 
							"default",
							system.getLocalUsername());
				}
			}
		}
		
		log.info("Successfully added new project " + bean.getName() + " to the database.");
		
	}
	
	public static void validate(ProjectBean bean) throws ProjectValidationException {
		
		if (bean == null) throw new ProjectValidationException("Project bean cannot be null");
		
		if (!ServiceUtil.isValid(bean.getComment())) 
			throw new ProjectValidationException("Project comment cannot be null");
		
		if (!ServiceUtil.isValid(bean.getDescription())) 
			throw new ProjectValidationException("Project description cannot be null");
		
		if (!ServiceUtil.isValid(bean.getStartDate())) 
			throw new ProjectValidationException("Project start date cannot be null");
		
		if (!ServiceUtil.isValid(bean.getEndDate())) 
			throw new ProjectValidationException("Project end date cannot be null");
		
		if (!bean.getEndDate().after(bean.getStartDate())) 
			throw new ProjectValidationException("Project end date cannot occur before start date");
		
//		if (!ServiceUtil.isValid(bean.getFundedProjectId())) 
//			throw new ProjectValidationException("Project funding id cannot be null");
		
//		if (!ServiceUtil.isValid(bean.getInstitution())) 
//			throw new ProjectValidationException("Project institution cannot be null");
		
		if (!ServiceUtil.isValid(bean.getName())) 
			throw new ProjectValidationException("Project name cannot be null");
		
		if (!ServiceUtil.isValid(bean.getPi())) 
			throw new ProjectValidationException("Project pi cannot be null");
		
		if (bean.getStatus() == null) 
			throw new ProjectValidationException("Project status cannot be null");
		
//		if (bean.getInstitutionType() == null) 
//			throw new ProjectValidationException("Project institution type cannot be null");
//		
//		if (bean.getType() == null)  
//			throw new ProjectValidationException("Project type cannot be null");
//		
		if (bean.getUsage() == null) 
			throw new ProjectValidationException("Project usage cannot be null");
	}

}
