/**
 * 
 */
package org.gridchem.service.dao;

import java.util.List;

import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.model.UserProjectResource;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author dooley
 *
 */
public class UserProjectResourceDao {

	public static void add(UserProjectResource upr)  throws UserException {
		
		if (upr == null) throw new UserException("UserProject cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	Session s = HibernateUtil.getSession();
	        s.save(upr);
	        HibernateUtil.commitTransaction();
	        
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException("Failed to add UserProject",e);
        }
	}
	
	public static void update(UserProjectResource upr)  throws UserException {
		
		if (upr == null) throw new UserException("UserProject cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	Session s = HibernateUtil.getSession();
	        s.update(upr);
	        HibernateUtil.commitTransaction();
	        
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException("Failed to add UserProject",e);
        }
	}
	
	public static boolean exists(UserProjectResource up) throws UserException {
		
		if (up == null) throw new UserException("UserProject cannot be null");
    	
    	return exists(up.getUserProject().getUser().getUsername(), 
    			up.getUserProject().getProject().getId(),
    			up.getResource().getName(),
    			up.getAllocationName());
	}
	
	public static boolean exists(String username, Long projectId, 
			String system, String allocationName) 
	throws UserException {
		
		if (!ServiceUtil.isValid(username)) throw new JobException("User id cannot be null");
    	if (projectId == null) throw new JobException("Project id cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
            String hql = "select COUNT(*) from UserProjectResource " +
            		"where userProject.user.username = :username " +
            		"and userProject.project.id = :pid " +
            		"and resource.name = :system " +
            		"and allocationName = :alloc";
            
            Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
            int rowCount = s.createSQLQuery(hql)
                .setString("username", username)
                .setLong("pid", projectId)
                .setString("system", system)
                .setString("alloc", allocationName)
                .executeUpdate();
            
            return rowCount > 0;
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException(e);
        }
	}
	
	@SuppressWarnings("unchecked")
	public static List<UserProjectResource> getAllForUserProjectResource(String username, Long projectId, String resource) throws UserException {
		
		if (!ServiceUtil.isValid(username)) throw new UserException("User id cannot be null");
		if (!ServiceUtil.isValid(resource)) throw new ResourceException("Resource cannot be null");
    	if (projectId == null) throw new ProjectException("Project id cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	String hql = "from UserProjectResource where resource.name = :name " +
        			"and userProject.user.username = :username " +
        			"and userProject.project.id = :pid";
        	
        	List<UserProjectResource> results = (List<UserProjectResource>)HibernateUtil.getSession()
        		.createQuery(hql)
        		.setString("name", resource)
        		.setString("username", username)
        		.setLong("pid", projectId)
        		.list();
        	
        	return results;
        		
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new UserException(e);
        }
	}
	
	/**
	 * Find the allocation name for community users on the named
	 * resource.
	 * 
	 * @param resource name
	 * @return the allocation name or null if the resource is not found.
	 */
	@SuppressWarnings("unchecked")
	public static String getCommunityLoginName(String resource) {
		
		if (!ServiceUtil.isValid(resource)) return null;
		
		// Project 1 is always the community user project
		String hql = "select loginName from UserProjectResource " +
				"where resource.name = :name " +
				"and userProject.user.username = :commusername " +
				"and userProject.project.id = :commprojectid " +
				"and enabled = :enabled";
		
		try {
			HibernateUtil.beginTransaction();
			
			List<String> results = (List<String>)HibernateUtil.getSession()
				.createQuery(hql)
				.setString("commusername", "ccguser")
				.setLong("commprojectid", new Long(1))
				.setString("name", resource)
				.setBoolean("enabled", true)
				.list();
			
			if (results.isEmpty()) return null;
			
			return results.get(0);
		} catch (Exception e) {
			throw new ResourceException("Failed to find community allocation name for resource.",e);
		}
	}

	public static void remove(UserProjectResource upr) {
		if (upr == null) throw new ResourceException("UserProject cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	Session s = HibernateUtil.getSession();
	        s.delete(upr);
	        HibernateUtil.commitTransaction();
	        
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new ResourceException("Failed to delete UserProject",e);
        }
		
	}

	@SuppressWarnings("unchecked")
	public static UserProjectResource get(String username, Long projectId, String resourceName,
			String allocationName) {
		
		if (!ServiceUtil.isValid(username)) return null;
		if (!ServiceUtil.isValid(projectId)) return null;
		if (!ServiceUtil.isValid(resourceName)) return null;
		if (!ServiceUtil.isValid(allocationName)) return null;
		
		// Project 1 is always the community user project
		String hql = "from UserProjectResource " +
				"where userProject.user.username = :username " +
				"and userProject.project.id = :pid " +
				"and resource.name = :name " +
				"and allocationName = :alloc";
		
		try {
			HibernateUtil.beginTransaction();
			
			List<UserProjectResource> results = (List<UserProjectResource>)HibernateUtil.getSession()
				.createQuery(hql)
				.setString("username", username)
				.setLong("pid", projectId)
				.setString("name", resourceName)
				.setString("alloc", allocationName)
				.list();
			
			if (results.isEmpty()) return null;
			
			return results.get(0);
			
		} catch (Exception e) {
			throw new ResourceException("Failed to find upr record.",e);
		}
	}
	
}
