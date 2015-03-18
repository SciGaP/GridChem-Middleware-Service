/**
 * 
 */
package org.gridchem.service.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.globus.gsi.GlobusCredential;
import org.globus.gsi.X509Credential;
import org.gridchem.service.beans.Address;
import org.gridchem.service.exceptions.CredentialManagementException;
import org.gridchem.service.util.crypt.SHA1;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Catchall class for static utility methods.
 * @author dooley
 *
 */
public class ServiceUtil {
	
	public static XStream xstream = new XStream(new DomDriver());

	static {
		String defaultFormat = "MMM d, yyyy h:mm:ss a";
	

		String[] acceptableFormats = new String[] { "MM/dd/yyyy HH:mm:ss",
				"yyyy-MM-dd HH:mm:ss.S z", "yyyy-MM-dd HH:mm:ss.S a",
				"yyyy-MM-dd HH:mm:ssz", "yyyy-MM-dd HH:mm:ss z", // JDK 1.3
																	// needs
																	// both prev
																	// versions
				"yyyy-MM-dd HH:mm:ssa" }; // backwards compatability
	
		xstream.registerConverter(new DateConverter(defaultFormat,
				acceptableFormats));
	
		createAliases();
	
	}	

	public static boolean isValid(String value) {
		return (value != null) && !value.equals("");
	}
	
	public static boolean isValid(File value) {
		return (value != null) && value.exists();
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isValid(Collection value) {
		return (value !=null) && (!value.isEmpty());
	}
	@SuppressWarnings("unchecked")
	public static boolean isValid(Map value) {
		return (value != null) && (value.size() > 0);
	}
	
	public static boolean isValid(Calendar value) {
		return value != null;
	}
	
	public static boolean isValid(Date value) {
		return value != null;
	}
	
	public static boolean isValid(Long value) {
		return value != null && value.intValue() >= 0;
	}
	
	public static boolean isValid(Integer value) {
		return value != null && value.intValue() >= 0;
	}
	
    /**
     * Determines of the given string is an alphanumeric string. All job names, 
     * research project names, etc must be alphanumeric strings. This requirement
     * is imposed for two reasons.  First, directory and file names are derived
     * from the research project and job names, thus they must conform to *nix
     * file naming rules. Second, we need to use non-alphanumeric characters to
     * delimit query terms for searching.
     * 
     * @param s
     * @return
     */
    public static boolean isAlphaNumeric(String s) {
    	if (!isValid(s)) return false;
        boolean valid = true;
        if (s == null || 
                s.indexOf(":") > -1 ||
                s.indexOf(";") > -1 || 
                s.indexOf(",") > -1 || 
                s.indexOf(" ") > -1 ||
                //s.indexOf("#") > -1 ||
                s.indexOf("\\") > -1 /*||
                s.indexOf("/") > -1 ||
                s.indexOf("%") > -1 ||
                s.indexOf("$") > -1 ||
                s.indexOf("@") > -1 ||
                s.indexOf("!") > -1 || 
                s.indexOf("^") > -1 ||
                s.indexOf("&") > -1 ||
                s.indexOf("*") > -1 ||
                s.indexOf("(") > -1 ||
                s.indexOf(")") > -1 ||
                s.indexOf("{") > -1 ||
                s.indexOf("}") > -1 ||
                s.indexOf("|") > -1 ||
                s.indexOf("'") > -1 ||
                s.indexOf("\"") > -1 ||
                s.indexOf("~") > -1 ||
                s.indexOf("`") > -1 ||
                s.indexOf("#") > -1 ||
                s.indexOf(".") > -1 ||
                s.indexOf("?") > -1 ||
                s.indexOf("<") > -1 ||
                s.indexOf(">") > -1 ||
                //s.indexOf("=") > -1 ||
                s.indexOf("+") > -1*/) {
            valid = false;
        }
        
        return valid;
    }
    
    public static boolean isValidEmailAddress(String value) {
    	if (!isValid(value)) return false;
    	String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
    }
    
    public static boolean isValidPhoneNumber(String value) {
    	if (!isValid(value)) return false;
    	String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		Pattern pattern = Pattern.compile(expression,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
    }
    
    public static boolean isValid(Address address) {
    	if (address == null) return false;
		if (!isValid(address.getStreet1())) return false;
		if (!isValid(address.getCity())) return false;
		if (!isValid(address.getState())) return false;
		if (!isValid(address.getZipCode()) || 
				address.getZipCode().length() != 5) return false;
		try {
			Integer.parseInt(address.getZipCode());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
    
	/**
	 * Create the alias mapping from via xstream from the xml to java objects.
	 * These resources were created in the service are are leverage through the
	 * jar of the gms service here in the client.
	 */
	private static void createAliases() {
		
	}
	
	/**
     * Create a string out of the given credential.  This is essentially a string 
     * representation of the file created by the save operation.
     * 
     * @param cred
     * @return
     */

    public static String serializeGlobusCredential(GlobusCredential cred) {
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

    public static String serializeX509Credential(X509Credential cred) {
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
