package org.gridchem.service.impl;

import java.util.List;

import org.gridchem.service.ProjectService;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.project.ProjectManager;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;

/**
 * POJO to handle operations for users.
 * 
 * @author dooley
 * 
 */
public class ProjectServiceImpl implements ProjectService {

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ProjectService#getProjects(java.lang.String)
	 */
	public String getProjects(String sessionId) throws Exception {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		// User is authenticated, but session project is not loaded, so
		// access the session via the dao rather than the convenience method
		// to avoid the project check that would thrown an exception.
		GMSSession session = SessionDao.getByToken(sessionId);
		
		if (session == null) throw new SessionException("Invalid session key: " + sessionId);
		
		// adding the tg user's external projects here is the only clean way
		// insert them without creating multiple interfaces for different 
		// user types in the session and user services. 
		List<ProjectBean> projects = new ProjectDao(session).getAll();
		
		if (projects == null || projects.size() == 0 ) {
			if (session.getType().equals(AccessType.TERAGRID)) {
				List<ProjectBean> projectBeans = DaoFactory.getProjectDao(
						session).getAll();
				for (ProjectBean project: projectBeans) {
					ProjectManager.importTeraGridProject(session,project);
				}
				projects = new ProjectDao(session).getAll();
			} else {
				throw new ProjectException("User does not have any valid projects.");
			}
		}
		
		return ServiceUtil.xstream.toXML(projects);
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ProjectService#getProjectCollaborators(java.lang.String)
	 */
	public String getCollaborators(String sessionId)
			throws UserException, ProjectException, PermissionException,
			SessionException, ProviderException {

		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);
		System.out.println("hello");
		
		return ServiceUtil.xstream.toXML(DaoFactory.getProjectDao(
				manager.getSession()).getCollaborators(manager.getSessionProjectId()));
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ProjectService#getProjectCollaborators(java.lang.String, java.lang.String)
	 */
	public String getProjectCollaborators(String sessionId, String projectId)
	throws UserException, ProjectException, PermissionException,
			SessionException, ProviderException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		if (!ServiceUtil.isValid(projectId)) {
			throw new SessionException("Invalid session id");
		}
		
		Long pid = null;
		try {
			pid = Long.valueOf(projectId);
		} catch (Exception e) {
			throw new ProjectException("Invalid project id");
		}
		
		SessionManager manager = new SessionManager(sessionId);

		if (!DaoFactory.getProjectDao(manager.getSession()).isUserProject(pid)) {
			throw new PermissionException("Session user does not have access to this project.");
		}
			
		return ServiceUtil.xstream.toXML(DaoFactory.getProjectDao(
				manager.getSession()).getCollaborators(pid));
	}

	public String getCurrentProject(String sessionId) throws Exception {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		if (manager.getSessionProjectId() == null) {
			throw new ProjectException("No project has been assigned to the current session.");
		}
		
		return ServiceUtil.xstream.toXML(DaoFactory.getProjectDao(
				manager.getSession()).get());
	
	}
}
