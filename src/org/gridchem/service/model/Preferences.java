/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Feb 6, 2006
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.gridchem.service.util.ServiceUtil;


/**
 * Concrete preferences implementation. Each user has a single
 * preference for their account which follows them from machine
 * to machine.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
@SuppressWarnings("unchecked")
public class Preferences {
	private Long id;
	private User user;
	private String preferences;
	private Map preferencesMap = new HashMap();
	private Date lastUpdated = new Date();
	private Date created = new Date();
	
	/**
	 * Default no-arg constructor
	 */
	public Preferences() {}

	/**
	 * Full preference constructor
	 * 
	 * @param user
	 * @param macAddress
	 * @param preferences
	 */
	public Preferences(User user, Map preferencesMap) {
		this.user = user;
		this.preferencesMap = preferencesMap;
	}
	
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
	 * @return
	 */
	public Map getPreferencesMap() {
		return preferencesMap;
	}
	
	/**
	 * @param preferencesMap
	 */
	public void setPreferencesMap(Map preferencesMap) {
		this.preferencesMap = preferencesMap;
		this.preferences = ServiceUtil.xstream.toXML(preferencesMap);
	}
	
	/**
	 * @return Returns the preferences.
	 */
	public String getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences The preferences to set.
	 */
	public void setPreferences(String preferences) {
		this.preferencesMap = (Map)ServiceUtil.xstream.fromXML(preferences);
		this.preferences = preferences;
	}
	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user The user to set.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return Returns the lastUpdated.
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdated The lastUpdated to set.
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    /**
     * @return Returns the created.
     */
    public Date getCreated() {
        return created;
    }
    /**
     * @param created The created to set.
     */
    public void setCreated(Date created) {
        this.created = created;
    }
//	 ********************** Common Methods ********************** //
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Preferences)) return false;
		final Preferences prefs = (Preferences) o;
		if (!preferences.equals(prefs.getPreferences())) return false;
		return true;
	}
	
	public int hashCode() {
		return preferencesMap.hashCode();
	}
	
	public String toString() {
		return  "Preferences (" + getId().toString()  + "), " +
				"Preferences: " + getPreferences();
	}

}
