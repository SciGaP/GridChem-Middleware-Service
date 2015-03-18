/**
 * 
 */
package org.gridchem.service.provider.teragrid.dao;

import java.util.List;

import org.gridchem.service.beans.SoftwareBean;
import org.gridchem.service.beans.SoftwareInstallationBean;
import org.gridchem.service.model.GMSSession;

/**
 * This class is currently unimplemented due to no interactive software
 * services in the TG.
 * 
 * @author dooley
 *
 */
public class TeraGridSoftwareDao  {

	public TeraGridSoftwareDao(GMSSession session) {
		// TODO Auto-generated constructor stub
	}
	
	public List<SoftwareBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SoftwareInstallationBean> findAllInstallations() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SoftwareInstallationBean> findAllInstallationsForResource(
			String resource) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SoftwareInstallationBean> findAllInstallationsOfSoftware(
			String software) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean userHasAccessToSoftware(String application) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
