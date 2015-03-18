/**
 * 
 */
package org.gridchem.service.authentication.ccg;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
//Jayeeta added following two imports
import java.util.Calendar;
import java.util.Properties;
import java.text.SimpleDateFormat;

import javax.net.ssl.SSLHandshakeException;

import org.apache.log4j.Logger;
import org.globus.common.CoGProperties;
//import org.globus.gsi.GlobusCredential;
import org.globus.gsi.X509Credential;
import org.globus.gsi.gssapi.GlobusGSSCredentialImpl;
import org.globus.myproxy.MyProxy;
import org.globus.myproxy.MyProxyException;
import org.gridchem.service.beans.AuthenticationBean;
//Jayeeta added UserBean
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.CredentialManagementException;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.util.Settings;
import org.ietf.jgss.GSSCredential;

/**
 * @author dooley
 *
 */
public class CCGAuthentication extends AuthenticationBean {
	private final Logger log = Logger.getLogger(CCGAuthentication.class);
	
	protected final static String LOGIN = "auth_myproxy.cgi";
        public static final String X509_CERT_DIR = "X509_CERT_DIR";
	
	public CCGAuthentication(String username) {

		//System.out.println("The server port user pass"+Settings.CCG_MYPROXY_SERVER+Settings.CCG_MYPROXY_PORT+Settings.COMMUNITY_USERNAME+Settings.COMMUNITY_PASSWORD);

		this.myproxyHost = Settings.CCG_MYPROXY_SERVER;
		this.myproxyPort = Settings.CCG_MYPROXY_PORT;
		this.myproxyUsername = Settings.COMMUNITY_USERNAME;
		this.myproxyPassword = Settings.COMMUNITY_PASSWORD;
		this.username = username;
		
		this.credential = retrieveCommunityCredential(username);
		
	}


	//private GlobusCredential retrieveCommunityCredential(String username) {
	private X509Credential retrieveCommunityCredential(String username) {
		
			try {
                                new Properties().setProperty("X509_CERT_DIR", "/etc/grid-security/certificates/");
                
				//GlobusCredential cred = getMyproxyDelegation(username,
				X509Credential cred = getMyproxyDelegation(username,
                		myproxyUsername,
                		myproxyPassword,
						myproxyHost,
						myproxyPort, 
                        AccessType.COMMUNITY);
                
//                stageRemoteCredential(myproxyUsername, myproxyPassword,
//                		username);
                
                return cred;
                
            } catch (CredentialManagementException e) {
                throw e;
            } catch(Exception e) {
				throw new CredentialManagementException("Community Credential Error",e);
			}
		
	}
	
	//Jayeeta modified following method inorder to have SAML enabled community credential

	//protected GlobusCredential getMyproxyDelegation(String userName,
	protected X509Credential getMyproxyDelegation(String userName,
			String myproxyUname, String myproxyPassword, 
			String myproxyHost, int myproxyPort, AccessType accessType) {
	      

		//GlobusCredential credential = null;
		X509Credential credential = null;
		MyProxy myproxy = new MyProxy();
	      
		// community cert is always stored in the ccg myproxy server
		myproxy.setHost(myproxyHost);
		myproxy.setPort(myproxyPort);
	      
		GSSCredential cred;
		//X509Credential cred;
	      
		String DATE_FORMAT_NOW = "yyyy-MM-dd'T'HH:mm:ssZ";

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String authInstant = sdf.format(cal.getTime());

		UserDao ud = new UserDao();
		UserBean ub = ud.get(userName);
		String userEmail=ub.getEmail();
		
		System.out.println(userEmail);

		try {
			// default lifetime is 360 hours
			int lifetime = 360 * 3600;
			
			cred = myproxy.get(myproxyUname,myproxyPassword,lifetime);
			
			credential = 
				((GlobusGSSCredentialImpl)cred).getX509Credential();
				//((GlobusGSSCredentialImpl)cred).getGlobusCredential(); //Jglobus 2.06 deprecation
		     	
            // The CGI used to submit jobs is present on the same machine
			// We write the proxy to disk for the CGI, while keeping a copy
			// in memory for all GMS_WS related tasks.  This is valid 
			// because the CGI and GMS_WS are running on the same machine.
			String proxyFile = File.separator + "tmp" + File.separator + 
	          		userName + ((accessType.equals(AccessType.COMMUNITY))?"CCG":"_ext") + 
	          		"_X509";
              
			OutputStream out = new FileOutputStream(proxyFile);
	          
			org.globus.util.Util.setFilePermissions(proxyFile, 755);
			
			credential.save(out);
              
			//out.close();
              
			/*String myCommand = "/home/ccguser/gridshib-saml-tools-0_5_0/bin/gridshib-saml-issuer --debug --user " + userName + " --sender-vouches --authn --authnMethod urn:oasis:names:tc:SAML:1.0:am:password --authnInstant " + authInstant + " --config /home/ccguser/gridshib-saml-tools-0_5_0/etc/tg-gateway-config.properties --properties certPath=" + proxyFile + " keyPath=" + proxyFile + " Attribute.mail.Name=urn:oid:0.9.2342.19200300.100.1.3 Attribute.mail.Value=" + userEmail + " --x509Lifetime 1296000 --x509 --outfile " + proxyFile ;
			System.out.println("myCommand = ");
			System.out.println(myCommand);
			Runtime.getRuntime().exec(myCommand);*/
			org.globus.util.Util.setFilePermissions(proxyFile, 600);
			/*credential = new GlobusCredential(proxyFile);
			credential.save(out);*/
			out.close();
			return credential;
			
      	} catch (MyProxyException e) {
	      	String message = ""; 
	      	if (e.getMessage().indexOf("Unknown CA") != -1) {
	              message = "MyProxy server \"" + 
	              		Settings.CCG_MYPROXY_SERVER +
	              		"\" does trust the signer of your certificate. " +
	              		"Please check your certificate or try another MyProxy server.\n";
	      	}
	      	throw new CredentialManagementException(
		          		message + e.getMessage());
      	} catch(Exception e) {
	          throw new CredentialManagementException(
	          		"Error saving credentials to local machine.",e);
      	}
	}
	
	/**
     * Stages community credential on CGI server.
     * 
     * @param myproxyUserName
     * @param password
     * @param username
     */
    protected void stageRemoteCredential(String myproxyUserName, String password,
            String username) {
        try {
            URL authURL  = new URL(Settings.CCG_CGI_SERVER + LOGIN);
            URLConnection connection = authURL.openConnection();
            connection.setDoOutput(true);
            
            // open writer
            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            String urlName = URLEncoder.encode(myproxyUserName, "UTF-8");
            writer.println("Rem_User=" + urlName);
            String urlPass = URLEncoder.encode(password, "UTF-8");
            writer.println("Rem_User_Paswd=" + urlPass);
            String urlGridChemName = URLEncoder.encode(username, "UTF-8");
            writer.println("GridChem_Rem_User=" + urlGridChemName);
            String urlGridChem = URLEncoder.encode("true", "UTF-8");
            writer.println("IsGridChem=" + urlGridChem);
            
            // close writer
            writer.close();
           
            readCGIResponse(connection);
            
//            log.info("Successfully staged credential on CGI server.");
            
        } catch (CredentialManagementException e) {
            throw e;
        } catch (SSLHandshakeException e) {
            throw new CredentialManagementException("Invalid CGI server certificate.",e);
        } catch (UnknownHostException e) {  
            throw new CredentialManagementException("Unknown CGI server: " + Settings.CCG_CGI_SERVER,e);
        } catch (Exception e) {
            throw new CredentialManagementException("Error loading credential on remote machine.");
        }

    }
    
    /**
     * Read the response from attempting to stage a credential on the CGI server.
     * 
     * @param connection
     * @throws Exception
     */
    @SuppressWarnings("unused")
    protected void readCGIResponse(URLConnection connection) throws Exception {

        String serversReply = "";
		int serversReplylen;
        
        // open reader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        
        serversReply = reader.readLine();
        serversReplylen = serversReply.length();
        
        while (null != serversReply) {
//            if (Settings.VERBOSE) 
//                log.info("CGI reply: " + serversReply);
          
            if (serversReply.substring(0,4).equals("User")) { 
                // login successful
                log.debug("read server reply: " + serversReply);
                reader.close();
                return;
            } else if (serversReply.substring(0,5).equals("ERROR") || 
                    serversReply.substring(0,13).equals("Enter MyProxy")) {
                throw new CredentialManagementException(serversReply);
            } else if (serversReply.substring(0,20).equals("check_diskfull_error")) {
                throw new CredentialManagementException("The disk on the CGI server is full.");
            } else {
                log.info("read server reply: " + serversReply);
            }
        }
    }
    
	/**
	 * Read and return the cog properties from the default location in the
	 * server's .globus folder.
	 * 
	 * @return
	 */
	protected CoGProperties getDefaultProperties() {
		return CoGProperties.getDefault();
	}
    
    /**
     * Create a string out of the given credential.  This is essentially a string 
     * representation of the file created by the save operation.
     * 
     * @param cred
     * @return
     */
    //public static String serialize(GlobusCredential cred) {
    public static String serialize(X509Credential cred) {
        String userProxy = "";
        if (cred != null) {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                cred.save(out);
                userProxy = out.toString();
            } catch (Exception e) {
                throw new CredentialManagementException(e);
            }
        }
        return userProxy;
    }
}
