package org.teragrid.service.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.apache.log4j.Logger;
import org.teragrid.service.profile.wsclients.IncaDowntimeClient;
import org.teragrid.service.tgcdb.dto.Load;
import org.teragrid.service.tgcdb.dto.Service;
import org.teragrid.service.tgcdb.dto.ComputeDTO;

public class StatusResolver {

	private static final Logger log = Logger.getLogger(StatusResolver.class);
	
	// table of status for each resource keyed off the info service resource id
	public Set<ComputeDTO> downtimeCache = new HashSet<ComputeDTO>();
	
	// table of queue load for each resource keyed off the info service resource id
	public Hashtable<String,Load> loadCache = new Hashtable<String,Load>();
	
	private String incaEndpoint;
	
	public StatusResolver(String incaEndpoint) {
		this.incaEndpoint = incaEndpoint;
		try {
			cacheSystemDowntimes();
		} catch (IOException e) {
			log.error("Unable to retrieve system downtimes.",e);
		}
		
//		try {
//			cacheSystemLoads();
//		} catch (Exception e) {
//			log.error("Unable to retrieve system loads.",e);
//		}
	}
	
	public void resolve(ComputeDTO system) {
		
		if(downtimeCache.contains(system)) {
			system.setStatus(Service.DOWN);
		} else {
			system.setStatus(Service.UP);
		}
	
//		if (loadCache.containsKey(system.getId())) {
//			system.setLoad(loadCache.get(system.getId()));
//		} else {
//			system.setLoad(new Load("not available",0,0));
//		}
	}
	
	private void cacheSystemDowntimes() throws IOException {
		// clear out expired cache
		downtimeCache.clear();
		
		IncaDowntimeClient inca = new IncaDowntimeClient(incaEndpoint);
		
		downtimeCache.addAll(inca.getResources());
		
	}
	
	
}
