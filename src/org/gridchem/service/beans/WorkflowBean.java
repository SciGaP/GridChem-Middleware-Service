/**
 * 
 */
package org.gridchem.service.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.gridchem.service.model.enumeration.JobStatusType;

/**
 * @author dooley
 *
 */
public class WorkflowBean {


	private Long id;
	private String name;
	private String description;
	private String input;
	private Date startTime;
	private Date stopTime;
	private JobStatusType status;
	private Long userId;
	private Set<JobBean> jobs = new HashSet<JobBean>();
	private boolean hidden;
	private boolean deleted;
	private Date created;
	private Date lastUpdated;
	
	/**
	 * no-args constructor
	 */
	public WorkflowBean() {
		
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

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	/**
	 * @return the jobs
	 */
	public Set<JobBean> getJobs() {
		return jobs;
	}

	public void addJob(JobBean job) {
		this.jobs.add(job);
	}
	
	/**
	 * @param jobs the jobs to set
	 */
	public void setJobs(Set<JobBean> jobs) {
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
		
		for (JobBean job: jobs) {
			val += job.getId() + ", ";
		}
		
		return val.substring(0, val.length()-3)+ "]";
	}
}
