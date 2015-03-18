/**
 * 
 */
package org.gridchem.service.authentication;

import java.util.Map;

import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.exceptions.CredentialManagementException;

/**
 * Interface for authenticating to the pluggable providers.
 * 
 * @author dooley
 *
 */
public interface LoginProvider {

	/** 
	 * Abstract method to retrieve a credential using some mechanism 
	 */
	public AuthenticationBean login(String username, String pass, Map<String,String> authMap) throws CredentialManagementException;
	
	
}
