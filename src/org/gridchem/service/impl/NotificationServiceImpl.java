package org.gridchem.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.gridchem.service.NotificationService;
import org.gridchem.service.beans.NotificationBean;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.job.JobManager;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Notification;
import org.gridchem.service.model.enumeration.NotificationType;
import org.gridchem.service.notification.NotificationManager;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;


/**
 * POJO to handle operations for notifications.
 * 
 * @author dooley
 *
 */
@SuppressWarnings("unused")
public class NotificationServiceImpl implements NotificationService {

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.NotificationService#register(java.lang.String, java.lang.String)
	 */
	public String registerDefault(String sessionId, String sJobId) 
	throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
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
		

		SessionManager manager = new SessionManager(sessionId);
		
		if (JobManager.isJobOwnedByUser(manager.getSession(),jobId)) {
			return NotificationManager.add(jobId).toString();
		} else {
			throw new PermissionException("Permission denied. User does not own job.");
		}
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.NotificationService#register(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String register(String sessionId, String sJobIds, String sType) 
	throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		ArrayList<Long> jobIds = new ArrayList<Long>();
		if (!ServiceUtil.isValid(sJobIds)) {
			throw new JobException("No job specified");
		} else {
			try {
				for (String jobId : sJobIds.split(";")) {
					jobIds.add(Long.valueOf(jobId));
				}
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}
		
		NotificationType type = null;
		if (ServiceUtil.isValid(sType)) {
			try {
				type = NotificationType.valueOf(sType);
			} catch (IllegalArgumentException e) {
				throw new NotificationException("Invalid notification type specified");	
			}
		} else {
			throw new NotificationException("No notification type specified");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		String sNotificationIds = "";
		for (Long jobId: jobIds) {
			if (!JobManager.isJobOwnedByUser(manager.getSession(), jobId)) {
				throw new PermissionException("Permission denied. User does not own job.");
			}
			
			sNotificationIds += ";" + NotificationManager.add(jobId, type).toString();
		}
		
		return sNotificationIds.substring(1);
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.NotificationService#remove(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String remove(String sessionId, String sJobId, String sType) 
	throws Exception {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
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
		
		NotificationType type = null;
		if (ServiceUtil.isValid(sType)) {
			try {
				type = NotificationType.valueOf(sType);
			} catch (IllegalArgumentException e) {
				throw new NotificationException("Invalid notification type specified");	
			}
		} else {
			throw new NotificationException("No notification type specified");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		if (JobManager.isJobOwnedByUser(manager.getSession(),jobId)) {
			NotificationManager.remove(jobId, type);
		} else {
			throw new PermissionException("Permission denied. User does not own job.");
		}
		return "success";
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.NotificationService#remove(java.lang.String, java.lang.String)
	 */
	public String removeForJob(String sessionId, String sJobId) throws Exception {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		Long jobId = null;
		if (!ServiceUtil.isValid(sJobId)) {
			throw new JobException("No job specified");
		} else {
			try {
				jobId = Long.valueOf(sJobId);
			} catch (NumberFormatException e) {
				throw new NotificationException("Invalid job id specified.");
			}
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		if (JobManager.isJobOwnedByUser(manager.getSession(),jobId)) {
			NotificationManager.remove(jobId);
		} else {
			throw new PermissionException("Permission denied. User does not own job.");
		}
		return "success";
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.NotificationService#remove(java.lang.String)
	 */
	public String removeAll(String sessionId) 
	throws NotificationException, PermissionException, SessionException {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		NotificationManager.clear(manager.getSessionUserId());
		
		return "success";
	}

	public String getNotifications(String sessionId, String sJobId)
			throws NotificationException, PermissionException, SessionException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		Long jobId = null;
		if (!ServiceUtil.isValid(sJobId)) {
			throw new JobException("No job specified");
		} else {
			try {
				jobId = new Long(sJobId);
			} catch (NumberFormatException e) {
				throw new JobException("Invalid job id specified.");
			}
		}
		
		SessionManager manager = new SessionManager(sessionId);
		List<NotificationBean> beans = new ArrayList<NotificationBean>();
		for(Notification n: NotificationManager.getAll(jobId)) {
			beans.add(n.toBean());
		}
		return ServiceUtil.xstream.toXML(beans);
	}
}
