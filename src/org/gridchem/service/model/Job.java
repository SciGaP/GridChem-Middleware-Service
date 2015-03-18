/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 23, 2005
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

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.beans.LogicalFileBean;
import org.gridchem.service.dao.BlackListDao;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.ProjectDao;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.SoftwareDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.job.prediction.Qbets;
import org.gridchem.service.job.prediction.QbetsPrediction;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;

/**
 * This is the GMS Job class.  A <tt>Job</tt> object is an internal
 * representation of an actual job submitted to a <tt>Hardware Resource</tt>.
 * The <tt>Job</tt> class has a many-to-1 associated with the <tt>User</tt>  
 * class, a many-to-1 associated with the <tt>Resource</tt> class, and a
 * 1-to-many relationship with the <tt>File</tt> class.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class Job {
    public static Logger log = Logger.getLogger(Job.class.getName());
    
    private Long id = null;
    private String name;
    private String localId;
    private String logicalId;
    private ComputeResource system;
    private Software software;
    private String module;
    private Queue queue;
    private User user;
    private String experimentName;
    private String workDir;
    private List<LogicalFile> inputFiles = new ArrayList<LogicalFile>();
//    private Set<LogicalFileBean> outputFiles;
    private StorageResource storage;
    private Long requestedCpus;
    private Long requestedMemory;
    private Calendar requestedCpuTime;
    private String errorDescription;
    private Date startTime;
    private Date stopTime;
    private Long usedCpus = new Long(0);
    private Long usedMemory = new Long(0);
    private Long usedCpuTime = new Long(0);
    private Long usedWallTime = new Long(0);
    private Date created = new Date();
    private Calendar estimatedResponseTime = requestedCpuTime;
    private Project project;
    private String allocationName;
    private Double cost;
    private JobStatusType status;
    private Date lastUpdated = new Date();
    private String metaData;
    private String xmlDescription;
    private boolean hidden = false;
    private boolean deleted = false;
    private boolean checkpointable = false;
    private boolean resubmittable = false;
    private int maxResubmissions = 0;
    private Workflow workflow;
    
    /**
	 * No-arg constructor for JavaBean tools.
	 */
    public Job() {}
    
    public Job(GMSSession session, JobBean job) 
    throws JobException, RemoteException, ProviderException{
    	
    	ResourceDao resourceDao = new ResourceDao(session);
    	ProjectDao projectDao = new ProjectDao(session);
    	SoftwareDao softwareDao = new SoftwareDao(session);
    	
        this.id = job.getId();
        this.user = new UserDao(session)._get();
        this.startTime = job.getStartTime();
        this.stopTime = job.getStopTime();
        this.usedCpus = job.getUsedCpus();
        this.usedMemory = job.getUsedMemory();
        this.usedCpuTime = job.getUsedCpuTime();
        this.usedWallTime = job.getUsedWallTime();
//        this.estimatedResponseTime = job.getEstimatedResponseTime();
        this.project = projectDao._get();
        this.cost = job.getCost();
        this.status = job.getStatus();
        this.lastUpdated = new Date();
        this.metaData = ServiceUtil.xstream.toXML(job.getMetaData());
        this.created = job.getCreated();
        this.name = job.getName();
        this.experimentName = job.getExperimentName();
        this.requestedCpus = job.getRequestedCpus();
        this.requestedMemory = job.getRequestedMemory();
        this.requestedCpuTime = job.getRequestedCpuTime();
        
        // look up the software resource this is manditory
        if (job.getSoftwareName() == null) {
            throw new JobException("Application not specified. Could not create job.");
        }
       
        System.out.println("These are the values " + softwareDao.userHasAccess(job.getSoftwareName()) + BlackListDao.userHasAccessToSoftware(session.getUserId(), job.getSoftwareName())+ " \n"); 
        if (!softwareDao.userHasAccess(job.getSoftwareName()) ||
            !BlackListDao.userHasAccessToSoftware(session.getUserId(), job.getSoftwareName())) {
        	throw new PermissionException("User is blacklisted from using " + job.getSoftwareName());
        }
        
        
        this.software = softwareDao._get(job.getSoftwareName());
        this.module = job.getModuleName();
        
        // if compute resource is unspecified, call the scheduling routine
        // to determine the resource most likely to start the job in the next 5 hours.
        if (!ServiceUtil.isValid(job.getSystemName())) {
            Job sampleJob = new Job();
            sampleJob.setSoftware(this.software);
            sampleJob.setUser(this.user);
            sampleJob.setRequestedCpuTime(job.getRequestedCpuTime());
            sampleJob.setRequestedCpus(job.getRequestedCpus());
            
            QbetsPrediction prediction = null;
            try {
            	prediction = Qbets.findFirstAvailableResource(session, job);
            } catch (ServiceException e) {
            	throw new JobException("Failed to find an available resource.");
            }
            if (prediction == null) {
            	throw new JobException("No resource found on which the job will start within " +
            			Settings.DEFAULT_JOB_START_TIME + "hours.");
            }
            
//            ScheduleTask task = new ScheduleTask(job);
//            job = task.execute();
            this.system = resourceDao._getCompute(prediction.getMachine().getName());
            this.workDir = system.getScratchDirectory();
            
            for (Queue q: system.getQueues()) {
            	if (q.getName().equals(prediction.getQueue().getName())) {
            		this.queue = q;
            	}
            }
            
            if (this.queue == null) {
            	this.queue = this.system.getDefaultQueue();
            }
            
            //this.allocationName = projectDao.getDefaultAllocation(session.getProjectId(), system.getName());
            this.allocationName = job.getAllocationName();
            
        } else {
            
            // lookup machine
            system = resourceDao._getCompute(job.getSystemName());
            
            if (system == null) {
            	throw new PermissionException("User does not have access to " + job.getSystemName());
            }
            
            if (ServiceUtil.isValid(job.getWorkDir())) {
            	this.workDir = job.getWorkDir();
            } else {
            	this.workDir = system.getScratchDirectory();
            }
            
            // find the specified queue or use the first matching queue.
            if (!ServiceUtil.isValid(job.getQueueName())) {
                
                // select the first queue that can support the job description
                for(Queue q: system.getQueues()) {
                    if (q.getMaxCpus() >= job.getRequestedCpus() && 
                            q.getMaxCpuTime().getTimeInMillis() >= job.getRequestedCpuTime().getTimeInMillis()) {
                        this.queue = q;
                    }
                }
                // make sure a queue was found
                if (this.queue == null) {
                    throw new JobException("No queue found to support the given job description.");
                }
                
            } else {
                // find the queue matching the given name
                for(Queue q: system.getQueues()) {
                    if (q.getName().equals(job.getQueueName())) {
                        this.queue = q;
                    }
                }
                // if no queue found, get default
                if (this.queue == null) {
                    this.queue = system.getDefaultQueue();
//                    throw new JobException(jobDTO.getQueue() + " queue not found.");
                }
                
                
            }
            
            // load the project resource so we know the name of the 
            // local project to which this job will be charged
//            ProjectResource.Id prId = new ProjectResource.Id(
//                    project.getProjectId(),
//                    this.computeResource.getId(),
//                    jobDTO.getProjectName());
//      
        }
        
        //this.allocationName = projectDao.getDefaultAllocation(session.getProjectId(), system.getName());
        this.allocationName = job.getAllocationName();
        
        // use the storage resource specified or just use the default
        if (!ServiceUtil.isValid(job.getStorageResource())) {
            
            storage = resourceDao._getDefaultStorageResourceForComputeResource(system.getName());
            
//            for(UserProject up: (Set<UserProject>) user.getUserProjects()) {
//                if (up.getProject().getProjectName().equals(jobDTO.getProjectName())) {
//                    storageResource = up.getMss();
//                    break;
//                }
//            }
            if (storage == null) {
                throw new JobException("No default storage archive assigned to " + system.getName());
            }
            
        } else {
        	if (!DaoFactory.getResourceDao(session)
                    .userHasAccessToStorageResource(job.getStorageResource())) {
        		throw new PermissionException("User does not have access to " + job.getStorageResource());
        	}
            storage = resourceDao._getStorage(job.getStorageResource());
        }
        
        // Copy the logical input files for this job
        if (job.getInputFiles() == null || job.getInputFiles().size() == 0) {
        	throw new JobException("No input files specified.");
        }
        
        // TODO: how do we handle the file uploads?  Let them upload blindly and tie the 
        // logical file references to the job here...is there another way to do it? 
        for (LogicalFileBean bean: job.getInputFiles()) {
        	LogicalFile file = new LogicalFile(bean);
        	this.inputFiles.add(file);
        }
    }
    
    /**
     * @return Returns the user.
      */
    public User getUser() {
        return user;
    }
    /**
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getLogicalId() {
		return logicalId;
	}

	public void setLogicalId(String logicalId) {
		this.logicalId = logicalId;
	}

	public ComputeResource getSystem() {
		return system;
	}

	public void setSystem(ComputeResource system) {
		this.system = system;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	/**
	 * @param queue the queue to set
	 */
	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	/**
	 * @return the queue
	 */
	public Queue getQueue() {
		return queue;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
        if (ServiceUtil.isAlphaNumeric(experimentName)) {
            this.experimentName = experimentName;
        } else {
            throw new JobException("Invalid experiment name \"" + 
            		experimentName + "\".\nNames may contain only " +  
                    "alphanumeric\ncharacters [a-zA-Z_0-9]");
        }
    }

	public String getWorkDir() {
		return workDir;
	}

	public void setWorkDir(String workDir) {
		this.workDir = workDir;
	}

	public List<LogicalFile> getInputFiles() {
		return inputFiles;
	}

	public void addLogicalFile(LogicalFile logicalFile) {
//		LogicalFileDao.persist(logicalFile);
		inputFiles.add(logicalFile);
	}
	
	public void setInputFiles(List<LogicalFile> inputFiles) {
//		// delete previous files
//		for (LogicalFile logicalFile: getInputFiles()) {
//			
//			// delete cached files
//			File file = new File(logicalFile.getLocalPath());
//			file.delete();
//			
//			// delete database record of the file
//			LogicalFileDao.delete(logicalFile);
//		}
//		
//		// add new files
//		for (LogicalFile logicalFile: inputFiles) {
//			LogicalFileDao.persist(logicalFile);
//		}
		this.inputFiles = inputFiles;
		
	}

//	public Set<LogicalFileBean> getOutputFiles() {
//		return outputFiles;
//	}
//
//	public void setOutputFiles(Set<LogicalFileBean> outputFiles) {
//		this.outputFiles = outputFiles;
//	}

	public StorageResource getStorage() {
		return storage;
	}

	public void setStorage(StorageResource storage) {
		this.storage = storage;
	}

	public Long getRequestedCpus() {
		return requestedCpus;
	}

	public void setRequestedCpus(Long requestedCpus) {
		this.requestedCpus = requestedCpus;
	}

	public Long getRequestedMemory() {
		return requestedMemory;
	}

	public void setRequestedMemory(Long requestedMemory) {
		this.requestedMemory = requestedMemory;
	}

	public Calendar getRequestedCpuTime() {
		return requestedCpuTime;
	}

	public void setRequestedCpuTime(Calendar requestedCpuTime) {
		this.requestedCpuTime = requestedCpuTime;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Long getUsedCpus() {
		return usedCpus;
	}

	public void setUsedCpus(Long usedCpus) {
		this.usedCpus = usedCpus;
	}

	public Long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(Long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public Long getUsedCpuTime() {
		return usedCpuTime;
	}

	public void setUsedCpuTime(Long usedCpuTime) {
		this.usedCpuTime = usedCpuTime;
	}

	public Long getUsedWallTime() {
		return usedWallTime;
	}

	public void setUsedWallTime(Long usedWallTime) {
		this.usedWallTime = usedWallTime;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Calendar getEstimatedResponseTime() {
		return estimatedResponseTime;
	}

	public void setEstimatedResponseTime(Calendar estimatedResponseTime) {
		this.estimatedResponseTime = estimatedResponseTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getAllocationName() {
		return allocationName;
	}

	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public JobStatusType getStatus() {
		return status;
	}

	public void setStatus(JobStatusType status) {
		this.status = status;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public String getXmlDescription() {
		return xmlDescription;
	}

	public void setXmlDescription(String xmlDescription) {
		this.xmlDescription = xmlDescription;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isCheckpointable() {
		return checkpointable;
	}

	public void setCheckpointable(boolean checkpointable) {
		this.checkpointable = checkpointable;
	}

	public boolean isResubmittable() {
		return resubmittable;
	}

	public void setResubmittable(boolean resubmittable) {
		this.resubmittable = resubmittable;
	}

	public int getMaxResubmissions() {
		return maxResubmissions;
	}

	public void setMaxResubmissions(int maxResubmissions) {
		this.maxResubmissions = maxResubmissions;
	}

	public String getName() {
		return name;
	}
	
	/**
     * @param name The name to set.
     */
    public void setName(String name) {
        if (ServiceUtil.isAlphaNumeric(name)) {
            this.name = name;
        } else {
            throw new JobException("Invalid job name \"" + name + "\".\n" + 
                    "Names may contain only alphanumeric\ncharacters [a-zA-Z_0-9]");
        }
    }
	
	// ********************** Convenience Methods ********************** //

    public static Job getDefault(GMSSession session) throws ProviderException {
        Job job = new Job();
        
        job.id = new Long(99999);
        job.localId = "unknown";
        job.name = "default_job-"+new Date().toString();
        job.experimentName = "default_jobs";
        job.workDir = "/scratch";
        job.requestedCpus = new Long(2);
        job.requestedMemory = new Long(1024);
        
        // look up the compute resource
        job.system = new ResourceDao(session)._getComputeResources().get(0);
        job.storage = new ResourceDao(session)._getDefaultStorageResourceForComputeResource(job.system.name);
        job.queue = job.system.getDefaultQueue();
        
        job.requestedCpuTime = Calendar.getInstance();
        job.requestedCpuTime.clear();
        job.requestedCpuTime.set(Calendar.HOUR, 2);
        job.status = JobStatusType.INITIAL;
        
        return job;
    }
    
    private String getFileListing(List<LogicalFile> files) {
    	String sListing = null;
    	for (LogicalFile file: files) {
    		if (sListing == null) {
    			sListing = "";
    		} else {
    			sListing += ";";
    		}
    		sListing += file.getRemotePath();
    	}
    	
    	return sListing;
    }

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Job)) return false;
		final Job job = (Job) o;
		if (!name.equals(job.name)) return false;
		if (!id.equals(job.id))
        if (!localId.equals(job.localId)) return false;
		if (!user.getId().equals(job.user.getId())) return false;
		return true;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return  "Job ('" + getId() + "'), " +
				"Job name: '" + getName() + "' " +
                "Application: '" + getSoftware().getName() + "' " +
                "Compute Resource: '" + getSystem().getName() + "' " +
				"Local Job Id: '" + getLocalId() + "' " +
				"Project name: '" + getExperimentName() + "' " +
				"Scratch Directory: '" + getWorkDir() + "' " +
				"Processors Requested: '" + getRequestedCpus() + "' " +
				"Queue: '" + getQueue().getName() + "'" +
				"Requested CPU Time: '" + getRequestedCpuTime().toString() + "' " +
                "Storage server: '" + getStorage().getName() + "' " +
				"Memory Requested: '" + getRequestedMemory() + "' " +
				"Job Request Time: '" + getCreated().toString() + "' " +
				//"Predicted Submittion Time: '" + getEstimatedResponseTime().toString() + "'" +
				"Allocation name: '" + getAllocationName() + "' " + 
				"Cost: '" + getCost() + "' " +
				"Job Status: '" + getStatus() + "'" +
				"Processors Used: '" + getUsedCpus() + "' " +
				"Memory Used: '" + getUsedMemory() + "' " +
				"Wall Time Used: '" + getUsedWallTime() + "' " +
				"CPU Time Used: '" + getUsedCpuTime() + "'" + 
				"Input Files: '" + getFileListing(getInputFiles());
//				"Output Files: '" + getFileListing(getOutputFiles());
	}
	
	public int compareTo(Object o) {
		if (o instanceof Job)
			return this.getCreated().compareTo( ((Job)o).getCreated() );
		return 0;
	}
    
	// ********************** Job Management Methods ********************** //
    
    public JobBean toBean() {
    	JobBean bean = new JobBean();
    	
    	bean.setId(getId());
    	
    	if (getUser() == null) {
    		bean.setUserId(null);
    	} else {
    		bean.setUserId(getUser().getId());
    	}
        bean.setLocalId(getLocalId()); //added -nikhil someone forgot to addthis .. unbelievable	
    	bean.setHostName(system.getHostname());//added -nikhil to set the directory in client
        bean.setAllocationName(getAllocationName());
    	bean.setName(getName());
    	bean.setExperimentName(getExperimentName());
    	bean.setWorkDir(getWorkDir());
    	
    	for (LogicalFile file: getInputFiles()) {
    		bean.addInputFile(file.toBean());
    	}
//    	bean.setOutputFiles(getOutputFiles());
        bean.setRequestedCpus(getRequestedCpus());
        bean.setRequestedMemory(getRequestedMemory());
        bean.setRequestedCpuTime(getRequestedCpuTime());
        
        if (getSystem() == null) {
        	bean.setSystemName(null);
        } else {
        	bean.setSystemName(system.getName());
        }
        
        if (getSoftware() == null) {
        	bean.setSoftwareName(null);
        } else {
        	bean.setSoftwareName(getSoftware().getName());
        }
        
        if (getQueue() == null) {
        	bean.setQueueName(null);
        } else {
        	bean.setQueueName(getQueue().getName());
        }
        
        bean.setStartTime(getStartTime());
        bean.setStopTime(getStopTime());
        bean.setUsedCpus(getUsedCpus());
        bean.setUsedCpuTime(getUsedCpuTime());
        bean.setUsedMemory(getUsedMemory());
        bean.setUsedWallTime(getUsedWallTime());
        bean.setCreated(getCreated());
        bean.setProjectName(getProject().getName());
        bean.setCost(getCost());
        bean.setMetaData(getMetaData());
        bean.setStatus(getStatus());
        
        if (getStorage() == null) {
        	bean.setStorageResource(null);
        } else {
        	bean.setStorageResource(getStorage().getName());
        }
        
        bean.setCheckpointable(isCheckpointable());
        bean.setResubmittable(isResubmittable());
        bean.setMaxResubmissions(getMaxResubmissions());
        
    	return bean;
    }

	public void setModule(String module) {
		this.module = module;
	}

	public String getModule() {
		return module;
	}
   

}
