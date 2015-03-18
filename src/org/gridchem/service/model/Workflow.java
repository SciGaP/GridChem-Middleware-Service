/**
 * 
 */
package org.gridchem.service.model;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.gridchem.service.beans.JobBean;
import org.gridchem.service.beans.WorkflowBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.WorkflowException;
import org.gridchem.service.model.enumeration.JobStatusType;

/**
 * This is the new parent class for all CCG Jobs.  A Workflow is the 
 * composition of one or more Job objects with additional metadata. Status
 * codes for the Workflow object correspond to job codes, however the 
 * status of a Workflow is not tied to any particular Job status.
 * 
 * @author dooley
 */
public class Workflow {
	
	private Long id;
	private String name;
	private String description;
	private String input;
	private Date startTime;
	private Date stopTime;
	private JobStatusType status;
	private User user;
	private Set<Job> jobs = new HashSet<Job>();
	private boolean hidden;
	private boolean deleted;
	private Date created;
	private Date lastUpdated;
	
	/**
	 * no-args constructor
	 */
	public Workflow() {
		
	}
	
	public Workflow(GMSSession session, WorkflowBean bean) 
	throws WorkflowException, JobException, RemoteException, ProviderException {
		
		
		id = bean.getId();
		name = bean.getName();
		description = bean.getDescription();
		input = bean.getInput();
		startTime = bean.getStartTime();
		stopTime = bean.getStopTime();
		status = bean.getStatus();
		
		if (!session.getUserId().equals(bean.getUserId())) {
			throw new WorkflowException("User does not have permission to this workflow.");
		}
		user = new UserDao(session)._get();
		
		for (JobBean jobBean: bean.getJobs()) {
			jobs.add(new Job(session, jobBean));
		}
		
		hidden = bean.isHidden();
		deleted = bean.isDeleted();
		created = bean.getCreated();
		lastUpdated = bean.getLastUpdated();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
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

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	/**
	 * @return the jobs
	 */
	public Set<Job> getJobs() {
		return jobs;
	}

	public void addJob(Job job) {
		this.jobs.add(job);
	}
	
	/**
	 * @param jobs the jobs to set
	 */
	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the stopTime
	 */
	public Date getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String toString() {
		String val = "workflow: " + name + ", jobs[";
		
		if (jobs.size() == 0) {
			return val + "]";
		}
		
		for (Job job: jobs) {
			val += job.getId() + ", ";
		}
		
		return val.substring(0, val.length()-3)+ "]";
	}
	
}
