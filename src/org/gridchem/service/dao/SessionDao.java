/* 
 * Created on Feb 5, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionDao {
    @SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(SessionDao.class);
    

    // ********************************************************** //

    @SuppressWarnings("unchecked")
	public static boolean isUnique(String token) {
        
    	if (!ServiceUtil.isValid(token))  throw new SessionException("Invalid token");
    	
//        clearCache();
        
        HibernateUtil.beginTransaction();
        
        Session session = HibernateUtil.getSession();
        
        String sql = "FROM GMSSession s where s.token = :token";
        
        List results = (List)session.createQuery(sql)
        	.setString("token",token).list();
        
        return results.isEmpty();
        
    }
    
    @SuppressWarnings("unchecked")
	public static GMSSession getByToken(String token) {
           	System.out.println(" This is the token " + token + "\n ");



    	if (!ServiceUtil.isValid(token)) return null;
    	
        clearCache();
        GMSSession s = null;
        Session session = null;
        List<GMSSession> sessions = null;
        HibernateUtil.beginTransaction();
        
        session = HibernateUtil.getSession();
 
        try {
//            session.flush();

            String sql = "FROM GMSSession s where s.token = :token";
            
            sessions = session.createQuery(sql)
            	.setString("token",token).list();
        System.out.println("This is the session "+ sessions.toString() +"\n");    
            if (sessions != null && sessions.size() > 0)
                s = sessions.get(0);
//            else 
//                throw new SessionException("No session with id " + token + " found.");
            
        }  catch (HibernateException ex) {
            ex.printStackTrace();
        }
       return s;
    }
    
    public static void persist(GMSSession session) {
    	
    	if (session == null || !ServiceUtil.isValid(session.getToken())) 
    		throw new SessionException("Session may not be null");
    	
        HibernateUtil.beginTransaction();
        try {
        	Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        s.saveOrUpdate(session);
	        
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
        	throw new SessionException("Failed to persist session.",ex);
        } finally {
        	
        }

    }
    
    public static void delete(GMSSession session) {
    	
    	if (session == null) throw new SessionException("Session may not be null");
    	
        HibernateUtil.beginTransaction();
        
    	try {
    		Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
	        s.delete(session);
	        
	        t.commit();
	        
	        s.flush();
	        
        } catch (Exception e) {
        	HibernateUtil.rollbackTransaction();
        	throw new SessionException("Failed to delete session.",e);
        } finally {
        	
        }

    }
    
    public static void clearCache() {
        HibernateUtil.beginTransaction();

        try {
            
            Session s = HibernateUtil.getSession();
            
            s.flush();
            
            s.clear();
            
        } catch (HibernateException ex) {
        	try {
				if (HibernateUtil.getSession().isOpen()) {
					HibernateUtil.rollbackTransaction();
				}
			} catch (Exception e) {}
            throw new JobException(ex);
        }
        
        HibernateUtil.commitTransaction();
        
    }
}
