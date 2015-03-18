package org.gridchem.service.impl;

import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.WorkflowException;

public interface WorkflowService {

	public abstract String createWorkflow(String sessionId, String sWorkflowBean)
			throws JobException, PermissionException, SessionException,
			WorkflowException;

	public abstract void addJobToWorkflow(String sessionId, String sJobId,
			String sWorkflowId) throws JobException, PermissionException,
			SessionException, WorkflowException;

	public abstract void removeJobFromWorkflow(String sessionId,
			String sWorkflowId, String sJobId) throws JobException,
			PermissionException, SessionException, WorkflowException;

	public abstract void deleteWorkflow(String sessionId, String sWorkflowId)
			throws WorkflowException;

}