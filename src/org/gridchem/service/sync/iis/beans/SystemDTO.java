/**
 * 
 */
package org.gridchem.service.sync.iis.beans;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.model.enumeration.ResourceStatusType;

/**
 * @author dooley
 *
 */
public class SystemDTO implements TgcdbDTO {
	
	private String id;
	private String tgcdbName;
	private String resourceId;
	private String name ;
	private String status;
	private String localUsername;
    private String site;
    private String type;
    private Load load;
//    private Set<Service> services = new HashSet<Service>();
	private String loginHostname;
	private String gridftpHostname;
    
	public SystemDTO() {}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the localUsername
	 */
	public String getLocalUsername() {
		return localUsername;
	}

	/**
	 * @param localUsername the localUsername to set
	 */
	public void setLocalUsername(String localUsername) {
		this.localUsername = localUsername;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the loads
	 */
	public Load getLoad() {
		return load;
	}

	/**
	 * @param loads the loads to set
	 */
	public void setLoad(Load load) {
		this.load = load;
	}

	/**
	 * @param loginHostname the loginHostname to set
	 */
	public void setLoginHostname(String loginHostname) {
		this.loginHostname = loginHostname;
	}

	/**
	 * @return the loginHostname
	 */
	public String getLoginHostname() {
		return loginHostname;
	}

	/**
	 * @param gridftpHostname the gridftpHostname to set
	 */
	public void setGridftpHostname(String gridftpHostname) {
		this.gridftpHostname = gridftpHostname;
	}

	/**
	 * @return the gridftpHostname
	 */
	public String getGridftpHostname() {
		return gridftpHostname;
	}

//	/**
//	 * @return the services
//	 */
//	public Set<Service> getServices() {
//		return services;
//	}
//
//	/**
//	 * @param services the services to set
//	 */
//	public void setServices(Set<Service> services) {
//		this.services = services;
//	}

	/**
	 * @param tgcdbName the tgcdbName to set
	 */
	public void setTgcdbName(String tgcdbName) {
		this.tgcdbName = tgcdbName;
	}

	/**
	 * @return the tgcdbName
	 */
	public String getTgcdbName() {
		return tgcdbName;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	public ComputeBean toBean() {
		ComputeBean bean = new ComputeBean();
		bean.setName(name);
		bean.setStatus(ResourceStatusType.valueOf(status.toUpperCase()));
		bean.setSystem(resourceId);
		return bean;
		
	}
}
