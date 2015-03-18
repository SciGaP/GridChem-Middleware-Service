/**
 * 
 */
package org.gridchem.service.beans;

/**
 * @author dooley
 *
 */
public class AllocationBean {
	public Long id;
	public String name;
	public String systemName;
	public String projectId;
	public UsageBean usage;
	public String created;
	public String expires;
	public boolean active;
	
	public AllocationBean() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public UsageBean getUsage() {
		return usage;
	}

	public void setUsage(UsageBean usage) {
		this.usage = usage;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
