/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 18, 2005
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

import java.io.Serializable;
/**
 * Address class used for contact information on a <tt>User</tt>.
 * Each instance of a user will have one address tied to it.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 * @see User
 */
@SuppressWarnings("serial")
public class Address implements Serializable {
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    
    /**
	 * No-arg constructor for JavaBean tools.
	 */
	public Address() {}
    
	/**
	 * Full Constructor
	 * 
	 * @param street First line of street address
	 * @param city city
	 * @param st state
	 * @param zip zip code
	 * @param uid 
	 */
    public Address(String street1,
    				  String street2,
            		  String city, 
            		  String state,
            		  String zip,
				  String country)
    {
        this.street1			= street1;
        this.street2			= street2;
        this.city			= city;
        this.state			= state;
        this.zipCode			= zip;
        this.country			= country;
        
    }
    
	// ********************** Accessor Methods ********************** //

	/**
	 * @return The street address
	 */
	public String getStreet1() { return street1; }
	public void setStreet1(String street1) { this.street1 = street1; }

	/**
	 * @return The street address
	 */
	public String getStreet2() { return street2; }
	public void setStreet2(String street2) { this.street2 = street2; }

	/**
	 * @return The city
	 */
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	
	/**
	 * @return The street address
	 */
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }
	
	/**
	 * @return The street address
	 */
	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipcode) { this.zipCode = zipcode; }


	/**
	 * @return The country address
	 */
	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }

	// ********************** Common Methods ********************** //

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;

		final Address address = (Address) o;

		if (!street1.equals(address.street1)) return false;
		if (!street2.equals(address.street2)) return false;
		if (!city.equals(address.city)) return false;
		if (!state.equals(address.state)) return false;
		if (!zipCode.equals(address.zipCode)) return false;
		if (!country.equals(address.country)) return false;
		return true;
	}

	public int hashCode() {
		int result;
		result = street1.hashCode();
		result = 29 * result + zipCode.hashCode();
		result = 29 * result + city.hashCode();
		return result;
	}

	public String toString() {
		return  getStreet1() + " " + getStreet2() + " " +
				getCity() + " " + getState() + " " + 
				getZipCode() + " " + getCountry();
	}

	// ********************** Business Methods ********************** //
}