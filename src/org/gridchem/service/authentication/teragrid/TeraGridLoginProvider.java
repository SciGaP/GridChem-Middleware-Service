/**
 * 
 */
package org.gridchem.service.authentication.teragrid;

import java.util.Map;

import org.globus.gsi.gssapi.GlobusGSSCredentialImpl;
import org.gridchem.service.authentication.LoginProvider;
import org.gridchem.service.authentication.ccg.CCGAuthentication;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.CredentialManagementException;
import org.gridchem.service.exceptions.LoginException;
import org.gridchem.service.exceptions.UserException;
import org.gridchem.service.util.SSOUtils;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;
import org.ietf.jgss.GSSCredential;

/**
 * @author dooley
 *
 */
public class TeraGridLoginProvider implements LoginProvider {
//	private static Logger log = Logger.getLogger(TeraGridLoginProvider.class);
	
	private String myproxyServer;
	private int myproxyPort;

	public AuthenticationBean login(String username, String pass, Map<String,String> authMap)
			throws CredentialManagementException, UserException {
		
		if (authMap == null || authMap.isEmpty()) throw new LoginException("Authentication service not specified.");
		
		myproxyServer = Settings.TERAGRID_MYPROXY_SERVER;
		myproxyPort = Settings.TERAGRID_MYPROXY_PORT;
		
		if (!ServiceUtil.isValid(myproxyPort)) {
			myproxyPort = 0;
		} 
		
		if (!ServiceUtil.isValid(username)) throw new LoginException("Bad username/password combination.");
		if (!ServiceUtil.isValid(pass)) throw new LoginException("Bad username/password combination.");
		if (!ServiceUtil.isValid(myproxyServer)) throw new LoginException("No myproxy server specified.");
		
		try {
			if(!username.toLowerCase().equals(username)) {
			    throw new LoginException("Invalid username. Usernames are case sensitive. " + 
                        "Please check your username and try again.");
            }
            
            if (!new UserDao()._isPasswordValid(username,pass)) {
				throw new LoginException("Incorrect password attempt for user " + username + ".");
			}
			
		} catch (LoginException e) {
		    throw e;
        } catch (Exception e){
			throw new LoginException(e);
		}
        
        String myproxyUsername = authMap.get("myproxy.username");
		String myproxyPassword = authMap.get("myproxy.password");
		
		// First we log the user in and generate a temporary sso password for them
		// so we don't store their long term pass anywhere.
		String tempPass = SSOUtils.createSessionPassword(myproxyServer, myproxyPort, myproxyUsername, myproxyPassword);
		
		if (tempPass != null) {
			SSOUtils.authenticateUser(myproxyServer, myproxyPort, myproxyUsername, tempPass);
			// Next we pull a proxy to use on the user's behalf.
//			GSSCredential cred = SSOUtils.getCredFromMyProxy(myproxyServer, myproxyPort, username, tempPass);
			GSSCredential cred = SSOUtils.getCredFromMyProxy(myproxyServer, myproxyPort, myproxyUsername, myproxyPassword);
			if (cred == null) {
				throw new LoginException("Bad username/password combination.");
			} else {
				// lastly we return the AuthenticationBean to the service.
				return new TeraGridAuthentication(cred, username, myproxyUsername, tempPass);
			}
		} else {
			throw new LoginException("Bad username/password combination.");
		}
	}
	
	public static void main(String [] args) {
		String myproxyServer = "myproxy.teragrid.org";
		int myproxyPort = 7512;
		
		String username = "ccguser";
		String pass = "80c#emc0mm";
		
		String tempPass = SSOUtils.createSessionPassword(myproxyServer, myproxyPort, username, pass);
		
		System.out.println("Temp pass is: " + tempPass);
		if (tempPass != null) {
			SSOUtils.authenticateUser(myproxyServer, myproxyPort, username, tempPass);
			GSSCredential cred = SSOUtils.getCredFromMyProxy(myproxyServer, myproxyPort, username, pass);
			
			if (cred == null) {
				System.out.println("error");
			} else {
				new TeraGridAuthentication(cred, username, username, tempPass);
				System.out.println(((GlobusGSSCredentialImpl)cred).getX509Credential().toString());
				//System.out.println(((GlobusGSSCredentialImpl)cred).getGlobusCredential().toString()); //JGlobus-2.0.6 deprecation
				System.out.println("Successfully");
			}
		}		
		
	}

}
