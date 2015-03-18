/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 11, 2005
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
 * 3. Neither the resourceStatuss of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the resourceStatuss of its contributors 
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

import java.util.Date;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.Address;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.model.enumeration.IMProviderType;
import org.gridchem.service.model.enumeration.UserClassificationType;
import org.gridchem.service.model.enumeration.UserPermissionType;


/**
 * This is the CCG user class.  From this class we peform many static user operations
 * dependent on the persistence system.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 * @see UserDAO
 */
public class User {
	public static Logger log = Logger.getLogger(User.class.getName());
	
	private Long id = null;
    private String username;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String department;
    private String institution;
    private String phone = "none provided";
    private String cell = "none provided";
    private String fax = "none provided";
    private Address address;
    private String email = "none provided";
    private String im = "none provided";
    private String comment = "none";
    private String originalPassword;
    private String password;
    private Date lastUpdated;
    private UserPermissionType permission = UserPermissionType.USER;
    private UserClassificationType classification = UserClassificationType.OTHER;
    
    // Fields only used for registration and never again
    private String appsRequested = "none";
    private int susPerJob = 0;
    private int memoryMBPerCPU = 0;
    private int diskGBPerJob = 0;
    private int processorsPerJob = 0;
    
    private Date created = new Date();
    
    /**
	 * No-arg constructor for JavaBean tools.
	 */
    public User() {}
   
    public User(UserBean bean) {
    	this();
    	
    	this.id = bean.getId();
    	this.username = bean.getUserName();
        this.firstName = bean.getFirstName();
        this.middleInitial = bean.getMiddleInitial();
        this.lastName = bean.getLastName();
        this.department = bean.getDepartment();
        this.institution = bean.getInstitute();
        this.phone = bean.getPhone();
        this.cell = bean.getCell();
        this.fax = bean.getFax();
        this.address = bean.getAddress();
        this.email = bean.getEmail();
        this.im = bean.getIm();
        this.permission = UserPermissionType.valueOf(bean.getPermission());
        this.classification = UserClassificationType.valueOf(bean.getClassification());
        
    }
    
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleInitial() {
		return middleInitial;
	}


	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getInstitution() {
		return institution;
	}


	public void setInstitution(String institution) {
		this.institution = institution;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCell() {
		return cell;
	}


	public void setCell(String cell) {
		this.cell = cell;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIm() {
		return im;
	}


	public void setIm(String im) {
		this.im = im;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getOriginalPassword() {
		return originalPassword;
	}


	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserPermissionType getPermission() {
		return permission;
	}


	public void setPermission(UserPermissionType permission) {
		this.permission = permission;
	}


	public UserClassificationType getClassification() {
		return classification;
	}


	public void setClassification(UserClassificationType classification) {
		this.classification = classification;
	}


	public String getAppsRequested() {
		return appsRequested;
	}


	public void setAppsRequested(String appsRequested) {
		this.appsRequested = appsRequested;
	}


	public int getSusPerJob() {
		return susPerJob;
	}


	public void setSusPerJob(int susPerJob) {
		this.susPerJob = susPerJob;
	}


	public int getMemoryMBPerCPU() {
		return memoryMBPerCPU;
	}


	public void setMemoryMBPerCPU(int memoryMBPerCPU) {
		this.memoryMBPerCPU = memoryMBPerCPU;
	}


	public int getDiskGBPerJob() {
		return diskGBPerJob;
	}


	public void setDiskGBPerJob(int diskGBPerJob) {
		this.diskGBPerJob = diskGBPerJob;
	}


	public int getProcessorsPerJob() {
		return processorsPerJob;
	}


	public void setProcessorsPerJob(int processorsPerJob) {
		this.processorsPerJob = processorsPerJob;
	}


	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		final User user = (User) o;
		if (!username.equals(user.getUsername())) return false;
		if (!password.equals(user.getPassword())) return false;
		return true;
	}
	
	public int hashCode() {
		return (getUsername() + getPassword()).hashCode();
	}
	
	public String toString() {
		return  "User (" + getId().toString()  + "), " +
				"First Name: '" + getFirstName() + "' " +
				"Last Name: '" + getLastName() + "' " +
				"Password: '" + getPassword() + "' " +
				"Username: '" + getUsername() + "' " +
				"Address: '" + getAddress().toString() + "' " +
				"Email: '" + getEmail() + "' " +
				"Comments: '" + getComment() + "' " +
				"Permission Type: '" + getPermission() + "' " +
				"Institute: '" + getInstitution() + "' " +
				"Department: '" + getDepartment() + "' " +
				"Phone: " + getPhone() + " " +
				"Fax: " + getFax() + " " +
				"SU's per job: " + getSusPerJob() + " " +
				"Disk GB per job: " + getDiskGBPerJob() + " " +
				"Procs per Job: " + getProcessorsPerJob() + " " +
				"Memory MB per job: " + getMemoryMBPerCPU();
	}
	
	public int compareTo(Object o) {
		if (o instanceof User)
			return this.getCreated().compareTo( ((User)o).getCreated() );
		return 0;
	}	
	
	public UserBean toBean() {
		UserBean bean = new UserBean();
		bean.setImProvider(IMProviderType.MSN.name());
		bean.setIm(getIm());
		bean.setEmail(getEmail());
		bean.setFax(getFax());
		bean.setCell(getCell());
		bean.setPhone(getPhone());
		bean.setAddress(getAddress());
		bean.setDepartment(getDepartment());
		bean.setInstitute(getInstitution());
		bean.setClassification(getClassification().name());
		bean.setPermission(getPermission().name());
		bean.setUserName(getUsername());
		bean.setMiddleInitial(getMiddleInitial());
		bean.setLastName(getLastName());
		bean.setFirstName(getFirstName());
		bean.setId(getId());
		
		return bean;
	}
    
}