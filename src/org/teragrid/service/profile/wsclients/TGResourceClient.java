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

public class TGResourceClient {
	private static Logger log = Logger.getLogger(TGResourceClient.class);
	
	private Set<ComputeDTO> ctssSystems = new HashSet<ComputeDTO>();
	
	public TGResourceClient(String endpoint) {
		// parse csv output from ctss-resource-v1 service
		Client client = new Client(Protocol.HTTP);  
		Response response = client.get(endpoint);  
		  
		// Parse the response into a SystemDTO object
		Representation output = response.getEntity(); 
		InputStream in;
		try {
			in = output.getStream();
			byte[] b = new byte[1024];
			String resources = "";
			while((in.read(b)) > -1) {
				resources += new String(b);
			}
			
			if (resources != null && !resources.equals("")) {
				for (String line: resources.split("\n")) { 
					if (line.startsWith("Site")) continue;
					System.out.println("Processing line " + line);
					String[] values = line.split(",");
					if (values.length == 1) continue;
					ComputeDTO sysDto = new ComputeDTO();
					sysDto.setSite(values[0].replaceAll("\"", ""));
					sysDto.setResourceId(values[1].replaceAll("\"", ""));
					sysDto.setName(values[2].replaceAll("\"","").equals("")?
							values[8].replaceAll("\"", ""):values[2].replaceAll("\"", ""));
//					sysDto.setType("compute");
	//				sysDto.setGridftpHostname(values[4].replaceAll("\"", ""));
					
					ctssSystems.add(sysDto);
				}
			}
		} catch (Exception e) {
			log.debug("Failed to retrieve output from " + endpoint,e);
		}
		
	}

	public Set<ComputeDTO> getResources() {
		return ctssSystems;
	}
}
