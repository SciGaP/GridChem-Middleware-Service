package org.gridchem.service;

import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SoftwareException;
import org.gridchem.service.exceptions.UserException;

public interface AdministrationService {

	// User interfaces
	public abstract String addUser(String sessionId, String sUser,
			String sProjectBean) throws UserException, PermissionException,
			SessionException;

	public abstract String disableUser(String sessionId, String sUser)
			throws UserException, PermissionException, SessionException;

	// Resource interfaces
	public abstract String addResource(String sessionId, String sResourceBean)
			throws ResourceException, PermissionException, SessionException;

	public abstract String disableResource(String sessionId,
			String sResourceBean) throws ResourceException,
			PermissionException, SessionException;

	public abstract String enableResource(String sessionId, String sResourceBean)
			throws ResourceException, PermissionException, SessionException;

	// Project interfaces
	public abstract String addProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException;

	public abstract String renewProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException;

	public abstract String enableProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException;

	public abstract String disableProject(String sessionId, String sProjectBean)
			throws ProjectException, PermissionException, SessionException;

	// Software Interfaces
	public abstract String addSoftware(String sessionId, String sSoftwareBean)
			throws SoftwareException, PermissionException, SessionException;

	public abstract String disableSoftware(String sessionId,
			String sSoftwareBean) throws SoftwareException,
			PermissionException, SessionException;

	public abstract String addSoftwareInstallation(String sessionId,
			String sSoftwareBean, String sResourceBean)
			throws ResourceException, SoftwareException, PermissionException,
			SessionException;

	public abstract String disableSoftwareInstallation(String sessionId,
			String sSoftwareBean, String sResourceBean)
			throws ResourceException, SoftwareException, PermissionException,
			SessionException;

	public abstract String addUserToBlacklist(String sessionId,
			String sUserBean, String sSoftwareBean) throws UserException,
			SoftwareException, PermissionException, SessionException;

	public abstract String removeUserFromBlacklist(String sessionId,
			String sUserBean, String sSoftwareBean) throws UserException,
			SoftwareException, PermissionException, SessionException;

}