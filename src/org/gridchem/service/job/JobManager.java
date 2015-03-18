/**
 * 
 */
package org.gridchem.service.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.job.task.CheckStatusTask;
import org.gridchem.service.job.task.DeleteTask;
import org.gridchem.service.job.task.KillTask;
import org.gridchem.service.job.task.SubmitTask;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.enumeration.JobStatusType;

/**
 * @author dooley
 *
 */
public class JobManager {
	public static final Logger logger = Logger.getLogger(JobManager.class);
//	
//	private GMSSession session;
//	
//	public JobManager(GMSSession session) {
//		this.session = session;
//	}
	
	public static List<JobBean> getJobs(GMSSession session) {
		
		if (session == null) throw new JobException("Session cannot be null");
		
		List<JobBean> jobs = new ArrayList<JobBean>();
		for (Job job: JobDao.getByUser(session.getUserId(), true)) {
			jobs.add(job.toBean());
		}
		
		return jobs;
	}
	
	public static List<JobBean> getAllJobs(GMSSession session) {
		
		if (session == null) throw new JobException("Session cannot be null");
		
		List<JobBean> jobs = new ArrayList<JobBean>();
		for (Job job: JobDao.getByUser(session.getUserId(), false)) {
			jobs.add(job.toBean());
		}
		
		return jobs;
	}
	
	
	public static void updateMetaData(JobBean jobBean, String xml) {
		
		if (jobBean == null) throw new JobException("JobBean cannot be null");
		
		if (xml == null) throw new JobException("Metadata cannot be null");
		
        Job job = JobDao.getById(jobBean.getId());
        
        if (job == null) throw new JobException("Invalid job id");
        
        job.setMetaData(xml);
        JobDao.persist(job);
    }
    
	/**
     * Submit this job. Status of job submission is returned in the form
     * of a GMS_JOB_* invariant.  If an exception occurs in submission, 
     * the content is wrapped and a JobException is thrown.
     *
     */
    public static Long submit(GMSSession session, JobBean jobBean) throws JobException{
        
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (jobBean == null) throw new JobException("JobBean cannot be null");
    	
        SubmitTask submitJob = new SubmitTask(session,jobBean, false);
        
        Job job = null;
        
        try {
            
            job = submitJob.execute();
        
        } catch(Exception e) { 
            throw new JobException(e);
        }
        
        logger.debug("Successfully updated record for job " + job.getId() + " after submission.\n");
        
        return job.getId();
    }
    
    
    /**
     * Kills the current job by performing a <code>KillTask</code>
     * 
     * @throws Exception
     * @see KillTask
     * 
     */
    public static void kill(GMSSession session, Long jobId) throws JobException {
    	
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (jobId == null) throw new JobException("Job id cannot be null");
    	
        KillTask killTask = new KillTask(session, jobId);
	    
        try {   
	        killTask.execute();
	    } catch(Exception e) { 
	        throw new JobException(e);
	    }
        
	    logger.debug("Successfully killed job " + jobId);
    }
    
    /**
     * Hides the given job.
     * 
     * @param session
     * @param jobBean
     * @throws PermissionException if the session user is not the owner of the job
     */
    public static void hide(GMSSession session, Long jobId) throws JobException {
    	
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (jobId == null) throw new JobException("Job id cannot be null");
    	
    	Job job = JobDao.getById(jobId);
    	
    	if (job == null) throw new JobException("Invalid job id");
    	
    	if (job.getUser().getId().equals(session.getUserId())) {
    		if (!job.isHidden()) {
    			job.setHidden(true);
	    		JobDao.persist(job);
    		}
    		logger.debug("Successfully hid job " + jobId);
    	} else {
    		throw new PermissionException("Permission denied");
    	}
        
    }
    
    /**
     * Unhides the given job.
     * 
     * @param session
     * @param jobId
     * @throws PermissionException if the session user is not the owner of the job
     */
    public static void unhide(GMSSession session, Long jobId) throws JobException {
    	
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (jobId == null) throw new JobException("Job id cannot be null");
    	
    	Job job = JobDao.getById(jobId);
    	
    	if (job == null) throw new JobException("Invalid job id");
    	
    	if (job.getUser().getId().equals(session.getUserId())) {
    		if (job.isHidden()) {
    			job.setHidden(false);
    			JobDao.persist(job);
    		}
    		logger.debug("Successfully unhid job " + jobId);
    	} else {
    		throw new PermissionException("Permission denied");
    	}
        
    }
    
    /**
     * Unhides all jobs for the session user.
     * 
     * @param session
     * @throws Exception
     */
    public static void unhideAll(GMSSession session) throws JobException {
    	
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	JobDao.unhideAll(session.getUserId(), session.getProjectId());
    	
    	logger.debug("Successfully unhid all jobs for user " + session.getUserId());
    }
    
    /**
     * Deletes the current job by performing a <code>DeleteTask</code>
     * 
     * @throws Exception
     * @see DeleteTask
     * 
     */
    public static void delete(GMSSession session, Long jobId) throws JobException {
    	
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (jobId == null) throw new JobException("Job id cannot be null");
    	
        DeleteTask deleteTask = new DeleteTask(session,jobId);
        
        deleteTask.execute();
        
        logger.debug("Successfully deleted job " + jobId);
    }
    
    /**
     * Resolves the current status of a job to running or not running.
     * 
     * @param jobId
     * @return true if the job running, false otherwise.
     */
    public static boolean isRunning(Long jobId) throws JobException {
    	
    	if (jobId == null) throw new JobException("Null job id given.");
        
    	boolean running = false;
        
        Job job = JobDao.getById(jobId);
        
        if (job == null) throw new JobException("No job with the given id found.");
        
        if (job.getStatus().equals(JobStatusType.FAILED) || 
        		job.getStatus().equals(JobStatusType.FINISHED) ||
        		job.getStatus().equals(JobStatusType.REMOVED) ||
        		job.getStatus().equals(JobStatusType.STOPPED) ||
        		job.getStatus().equals(JobStatusType.RUNTIME_ERROR) ||
        		job.getStatus().equals(JobStatusType.SUBMISSION_ERROR) ||
        		job.getStatus().equals(JobStatusType.HOLD) ||
        		job.getStatus().equals(JobStatusType.NOT_IN_QUEUE) ||
        		job.getStatus().equals(JobStatusType.TIME_ELAPSED) ||
        		job.getStatus().equals(JobStatusType.UNKNOWN)) {
            running = false;
        } else {
            running = true;
        }
        
        return running;
    }
    
//    public File getOutput(GMSSession session, Long jobId) throws Exception {
//        GetFileTask getFileTask = new GetFileTask(session, jobId);
//        
//        return getFileTask.execute();
//    }
    
    public static List<JobBean> search(GMSSession session, JobBean bean) {
    	
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (bean == null) throw new JobException("Job bean cannot be null");
    	
    	
    	List<JobBean> results = new ArrayList<JobBean>();
    	
    	bean.setUserId(session.getUserId());
    	
    	for (Job job: JobDao.getByExample(bean)) {
    		results.add(job.toBean());
    	}
    	
    	return results;
    }
//    /**
//     * Find all historical jobs matching the search criteria specified in the 
//     * JobBean object. In all cases, zero matching jobs is not an error, it's 
//     * a valid result, thus no exceptions are thrown unless the user does not
//     * own the resulting job.
//     * 
//     * @param jobDTO
//     * @return
//     */
//    public static LinkedHashSet<JobBean> findMatchingJobs(JobBean jobDTO, Long userID) {
//        LinkedHashSet<JobBean> results = new LinkedHashSet<JobBean>();
//        
//        Collection<Job> jobs = JobDao.findMatching(jobDTO,userID);
//        
//        for(Job job: jobs) {
//            if (job != null) {
//                if (job.getUserId().equals(userID)) {
//                    results.add(new JobBean(job));
//                }
//            }
//        }
//        
//        return results;
//    }
//    
//    /**
//     * Find all historical jobs matching the search criteria specified in the 
//     * JobBean object. In all cases, zero matching jobs is not an error, it's 
//     * a valid result, thus no exceptions are thrown unless the user does not
//     * own the resulting job.
//     * 
//     * @param jobDTO
//     * @return
//     */
//    public static ArrayList<JobBean> findMatchingJobs(JobSearchFilter jsf, 
//            Long userID, Long projectID) {
//        ArrayList<JobBean> results = new ArrayList<JobBean>();
//        
//        Collection<Job> jobs = JobDao.findMatching(jsf,userID,projectID);
//        
//        for(Job job: jobs) {
//            if (job != null) {
//                results.add(new JobBean(job));
//            }
//        }
//        
//        return results;
//    }
    
    /**
     * Query the resource associated with this job for its status.  This
     * bypasses the information service and directly queries the accounting
     * logs on the remote resource.
     * 
     * @param GMSSession
     * @return JobStatusType
     */
    public static JobStatusType checkStatus(GMSSession session, Long jobId) throws JobException {

    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (jobId == null) throw new JobException("Job id cannot be null");
    	    	
        CheckStatusTask csTask = new CheckStatusTask(session, jobId);
        
        Job job = csTask.execute();
        
        return job.getStatus();
    }
    

	public static boolean isJobOwnedByUser(GMSSession session, Long jobId) throws JobException {
		
    	if (session == null) throw new JobException("Session cannot be null");
    	
    	if (jobId == null) throw new JobException("Job id cannot be null");

		Job job = JobDao.getById(jobId);
		
		if (job == null) throw new JobException("Invalid job id.");
		
		return job.getUser().getId().equals(session.getUserId());
	}
}
