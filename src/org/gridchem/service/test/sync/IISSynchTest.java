package org.gridchem.service.test.sync;

import java.net.InetAddress;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.sync.IISDiscoveryAdaptor;

public class IISSynchTest extends TestCase{

	// ********************************************************** //
	
	/** 
	 * Test that the call to iis returns a resource array.
	 */
	public void testIISSyncNotNullResources() throws Exception {
		
		IISDiscoveryAdaptor iisSynch = new IISDiscoveryAdaptor();
		List<ResourceBean> beans = iisSynch.retrieveResources();
		
		assertNotNull(beans);
		
	}
	
	/** 
	 * Test that the call to iis returns a non empty resource array.
	 */
	public void testIISSyncNotEmptyResources() throws Exception {
		
		IISDiscoveryAdaptor iisSynch = new IISDiscoveryAdaptor();
		List<ResourceBean> beans = iisSynch.retrieveResources();
		
		assertTrue(beans.size() > 0);
		
	}
	
	/** 
	 * Test that the call to iis returns resource with non-null
	 * hostnames.
	 */
	public void testIISSyncNotNullHostnames() throws Exception {
		
		IISDiscoveryAdaptor iisSynch = new IISDiscoveryAdaptor();
		List<ResourceBean> beans = iisSynch.retrieveResources();
		
		// verify the resource has a valid hostname
		for (ResourceBean bean: beans) {
			assertNotNull(bean.getHostname());
		}
		
	}
	
	/** 
	 * Test that the call to iis returns resource with non-null
	 * hostnames.
	 */
	public void testIISSyncValidHostnames() throws Exception {
		
		IISDiscoveryAdaptor iisSynch = new IISDiscoveryAdaptor();
		List<ResourceBean> beans = iisSynch.retrieveResources();
		
		// verify the hostname is valid
		for (ResourceBean bean: beans) {
			InetAddress address = InetAddress.getByName(bean.getHostname());
			assertFalse(address.isLoopbackAddress());	
		}
		
	}
	
	/** 
	 * Test that the call to iis actually returns resources.
	 */
	public void testIISSyncNotNullStaus() throws Exception {
		
		IISDiscoveryAdaptor iisSynch = new IISDiscoveryAdaptor();
		List<ResourceBean> beans = iisSynch.retrieveResources();
		
		for (ResourceBean bean: beans) {
			assertNotNull(bean.getStatus());
		}
		
	}
	
	/** 
	 * Test that the call to iis actually returns resources.
	 */
	public void testIISSyncValidStaus() throws Exception {
		
		IISDiscoveryAdaptor iisSynch = new IISDiscoveryAdaptor();
		List<ResourceBean> beans = iisSynch.retrieveResources();
		
		for (ResourceBean bean: beans) {
			assertTrue(bean.getStatus().equals(ResourceStatusType.UP) || 
					bean.getStatus().equals(ResourceStatusType.DOWN));
		}
		
	}
    
	// ********************************************************** //

	public IISSynchTest(String x) {
		super(x);
	}

	public static Test suite() {
		return new TestSuite(IISSynchTest.class);
	}

	public static void main(String[] args) throws Exception {
		TestRunner.run( suite() );
	}

}
