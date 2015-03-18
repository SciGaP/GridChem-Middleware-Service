package org.gridchem.service.impl;


import org.gridchem.service.UserService;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.PreferencesException;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.user.PreferenceManager;
import org.gridchem.service.user.UserManager;
import org.gridchem.service.util.ServiceUtil;


/**
 * POJO to handle operations for users.
 * 
 * @author dooley
 *
 */
public class UserServiceImpl implements UserService {

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.UserService#getProfile(java.lang.String)
	 */
	public String getProfile(String sessionId) 
	throws UserException, SessionException, ProviderException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(manager.getSessionUser());
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.UserService#updateProfile(java.lang.String, java.lang.String)
	 */
	public String updateProfile(String sessionId, String sUserBean) 
	throws UserException, PermissionException, SessionException, ParameterException, InfrastructureException, ProfileValidationException, ProviderException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		if (!ServiceUtil.isValid(sUserBean)) {
			throw new ParameterException("No user profile provided");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		UserBean userBean = (UserBean)ServiceUtil.xstream.fromXML(sUserBean);
		
		if (manager.isSessionUser(userBean)) {
			UserManager.updateProfile(manager.getSession(),userBean);
		} else {
			throw new PermissionException("Permission denied.");
		}
		return "success";
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.UserService#getPreferences(java.lang.String)
	 */
	public String getPreferences(String sessionId)
	throws PreferencesException, SessionException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		return PreferenceManager.getPreferencesForUser(manager.getSessionUserId());
		
	}
}
