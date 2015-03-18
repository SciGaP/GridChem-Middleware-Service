/**
 * 
 */
package org.gridchem.service.impl;

import org.gridchem.service.TriggerService;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;

/**
 * @author dooley
 *
 */
public class TriggerServiceImpl implements TriggerService {
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.TriggerService#update(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String update(String apiKey, String sJobId, String sStatus) throws JobException {
		
		HibernateUtil.flush();
		
		if (!ServiceUtil.isValid(sStatus)) {
			throw new JobException("Invalid job status type");
		} else if (apiKey.equals(Settings.API_KEY)) {
			throw new PermissionException("Invalid job status type");
		}
		
		Long jobId = null;
		if (!ServiceUtil.isValid(sJobId)) {
			throw new JobException("No job specified");
		} else {
			try {
				jobId = Long.valueOf(sJobId);
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}
		
		if (!ServiceUtil.isValid(sStatus)) {
			throw new JobException("Invalid job status type");
		}
		
		JobStatusType status = null;
		try {
			status = JobStatusType.valueOf(sStatus);
		} catch (Exception e) {
			throw new JobException("Invalid job status type");
		}
		
		Job job = JobDao.getById(jobId);
		job.setStatus(status);
		JobDao.persist(job);
		
		return "success";

	}

}
