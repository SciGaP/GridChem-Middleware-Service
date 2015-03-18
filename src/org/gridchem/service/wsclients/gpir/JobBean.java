/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jan 24, 2007
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

package org.gridchem.service.wsclients.gpir;

import java.util.Calendar;

import org.gridchem.service.model.enumeration.JobStatusType;

/**
 *
 * @author Rion Dooley
 */
public class JobBean implements GPIRBean {
    
    protected String id;
    protected JobStatusType status;
    protected String owner;
    protected String queue;
    protected Calendar submissionTime;
    protected Calendar startTime;
    protected String name;
    protected String project;
    protected String executable;
    protected String priority;
    protected String runningOn;
    protected Calendar cpuTimeLimit;
    protected Calendar cpuTimeDuration;
    protected Load memoryLoad;
    protected Load processorLoad;
    protected String hostname;
    
    class Load {
        private int limit;
        private int peak;
        private int current;
        private int avg;
        
        public Load() {}
        
        public Load(int limit, int peak, int current, int avg) {
            this.limit = limit;
            this.peak = peak;
            this.current = current;
            this.avg = avg;
        }

        public int getAvg() {
            return avg;
        }

        public void setAvg(int avg) {
            this.avg = avg;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPeak() {
            return peak;
        }

        public void setPeak(int peak) {
            this.peak = peak;
        }
        
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[localJobId: " + id + "],");
        sb.append("[name: " + name + "],");
        sb.append("[status: " + status + "],");
        //sb.append("[hostname: " + hostname + "],");
        return sb.toString();
    }

    /**
     * @return the cpuTimeDuration
     */
    public Calendar getCpuTimeDuration() {
        return cpuTimeDuration;
    }

    /**
     * @param cpuTimeDuration the cpuTimeDuration to set
     */
    public void setCpuTimeDuration(Calendar cpuTimeDuration) {
        this.cpuTimeDuration = cpuTimeDuration;
    }

    /**
     * @return the cpuTimeLimit
     */
    public Calendar getCpuTimeLimit() {
        return cpuTimeLimit;
    }

    /**
     * @param cpuTimeLimit the cpuTimeLimit to set
     */
    public void setCpuTimeLimit(Calendar cpuTimeLimit) {
        this.cpuTimeLimit = cpuTimeLimit;
    }

    /**
     * @return the executable
     */
    public String getExecutable() {
        return executable;
    }

    /**
     * @param executable the executable to set
     */
    public void setExecutable(String executable) {
        this.executable = executable;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the memoryLoad
     */
    public Load getMemoryLoad() {
        return memoryLoad;
    }

    /**
     * @param memoryLoad the memoryLoad to set
     */
    public void setMemoryLoad(Load memoryLoad) {
        this.memoryLoad = memoryLoad;
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
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the processorLoad
     */
    public Load getProcessorLoad() {
        return processorLoad;
    }

    /**
     * @param processorLoad the processorLoad to set
     */
    public void setProcessorLoad(Load processorLoad) {
        this.processorLoad = processorLoad;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * @param queue the queue to set
     */
    public void setQueue(String queue) {
        this.queue = queue;
    }

    /**
     * @return the runningOn
     */
    public String getRunningOn() {
        return runningOn;
    }

    /**
     * @param runningOn the runningOn to set
     */
    public void setRunningOn(String runningOn) {
        this.runningOn = runningOn;
    }

    /**
     * @return the startTime
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
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
     * @return the submissionTime
     */
    public Calendar getSubmissionTime() {
        return submissionTime;
    }

    /**
     * @param submissionTime the submissionTime to set
     */
    public void setSubmissionTime(Calendar submissionTime) {
        this.submissionTime = submissionTime;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    
}
