/**
 * 
 */
package org.gridchem.service.sync.iis.beans;

import org.gridchem.service.beans.UserBean;
import org.gridchem.service.model.enumeration.UserClassificationType;


/**
 * @author dooley
 *
 */
public class Profile {

	private int id;
	private String username;
	private String firstName;
	private String middleName;
	private String lastName;
	private String organization;
	private String department;
	private String position;
	private String email;
	private Address address;
	private String homePhoneNumber;
	private String homePhoneExtension;
	private String busPhoneNumber;
	private String busPhoneExtension;
	private String faxNumber;
	
	public Profile(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getHomePhoneExtension() {
		return homePhoneExtension;
	}

	public void setHomePhoneExtension(String homePhoneExtension) {
		this.homePhoneExtension = homePhoneExtension;
	}

	public String getBusPhoneNumber() {
		return busPhoneNumber;
	}

	public void setBusPhoneNumber(String busPhoneNumber) {
		this.busPhoneNumber = busPhoneNumber;
	}

	public String getBusPhoneExtension() {
		return busPhoneExtension;
	}

	public void setBusPhoneExtension(String busPhoneExtension) {
		this.busPhoneExtension = busPhoneExtension;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
	public String toString() {
		return quote(id) + "," + quote(username) + "," +
				quote(firstName) + "," + quote(middleName) + "," +
				quote(lastName) + "," + quote(email) + "," +
				quote(address) + "," + quote(busPhoneNumber) + "," +
				quote(busPhoneExtension) + "," + quote(faxNumber) + "," +
				quote(organization) + "," + quote(department) + "," +
				quote(position);
	}
	
	private String quote(Object o) {
		return "\"" + (o == null?o:o.toString()) + "\"";
	}
	
	public UserBean toBean() {
		UserBean bean = new UserBean();
		bean.setUserName(username);
        bean.setFirstName(firstName);
        bean.setLastName(lastName);
        bean.setMiddleInitial(middleName);
        bean.setDepartment(department);
        bean.setInstitute(organization);
        bean.setPhone(busPhoneNumber);
        bean.setFax(faxNumber);
        bean.setAddress(address.toBean());
        bean.setEmail(email);
        bean.setClassification(UserClassificationType.OTHER.name());
        return bean;
	}
}
