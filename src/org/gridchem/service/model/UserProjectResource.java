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

package org.gridchem.service.model;

import java.io.Serializable;
import java.util.Date;

import org.gridchem.service.model.enumeration.UserPermissionType;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class UserProjectResource {
	// ******************* Begin Inner composite Id class ******************* //
	@SuppressWarnings("serial")
	public static class Id implements Serializable {
		private Long resourceId;
		private Long userId;
		private Long projectId;
		private String allocationName;
        
	    public Id() {}

		public Id(Long resourceId, Long userId, Long projectId, String allocationName ) {
			this.resourceId = resourceId;
			this.userId = userId;
			this.projectId = projectId;
			this.allocationName = allocationName;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id that = (Id)o;
				return this.resourceId.equals(that.resourceId) &&
					   this.userId.equals(that.userId) &&
					   this.projectId.equals(that.projectId) &&
					   this.allocationName.equals(that.allocationName);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return resourceId.hashCode() + userId.hashCode() + 
				projectId.hashCode() + allocationName.hashCode();
		}
	}
	// ******************* End Inner composite Id class ******************* //

	private Id id = new Id();
	//private Long id;
	private Date created = new Date();
	//private User user;
	private CCGResource resource;
	private UserProject userProject;
	private String loginName;
	private UserPermissionType localUserType;
    private String allocationName;
	private Usage usage;
	private boolean enabled = true;
	private boolean banned = false;
	
	/**
	 * No-arg constructor for JavaBean tools.
	 */
	public UserProjectResource() {}
	
	
    /**
     * Full constructor
     * 
     * @param user
     * @param projectResource
     * @param loginName
     * @param localUserType
     * @param usage
     */
    public UserProjectResource(CCGResource resource, 
            UserProject userProject, String loginName, 
            UserPermissionType localUserType, String allocationName,
            Usage usage) {
        this.id.userId = userProject.getUser().getId();
        this.id.projectId = userProject.getProject().getId();
        this.id.resourceId = resource.getId();
        this.id.allocationName = allocationName;
        this.resource = resource;
        this.userProject = userProject;
        this.loginName = loginName;
        this.localUserType = localUserType;
        this.allocationName = allocationName;
        this.usage = usage;
    }
    
    
    /**
     * Useful constructor for looking up by example using the UPR DAO
     * @param userId
     * @param projectId
     * @param resourceId
     * @param userName
     */
    public UserProjectResource(Long userId, Long projectId, Long resourceId, String allocationName) {
        this.id = new Id(userId,projectId,resourceId,allocationName);
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
     * @return Returns the localUserType.
     */
    public UserPermissionType getLocalUserType() {
        return localUserType;
    }
    /**
     * @param localUserType The localUserType to set.
     */
    public void setLocalUserType(UserPermissionType localUserType) {
        this.localUserType = localUserType;
    }
    /**
     * @return Returns the loginName.
     */
    public String getLoginName() {
        return loginName;
    }
    /**
     * @param loginName The loginName to set.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    /**
     * @return Returns the userProject.
     */
    public UserProject getUserProject() {
        return userProject;
    }
    /**
     * @param userProject The userProject to set.
     */
    public void setUserProject(UserProject userProject) {
        this.userProject = userProject;
    }
    /**
     * @return Returns the projectResource.
     */
    public CCGResource getResource() {
        return resource;
    }
    /**
     * @param projectResource The projectResource to set.
     */
    public void setResource(CCGResource resource) {
        this.resource = resource;
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
     * @return Returns the banned.
     */
    public boolean isBanned() {
        return banned;
    }
    /**
     * @param banned The banned to set.
     */
    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
	// ********************** Common Methods ********************** //

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserProjectResource)) return false;
		final UserProjectResource userProjectResource = (UserProjectResource) o;
		if (!id.equals(userProjectResource.id)) return false;
		return true;
	}
	
	public int compareTo(Object o) {
		// CategorizedUsers are sorted by date
		if (o instanceof UserProjectResource)
			return getCreated().compareTo( ((UserProjectResource)o).getCreated() );
		return 0;
	}

	public String toString() {
		return  "User: '" + this.userProject.getUser().getUsername() + "', " +
				"Resource: '" + this.resource.getName() + "', " +
				"Project: '" + this.userProject.getProject().getName() + "', " +
                "Allocation Name: '" + getAllocationName() + "', " + 
				"Login Name: '" + getLoginName() + "', " + 
				"Usage: '" + this.getUsage().toString() + "', " +
				"Banned: '" + ((isBanned())?"yes":"no") + "'";
	}

	// ********************** Business Methods ********************** //

}
