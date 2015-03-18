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



/**
 * This is the Software data transfer object.  It differs from the 
 * Software object in that it contains composite information from the software 
 * and hardware references in the original SoftwareInstallations objects .  A Set  
 * of these objects will be added to a VO object and serialized using xstream to 
 * the client.
 * 
 * The benefit of this approach is that the client no longer has to parse through
 * abstract data formats to figure out the multitude of application/hpc resource 
 * combinations.  They are all included in these objects. 
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 * 
 * @see Software
 * @see ComputeResource
 * @see SoftwareInstallation
 */
@SuppressWarnings("unchecked")
public class SoftwareBean extends ResourceBean implements Comparable{
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
    private List<String> modules = new ArrayList<String>();
    public SoftwareBean() {}

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
	 * @return the releaseVersion
	 */
	public String getReleaseVersion() {
		return releaseVersion;
	}

	/**
	 * @param releaseVersion the releaseVersion to set
	 */
	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the authorEmail
	 */
	public String getAuthorEmail() {
		return authorEmail;
	}

	/**
	 * @param authorEmail the authorEmail to set
	 */
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	/**
	 * @return the helpWebsite
	 */
	public String getHelpWebsite() {
		return helpWebsite;
	}

	/**
	 * @param helpWebsite the helpWebsite to set
	 */
	public void setHelpWebsite(String helpWebsite) {
		this.helpWebsite = helpWebsite;
	}

	/**
	 * @return the acronym
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * @param acronym the acronym to set
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * @return the versionRelease
	 */
	public String getVersionRelease() {
		return versionRelease;
	}

	/**
	 * @param versionRelease the versionRelease to set
	 */
	public void setVersionRelease(String versionRelease) {
		this.versionRelease = versionRelease;
	}

	/**
	 * @return the versionDate
	 */
	public Date getVersionDate() {
		return versionDate;
	}

	/**
	 * @param versionDate the versionDate to set
	 */
	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
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

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<String> modules) {
		this.modules = modules;
	}

	/**
	 * @return the modules
	 */
	public List<String> getModules() {
		return modules;
	}
	
	public boolean equals(Object o) {
		if (o instanceof SoftwareBean) {
			return ((SoftwareBean) o).getName().equals(name) &&  ((SoftwareBean) o).getAcronym().equals(acronym);
		}
		return false;
	}

	public String toString() {
        return "Software: " + getName() + "\n" + getShortDescription() + "\n";
    }

}