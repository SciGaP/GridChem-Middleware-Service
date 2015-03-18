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

import org.gridchem.service.model.enumeration.UserPermissionType;

/**
 * Many to many mapping class of Project and User.  This class
 * contains one extra component attribute, the access type.
 *
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 * 
 * @see User
 * @see Project
 * 
 */
@SuppressWarnings({ "serial", "unchecked" })
public class UserProject implements Serializable, Comparable {
//  ******************* Begin Inner composite Id class ******************* //
	public static class Id implements Serializable {
		private Long userId;
		private Long projectId;
		
	    public Id() {}

		public Id(Long userId, Long projectId ) {
			this.userId = userId;
			this.projectId = projectId;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id that = (Id)o;
				return this.userId.equals(that.userId) &&
					   this.projectId.equals(that.projectId);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return userId.hashCode() + projectId.hashCode();
		}
	}
	// ******************* End Inner composite Id class ******************* //
    private Id id = new Id();
	private User user;
	private Project project;
	private UserPermissionType userType;
	private Usage usage;
    private StorageResource mss;
    private boolean enabled = true;
    
//	private PersistentSet userProjectResources;
	
	/**
	 * No-arg constructor for JavaBean tools.
	 */
	public UserProject() {}
	
	/**
	 * Minimal constructor;
	 */
	public UserProject(User user,Project project) {
		this.user = user;
		this.project = project;	
		this.id = new Id(user.getId(),project.getId());
	}
	
//	/**
//	 * Full constructor;
//	 */
//	public UserProject(Project project, User user, 
//	        Usage usage, AdminType userType, PersistentSet userProjectResources) {
//		this.user = user;
//		this.project = project;	
//		this.userType = userType;
//		this.userProjectResources = userProjectResources;
//	}

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
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * @param project The project to set.
     */
    public void setProject(Project project) {
        this.project = project;
    }
	
	/**
     * @return the mss
     */
    public StorageResource getMss() {
        return mss;
    }

    /**
     * @param mss the mss to set
     */
    public void setMss(StorageResource mss) {
        this.mss = mss;
    }

    /**
	 * @return user
	 */
	public User getUser() { 
	    return user;
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
	public UserPermissionType getUserType() {
		return this.userType;
	}
	/**
	 * @param projectNameLocal The projectNameLocal to set.
	 */
	public void setUserType(UserPermissionType userType) {
		this.userType = userType;
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
		if (!(o instanceof UserProject)) return false;
		final UserProject userProject = (UserProject) o;
		if (!project.equals(userProject.project)) return false;
		if (!user.equals(userProject.user)) return false;
		return true;
	}
	
	public int compareTo(Object o) {
		// CategorizedProjects are sorted by date
		if (o instanceof UserProject)
			return ((this.project.equals( ((UserProject)o).getProject() ))?1:0);
		return 0;
	}

	public String toString() {
		return  "Project: " + this.project.getName() + ", " +
				"User: '" + this.user.getUsername() + "'" +
				"User Type: '" + this.userType.toString() +  "'" +
                "User Type: '" + this.mss  + "'";
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	// ********************** Business Methods ********************** //

}

