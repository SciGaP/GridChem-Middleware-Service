package org.gridchem.service;

import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.PreferencesException;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.UserException;

public interface UserService {

	/**
	 * Get the local user profile information. For TeraGrid users,
	 * their profile information is pulled and stored locally at 
	 * their first login.
	 * 
	 * @param sessionId
	 * @return
	 * @throws UserException
	 * @throws SessionException
	 * @throws ProviderException
	 */
	public abstract String getProfile(String sessionId) throws UserException,
			SessionException, ProviderException;

	/**
	 * Update the user's local profile information.  If the user is
	 * a TeraGrid user, only the local profile info is updated. Their
	 * TeraGrid info remains unchanged.
	 * 
	 * @param sessionId
	 * @param sUserBean
	 * @throws UserException
	 * @throws PermissionException
	 * @throws SessionException
	 * @throws ParameterException
	 * @throws InfrastructureException
	 * @throws ProfileValidationException
	 * @throws ProviderException
	 */
	public abstract String updateProfile(String sessionId, String sUserBean)
			throws UserException, PermissionException, SessionException,
			ParameterException, InfrastructureException,
			ProfileValidationException, ProviderException;

	/**
	 * Return the preferences for the user. Each user has a single
	 * preference associated with their profile.
	 * 
	 * @param sessionId
	 * @return
	 * @throws UserException
	 * @throws SessionException
	 */
	public abstract String getPreferences(String sessionId)
			throws PreferencesException, SessionException;
	
	

}