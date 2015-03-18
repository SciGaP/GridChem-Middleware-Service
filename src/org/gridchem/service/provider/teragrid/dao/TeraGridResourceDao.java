/**
 * 
 */
package org.gridchem.service.provider.teragrid.dao;

import java.util.ArrayList;
import java.util.List;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.QueueBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.User;
import org.gridchem.service.sync.iis.beans.SystemDTO;
import org.gridchem.service.wsclients.iis.TeraGridProfileServiceClient;

/**
 * @author dooley
 *
 */
public class TeraGridResourceDao {
	
	private TeraGridProfileServiceClient client;
	
	public TeraGridResourceDao(GMSSession session) {
		User user = new UserDao(session)._get();
		client = new TeraGridProfileServiceClient(user.getUsername().substring(3),user.getPassword());
	}

	
//	public String findLocalUsernameForComputeResource(String systemName) {
//		SystemDTO system = client.getResource(systemName);
//		if (system == null) {
//			return null;
//		} else {
//			return system.getLocalUsername();
//		}
//	}
	
	// won't ever be called
	public List<QueueBean> getAllQueuesForResource(String systemName) {
		// TODO query webmds for queue info
		throw new InfrastructureException("Not implemented.");
	}

	public List<ResourceBean> getAllResources() {
		List<SystemDTO> systems = client.getResources();
		List<ResourceBean> beans = new ArrayList<ResourceBean>();
		
		for (SystemDTO system: systems) {
			beans.add(system.toBean());
		}
		
		return beans;
	}

	public ComputeBean getComputeResource(String systemName) {
		SystemDTO system = client.getResource(systemName);
		if (system == null) return null;
		return system.toBean();
	}
	
	public List<SystemDTO> _getComputeResources() {
		List<SystemDTO> systems = client.getResources();
		return systems;
	}

	public List<ComputeBean> getComputeResources() {
		List<SystemDTO> systems = _getComputeResources();
		List<ComputeBean> beans = new ArrayList<ComputeBean>();
		
		for (SystemDTO system: systems) {
			beans.add(system.toBean());
		}
		
		return beans;
	}

	public List<ComputeBean> getComputeResourcesAtSite(String siteName) {
		List<SystemDTO> systems = client.getResources();
		List<ComputeBean> beans = new ArrayList<ComputeBean>();
		
		for (SystemDTO system: systems) {
			if (system.getSite().equalsIgnoreCase(siteName)) {
				beans.add(system.toBean());
			}
		}
		
		return beans;
	}

	public QueueBean getDefaultQueueForResource(String systemName) {
		// TODO query webmds for default queue on systemName
		throw new InfrastructureException("Not implemented.");
	}

	public StorageBean getDefaultStorageResourceForComputeResource(
			String systemName) {
		// TODO query webmds for storage resource at site mapping to systemName
		throw new InfrastructureException("Not implemented.");
	}

	public List<QueueBean> getQueuesForResource(String systemName) {
		// TODO query webmds for queues on systemName
		throw new InfrastructureException("Not implemented.");
	}

	public StorageBean getStorageResource(String storageName) {
		// TODO query webmds for storage resource with name = storageName
		throw new InfrastructureException("Not implemented.");
	}

	public List<StorageBean> getStorageResources() {
		// TODO query webmds for storage resources at any site.
		throw new InfrastructureException("Not implemented.");
	}

	public List<StorageBean> getStorageResourcesAtSite(String siteName) {
		// TODO query webmds for storage resources at site mapping to siteName
		throw new InfrastructureException("Not implemented.");
	}

	public boolean userHasAccessToComputeResource(String systemName) {
		return (client.getResource(systemName) != null);
	}

	public boolean userHasAccessToStorageResource(String storageName) {
		// TODO query webmds for site of storage resource with name = storageName
		// then query $profile to see if they have access to any resources at that site
		return false;
	}
	
	public String findLocalUsernameForComputeResource(String systemName) {
		for (SystemDTO system: _getComputeResources()) {
			if (system.getName().equals(systemName)) {
				return system.getLocalUsername();
			}
		}
		
		return null;
	}


}
