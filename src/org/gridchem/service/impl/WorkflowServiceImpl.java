/**
 * 
 */
package org.gridchem.service.impl;

import org.gridchem.service.beans.WorkflowBean;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.WorkflowException;
import org.gridchem.service.model.Workflow;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.workflow.WorkflowManager;

/**
 * @author dooley
 *
 */
public class WorkflowServiceImpl implements WorkflowService {

	public WorkflowServiceImpl() {}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.WorkflowService#createWorkflow(java.lang.String, java.lang.String)
	 */
	public String createWorkflow(String sessionId, String sWorkflowBean)
	throws JobException, PermissionException, SessionException, WorkflowException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		if (!ServiceUtil.isValid(sWorkflowBean)) {
			throw new WorkflowException("Invalid workflow");
		}

		SessionManager manager = new SessionManager(sessionId);

		try {
			
			Workflow workflow = WorkflowManager.add(manager.getSession(), 
					(WorkflowBean)ServiceUtil.xstream.fromXML(sWorkflowBean));
			
			return ServiceUtil.xstream.toXML(workflow.getId());
			
		} catch (Exception e) {
			throw new WorkflowException("Invalid workflow representation.",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.WorkflowService#addJobToWorkflow(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addJobToWorkflow(String sessionId, String sJobId,String sWorkflowId) 
	throws JobException, PermissionException, SessionException, WorkflowException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		Long jobId = null;
		if (ServiceUtil.isValid(sJobId)) {
			try {
				jobId = Long.valueOf(sJobId);
			} catch (Exception e) {
				throw new JobException("Invalid job id");
			}
		} else {
			throw new JobException("Invalid job id");
		}
		
		Long workflowId = null;
		if (ServiceUtil.isValid(sWorkflowId)) {
			try {
				workflowId = Long.valueOf(sWorkflowId);
			} catch (Exception e) {
				throw new JobException("Invalid workflow id");
			}
		} else {
			throw new JobException("Invalid workflow id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		WorkflowManager.addJobToWorkflow(manager.getSession(), workflowId, jobId);
		
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.WorkflowService#removeJobFromWorkflow(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void removeJobFromWorkflow(String sessionId, String sWorkflowId,
		String sJobId) throws JobException, PermissionException,
		SessionException, WorkflowException {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		Long jobId = null;
		if (ServiceUtil.isValid(sJobId)) {
			try {
				jobId = Long.valueOf(sJobId);
			} catch (Exception e) {
				throw new JobException("Invalid job id");
			}
		} else {
			throw new JobException("Invalid job id");
		}
		
		Long workflowId = null;
		if (ServiceUtil.isValid(sWorkflowId)) {
			try {
				workflowId = Long.valueOf(sWorkflowId);
			} catch (Exception e) {
				throw new JobException("Invalid workflow id");
			}
		} else {
			throw new JobException("Invalid workflow id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		WorkflowManager.removeJobFromWorkflow(manager.getSession(), workflowId, jobId);
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.WorkflowService#deleteWorkflow(java.lang.String, java.lang.String)
	 */
	public void deleteWorkflow(String sessionId, String sWorkflowId) throws WorkflowException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		Long workflowId = null;
		if (ServiceUtil.isValid(sWorkflowId)) {
			try {
				workflowId = Long.valueOf(sWorkflowId);
			} catch (Exception e) {
				throw new JobException("Invalid workflow id");
			}
		} else {
			throw new JobException("Invalid workflow id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		WorkflowManager.delete(manager.getSession(), workflowId);
	}
}
