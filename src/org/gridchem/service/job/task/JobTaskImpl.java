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

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.gridchem.service.beans.JobBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.JobSubmissionException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.util.SSOUtils;


public abstract class JobTaskImpl {

    protected static final String GAUSSIAN = "gauss_launch2.cgi";
    protected static final String GAMESS = "gamess_launch.cgi";
    protected static final String NWCHEM = "nwchem_launch.cgi";
    protected static final String MOLPRO = "molpro_launch.cgi";
    protected static final String QMCPACK = "qmcpack_launch.cgi";
    protected static final String AMBER = "amber_launch.cgi";
    protected static final String ADF = "adf_launch.cgi";
    protected static final String WIEN2K = "wien2k_launch.cgi";
    protected static final String KILL = "killjob1.cgi";
    protected static final String PREDICT ="jbeststartend.cgi";
    protected static final String DATA = "getdata.cgi";
    protected static final String CHECK_STATUS = "bhist-cgi.pl";
    
    protected static final int SLEEP_TIME = 1000;
    
    protected Job job;
    
    protected GMSSession session;
    
    boolean notifyUser = false;
    
    static {
        // Set the truststore system variable to let jvm know to use the cg keystore
        // for all ssl communication.
    	try {
			SSOUtils.enableSSL();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public JobTaskImpl(GMSSession session, JobBean jobBean) {
    	this.session = session;
    	
    	if (jobBean.getId() == null) {
            try {
				job = new Job(session, jobBean);
			} catch (Exception e) {
				throw new JobException(e);
			}
        } else {
            job = JobDao.getById(jobBean.getId());
        }
        
    	if (job == null) {
    		throw new JobException("Invalid job id");
    	}
    	
    	if (!job.getUser().getId().equals(session.getUserId())) 
        	throw new PermissionException("User does not have permission to kill job");
        
        this.notifyUser = false;
        
    }
    
    public JobTaskImpl(GMSSession session, Long jobID) {
    	this.session = session;
    	job = JobDao.getById(jobID);
    	if (job == null) {
    		throw new JobException("Invalid job id");
    	}
    	if (!job.getUser().getId().equals(session.getUserId())) {
    		throw new PermissionException("Permission denied.");
    	}
        this.notifyUser = false;
    }
    
    public JobTaskImpl(GMSSession session, Job job) {
    	this.session = session;
    	this.job = job;
    	
    	if (!job.getUser().getId().equals(session.getUserId())) 
        	throw new PermissionException("Permission denied.");
        
    }
    
    public JobTaskImpl(GMSSession session, JobBean jobBean, boolean notifyUser) {
    	this.session = session;
    	if (jobBean.getId() == null) {
        	try {
				job = new Job(session, jobBean);
			} catch (Exception e) {
				throw new JobException(e);
			}
        } else {
            job = JobDao.getById(jobBean.getId());
        }
        
    	if (job == null) {
    		throw new JobException("Invalid job id");
    	}
    	
    	if (!job.getUser().getId().equals(session.getUserId())) 
        	throw new PermissionException("Permission denied.");
    	
        this.notifyUser = notifyUser;
    }
    
    protected void resolveJobRecord() throws Exception {
//        JobBean example = new JobBean();
//        
//        example.setName(job.getName());
//        
//        example.setLocalJobID(job.getLocalJobID());
//        
//        example.setApplication(job.getSoftwareResource().getName());
//        
//        example.setSubmitMachine(job.getComputeResource().getName());
//        
//        System.out.println("Resolving job[" + job.getJobID() + "]: " + job.getName() + ", " + job.getLocalJobID() + ", " + job.getComputeResource().getName() + ", " + job.getSoftwareResource().getName());
        
        Job match = JobDao.getById(job.getId());
//        Collection<Job> matches = new JobDAO().findMatching(example,job.getUser().getId());
        
        if (match == null) {// || matches.size() == 0) {
            throw new JobSubmissionException("Error resolving job after submission. " + 
                    "Not matching records found.");
        } //else if (matches.size() > 1) {
//            throw new JobSubmissionException("Error resolving job after submission. " + 
//            "Multiple matching records found.");
//        }
//        
//        Job match = matches.iterator().next();
        
        job.setId(match.getId());
        
        job.setCreated(match.getCreated());
        
        job.setLastUpdated(match.getLastUpdated());
        
        job.setUsedCpus(match.getRequestedCpus());
        
        job.setUsedMemory(match.getRequestedMemory());
        
        job.setUsedCpuTime(match.getUsedCpuTime());
        
        job.setUsedWallTime(match.getUsedWallTime());
        
        job.setStatus(match.getStatus());
        
//        System.out.println("New job id is " + job.getJobID() );
    }
    
//    /**
//     * This method is called when a job submission fails due to resources being incapable of 
//     * post-exectution file staging.  We disable the resource from being able to submit any
//     * more jobs and send an email to the wiki telling it that submission to this resource is
//     * disabled.
//     */
//    protected void disableResource(String message) {
//        
//        // get all the PR's for the current resource and allocation name
//        AllocationBean allocation = new AllocationBean();
//        allocation.setName(job.getAllocationName());
//        pr.setResource(job.getComputeResource());
//        
//        ProjectResourceDAO prDAO = new ProjectResourceDAO();
//        
//        Collection<ProjectResource> projectResources = prDAO.findByExample(pr);  
//        
//        for(ProjectResource tempPR: projectResources) {
//            tempPR.setEnabled(false);
//        }
//        
//        prDAO.makePersistent(projectResources);
//        
//        // send an email to the wiki notifying the public of the situation
//        User admin = new UserDAO().getUser("ccguser", false);
//        
//        String subject = "postit Resource " + job.getComputeResource().getName() + " is down.";
//        
//        try {
//        
//            EmailMessage.send(admin,subject,message);
//            
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//    }
}
