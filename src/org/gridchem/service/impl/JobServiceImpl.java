package org.gridchem.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.gridchem.service.JobService;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.job.JobManager;
import org.gridchem.service.job.prediction.Qbets;
import org.gridchem.service.job.prediction.QbetsPrediction;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;

/**
 * POJO to handle operations for jobs.
 * 
 * @author dooley
 * 
 */
public class JobServiceImpl implements JobService {

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#list(java.lang.String)
	 */
	public String list(String sessionId) {
		
		HibernateUtil.flush();

		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);

		return ServiceUtil.xstream.toXML(JobManager.getJobs(manager.getSession()));
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#listAll(java.lang.String)
	 */
	public String listAll(String sessionId) {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);

		return ServiceUtil.xstream.toXML(JobManager.getAllJobs(manager.getSession()));
	}

	/* 
	 * This won't workw ith a simple example search because there are boundary qualifiers for
	 * date and wildcard matches supported, so we must revert back to the jobsearchfilter
	 * approach. To reduce the client footprint, we will add a SearchBean class from which
	 * we can specify an example job and qualifiers for each field.
	 * 
	 * (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#search(java.lang.String, java.lang.String)
	 */
	public String search(String sessionId, String sJob)
			throws JobException, SessionException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		JobBean bean = null;
		if (!ServiceUtil.isValid(sJob)) {
			throw new JobException("No job specified");
		} else {
			try {
				bean = (JobBean) ServiceUtil.xstream.fromXML(sJob);
			} catch (Exception e) {
				throw new JobException("Invalid job id specified.");
			}
		}
		
		SessionManager manager = new SessionManager(sessionId);

		List<JobBean> jobs = JobManager.search(manager.getSession(),bean);

		return ServiceUtil.xstream.toXML(jobs);
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#createJob(java.lang.String, java.lang.String)
	 */
	public String createJob(String sessionId, String sJobBean)
			throws JobException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
		"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#uploadInputFile(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String uploadInputFile(String sessionId, String sJobId,
			String sInputStream) throws JobException, PermissionException,
			SessionException {
		throw new UnsupportedOperationException(
		"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#downloadInputFile(java.lang.String, java.lang.String)
	 */
	public String downloadInputFile(String sessionId, String sJobId)
			throws JobException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
		"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#submit(java.lang.String, java.lang.String)
	 */
	public Long submit(String sessionId, String sJob) throws Exception {

		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		JobBean bean = null;
		if (!ServiceUtil.isValid(sJob)) {
			throw new JobException("No job specified");
		} else {
			try {
				bean = (JobBean) ServiceUtil.xstream.fromXML(sJob);
				
			} catch (Exception e) {
				throw new JobException("Invalid job id specified.");
			}
		}

		SessionManager manager = new SessionManager(sessionId);

		Long jobId = JobManager.submit(manager.getSession(), bean);

		return jobId;
	}

//	/**
//	 * Stop the job with the given CCG jobid.
//	 * 
//	 * @param sessionId
//	 * @param sJobId
//	 * @throws Exception
//	 */
//	public void kill(String sessionId, String sJobId) throws Exception {
//
//		if (!ServiceUtil.isValid(sessionId)) {
//			throw new SessionException("Invalid session id");
//		}
//
//		Long jobId = null;
//		if (!ServiceUtil.isValid(sJobId)) {
//			throw new ParameterException("No job specified");
//		} else {
//			try {
//				jobId = Long.valueOf(sJobId);
//			} catch (NumberFormatException e) {
//				throw new ParameterException("Invalid job id specified.");
//			}
//		}
//
//		SessionManager manager = new SessionManager(sessionId);
//
//		JobManager.kill(manager.getSession(), jobId);
//	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#kill(java.lang.String, java.lang.String)
	 */
	public String kill(String sessionId, String sJobIds) throws Exception {

		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		ArrayList<Long> jobIds = new ArrayList<Long>();
		if (!ServiceUtil.isValid(sJobIds)) {
			throw new JobException("No job specified");
		} else {
			try {
				for (String id : sJobIds.split(";")) {
					jobIds.add(Long.valueOf(id));
				}
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}

		SessionManager manager = new SessionManager(sessionId);

		for (Long id : jobIds) {

			JobManager.kill(manager.getSession(), id);
		}
		return "success";
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#delete(java.lang.String, java.lang.String)
	 */
	public String delete(String sessionId, String sJobIds) throws Exception {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		ArrayList<Long> jobIds = new ArrayList<Long>();
		if (!ServiceUtil.isValid(sJobIds)) {
			throw new JobException("No job specified");
		} else {
			try {
				for (String id : sJobIds.split(";")) {
					jobIds.add(Long.valueOf(id));
				}
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}

		SessionManager manager = new SessionManager(sessionId);

		for (Long id : jobIds) {

			JobManager.delete(manager.getSession(), id);
		}
		return "success";
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#hide(java.lang.String, java.lang.String)
	 */
	public void hide(String sessionId, String sJobIds) throws Exception {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		ArrayList<Long> jobIds = new ArrayList<Long>();
		if (!ServiceUtil.isValid(sJobIds)) {
			throw new JobException("No job specified");
		} else {
			try {
				for (String id : sJobIds.split(";")) {
					jobIds.add(Long.valueOf(id));
				}
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}

		SessionManager manager = new SessionManager(sessionId);

		for (Long id : jobIds) {

			JobManager.hide(manager.getSession(), id);
		}
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#unhide(java.lang.String, java.lang.String)
	 */
	public String unhide(String sessionId, String sJobIds) throws Exception {

		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		ArrayList<Long> jobIds = new ArrayList<Long>();
		if (!ServiceUtil.isValid(sJobIds)) {
			throw new JobException("No job specified");
		} else {
			try {
				for (String id : sJobIds.split(";")) {
					jobIds.add(Long.valueOf(id));
				}
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}

		SessionManager manager = new SessionManager(sessionId);

		for (Long id : jobIds) {

			JobManager.unhide(manager.getSession(), id);
		}
		return "success";
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#unhideAll(java.lang.String)
	 */
	public String unhideAll(String sessionId) throws Exception {

		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);

		JobManager.unhideAll(manager.getSession());
		return "success";
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#resubmitJob(java.lang.String, java.lang.String)
	 */
	public String resubmitJob(String sessionId, String sJobId)
			throws JobException, PermissionException, SessionException {
		throw new UnsupportedOperationException(
				"Operation is not yet implemented.");
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.JobService#predictStartTime(java.lang.String, java.lang.String)
	 */
	public String predictStartTime(String sessionId, String sJob) 
	throws RemoteException, ProviderException, ServiceException, ParameterException{
		
		HibernateUtil.flush();

		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		JobBean bean = null;
		if (!ServiceUtil.isValid(sJob)) {
			throw new JobException("No job specified");
		} else {
			try {
				bean = (JobBean) ServiceUtil.xstream.fromXML(sJob);
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}

		SessionManager manager = new SessionManager(sessionId);

		QbetsPrediction prediction = Qbets.findFirstAvailableResource(manager
				.getSession(), bean);

		return ServiceUtil.xstream.toXML(prediction);

	}
}
