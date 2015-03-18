/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 9, 2007
 * 
 * Developed by: CCT, Center for Computation and Technology, 
 * 				NCSA, University of Illinois at Urbana-Champaign
 * 				OSC, Ohio Supercomputing Center
 * 				TACC, Texas Advanced Computing Center
 * 				UKy, University of Kentucky
 * 
 * https://www.gridchem.org/
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal with the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom 
 * the Software is furnished to do so, subject to the following conditions:
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimers.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimers in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the names of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the names of its contributors 
 *    may be used to endorse or promote products derived from this Software without 
 *    specific prior written permission.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS WITH THE SOFTWARE.
*/

package org.gridchem.service.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.model.Notification;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
@SuppressWarnings({ "unchecked" })
public class NotificationDao {
    private static Logger log = Logger.getLogger(NotificationDao.class);

    public static Notification getNotificationById(Long nId)
            throws NotificationException {
    	
    	if (!ServiceUtil.isValid(nId)) return null; 
    		
    	
    	HibernateUtil.beginTransaction();
        Session session = HibernateUtil.getSession();
        Notification notif = null;
        try {
            notif = (Notification) session.load(Notification.class, nId);
        } catch (ObjectNotFoundException ex) {
			return null;
		}  catch (HibernateException ex) {
            throw new NotificationException(ex);
        }
        return notif;
    }

    // ********************************************************** //

    public static void deleteAllForUser(Long userId) throws NotificationException {
    	HibernateUtil.beginTransaction();
        Session session = HibernateUtil.getSession();
        
        if (!ServiceUtil.isValid(userId)) 
    		throw new NotificationException("User cannot be null.");
        
        try {
        	Query query = session.createSQLQuery(
        			"delete n from JobNotifications n " +
        			"left join Jobs j on n.jobID = j.jobID " +
        			"where j.userID = '" + userId.longValue() + "'");
        	query.executeUpdate();
        	session.getTransaction().commit();
        } catch (Exception e) {
        	throw new NotificationException("Failed to delete notifications for user: " + userId,e);
        }
        	
    }
    
	public static List<Notification> getAllByJobID(Long jobID)
            throws NotificationException {
    	
		if (!ServiceUtil.isValid(jobID)) return new ArrayList<Notification>();
		
    	HibernateUtil.beginTransaction();
        Session session = HibernateUtil.getSession();
        
        List<Notification> sites = null;
        
        try {
            
        	String hql = "from Notification where job.id = :jid";
        	
            sites = session.createQuery(hql)
                .setLong("jid",jobID).list();
            
        }  catch (HibernateException ex) {
            throw new NotificationException(ex);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return sites;
    }
	
	public static boolean exists(Notification notif) throws NotificationException {
		
		if (notif == null) 
    		throw new NotificationException("Notification cannot be null.");
    	
    	if (notif.getJob() == null || notif.getJob().getId() == null) 
    		throw new NotificationException("Notification job cannot be null.");
    	
    	if (notif.getType() == null) 
    		throw new NotificationException("Notification type cannot be null.");
    	
    	if (notif.getStatus() == null) 
    		throw new NotificationException("Notification status cannot be null.");
    	
		List<Notification> results = HibernateUtil.getSession().createCriteria(Notification.class)
			.add(Restrictions.eq("job",notif.getJob()))
			.add(Restrictions.eq("status",notif.getStatus()))
			.add(Restrictions.eq("type",notif.getType())).list();
		
		return !results.isEmpty();
					
	}

	
	public static void add(Notification notif)
    throws NotificationException {

    	if (notif == null) 
    		throw new NotificationException("Notification cannot be null.");
    	
    	if (notif.getJob() == null || notif.getJob().getId() == null) 
    		throw new NotificationException("Notification job cannot be null.");
    	
    	if (notif.getType() == null) 
    		throw new NotificationException("Notification type cannot be null.");
    	
    	if (notif.getStatus() == null) 
    		throw new NotificationException("Notification status cannot be null.");
    	
    	HibernateUtil.beginTransaction();
    	try {
    		
    		if (exists(notif)) {
				throw new NotificationException("Notification already exists.");
			}
    		
			notif.setLastUpdated(new Date());
            HibernateUtil.getSession().save(notif);
            HibernateUtil.commitTransaction();
        } catch (NotificationException ex) {
        	throw ex;
        }catch (Exception ex) {
            throw new NotificationException(ex);
        }
    }
    
    public static void add(Collection<Notification> notifications)
    throws NotificationException {

    	if (!ServiceUtil.isValid(notifications)) 
    		throw new NotificationException("Notifications cannot be empty.");
    	
    	for (Notification notif: notifications) {
    		if (notif == null) 
        		throw new NotificationException("Notification cannot be null.");
        	
        	if (notif.getJob() == null || notif.getJob().getId() == null) 
        		throw new NotificationException("Notification job cannot be null.");
        	
        	if (notif.getType() == null) 
        		throw new NotificationException("Notification type cannot be null.");
        	
        	if (notif.getStatus() == null) 
        		throw new NotificationException("Notification status cannot be null.");
    	}
    	
    	HibernateUtil.beginTransaction();
    	try {
    		Session session = HibernateUtil.getSession();
    		for (Notification n: notifications) {
    			if (exists(n)) {
        			throw new NotificationException("Notification already exists.");
        		}
    			n.setLastUpdated(new Date());
    			session.save(n);
    		}
    		HibernateUtil.commitTransaction();
    	} catch (NotificationException ex) {
        	throw ex;
        } catch (HibernateException ex) {
            throw new NotificationException(ex);
        }
    }
    
    public static void update(Notification notif)
    throws NotificationException {

    	if (notif == null) 
    		throw new NotificationException("Notification cannot be null.");
    	
    	if (notif.getJob() == null || notif.getJob().getId() == null) 
    		throw new NotificationException("Notification job cannot be null.");
    	
    	if (notif.getType() == null) 
    		throw new NotificationException("Notification type cannot be null.");
    	
    	if (notif.getStatus() == null) 
    		throw new NotificationException("Notification status cannot be null.");
    	
    	HibernateUtil.beginTransaction();
    	try {
    		notif.setLastUpdated(new Date());
            HibernateUtil.getSession().update(notif);
            HibernateUtil.commitTransaction();
		
        } catch (Exception ex) {
            throw new NotificationException(ex);
        }
    }
    
    public static void update(Collection<Notification> notifications)
    throws NotificationException {

    	if (!ServiceUtil.isValid(notifications)) 
    		throw new NotificationException("Notifications cannot be empty.");
    	
    	for (Notification notif: notifications) {
    		if (notif == null) 
        		throw new NotificationException("Notification cannot be null.");
        	
        	if (notif.getJob() == null || notif.getJob().getId() == null) 
        		throw new NotificationException("Notification job cannot be null.");
        	
        	if (notif.getType() == null) 
        		throw new NotificationException("Notification type cannot be null.");
        	
        	if (notif.getStatus() == null) 
        		throw new NotificationException("Notification status cannot be null.");
    	}
    	
    	HibernateUtil.beginTransaction();
    	try {
    		Session session = HibernateUtil.getSession();
    		for (Notification n: notifications) {
    			n.setLastUpdated(new Date());
    			session.update(n);
    		}
    		HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            throw new NotificationException(ex);
        }
    }
    
    public static void delete(Notification notif)
        throws NotificationException {
    	
    	if (notif == null) throw new NotificationException("Notification cannot be null.");
    	
    	HibernateUtil.beginTransaction();
        try {
            HibernateUtil.getSession().delete(notif);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            throw new NotificationException(ex);
        }
    }
    
    public static void delete(Collection<Notification> notifications)
    throws NotificationException {
	
    	if (!ServiceUtil.isValid(notifications)) throw new NotificationException("Notifications cannot be null.");
	
		HibernateUtil.beginTransaction();
	    try {
	    	for (Notification n: notifications) {
	    		HibernateUtil.getSession().delete(n);
	    	}
	        HibernateUtil.commitTransaction();
	    } catch (HibernateException ex) {
	        throw new NotificationException(ex);
	    }
	}
}
