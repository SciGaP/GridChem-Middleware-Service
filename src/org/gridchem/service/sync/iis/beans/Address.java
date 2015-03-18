package org.gridchem.service.sync.iis.beans;


public class Address implements TgcdbDTO {
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	public Address() {}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zip) {
		this.zipcode = zip;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
		return "\t\t\"address\" : {\n" +
			"\t\t\t\"street1 : " + getStreet1() +  "," +
			"\t\t\t\"street2 : " + getStreet2() + "," +
			"\t\t\t\"city : " + getCity() + "," +
			"\t\t\t\"state : " + getState() + "," +
			"\t\t\t\"zip : " + getZipcode() +
			"\t\t}" + ",";
	}
	
	public org.gridchem.service.beans.Address clone() {
		org.gridchem.service.beans.Address address = new org.gridchem.service.beans.Address();
		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipcode);
		address.setCountry(country);
		
		return address;
	}
	
	public org.gridchem.service.beans.Address toBean() {
		return this.clone();
	}
	
	
}
