/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 20, 2005
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
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.beans.SearchBean;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.JobSearchException;
import org.gridchem.service.job.SearchCriterionBuilder;
import org.gridchem.service.model.Job;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Persistence class for <tt>Job</tt>. 
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 * @see Job
 */
public class JobDao extends AbstractDao {
	private static Logger log = Logger.getLogger(JobDao.class);
	
	public static Job getById(Long jobId)
			throws JobException {

		if (!ServiceUtil.isValid(jobId)) return null;
		
		HibernateUtil.beginTransaction();
		
		Session session = HibernateUtil.getSession();
		
		Job job = null;
		
		try {
		
			job = (Job) session.load(Job.class, jobId);
		} catch (ObjectNotFoundException ex) {
			return null;
		} catch (HibernateException ex) {
			throw new JobException(ex);
		}
		return job;
	}

	// ********************************************************** //

    @SuppressWarnings({ "unchecked" })
	public static List<Job> getBySystem(String system)
            throws JobException {

    	if (!ServiceUtil.isValid(system)) return new ArrayList<Job>();
    	
    	HibernateUtil.beginTransaction();
    	
        Session session = HibernateUtil.getSession();
        
        List<Job> jobs = new ArrayList<Job>();
        
        try {
            String hql = "from Job where system.name = :system";
            
            jobs = session.createQuery(hql)
                .setString("system", system)
                .list();
            
        }  catch (HibernateException ex) {
            throw new JobException(ex);
        }
        
        return jobs;
    }
    
	// ********************************************************** //

	@SuppressWarnings("unchecked")
	public static Job getByName(String name)
			throws JobException {

		if (!ServiceUtil.isValid(name)) return null;
		
		HibernateUtil.beginTransaction();
		
		Session session = HibernateUtil.getSession();
		String queryString = "from Job j " +
						"where j.name = :name";
		Job job = null;
		try {
			List result = session.createQuery(queryString)
			.setString("name",name).list();
			
			if (result.isEmpty()) return null; 
			
    		job = (Job) result.get(0);
    		
		}  catch (HibernateException ex) {
			throw new JobException(ex);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return job;
	}
    
	// ********************************************************** //
            
    @SuppressWarnings("unchecked")
	public static List<Job> getBySystemLocalId(String system, String localId) {
        
    	if (!ServiceUtil.isValid(system) || !ServiceUtil.isValid(localId)) return new ArrayList<Job>();
    	
    	HibernateUtil.beginTransaction();
    	
    	Session session = HibernateUtil.getSession();
        
        List<Job> result = new ArrayList<Job>();
        
        String queryString = "from Job " +
                        "where localId = :localId and " + 
                        "system.name = :system  ";
        
        try {
        
            result = session.createQuery(queryString)
                .setString("localId",localId)
                .setString("system",system).list();
            
        }  catch (QueryException ex) {
        	return new ArrayList<Job>();
        } catch (HibernateException ex) {
            throw new JobException(ex);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        
        return result;
    }
    
    @SuppressWarnings({ "unchecked" })
	public static List<Job> getAfterDate(Calendar cal) {
    	
    	if (!ServiceUtil.isValid(cal)) return new ArrayList<Job>();
    	
    	HibernateUtil.beginTransaction();
        
        Session session = HibernateUtil.getSession();
        
        List result = null;
        
        try {
            //java.sql.Date cutoffDate = new java.sql.Date(cal.getTimeInMillis());
            
            result = session.createCriteria(Job.class)
                .add(Restrictions.gt("created", cal.getTime())).list();
            
            //Hibernate.initialize(result);
            
        }  catch (HibernateException ex) {
            throw new JobException(ex);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        
        return result;
    }
	
	// ********************************************************** //

//	@SuppressWarnings("unchecked")
//	public static List<Job> getByUser(Long userId, boolean getAll)
//			throws JobException {
//
//		/*Session session = HibernateUtil.getSession();
//		System.out.println("mark2");
//		String queryString = "select j from Job as j left join fetch" +
//		"j.user as u where u.userId = :searchString";*/
//		List jobs = null;
//		
//        String query = "from Job j where j.user.id = :userID " +
//        		"and j.deleted = 0 ORDER by jobID DESC";
//		
//        try {
//            
//			Query q = HibernateUtil.getSession().createQuery(query)
//                .setLong("userID",userId);
//                
//            
//            if (!getAll) {
//                q.setMaxResults(Settings.MAX_JOBS_RETURNED);
//            }
//            
//            jobs = q.list();
//            
//        }catch (HibernateException ex) {
//			throw new JobException(ex);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//        
//		return jobs;
//	}
    
	// ********************************************************** //

	@SuppressWarnings("unchecked")
	public static List<Job> getAll()
			throws JobException {

		List<Job> jobs;
		
		HibernateUtil.beginTransaction();
		
		try {
            
			jobs = HibernateUtil.getSession().createCriteria(Job.class)
                            .addOrder(Order.desc("created"))
                            .list();
            
		} catch (HibernateException ex) {
			throw new JobException(ex);
		}
		return jobs;
	}

	// ********************************************************** //

	@SuppressWarnings("unchecked")
	public static List<Job> getByExample(JobBean exampleJob)
			throws JobException {

		if (exampleJob == null) return new ArrayList<Job>();
		
		HibernateUtil.beginTransaction();
		
		Job job = new Job();
		job.setHidden(false);
		job.setDeleted(false);
		
		Example example = Example.create(job)
			.ignoreCase()
			.excludeZeroes()
			.excludeProperty("lastUpdated")
			.excludeProperty("usedCpus")
			.excludeProperty("usedMemory")
			.excludeProperty("usedCpuTime")
			.excludeProperty("usedWallTime")
			.excludeProperty("created")
			.excludeProperty("estimatedResponseTime")
			.excludeProperty("lastUpdated")
			.excludeProperty("checkpointable")
			.excludeProperty("resubmittable")
			.excludeProperty("maxResubmissions")
			.excludeProperty("inputFiles")
			.excludeProperty("outputFiles")
			.enableLike(MatchMode.ANYWHERE);
		
		return HibernateUtil.getSession().createCriteria(Job.class)
				.add(example)
				.list();
	}
    
    public static String[] parseSearchTerm(String terms) {
        return terms.split(",");
    }
    
    /**
     * Finds all jobs matching the given search filter. This is a more
     * robust search allowing for <, <=, >, >=, prefix, suffix, like, 
     * and negations.
     * 
     * @param jsf
     * @return
     */
    @SuppressWarnings({ "unchecked" })
	public static List<Job> findMatching(SearchBean searchBean, Long userId, 
            Long projectId) {
    	List<Job> jobs = new ArrayList<Job>();
        String search;
        // otherwise put all present search terms in the job criteria and 
        // look up the job.
        Criteria crit = HibernateUtil.getSession().createCriteria(Job.class);
        
        // only search through records of the given user
        crit.add(Restrictions.eq("userId",userId));
        search = "userId=" + userId;
        
        //do not return deleted jobs to the user.
        crit.add(Restrictions.eq("deleted", 0)); 
        search += " deleted=false";
        
        // if a user is community, give them access to all community jobs
        crit.add(Restrictions.eq("projectId", projectId));
        
        try {   
            // if a job ID is given, lookup that job by ID.
            Enumeration paramKeys = searchBean.getCriteria().keys();
            while (paramKeys.hasMoreElements()) {
                  String key = (String)paramKeys.nextElement();
                  
                  SearchBean.SearchParameter sp = searchBean.getSearchParameter(key);
                  
                  crit.add(SearchCriterionBuilder.build(key, sp));
                  
                  search += ", " + key + " " + sp.type + " " + sp.value;
            }
        
            log.debug("Searching for: " + search);
          
            jobs = crit.addOrder(Order.desc("created")).list();
          
        } catch (JobSearchException e) {
            throw e;
        }catch (Exception e) {
            e.printStackTrace();
            throw new JobSearchException("Unknown error performing search \"" + search + "\"",e);
        }
      
        return jobs;
    }
//    
//    /**
//     * Find all jobs matching the criteria contained in the given jobDTO.
//     * This is essentially a find by example.
//     * 
//     * @param jobBean
//     * @param userID
//     * @return
//     */
//    @SuppressWarnings({ "unchecked" })
//	public static Collection<Job> findMatching(JobBean jobBean, Long userID) {
//        Collection<Job> jobs = new LinkedHashSet();
//        
//        try {    
//            String search = "";
//            // if a job ID is given, lookup that job by ID.
//            Job j = new Job();
//            if (jobBean.getId() != null) {
//                
//                j = findById(jobBean.getId());
//                
//                jobs.add(j);
//                
//                return jobs;
//            }
//            
//            // otherwise put all present search terms in the job criteria and 
//            // look up the job.
//            Criteria crit = HibernateUtil.getSession().createCriteria(Job.class);
//            
//            // only search through records of the given user
//            crit.add(Restrictions.eq("userId",userID));
//            
//            search += "userId=" + userID;
//            
//            if (jobBean.getName() != null && !jobBean.getName().equals("")) {
//                crit.add(Restrictions.like("name",jobBean.getName(),MatchMode.ANYWHERE));
//                search += " name=" + jobBean.getName();
//            }
//            
//            if (jobBean.getLocalId() != null && !jobBean.getLocalId().equals("")) {
//                crit.add(Restrictions.like("localId",jobBean.getLocalId(),MatchMode.ANYWHERE));
//                search += " localJobID=" + jobBean.getLocalId();
//            }
//            
//            if (jobBean.getExperimentName() != null 
//                    && !jobBean.getExperimentName().equals("")) {
//                crit.add(Restrictions.like("experimentName", 
//                        jobBean.getExperimentName(),
//                        MatchMode.ANYWHERE));
//                search += " rp=" + jobBean.getExperimentName();
//            }
//            
//            if (jobBean.getStatus() != null 
//                    && !jobBean.getStatus().equals("")) {
//                for(JobStatusType status:JobStatusType.values()) {
//                   if (status.equals(jobBean.getStatus())) {
//                       crit.add(Restrictions.eq("status", status));
//                   }
//                }
//                search += " status=" + jobBean.getStatus();
//            }
//            
//            if (jobBean.getSystemName() != null 
//                    && !jobBean.getSystemName().equals("")) {
//                crit.add(Restrictions.eq("systemName", jobBean.getSystemName()));
//                search += " hpc=" + jobBean.getSystemName();
//            }
//            
//            if (jobBean.getSoftwareName() != null 
//                    && !jobBean.getSoftwareName().equals("")) {
//                crit.add(Restrictions.like("softwareName", jobBean.getSoftwareName()));
//                search += " app=" + jobBean.getSoftwareName();
//            }
//            
//            Date start = formatDate(jobBean.getStartTime());
//            if ( start != null) {
//                crit.add(Restrictions.gt("startTime", jobBean.getStartTime()));
//                search += " after=" + jobBean.getStartTime();
//            }
//            
//            Date stop = formatDate(jobBean.getStopTime());
//            if (stop != null) {
//                crit.add(Restrictions.lt("stopTime", jobBean.getStopTime()));
//                search += " before=" + jobBean.getStopTime();
//            }
//           
//            log.debug("Searching for: " + search);
//            System.out.println("Searching for: " + search);
//            
//            jobs = crit.addOrder(Order.desc("created")).list();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return jobs;
//    }
   

	// ********************************************************** //

	public static void persist(Job job)
			throws JobException {
	    
		if (job == null) throw new JobException("Job cannot be null");
		
	    HibernateUtil.beginTransaction();
		
	    try {
            
	        Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        s.saveOrUpdate(job);
	        
	        t.commit();
	        
//	        s.flush();
            
//            s.clear();
	        
		} catch (HibernateException ex) {
			try {
				if (HibernateUtil.getSession().isOpen()) {
					HibernateUtil.rollbackTransaction();
				}
			} catch (Exception e) {}
			throw new JobException(ex);
		} finally {
		    
		}
	}

	// ********************************************************** //

	public static void delete(Job job)
			throws JobException {

		if (job == null) throw new JobException("Job cannot be null");
		
		HibernateUtil.beginTransaction();
		
		try {
			Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
			s.delete(job);
			
			t.commit();
			
//			s.flush();
            
//            s.clear();
			
		} catch (HibernateException ex) {
			HibernateUtil.rollbackTransaction();
			throw new JobException(ex);
		}
	}
    
    /**
     * 
     * @param userId
     * @return
     */
    @SuppressWarnings({ "unchecked" })
	public static List<Job> getByUser(String username, boolean limitResults) {
    	
    	if (!ServiceUtil.isValid(username)) return new ArrayList<Job>();
    	
        List<Job> jobs = null;
        HibernateUtil.beginTransaction();

log.info("step 1.0");

        try {
            
        	String hql = "from Job where user.username = :username " +
        			"and deleted = :deleted " +
        			"and hidden = :hidden " +
        			"order by created desc";
log.info("step 1.1");
        	
            Query q = HibernateUtil.getSession().createQuery(hql)
                .setString("username", username)
                .setBoolean("deleted", false)
                .setBoolean("hidden",false);
           
 log.info("step 1.2");

            if (limitResults) {
                q.setMaxResults(0);//Settings.MAX_JOBS_RETURNED);
            }
log.info("step 1.3");

            
            jobs = q.list();
log.info("step 1.4");

            
        } catch (HibernateException e) {
            throw new JobException(e);
        }
        
//            HibernateUtil.commitTransaction();
        
        return jobs;
    }

    /**
     * 
     * @param userId
     * @return
     */
    @SuppressWarnings({ "unchecked" })
	public static List<Job> getByUser(Long userId, boolean limitResults) {
    	
    	if (!ServiceUtil.isValid(userId)) return new ArrayList<Job>();
    	
        List<Job> jobs = null;
        HibernateUtil.beginTransaction();

log.info("step 2.0");

        try {
            
            Criteria c = HibernateUtil.getSession().createCriteria(Job.class)
                .add(Restrictions.eq("user.id", userId))
                .add(Restrictions.eq("deleted", false))
                .add(Restrictions.eq("hidden", false));

log.info("step 2.1");
            
            if (limitResults) {
               // c.setMaxResults(Settings.MAX_JOBS_RETURNED);
            }

log.info(c.toString());

log.info("step 2.2");
            jobs = c.addOrder(Order.desc("created")).list();
            
        } catch (HibernateException e) {
            throw new JobException(e);
        }
        
//            HibernateUtil.commitTransaction();
        
        return jobs;
    }
    
    public static void unhideAll(Long userId, Long projectId) {
    	
    	if (userId == null) throw new JobException("User id cannot be null");
    	if (projectId == null) throw new JobException("Project id cannot be null");
    	
    	HibernateUtil.beginTransaction();

        try {
            String sql = "UPDATE Jobs j SET j.hidden = :newhidden where j.userID = :uid and j.projectID = :pid and j.hidden = :oldhidden";
            
            Session s = HibernateUtil.getSession();
	        Transaction t = s.getTransaction();
	        if (!t.isActive()) {
	        	t.begin();
	        }
	        
            int rowCount = s.createSQLQuery(sql)
                .setLong("uid", userId)
                .setLong("pid", projectId)
                .setBoolean("newhidden", false)
                .setBoolean("oldhidden", true)
                .executeUpdate();
            
            t.commit();
            
            s.flush();
            
            s.clear();
            
            System.out.println("Unhide successful. Rows affected: " + rowCount);
            
        } catch (HibernateException e) {
        	HibernateUtil.rollbackTransaction();
            throw new JobException(e);
        }
        
        
    }
    
    public static void clearCache() {
        HibernateUtil.beginTransaction();

        try {
           HibernateUtil.getSession().flush();
           HibernateUtil.getSession().clear();
//           HibernateUtil.commitTransaction();    
        } catch (HibernateException e) {
            throw new JobException(e);
        }
        
        
        
    }
}

