/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Dec 19, 2005
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
 * 3. Neither the projectNames of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the projectNames of its contributors 
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

import java.util.Date;

import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UsageBean;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.InstitutionType;
import org.gridchem.service.model.enumeration.ProjectStatusType;

/**
 * This the project class which ties an allocation of work on the CCG 
 * to one or more users.  We add a helper attribute named "pi" to this 
 * class to denote the situation where several users with PI classification
 * are collaborating on a project.  The person who requested and was 
 * granted this allocation is denoted the PI.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class Project {
	private Long id;
	private String name;
	private String description;
	private String comment;
	private InstitutionType institutionType;
	private String institution;
	private ProjectStatusType status;
	private Date startDate;
	private Date endDate;
	private String pi;
	private Date lastUpdated;
	private boolean theDefault;
	private Date created = new Date();
	private String fundingOrganization;
	private String fundedProjectId;
	private Usage usage;
	private AccessType type;
	
	// Fields used during allocation process. Not used again
	private String extProjectHostList = "";
	private int susPerJob = 0;
	private int memoryMBPerCPU = 0;
	private int diskGBPerJob = 0;
	private int cpusPerJob = 0;
	private String appsRequested = "";
	
	
	public Project() {}
	
	public Project(ProjectBean bean) {
		this.id = bean.getId();
		this.name = bean.getName();
		this.description = bean.getDescription();
		this.comment = bean.getComment();
		this.institutionType = bean.getInstitutionType();
		this.institution = bean.getInstitution();
		this.status = bean.getStatus();
		this.startDate = bean.getStartDate();
		this.endDate = bean.getEndDate();
		this.pi = bean.getPi();
		this.lastUpdated = bean.getLastUpdated();
		this.theDefault = bean.isTheDefault();
		this.created = bean.getCreated();
		this.fundingOrganization = bean.getFundingOrganization();
		this.fundedProjectId = bean.getFundedProjectId();
		this.usage = new Usage(bean.getUsage().getAllocated(), 
				bean.getUsage().getUsed(), bean.getUsage().getBalance());
		this.type = bean.getType();
		
	}
	
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
	 * @return the projectDescription
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param projectDescription the projectDescription to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the sponsorClass
	 */
	public InstitutionType getInstitutionType() {
		return institutionType;
	}

	/**
	 * @param sponsorClass the sponsorClass to set
	 */
	public void setInstitutionType(InstitutionType institutionType) {
		this.institutionType = institutionType;
	}

	/**
	 * @return the sponsorName
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * @param sponsorName the sponsorName to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/**
	 * @return the projectStatus
	 */
	public ProjectStatusType getStatus() {
		return status;
	}

	/**
	 * @param projectStatus the projectStatus to set
	 */
	public void setStatus(ProjectStatusType status) {
		this.status = status;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the pi
	 */
	public String getPi() {
		return pi;
	}

	/**
	 * @param pi the pi to set
	 */
	public void setPi(String pi) {
		this.pi = pi;
	}

	/**
	 * @return the dateModified
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param dateModified the dateModified to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the isDefault
	 */
	public boolean isTheDefault() {
		return theDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setTheDefault(boolean theDefault) {
		this.theDefault = theDefault;
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

	/**
	 * @return the funder
	 */
	public String getFundingOrganization() {
		return fundingOrganization;
	}

	/**
	 * @param funder the funder to set
	 */
	public void setFundingOrganization(String fundingOrganization) {
		this.fundingOrganization = fundingOrganization;
	}

	/**
	 * @return the fundedProject
	 */
	public String getFundedProjectId() {
		return fundedProjectId;
	}

	/**
	 * @param fundedProject the fundedProject to set
	 */
	public void setFundedProjectId(String fundedProjectId) {
		this.fundedProjectId = fundedProjectId;
	}

	/**
	 * @return the usage
	 */
	public Usage getUsage() {
		return usage;
	}

	/**
	 * @param usage the usage to set
	 */
	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	/**
	 * @return the extProjectHostList
	 */
	public String getExtProjectHostList() {
		return extProjectHostList;
	}

	/**
	 * @param extProjectHostList the extProjectHostList to set
	 */
	public void setExtProjectHostList(String extProjectHostList) {
		this.extProjectHostList = extProjectHostList;
	}

	/**
	 * @return the susPerJob
	 */
	public int getSusPerJob() {
		return susPerJob;
	}

	/**
	 * @param susPerJob the susPerJob to set
	 */
	public void setSusPerJob(int susPerJob) {
		this.susPerJob = susPerJob;
	}

	/**
	 * @return the memoryMBPerCPU
	 */
	public int getMemoryMBPerCPU() {
		return memoryMBPerCPU;
	}

	/**
	 * @param memoryMBPerCPU the memoryMBPerCPU to set
	 */
	public void setMemoryMBPerCPU(int memoryMBPerCPU) {
		this.memoryMBPerCPU = memoryMBPerCPU;
	}

	/**
	 * @return the diskGBPerJob
	 */
	public int getDiskGBPerJob() {
		return diskGBPerJob;
	}

	/**
	 * @param diskGBPerJob the diskGBPerJob to set
	 */
	public void setDiskGBPerJob(int diskGBPerJob) {
		this.diskGBPerJob = diskGBPerJob;
	}

	/**
	 * @return the cpusPerJob
	 */
	public int getCpusPerJob() {
		return cpusPerJob;
	}

	/**
	 * @param cpusPerJob the cpusPerJob to set
	 */
	public void setCpusPerJob(int cpusPerJob) {
		this.cpusPerJob = cpusPerJob;
	}

	/**
	 * @return the appsRequested
	 */
	public String getAppsRequested() {
		return appsRequested;
	}

	/**
	 * @param appsRequested the appsRequested to set
	 */
	public void setAppsRequested(String appsRequested) {
		this.appsRequested = appsRequested;
	}

	// ********************** Common Methods ********************** //
	
    /**
	 * @param type the type to set
	 */
	public void setType(AccessType type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public AccessType getType() {
		return type;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Project)) return false;
		final Project project = (Project) o;
		if (!name.equals(project.getName())) return false;
		if (!institutionType.equals(project.institutionType)) return false;
		if (!fundingOrganization.equals(project.fundingOrganization)) return false;
		if (!fundedProjectId.equals(project.fundingOrganization)) return false;
		if (!institution.equals(project.institution)) return false;
		if (!status.equals(project.status)) return false;
		if (!startDate.equals(project.startDate)) return false;
		if (!endDate.equals(project.endDate)) return false;
		if (theDefault != project.theDefault) return false;
		return true;
	}
	
	public int hashCode() {
		return (new String(name + startDate)).hashCode();
	}
	
	public String toString() {
		return  "Project ('" + getId().toString() + "') " +
				"Name: '" + getName() + "' " +
				"Funder: '" + getFundingOrganization() + "' " +
				"Funded Project: '" + getFundedProjectId() + "' " +
				"Comment: '" + getComment() + "' " +
				"Sponsoring Institution Type: '" + getInstitutionType() + "' " +
				"Sponsoring Institution: '" + getInstitution() + "' " +
				"Project Status: '" + getStatus() + "' " +
				"Start Date: '" + getStartDate() + "' " +
				"End Date: '" + getEndDate() + "' " +
				"Date Modified: '" + lastUpdated.toString() + "' " +
				"Default: '" + theDefault + "'" +
				getUsage().toString();
	}
	
	public int compareTo(Object o) {
		if (o instanceof Project)
			return (this.equals(o) ? 1 : 0 );
		return 0;
	}
	
	public ProjectBean toBean() {
		ProjectBean bean = new ProjectBean();
		bean.setId(id);
		bean.setName(name);
		bean.setComment(comment);
		bean.setDescription(description);
		bean.setInstitutionType(institutionType);
		bean.setInstitution(institution);
		bean.setStatus(status);
		bean.setStartDate(startDate);
		bean.setEndDate(endDate);
		bean.setPi(pi);
		bean.setLastUpdated(lastUpdated);
		bean.setCreated(created);
		bean.setTheDefault(theDefault);
		bean.setFundingOrganization(fundingOrganization);
		bean.setFundedProjectId(fundedProjectId);
		bean.setUsage(new UsageBean(usage.getAllocated(),usage.getUsed(),usage.getBalance()));
		bean.setType(type);
		bean.setInstitutionType(institutionType);
		
		return bean;
	}
	
}
