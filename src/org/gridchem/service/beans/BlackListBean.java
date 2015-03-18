/**
 * 
 */
package org.gridchem.service.beans;

import java.util.Date;

/**
 * @author dooley
 *
 */
public class BlackListBean {

	private Long id;
	private String username;
	private String software;
	private boolean enabled;
	private Date created;
	private Date lastUpdated;
	
	/**
	 * No-arg constructor
	 */
	public BlackListBean() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

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
		return "User " + username + " banned from using " + software + " on " + created;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof BlackListBean) {
			return ((username.equals(((BlackListBean)obj).username)) && 
				(software.equals(((BlackListBean)obj).software)));
		}
		return false;
	}
}
