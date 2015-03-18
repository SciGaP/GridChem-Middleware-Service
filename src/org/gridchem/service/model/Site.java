/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Mar 22, 2006
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.gridchem.service.beans.SiteBean;


/**
 * This class represents a physical organization which owns and/or 
 * administers non-software resources.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 * @see ComputeResource
 * @see NetworkResource
 * @see VisualizationResource
 * @see StorageResource
 * @see CCGResource
 */
public class Site {
	private Long id;
    private String name;
    private String description;
    private String acronym;
    private Date lastUpdated = new Date();
    private Date created = new Date();
    private Set<CCGResource> resources;
        
    /**
     * Default noargs constructor
     */
    public Site() {}
    
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
	 * @param resources the resources to set
	 */
	public void setResources(Set<CCGResource> resources) {
		this.resources = resources;
	}

	/**
	 * @return the resources
	 */
	public Set<CCGResource> getResources() {
		return resources;
	}

	/**
	 * @return the storageResources
	 */
	public List<ComputeResource> getComputeResources() {
		List<ComputeResource> computeResources = new ArrayList<ComputeResource>();
		for (CCGResource resource: getResources()) {
			if (resource instanceof ComputeResource) {
				computeResources.add((ComputeResource)resource);
			}
		}
		return computeResources;
	}
	
	/**
	 * @return the storageResources
	 */
	public List<VisualizationResource> getVizResources() {
		List<VisualizationResource> vizResources = new ArrayList<VisualizationResource>();
		for (CCGResource resource: getResources()) {
			if (resource instanceof VisualizationResource) {
				vizResources.add((VisualizationResource)resource);
			}
		}
		return vizResources;
	}
	
	/**
	 * @return the storageResources
	 */
	public List<NetworkResource> getNetworkResources() {
		List<NetworkResource> networkResources = new ArrayList<NetworkResource>();
		for (CCGResource resource: getResources()) {
			if (resource instanceof NetworkResource) {
				networkResources.add((NetworkResource)resource);
			}
		}
		return networkResources;
	}
	/**
	 * @return the storageResources
	 */
	public List<StorageResource> getStorageResources() {
		List<StorageResource> storageResources = new ArrayList<StorageResource>();
		for (CCGResource resource: getResources()) {
			if (resource instanceof StorageResource) {
				storageResources.add((StorageResource)resource);
			}
		}
		return storageResources;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		final Site site = (Site) o;
		if (!acronym.equals(site.getAcronym())) return false;
		if (!name.equals(site.getName())) return false;
		if (!description.equals(site.getDescription())) return false;
		return true;
	}
	
	public int hashCode() {
		return (getAcronym() + getName()).hashCode();
	}
	
	public String toString() {
		return  "Site (" + getId().toString()  + "), " +
				"Site Name: '" + getName() + "' " +
				"Site Acronym: '" + getAcronym() + "' " +
				"Site Description: '" + getDescription() + "' " +
				"Last Updated: '" + getLastUpdated() + "' " +
				"Created on: '" + getCreated().toString() + "' ";
	}
	
	public int compareTo(Object o) {
		if (o instanceof Site)
			return (this.getId().compareTo(((Site)o).getId()));
		return 0;
	}
	
	public SiteBean toBean() {
		SiteBean bean = new SiteBean();
		bean.setName(name);
		bean.setDescription(description);
		bean.setAcronym(acronym);
		
		return bean;
	}
}
