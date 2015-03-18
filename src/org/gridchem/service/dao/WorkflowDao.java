/**
 * 
 */
package org.gridchem.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.gridchem.service.exceptions.WorkflowException;
import org.gridchem.service.model.Workflow;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 * @author dooley
 *
 */
public class WorkflowDao {
	
	@SuppressWarnings("unchecked")
	public static List<Workflow> getAll() 
			throws WorkflowException {

		List<Workflow> results;
		
		HibernateUtil.beginTransaction();
		
		try {
            
			results = HibernateUtil.getSession().createCriteria(Workflow.class)
                            .addOrder(Order.desc("created"))
                            .list();
            
		} catch (HibernateException ex) {
			throw new WorkflowException(ex);
		}
		return results;
		
	}
	
	public static Workflow getById(Long workflowId)
			throws WorkflowException {

		if (!ServiceUtil.isValid(workflowId)) return null;
		
		HibernateUtil.beginTransaction();
		
		Session session = HibernateUtil.getSession();
		
		Workflow workflow = null;
		
		try {
		
			workflow = (Workflow) session.load(Workflow.class, workflowId);
			
		}  catch (HibernateException ex) {
			throw new WorkflowException(ex);
		}
		return workflow;
	}
	
//	@SuppressWarnings("unchecked")
//	public static List<Workflow> getByProject(Long projectId)
//			throws WorkflowException {
//
//		List<Workflow> results = new ArrayList<Workflow>();
//		
//		if (!ServiceUtil.isValid(projectId)) return results;
//		
//		HibernateUtil.beginTransaction();
//		
//		Session session = HibernateUtil.getSession();
//		
//		try {
//		
//			results = session.createCriteria(Workflow.class)
//				.add(Restrictions.eq("project", projectId))
//				.list();
//			
//		} catch (HibernateException ex) {
//			throw new WorkflowException(ex);
//		}
//		return results;
//	}
	
	@SuppressWarnings("unchecked")
	public static List<Workflow> getByUser(Long userId)
			throws WorkflowException {

		List<Workflow> results = new ArrayList<Workflow>();
		
		if (!ServiceUtil.isValid(userId)) return results;
		
		HibernateUtil.beginTransaction();
		
		Session session = HibernateUtil.getSession();
		
		try {
		
			String hql = "from Workflow where user.id = :userId";
			
			results = session.createQuery(hql)
				.setLong("userId", userId)
				.list();
			
		} catch (HibernateException ex) {
			throw new WorkflowException(ex);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public static List<Workflow> getByUser(String username) 
			throws WorkflowException {

		List<Workflow> results = new ArrayList<Workflow>();
		
		if (!ServiceUtil.isValid(username)) return results;
		
		HibernateUtil.beginTransaction();
		
		Session session = HibernateUtil.getSession();
		
		try {
		
			String hql = "from Workflow where user.username = :username";
			
			results = session.createQuery(hql)
				.setString("username", username)
				.list();
			
		} catch (HibernateException ex) {
			throw new WorkflowException(ex);
		}
		return results;
	}
	
	public static void setHidden(Long workflowId, boolean hidden) throws WorkflowException {
		
		if (!ServiceUtil.isValid(workflowId)) throw new WorkflowException("Workflow cannot be null");
		
		HibernateUtil.beginTransaction();
		
		try {
			
            String jobSQL = "update Jobs set hidden = :hidden where workflowId = :workflowId";
            
            String workflowSQL = "update Workflows set hidden = :hidden where id = :workflowId";
            
            Session s = HibernateUtil.getSession();
	        
            Transaction t = s.getTransaction();
	        
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
	        // update jobs
            int rowCount = s.createSQLQuery(jobSQL)
                .setLong("workflowId", workflowId)
                .setBoolean("hidden", hidden)
                .executeUpdate();
            
            // update workflows
            rowCount = s.createSQLQuery(workflowSQL)
            	.setLong("workflowId", workflowId)
                .setBoolean("hidden", hidden)
                .executeUpdate();
            
            t.commit();
            
            s.flush();
            
            s.clear();
            
            System.out.println((hidden?"Hide":"Unhide") + " successful. Rows affected: " + rowCount);
            
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new WorkflowException(e);
        }
        
	}
	
	public static void persist(Workflow workflow) throws WorkflowException {
		
		if (workflow == null) throw new WorkflowException("Workflow cannot be null");
		
	    HibernateUtil.beginTransaction();
		
	    try {
            
	        Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        s.saveOrUpdate(workflow);
	        
	        t.commit();
	        
	        s.flush();
//            
//            s.clear();
	        
		} catch (HibernateException ex) {
			try {
				if (HibernateUtil.getSession().isOpen()) {
					HibernateUtil.rollbackTransaction();
				}
			} catch (Exception e) {}
			throw new WorkflowException(ex);
		} finally {
		    
		}
	}
	
	public static void delete(Workflow workflow) throws WorkflowException {
		
		if (workflow == null) throw new WorkflowException("Workflow cannot be null");
		
	    HibernateUtil.beginTransaction();
		
	    try {
            
	        Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        s.delete(workflow);
	        
	        t.commit();
	        
	        s.flush();
            
            s.clear();
	        
		} catch (HibernateException ex) {
			try {
				if (HibernateUtil.getSession().isOpen()) {
					HibernateUtil.rollbackTransaction();
				}
			} catch (Exception e) {}
			throw new WorkflowException(ex);
		} finally {
		    
		}
	}
	
}
