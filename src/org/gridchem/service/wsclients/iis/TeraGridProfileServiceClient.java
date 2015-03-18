package org.gridchem.service.wsclients.iis;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.sync.iis.beans.Address;
import org.gridchem.service.sync.iis.beans.Allocation;
import org.gridchem.service.sync.iis.beans.BandwidthMeasurement;
import org.gridchem.service.sync.iis.beans.Collaborator;
import org.gridchem.service.sync.iis.beans.Profile;
import org.gridchem.service.sync.iis.beans.SystemDTO;
import org.gridchem.service.test.profile.HTTPSClient;
import org.gridchem.service.util.Settings;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TeraGridProfileServiceClient {
	private static Logger log = Logger.getLogger(TeraGridProfileServiceClient.class);

//	static {
//        // Set the truststore system variable to let jvm know to use the cg keystore
//        // for all ssl communication.
//        System.setProperty("java.protocol.handler.pkgs",
//            "com.sun.net.ssl.internal.www.protocol");
//        
//        File keystore = new File("/share/schema/gms/ccgkeystore");
////        File keystore = new File("security/ccgkeystore");
//        if (!keystore.exists()) {
//            
//            keystore = new File("security/ccgkeystore");
//            
//            if (!keystore.exists()) {
//                throw new JobSubmissionException("Could not find Job Submission Server Certificate. Job submission will fail.");
//            }
//        }
//        
//        System.setProperty("javax.net.ssl.trustStore",keystore.toString());
//        System.setProperty("javax.net.ssl.trustStorePassword","changeit");
//        
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider()); 
//        
//    }
	
//	private Client client = new Client(Protocol.HTTPS);  
//	
//	private ChallengeResponse authentication;
	
	protected XStream xstream = new XStream(new DomDriver());
	
	private String defaultFormat = "MMM d, yyyy h:mm:ss a";
    
    private String[] acceptableFormats = new String[] {
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
    }
    
	private String username;
	private String passwd;
//	
	public TeraGridProfileServiceClient(String username, String pass) {
		this.username = username;
		this.passwd = pass;
////		try {
////			SSOUtils.enableSSL();
////		} catch (GeneralSecurityException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC,
//				username, pass);
		
	}
	
	public List<SystemDTO> getResources() throws PermissionException {
		List<SystemDTO> beans = new ArrayList<SystemDTO>();
		
//		Request request = new Request(Method.GET,Settings.TERAGRID_PROFILE_SERVER + "/resource");
//		request.setChallengeResponse(authentication);
//		Response response = client.handle(request);
//		if (response.getStatus().isSuccess()) {  
		try {
			HTTPSClient client = new HTTPSClient(Settings.TERAGRID_PROFILE_SERVER + "/resource",username, passwd);
		    // Parse the response into a SystemDTO object
//			Representation output = response.getEntity(); 
//			InputStream in;
//			try {
//				in = output.getStream();
//				byte[] b = new byte[1024];
//				String resources = "";
//				while((in.read(b)) > -1) {
//					resources += new String(b);
//				}
				String resources = client.getText();
				if (resources != null && !resources.equals("")) {
					for (String line: resources.split("\n")) { 
						if (line.startsWith("site")) continue;
						log.debug("Processing line " + line);
						String[] values = line.split(",");
						if (values.length == 1) continue;
						SystemDTO bean = new SystemDTO();
						bean.setSite(values[0].replaceAll("\"", ""));
						bean.setResourceId(values[1].replaceAll("\"", ""));
						bean.setTgcdbName(values[2].replaceAll("\"", ""));
						bean.setName(values[3].replaceAll("\"", ""));
						bean.setLoginHostname(values[4].replaceAll("\"", ""));
						bean.setGridftpHostname(values[5].replaceAll("\"", ""));
						bean.setStatus(values[6].replaceAll("\"",""));
						bean.setLocalUsername(values[7].replaceAll("\"", ""));
						bean.setType(values[8].replaceAll("\"", ""));
						beans.add(bean);
					}
				}
				return beans;
		} catch( IOException ioe ) {
        	// Unauthorized access  
		    throw new PermissionException("Access authorized by the server, " +  
		                    "check your credentials");  
        } catch (Exception e) {
			throw new PermissionException("Failed to retrieve output from " + 
					Settings.TERAGRID_PROFILE_SERVER + "/resource",e);
		}
	}

	public SystemDTO getResource(String resourceId) {
		try {
			HTTPSClient client = new HTTPSClient(
					Settings.TERAGRID_PROFILE_SERVER + "/resource/resourceid/" + resourceId,
					username, passwd);
			
			String resources = client.getText();
			
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("site")) continue;
//						log.debug("Processing line " + line);
					String[] values = line.split(",");
					if (values.length == 1) continue;
					SystemDTO bean = new SystemDTO();
					bean.setSite(values[0].replaceAll("\"", ""));
					bean.setResourceId(values[1].replaceAll("\"", ""));
					bean.setTgcdbName(values[2].replaceAll("\"", ""));
					bean.setName(values[3].replaceAll("\"", ""));
					bean.setLoginHostname(values[4].replaceAll("\"", ""));
					bean.setGridftpHostname(values[5].replaceAll("\"", ""));
					bean.setStatus(values[6].replaceAll("\"",""));
					bean.setLocalUsername(values[7].replaceAll("\"", ""));
					bean.setType(values[8].replaceAll("\"", ""));
					
					return bean;
				}
			}
			return null;
		} catch( IOException ioe ) {
        	// Unauthorized access  
		    throw new PermissionException("Access authorized by the server, " +  
		                    "check your credentials");  
        } catch (Exception e) {
			throw new PermissionException("Failed to retrieve output from " + 
					Settings.TERAGRID_PROFILE_SERVER + "/resource",e);
		}
	}

	public List<Allocation> getProjects() {
		List<Allocation> beans = new ArrayList<Allocation>();
		try {
			HTTPSClient client = new HTTPSClient(
					Settings.TERAGRID_PROFILE_SERVER + "/project/",
					username, passwd);
			String resources = client.getText();
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("id")) continue;
//						log.debug("Processing line " + line);
					String[] values = line.split(",");
					if (values.length == 1) continue;
					Allocation bean = new Allocation();
					bean.setAllocationId(new Integer(values[0].replaceAll("\"","")));
					bean.setProjectId(values[1].replaceAll("\"", ""));
					bean.setProjectTitle(values[2].replaceAll("\"", ""));
					bean.setStartDate(parseDate(values[3].replaceAll("\"", "")));
					bean.setEndDate(parseDate(values[4].replaceAll("\"", "")));
					bean.setBaseAllocation(new BigDecimal(values[5].replaceAll("\"", "")));
					bean.setUsedAllocation(new BigDecimal(values[6].replaceAll("\"", "")));
					bean.setRemainingAllocation(new BigDecimal(values[7].replaceAll("\"", "")));
					bean.setAllocResourceName(values[8].replaceAll("\"", ""));
					bean.setPiFirstName(values[9].replaceAll("\"", ""));
					bean.setPiLastName(values[10].replaceAll("\"", ""));
					bean.setAcctState(values[11].replaceAll("\"", ""));
					bean.setProjState(values[12].replaceAll("\"", ""));
					beans.add(bean);
				}
			}
			return beans;
		} catch( IOException ioe ) {
        	// Unauthorized access  
		    throw new PermissionException("Access authorized by the server, " +  
		                    "check your credentials");  
        } catch (Exception e) {
			throw new PermissionException("Failed to retrieve output from " + 
					Settings.TERAGRID_PROFILE_SERVER + "/resource",e);
		}
		
	}
	
	public Allocation getProject(String projectName) {
		try {
			HTTPSClient client = new HTTPSClient(
					Settings.TERAGRID_PROFILE_SERVER + "/project/project_number/" + projectName,
					username, passwd);
			String resources = client.getText();
			if (resources != null && !resources.equals("")) {
				String line = resources.split("\n")[1]; 
				String[] values = line.split(",");
				Allocation bean = new Allocation();
				bean.setAllocationId(new Integer(values[0].replaceAll("\"","")));
				bean.setProjectId(values[1].replaceAll("\"", ""));
				bean.setProjectTitle(values[2].replaceAll("\"", ""));
				bean.setStartDate(parseDate(values[3].replaceAll("\"", "")));
				bean.setEndDate(parseDate(values[4].replaceAll("\"", "")));
				bean.setBaseAllocation(new BigDecimal(values[5].replaceAll("\"", "")));
				bean.setUsedAllocation(new BigDecimal(values[6].replaceAll("\"", "")));
				bean.setRemainingAllocation(new BigDecimal(values[7].replaceAll("\"", "")));
				bean.setAllocResourceName(values[8].replaceAll("\"", ""));
				bean.setPiFirstName(values[9].replaceAll("\"", ""));
				bean.setPiLastName(values[10].replaceAll("\"", ""));
				bean.setAcctState(values[11].replaceAll("\"", ""));
				bean.setProjState(values[12].replaceAll("\"", ""));
				return bean;
			}
			return null;
		} catch( IOException ioe ) {
        	// Unauthorized access  
		    throw new PermissionException("Access authorized by the server, " +  
                    "check your credentials");  
    	} catch (Exception e) {
			throw new PermissionException("Failed to retrieve output from " + 
					Settings.TERAGRID_PROFILE_SERVER + "/project/project_number/"+projectName,e);
		}
	
	}
	
	public List<Collaborator> getCollaborators() {
		List<Collaborator> beans = new ArrayList<Collaborator>();
		try {
			HTTPSClient client = new HTTPSClient(
					Settings.TERAGRID_PROFILE_SERVER + "/collab",
					username, passwd);
			String resources = client.getText();
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("id")) continue;
//						log.debug("Processing line " + line);
					String[] values = line.split(",");
					if (values.length == 1) continue;
					Collaborator bean = new Collaborator();
					bean.setUsername(values[1].replaceAll("\"", ""));
					bean.setFirstName(values[2].replaceAll("\"", ""));
					bean.setLastName(values[4].replaceAll("\"", ""));
					bean.setEmail(values[5].replaceAll("\"", ""));
					Address address = new Address();
					address.setStreet1(values[6].replaceAll("\"", ""));
					address.setStreet2(values[7].replaceAll("\"", ""));
					address.setCity(values[8].replaceAll("\"", ""));
					address.setState(values[9].replaceAll("\"", ""));
					address.setZipcode(values[10].replaceAll("\"", ""));
					address.setCountry(values[11].replaceAll("\"", ""));
					bean.setAddress(address);
					bean.setBusPhoneNumber(values[12].replaceAll("\"", ""));
					bean.setBusPhoneExtension(values[13].replaceAll("\"", ""));
					bean.setFaxNumber(values[14].replaceAll("\"", ""));
					bean.setOrganization(values[15].replaceAll("\"", ""));
					bean.setDepartment(values[16].replaceAll("\"", ""));
					bean.setPosition(values[17].replaceAll("\"", ""));
					bean.setProjectName(values[18].replaceAll("\"", ""));
//						sysDto.setType("compute");
	//				sysDto.setGridftpHostname(values[4].replaceAll("\"", ""));
					beans.add(bean);
				}
			}
			return beans;
		} catch( IOException ioe ) {
        	// Unauthorized access  
		    throw new PermissionException("Access authorized by the server, " +  
                    "check your credentials");  
    	} catch (Exception e) {
			throw new PermissionException("Failed to retrieve output from " + 
					Settings.TERAGRID_PROFILE_SERVER + "/resource",e);
		}
	}
	
	public List<Collaborator> getCollaboratorsForProject(String projectName) {
		List<Collaborator> beans = new ArrayList<Collaborator>();
		try {
			HTTPSClient client = new HTTPSClient(
					Settings.TERAGRID_PROFILE_SERVER + "/project/project_number/" + projectName + "/collab",
					username, passwd);
			String resources = client.getText();
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("id")) continue;
//						log.debug("Processing line " + line);
					String[] values = line.split(",");
					if (values.length == 1) continue;
					Collaborator bean = new Collaborator();
					bean.setUsername(values[1].replaceAll("\"", ""));
					bean.setFirstName(values[2].replaceAll("\"", ""));
					bean.setLastName(values[4].replaceAll("\"", ""));
					bean.setEmail(values[5].replaceAll("\"", ""));
					Address address = new Address();
					address.setStreet1(values[6].replaceAll("\"", ""));
					address.setStreet2(values[7].replaceAll("\"", ""));
					address.setCity(values[8].replaceAll("\"", ""));
					address.setState(values[9].replaceAll("\"", ""));
					address.setZipcode(values[10].replaceAll("\"", ""));
					address.setCountry(values[11].replaceAll("\"", ""));
					bean.setAddress(address);
					bean.setBusPhoneNumber(values[12].replaceAll("\"", ""));
					bean.setBusPhoneExtension(values[13].replaceAll("\"", ""));
					bean.setFaxNumber(values[14].replaceAll("\"", ""));
					bean.setOrganization(values[15].replaceAll("\"", ""));
					bean.setDepartment(values[16].replaceAll("\"", ""));
					bean.setPosition(values[17].replaceAll("\"", ""));
					bean.setProjectName(values[18].replaceAll("\"", ""));
//						sysDto.setType("compute");
	//				sysDto.setGridftpHostname(values[4].replaceAll("\"", ""));
					beans.add(bean);
				}
			}
			return beans;
		} catch( IOException ioe ) {
        	// Unauthorized access  
		    throw new PermissionException("Access authorized by the server, " +  
                    "check your credentials");  
    	} catch (Exception e) {
			throw new PermissionException("Failed to retrieve output from " + 
					Settings.TERAGRID_PROFILE_SERVER + 
					"/project/project_name/" + projectName + 
					"/collab",e);
		} 
	}
	
	public Profile getUser() {
		try {
			HTTPSClient client = new HTTPSClient(
					Settings.TERAGRID_PROFILE_SERVER,
					username, passwd);
			String resources = client.getText();
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("id")) continue;
//						log.debug("Processing line " + line);
					String[] values = line.split(",");
					if (values.length == 1) continue;
					Profile bean = new Profile();
					bean.setUsername(values[1].replaceAll("\"", ""));
					bean.setFirstName(values[2].replaceAll("\"", ""));
					bean.setLastName(values[4].replaceAll("\"", ""));
					bean.setEmail(values[5].replaceAll("\"", ""));
					Address address = new Address();
					address.setStreet1(values[6].replaceAll("\"", ""));
					address.setStreet2(values[7].replaceAll("\"", ""));
					address.setCity(values[8].replaceAll("\"", ""));
					address.setState(values[9].replaceAll("\"", ""));
					address.setZipcode(values[10].replaceAll("\"", ""));
					address.setCountry(values[11].replaceAll("\"", ""));
					bean.setAddress(address);
					bean.setBusPhoneNumber(values[12].replaceAll("\"", ""));
					bean.setBusPhoneExtension(values[13].replaceAll("\"", ""));
					bean.setFaxNumber(values[14].replaceAll("\"", ""));
					bean.setOrganization(values[15].replaceAll("\"", ""));
					bean.setDepartment(values[16].replaceAll("\"", ""));
					bean.setPosition(values[17].replaceAll("\"", ""));
					
					return bean;
				}
			}
			return null;
		} catch( IOException ioe ) {
        	// Unauthorized access  
		    throw new PermissionException("Access authorized by the server, " +  
		                    "check your credentials");  
        } catch (Exception e) {
			throw new PermissionException("Failed to retrieve output from " + 
					Settings.TERAGRID_PROFILE_SERVER,e);
		}
	}
	
	private Date parseDate(String date) {
		Date fdate = null;
		
		try {
			fdate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").parse(date);
		} catch (ParseException e) {
			log.error("Failed to parse date of TeraGrid import",e);
		}
		
		return fdate;
	}
	
}
