package org.gridchem.service.beans;

//import org.globus.gsi.GlobusCredential;
import org.globus.gsi.X509Credential;

public abstract class AuthenticationBean {
	
	//protected GlobusCredential credential;
	protected X509Credential credential;
	protected String username;
	protected String myproxyUsername;
	protected String myproxyPassword;
	protected String myproxyHost;
	protected int myproxyPort = 0;
    private boolean valid = false;
	
	
//	public GlobusCredential getCredential() {
//		return credential;
//	}
//JGlobus 2.1.0 	
	 public X509Credential getCredential() {
              return credential;
         }
//
	//public void setCredential(GlobusCredential credential) throws Exception {
	//	this.credential = credential;
	//}
        public void setCredential(X509Credential credential) throws Exception {
                this.credential = credential;
        }

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getMyproxyUsername() {
		return myproxyUsername;
	}

	public void setMyproxyUsername(String myproxyUsername) {
		this.myproxyUsername = myproxyUsername;
	}

	public String getMyproxyPassword() {
		return myproxyPassword;
	}

	public void setMyproxyPassword(String myproxyPassword) {
		this.myproxyPassword = myproxyPassword;
	}

	public String getMyproxyHost() {
		return myproxyHost;
	}

	public void setMyproxyHost(String myproxyHost) {
		this.myproxyHost = myproxyHost;
	}

	public int getMyproxyPort() {
		return myproxyPort;
	}

	public void setMyproxyPort(int myproxyPort) {
		this.myproxyPort = myproxyPort;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
}
