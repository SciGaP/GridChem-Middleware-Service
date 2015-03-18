package org.gridchem.service;

import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;

public interface SessionService {

	public abstract String getAuthenticationTypes();

	/**
	 * Here is where we broker authentication.  If the user authenticates successfully
	 * their information is used to create a new user and project entry in the db. 
	 * 
	 * @param username
	 * @param pass
	 * @param authMap
	 * @param type
	 * @return
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ParameterException
	 * @throws ProviderException
	 */
	public abstract String createSession(String username, String pass,
			String authMap, String type) throws PermissionException,
			SessionException, ParameterException, ProviderException;

	/**
	 * Extend the existing session by a standard session interval.
	 * 
	 * @param sessionId
	 * @return
	 * @throws PermissionException
	 * @throws SessionException
	 */
	public abstract long renewSession(String sessionId)
			throws PermissionException, SessionException;

	/**
	 * Immediately expire the current session.
	 *  
	 * @param sessionId
	 * @throws PermissionException
	 * @throws SessionException
	 */
	public abstract String destroySession(String sessionId)
			throws PermissionException, SessionException;

	/**
	 * Get the remaining time in milliseconds of the current session.
	 * 
	 * @param sessionId
	 * @return
	 * @throws PermissionException
	 * @throws SessionException
	 */
	public abstract String getRemainingTime(String sessionId)
			throws PermissionException, SessionException;

	/**
	 * Set assign the project with the given projectid as the default
	 * user project for this session. 
	 * 
	 * @param sessionId
	 * @param sProjectId
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ProviderException
	 * @throws ParameterException
	 */
	public abstract String setSessionProject(String sessionId, String sProjectId)
			throws PermissionException, SessionException, ProviderException;

}