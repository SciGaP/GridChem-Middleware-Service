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


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.wsclients.gpir.JobBean.Load;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 *
 * @author ericrobe
 */
public class JobParser implements GenericParser {
    Element elJob;
    Namespace nsJob;
    JobBean bean;
    
    public JobParser() {
        
    }
    
    public JobParser(Element element,String hostname) {
        parse(element);
        bean.setHostname(hostname);
    }
    
    public void parse(Element element) {
        elJob = element;
        bean = new JobBean();
        handleId();
        handleStatus();
        handleOwner();
        handleSubmissionTime();
        handleStartTime();
        handleName();
        handleProject();
        handleQueueInfo();
        handleExecutable();
        handlePriority();
        handleRunningOn();
        handleCPUTime();
        handleMemory();
        handleProcessors();
    }
    public JobBean getBean() {
        return bean;
    }
    
    protected void handleId() {
        String jid = elJob.getAttribute("id").getValue();
        
        if (jid != null) {
            bean.setId(elJob.getAttribute("id").getValue());
//            System.out.println("Jobid for the given job is " + jid);
        } else {
//            System.out.println("No jobid for the given job");
            bean.setId("");
        }
    }
    
    protected void handleStatus() {
        Element elStatus = elJob.getChild("Status", nsJob);
        if (elStatus != null) {
            bean.setStatus(resolveStatusCode(elStatus.getValue()));
        }
    }
    
    protected void handleOwner() {
        Element elOwner = elJob.getChild("Owner", nsJob);
        if (elOwner != null) {
            bean.setOwner(elOwner.getValue());
        }
    }
    
    protected void handleSubmissionTime() {
        Element elSubmissionTime = 
            elJob.getChild("SubmissionTime", nsJob);
        
        if (elSubmissionTime != null) {
            // need to format this value into a Calendar object
            bean.setSubmissionTime(resolveDateString(elSubmissionTime.getValue()));
        }
    }
    
    protected void handleStartTime() {
        
        if (bean.getStatus().equals("Deferred")) {
            bean.setStartTime(null);
        } else {
            Element elStartTime = elJob.getChild("StartTime", nsJob);
            if (elStartTime != null) {
                // need to format this value into a Calendar object
                bean.setStartTime(resolveDateString(elStartTime.getValue()));
            }
        }
    }
    
    protected void handleName() {
        Element elName = elJob.getChild("Name", nsJob);
        if (elName != null) {
            bean.setName(elName.getValue());
        }
    }
    
    protected void handleProject() {
        Element elProject = elJob.getChild("Project", nsJob);
        if (elProject != null) {
            bean.setProject(elProject.getValue());
        }
    }
    
    protected void handleExecutable() {
        Element elExecutable = elJob.getChild("Executable", nsJob);
        if (elExecutable != null) {
            bean.setExecutable(elExecutable.getValue());
        }
    }
    
    protected void handleQueueInfo() {
        Element elQueueInfo = elJob.getChild("QueueInfo", nsJob);
        if (elQueueInfo != null) {
            bean.setQueue(elQueueInfo.getValue());
        }
    }
    
    protected void handlePriority() {
        Element elPriority = elJob.getChild("Priority", nsJob);
        if (elPriority != null) {
            bean.setPriority(elPriority.getValue());
        }
    }
    
    protected void handleRunningOn() {
        Element elRunningOn = elJob.getChild("RunningOn", nsJob);
        if (elRunningOn != null) {
            bean.setRunningOn(elRunningOn.getValue());
        }
    }
    
    protected void handleCPUTime() {
        Element elCPUTime = elJob.getChild("CPUTime", nsJob);
        if (elCPUTime != null) {
            Namespace nsCPUTime = elCPUTime.getNamespace();
            bean.setCpuTimeLimit(parseCPUTime(elCPUTime.getChildTextTrim("Limit", nsCPUTime)));
            bean.setCpuTimeDuration(parseCPUTime(elCPUTime.getChildTextTrim("Duration", nsCPUTime)));
        }
    }
    
    protected void handleMemory() {
        Element elMemory = elJob.getChild("Memory", nsJob);
        if (elMemory != null) {
            Namespace nsMemory = elMemory.getNamespace();
            
            Load load = bean.new Load();
            
            load.setLimit(parseMemory(elMemory.getChildTextTrim("Limit", nsMemory)));
            load.setPeak(parseMemory(elMemory.getChildTextTrim("Peak", nsMemory)));
            load.setCurrent(parseMemory(elMemory.getChildTextTrim("Current", nsMemory)));
            load.setAvg(parseMemory(elMemory.getChildTextTrim("Average", nsMemory)));
            
            bean.setMemoryLoad(load);
        }
    }

    protected void handleProcessors() {
        Element elProcessors = elJob.getChild("Processors", nsJob);
        if (elProcessors != null) {
            Namespace nsProcessors = elProcessors.getNamespace();
        
            Load load = bean.new Load();
            
            load.setLimit(parseProcessors(elProcessors.getChildTextTrim("Limit", nsProcessors)));
            load.setPeak(parseProcessors(elProcessors.getChildTextTrim("Peak", nsProcessors)));
            load.setCurrent(parseProcessors(elProcessors.getChildTextTrim("Current", nsProcessors)));
            load.setAvg(parseProcessors(elProcessors.getChildTextTrim("Average", nsProcessors)));
            
            bean.setProcessorLoad(load);
        }
    }
    
    /**
     * Convert the various string date representations received from GPIR into
     * actual Calendar objects.
     * 
     * @param dateString
     * @return
     */
    private Calendar resolveDateString(String dateString) {
        Calendar cal = Calendar.getInstance();
        
        try {
            Date date = new SimpleDateFormat("MM/dd hh/mm").parse(dateString);
            cal.setTimeInMillis(date.getTime());
            return cal;
        } catch (Exception e) {}
        
        try {
            Date date = new SimpleDateFormat("EEE MMM d h:mm:ss").parse(dateString);
            cal.setTimeInMillis(date.getTime());
            return cal;
        } catch (Exception e) {}
        
        return null;
    }
    
    /**
     * Each scheduling system returns its own status codes.  Here we 
     * map the codes into an enumerated JobStatusType object.
     * 
     * @param code
     * @return
     */
    public static JobStatusType resolveStatusCode(String code) {
        // PBS states
        if (code.toUpperCase().equals("R")) {
            return JobStatusType.RUNNING;
        } else if (code.toUpperCase().equals("E")) {
            return JobStatusType.EXITING;
        } else if (code.toUpperCase().equals("H")) {
            return JobStatusType.HOLD;
        } else if (code.toUpperCase().equals("Q")) {
            return JobStatusType.SCHEDULED;
        } else if (code.toUpperCase().equals("W")) {
            return JobStatusType.WAITING;
        } else if (code.toUpperCase().equals("S")) {
            return JobStatusType.STOPPED;
        } else if (code.toUpperCase().equals("T")) {
            return JobStatusType.MIGRATING;
        
            // Moab states    
        } else if (code.toUpperCase().equals("IDLE")) {
            return JobStatusType.SCHEDULED;
        } else if (code.toUpperCase().equals("HOLD")) {
            return JobStatusType.HOLD;
        } else if (code.toUpperCase().equals("STAGED")) {
            return JobStatusType.SCHEDULED;
        } else if (code.toUpperCase().equals("STARTING")) {
            return JobStatusType.STARTING;
        } else if (code.toUpperCase().equals("RUNNING")) {
            return JobStatusType.RUNNING; 
        } else if (code.toUpperCase().equals("SUSPENDED")) {
            return JobStatusType.SUSPENDED;    
        } else if (code.toUpperCase().equals("CANCELLING")) {
            return JobStatusType.CANCELLING;    
        } else if (code.toUpperCase().equals("COMPLETED")) {
            return JobStatusType.FINISHED;    
        } else if (code.toUpperCase().equals("REMOVED")) {
            return JobStatusType.STOPPED;    
        } else if (code.toUpperCase().equals("VACATED")) {
            return JobStatusType.FAILED;    
        
        // LSF states
        } else if (code.toUpperCase().equals("C")) {
            return JobStatusType.FINISHED;    
        } else if (code.toUpperCase().equals("CA")) {
            return JobStatusType.STOPPED;    
        } else if (code.toUpperCase().equals("CK")) {
            return JobStatusType.CHECKPOINTING;    
        } else if (code.toUpperCase().equals("CP")) {
            return JobStatusType.FINISHED;    
        } else if (code.toUpperCase().equals("D")) {
            return JobStatusType.WAITING;    
        } else if (code.toUpperCase().equals("E")) {
            return JobStatusType.PREEMPTED;    
        } else if (code.toUpperCase().equals("EP")) {
            return JobStatusType.PREEMPTED;    
        } else if (code.toUpperCase().equals("H")) {
            return JobStatusType.HOLD;    
        } else if (code.toUpperCase().equals("HS")) {
            return JobStatusType.HOLD;    
        } else if (code.toUpperCase().equals("I")) {
            return JobStatusType.SCHEDULED;    
        } else if (code.toUpperCase().equals("MP")) {
            return JobStatusType.RESUMING;    
        } else if (code.toUpperCase().equals("NR")) {
            return JobStatusType.FAILED;    
        } else if (code.toUpperCase().equals("NQ")) {
            return JobStatusType.SUBMISSION_ERROR;    
        } else if (code.toUpperCase().equals("P")) {
            return JobStatusType.WAITING;    
        } else if (code.toUpperCase().equals("R")) {
            return JobStatusType.RUNNING;    
        } else if (code.toUpperCase().equals("RM")) {
            return JobStatusType.STOPPED;    
        } else if (code.toUpperCase().equals("RP")) {
            return JobStatusType.STOPPED;    
        } else if (code.toUpperCase().equals("S")) {
            return JobStatusType.HOLD;    
        } else if (code.toUpperCase().equals("ST")) {
            return JobStatusType.STARTING;    
        } else if (code.toUpperCase().equals("SX")) {
            return JobStatusType.SUBMISSION_ERROR;    
        } else if (code.toUpperCase().equals("TX")) {
            return JobStatusType.FINISHED;    
        } else if (code.toUpperCase().equals("V")) {
            return JobStatusType.FAILED;    
        } else if (code.toUpperCase().equals("VP")) {
            return JobStatusType.FAILED;    
        } else if (code.toUpperCase().equals("X")) {
            return JobStatusType.FAILED;    
        } else if (code.toUpperCase().equals("XP")) {
            return JobStatusType.FAILED;    
        
        // none of the above
        } else { 
            return JobStatusType.UNKNOWN;
        }
                
    }
    
    private Calendar parseCPUTime(String dateString) {
        
//        System.out.println("Parsing cpu time " + dateString);
        
        Calendar cal = null; 
        
        if (dateString != null) {
            cal = Calendar.getInstance();
            cal.clear();
            
            String[] time = dateString.split(":");
            
            cal.set(Calendar.HOUR_OF_DAY,new Integer(time[0]).intValue());
            
            if (time.length > 1) {
                cal.set(Calendar.MINUTE,new Integer(time[1]).intValue());
            }
        }
        
        return cal;
    }
    
    private int parseMemory (String memory) {
        if (memory == null) 
            return -1;
        
        if (memory.indexOf("-") > -1) {
            return -1;
        } else {
            return new Integer(memory).intValue();
        }
    }
    
    private int parseProcessors (String procs) {
        if (procs == null) 
            return -1;
        
        if (procs.indexOf("-") > -1) {
            return -1;
        } else {
            return new Integer(procs).intValue();
        }
    }
    
}
