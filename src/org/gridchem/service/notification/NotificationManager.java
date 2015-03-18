/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jun 19, 2006
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

package org.gridchem.service.notification;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.NotificationDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.job.JobManager;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.Notification;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.NotificationType;
import org.gridchem.service.util.ServiceUtil;

/**
 * Management class to queue and batch perform notifications upon event
 * triggers such as job updates, notification events from other services,
 * etc.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class NotificationManager {
    public static Logger log = Logger.getLogger(NotificationManager.class.getName());
    
    /**
     * Send all notifications registered with the job and it's current status.
     * 
     * @throws NotificationException
     */
    public static void sendChangeOfStatusMessage(UserBean user, Job job) throws NotificationException {
        log.debug("JobBean[" + job.getId() + "] changed status to " + job.getStatus() +
                ". Checking for registered notification.");
        
        Collection<Notification> notifications = NotificationDao.getAllByJobID(job.getId());
        
        for(Notification notification: notifications) {
            if (notification.getStatus().equals(job.getStatus())) {
                // we have a notification registered for this status on this job
                log.debug("Sending " + notification.getType() + " notification to user.");
                
                // if the job failed update the status message.
                if (notification.getStatus().equals(JobStatusType.FAILED)) {
                	notification.setMessage(notification.getMessage() + 
                            "\n\nFailure caused by: " + 
                            job.getErrorDescription());
                }
                
                if (notification.getType().equals(NotificationType.EMAIL) && 
                		!notification.isDelivered()) {
                    EmailMessage.send(user, notification);
                } else if (notification.getType().equals(NotificationType.SMS)) {
                    TextMessage.send(user, notification);
                } else if (notification.getType().equals(NotificationType.IM)) {
                    IMMessage.send(user, notification);
                } else {
                    log.debug(notification.getType() + " delivery not currently supported.");
                    throw new NotificationException("Invalid notification type");
                }
                
            }
        }
        
        // if the job stopped, delete the notifications in the db for the given job.
        if (job.getStatus().equals(JobStatusType.FINISHED) || 
                job.getStatus().equals(JobStatusType.FAILED) || 
                job.getStatus().equals(JobStatusType.RUNTIME_ERROR) || 
                job.getStatus().equals(JobStatusType.STOPPED) ||
                job.getStatus().equals(JobStatusType.REMOVED)) {
            
            try {
                NotificationDao.delete(notifications);
            } catch (Exception e) {
                log.error(e);
            } 
        }
    }
    
    /**
     * Return a HashSet of NotificationDTO objects representing the notifications
     * registered with this job.
     * 
     * @param jobID
     * @return
     * @throws NotificationException 
     */
    public static List<Notification> getAll(Long jobID) throws NotificationException {
        return NotificationDao.getAllByJobID(jobID);
    }
    
//    /**
//     * Update the notifications for the given job with the ones listed in this hashset.
//     * 
//     * @param newNotificationDTOs
//     * @throws Exception
//     */
//    public static void addAll(Collection<Notification> notifications) throws Exception {
//        
//    	for (Notification n: notifications) {
//	    	
//    		Job job = JobDao.findById(n.getJobId());
//	        
//	        if (!job.getStatus().equals(JobStatusType.FINISHED) &&
//	                !job.getStatus().equals(JobStatusType.STOPPED) &&
//	                !job.getStatus().equals(JobStatusType.TIME_ELAPSED) &&
//	                !job.getStatus().equals(JobStatusType.NOT_IN_QUEUE) &&
//	                !job.getStatus().equals(JobStatusType.RUNTIME_ERROR)) {
//	            throw new NotificationException("JobBean is no longer running. Notifications " +
//	                    "for this job disabled.");
//	        } 
//	        
//	        //  the db will update existing notifications and add the rest.
//	    	NotificationDao.persist(notifications);
//    	}
//    	
//    }
    
//    /**
//     * Update the notifications for the given job with the ones listed in this hashset.
//     * 
//     * @param notifications
//     * @throws Exception
//     */
//    public static void add(HashSet<Notification> notifications) throws Exception {
//    	NotificationDao.persist(notifications);
//    }
    
    public static Long add(Long jobId) throws NotificationException {
    	if (jobId == null) throw new NotificationException("No job id specified.");
    	
    	try {
	    	if (!JobManager.isRunning(jobId)) {
	    		throw new NotificationException("Job is no longer running. " +
	    				"Notifications for this job disabled.");
	    	}
    	} catch (JobException e) {
    		throw new NotificationException(e);
    	}
    	
    	Notification n = getDefaultNotification();
    	n.setJob(JobDao.getById(jobId));
    	
    	NotificationDao.add(n);
    	
    	return n.getId();
    }
    
    public static Long add(Long jobId, NotificationType type) throws NotificationException {
    	
    	if (jobId == null) throw new NotificationException("No job id specified.");
    	if (type == null) throw new NotificationException("No job type specified.");
    	
    	try {
    		if (!JobManager.isRunning(jobId)) {
    			throw new NotificationException("Job is no longer running. " +
    				"Notifications for this job disabled.");
    		}
    	} catch (JobException e) {
    		throw new NotificationException(e);
    	}	
    	
    	Notification n = getDefaultNotification();
    	n.setJob(JobDao.getById(jobId));
    	n.setStatus(JobStatusType.FINISHED);
    	n.setType(type);
    	
    	NotificationDao.add(n);
    	
    	return n.getId();
    }
    
    public static void remove(Long jobId, NotificationType type) throws NotificationException {
    	
    	if (jobId == null) throw new NotificationException("No job id given.");
    	if (type == null) throw new NotificationException("No type id given.");
    	
    	List<Notification> notifs = NotificationDao.getAllByJobID(jobId);
    	
    	if (!ServiceUtil.isValid(notifs)) throw new NotificationException("Invalid job id.");
    	
    	for (Notification n: notifs) {
        	if (n.getType().equals(type)) {
        		NotificationDao.delete(n);
        	}
        }
    }
        
    public static void remove(Long jobId) throws NotificationException {
        NotificationDao.delete(NotificationDao.getAllByJobID(jobId));
    }
    
    public static void remove(HashSet<Notification> notifications) throws NotificationException {
    	for (Notification n: notifications) {
    		if (!NotificationDao.exists(n)) {
    			throw new NotificationException("Notification does not exist.");
    		}
    		NotificationDao.delete(n);
    	}
    }
    
    public static void clear(Long userId) throws NotificationException {
    	NotificationDao.deleteAllForUser(userId);
    }
    
    public static Notification getDefaultNotification() {
    	Notification n = new Notification();
    	n.setStatus(JobStatusType.FINISHED);
    	n.setDelivered(false);
    	n.setType(NotificationType.EMAIL);
    	return n;
    }

}
