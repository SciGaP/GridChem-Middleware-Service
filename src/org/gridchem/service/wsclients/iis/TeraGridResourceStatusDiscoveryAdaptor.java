package org.gridchem.service.wsclients.iis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;
import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.sync.ResourceDiscoveryAdaptor;
import org.gridchem.service.sync.iis.beans.Allocation;
import org.gridchem.service.sync.iis.beans.BandwidthMeasurement;
import org.gridchem.service.sync.iis.beans.Profile;
import org.gridchem.service.sync.iis.beans.SystemDTO;
import org.gridchem.service.util.Settings;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Syncronizer to keep the status of TeraGrid resources in sync
 * with the resource statuses in the central db.
 * 
 * @author dooley
 *
 */
public class TeraGridResourceStatusDiscoveryAdaptor implements ResourceDiscoveryAdaptor {

	private static Logger log = Logger.getLogger(TeraGridResourceStatusDiscoveryAdaptor.class);
	
	private Protocol myhttps;
	
	protected static XStream xstream = new XStream(new DomDriver());
	
	private static String defaultFormat = "MMM d, yyyy h:mm:ss a";
    
    private static String[] acceptableFormats = new String[] {
            "MM/dd/yyyy HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.S z",
            "yyyy-MM-dd HH:mm:ss.S a",
            "yyyy-MM-dd HH:mm:ssz",
            "yyyy-MM-dd HH:mm:ss z", // JDK 1.3 needs both prev versions
            "yyyy-MM-dd HH:mm:ssa" }; // backwards compatability
    
    {
        xstream.registerConverter(new DateConverter(defaultFormat, acceptableFormats));
        xstream.alias("profile", Profile.class);
        xstream.alias("project", Allocation.class);
        xstream.alias("system", SystemDTO.class);
        xstream.alias("measurement", BandwidthMeasurement.class);
        xstream.alias("systems", ArrayList.class);
    }
    
	
	public TeraGridResourceStatusDiscoveryAdaptor() {
		myhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 8443);
		Protocol.registerProtocol("https",myhttps);
	}

	public List<ResourceBean> retrieveResources() {
		List<ResourceBean> beans = null;
		try {
			 // pass our credentials to HttpClient, they will only be used for
	        // authenticating to servers with realm "realm" on the host
	        // "www.verisign.com", to authenticate against
	        // an arbitrary realm or host change the appropriate argument to null.
			HttpClient client = new HttpClient();
			client.getState().setCredentials(
	                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM), 
	                new UsernamePasswordCredentials(Settings.COMMUNITY_USERNAME, Settings.COMMUNITY_PASSWORD));
	        
			// create a GET method that reads a file over HTTPS, we're assuming
	        // that this file requires basic authentication using the realm above.
	        GetMethod get = new GetMethod(Settings.TERAGRID_PROFILE_SERVER + "/resource");
	        
	        // Tell the GET method to automatically handle authentication. The
	        // method will use any appropriate credentials to handle basic
	        // authentication requests.  Setting this value to false will cause
	        // any request for authentication to return with a status of 401.
	        // It will then be up to the client to handle the authentication.
	        get.setDoAuthentication(true);
	        
	        // execute the GET
            @SuppressWarnings("unused")
			int status = client.executeMethod( get );

            // print the status and response
//            log.debug(status + "\n" + get.getResponseBodyAsString());
            
            beans = parseXml(get.getResponseBodyAsString());
            
            get.releaseConnection();
		} catch (Exception e) {
			log.error("Failed to retrieve status info from TeraGrid",e);
		}
		
		return beans;
	}

	@SuppressWarnings("unchecked")
	private List<ResourceBean> parseXml(String xml) {
		List<ResourceBean> beans = new ArrayList<ResourceBean>();
		
		List<SystemDTO> systems = (List<SystemDTO>)xstream.fromXML(xml);
		
		for (SystemDTO system: systems) {
			beans.add(parseSystem(system));
		}
		
		return beans;
	}
	
	private ResourceBean parseSystem(SystemDTO system) {
		ComputeBean bean = new ComputeBean();
		bean.setName(system.getName());
		bean.setStatus(ResourceStatusType.valueOf(system.getStatus().toUpperCase()));
		bean.setLoad(null);
		return bean;
	}
	
	public static void main(String[] args) {
		
		TeraGridResourceStatusDiscoveryAdaptor client = new TeraGridResourceStatusDiscoveryAdaptor();
		
		for (ResourceBean bean: client.retrieveResources()) {
			System.out.println(bean.getName() + " " + bean.getStatus());
		}
		
	}
}