package org.gridchem.service.beans;

import java.util.Date;

import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.model.enumeration.NotificationType;

/**
 * Simple bean to handle notification information.
 * 
 * @author dooley
 *
 */
public class NotificationBean {
	private Long id;
	private Long jobId;
    private NotificationType type;
    private JobStatusType status;
    private Date created;
    private Date lastUpdated;
    
    public NotificationBean(){}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the jobId
	 */
	public Long getJobId() {
		return jobId;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public JobStatusType getStatus() {
		return status;
	}

	public void setStatus(JobStatusType status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
    public String toString() {
    	return "job " + id + " " + type.name() + " " + status.name() + " " + lastUpdated;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof NotificationBean) {
    		NotificationBean n = (NotificationBean)o;
    		return (n.type.equals(type) && n.status.equals(status) && n.id.equals(id));
    	} else {
    		return false;
    	}

    }
}
