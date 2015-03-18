/* 
 * Created on Feb 7, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.dao;

import java.util.List;

import org.gridchem.service.exceptions.FileException;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.model.LogicalFile;
import org.gridchem.service.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LogicalFileDao {

    public static void persist(LogicalFile file) {
        
        HibernateUtil.beginTransaction();
        
        Session session = HibernateUtil.getSession();
        
        Transaction tx = session.getTransaction();
        
        tx.begin();
        
        session.saveOrUpdate(file);
        
        session.flush();
        
        tx.commit();
        
    }
    
    public static void delete(LogicalFile file) {
        
        HibernateUtil.beginTransaction();
        
        Session session = HibernateUtil.getSession();
        
        Transaction tx = session.getTransaction();
        
        tx.begin();
        
        session.delete(file);
        
        session.flush();
        
        tx.commit();
        
    }
    
    public static LogicalFile get(Long id) {
        
//        clearCache();
        
        HibernateUtil.beginTransaction();
        
//        System.out.println("Retrieving logical file record " + id);
        LogicalFile file = null;
        Session session = HibernateUtil.getSession();
        try {
            file = (LogicalFile) session.load(LogicalFile.class, id, LockMode.UPGRADE);
        }catch (HibernateException e) {
            throw new FileException(e);
        }
        
        HibernateUtil.commitTransaction();
        
        return file;
        
    }
    
    public static void clearCache() {
        HibernateUtil.beginTransaction();

        try {
            
            Session s = HibernateUtil.getSession();
            
            s.flush();
            
            s.clear();
            
        } catch (HibernateException e) {
            throw new JobException(e);
        }
        
        HibernateUtil.commitTransaction();
        
    }

	@SuppressWarnings("unchecked")
	public static List<LogicalFile> getAllForJob(Long jobId) {
		 HibernateUtil.beginTransaction();
	        
//        System.out.println("Retrieving logical file records for job " + jobId);
        
        List<LogicalFile> files = null;
        
        Session session = HibernateUtil.getSession();
        
        try {
            files = (List<LogicalFile>) session.createQuery("from LogicalFile where jobId = :jobId")
            	.setLong("jobId", jobId)
            	.list();
            
            return files;
        }catch (HibernateException e) {
            throw new FileException(e);
        }
        
        
	}
}
