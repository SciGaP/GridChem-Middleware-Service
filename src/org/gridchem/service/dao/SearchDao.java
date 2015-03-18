/**
 * 
 */
package org.gridchem.service.dao;

import java.util.LinkedList;
import java.util.List;

import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SoftwareException;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.Software;
import org.gridchem.service.model.enumeration.JobSearchFilterType;
import org.gridchem.service.persistence.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Helper class for job searches.
 * 
 * @author dooley
 *
 */
public class SearchDao {

	@SuppressWarnings("unchecked")
	public static List<Software> _getSoftware(String softwareName,
			JobSearchFilterType filter) throws SoftwareException {
		 
		HibernateUtil.beginTransaction();
		
		Session session = HibernateUtil.getSession();
		
		List<Software> resources = new LinkedList<Software>();
        
		try {
			
             Criteria crit = session.createCriteria(Software.class);
             
             if(filter.name().equals(JobSearchFilterType.PREFIX)) {
                 crit.add(Restrictions.like("name",softwareName,MatchMode.START).ignoreCase());
             } else if(filter.name().equals(org.gridchem.service.model.enumeration.JobSearchFilterType.SUFFIX)) {
                 crit.add(Restrictions.like("name",softwareName,MatchMode.END).ignoreCase());
             } else if (filter.name().equals(JobSearchFilterType.EQ)) {
                 crit.add(Restrictions.like("name",softwareName,MatchMode.EXACT).ignoreCase());
             } else if (filter.name().equals(JobSearchFilterType.NEQ)) {
                 crit.add(Restrictions.not(Restrictions.like("name",softwareName,MatchMode.EXACT)));
             } else if (filter.name().equals(JobSearchFilterType.LIKE)) {
                 crit.add(Restrictions.like("name",softwareName,MatchMode.ANYWHERE).ignoreCase());
             } else if (filter.name().equals(JobSearchFilterType.NOT_LIKE)) {
                 crit.add(Restrictions.not(Restrictions.like("name",softwareName,MatchMode.ANYWHERE)));
             } 
             
             resources = crit.list();
             
             if(resources.isEmpty()) return null;
             
         } catch (HibernateException e) {
             throw new SoftwareException("Failed to perform software search", e);
         }
         
         return resources;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ComputeResource> _getComputeResources(String resourceName, JobSearchFilterType filter)
    throws ResourceException {
		
		HibernateUtil.beginTransaction();
		
		Session session = HibernateUtil.getSession();
		
		List<ComputeResource> resources = new LinkedList<ComputeResource>();
		
		try {
		    
		    Criteria crit = session.createCriteria(ComputeResource.class);
		    
		    if(filter.name().equals(JobSearchFilterType.PREFIX)) {
		        crit.add(Restrictions.like("name",resourceName,MatchMode.START).ignoreCase());
		    } else if(filter.name().equals(JobSearchFilterType.SUFFIX)) {
		        crit.add(Restrictions.like("name",resourceName,MatchMode.END).ignoreCase());
		    } else if (filter.name().equals(JobSearchFilterType.EQ)) {
		        crit.add(Restrictions.like("name",resourceName,MatchMode.EXACT).ignoreCase());
		    } else if (filter.name().equals(JobSearchFilterType.NEQ)) {
		        crit.add(Restrictions.not(Restrictions.like("name",resourceName,MatchMode.EXACT)));
		    } else if (filter.name().equals(JobSearchFilterType.LIKE)) {
		        crit.add(Restrictions.like("name",resourceName,MatchMode.ANYWHERE).ignoreCase());
		    } else if (filter.name().equals(JobSearchFilterType.NOT_LIKE)) {
		        crit.add(Restrictions.not(Restrictions.like("name",resourceName,MatchMode.ANYWHERE)));
		    } 
		   
		    resources = (List<ComputeResource>)crit.list();
		    
		    if(resources.isEmpty()) return null;
		    
		} catch (HibernateException e) {
		    throw new ResourceException("Failed to preform resource search", e);
		}
		
			return resources;
	}
}
