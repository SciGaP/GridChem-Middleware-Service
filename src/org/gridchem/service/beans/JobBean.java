/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Mar 28, 2006
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

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.gridchem.service.model.enumeration.JobStatusType;


/**
 * Deserialized job object. A JobDTO object accurately represents one record in the
 * Jobs table.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class JobBean {
    
    private Long id = null;
    private String name;
    private String localId;
    private String logicalId;
    private String systemName;
    private String hostName; //added -nik for setting directory
    private String softwareName;
    private String moduleName;
    private String queueName;
    private Long userId;
    private String experimentName;
    private String workDir;
    private List<LogicalFileBean> inputFiles = new ArrayList<LogicalFileBean>();
    private List<LogicalFileBean> outputFiles;
    private String storageResource;
    private Long requestedCpus;
    private Long requestedMemory;
    private Calendar requestedCpuTime;
    private Date startTime;
    private Date stopTime;
    private Long usedCpus = new Long(0);
    private Long usedMemory = new Long(0);
    private Long usedCpuTime = new Long(0);
    private Long usedWallTime = new Long(0);
    private Date created = new Date();
    private String projectName;
    private String allocationName;
    private Double cost;
    private JobStatusType status;
    private Date lastUpdated = new Date();
    private String metaData;
    private boolean checkpointable = false;
    private boolean resubmittable = false;
    private int maxResubmissions = 0;
    
	public JobBean() {}
	
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
	 * @return the localId
	 */
	public String getLocalId() {
		return localId;
	}

	/**
	 * @param localId the localId to set
	 */
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	/**
	 * @return the logicalId
	 */
	public String getLogicalId() {
		return logicalId;
	}

	/**
	 * @param logicalId the logicalId to set
	 */
	public void setLogicalId(String logicalId) {
		this.logicalId = logicalId;
	}

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	/**
	 * @param systemName the systemName to set
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

//added nik
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}



	/**
	 * @return the softwareName
	 */
	public String getSoftwareName() {
		return softwareName;
	}

	/**
	 * @param softwareName the softwareName to set
	 */
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the experimentName
	 */
	public String getExperimentName() {
		return experimentName;
	}

	/**
	 * @param experimentName the experimentName to set
	 */
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	/**
	 * @return the workDir
	 */
	public String getWorkDir() {
		return workDir;
	}

	/**
	 * @param workDir the workDir to set
	 */
	public void setWorkDir(String workDir) {
		this.workDir = workDir;
	}

	/**
	 * @return the inputFiles
	 */
	public List<LogicalFileBean> getInputFiles() {
		return inputFiles;
	}

	/**
	 * @param inputFiles the inputFiles to set
	 */
	public void setInputFiles(List<LogicalFileBean> inputFiles) {
		this.inputFiles = inputFiles;
	}

	/**
	 * @param bean
	 */
	public void addInputFile(LogicalFileBean bean) {
		this.inputFiles.add(bean);
	}
	
	/**
	 * @return the outputFiles
	 */
	public List<LogicalFileBean> getOutputFiles() {
		return outputFiles;
	}

	/**
	 * @param outputFiles the outputFiles to set
	 */
	public void setOutputFiles(List<LogicalFileBean> outputFiles) {
		this.outputFiles = outputFiles;
	}

	/**
	 * @return the storageResource
	 */
	public String getStorageResource() {
		return storageResource;
	}

	/**
	 * @param storageResource the storageResource to set
	 */
	public void setStorageResource(String storageResource) {
		this.storageResource = storageResource;
	}

	/**
	 * @return the requestedCpus
	 */
	public Long getRequestedCpus() {
		return requestedCpus;
	}

	/**
	 * @param requestedCpus the requestedCpus to set
	 */
	public void setRequestedCpus(Long requestedCpus) {
		this.requestedCpus = requestedCpus;
	}

	/**
	 * @return the requestedMemory
	 */
	public Long getRequestedMemory() {
		return requestedMemory;
	}

	/**
	 * @param requestedMemory the requestedMemory to set
	 */
	public void setRequestedMemory(Long requestedMemory) {
		this.requestedMemory = requestedMemory;
	}

	/**
	 * @return the requestedCpuTime
	 */
	public Calendar getRequestedCpuTime() {
		return requestedCpuTime;
	}

	/**
	 * @param requestedCpuTime the requestedCpuTime to set
	 */
	public void setRequestedCpuTime(Calendar requestedCpuTime) {
		this.requestedCpuTime = requestedCpuTime;
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

	/**
	 * @return the usedCpus
	 */
	public Long getUsedCpus() {
		return usedCpus;
	}

	/**
	 * @param usedCpus the usedCpus to set
	 */
	public void setUsedCpus(Long usedCpus) {
		this.usedCpus = usedCpus;
	}

	/**
	 * @return the usedMemory
	 */
	public Long getUsedMemory() {
		return usedMemory;
	}

	/**
	 * @param usedMemory the usedMemory to set
	 */
	public void setUsedMemory(Long usedMemory) {
		this.usedMemory = usedMemory;
	}

	/**
	 * @return the usedCpuTime
	 */
	public Long getUsedCpuTime() {
		return usedCpuTime;
	}

	/**
	 * @param usedCpuTime the usedCpuTime to set
	 */
	public void setUsedCpuTime(Long usedCpuTime) {
		this.usedCpuTime = usedCpuTime;
	}

	/**
	 * @return the usedWallTime
	 */
	public Long getUsedWallTime() {
		return usedWallTime;
	}

	/**
	 * @param usedWallTime the usedWallTime to set
	 */
	public void setUsedWallTime(Long usedWallTime) {
		this.usedWallTime = usedWallTime;
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

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

//	/**
//	 * @return the projectId
//	 */
//	public Long getProjectId() {
//		return projectId;
//	}
//
//	/**
//	 * @param projectId the projectId to set
//	 */
//	public void setProjectId(Long projectId) {
//		this.projectId = projectId;
//	}

	/**
	 * @return the allocationName
	 */
	public String getAllocationName() {
		return allocationName;
	}

	/**
	 * @param allocationName the allocationName to set
	 */
	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
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
	 * @return the metaData
	 */
	public String getMetaData() {
		return metaData;
	}

	/**
	 * @param metaData the metaData to set
	 */
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	/**
	 * @return the checkpointable
	 */
	public boolean isCheckpointable() {
		return checkpointable;
	}

	/**
	 * @param checkpointable the checkpointable to set
	 */
	public void setCheckpointable(boolean checkpointable) {
		this.checkpointable = checkpointable;
	}

	/**
	 * @return the resubmittable
	 */
	public boolean isResubmittable() {
		return resubmittable;
	}

	/**
	 * @param resubmittable the resubmittable to set
	 */
	public void setResubmittable(boolean resubmittable) {
		this.resubmittable = resubmittable;
	}

	/**
	 * @return the maxResubmissions
	 */
	public int getMaxResubmissions() {
		return maxResubmissions;
	}

	/**
	 * @param maxResubmissions the maxResubmissions to set
	 */
	public void setMaxResubmissions(int maxResubmissions) {
		this.maxResubmissions = maxResubmissions;
	}

	//  ********************** Private Methods ********************** // 
    @SuppressWarnings("unused")
	private String formatTime(Time time) {
        DateFormat formatter = 
            new SimpleDateFormat("H:mm:ss");
        return formatter.format(time);
    }
    @SuppressWarnings("unused")
	private String formatTime(Date time) {
        DateFormat formatter = 
            new SimpleDateFormat("H:mm:ss");
        return formatter.format(time);
    }
    @SuppressWarnings("unused")
	private String formatDate(Date date) {
        DateFormat formatter = 
            new SimpleDateFormat("MM/dd/yyyy H:mm:ss");
        return formatter.format(date);
    }

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return moduleName;
	}
    
}
