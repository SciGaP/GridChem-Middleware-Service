package org.gridchem.service;

import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SiteException;

public interface ResourceService {

	/**
	 * Returns all resources to which the user has access.  For TeraGrid users
	 * this will only be their TeraGrid resources supported by the CCG.
	 * 
	 * @param sessionId
	 * @return
	 * @throws ResourceException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getResources(String sessionId)
			throws ResourceException, PermissionException, SessionException,
			ProviderException;

	/**
	 * Returns all compute resources to which the user has access.  For TeraGrid users
	 * this will only be their TeraGrid resources supported by the CCG.
	 * 
	 * @param sessionId
	 * @return
	 * @throws ResourceException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getComputeResources(String sessionId)
			throws ResourceException, PermissionException, SessionException,
			ProviderException;

	/**
	 * Returns all compute resources at the given site to which the user 
	 * has access.  For TeraGrid users this will only be their TeraGrid 
	 * resources supported by the CCG.
	 * 
	 * @param sessionId
	 * @param siteName
	 * @return
	 * @throws ResourceException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 * @throws ParameterException
	 */
	public abstract String getComputeResourcesForSite(String sessionId,
			String siteName) throws ResourceException, PermissionException,
			SessionException, ProviderException, SiteException;

	/**
	 * Returns all storage resources to which the user has access.  For TeraGrid users
	 * this will only be their TeraGrid resources supported by the CCG.
	 * 
	 * @param sessionId
	 * @return
	 * @throws ResourceException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getStorageResources(String sessionId)
			throws ResourceException, PermissionException, SessionException,
			ProviderException;

	/**
	 * Returns all storage resources at the given site to which the user has 
	 * access.  For TeraGrid users this will only be their TeraGrid resources 
	 * supported by the CCG.
	 * 
	 * @param sessionId
	 * @param siteName
	 * @return
	 * @throws ResourceException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 * @throws ParameterException
	 */
	public abstract String getStorageResourcesForSite(String sessionId,
			String siteName) throws ResourceException, SessionException,
			ProviderException, SiteException;

}