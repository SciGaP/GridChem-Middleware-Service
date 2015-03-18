/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Mar 16, 2007
 * 
 * Developed by: CCT, Center for Computation and Technology, 
 *              NCSA, University of Illinois at Urbana-Champaign
 *              OSC, Ohio Supercomputing Center
 *              TACC, Texas Advanced Computing Center
 *              UKy, University of Kentucky
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

package org.gridchem.service.model;

import java.util.Date;

import org.gridchem.service.beans.JobBean;
import org.gridchem.service.beans.NotificationBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.NotificationType;

/**
 * Dereferenced handles for notifications registered with the GRMS and GMS.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class Notification {

	private Long id;
    private Job job;
    private NotificationType type;
    private JobStatusType status;
    private String subject;
    private String message;
    private Date lastUpdated;
    private Date created = new Date();
    private boolean delivered = false;
    
    public Notification(){}
    
    /**
     * Creates an email notification for the given job
     * @param user
     * @param job
     * @param status
     * @param message
     */
    public Notification(JobBean job, JobStatusType status, String message) {
        this.status = status;
        this.message = message;
        this.type = NotificationType.EMAIL;
        this.job = JobDao.getById(job.getId());
    }
    
    /**
     * @param job
     * @param type
     * @param status
     */
    public Notification(JobBean job, 
            NotificationType type, JobStatusType status) {
        this.type = type;
        this.status = status;
        this.job = JobDao.getById(job.getId());
        this.message = "";
    }
    
    /**
     * @param job
     * @param type
     * @param status
     * @param message
     */
    public Notification(JobBean job, NotificationType type, 
            JobStatusType status, String message) {
        this.type = type;
        this.status = status;
        this.message = message;
        this.job = JobDao.getById(job.getId());
        
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the status
     */
    public JobStatusType getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(JobStatusType status) {
        this.status = status;
    }
    /**
     * @return the type
     */
    public NotificationType getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(NotificationType type) {
        this.type = type;
    }
    
    /**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

    /**
     * @return the delivered
     */
    public boolean isDelivered() {
        return delivered;
    }

    /**
     * @param delivered the delivered to set
     */
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    /**
     * @return the notificationId
     */
    public Long getId() {
        return id;
    }

    /**
     * @param notificationId the notificationId to set
     */
    public void setId(Long notificationId) {
        this.id = notificationId;
    }

    /**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        final Notification notif = (Notification) o;
        if (!type.equals(notif.type)) return false;
        if (!status.equals(notif.status)) return false;
        if (!job.getId().equals(notif.job.getId())) return false;
        return true;
    }
    
    public String toString() {
        return getType() + " " + getStatus() + " " +
            " " + getMessage();
    }
    
    public NotificationBean toBean() {
    	NotificationBean bean = new NotificationBean();
    	bean.setId(id);
    	bean.setType(type);
    	bean.setStatus(status);
    	bean.setCreated(created);
    	bean.setLastUpdated(lastUpdated);
    	bean.setJobId(job.getId());
    	
    	return bean;
    }
}
