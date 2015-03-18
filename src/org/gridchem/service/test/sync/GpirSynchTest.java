package org.gridchem.service.test.sync;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.LoadBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.sync.gpir.GpirDiscoveryAdaptor;

public class GpirSynchTest extends TestCase{

	// ********************************************************** //
	/** 
	 * Test that a resource array is return from the call to gpir
	 */
	public void testGpirSyncNotNullResources() throws Exception {
		
		GpirDiscoveryAdaptor gpirSync = new GpirDiscoveryAdaptor();
		List<ResourceBean> beans = gpirSync.retrieveResources();
		
		assertNotNull(beans);
	}
	
	/** 
	 * Test that the resource array returned from the call to gpir 
	 * is not empty
	 */
	public void testGpirSyncNotEmptyResources() throws Exception {
		
		GpirDiscoveryAdaptor gpirSync = new GpirDiscoveryAdaptor();
		List<ResourceBean> beans = gpirSync.retrieveResources();
		
		assertTrue(beans.size() > 0);
	}
	
	/** 
	 * Test that resource loads from GPIR are not null
	 */
	public void testGpirSyncNotNullLoads() throws Exception {
		
		GpirDiscoveryAdaptor gpirSync = new GpirDiscoveryAdaptor();
		List<ResourceBean> beans = gpirSync.retrieveResources();
		
		for (ResourceBean resourceBean: beans) {
			if (resourceBean instanceof ComputeBean) {
				assertNotNull(((ComputeBean)resourceBean).getLoad());
			}
		}
	}
	
	/** 
	 * Test that resource loads from GPIR are set properly
	 */
	public void testGpirSyncValidLoads() throws Exception {
		
		GpirDiscoveryAdaptor gpirSync = new GpirDiscoveryAdaptor();
		List<ResourceBean> beans = gpirSync.retrieveResources();
		
		for (ResourceBean resourceBean: beans) {
			if (resourceBean instanceof ComputeBean) {
				LoadBean loadBean = ((ComputeBean)resourceBean).getLoad();
				assertTrue(loadBean.getCpu() >= 0);
				assertTrue(loadBean.getDisk() >= 0);
				assertTrue(loadBean.getJobsOther() >= 0);
				assertTrue(loadBean.getJobsQueued() >= 0);
				assertTrue(loadBean.getJobsRunning() >= 0);
				assertTrue(loadBean.getMemory() >= 0);
				assertTrue(loadBean.getQueue() >= 0);
			}
		}
	}
	
	/** 
	 * Test that resources status values from GPIR are set to null.
	 */
	public void testGpirSyncNullStatus() throws Exception {
		
		GpirDiscoveryAdaptor gpirSync = new GpirDiscoveryAdaptor();
		List<ResourceBean> beans = gpirSync.retrieveResources();
		
		for (ResourceBean bean: beans) {
			assertNull(bean.getStatus());
		}
		
	}
	
	// ********************************************************** //

	public GpirSynchTest(String x) {
		super(x);
	}

	public static Test suite() {
		return new TestSuite(GpirSynchTest.class);
	}

	public static void main(String[] args) throws Exception {
		TestRunner.run( suite() );
	}

}
