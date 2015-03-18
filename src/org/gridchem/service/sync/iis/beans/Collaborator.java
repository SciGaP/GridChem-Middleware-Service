/**
 * 
 */
package org.gridchem.service.sync.iis.beans;

import org.gridchem.service.beans.UserBean;


/**
 * @author dooley
 *
 */
public class Collaborator extends Profile {

	private String projectName;
	
	public Collaborator(){}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}
	
	public UserBean toBean() {
		return super.toBean();
	}
		
}
