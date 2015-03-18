package org.teragrid.service.profile.wsclients;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.restlet.Client;
import org.restlet.data.Protocol;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.teragrid.service.tgcdb.dto.ComputeDTO;

public class KitServicesClient {
	private static Logger log = Logger.getLogger(KitServicesClient.class);
	
	private Set<ComputeDTO> ctssSystems = new HashSet<ComputeDTO>();
	
	public KitServicesClient(String endpoint) {
		String gridftpUrl = endpoint + "type/gridftp/name/gridftp-default-server/";
		String loginUrl = endpoint + "type/gsi-openssh/name/gsi-openssh/";
		
		retrieveGridFTPEndpoints(gridftpUrl);
		
		retrieveGsiSshEndpoints(loginUrl);
	}
	
	private void retrieveGridFTPEndpoints(String endpoint) {
		// parse csv output from ctss-resource-v1 service
		Client client = new Client(Protocol.HTTP);  
		Response response = client.get(endpoint);  
		  
		// Parse the response into a SystemDTO object
		Representation output = response.getEntity(); 
		InputStream in = null;
		try {
			in = output.getStream();
			byte[] b = new byte[1024];
			String resources = "";
			while((in.read(b)) > -1) {
				resources += new String(b);
			}
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("Type")) continue;
//					log.debug("Processing line " + line);
					String[] values = line.split(",");
					if (!(values.length >= 7)) continue;
					ComputeDTO sysDto = new ComputeDTO();
					sysDto.setSite(values[5].replaceAll("\"", ""));
					sysDto.setResourceId(values[6].replaceAll("\"", ""));
					sysDto.setGridftpHostname(values[4].replaceAll("\"", ""));
					
					ctssSystems.add(sysDto);
				}
			}
		} catch (Exception e) {
			log.debug("Failed to retrieve output from " + endpoint,e);
		} finally {
			try { in.close(); } catch (Exception e) { log.error(e);}
		}
		
	}
	
	private void retrieveGsiSshEndpoints(String endpoint) {
		// parse csv output from ctss-resource-v1 service
		Client client = new Client(Protocol.HTTP);  
		Response response = client.get(endpoint);  
		  
		// Parse the response into a SystemDTO object
		Representation output = response.getEntity(); 
		InputStream in = null;
		try {
			in = output.getStream();
			byte[] b = new byte[1024];
			String resources = "";
			while((in.read(b)) > -1) {
				resources += new String(b);
			}
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("Type")) continue;
//					log.debug("Processing line " + line);
					String[] values = line.split(",");
					if (values.length != 13) continue;
					
					for (ComputeDTO system: ctssSystems) {
						if (system.getResourceId().equals(values[6].replaceAll("\"", ""))) {
							String login = values[4];
							if (login == null) continue;
							login = login.replaceAll("\"", "");
							system.setLoginHostname(login.contains(":")?login.substring(0,login.indexOf(":")):login);
						}
					}
					
				}
			}
		} catch (Exception e) {
			log.debug("Failed to retrieve output from " + endpoint,e);
		} finally {
			try { in.close(); } catch (Exception e) { log.error(e);}
		}
		
	}

	public Set<ComputeDTO> getResources() {
		return ctssSystems;
	}
	
	@SuppressWarnings("unused")
	private ComputeDTO getResource(String resourceId) {
		
		return null;
	}
}
