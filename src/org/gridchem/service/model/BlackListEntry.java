/**
 * 
 */
package org.gridchem.service.model;

import java.io.Serializable;
import java.util.Date;

import org.gridchem.service.beans.BlackListBean;

/**
 * @author dooley
 *
 */
public class BlackListEntry {
//  ******************* Begin Inner composite Id class ******************* //
	@SuppressWarnings("serial")
	public static class Id implements Serializable {
		private Long userId;
		private Long softwareId;
		
	    public Id() {}

		public Id(Long userId, Long softwareId ) {
			this.userId = userId;
			this.softwareId = softwareId;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id that = (Id)o;
				return this.userId.equals(that.userId) &&
					   this.softwareId.equals(that.softwareId);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return userId.hashCode() + softwareId.hashCode();
		}
	}
	// ******************* End Inner composite Id class ******************* //
    private Id id = new Id();
	private User user;
	private Software software;
//	private ComputeResource system;
	private boolean enabled;
	private Date created = new Date();
	private Date lastUpdated;
	
	public BlackListEntry() {}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

//	public ComputeResource getSystem() {
//		return system;
//	}
//
//	public void setSystem(ComputeResource system) {
//		this.system = system;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String toString() {
		return "User " + user.getUsername() + " banned from using " + software.getName() + " on " + created;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof BlackListEntry) {
			return ((user.equals(((BlackListEntry)obj).user)) && 
				(software.equals(((BlackListEntry)obj).software)));
		}
		return false;
	}
	
	public BlackListBean toBean() {
		BlackListBean bean = new BlackListBean();
		bean.setUsername(user.getUsername());
		bean.setSoftware(software.getName());
		bean.setEnabled(enabled);
		bean.setLastUpdated(lastUpdated);
		bean.setCreated(created);
		
		return bean;
	}
}
