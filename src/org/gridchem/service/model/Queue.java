/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 24, 2005
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

package org.gridchem.service.model;

import java.util.Calendar;
import java.util.Date;

import org.gridchem.service.beans.QueueBean;
import org.gridchem.service.model.enumeration.QueueStatusType;


/**
 * Represents a batch scheduling queue on a compute resource. A <tt>Queue</tt>
 * is associated in a 1 to many relationship with the <tt>Job</tt> class, and
 * is dependent upon a <tt>ComputeResource</tt>.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 * 
 * @see Job
 * @see ComputeResource
 */
public class Queue {
    private Long id = null;
    private String name;
    private String comment;
    private QueueStatusType status;
    private boolean theDefault;
	private Long assignedCpuNumber;
	private Long maxQueuedJobs;
	private Long maxRunningJobs;
	private Calendar maxWallClockTime;
    private Long running;
    private Long waiting;
    private Long other;
    private Calendar maxCpuTime;
    private int maxCpus;
    private int maxNodes;
    private Date lastUpdate;
    private Long maxCpuMem;
    private ComputeResource compute;
    
    public Queue() {}
    
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
	 * @return the assignedCpuNumber
	 */
	public Long getAssignedCpuNumber() {
		return assignedCpuNumber;
	}

	/**
	 * @param assignedCpuNumber the assignedCpuNumber to set
	 */
	public void setAssignedCpuNumber(Long assignedCpuNumber) {
		this.assignedCpuNumber = assignedCpuNumber;
	}

	/**
	 * @return the status
	 */
	public QueueStatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(QueueStatusType status) {
		this.status = status;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the maxQueuedJobs
	 */
	public Long getMaxQueuedJobs() {
		return maxQueuedJobs;
	}

	/**
	 * @param maxQueuedJobs the maxQueuedJobs to set
	 */
	public void setMaxQueuedJobs(Long maxQueuedJobs) {
		this.maxQueuedJobs = maxQueuedJobs;
	}

	/**
	 * @return the maxRunningJobs
	 */
	public Long getMaxRunningJobs() {
		return maxRunningJobs;
	}

	/**
	 * @param maxRunningJobs the maxRunningJobs to set
	 */
	public void setMaxRunningJobs(Long maxRunningJobs) {
		this.maxRunningJobs = maxRunningJobs;
	}

	/**
	 * @return the useAsDefault
	 */
	public boolean isTheDefault() {
		return theDefault;
	}

	/**
	 * @param useAsDefault the useAsDefault to set
	 */
	public void setTheDefault(boolean theDefault) {
		this.theDefault = theDefault;
	}

	/**
	 * @return the maxCpuMem
	 */
	public Long getMaxCpuMem() {
		return maxCpuMem;
	}

	/**
	 * @param maxCpuMem the maxCpuMem to set
	 */
	public void setMaxCpuMem(Long maxCpuMem) {
		this.maxCpuMem = maxCpuMem;
	}

	/**
	 * @return the maxCpus
	 */
	public int getMaxCpus() {
		return maxCpus;
	}

	/**
	 * @param maxCpus the maxCpus to set
	 */
	public void setMaxCpus(int maxCpus) {
		this.maxCpus = maxCpus;
	}

	/**
	 * @return the maxCpuTime
	 */
	public Calendar getMaxCpuTime() {
		return maxCpuTime;
	}

	/**
	 * @param maxCpuTime the maxCpuTime to set
	 */
	public void setMaxCpuTime(Calendar maxCpuTime) {
		this.maxCpuTime = maxCpuTime;
	}

	/**
	 * @return the maxNodes
	 */
	public int getMaxNodes() {
		return maxNodes;
	}

	/**
	 * @param maxNodes the maxNodes to set
	 */
	public void setMaxNodes(int maxNodes) {
		this.maxNodes = maxNodes;
	}

	/**
	 * @return the maxWallClockTime
	 */
	public Calendar getMaxWallClockTime() {
		return maxWallClockTime;
	}

	/**
	 * @param maxWallClockTime the maxWallClockTime to set
	 */
	public void setMaxWallClockTime(Calendar maxWallClockTime) {
		this.maxWallClockTime = maxWallClockTime;
	}

	/**
	 * @return the running
	 */
	public Long getRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(Long running) {
		this.running = running;
	}

	/**
	 * @return the waiting
	 */
	public Long getWaiting() {
		return waiting;
	}

	/**
	 * @param waiting the waiting to set
	 */
	public void setWaiting(Long waiting) {
		this.waiting = waiting;
	}

	/**
	 * @return the other
	 */
	public Long getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(Long other) {
		this.other = other;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @param compute the compute to set
	 */
	public void setCompute(ComputeResource compute) {
		this.compute = compute;
	}

	/**
	 * @return the compute
	 */
	public ComputeResource getCompute() {
		return compute;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Queue)) return false;
		final Queue queue = (Queue) o;
		if (!getName().equals(queue.getName())) return false;
		if (!getCompute().getId().equals(queue.getCompute().getId())) return false;
		return true;
	}
	
	public int hashCode() {
		return getName().hashCode();
	}
	
	public String toString() {
		return  "Queue ('" + getId() + "'), " +
				"Queue name: '" + getName() + "'" +
				"Total CPUs: '" + getAssignedCpuNumber() + "'" +
				"Queue Status: '" + getStatus().toString() + "'" +
				"Max Jobs in Queue: '" + maxQueuedJobs + "'" +
				"Max Running Jobs: '" + maxRunningJobs + "'" +
				"Wall Clock Limit: '" + maxWallClockTime.getTimeInMillis() + "'" +
				"CPU Clock Time Limit: '" + maxCpuTime.getTimeInMillis() + "'" +
				"Currently Running Jobs: '" + getRunning() + "'" +
                "Currently Waiting Jobs: '" + getWaiting() + "'" +
                "Currently Other Jobs: '" + getOther() + "'" +
				"Last Updated: '" + getLastUpdate() + "'";
	}
	
	public int compareTo(Object o) {
		if (o instanceof Queue)
			return this.getLastUpdate().compareTo( ((Queue)o).getLastUpdate() );
		return 0;
	}

	// ********************** Helper Methods ********************** //
    
    public QueueBean toBean() {
    	QueueBean bean = new QueueBean();
    	bean.setAssignedCpuNumber(assignedCpuNumber);
    	bean.setComment(comment);
    	bean.setDefaultQueue(theDefault);
    	bean.setMaxCpuMem(maxCpuMem);
    	bean.setMaxCpus(maxCpus);
    	bean.setMaxCpuTime(maxCpuTime);
    	bean.setMaxNodes(maxNodes);
    	bean.setMaxQueuedJobs(maxQueuedJobs);
    	bean.setMaxRunningJobs(maxRunningJobs);
    	bean.setMaxWallClockTime(maxWallClockTime);
    	bean.setName(name);
    	bean.setOther(other);
    	bean.setRunning(running);
    	bean.setStatus(status);
    	bean.setWaiting(waiting);
    	
    	return bean;
    }
}
