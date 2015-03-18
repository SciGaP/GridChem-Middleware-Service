/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jun 30, 2006
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
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.JobKillException;
import org.gridchem.service.exceptions.JobSchedulingException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;


/**
 * Kill the given job by either removing it from the queue or killing a
 * running process.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class KillTask extends JobTaskImpl implements JobTask {
    public static Logger log = Logger.getLogger(KillTask.class.getName());
    
    String cgiOutput = "";
    
    /**
     * @param job
     * @param user
     */
    public KillTask(GMSSession session, Long jobId) {
        super(session, jobId);
    }

   /**
     * Stop the job by either removing it from the queue or sending a kill
     * command to running processes.
     * 
     */
    public Job execute() throws JobException{
        URL cgiURL;
        
        try {
            
            validate();
            
            if (Settings.DEBUG) 
                log.info("Attempting to kill job [" + job.getId() + 
                        "]" + ": " + job.getName());
            
            cgiURL = new URL(Settings.CCG_CGI_SERVER + "killjob1.cgi");
            
            tryKillCGI(cgiURL);
            
            HibernateUtil.getSession().flush();
            
//            resolveJobRecord();
            
            return job;
            
        } catch (JobKillException e) {
            throw e;
        } catch (Exception e) {
            JobKillException ke = new JobKillException("Job kill failed.", e, cgiOutput);
            throw ke;
        }
    }
    
    public void validate() throws JobException {
        if (job == null) 
            throw new JobKillException("No job specified for submission.");
        
        if (job.getLocalId() == null || job.getLocalId().equals("")) {
            throw new JobKillException("Cannot kill given job. No local job id is associated with this job.");
        }
        
        if (job.getStatus().equals(JobStatusType.FAILED) || 
                job.getStatus().equals(JobStatusType.FINISHED) ||
                job.getStatus().equals(JobStatusType.REMOVED) ||
                job.getStatus().equals(JobStatusType.STOPPED) ||
                job.getStatus().equals(JobStatusType.RUNTIME_ERROR) ||
                job.getStatus().equals(JobStatusType.SUBMISSION_ERROR) ||
                job.getStatus().equals(JobStatusType.UNKNOWN)) {
            
            throw new JobKillException("Job is not running.");
            
        }
    }
    
    /**
     * Call the CGI script to stop the given job.
     * 
     * @param cgiURL
     */
    private void tryKillCGI(URL cgiURL) {
        String jobID = job.getLocalId();
        String mach = job.getSystem().getName();
        String line = "";
        
        // Send the above info to the CGI scripts to kill the job
        try
        {
            String line2;
            URLConnection connex = cgiURL.openConnection();
            connex.setDoOutput(true);
            PrintWriter outStream = new PrintWriter(connex.getOutputStream());
            String JoBID = URLEncoder.encode(jobID,"UTF-8");
            String sys = URLEncoder.encode(mach,"UTF-8");
            String userName;
            String isGridChem;

            if (session.getType().equals(AccessType.COMMUNITY)) {
                userName = "ccguser";
                isGridChem = "true";
            } else {
                // if an external user, get the user's local username from the db
                String localName = DaoFactory.getResourceDao(session).findLocalUsernameForComputeResource(job.getSystem().getName());
                
                // if none is found, throw an exception
                if (!ServiceUtil.isValid(localName) || localName.equals("ccguser")) {
                    throw new JobSchedulingException("Could not resolve local username. Job submission failed.");
                } 
         
                userName = localName;
                isGridChem = "false";
            }
            
            userName = URLEncoder.encode(userName,"UTF-8");
            outStream.println("IsGridChem=" + URLEncoder.encode(isGridChem,"UTF-8"));
            if (Settings.VERBOSE) log.info("ManageWindow:IsGridChem=" + "true");
            
            outStream.println("JobID="+JoBID);
            if (Settings.VERBOSE) log.info("Kill Job:killJob JobID="+JoBID);
            outStream.println("Username="+userName);
            if (Settings.VERBOSE) log.info("Kill Job:killJob Username="+userName);
            outStream.println("GridChemUsername=" + job.getUser().getUsername());
            if (Settings.VERBOSE) log.info("Kill Job:KillJob GridChemUsername=" + job.getUser().getUsername());
            outStream.println("Sysnm="+sys);
            if (Settings.VERBOSE) log.info("Kill Job:killJob Sysnm="+sys);
            
            outStream.close();

            BufferedReader inStream = new BufferedReader(new 
                    InputStreamReader(connex.getInputStream()));
            
            while ((line2 = inStream.readLine()) != null) {
                int m = line2.length();
                if (m > 0) {
                    line = line2;
                    if (Settings.VERBOSE) log.info(line);
                    cgiOutput += line;
                    if (line.startsWith("ERROR: jobid seems to NOT EXIST")) {
                        throw new JobException(line);
                    } else if (line.startsWith("llcancel: Usage:")) {
                        throw new JobException(line);
                    } else if (line.startsWith("kill_job__no_job_id_given_err")) {
                        throw new JobException("Job " + job.getLocalId() + " not found for the given job. Kill job failed.");
                    }
                }
            }
            
            job.setStatus(JobStatusType.STOPPED);
            
            JobDao.persist(job);
            
        } catch (JobKillException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobKillException("Job kill failed.",e,cgiOutput);
        } 
    }

}
