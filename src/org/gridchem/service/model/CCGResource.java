/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jan 19, 2006
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

import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.model.enumeration.ResourceType;
import org.hibernate.collection.PersistentSet;

/**
 * Parent class for all GMS ResourceSynchronizer.  
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 * @see GMSAbstractResource
 */
public abstract class CCGResource {
	protected Long id;
	protected String name;
	protected String comment;
	protected String hostname;
	protected String ipAddress;
	protected Date created = new Date();
	protected Date lastDownTime;
	protected Date lastUpdated;
	private ResourceType type;
	protected ResourceStatusType status;
	protected PersistentSet userResources;
	protected Site site;
	protected boolean valid;
	
//	protected PersistentSet projectResources;
	
	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return Returns the created.
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created The created to set.
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return Returns the hostname.
	 */
	public String getHostname() {
		return hostname;
	}
	/**
	 * @param hostname The hostname to set.
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	/**
	 * @return Returns the ipAddress.
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress The ipAddress to set.
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param type the type to set
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}
	/**
	 * @return the type
	 */
	public ResourceType getType() {
		return type;
	}
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the lastDownTime.
	 */
	public Date getLastDownTime() {
		return lastDownTime;
	}
	/**
	 * @param lastDownTime The lastDownTime to set.
	 */
	public void setLastDownTime(Date lastDownTime) {
		this.lastDownTime = lastDownTime;
	}
	/**
	 * @return Returns the lastUpdate.
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdate The lastUpdate to set.
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/**
	 * @return Returns the userResources.
	 */
	public PersistentSet getUserResources() {
		return userResources;
	}
	/**
	 * @param userResources The userResources to set.
	 */
	public void setUserResources(PersistentSet userResources) {
		this.userResources = userResources;
	}
	
	/**
	 * @param site the site to set
	 */
	public void setSite(Site site) {
		this.site = site;
	}
	/**
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public abstract ResourceBean toBean();
	
}
