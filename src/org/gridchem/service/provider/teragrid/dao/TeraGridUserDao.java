/**
 * 
 */
package org.gridchem.service.provider.teragrid.dao;

import org.gridchem.service.beans.UserBean;
import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.exceptions.ProfileValidationException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.sync.iis.beans.Profile;
import org.gridchem.service.wsclients.iis.TeraGridProfileServiceClient;

/**
 * Abstract layer for querying the TG middleware for user
 * specific information.
 * 
 * @author dooley
 *
 */
public class TeraGridUserDao {

//	private GMSSession session;
	/**
	 * 
	 */
	public TeraGridUserDao(GMSSession session) {
//		this.session = session;
	}

	public UserBean findByUsername(String username)
			throws InfrastructureException {
		throw new InfrastructureException("Not implemented.");
	}

	public UserBean findSessionUser() throws InfrastructureException {
		// TODO query $profile for user.username = session.getUser.username
//		User user = new CCGUserDao(session).findSessionUser_internal();
//		return findByUsername(user.getUsername(), user.getPassword());
		throw new InfrastructureException("Not implemented.");
	}

	public void persist(UserBean userBean) throws ProfileValidationException,
			InfrastructureException {
		// TODO unimplmented due to read-only functionality of tg service.
		throw new InfrastructureException("Not implemented.");
	}
	
	public static UserBean findByUsername(String authName, String authPass) {
		// TODO query $profile for user.username = username using given id & pass 
		Profile profile = new TeraGridProfileServiceClient(authName, authPass).getUser();
		
		UserBean bean = profile.toBean();
//		
//		bean.setId((long)profile.getId());
//		bean.setUserName(profile.getUsername());
//		bean.setClassification(profile.getPosition());
//		bean.setDepartment(profile.getDepartment());
//		bean.setFirstName(profile.getFirstName());
//		bean.setMiddleInitial((profile.getMiddleName()==null)?((profile.getMiddleName().length()>1)?profile.getMiddleName().substring(0,1):profile.getMiddleName()):"");
//		bean.setLastName(profile.getLastName());
//		bean.setInstitute(profile.getOrganization());
//		bean.setDepartment(profile.getDepartment());
//		bean.setEmail(profile.getEmail());
//		if (profile.getAddress() != null) {
//			bean.setAddress(profile.getAddress().clone());
//		} else {
//			bean.setAddress(new Address());
//		}
//		bean.setPhone(profile.getBusPhoneNumber());
//		bean.setFax(profile.getFaxNumber());
		return bean;
	}

}
