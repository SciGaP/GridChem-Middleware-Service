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

import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.model.enumeration.TransferProtocolType;

/**
 * Concrete class representing a Storage resource such as raid server or tape backup.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class StorageResource extends CCGResource {
	private double total;
	private double free;
	private double seekTime;
	private double rpm;
	private TransferProtocolType protocol = TransferProtocolType.GSIFTP;
	private double quota;
	private boolean purge = false;
	private boolean backup = false;
	private String website;
	
	/**
	 * Default no-arg contructor.
	 */
	public StorageResource() {}
	
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the free
	 */
	public double getFree() {
		return free;
	}

	/**
	 * @param free the free to set
	 */
	public void setFree(double free) {
		this.free = free;
	}

	/**
	 * @return the seekTime
	 */
	public double getSeekTime() {
		return seekTime;
	}

	/**
	 * @param seekTime the seekTime to set
	 */
	public void setSeekTime(double seekTime) {
		this.seekTime = seekTime;
	}

	/**
	 * @return the rpm
	 */
	public double getRpm() {
		return rpm;
	}

	/**
	 * @param rpm the rpm to set
	 */
	public void setRpm(double rpm) {
		this.rpm = rpm;
	}

	/**
	 * @return the access
	 */
	public TransferProtocolType getProtocol() {
		return protocol;
	}

	/**
	 * @param access the access to set
	 */
	public void setProtocol(TransferProtocolType protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the quota
	 */
	public double getQuota() {
		return quota;
	}

	/**
	 * @param quota the quota to set
	 */
	public void setQuota(double quota) {
		this.quota = quota;
	}

	/**
	 * @return the purge
	 */
	public boolean getPurge() {
		return purge;
	}

	/**
	 * @param purge the purge to set
	 */
	public void setPurge(boolean purge) {
		this.purge = purge;
	}

	/**
	 * @return the backup
	 */
	public boolean getBackup() {
		return backup;
	}

	/**
	 * @param backup the backup to set
	 */
	public void setBackup(boolean backup) {
		this.backup = backup;
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


	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ComputeResource)) return false;
		final ComputeResource computeResource = (ComputeResource) o;
		if (!id.equals(computeResource.id)) return false;
		if (!name.equals(computeResource.name)) return false;
		if (!comment.equals(computeResource.comment)) return false;
		if (!getType().equals(computeResource.comment)) return false;
		return true;
	}
	
	public int hashCode() {
		return (new String(this.getId()+
				this.getName())).hashCode();
	}
	
	public String toString() {
	    return getName() + " " + getHostname();
        //		return  "Storage Resource: (\"" + getId().toString() + "\") " +
//				"Name: '" + getName() + "' " +
//				"Comment: '" + getComment() + "' " +
//				"Resource Type: '" + getType().toString() + "' " +
//				"Total disk space: '" + getDiskTotalSpace() + "' " +
//				"Available disk space: '" + getDiskFreeSpace() + "' " +
//				"Hostname: '" + getHostname() + "' " +
//				"IP Address: '" + getIpAddress() + "' " +
//				"Seek Time: '" + getDiskSeekTime() + "' " +
//				"RPM: '" + getDiskRpm() + "' " +
//				"Access: '" + getAccess() + "' " +
//				"Quota: '" + getQuota() + "' " +
//				"Purge: '" + getPurge()+ "' " +
//				"Backup: '" + getBackup() + "' " +
//				"Website: '" + getWebsite() + "' " +
//				"Last Down Time: '" + getLastDownTime().toString() + "'" +
//				"Last Update: '" + getLastUpdated().toString() + "'" +
//				"Created: '" + getCreated().toString() + "'" +
//				"Status: '" + getStatus().toString() + "' " ;
	}
	
	public int compareTo(Object o) {
		if (o instanceof SoftwareInstallation)
			return this.getId().compareTo(
					((ComputeResource)o).getId() );
		return 0;
	}
	
	public StorageBean toBean() {
		StorageBean bean = new StorageBean();
		bean.setName(getName());
		bean.setHostname(hostname);
		bean.setSite(site.toBean());
		bean.setComment(comment);
		bean.setWebsite(getWebsite());
		bean.setProtocol(protocol);
		bean.setTotal(total);
		bean.setFree(free);
		bean.setQuota(quota);
		return bean;
	}

}
