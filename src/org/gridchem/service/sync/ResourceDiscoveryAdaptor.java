/**
 * 
 */
package org.gridchem.service.sync;

import java.util.List;

import org.gridchem.service.beans.ResourceBean;

/**
 * Interface to define how to synchronize resources.
 * 
 * @author dooley
 *
 */
public interface ResourceDiscoveryAdaptor {

	public List<ResourceBean> retrieveResources();
	
	
}
