package org.gridchem.service.dao;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao {
	private static final Log log = LogFactory.getLog(AbstractDao.class);
	
	protected GMSSession userSession;
	
	protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Session hSession;
    
    public AbstractDao() {
    	this.hSession = HibernateUtil.getSession();
    	//this.hSession = sessionFactory.getCurrentSession();
    	this.hSession.beginTransaction();
    }
   
	public AbstractDao(GMSSession session) {
		this();
		this.userSession = session;
		
	}
	
	protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("java:comp/env/gms/GridchemServiceSessionFactory");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(Object transientInstance) {
        log.debug("persisting " + transientInstance.getClass().getSimpleName() + " instance");
        
		try {
			
	        Transaction t = hSession.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        hSession.persist(transientInstance);
			
			t.commit();
			
//			s.flush();
            
//            s.clear();
			
		} catch (HibernateException ex) {
			HibernateUtil.rollbackTransaction();
			throw ex;
		}
    }
    
    public void delete(Object transientInstance) {
    	log.debug("deleting " + transientInstance.getClass().getSimpleName() + " instance");
    	try {
			Transaction t = hSession.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        hSession.delete(transientInstance);
			
			t.commit();
			
//			s.flush();
            
//            s.clear();
			
		} catch (HibernateException ex) {
			HibernateUtil.rollbackTransaction();
			throw ex;
		}
    }
	
}
