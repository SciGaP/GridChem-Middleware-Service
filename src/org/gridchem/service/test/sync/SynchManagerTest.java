package org.gridchem.service.test.sync;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.sync.IISDiscoveryAdaptor;
import org.gridchem.service.sync.SyncManager;
import org.gridchem.service.sync.gpir.GpirDiscoveryAdaptor;
import org.hibernate.criterion.Restrictions;

public class SynchManagerTest extends TestCase{

	// ********************************************************** //
	
	/** 
	 * Test that resources load values actually synced with the db.
	 */
	@SuppressWarnings("unchecked")
	public void testGpirSync() throws Exception {
		
		GpirDiscoveryAdaptor gpirSync = new GpirDiscoveryAdaptor();
		List<ResourceBean> beans = gpirSync.retrieveResources();
		
		// resource loads should be synced after this call
		SyncManager.sync(beans);
		
		// verify they are synced by checking the load value
		// matches the value in the db.
		for (ResourceBean bean: beans) {
			
			if (bean instanceof StorageBean) continue;
			
			// find the resource
			HibernateUtil.beginTransaction();
			String name = bean.getName();
			if (name.indexOf(' ') != -1) {
				name = bean.getName().split(" ")[1];
			}
			
			List<ComputeResource> resources = (List<ComputeResource>)HibernateUtil.getSession()
				.createCriteria(ComputeResource.class)
				.add(Restrictions.ilike("name",name))
				.list();
			
			// if we can't resolve the bean, let it be.
			if (resources == null || resources.isEmpty()) continue;
			
			ComputeResource compute = resources.get(0);
			
			assertEquals(compute.getLoad().getCpu(),((ComputeBean)bean).getLoad().getCpu());
			assertEquals(compute.getLoad().getDisk(),((ComputeBean)bean).getLoad().getDisk());
			assertEquals(compute.getLoad().getJobsOther(),((ComputeBean)bean).getLoad().getJobsOther());
			assertEquals(compute.getLoad().getJobsQueued(),((ComputeBean)bean).getLoad().getJobsQueued());
			assertEquals(compute.getLoad().getJobsRunning(),((ComputeBean)bean).getLoad().getJobsRunning());
			assertEquals(compute.getLoad().getMemory(),((ComputeBean)bean).getLoad().getMemory());
			assertEquals(compute.getLoad().getQueue(),((ComputeBean)bean).getLoad().getQueue());
		}
		
	}
	
	/** 
	 * Test that resources status values actually synced with the db.
	 */
	@SuppressWarnings("unchecked")
	public void testIISSync() throws Exception {
		
		IISDiscoveryAdaptor iisSynch = new IISDiscoveryAdaptor();
		List<ResourceBean> beans = iisSynch.retrieveResources();
		
		// resource loads should be synced after this call
		SyncManager.sync(beans);
		
		// verify they are synced by checking the status value
		// matches the value in the db.
		for (ResourceBean bean: beans) {
			
			if (bean instanceof StorageBean) continue;
			
			// find the resource
			HibernateUtil.beginTransaction();
			String name = bean.getName();
			if (name.indexOf(' ') != -1) {
				name = bean.getName().split(" ")[1];
			}
			
			List<ComputeResource> resources = (List<ComputeResource>)HibernateUtil.getSession()
				.createCriteria(ComputeResource.class)
				.add(Restrictions.ilike("name",name))
				.list();
			
			// if we can't resolve the bean, let it be.
			if (resources == null || resources.isEmpty()) continue;
			
			ComputeResource compute = resources.get(0);
		
			assertEquals(compute.getStatus().name(),bean.getStatus().name());
		}
		
	}
	
	// ********************************************************** //

	public SynchManagerTest(String x) {
		super(x);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		
//		suite.addTest(new TestSuite(GpirSynchTest.class));
		suite.addTest(new TestSuite(IISSynchTest.class));
//		suite.addTest(new TestSuite(SynchManagerTest.class));
		
		return suite;
	}

	public static void main(String[] args) throws Exception {
		TestRunner.run( suite() );
	}

}
