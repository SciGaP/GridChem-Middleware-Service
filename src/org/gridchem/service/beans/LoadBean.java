/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Feb 3, 2006
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

/**
 * Helper class to reflect the current state of a resource
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class LoadBean {
	private long cpu;
	private long memory;
	private long disk;
	private long queue;
    private long jobsRunning;
    private long jobsQueued;
    private long jobsOther;
	
	/**
	 * default no-args constructor
	 */
	public LoadBean() {
		this.cpu = 0;
		this.memory = 0;
		this.disk = 0;
		this.queue = 0;
	}
    
    public LoadBean(long cpu, long memory, long disk,
            long queue, long running, long queued,
            long other) {
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
        this.queue = queue;
        this.jobsRunning = running;
        this.jobsQueued = queued;
        this.jobsOther = other;
    }

	
	/**
	 * @return Returns the cpu.
	 */
	public long getCpu() {
		return cpu;
	}
	/**
	 * @param cpu The cpu to set.
	 */
	public void setCpu(long cpu) {
		this.cpu = cpu;
	}
	/**
	 * @return Returns the disk.
	 */
	public long getDisk() {
		return disk;
	}
	/**
	 * @param disk The disk to set.
	 */
	public void setDisk(long disk) {
		this.disk = disk;
	}
	/**
	 * @return Returns the memory.
	 */
	public long getMemory() {
		return memory;
	}
	/**
	 * @param memory The memory to set.
	 */
	public void setMemory(long memory) {
		this.memory = memory;
	}
	/**
	 * @return Returns the queue.
	 */
	public long getQueue() {
		return queue;
	}
	/**
	 * @param queue The queue to set.
	 */
	public void setQueue(long queue) {
		this.queue = queue;
	}
    /**
     * @return the jobsOther
     */
    public long getJobsOther() {
        return jobsOther;
    }
    /**
     * @param jobsOther the jobsOther to set
     */
    public void setJobsOther(long jobsOther) {
        this.jobsOther = jobsOther;
    }
    /**
     * @return the jobsQueued
     */
    public long getJobsQueued() {
        return jobsQueued;
    }
    /**
     * @param jobsQueued the jobsQueued to set
     */
    public void setJobsQueued(long jobsQueued) {
        this.jobsQueued = jobsQueued;
    }
    /**
     * @return the jobsRunning
     */
    public long getJobsRunning() {
        return jobsRunning;
    }
    /**
     * @param jobsRunning the jobsRunning to set
     */
    public void setJobsRunning(long jobsRunning) {
        this.jobsRunning = jobsRunning;
    }
	
//	 ********************** Common Methods ********************** //
    
    public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof LoadBean)) return false;
		final LoadBean load = (LoadBean) o;
		if (cpu != load.cpu) return false;
		if (memory != load.memory) return false;
		if (disk != load.disk) return false;
		if (queue != load.queue) return false;
		return true;
	}
	
	public int hashCode() {
		return (new String(this.toString()+
				this.toString())).hashCode();
	}
	
	public String toString() {
		return  "CPU: " + getCpu() + ", " +
				"Memory: " + getMemory() + ", " +
				"Disk: " + getDisk() + ", " +
				"Queue: " + getQueue();		
	}
	
	public long compareTo(Object o) {
		if (o instanceof SoftwareInstallationBean)
			return this.toString().compareTo(
					((ResourceBean)o).toString() );
		return 0;
	}
}
