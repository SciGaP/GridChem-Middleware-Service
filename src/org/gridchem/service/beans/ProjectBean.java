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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.InstitutionType;
import org.gridchem.service.model.enumeration.ProjectStatusType;

/**
 * This is the Project data transfer object.  It differs from the Project
 * object in that it contains Usage information for the user.  This information 
 * is pulled via the UsageDAO object from the UserProjectResource table. Results
 * will be placed in a UsageDTO object and added to the "usage" set in this 
 * object.
 * 
 * Another difference which came naturally out of the design is that from a VO
 * perspective there is a natural information hierarchy from the user's point of 
 * view.  This hierarchy is as follows:
 * 
 * UserDTO
 *   - user info fields
 *   - ProjectDTO(1)
 *     - project info fields
 *     - overall project Usage stats
 *       - individual HardwareDTO(1)
 *         - hardware info fields
 *         - individual Usage stats for project on this resource 
 *         - SoftwareDTO(1) available for this resource 
 *         - SoftwareDTO(N) available for this resource 
 *       - individual HardwareDTO(2)
 *         - hardware info fields
 *         - individual Usage stats for project on this resource 
 *         - SoftwareDTO(1) available for this resource 
 *         - SoftwareDTO(N) available for this resource 
 *   - ProjectDTO(N)
 *     - ...
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 * @see HardwareDTO
 * @see SoftwareDTO
 * @see Usage
 */
public class ProjectBean {
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
	private boolean current = false;
	private String fundingOrganization;
	private String fundedProjectId;
	private UsageBean usage;
	private AccessType type;
	private List<ComputeBean> systems = new ArrayList<ComputeBean>();
	private Date created = new Date();
	
    /**
     * Default no-args constructor.
     */
    public ProjectBean() {}

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
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
	 * @param fundingOrganization the fundingOrganization to set
	 */
	public void setFundingOrganization(String fundingOrganization) {
		this.fundingOrganization = fundingOrganization;
	}

	/**
	 * @return the fundingOrganization
	 */
	public String getFundingOrganization() {
		return fundingOrganization;
	}

	/**
	 * @param fundedProjectId the fundedProjectId to set
	 */
	public void setFundedProjectId(String fundedProjectId) {
		this.fundedProjectId = fundedProjectId;
	}

	/**
	 * @return the fundedProjectId
	 */
	public String getFundedProjectId() {
		return fundedProjectId;
	}

	/**
	 * @return the institutionType
	 */
	public InstitutionType getInstitutionType() {
		return institutionType;
	}

	/**
	 * @param institutionType the institutionType to set
	 */
	public void setInstitutionType(InstitutionType institutionType) {
		this.institutionType = institutionType;
	}

	/**
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/**
	 * @return the status
	 */
	public ProjectStatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
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
	 * @param current the current to set
	 */
	public void setCurrent(boolean current) {
		this.current = current;
	}

	/**
	 * @return the current
	 */
	public boolean isCurrent() {
		return current;
	}

	public void setSystems(List<ComputeBean> systems) {
		this.systems = systems;
	}

	public List<ComputeBean> getSystems() {
		return systems;
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
	 * @param usage the usage to set
	 */
	public void setUsage(UsageBean usage) {
		this.usage = usage;
	}

	/**
	 * @return the usage
	 */
	public UsageBean getUsage() {
		return usage;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((institution == null) ? 0 : institution.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pi == null) ? 0 : pi.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectBean other = (ProjectBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (institution == null) {
			if (other.institution != null)
				return false;
		} else if (!institution.equals(other.institution))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pi == null) {
			if (other.pi != null)
				return false;
		} else if (!pi.equals(other.pi))
			return false;
		return true;
	}

	/**
	 * @return
	 * @see java.lang.String#toString()
	 */
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
		"Date Modified: '" + getLastUpdated() + "' " +
		"Default: '" + theDefault + "'" +
		getUsage().toString();
	}
   
    

    
}