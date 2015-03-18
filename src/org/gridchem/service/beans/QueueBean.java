/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Oct 26, 2006
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

package org.gridchem.service.beans;

import java.util.Calendar;

import org.gridchem.service.model.enumeration.QueueStatusType;

/**
 * Class containing dereferenced queue information.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class QueueBean {

    private String name;
    private long assignedCpuNumber;
    private QueueStatusType status;
    private String comment;
    private long maxQueuedJobs;
    private long maxRunningJobs;
    private Calendar maxWallClockTime;
    private Calendar maxCpuTime;
    private long running;
    private long waiting;
    private long other;
    private int maxNodes;
    private int maxCpus;
    private long maxCpuMem;
    private boolean defaultQueue;
   
    public QueueBean() {}
    
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
	public long getAssignedCpuNumber() {
		return assignedCpuNumber;
	}

	/**
	 * @param assignedCpuNumber the assignedCpuNumber to set
	 */
	public void setAssignedCpuNumber(long assignedCpuNumber) {
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
	public long getMaxQueuedJobs() {
		return maxQueuedJobs;
	}
	
	/**
	 * @param maxQueuedJobs the maxQueuedJobs to set
	 */
	public void setMaxQueuedJobs(long maxQueuedJobs) {
		this.maxQueuedJobs = maxQueuedJobs;
	}

	/**
	 * @return the maxRunningJobs
	 */
	public long getMaxRunningJobs() {
		return maxRunningJobs;
	}

	/**
	 * @param maxRunningJobs the maxRunningJobs to set
	 */
	public void setMaxRunningJobs(long maxRunningJobs) {
		this.maxRunningJobs = maxRunningJobs;
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
	 * @return the running
	 */
	public long getRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(long running) {
		this.running = running;
	}

	/**
	 * @return the waiting
	 */
	public long getWaiting() {
		return waiting;
	}

	/**
	 * @param waiting the waiting to set
	 */
	public void setWaiting(long waiting) {
		this.waiting = waiting;
	}

	/**
	 * @return the other
	 */
	public long getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(long other) {
		this.other = other;
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
	 * @return the maxCpuMem
	 */
	public long getMaxCpuMem() {
		return maxCpuMem;
	}
	
	/**
	 * @param maxCpuMem the maxCpuMem to set
	 */
	public void setMaxCpuMem(long maxCpuMem) {
		this.maxCpuMem = maxCpuMem;
	}

	/**
	 * @return the isDefault
	 */
	public boolean isDefaultQueue() {
		return defaultQueue;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setDefaultQueue(boolean isDefault) {
		defaultQueue = isDefault;
	}

	public String toString() {
        return this.getName();
    }
}
