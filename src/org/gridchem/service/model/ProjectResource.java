/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 27, 2005
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

import java.io.Serializable;
import java.util.Date;

/**
 * Many to many join class of Project and CCGResource.  This class
 * contains usage information and the project name in addition to
 * references to the project and resource objects themselves.  Another 
 * useful function of this object is the bag of userProjectResources.
 * Given an instance of a single project and resource, we can query 
 * for this object, and automatically get the set of all resources 
 * corresponding to that combination.  This is very useful when 
 * building the user's VO.
 *
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 * 
 * @see CCGResource
 * @see Project
 * 
 */
@SuppressWarnings({ "serial", "unchecked" })
public class ProjectResource implements Serializable, Comparable {
    // ******************* Begin Inner composite Id class ******************* //
	public static class Id implements Serializable {
		private Long resourceId;
		private Long projectId;
        private String allocationName;
		
	    public Id() {}

		public Id(Long projectId, Long resourceId, String allocationName ) {
			this.resourceId = resourceId;
			this.projectId = projectId;
            this.allocationName = allocationName;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id that = (Id)o;
				return this.resourceId.equals(that.resourceId) &&
					   this.projectId.equals(that.projectId) && 
                       this.allocationName.equals(that.allocationName);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return resourceId.hashCode() + projectId.hashCode() + allocationName.hashCode();
		}
	}
	// ******************* End Inner composite Id class ******************* //

	private Id id;
	private Project project;
	private CCGResource resource;
	private String allocationName;
	private Usage usage;
    private boolean enabled;
    private Date startDate = new Date();
    private Date endDate = new Date();
	
	/**
	 * No-arg constructor for JavaBean tools.
	 */
	public ProjectResource() {}
	
	public ProjectResource(Project project, CCGResource resource,
	        String allocationName) {
		this.id = new Id(project.getId(), resource.getId(), allocationName);
		this.resource = resource;
		this.project = project;	
		this.allocationName = allocationName;
        this.enabled = false;
		this.usage = new Usage();
	}
	
	/**
	 * Full constructor;
	 */
	public ProjectResource(Project project, CCGResource resource,
	        String allocationName, boolean enabled, Usage usage) {
		this.id = new Id(project.getId(), resource.getId(), allocationName);
		this.resource = resource;
		this.project = project;	
		this.allocationName = allocationName;
        this.enabled = enabled;
		this.usage = usage;
	}

	// ********************** Accessor Methods ********************** //

	/**
	 * @return Returns the id.
	 */
	public Id getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
	}
	/**
	 * @return Returns the resource.
	 */
	public CCGResource getResource() {
		return resource;
	}
	/**
	 * @param resource The resource to set.
	 */
	public void setResource(CCGResource resource) {
		this.resource = resource;
	}

    /**
     * @param project The project to set.
     */
    public void setProject(Project project) {
        this.project = project;
    }
	/**
	 * @return project
	 */
	public Project getProject() { 
	    return this.project; 
	}
	/**
	 * @return Returns the projectNameLocal.
	 */
	public String getAllocationName() {
		return this.allocationName;
	}
	/**
	 * @param projectNameLocal The projectNameLocal to set.
	 */
	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}
    /**
     * @return Returns the usage.
     */
    public Usage getUsage() {
        return usage;
    }
    /**
     * @param usage The usage to set.
     */
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    /**
     * @return the valid
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param valid the valid to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    
//    public boolean isValid() {
//        return ((valid == 0)?false:true);
//    }
//    /**
//     * @return Returns the userProjectResources.
//     */
//    public PersistentSet getUserProjectResources() {
//        return userProjectResources;
//    }
//    /**
//     * @param userProjectResources The userProjectResources to set.
//     */
//    public void setUserProjectResources(PersistentSet userProjectResources) {
//        this.userProjectResources = userProjectResources;
//    }
	// ********************** Common Methods ********************** //

    public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProjectResource)) return false;
		final ProjectResource projectResource = (ProjectResource) o;
		if (!project.equals(projectResource.project)) return false;
		if (!resource.equals(projectResource.resource)) return false;
        if (!allocationName.equals(projectResource.allocationName)) return false;
		return true;
	}
	
	public int compareTo(Object o) {
		// CategorizedProjects are sorted by date
		if (o instanceof ProjectResource)
			return ((this.project.equals( ((ProjectResource)o).getProject() ))?1:0);
		return 0;
	}

	public String toString() {
		return  "Project: '" + this.project.getName() + "', " + 
                "Resource: '" + this.resource.getName() + "'" +
                "Allocation Name: '" + this.allocationName + "', " +
                this.usage.toString();
	}

	// ********************** Business Methods ********************** //

}

