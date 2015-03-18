/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jan 30, 2007
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

package org.gridchem.service.job.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.job.JobManager;
import org.gridchem.service.job.RusParser;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.util.Settings;

/**
 * Query a remote resource for the status of a job.  This is done via 
 * a call to a cgi script. 
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class CheckStatusTask extends JobTaskImpl implements JobTask {
    public static Logger log = Logger.getLogger(CheckStatusTask.class.getName());
    
    protected Properties props;
    
    /**
     * @param jobDTO
     */
    public CheckStatusTask(GMSSession session, JobBean jobBean) {
        super(session, jobBean);
    }

    /**
     * @param jobID
     */
    public CheckStatusTask(GMSSession session, Long jobID) {
        super(session,jobID);
    }

    /**
     * @param job
     * @param user
     */
    public CheckStatusTask(GMSSession session, Job job) {
        super(session, job);
    }

    /**
     * @param jobDTO
     * @param user
     * @param notifyUser
     */
    public CheckStatusTask(GMSSession session, JobBean jobDTO, boolean notifyUser) {
        super(session, jobDTO, notifyUser);
        
    }

    /* (non-Javadoc)
     * @see org.gridchem.service.gms.model.job.task.JobTask#execute()
     */
    public Job execute() throws JobException {
        URL cgiURL;
        
        // does nothing right now
        
//        try {
//            queryGRMS();
//            checkProxy();
//        } catch (RemoteException e) {
//            // TODO Auto-generated catch block
//            log.error(e.getMessage());
//        } catch (Exception e) {}
        
        
//         if job is not in an active state, query the history logs.
        if (JobManager.isRunning(job.getId())) {
            
            try {
                
                cgiURL = new URL(Settings.CCG_CGI_SERVER + CHECK_STATUS);
                
                tryStatusCGI(cgiURL);
                
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                log.error(e);
            }
            
        } else {
            
            // the providers implement reliable resource and job monitoring
//            queryGPIR();
//            queryGRMS(job);
        } 
        
        return job;
    
    }
    
//    private void queryGRMS() throws RemoteException {
//        GrmsJobIdentifier id = new GrmsJobIdentifier();
//        
////        System.out.println("Testing status of grmsjob " + job.getGrmsJobID());
//        
//        id.setValue(job.getGrmsJobID());
//      
//        try {
//            GrmsJobInformation info = grms.getJobInformation(id);
//            
//            // if the job setup task failed
//            GrmsTaskIdentifier taskID = JobParser.getTaskIdentifier(job);
//            
//            if (info.getErrorDescription() == null || 
//                    info.getErrorDescription().equals("") || 
//                    info.getErrorDescription().indexOf("presub_") > -1) {
//                taskID.setValue("presub_" + taskID.getValue());
//            } 
//            
//            GrmsTaskInformation taskInfo = grms.getTaskInformation(id, taskID);
//            
//            JobStatus status = JobParser.resolveStatus(info.getStatus());
//            System.out.println("Status from GRMS is " + info.getStatus().getValue());
//            log.debug("Status from GRMS is " + info.getStatus().getValue());
//            job.setStatus(status);
//            
//            // if job just started running, then update the start time
//            if (status.equals(JobStatus.RUNNING)) {
//            
//                Calendar cal = taskInfo.getHistory()[taskInfo.getHistory().length-1].getLocalStartTime();
//                
//                if (cal != null) {
//                    job.setStartTime(cal.getTime());
//                } 
//                
//              // if it just stopped, then add the stop time.
//            } else if (job.getStatus().equals(JobStatus.FINISHED)) {
//                
//                Calendar cal = taskInfo.getFinishTime();
//                
//                if (cal != null) {
//                    job.setStopTime(cal.getTime());
//                } 
//                
//            } else if (job.getStatus().equals(JobStatus.FAILED) ||
//                    job.getStatus().equals(JobStatus.REMOVED) || 
//                    job.getStatus().equals(JobStatus.STOPPED) ||
//                    job.getStatus().equals(JobStatus.RUNTIME_ERROR) ||
//                    job.getStatus().equals(JobStatus.SUBMISSION_ERROR) ||
//                    job.getStatus().equals(JobStatus.UNKNOWN)) {
//                
//                Calendar cal = taskInfo.getFinishTime();
//                
//                if (cal != null) {
//                    job.setStopTime(cal.getTime());
//                } 
//                
//            }
//            
//            // update the job status in the db
//            Session s = HibernateUtil.getSession();
//            Transaction tx = s.getTransaction();
//            tx.begin();
//            s.saveOrUpdate(job);
//            tx.commit();
//            
//            job.setErrorDescription(taskInfo.getErrorDescription());
//            
//        } catch (Exception e) {
//            log.error(e);
//            e.printStackTrace();
//        }
//    }
    
//    private void checkProxy() {
//        
//        if (!job.getStatus().equals(JobStatusType.FAILED) ||
//                !job.getStatus().equals(JobStatusType.REMOVED) || 
//                !job.getStatus().equals(JobStatusType.STOPPED) ||
//                !job.getStatus().equals(JobStatusType.RUNTIME_ERROR) ||
//                !job.getStatus().equals(JobStatusType.SUBMISSION_ERROR) ||
//                !job.getStatus().equals(JobStatusType.UNKNOWN) ||
//                !job.getStatus().equals(JobStatusType.NOT_IN_QUEUE)) {
//            GrmsJobIdentifier id = new GrmsJobIdentifier();
//            
//            System.out.println("Refreshing job proxy " + job.getGrmsJobID());
//          
//            id.setValue(job.getGrmsJobID());
//        
//            try {
//                Calendar minTime = Calendar.getInstance();
//                minTime.clear();
//                minTime.add(Calendar.MINUTE,30);
//              
//                GrmsProxyInformation[] proxies = grms.getProxiesInformation(id);
//              
//                for(int i=0;i<proxies.length;i++) {
//                  
//                    Calendar timeLeft = proxies[i].getTimeLeft().getAsCalendar();
//                  
//                    if (timeLeft.before(minTime)) {
//                      
//                        grms.refreshJobProxy(id);
//                      
//                        break;
//                  
//                    }
//                }
//              
//            } catch (Exception e) {
//                log.debug("Could not refresh proxy for job " + id.getValue(),e);
//            }
//        }
//    }
    
    private void tryStatusCGI(URL cgiURL) {
        String jobId = job.getLocalId();
        String hostname = job.getSystem().getHostname();
        String jobStatusXml = "";
        
        // Send the above info to the CGI script to call bhist on the job
        try {
            
            String line;
            URLConnection connex = cgiURL.openConnection();
            connex.setDoOutput(true);
            PrintWriter outStream = new PrintWriter(connex.getOutputStream());
            
//            outStream.println("IsGridChem=" + URLEncoder.encode("true","UTF-8"));
//            if (Settings.VERBOSE) log.info("ManageWindow:IsGridChem=" + "true");
            
            String encodedHostname = URLEncoder.encode(hostname,"UTF-8");
            outStream.println("hostname=" + encodedHostname);
            if (Settings.VERBOSE) log.info("Check Job Status: Value=" + encodedHostname);
            
//            String encodedUsername = URLEncoder.encode("ccguser","UTF-8");
//            outStream.println("Username=" + encodedUsername);
//            if (Settings.VERBOSE) log.info("Check Job Status:Username=" + encodedUsername);
//            
//            outStream.println("GridChemUsername=" + job.getUser().getUserName());
//            if (Settings.VERBOSE) log.info("Check Job Status:GridChemUsername=" + job.getUser().getUserName());
            
            String encodedJobId = URLEncoder.encode(jobId,"UTF-8");
            outStream.println("localJobID=" + encodedJobId);
            if (Settings.VERBOSE) log.info("Check Job Status: Hostname=" + encodedJobId);
            
            outStream.close();

            BufferedReader inStream = new BufferedReader(new 
                    InputStreamReader(connex.getInputStream()));
            
            while ((line = inStream.readLine()) != null) {
                if (line.length() > 0) {
                    jobStatusXml += line;
                    
                    if (Settings.VERBOSE) {
                        log.info(line);
                        System.out.println(line);
                    }
                    
                    if (line.indexOf("Status of localJobID=" + jobId + " is UNKNOWN") > -1) {
                        throw new JobException("Updated status of job is UNKNOWN");
                    }
                }
            }
            
            JobStatusType newStatus = RusParser.getStatus(jobStatusXml);
            
            if (!newStatus.equals(JobStatusType.UNKNOWN)) {
//                Session s = HibernateUtil.getSession();
//                Transaction tx = s.beginTransaction();
                
                // update the jobs status with what we found from the query
                job.setStatus(newStatus);
                JobDao.persist(job);
//                s.saveOrUpdate(job);
//                tx.commit();
                
            } else {
                throw new JobException("Updated status of job is UNKNOWN");
            }
                
            
//                    if (line.startsWith("ERROR: jobid seems to NOT EXIST")) {
//                        throw new JobException(line);
//                    } else if (line.startsWith("llcancel: Usage:")) {
//                        throw new JobException(line);
//                    } else if (line.startsWith("kill_job__no_job_id_given_err")) {
//                        throw new JobException("Job jobid found for the given job. Kill job failed.");
//                    } else if (line.indexOf("Job Status") > -1) {
//                        
//                            String[] statusString = line.split(":");
//                        
//                            status = JobParser.resolveStatusCode(statusString[1].trim());
//                        
//                        } else if (line.indexOf("Started") > -1) {
//                            
//                            String startedString = line.substring(line.indexOf(":") + 1);
//                            
//                            job.setStartTime(new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy")
//                                    .parse(startedString.trim()));
//                            
//                        } else if (line.indexOf("Cpu time") > -1) {
//                            
//                            String[] cpuTimeString = line.split(":");
//                            
//                            long hours = new Long(cpuTimeString[1].trim()).longValue() * 60 * 60 * 1000;
//                            long minutes =new Long(cpuTimeString[2].trim()).longValue() * 60 * 1000;
//                            long seconds = new Long(cpuTimeString[3].substring(0,2).trim()).longValue() * 1000;
//                            
//                            job.setUsedCpuTime(hours + minutes + seconds);
//                            
//                        } else if (line.indexOf("Run Time") > -1) {
//                            
//                            String[] runTimeString = line.split(":");
//                            
//                            long hours = new Long(runTimeString[1].trim()).longValue() * 60 * 60 * 1000;
//                            long minutes =new Long(runTimeString[2].trim()).longValue() * 60 * 1000;
//                            long seconds = new Long(runTimeString[3].substring(0,2).trim()).longValue() * 1000;
//                            
//                            job.setUsedWallTime(hours + minutes + seconds);
//                            
//                        } else if (line.indexOf("Peak Task Memory") > -1) {
//                            
//                            String[] statusString = line.split(":");
//                            
//                            long memory = 0;
//                            
//                            // adjust for different units of measurement
//                            if (statusString[1].indexOf("MB") > -1) {
//                                memory = new Long(statusString[1].substring(0,
//                                        statusString[1].indexOf("MB")).trim()).longValue();
//                            } else {
//                                memory = (long)(new Double(statusString[1].substring(0,
//                                        statusString[1].indexOf("GB")).trim()).doubleValue() * 1000);
//                            }
//                            
//                            job.setUsedMemory(memory);
//                                
//                        } else if (line.indexOf("Current cpu usage") > -1) {
//                            
//                            String[] usedCPUString = line.split(":");
//                            
//                            job.setUsedCpus(new Long(usedCPUString[1].trim()).longValue());
//                        
//                        }
//                }
//            }
            
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobException("Job status check failed.",e);
        } 
    }
    
//    private void queryGPIR() {
//        String jobXML = retrieveXML("ccg_jobs.xml");
//        JobXmlHandler handler = null;
//        JobCollection collection = null;
//        List<JobBean> jobBeans = null;
//        
//        try
//        {
//        if(jobXML != null) {
//            handler = new JobXmlHandler(jobXML);
//            collection = handler.getBeans();
//            
//            jobBeans = collection.get(GpirProperties.JOBS);
//            if (Settings.VERBOSE) 
//                log.debug("GPIR returned " + jobBeans.size() + " jobs");
//
//            // open the properties file containing the list of unresolved jobs.
//            // this file should be ok for rewriting because we are generating
//            // a complete list every time this routine runs.
//            File jobFile = new File("lostjobs.dat");
//            jobFile.createNewFile();
//            
//            props = new Properties();
//            props.load(new FileInputStream("lostjobs.dat"));
//            
//            syncJob(job,jobBeans);
//            
//        }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Exceptions");
//        }
//        
//        
//    }
    
//    /**
//     * Get the XML data from the info service
//     * @param serviceHost
//     */
//    public String retrieveXML(String xmlFilename) 
//    throws SynchronizationException {
//        String resourceXML = "";
//        try {
//            resourceXML = Query.getQuery("vo",
//                    "jobs",
//                    Settings.VO,
//                    new File(xmlFilename));
//            if (!resourceXML.equals(""))
//                log.debug("Received XML from GPIR!!");
//        } catch (Exception e) {
//            log.debug("No data retrieved from GPIR");
//            throw new SynchronizationException(e);
//        }
//        return resourceXML;
//    }
    
    
//    /**
//     * Routine to resolve attributes of compute resource bean (i.e. gpir compute 
//     * resource) into a ccg compute resource object for persisting.
//     * 
//     * @param crb
//     */
//    private void syncJob(org.gridchem.service.gms.model.job.Job job, List<JobBean> jBeanList) {
//        
//        Session s = HibernateUtil.getSession();
//        Transaction tx = s.beginTransaction();
//        boolean updated = false;
//        try {
//            // System.out.println("Searching for resource matching " + jb.getHostname());
//            // find compute resource for job so we can look up the job
//            // record in the db
//            for(JobBean jb: jBeanList) {
//                if (job.getComputeResource().getHostname().equals(jb.getHostname())) {
//                    SchedulerType sched = job.getComputeResource().getScheduler();
//                    
//                    // resolve the local job bean id into a number rather than the 
//                    // for it's in from pbs, ll, etc
//                    if (sched.equals(SchedulerType.PBS)) {
//                                
//                        if (jb.getId().indexOf(".") > 0) {
//                            jb.setId(jb.getId().substring(0,jb.getId().indexOf(".")));
//                        } else {
//                            // jobid is already just a number
//                        }
//                    } else if (sched.equals(SchedulerType.LL) ||
//                            sched.equals(SchedulerType.LSF)) {
//                        if (jb.getId().indexOf(".") > -1) {
//                            // remove the node name from the string
//                            jb.setId(jb.getId().substring(jb.getId().indexOf(".")+1));
//                            
//                            // remove the trailing number if it exists
//                            if (jb.getId().indexOf(".") > -1) {
//                                jb.setId(jb.getId().substring(0,jb.getId().indexOf(".")));
//                            } else {
//                                // jobid is already just a number
//                            }
//                            
//                        } else {
//                            // leave it as is.
//                        }
//                    }
//                }
//                
//                try {
//                    // if the job is a match with this bean, we simply update the status
//                    if (jb.getId().equals(job.getLocalJobID()) && 
//                            !(job.getStatus().equals(JobStatus.FAILED) ||
//                                job.getStatus().equals(JobStatus.FINISHED) ||
//                                job.getStatus().equals(JobStatus.REMOVED) ||
//                                job.getStatus().equals(JobStatus.RUNTIME_ERROR) ||
//                                job.getStatus().equals(JobStatus.STOPPED) ||
//                                job.getStatus().equals(JobStatus.SUBMISSION_ERROR))) {
//                        
//                        // here we assume that the status returned from GPIR is correct.
//                        job.setStatus(jb.getStatus());
//                        
//                        // update record in the db
//                        updated = true;
////                      s.saveOrUpdate(job);
////                      tx.commit();
//                        
////                      System.out.println("Updating status of job[" + 
////                            job.getJobID() + "] to " + jb.getStatus() + " " + job.getCreated());
//                    }
//                } catch (Exception e) {
////                    System.out.println("Could not update status of job[" + 
////                            job.getJobID() + "] to " + jb.getStatus() + " " + job.getCreated());
//                    log.error("Could not update status of job[" + 
//                            job.getJobID() + "] to " + jb.getStatus(),e);
//                }
//            }
//            
//            // if we've gone through all the beans and the job isn't found in the list of
//            // job bean, then we mark it as "NOT_IN_QUEUE", write the hostname and local id
//            // to disk, and it will be resolved by the hourly cron job.
//            if (!updated && !(job.getStatus().equals(JobStatus.FAILED) ||
//                    job.getStatus().equals(JobStatus.FINISHED) ||
//                    job.getStatus().equals(JobStatus.REMOVED) ||
//                    job.getStatus().equals(JobStatus.RUNTIME_ERROR) ||
//                    job.getStatus().equals(JobStatus.STOPPED) ||
//                    job.getStatus().equals(JobStatus.SUBMISSION_ERROR))) {
//                try {
//                    
//                    // add the local jobid to a list of unresolved jobs for the 
//                    // appropriate resource and store it in a java.util.Properties
//                    // object.  the list is then stored to disk.
//                    String jobIdList = props.getProperty(job.getComputeResource().getHostname());
//                    
//                    if (jobIdList != null) {
//                        props.put(job.getComputeResource().getHostname(), 
//                                job.getLocalJobID() + "," + jobIdList);
//                    } else {
//                        props.put(job.getComputeResource().getHostname(), 
//                                job.getLocalJobID());
//                    } 
//                    
//                    if (!job.getStatus().equals(JobStatus.NOT_IN_QUEUE)) {
//                        job.setStatus(JobStatus.NOT_IN_QUEUE);
//                        
//                        // update record in the db
//                        //s.saveOrUpdate(job);
//                        //tx.commit();
////                        System.out.println("Updating status of job[" + 
////                                job.getJobID() + "] to " + JobStatus.NOT_IN_QUEUE + " " + job.getCreated());
//                    }
//                } catch (Exception e) {
//                    System.out.println("Could not update status of job[" + 
//                            job.getJobID() + "] to " + JobStatus.NOT_IN_QUEUE + " " + job.getCreated());
//                    log.error("Could not update status of job[" + 
//                            job.getJobID() + "] to " + JobStatus.NOT_IN_QUEUE,e);
//                }
//                
//            }
//            
//        } catch (ResourceException e) {
//            e.printStackTrace();
//            throw e;
//        } catch (Exception exception) {
//
//            throw new SynchronizationException(
//                "Could not synchronize job " + job.getComputeResource().getName() + 
//                " on resource " + job.getComputeResource().getHostname() + 
//                " with the GMS database.", 
//                exception);
//        } 
//    }
    
    public void validate() throws Exception {
        // TODO Auto-generated method stub
        
    }


}
