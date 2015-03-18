/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Dec 6, 2005
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

import java.util.HashSet;
import java.util.Set;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.SoftwareBean;
import org.gridchem.service.model.enumeration.BatchSchedulerType;
import org.gridchem.service.model.enumeration.ResourceStatusType;

/**
 * POJO to represent the ComputeResource table in the db.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
@SuppressWarnings("unchecked")
public class ComputeResource extends CCGResource {
    private long totalCpu;
    private String system;
    private long totalNodes;
    private long totalMemory;
    private long totalDisk;
    private double peakPerformance;
    private String scratchDisk;
    private String scratchDirectory;
    private String webSite;
    private String adminEmail;
    private String pflag;
    private String scpFlag;
    private String reFlag;
    private long availableCpu;
    private long availableDisk;
    private String jobsProgramPath;
    private String histProgramPath;
    private String killProgramPath;
    private String siteAcronym;
    private boolean lindaIsAvailable;
    private BatchSchedulerType scheduler;
    private Load load;
	private Set<Queue> queues;
    private Set<SoftwareInstallation> softwareInstallations = new HashSet<SoftwareInstallation>();
    
    /**
	 * No-arg constructor for JavaBean tools.
	 */
    public ComputeResource () {}
	
	/**
	 * @return Returns the userResources.
	 */
	public long getTotalCpu() {
		return totalCpu;
	}
	/**
	 * @param userResources The userResources to set.
	 */
	public void setTotalCpu(long totalCpu) {
		this.totalCpu = totalCpu;
	}
	
	public Set<Queue> getQueues() {
		return queues;
	}
	
	/**
	 * @param queues The queues to set.
	 */
	public void setQueues(Set queues) {
		this.queues = queues;
	}
	
	public Queue getDefaultQueue() {
	    for(Queue q: getQueues()) {
            if (q.isTheDefault()) {
                return q;
            }
        }
	    return null;
	}
	
	/**
	 * @return Returns the softwareInstallation.
	 */
	public Set getSoftwareInstallations() {
		return softwareInstallations;
	}
	/**
	 * @param softwareInstallation The softwareInstallation to set.
	 */
	public void setSoftwareInstallations(
            Set softwareInstallations) {
		this.softwareInstallations = softwareInstallations;
	}
	/**
	 * @return Returns the adminEmail.
	 */
	public String getAdminEmail() {
		return adminEmail;
	}
	/**
	 * @param adminEmail The adminEmail to set.
	 */
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	/**
	 * @return Returns the peakPerformance.
	 */
	public double getPeakPerformance() {
		return peakPerformance;
	}
	/**
	 * @param peakPerformance The peakPerformance to set.
	 */
	public void setPeakPerformance(double peakPerformance) {
		this.peakPerformance = peakPerformance;
	}
	/**
	 * @return Returns the scratchDisk.
	 */
	public String getScratchDisk() {
		return scratchDisk;
	}
	/**
	 * @param scratchDisk The scratchDisk to set.
	 */
	public void setScratchDisk(String scratchDisk) {
		this.scratchDisk = scratchDisk;
	}
    
	/**
     * @return the scratchDirectory
     */
    public String getScratchDirectory() {
        return scratchDirectory;
    }
    /**
     * @param scratchDirectory the scratchDirectory to set
     */
    public void setScratchDirectory(String scratchDirectory) {
        this.scratchDirectory = scratchDirectory;
    }


    /**
	 * @return Returns the status.
	 */
	public ResourceStatusType getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(ResourceStatusType status) {
		this.status = status;
	}
	/**
	 * @return Returns the system.
	 */
	public String getSystem() {
		return system;
	}
	/**
	 * @param system The system to set.
	 */
	public void setSystem(String system) {
		this.system = system;
	}
	/**
	 * @return Returns the totalNodes.
	 */
	public long getTotalNodes() {
		return totalNodes;
	}
	/**
	 * @param totalNodes The totalNodes to set.
	 */
	public void setTotalNodes(long totalNodes) {
		this.totalNodes = totalNodes;
	}
	/**
	 * @return Returns the webSite.
	 */
	public String getWebSite() {
		return webSite;
	}
	/**
	 * @param webSite The webSite to set.
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	/**
	 * @return Returns the histProgramPath.
	 */
	public String getHistProgramPath() {
		return histProgramPath;
	}
	/**
	 * @param histProgramPath The histProgramPath to set.
	 */
	public void setHistProgramPath(String histProgramPath) {
		this.histProgramPath = histProgramPath;
	}
	/**
	 * @return Returns the jobsProgramPath.
	 */
	public String getJobsProgramPath() {
		return jobsProgramPath;
	}
	/**
	 * @param jobsProgramPath The jobsProgramPath to set.
	 */
	public void setJobsProgramPath(String jobsProgramPath) {
		this.jobsProgramPath = jobsProgramPath;
	}
	/**
	 * @return Returns the killProgramPath.
	 */
	public String getKillProgramPath() {
		return killProgramPath;
	}
	/**
	 * @param killProgramPath The killProgramPath to set.
	 */
	public void setKillProgramPath(String killProgramPath) {
		this.killProgramPath = killProgramPath;
	}
	/**
	 * @return Returns the pFlag.
	 */
	public String getPflag() {
		return pflag;
	}
	/**
	 * @param flag The pFlag to set.
	 */
	public void setPflag(String flag) {
		pflag = flag;
	}
	/**
	 * @return Returns the reFlag.
	 */
	public String getReFlag() {
		return reFlag;
	}
	/**
	 * @param reFlag The reFlag to set.
	 */
	public void setReFlag(String reFlag) {
		this.reFlag = reFlag;
	}
	/**
	 * @return Returns the scheduler.
	 */
	public BatchSchedulerType getScheduler() {
		return scheduler;
	}
	/**
	 * @param scheduler The scheduler to set.
	 */
	public void setScheduler(BatchSchedulerType scheduler) {
		this.scheduler = scheduler;
	}
	/**
	 * @return Returns the scpFlag.
	 */
	public String getScpFlag() {
		return scpFlag;
	}
	/**
	 * @param scpFlag The scpFlag to set.
	 */
	public void setScpFlag(String scpFlag) {
		this.scpFlag = scpFlag;
	}
	/**
	 * @return Returns the siteAcronym.
	 */
	public String getSiteAcronym() {
		return siteAcronym;
	}
	/**
	 * @param siteAcronym The siteAcronym to set.
	 */
	public void setSiteAcronym(String siteAcronym) {
		this.siteAcronym = siteAcronym;
	}
	/**
	 * @return Returns the availableCpu.
	 */
	public long getAvailableCpu() {
		return availableCpu;
	}
	/**
	 * @param availableCpu The availableCpu to set.
	 */
	public void setAvailableCpu(long availableCpu) {
		this.availableCpu = availableCpu;
	}

	/**
	 * @param totalDisk the totalDisk to set
	 */
	public void setTotalDisk(long totalDisk) {
		this.totalDisk = totalDisk;
	}

	/**
	 * @return the totalDisk
	 */
	public long getTotalDisk() {
		return totalDisk;
	}

	/**
	 * @return Returns the load.
	 */
	public Load getLoad() {
		return load;
	}
	/**
	 * @param load The load to set.
	 */
	public void setLoad(Load load) {
		this.load = load;
	}
	/**
	 * @return the totalMemory
	 */
	public long getTotalMemory() {
		return totalMemory;
	}

	/**
	 * @param totalMemory the totalMemory to set
	 */
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	/**
     * @return the lindaIsAvailable
     */
    public boolean getLindaIsAvailable() {
        return lindaIsAvailable;
    }


    /**
     * @param lindaIsAvailable the lindaIsAvailable to set
     */
    public void setLindaIsAvailable(boolean lindaIsAvailable) {
        this.lindaIsAvailable = lindaIsAvailable;
    }


    /**
	 * @param availableMemory the availableMemory to set
	 */
	public void setAvailableDisk(long availableDisk) {
		this.availableDisk = availableDisk;
	}

	/**
	 * @return the availableMemory
	 */
	public long getAvailableDisk() {
		return availableDisk;
	}

    
	// ********************** Common Methods ********************** //
	
   public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ComputeResource)) return false;
		final ComputeResource computeResource = (ComputeResource) o;
		if (!id.equals(computeResource.id)) return false;
		if (!name.equals(computeResource.name)) return false;
		if (!comment.equals(computeResource.comment)) return false;
		if (!getType().equals(computeResource.getType())) return false;
		if (totalCpu != computeResource.totalCpu) return false;
		return true;
	}
	
	public int hashCode() {
		return (new String(this.getId()+
				this.getName())).hashCode();
	}
	
	public String toString() {
		return  //"Compute Resource: (\"" + getId().toString() + "\") " +
				"Name: '" + getName() + "' " +
				"Comment: '" + getComment() + "' " +
				//"Type: '" + getType().toString() + "' " +
				"Total CPU: '" + getTotalCpu() + "' " +
				"Available CPU: '" + getTotalCpu() + "' " +
				"Hostname: '" + getHostname() + "' " +
				"IP Address: '" + getIpAddress() + "' " +
				"System: '" + getSystem() + "' " +
				"Total Nodes: '" + getTotalNodes() + "' " +
				"Available Nodes: '" + getTotalNodes() + "' " +
				"Peak Performance: '" + getPeakPerformance() + "' " +
				"Scratch Directory: '" + getScratchDirectory() + "' " +
				"Disk Total Space: '" + getTotalDisk() + "'" +
				"Disk Available Space: '" + getLoad().getDisk() + "'" +
				"Last Down Time: '" + getLastDownTime().toString() + "'" +
				//"Last Update: '" + getLastUpdate().toString() + "'" +
				//"Created: '" + getCreated().toString() + "'" +
				//"Status: '" + getStatus().toString() + "' " +
				"Website: '" + getWebSite() + "' " +
				"Admin Email: '" + getAdminEmail() + "' " +
				"P Flag: '" + getPflag() + "' " +
				"SCP Flag: '" + getScpFlag() + "' " +
				"RE Flag: '" + getReFlag() + "' " +
				"Jobs Program Path: '" + getJobsProgramPath() + "' " +
				"Hist Program Path: '" + getHistProgramPath() + "' " +
				"Kill Program Path: '" + getKillProgramPath() + "' " +
				"Site Acronym: '" + getSiteAcronym() + "' ";
				//"Batch Scheduler: '" + getScheduler().getName() + "' ";
	}
	
	public int compareTo(Object o) {
		if (o instanceof SoftwareInstallation)
			return this.getId().compareTo(
					((ComputeResource)o).getId() );
		return 0;
	}
	
//	 ********************** Business Methods ********************** //
	
	public ResourceBean toBean() {
	    ComputeBean bean = new ComputeBean();
	    bean.setName(name);
	    bean.setComment(comment);
	    bean.setHostname(hostname);
	    bean.setIpAddress(ipAddress);
	    bean.setSite(site.toBean());
	    bean.setWebsite(webSite);
	    bean.setStatus(status);
	    bean.setLoad(load.toBean());
	    bean.setAvailableCpu(availableCpu);
	    bean.setScheduler(scheduler);
	    bean.setSystem(system);
	    bean.setTotalNodes(totalNodes);
	    bean.setTotalCpu(totalCpu);
	    bean.setTotalDisk(getTotalDisk());
	    bean.setPeakPerformance(peakPerformance);
	    bean.setTotalMemory(totalMemory);
	    bean.setAvailableDisk(availableDisk);
	    bean.setDefaultScratchDirectory(scratchDirectory);
	    
	    for (Queue q: getQueues()) {
	    	bean.getQueues().add(q.toBean());
	    }
	    
	    
	    for (SoftwareInstallation si: softwareInstallations) {
	    	bean.getSoftware().add((SoftwareBean)si.getSoftware().toBean());
	    }
	    
		return bean;
	}
}
