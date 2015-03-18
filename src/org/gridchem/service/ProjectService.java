package org.gridchem.service;

import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ProjectValidationException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.UserException;

public interface ProjectService {

	/**
	 * Get the current project for the user session. This returns the fully 
	 * populated ProjectBean as opposed to the lightweight ones returned 
	 * during authentication. 
	 * 
	 * @param sessionId
	 * @return serialized ProjectBean with allocations and resources.
	 * @throws Exception
	 */
	public abstract String getCurrentProject(String sessionId) throws Exception;
	
	/**
	 * Return a serialized list of project beans for the current session user.
	 * 
	 * @param sessionId
	 * @return
	 * @throws ProjectException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 * TODO: when do we update imported projects?
	 * @throws ProjectValidationException 
	 */
	public abstract String getProjects(String sessionId) throws Exception;

	/**
	 * Find all project collaborators for the current session project.
	 * 
	 * @param sessionId
	 * @return serialized list of CollaboratorBeans
	 * @throws UserException
	 * @throws ProjectException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getCollaborators(String sessionId)
			throws UserException, ProjectException, PermissionException,
			SessionException, ProviderException;

	/**
	 * Find all project collaborators for the given project.
	 * 
	 * @param sessionId
	 * @param projectId
	 * @return serialized list of CollaboratorBeans
	 * @throws UserException
	 * @throws ProjectException
	 * @throws PermissionException if user is not part of given project
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getProjectCollaborators(String sessionId,
			String projectId) throws UserException, ProjectException,
			PermissionException, SessionException, ProviderException;

}