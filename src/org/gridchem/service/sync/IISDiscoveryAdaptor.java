/**
 * 
 */
package org.gridchem.service.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.util.Settings;
import org.teragrid.service.profile.wsclients.IncaDowntimeClient;
import org.teragrid.service.profile.wsclients.KitServicesClient;
import org.teragrid.service.profile.wsclients.TGResourceClient;
import org.teragrid.service.tgcdb.dto.ComputeDTO;



/**
 * @author dooley
 *
 */
public class IISDiscoveryAdaptor implements ResourceDiscoveryAdaptor {
	
	private List<ResourceBean> iisResources = new ArrayList<ResourceBean>();
	
	public IISDiscoveryAdaptor() {
		
		IncaDowntimeClient incaClient = new IncaDowntimeClient(Settings.INCA_SERVICE_URL);
		TGResourceClient iisClient = new TGResourceClient(Settings.IIS_SERVICE_URL);
		KitServicesClient kitClient = new KitServicesClient(Settings.KIT_SERVICE_URL);
		
		Set<ComputeDTO> downSystems = incaClient.getResources();
		Set<ComputeDTO> iisSystems = iisClient.getResources();
		Set<ComputeDTO> kitSystems = kitClient.getResources();
		
		for (ComputeDTO system: iisSystems) {
			// find out which resources are there
			
			
			ComputeDTO downSystem = findByResourceId(system.getResourceId(),downSystems);
			ComputeDTO kitSystem = findByResourceId(system.getResourceId(),kitSystems);
			
			if (downSystem != null) {
				system.setStatus(ResourceStatusType.DOWN.name());
			} else {
				system.setStatus(ResourceStatusType.UP.name());
			}
			
			if (kitSystem != null) {
				system.setGridftpHostname(kitSystem.getGridftpHostname());
				system.setLoginHostname(kitSystem.getLoginHostname());
			} else {
				continue;
			}
			
			ComputeBean bean = new ComputeBean();
			bean.setName(system.getName());
			bean.setStatus(system.getStatus().equals(ResourceStatusType.DOWN.name())?ResourceStatusType.DOWN:ResourceStatusType.UP);
			bean.setHostname(system.getLoginHostname());
			
			// resolve against the resources in the db to find the record
			
			iisResources.add(bean);
			
			System.out.println("Added resource " + system.getName() + ", " + system.getLoginHostname() + ", " + system.getStatus());
		
		}
	}
	
	public static void main(String[] args) {
		new IISDiscoveryAdaptor();
	}

	public List<ResourceBean> retrieveResources() {
		return this.iisResources;
	}

	private ComputeDTO findByResourceId(String resourceId, Set<ComputeDTO> systems) {
		
		for (ComputeDTO system: systems) {
			if (system.getResourceId().equals(resourceId)) {
				return system;
			}
		}
		return null;
	}
}
