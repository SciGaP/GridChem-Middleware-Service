/**
 * 
 */
package org.gridchem.service.authentication.myproxy;

import java.util.Map;

import org.gridchem.service.authentication.LoginProvider;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.exceptions.CredentialManagementException;
import org.gridchem.service.exceptions.LoginException;
import org.gridchem.service.util.SSOUtils;
import org.gridchem.service.util.ServiceUtil;
import org.ietf.jgss.GSSCredential;

/**
 * @author dooley
 *
 */
public class MyProxyLoginProvider implements LoginProvider {
//	private static Logger log = Logger.getLogger(TeraGridLoginProvider.class);
	private String myproxyUsername;
	private String myproxyPassword;
	private String myproxyServer;
	private int myproxyPort;

	public AuthenticationBean login(String username, String pass, Map<String,String> authMap)
			throws CredentialManagementException {
		
		if (authMap == null || authMap.isEmpty()) throw new LoginException("Authentication service not specified.");
		
		myproxyUsername = authMap.get("myproxy.username");
		myproxyPassword = authMap.get("myproxy.password");
		myproxyServer = authMap.get("myproxy.server");
		String port = authMap.get("myproxy.port");
		
		if (!ServiceUtil.isValid(port)) {
			myproxyPort = 0;
		} else {
			myproxyPort = Integer.valueOf(port).intValue();
		}
		
		if (!ServiceUtil.isValid(myproxyUsername)) throw new LoginException("Bad username/password combination.");
		if (!ServiceUtil.isValid(myproxyPassword)) throw new LoginException("Bad username/password combination.");
		if (!ServiceUtil.isValid(myproxyServer)) throw new LoginException("No myproxy server specified.");
		
		// First we log the user in and generate a temporary sso password for them
		// so we don't store their long term pass anywhere.
		String tempPass = SSOUtils.createSessionPassword(myproxyServer, myproxyPort, myproxyUsername, myproxyPassword);
		
		if (tempPass != null) {
			
			// Next we pull a proxy to use on the user's behalf.
			GSSCredential cred = SSOUtils.getCredFromMyProxy(myproxyServer, myproxyPort, username, tempPass);
			if (cred == null) {
				throw new CredentialManagementException("Bad username/password combination.");
			} else {
				// lastly we return the AuthenticationBean to the service.
				return new MyProxyAuthentication(cred, username, username, tempPass);
			}
		} else {
			throw new CredentialManagementException("Bad username/password combination.");
		}
	}
	
	 

}
