package org.gridchem.service;

import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SoftwareException;

public interface SoftwareService {

	/**
	 * Returns a serialized list of all the software packages
	 * to which the user has access.
	 * 
	 * @param sessionId
	 * @return
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getAllSoftware(String sessionId)
			throws PermissionException, SessionException, ProviderException;

	/**
	 * Returns a serialized list of software installations for the user
	 * on the given resource. For TeraGrid users, they will only see 
	 * installs on CCG resources.
	 * 
	 * @param sessionId
	 * @param sResource
	 * @return
	 * @throws ResourceException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ParameterException
	 * @throws ProviderException
	 */
	public abstract String getSoftwareInstallationsForResource(
			String sessionId, String sResource) throws ResourceException,
			PermissionException, SessionException, ProviderException;

	/**
	 * Returns a serialized list of all software installations for the user.
	 * For TeraGrid users, they will only see installs on CCG resources.
	 * 
	 * @param sessionId
	 * @return
	 * @throws ResourceException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getSoftwareInstallations(String sessionId)
			throws ResourceException, PermissionException, SessionException,
			ProviderException;

	/**
	 * Returns a list of software installations on which the given software is installed.
	 * 
	 * @param sessionId
	 * @param sSoftware
	 * @return
	 * @throws SoftwareException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 * @throws ParameterException
	 */
	public abstract String getSoftwareInstallationsForSoftware(
			String sessionId, String sSoftware) throws SoftwareException,
			PermissionException, SessionException, ProviderException,
			ParameterException;

}