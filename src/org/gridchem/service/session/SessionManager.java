package org.gridchem.service.session;

import java.io.ByteArrayInputStream;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.X509Credential;
import org.globus.gsi.GlobusCredentialException;
import org.globus.gsi.CredentialException;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.provider.teragrid.dao.TeraGridUserDao;
import org.gridchem.service.user.UserManager;
import org.gridchem.service.util.ServiceUtil;

public class SessionManager {
	private static Logger log = Logger.getLogger(SessionManager.class);
	
	private GMSSession session = null;
	public SessionManager(String token) {
		session = SessionDao.getByToken(token);
        
        if (session == null) {
           throw new SessionException("Invalid session key: " + token);
        }
       
//        if (session.getProjectId() == null) {
//        	throw new SessionException("No project assigned to current session.");
//        }
        
        if (session.getExpires().before(Calendar.getInstance())) {
        	throw new SessionException("Session has expired.");
        }
        
        try {
			if (isProxyExpired(session.getProxy())) {
				throw new SessionException("Credential has expired.");
			}
		} catch (GlobusCredentialException e) {
			// Shoudl failure to check proxy time left should be an exception?
			e.printStackTrace();
		}
	}
	
	
	public static GMSSession createSession(String type, AuthenticationBean bean) throws UserException{
		
		if (!ServiceUtil.isValid(type)) throw new SessionException("Invalid session type");
		if (bean == null) throw new SessionException("Invalid authentication object");
		
		// then create a session with their information
		GMSSession session = new GMSSession();
        session.setToken(SessionKeyGenerator.generateKey());
        session.setType(AccessType.valueOf(type));
        //session.setProxy(ServiceUtil.serializeGlobusCredential(bean.getCredential()));
        session.setProxy(ServiceUtil.serializeX509Credential(bean.getCredential()));
        
        //start modification - nik
        UserBean user = null;
        int count = 0; //for retries -nik
        while(count < 10 && user == null){
        user= new UserDao(session).get((type.equals(AccessType.TERAGRID)?"tg-":"") + bean.getUserName());
        }
        //end modification - nik

       // UserBean user = new UserDao(session).get((type.equals(AccessType.TERAGRID)?"tg-":"") + bean.getUserName());
        
        if (user == null) {
        	if (AccessType.valueOf(type).equals(AccessType.TERAGRID)) {
        		// user is an external TeraGrid user. Add them to the user db.
        		// project info will be looked up when they request a project
        		// listing later on.
        		user = TeraGridUserDao.findByUsername(
        				bean.getMyproxyUsername(), 
        				bean.getMyproxyPassword());
        		
        		try {
					UserManager.addUser(user, bean.getMyproxyPassword());
				} catch (Exception e) {
					throw new UserException("Unable to add TeraGrid user: " + bean.getUserName(),e);
				}
        	} else {
        		throw new UserException("No user found with username: " + bean.getUserName());
        	}
        }
        
        session.setUserId(user.getId());
        
        SessionDao.persist(session);


 
        log.info("Successful login for user: " + bean.getUserName());
        
        return session;
	}
	
	public GMSSession getSession() {
		return this.session;
	}
	
	public long renew() {
		session.setExpires(Calendar.getInstance());
		session.getExpires().add(Calendar.HOUR,2);
		SessionDao.persist(session);
		
		log.debug("Successfully renewed session: " + session.getId());
		
		return session.getExpires().getTimeInMillis();
	}
	
	public void destroy() {
		session.setExpires(Calendar.getInstance());
		session.setDestroyed(Calendar.getInstance());
		SessionDao.persist(session);
		
		log.debug("Successfully destroyed session: " + session.getId());
		
	}
	
	public long getRemainingTime() {
		return Math.max((session.getExpires().getTimeInMillis() - System.currentTimeMillis()), 0);
	}
	
	/**
	 * Convenience method to find the current session user.
	 * 
     * @return the user bean
     * @throws ProviderException 
     * 
     */
    public UserBean getSessionUser() throws ProviderException {
        
        return new UserDao(session).get();
    }
    
    /**
     * @return the userId
     * @throws ProviderException 
     * 
     */
    public Long getSessionUserId() {
        
    	return session.getUserId();
    }
    
    private static boolean isProxyExpired(String proxyString) throws GlobusCredentialException {
    	
    	if (!ServiceUtil.isValid(proxyString)) return true;
    	
		ByteArrayInputStream bis = new ByteArrayInputStream(proxyString.getBytes());
	
                try {	
		//GlobusCredential cred = new GlobusCredential(bis);
		X509Credential cred = new X509Credential(bis);
		cred.verify();
		
		return (cred == null || cred.getTimeLeft() <= 0 );
        	} catch (CredentialException e){ return false; } 	
	}
    
    public void setSessionProject(Long projectId) {
    	
    	if (DaoFactory.getProjectDao(session).isUserProject(projectId)) {
			session.setProjectId(projectId);
			SessionDao.persist(session);
		} else {
			throw new PermissionException("Project is not valid for user");
		}
    }

    
    public ProjectBean getSessionProject() {
    	return new ProjectDao(session).get();
    }
    
    public Long getSessionProjectId() {
    	
    	return session.getProjectId();
    }

	public boolean isSessionUser(UserBean userBean) {
		
		try {
			return (userBean.getId().equals(session.getUserId()));
		} catch (Exception e) {
			return false;
		}
	}
}
