package org.gridchem.service.impl;


import java.util.Map;

import org.apache.log4j.Logger;
import org.gridchem.service.SessionService;
import org.gridchem.service.authentication.LoginFactory;
import org.gridchem.service.authentication.LoginProvider;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.job.JobManager;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;


/**
 * POJO to handle operations for sessions.
 * 
 * @author dooley
 *
 */
public class SessionServiceImpl implements SessionService {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(SessionServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SessionService#getAuthenticationTypes()
	 */
	public String getAuthenticationTypes() {
		return ServiceUtil.xstream.toXML(AccessType.values());
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SessionService#createSession(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String createSession(String username, String pass, String authMap, String type) 
	throws PermissionException, SessionException, ParameterException, ProviderException {
		
		HibernateUtil.flush();
        
		AccessType accessType;
		Map map = null;
		
		if (!ServiceUtil.isValid(username) || !ServiceUtil.isValid(pass)) {
			throw new PermissionException("Invalid username/password combination.");
		}
		
		if (!ServiceUtil.isValid(type)) {
			throw new ProviderException("Invalid access type");
		} else {
			try {
				accessType = AccessType.valueOf(type);
			} catch (Exception e) {
				throw new ProviderException("Invalid access type");
			}
		}
		
		if (ServiceUtil.isValid(authMap)) {
			map = (Map)ServiceUtil.xstream.fromXML(authMap);
			
			if (!accessType.equals(AccessType.COMMUNITY)) {
				if (!ServiceUtil.isValid(map)) {
					throw new ParameterException("Authentication parameters cannot be null");
				}
			}
		}
		
		// first we must authenticate the user
		LoginProvider loginProvider = LoginFactory.getLoginProvider(accessType);
        AuthenticationBean authBean = loginProvider.login(username, pass, map);
        
        // create a new session from the authenticationbean
        GMSSession session = SessionManager.createSession(type, authBean);
        
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        
        // return the session token to the user
        return session.getToken();
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SessionService#renewSession(java.lang.String)
	 */
	public long renewSession(String sessionId) 
	throws PermissionException, SessionException {
		
		HibernateUtil.flush();
        
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		HibernateUtil.closeSession();
		
		return manager.renew();
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SessionService#destroySession(java.lang.String)
	 */
	public String destroySession(String sessionId) 
	throws PermissionException, SessionException {
		
		HibernateUtil.flush();
        
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		manager.destroy();
		
		HibernateUtil.closeSession();
		
		return "success";
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SessionService#getRemainingTime(java.lang.String)
	 */
	public String getRemainingTime(String sessionId) 
	throws PermissionException, SessionException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		HibernateUtil.closeSession();
		
		return manager.getRemainingTime()+"";
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SessionService#setSessionProject(java.lang.String, java.lang.String)
	 */
	public String setSessionProject(String sessionId, String sProjectId) 
	throws PermissionException, SessionException, ProviderException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		Long projectId = null;
		try {
			projectId = Long.valueOf(sProjectId);
		} catch (Exception e) {
			throw new ProjectException("Invalid project id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		manager.setSessionProject(projectId);
		
		HibernateUtil.closeSession();
		
		return "success";
	}
}
