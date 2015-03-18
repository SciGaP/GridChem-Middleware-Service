/**
 * 
 */
package org.gridchem.service.authentication.myproxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.globus.gsi.GlobusCredential;
import org.globus.gsi.X509Credential;
import org.globus.gsi.gssapi.GlobusGSSCredentialImpl;
import org.globus.util.Util;
import org.gridchem.service.beans.AuthenticationBean;
import org.gridchem.service.exceptions.CredentialManagementException;
import org.ietf.jgss.GSSCredential;

/**
 * @author dooley
 *
 */
public class MyProxyAuthentication extends AuthenticationBean {
//	private final Logger log = Logger.getLogger(TeraGridAuthentication.class);
    
	public MyProxyAuthentication() {}

	public MyProxyAuthentication(GSSCredential credential, String username, 
			String myproxyUsername, String myproxyPassword) throws CredentialManagementException {
		super();
		
		this.username = username;
		this.myproxyUsername = myproxyUsername;
		this.myproxyPassword = myproxyPassword;
		
		setCredential(((GlobusGSSCredentialImpl)credential).getX509Credential());
		//setCredential(((GlobusGSSCredentialImpl)credential).getGlobusCredential()); //JGlobus2.0.6 deprecatoin
		
		
	}
	
	@Override
	//public void setCredential(GlobusCredential credential) throws CredentialManagementException {
	public void setCredential(X509Credential credential) throws CredentialManagementException {
		// The CGI used to submit jobs is present on the same machine
		// We write the proxy to disk for the CGI, while keeping a copy
		// in memory for all GMS_WS related tasks.  This is valid 
		// because the CGI and GMS_WS are running on the same machine.
		String proxyFile = File.separator + "tmp" + File.separator + 
          		getUserName() + "_ext_x509";
          
		Util.setFilePermissions(proxyFile, 755);
		try {
			OutputStream out = new FileOutputStream(proxyFile);
	        credential.save(out);
	        out.close();
		
		Process pr = Runtime.getRuntime().exec("chmod 600 " + proxyFile);

		} catch (Exception e) {
			throw new CredentialManagementException("Failed to save credential to disk",e);
		}
	}

}
