package org.gridchem.service.beans;

import org.gridchem.service.model.enumeration.ResourceStatusType;

public class ResourceBean {

	protected String name;
	protected String hostname;
	protected String comment;
	protected String ipAddress;
	protected SiteBean site;
	protected String website;
	protected ResourceStatusType status;

	public ResourceBean() {
		super();
	}

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
	 * @return the status
	 */
	public ResourceStatusType getStatus() {
	    return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResourceStatusType status) {
	    this.status = status;
	}

	/**
	 * @return Returns the site.
	 */
	public SiteBean getSite() {
	    return site;
	}

//	/**
//	 * @return the resourceProjects
//	 */
//	public HashSet<String> getResourceProjects() {
//	    return resourceProjects;
//	}
//
//	/**
//	 * @param resourceProjects the resourceProjects to set
//	 */
//	public void setResourceProjects(HashSet<String> resourceProjects) {
//	    this.resourceProjects = resourceProjects;
//	}
//
//	public void addResourceProject(String rpName) {
//	    this.resourceProjects.add(rpName);
//	}

	/**
	 * @param site the site to set
	 */
	public void setSite(SiteBean site) {
	    this.site = site;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
	    return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
	    this.website = website;
	}

	public int compareTo(Object o) {
	    if (o instanceof ResourceBean) {
	    	ResourceBean hw = (ResourceBean)o;
	        if (getSite().getName().equals(hw.getSite().getName())) {
	            return getName().compareTo(hw.getName());
	        } else {
	            return getSite().getName().compareTo(hw.getSite().getName());
	        }
	    }
	    return 0;
	}

}