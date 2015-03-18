/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 24, 2005
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


/**
 * Abstract parent class of all hardware and software resources.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 * 
 * @see HardwareResource
 * @see Software
 * @see ResourceDAO
 * @hibernate.class
 * table="RESOURCE"
 * 
 */
public interface GMSAbstractResource {
	
    /**
     * @return Returns the id.
     * 
     * @hibernate.id
     * column="RESOURCE_ID"
     * unsaved-value="null"
     * generator-class="native"
     */
    public Long getId();
    
    /**
     * Return the descriptive name of the resource.
     * 
     * @return Returns the name.
     * 
     * @hibernate.property
     * column="RESOURCE_NAME"
     * not-null="true"
     * length="64"
     */
    public String getName();
    
    /**
     * Give the resource a descriptive name such as
     * copper, gaussian, or LONI.
     * 
     * @param name The descriptive name of the resource
     */
    public void setName(String name);
    
    /**
     * Get verbal description of a <tt>Resource</tt>.
     * @return Returns the comment.
     * 
     * @hibernate.property
     * column="COMMENT"
     * length="2048"
     */
    public String getComment();
    
    /**
     * Set verbal description of a <tt>Resource</tt>.
     * @param comment The comment to set.
     */
    public void setComment(String comment);
    /**
     * Return the GMSAbstractResource type.  
     * @return Returns the type.
     * 
     * @hibernate.property
     * column="TYPE"
     * length="16"
     * not-null="true"
     */
    public String getType();
    
    /**
     * Set the type of the <tt>GMSAbstractResource</tt>.  
     * 
     * @param type The type to set.
     */
    public void setType(String type);
    
    /**
     * @param Set the userResources
     */
    //public void setUserResources(Set userResources);
    
    /**
     * Convenience class to add a this <tt>GMSHardwareResource</tt> to 
     * another user's VO.
     * 
     * @param userResource
     */
    //public void addUserResource(UserResource userResource);
    
    /**
     * @return Set of UserResources mapping this <tt>GMSHardwareResource</tt>
     * to all possible user VO's.
     * 
     * @hibernate.set
	 * cascade="all-delete-orphan"
	 * lazy="true"
	 * inverse="false"
	 * access="org.gridchem.service.gms.persistence.DirectSetAccessor"
	 * @hibernate.collection-key
	 * column="USERRESOURCE_ID"
	 * not-null="true"
	 * @hibernate.collection-one-to-many
	 * class="org.gridchem.service.model.UserResource"	
     */
    //public Set getUserResources();
}
