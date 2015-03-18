package org.gridchem.service;

import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;

public interface NotificationService {

	/**
	 * Register an email notification for the given job.
	 * 
	 * @param sessionId
	 * @param sJobId
	 * @return
	 * @throws PermissionException if the session user does not own the job.
	 */
	public abstract String registerDefault(String sessionId, String sJobId)
			throws Exception;

	/**
	 * Register a notification of the given type for the given job.
	 * 
	 * @param sessionId
	 * @param sJobId
	 * @param sType
	 * @return
	 * @throws PermissionException if the session user does not own the job.
	 */
	public abstract String register(String sessionId, String sJobIds,
			String sType) throws Exception;

	/**
	 * Remove the notification registrations of the given type for the given job.
	 * 
	 * @param sessionId
	 * @param sJobId
	 * @param sType
	 * @throws NumberFormatException
	 * @throws PermissionException if the session user does not own the job.
	 */
	public abstract String remove(String sessionId, String sJobId, String sType)
			throws Exception;

	/**
	 * Remove the notification registrations for the given job.
	 * 
	 * @param sessionId
	 * @param sJobId
	 * @throws PermissionException if the session user does not own the job.
	 */
	public abstract String removeForJob(String sessionId, String sJobId)
			throws Exception;

	/**
	 * Remove all notifications for the session user.
	 * 
	 * @param sessionId
	 * @throws NotificationException
	 * @throws PermissionException
	 * @throws SessionException
	 */
	public abstract String removeAll(String sessionId) throws NotificationException,
			PermissionException, SessionException;
	
	/**
	 * Returns a serialized list of the notifications for the given job id.
	 * 
	 * @param sessionId
	 * @throws NotificationException
	 * @throws PermissionException
	 * @throws SessionException
	 */
	public abstract String getNotifications(String sessionId, String sJobId) throws NotificationException,
			PermissionException, SessionException;

}