/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Mar 24, 2006
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

package org.gridchem.service.beans;

/**
 * This is the User class for the data transfer object (DTO) package. It serves
 * as a container for the data from the user class without all the linked objects
 * created by Hibernate.  This object will be serialized and passed as part of
 * a VO object upon successful login using xstream.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 * @see org.gridchem.service.gms.model.user.User
 * @see VO
 */
public class UserBean {
	private Long id;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String userName;
    private String institute;
    private String department;
    private Address address;
    private String phone;
    private String cell;
    private String fax;
    private String email;
    private String im;
    private String imProvider;
    private String classification;
    private String permission;
    
    /**
	 * No-arg constructor for JavaBean tools.
	 */
    public UserBean() {}
        
    //  ********************** Getters and Setters ********************** //
    
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	} 
    /**
     * @return Returns the address.
     */
    public Address getAddress() {
        return address;
    }
    /**
     * @param address The address to set.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    /**
     * @return Returns the department.
     */
    public String getDepartment() {
        return department;
    }
    /**
     * @param department The department to set.
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return Returns the fax.
     */
    public String getFax() {
        return fax;
    }
    /**
     * @param fax The fax to set.
     */
    public void setFax(String fax) {
        this.fax = fax;
    }
    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return Returns the institute.
     */
    public String getInstitute() {
        return institute;
    }
    /**
     * @param institute The institute to set.
     */
    public void setInstitute(String institute) {
        this.institute = institute;
    }
    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return Returns the middleInitial.
     */
    public String getMiddleInitial() {
        return middleInitial;
    }
    /**
     * @param middleInitial The middleInitial to set.
     */
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }
    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getImProvider() {
		return imProvider;
	}

	public void setImProvider(String imProvider) {
		this.imProvider = imProvider;
	}

	/**
	 * @return the cell
	 */
	public String getCell() {
		return cell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}

	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	public String toString() {
        return "User: " + getUserName() + "\n" + 
        		getFirstName() + " " + getMiddleInitial() + " " + getLastName() + "\n" +
        		"A " + getAddress() + "\n" + 
        		"P " + getPhone() + "\n" + 
        		"F " + getFax() + "\n" + 
        		"E " + getEmail() + "\n";   		
    }
    
}
