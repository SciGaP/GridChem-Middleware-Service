package org.gridchem.service.impl;

import org.gridchem.service.AdministrationService;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SoftwareException;
import org.gridchem.service.exceptions.UserException;

/**
 * POJO to handle operations for users.
 * 
 * @author dooley
 * 
 */
public class AdministrationServiceImpl implements AdministrationService {

	// User interfaces
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#addUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String addUser(String sessionId, String sUser, String sProjectBean)
			throws UserException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#disableUser(java.lang.String, java.lang.String)
	 */
	public String disableUser(String sessionId, String sUser)
			throws UserException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	// Resource interfaces
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#addResource(java.lang.String, java.lang.String)
	 */
	public String addResource(String sessionId, String sResourceBean)
			throws ResourceException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#disableResource(java.lang.String, java.lang.String)
	 */
	public String disableResource(String sessionId, String sResourceBean)
			throws ResourceException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#enableResource(java.lang.String, java.lang.String)
	 */
	public String enableResource(String sessionId, String sResourceBean)
			throws ResourceException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	// Project interfaces
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#addProject(java.lang.String, java.lang.String)
	 */
	public String addProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#renewProject(java.lang.String, java.lang.String)
	 */
	public String renewProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#enableProject(java.lang.String, java.lang.String)
	 */
	public String enableProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#disableProject(java.lang.String, java.lang.String)
	 */
	public String disableProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	// Software Interfaces
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#addSoftware(java.lang.String, java.lang.String)
	 */
	public String addSoftware(String sessionId, String sSoftwareBean)
			throws SoftwareException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#disableSoftware(java.lang.String, java.lang.String)
	 */
	public String disableSoftware(String sessionId, String sSoftwareBean)
			throws SoftwareException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#addSoftwareInstallation(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String addSoftwareInstallation(String sessionId,
			String sSoftwareBean, String sResourceBean)
			throws ResourceException, SoftwareException, PermissionException,
			SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#disableSoftwareInstallation(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String disableSoftwareInstallation(String sessionId,
			String sSoftwareBean, String sResourceBean)
			throws ResourceException, SoftwareException, PermissionException,
			SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#addUserToBlacklist(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String addUserToBlacklist(String sessionId, String sUserBean,
			String sSoftwareBean) throws UserException, SoftwareException,
			PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.AdministrationService#removeUserFromBlacklist(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String removeUserFromBlacklist(String sessionId, String sUserBean,
			String sSoftwareBean) throws UserException, SoftwareException,
			PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}
}
