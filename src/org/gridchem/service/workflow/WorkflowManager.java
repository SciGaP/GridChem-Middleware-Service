package org.gridchem.service.workflow;

import java.rmi.RemoteException;

import org.gridchem.service.beans.WorkflowBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.WorkflowDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.WorkflowException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.Workflow;
import org.gridchem.service.util.ServiceUtil;

public class WorkflowManager {
	
	public static Workflow add(GMSSession session, WorkflowBean bean) 
	throws WorkflowException, JobException, RemoteException, ProviderException {
	
		if (session == null) throw new SessionException("Session cannot be null");
		if (bean == null) throw new WorkflowException("Workflow cannot be null");
		
		Workflow workflow = new Workflow(session,bean);
		
		if (!workflow.getUser().getId().equals(session.getUserId())) {
			throw new PermissionException("User does not have permission to edit this workflow.");
		}
		
		WorkflowDao.persist(workflow);
		
		return workflow;
	}

	public static void addJobToWorkflow(GMSSession session, Long workflowId,
			Long jobId) throws WorkflowException {
		
		if (session == null) throw new SessionException("Session cannot be null");
		
		if (!ServiceUtil.isValid(workflowId)) throw new WorkflowException("Invalid workflow id");
		Workflow workflow = WorkflowDao.getById(workflowId);
		if (workflow == null) {
			throw new WorkflowException("Invalid workflow id");
		}
		
		if (!workflow.getUser().getId().equals(session.getUserId())) {
			throw new PermissionException("User does not have permission to edit this workflow.");
		}
		
		if (!ServiceUtil.isValid(jobId)) throw new JobException("Invalid job id");
		Job job = JobDao.getById(jobId);
		if (job == null) {
			throw new JobException("Invalid job id");
		}

		if (!job.getUser().getId().equals(session.getUserId())) {
			throw new PermissionException("User does not have permission to edit this job.");
		}
		
		if (job.getWorkflow() == null) {
			job.setWorkflow(workflow);
			JobDao.persist(job);
		} else {
			throw new WorkflowException("Job is already part of another workflow");
		}
	}

	public static void removeJobFromWorkflow(GMSSession session,
			Long workflowId, Long jobId) throws WorkflowException {
		
		if (session == null) throw new SessionException("Session cannot be null");
		
		if (!ServiceUtil.isValid(workflowId)) throw new WorkflowException("Invalid workflow id");
		Workflow workflow = WorkflowDao.getById(workflowId);
		if (workflow == null) {
			throw new WorkflowException("Invalid workflow id");
		}
		
		if (!workflow.getUser().getId().equals(session.getUserId())) {
			throw new PermissionException("User does not have permission to edit this workflow.");
		}
		
		if (!ServiceUtil.isValid(jobId)) throw new JobException("Invalid job id");
		Job job = JobDao.getById(jobId);
		if (job == null) {
			throw new JobException("Invalid job id");
		}

		if (!job.getUser().getId().equals(session.getUserId())) {
			throw new PermissionException("User does not have permission to edit this job.");
		}
		
		job.setWorkflow(null);
		JobDao.persist(job);
	}

	public static void delete(GMSSession session, Long workflowId) throws WorkflowException {
		
		if (session == null) throw new SessionException("Session cannot be null");
		
		if (!ServiceUtil.isValid(workflowId)) throw new WorkflowException("Invalid workflow id");
		Workflow workflow = WorkflowDao.getById(workflowId);
		if (workflow == null) {
			throw new WorkflowException("Invalid workflow id");
		}
		
		if (workflow.getUser().getId().equals(session.getUserId())) {
			for (Job job: workflow.getJobs()) {
				JobDao.delete(job);
			}
			WorkflowDao.delete(workflow);
		} else {
			throw new PermissionException("User does not have permission to delete this workflow.");
		}
	}

}
