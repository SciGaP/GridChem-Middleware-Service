/**
 * 
 */
package org.gridchem.service.dao;

import java.util.List;

import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author dooley
 *
 */
public class UserProjectDao {

	public static void add(UserProject up)  throws UserException {
		
		if (up == null) throw new UserException("UserProject cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	Session s = HibernateUtil.getSession();
        	Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        s.save(up);
	        
	        HibernateUtil.commitTransaction();
	        
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException("Failed to add UserProject",e);
        }
	}
	
	public static void update(UserProject up)  throws UserException {
		
		if (up == null) throw new UserException("UserProject cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	Session s = HibernateUtil.getSession();
	        s.update(up);
	        HibernateUtil.commitTransaction();
	        
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException("Failed to update UserProject",e);
        }
	}
	
	public static void remove(UserProject up)  throws UserException {
		
		if (up == null) throw new UserException("UserProject cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	Session s = HibernateUtil.getSession();
	        s.delete(up);
	        HibernateUtil.commitTransaction();
	        
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException("Failed to remove UserProject",e);
        }
	}

	public static boolean exists(UserProject up) throws UserException {
		
		if (up == null) throw new UserException("UserProject cannot be null");
    	
    	return exists(up.getUser().getUsername(), up.getProject().getId());
	}
	
	@SuppressWarnings("unchecked")
	public static boolean exists(String username, Long projectId) throws UserException {
		
		if (!ServiceUtil.isValid(username)) throw new UserException("User id cannot be null");
    	if (projectId == null) throw new UserException("Project id cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
            String hql = "from UserProject where user.username = :username and project.id = :pid";
            
            Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
            List results = s.createQuery(hql)
                .setString("username", username)
                .setLong("pid", projectId)
                .list();
            
            return results.size() > 0;
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException(e);
        }
	}
	
	@SuppressWarnings("unchecked")
	public static UserProject get(String username, Long projectId) throws UserException {
		
		if (!ServiceUtil.isValid(username)) throw new UserException("User id cannot be null");
    	if (projectId == null) throw new ProjectException("Project id cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	String hql = "from UserProject where user.username = :username and project.id = :pid";
        	
        	List<UserProject> results = (List<UserProject>)HibernateUtil.getSession()
        		.createQuery(hql)
        		.setString("username", username)
        		.setLong("pid", projectId)
        		.list();
        	
        	if (results.isEmpty()) return null;
        	
        	return results.get(0);
        		
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException(e);
        }
	}
	
}
