package org.teragrid.service.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.gridchem.service.util.Settings;
import org.restlet.Context;
import org.teragrid.service.profile.wsclients.CtssResourceClient;
import org.teragrid.service.profile.wsclients.IncaDowntimeClient;
import org.teragrid.service.profile.wsclients.KitServicesClient;
import org.teragrid.service.tgcdb.dto.ComputeDTO;
import org.teragrid.service.tgcdb.dto.Service;

public class ResourceCache {
	private static final Logger log = Logger.getLogger(ResourceCache.class);
	
	private static Set<ComputeDTO> resourceCache = new HashSet<ComputeDTO>();
	private static long lastUpdated = System.currentTimeMillis();
	
	private static String ctssUrl = Settings.IIS_SERVICE_URL;
	private static String incaUrl = Settings.INCA_SERVICE_URL;
	private static String kitServiceUrl = Settings.KIT_SERVICE_URL;
	private static int timeout = Settings.REFRESH_RATE*3600;
	
	public static void init(Context context) {
//		ctssUrl = Settings.IIS_SERVICE_URL;
//		incaUrl = Settings.INCA_SERVICE_URL;
//		timeout = Settings.REFRESH_RATE*3600;
//		kitServiceUrl = Settings.KIT_SERVICE_URL;
	}
	
	
	public static Set<ComputeDTO> getResources() {
		
		checkCache();
		
		return resourceCache;
	}
	
	public static ComputeDTO getResourceByTgcdbName(String tgcdbName) {
		
		checkCache();
		
		for(ComputeDTO system: resourceCache) {
			if (system.getTgcdbName().equalsIgnoreCase(tgcdbName)) {
				return system;
			}
		}
		
		return null;
	}

	public static ComputeDTO getResourceByResourceId(String resourceId) {
		
		checkCache();
		
		for(ComputeDTO system: resourceCache) {
			if (system.getResourceId().equalsIgnoreCase(resourceId)) {
				return system;
			}
		}
		
		return null;
	}
	
	public static ComputeDTO getResourceByName(String name) {
		
		checkCache();
		
		for(ComputeDTO system: resourceCache) {
			if (system.getName().equalsIgnoreCase(name)) {
				return system;
			}
		}
		
		return null;
	}
	
	private static void checkCache() {
		
		 // check to see if the cache has expired
    	if (lastUpdated + timeout <= System.currentTimeMillis() || resourceCache.isEmpty()) {
    		
    		log.debug("Updating entire resource cache...");
    		
    		try {
				refreshResources();
			} catch (IOException e) {
				log.error("Failed to retrieve resource listing from ctss-resource-v1",e);
			}

			try {
				refreshEndpoints();
			} catch (Exception e) {
				log.error("Failed to retrieve resource listing from ctss-resource-v1",e);
			}
			
    		try {
				updateDowntimes();
			} catch (IOException e) {
				log.error("Failed to retrieve resource downtimes from Inca",e);
			}
    		
    		lastUpdated = System.currentTimeMillis();
        }
	}
	
	/**
	 * Calls the ctss-resource-v1 iis service for a listing of resources
	 * @throws IOException
	 */
	private static void refreshResources() throws IOException {
		CtssResourceClient client = new CtssResourceClient(ctssUrl);
		resourceCache = client.getResources();
	}
	
	/**
	 * Calls the kit-services-v1 iis service for a listing of resources and
	 * their gridftp endpoints
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	private static void refreshEndpoints() throws IOException {
		KitServicesClient client = new KitServicesClient(kitServiceUrl);
		for(ComputeDTO system: resourceCache) {
			for(ComputeDTO kitSystem: client.getResources()) {
				if (system.getResourceId().equals(kitSystem.getResourceId())) {
					try {
						system.setGridftpHostname(new URI(kitSystem.getGridftpHostname()).getHost());
					} catch (Exception e) {
						log.error(e);
					}
					try {
						system.setLoginHostname(kitSystem.getLoginHostname());
					} catch (Exception e) {
						log.error(e);
					}
				}
			}
		}
	}
	
	/**
	 * Sets the status on resources found in the published Inca
	 * downtime-iis.txt file.
	 * 
	 * @throws IOException
	 */
	private static void updateDowntimes() throws IOException {
		IncaDowntimeClient client = new IncaDowntimeClient(incaUrl);
		
		for(ComputeDTO system: resourceCache) {
			for(ComputeDTO downtime: client.getResources()) {
				if (system.getResourceId().equals(downtime.getResourceId())) {
					system.setStatus(Service.DOWN);
				}
			}
		}
	}
	
	/**
	 * Returns a sample csv output as would be returned from the ctss-resource-v1 service
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String createDummyData() {
		return "SiteID,ResourceID,TgcdbResourceName,ResourceName,OrganizationName,PopsName,AmieName" + 
				"iu.teragrid.org,bigred.iu.teragrid.org,\"bigred.iu.teragrid\",\"IU BigRed\",\"Indiana University\",\"Indiana University IBM e1350 (Big Red)\",\"IU\"" + 
				"iu.teragrid.org,hpss.iu.teragrid.org,\"hpss.iu.teragrid\",\"IU HPSS Archival Service\",\"Indiana University\",\"Indiana University HPSS Archival Storage\",\"IU\"" + 
				"loni-lsu.teragrid.org,queenbee.loni-lsu.teragrid.org,\"queenbee.loni-lsu.teragrid\",\"LONI Queenbee\",\"Louisiana Optical Network Initiative\",\"\",\"LONI-LSU\"" + 
				"ncar.teragrid.org,frost.ncar.teragrid.org,\"frost.ncar.teragrid\",\"frost.ncar.teragrid.org\",\"National Center for Atmospheric Research\",\"NCAR IBM Blue Gene (Frost)\",\"NCAR\"" + 
				"ncsa.teragrid.org,abe.ncsa.teragrid.org,\"abe.ncsa.teragrid\",\"NCSA Abe\",\"National Center for Supercomputing Applications\",\"NCSA/LONI Dell PowerEdge Linux Clusters (Abe/Queenbee)\",\"NCSA\"" + 
				"ncsa.teragrid.org,cobalt.ncsa.teragrid.org,\"cobalt.ncsa.teragrid\",\"NCSA Cobalt Altix\",\"National Center for Supercomputing Applications\",\"NCSA SGI Altix (Cobalt)\",\"NCSA\"" + 
				"ncsa.teragrid.org,dtf.ncsa.teragrid.org,\"dtf.ncsa.teragrid\",\"dtf.ncsa.teragrid.org\",\"National Center for Supercomputing Applications\",\"\",\"NCSA\"" + 
				"ncsa.teragrid.org,lincoln.ncsa.teragrid.org,\"lincoln.ncsa.teragrid\",\"NCSA Lincoln\",\"National Center for Supercomputing Applications\",\"NCSA Lincoln Supercluster\",\"NCSA\"" + 
				"nics.teragrid.org,kraken.nics.teragrid.org,\"kraken.nics.teragrid\",\"NICS Kraken Cray XT5\",\"University of Tennessee, Knoxville\",\"NICS Cray XT5 (Kraken)\",\"NICS\"" + 
				"ornl.teragrid.org,nstg.ornl.teragrid.org,\"nstg.ornl.teragrid\",\"ORNL Neutron Science TeraGrid Gateway\",\"Oak Ridge National Laboratory\",\"\",\"ORNL\"" + 
				"psc.teragrid.org,bigben.psc.teragrid.org,\"bigben.psc.teragrid\",\"PSC Bigben\",\"Pittsburgh Supercomputing Center\",\"PSC XT3 (Big Ben)\",\"PSC\"" + 
				"psc.teragrid.org,pople.psc.teragrid.org,\"pople.psc.teragrid\",\"PSC Pople\",\"Pittsburgh Supercomputing Center\",\"PSC SGI Altix (Pople)\",\"PSC\"" + 
				"purdue.teragrid.org,brutus.purdue.teragrid.org,\"brutus.purdue.teragrid\",\"Purdue Condor Pools\",\"Purdue University\",\"Purdue FPGA Prototyping Environment (Brutus)\",\"Purdue\"" + 
				"purdue.teragrid.org,condor.purdue.teragrid.org,\"condor.purdue.teragrid\",\"Purdue Condor Pools\",\"Purdue University\",\"Purdue Condor Pool\",\"Purdue\"" + 
				"purdue.teragrid.org,steele.purdue.teragrid.org,\"steele.purdue.teragrid\",\"Purdue Steele\",\"Purdue University\",\"Purdue Dell PowerEdge Linux Cluster (Steele)\",\"Purdue\"" + 
				"sdsc.teragrid.org,dtf.sdsc.teragrid.org,\"dtf.sdsc.teragrid\",\"SDSC DTF\",\"San Diego Supercomputer Center\",\"\",\"SDSC\"" + 
				"tacc.teragrid.org,lonestar.tacc.teragrid.org,\"lonestar.tacc.teragrid\",\"TACC Lonestar\",\"University of Texas at Austin\",\"TACC Dell PowerEdge Linux Cluster (Lonestar)\",\"TACC\"" + 
				"tacc.teragrid.org,ranger.tacc.teragrid.org,\"ranger.tacc.teragrid\",\"TACC Ranger\",\"University of Texas at Austin\",\"TACC Sun Constellation Cluster (Ranger)\",\"TACC\"" + 
				"tacc.teragrid.org,spur.tacc.teragrid.org,\"spur.tacc.teragrid\",\"TACC Spur\",\"University of Texas at Austin\",\"TACC Sun Visualization System (Spur)\",\"TACC\"" + 
				"uc.teragrid.org,dtf.uc.teragrid.org,\"dtf.anl.teragrid\",\"UC/ANL DTF\",\"Argonne National Laboratory\",\"\",\"ANL\"" + 
				"uc.teragrid.org,viz.uc.teragrid.org,\"viz.anl.teragrid\",\"UC/ANL Visualization\",\"Argonne National Laboratory\",\"UC/ANL Visualization Cluster\",\"ANL\"";
	}
}
