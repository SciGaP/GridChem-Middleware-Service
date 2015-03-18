/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 30, 2005
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

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.SoftwareBean;


/**
 * A SoftwareResources is any software available to the user. Software is 
 * useless without hardware to run it, but that does not mean that software 
 * cannot exist without hardware.  Thus there is a 0 to many relationship 
 * between a software and hardware resource.  As well, there is a one to 
 * many relationship between a software resource and the 
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class Software {
	
	private Long id;
	private String name;
	private String comment;
	private String releaseVersion;
    private String authorName;
    private String authorEmail;
    private String helpWebsite;
    private String acronym;
    private String versionRelease;
    private Date versionDate;
    private String shortDescription;
    private String longDescription;
    private String inputFileExtension;
    private Set<SoftwareModule> modules;
    private Date created = new Date();
    private Date lastUpdated = new Date();
    
    /**
	 * No-arg constructor for JavaBean tools.
	 */
    public Software() {}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Returns the colloquial name of this software resource.
	 * ex. Gaussian 2005 = g05
	 * 
	 * @return Returns the acronym.
	 */
	public String getAcronym() {
		return acronym;
	}
	/**
	 * @param acronym The acronym to set.
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	/**
	 * @return Returns the authorEmail.
	 */
	public String getAuthorEmail() {
		return authorEmail;
	}
	/**
	 * @param authorEmail The authorEmail to set.
	 */
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	/**
	 * @return Returns the authorName.
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName The authorName to set.
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return Returns the helpWebsite.
	 */
	public String getHelpWebsite() {
		return helpWebsite;
	}
	/**
	 * @param helpWebsite The helpWebsite to set.
	 */
	public void setHelpWebsite(String helpWebsite) {
		this.helpWebsite = helpWebsite;
	}
	/**
	 * @return Returns the longDescription.
	 */
	public String getLongDescription() {
		return longDescription;
	}
	/**
	 * @param longDescription The longDescription to set.
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	/**
	 * @return Returns the releaseVersion.
	 */
	public String getReleaseVersion() {
		return releaseVersion;
	}
	/**
	 * @param releaseVersion The releaseVersion to set.
	 */
	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}
	/**
	 * @return Returns the shortDescription.
	 */
	public String getShortDescription() {
		return shortDescription;
	}
	/**
	 * @param shortDescription The shortDescription to set.
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	/**
	 * @return Returns the versionDate.
	 */
	public Date getVersionDate() {
		return versionDate;
	}
	/**
	 * @param versionDate The versionDate to set.
	 */
	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}
	/**
	 * @return Returns the versionRelease.
	 */
	public String getVersionRelease() {
		return versionRelease;
	}
	/**
	 * @param versionRelease The versionRelease to set.
	 */
	public void setVersionRelease(String versionRelease) {
		this.versionRelease = versionRelease;
	}
	/**
     * @return the inputFileExtension
     */
    public String getInputFileExtension() {
        return inputFileExtension;
    }

    /**
     * @param inputFileExtension the inputFileExtension to set
     */
    public void setInputFileExtension(String inputFileExtension) {
        this.inputFileExtension = inputFileExtension;
    }
	// ********************** Common Methods ********************** //
	
    /**
	 * @param modules the modules to set
	 */
	public void setModules(Set<SoftwareModule> modules) {
		this.modules = modules;
	}

	/**
	 * @return the modules
	 */
	public Set<SoftwareModule> getModules() {
		return modules;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Software)) return false;
		final Software softwareResource = (Software) o;
		if (!getName().equals(softwareResource.getName())) return false;
		if (!releaseVersion.equals(softwareResource.releaseVersion)) return false;
		if (!acronym.equals(softwareResource.acronym)) return false;
		if (!authorName.equals(softwareResource.authorName)) return false;
		if (!releaseVersion.equals(softwareResource.releaseVersion)) return false;
		if (!authorName.equals(softwareResource.authorName)) return false;
		if (!authorEmail.equals(softwareResource.authorEmail)) return false;
		if (!shortDescription.equals(softwareResource.shortDescription)) return false;
		if (!longDescription.equals(softwareResource.longDescription)) return false;
		if (!helpWebsite.equals(softwareResource.helpWebsite)) return false;
		if (!getCreated().equals(softwareResource.getCreated())) return false;
		return true;
	}
	
	public int hashCode() {
		return (new String(this.getName() + this.releaseVersion)).hashCode();
	}
	
	public String toString() {
		return  "Software Resource ('" + getId() + "'), " +
				"Name: '" + getName() + "'" +
				"Acronym: '" + getAcronym() + "'" +
				"Release Version: '" + getReleaseVersion() + "'" +
				"Release Date: '" + getVersionDate() + "'" +
				"Comment: '" + getComment() + "'" +
				"Author Address: '" + getAuthorName() + "'" +
				"Author Email: '" + getAuthorEmail() + "'" +
				"Short Description: '" + getShortDescription() + "'" +
				"Help URL: '" + getHelpWebsite() + "'";
	}
	
	public int compareTo(Object o) {
		if (o instanceof Software)
			return this.getCreated().compareTo( ((Software)o).getCreated() );
		return 0;
	}

	public ResourceBean toBean() {
		SoftwareBean bean = new SoftwareBean();
		bean.setName(getName());
		bean.setAcronym(acronym);
		bean.setAuthorName(authorName);
		bean.setAuthorEmail(authorEmail);
		bean.setHelpWebsite(helpWebsite);
		bean.setVersionRelease(versionRelease);
		bean.setVersionDate(versionDate);
		bean.setShortDescription(shortDescription);
		bean.setLongDescription(longDescription);
		
		for(SoftwareModule module: modules) {
			bean.getModules().add(module.getName());
		}
		
		return bean;
	}
}
