/**
 * 
 */
package org.gridchem.service.dao;

import java.util.List;

import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.model.ProjectResource;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.Session;

/**
 * @author dooley
 *
 */
public class ProjectResourceDao {

	/**
	 * Find the allocation name for community users on the named
	 * resource.
	 * 
	 * @param resource name
	 * @return the allocation name or null if the resource is not found.
	 */
	@SuppressWarnings("unchecked")
	public static String getCommunityAllocationName(String resource) {
		
		if (!ServiceUtil.isValid(resource)) return null;
		
		// Project 1 is always the community user project
		String hql = "select allocationName from ProjectResource " +
				"where resource.name = :name " +
				"and project.id = '1'";
		
		try {
			HibernateUtil.beginTransaction();
			
			List<String> results = (List<String>)HibernateUtil.getSession()
				.createQuery(hql)
				.setString("name", resource)
				.list();
			
			if (results.isEmpty()) return null;
			
			return results.get(0);
		} catch (Exception e) {
			throw new ResourceException("Failed to find community allocation name for resource.",e);
		}
	}

	public static void add(ProjectResource pr) {
		HibernateUtil.beginTransaction();
		try {
			
			HibernateUtil.getSession().saveOrUpdate(pr);
			
			HibernateUtil.commitTransaction();
			
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			throw new ProjectException("Failed to add project resource",e);
		}
	}

	public static void remove(ProjectResource pr) {
		if (pr == null) throw new ProjectException("ProjectResource cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
        	
        	Session s = HibernateUtil.getSession();
	        s.delete(pr);
	        HibernateUtil.commitTransaction();
	        
        } catch (Exception e) {
        	HibernateUtil.rollbackTransaction();
            throw new ProjectException("Failed to remove ProjectResource",e);
        }
		
	}

	@SuppressWarnings("unchecked")
	public static ProjectResource get(Long projectId, String resource, String allocationName) {
		
		if (!ServiceUtil.isValid(projectId)) return null;
		if (!ServiceUtil.isValid(resource)) return null;
		if (!ServiceUtil.isValid(allocationName)) return null;
		
		// Project 1 is always the community user project
		String hql = "from ProjectResource " +
				"where project.id = :pid " +
				"and resource.name = :name " +
				"and allocationName = :alloc";
		
		try {
			HibernateUtil.beginTransaction();
			
			List<ProjectResource> results = (List<ProjectResource>)HibernateUtil.getSession()
				.createQuery(hql)
				.setLong("pid", projectId)
				.setString("name", resource)
				.setString("alloc", allocationName)
				.list();
			
			if (results.isEmpty()) return null;
			
			return results.get(0);
			
		} catch (Exception e) {
			throw new ResourceException("Failed to find upr record.",e);
		}
	}

}
