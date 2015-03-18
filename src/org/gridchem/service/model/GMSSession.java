/* 
 * Created on Feb 5, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.model;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.model.enumeration.AccessType;

public class GMSSession {
    public static Logger log = Logger.getLogger(GMSSession.class.getName());
    
    private Long id = null;
    private String token;
    private Calendar created = Calendar.getInstance();
    private Calendar destroyed;
    private Calendar expires;
    private Long userId;
    private Long projectId;
    private String proxy;
    private AccessType type;
    
    public GMSSession() {
    	created = Calendar.getInstance();
        expires = Calendar.getInstance();
        expires.add(Calendar.HOUR_OF_DAY, 24);
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the key
     */
    public String getToken() {
        return token;
    }

    /**
     * @param key the key to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    /**
     * @return the created
     */
    public Calendar getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Calendar created) {
        this.created = created;
    }

    /**
     * @return the destroyed
     */
    public Calendar getDestroyed() {
        return destroyed;
    }

    /**
     * @param destroyed the destroyed to set
     */
    public void setDestroyed(Calendar destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * @return the expires
     */
    public Calendar getExpires() {
        return expires;
    }

    /**
     * @param expires the expires to set
     */
    public void setExpires(Calendar expires) {
        this.expires = expires;
    }

    public UserBean getUser() {
    	return new UserDao().get(userId);
    }
    /**
     * @return the userId
     */
    public Long getUserId() {
        return this.userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the proxy
     */
    public String getProxy() {
        return proxy;
    }

    /**
     * @param proxy the proxy to set
     */
    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
    
    /**
     * @return the project
     * @throws ProviderException 
     */
    public ProjectBean getProject() throws ProviderException {
        return DaoFactory.getProjectDao(this).get();
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    /**
     * @return the projectId
     */
    public Long getProjectId() {
        return this.projectId;
    }
    
    /**
	 * @return the type
	 */
	public AccessType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AccessType type) {
		this.type = type;
	}
	
	// ********************** Common Methods ********************** //
	    
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GMSSession)) return false;
        final GMSSession s = (GMSSession) o;
        if (!token.equals(s.token)) return false;
        if (!userId.equals(s.userId)) return false;
        if (!projectId.equals(s.projectId)) return false;
        return true;
    }
    
    public int hashCode() {
        return token.hashCode() + created.hashCode();
    }
    
    public String toString() {
        return  "Id ('" + getId() + "'), " +
                "Session key: '" + getToken() + "' " +
                "project: '" + getProjectId() + "' " +
                "user: '" + getUserId() + "' " +
                "Created: '" + getCreated().getTime() + "' " +
                "Expries: '" + getExpires().getTime() + "' ";
    }
    
    public int compareTo(Object o) {
        if (o instanceof GMSSession)
            return this.getCreated().compareTo( ((GMSSession)o).getCreated() );
        return 0;
    }
    
}
