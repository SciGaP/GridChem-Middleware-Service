/**
 * 
 */
package org.gridchem.service.beans;

import java.util.Hashtable;

/**
 * Class to hold information about project collaborators. This class extends the UserBean class and
 * adds in a table of usage data keyed by resource hostnames.
 * 
 * @author dooley
 *
 */
public class CollaboratorBean extends UserBean {


	private UsageBean totalUsage;
	private Hashtable<String,UsageBean> usageTable = new Hashtable<String,UsageBean>();
	
	public CollaboratorBean() {}
	
	public CollaboratorBean(UserBean user) {
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setMiddleInitial(user.getMiddleInitial());
		setUserName(user.getUserName());
		setInstitute(user.getInstitute());
		setDepartment(user.getDepartment());
		setAddress(user.getAddress());
		setPhone(user.getPhone());
		setCell(user.getCell());
		setFax(user.getFax());
		setEmail(user.getEmail());
		setIm(user.getIm());
		setImProvider(user.getImProvider());
		setClassification(user.getClassification());
	}

	/**
	 * @param totalUsage the totalUsage to set
	 */
	public void setTotalUsage(UsageBean totalUsage) {
		this.totalUsage = totalUsage;
	}

	/**
	 * @return the totalUsage
	 */
	public UsageBean getTotalUsage() {
		return totalUsage;
	}

	/**
	 * @param usageTable the usageTable to set
	 */
	public void setUsageTable(Hashtable<String,UsageBean> usageTable) {
		this.usageTable = usageTable;
	}

	/**
	 * @return the usageTable
	 */
	public Hashtable<String,UsageBean> getUsageTable() {
		return usageTable;
	}
	
	public void addUsageRecord(String hostname, UsageBean usageBean) {
		// if the entry already exists, overwrite it
		if (usageTable.keySet().contains(hostname)) {
			this.usageTable.remove(hostname);
		}
		
		usageTable.put(hostname, usageBean);
	}
	
	public void removeUsageRecord(String hostname) {
		usageTable.remove(hostname);
	}
	
	public boolean equals(Object o) {
		if (o instanceof CollaboratorBean) {
			return ((CollaboratorBean) o).getUserName().equals(getUserName());
		} 
		
		return false;
		
	}
	
}
