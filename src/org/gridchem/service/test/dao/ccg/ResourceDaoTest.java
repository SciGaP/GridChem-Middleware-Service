/**
 * 
 */
package org.gridchem.service.test.dao.ccg;

import java.util.List;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.QueueBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.SessionDao;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.Settings;

/**
 * Test class for interacting with the GMSDB resource tables
 * 
 * @author dooley
 *
 */
public class ResourceDaoTest extends GMSTestCase {
	
	private ResourceDao resourceDao;
//	private ProjectDao projectDao;
//	private UserProject up;
//	private ProjectResource pr;
//	private UserProjectResource upr;
	
	/**
	 * @param name
	 */
	public ResourceDaoTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.test.GMSTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		resourceDao = new ResourceDao(session);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test null returned for local username on null compute resource name
	 */
	public void testFindLocalUsernameForComputeResourceNull() {
		assertNull(resourceDao.findLocalUsernameForComputeResource(null));
	}
	
	/**
	 * Test null returned for local username on empty compute resource name
	 */
	public void testFindLocalUsernameForComputeResourceEmpty() {
		assertNull(resourceDao.findLocalUsernameForComputeResource(""));
	}
	
	/**
	 * Test null returned for local username on invalid compute resource name
	 */
	public void testFindLocalUsernameForComputeResourceInvalid() {
		assertNull(resourceDao.findLocalUsernameForComputeResource("-1"));
	}
	
	/**
	 * Test username found for compute resource
	 */
	public void testFindLocalUsernameForComputeResource() {
		assertEquals(resourceDao.findLocalUsernameForComputeResource(TEST_SYSTEMNAME),Settings.COMMUNITY_USERNAME);
	}

	/**
	 * Test every compute resource has a local username associated with it for the user
	 */
	public void testLocalUsernameForEveryComputeResource() {
		
		List<ComputeBean> resources = resourceDao.getComputeResources();
		
		for (ComputeBean resource: resources) {
			assertEquals(resourceDao.findLocalUsernameForComputeResource(resource.getName()),Settings.COMMUNITY_USERNAME);
		}
	}
	
	/**
	 * Test empty list returned for null resource name
	 */
	public void testGetAllQueuesForResourceNull() {
		List<QueueBean> beans = resourceDao.getQueuesForResource(null);
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test empty list returned for empty resource name
	 */
	public void testGetAllQueuesForResourceEmpty() {
		List<QueueBean> beans = resourceDao.getQueuesForResource("");
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test empty list returned for invalid resource name
	 */
	public void testGetAllQueuesForResourceInvalid() {
		List<QueueBean> beans = resourceDao.getQueuesForResource("-1");
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test list of queues returned for resource
	 */
	public void testGetAllQueuesForResource() {
		List<QueueBean> beans = resourceDao.getQueuesForResource(TEST_SYSTEMNAME);
		assertFalse(beans.isEmpty());
	}
	
	/**
	 * Test that there is at least one queue present for every compute resource
	 */
	public void testQueueExistsForEveryComputeResource() {
		List<ComputeBean> resources = resourceDao.getComputeResources();
		
		for (ComputeBean resource: resources) {
			assertFalse(resourceDao.getQueuesForResource(resource.getName()).isEmpty());
		}
	}

	/**
	 * Test list of all resources is returned
	 */
	public void testGetAllResources() {
		List<ResourceBean> beans = resourceDao.getAllSessionResources();
		assertFalse(beans.isEmpty());
	}

	/**
	 * Test null is returned for the null resource
	 */
	public void testGetComputeResourceNull() {
		assertNull(resourceDao.getComputeResource(null));
	}
	
	/**
	 * Test null is returned for the empty resource
	 */
	public void testGetComputeResourceEmpty() {
		assertNull(resourceDao.getComputeResource(""));
	}
	
	/**
	 * Test null is returned for the invalid resource
	 */
	public void testGetComputeResourceInvalid() {
		assertNull(resourceDao.getComputeResource("-1"));
	}
	
	/**
	 * Test a compute resource is returned for the named resource
	 */
	public void testGetComputeResource() {
		ComputeBean bean = resourceDao.getComputeResource(TEST_SYSTEMNAME);
		assertNotNull(bean);
		assertFalse(bean.getAllocations().isEmpty());
	}
	
	/**
	 * Test a compute resource is returned for the named resource
	 */
	public void testInternalGetComputeResource() {
		assertNotNull(resourceDao._getComputeResource(TEST_SYSTEMNAME));
		assertTrue(resourceDao._getComputeResource(TEST_SYSTEMNAME) instanceof ComputeResource);
	}

	/**
	 * Test null is returned for the null hostname
	 */
	public void testGetComputeResourceByHostnameNull() {
		assertNull(resourceDao.getComputeResourceByHostname(null));
	}
	
	/**
	 * Test null is returned for the empty hostname
	 */
	public void testGetComputeResourceByHostnameEmpty() {
		assertNull(resourceDao.getComputeResourceByHostname(""));
	}
	
	/**
	 * Test null is returned for the invalid hostname
	 */
	public void testGetComputeResourceByHostnameInvalid() {
		assertNull(resourceDao.getComputeResourceByHostname("-1"));
	}
	
	/**
	 * Test a compute resource is returned for the given hostname
	 */
	public void testGetComputeResourceByHostname() {
		ComputeBean bean = resourceDao.getComputeResource(TEST_SYSTEMNAME);
		assertNotNull(resourceDao.getComputeResourceByHostname(bean.getHostname()));
	}

	/**
	 * Test a list of compute resources is returned for the null hostname
	 */
	public void testGetComputeResources() {
		List<ComputeBean> beans = resourceDao.getComputeResources();
		assertNotNull(beans);
		assertFalse(beans.isEmpty());
		for(ComputeBean bean: beans) {
			assertFalse(bean.getAllocations().isEmpty());
		}
	}
	
	/**
	 * Test a list of compute resources is returned for the null hostname
	 */
	public void testGetProjectComputeResourcesProjectIdNull() {
		assertNull(resourceDao.getProjectComputeResources(null));
	}
	
	/**
	 * Test a list of compute resources is returned for the null hostname
	 */
	public void testGetProjectComputeResourcesProjectIdInvalid() {
		assertNull(resourceDao.getProjectComputeResources(new Long(-1)));
	}
	
	/**
	 * Test a list of compute resources is returned for the null hostname
	 */
	public void testGetProjectComputeResources() {
//		GMSSession session = SessionDao.getByToken("126117119838400.559");
//		resourceDao = new ResourceDao(session);
//		List<ComputeBean> beans = resourceDao.getProjectComputeResources(new Long(8));
		List<ComputeBean> beans = resourceDao.getProjectComputeResources(project.getId());
		assertNotNull(beans);
		assertFalse(beans.isEmpty());
		for(ComputeBean bean: beans) {
			assertFalse(bean.getAllocations().isEmpty());
		}
	}

	/**
	 * Test an empty list of compute resources is returned for the null site
	 */
	public void testGetComputeResourcesAtSiteNull() {
		List<ComputeBean> beans = resourceDao.getComputeResourcesAtSite(null);
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test an empty list of compute resources is returned for the empty site
	 */
	public void testGetComputeResourcesAtSiteEmpty() {
		List<ComputeBean> beans = resourceDao.getComputeResourcesAtSite("");
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test an empty list of compute resources is returned for the invalid site
	 */
	public void testGetComputeResourcesAtSiteInvalid() {
		List<ComputeBean> beans = resourceDao.getComputeResourcesAtSite("-1");
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test a list of compute resources is returned for the given site
	 */
	public void testGetComputeResourcesAtSite() {
		ComputeBean bean = resourceDao.getComputeResource(TEST_SYSTEMNAME);
		List<ComputeBean> beans = resourceDao.getComputeResourcesAtSite(bean.getSite().getName());
		assertFalse(beans.isEmpty());
	}

	/**
	 * Test every site has a compute resource
	 */
	public void testGetComputeResourceAtEverySite() {
		List<ComputeBean> resources = resourceDao.getComputeResources();
		
		for (ComputeBean resource: resources) {
			List<ComputeBean> beans = resourceDao.getComputeResourcesAtSite(resource.getSite().getName());
			assertFalse(beans.isEmpty());
		}
	}
	
	/**
	 * Test null is returned for the null resource name
	 */
	public void testGetDefaultQueueForResourceNull() {
		assertNull(resourceDao.getDefaultQueueForResource(null));
	}
	
	/**
	 * Test null is returned for the empty resource name
	 */
	public void testGetDefaultQueueForResourceEmpty() {
		assertNull(resourceDao.getDefaultQueueForResource(""));
	}
	
	/**
	 * Test null is returned for the invalid resource name
	 */
	public void testGetDefaultQueueForResourceInvalid() {
		assertNull(resourceDao.getDefaultQueueForResource("-1"));
	}
	
	/**
	 * Test a queue is returned for the given resource name
	 */
	public void testGetDefaultQueueForResource() {
		assertNotNull(resourceDao.getDefaultQueueForResource(TEST_SYSTEMNAME));
	}
	
	/**
	 * Test every compute resource has a default queue
	 */
	public void testDefaultQueueForEveryResource() {
		
		List<ComputeBean> resources = resourceDao.getComputeResources();
		
		for (ComputeBean resource: resources) {
			assertNotNull(resourceDao.getDefaultQueueForResource(resource.getName()));
		}
	}
	
	/**
	 * Test null is returned for the null resource name
	 */
	public void testGetDefaultStorageResourceForComputeResourceNull() {
		assertNull(resourceDao.getDefaultStorageResourceForComputeResource(null));
	}
	
	/**
	 * Test null is returned for the empty resource name
	 */
	public void testGetDefaultStorageResourceForComputeResourceEmpty() {
		assertNull(resourceDao.getDefaultStorageResourceForComputeResource(""));
	}
	
	/**
	 * Test null is returned for the invalid resource name
	 */
	public void testGetDefaultStorageResourceForComputeResourceInvalid() {
		assertNull(resourceDao.getDefaultStorageResourceForComputeResource("-1"));
	}

	/**
	 * Test a storage resource is returned for the given resource name
	 */
	public void testGetDefaultStorageResourceForComputeResource() {
		assertNotNull(resourceDao.getDefaultStorageResourceForComputeResource(TEST_SYSTEMNAME));
	}
	
	/**
	 * Test every compute resource has a default storage resource
	 */
	public void testDefaultStorageResourceForEveryComputeResource() {
		List<ComputeBean> resources = resourceDao.getComputeResources();
		
		for (ComputeBean resource: resources) {
			assertNotNull(resourceDao.getDefaultStorageResourceForComputeResource(resource.getName()));
		}
	}

	/**
	 * Test return null for storage resource with null name
	 */
	public void testGetStorageResourceNull() {
		assertNull(resourceDao.getStorageResource(null));
	}
	
	/**
	 * Test return null for storage resource with empty name
	 */
	public void testGetStorageResourceEmpty() {
		assertNull(resourceDao.getStorageResource(""));
	}
	
	/**
	 * Test return null for storage resource with invalid name
	 */
	public void testGetStorageResourceInvalid() {
		assertNull(resourceDao.getStorageResource("-1"));
	}
	
	/**
	 * Test return storage resource with given name
	 */
	public void testGetStorageResource() {
		assertNotNull(resourceDao.getStorageResource(TEST_STORAGENAME));
	}

	/**
	 * Test return list of storage resources
	 */
	public void testGetStorageResources() {
		List<StorageBean> beans = resourceDao.getStorageResources();
		assertNotNull(beans);
		assertFalse(beans.isEmpty());
	}

	/**
	 * Test return empty list of storage resources at null site name
	 */
	public void testGetStorageResourcesAtSiteNull() {
		List<StorageBean> beans = resourceDao.getStorageResourcesAtSite(null);
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test return empty list of storage resources at empty site name
	 */
	public void testGetStorageResourcesAtSiteEmpty() {
		List<StorageBean> beans = resourceDao.getStorageResourcesAtSite("");
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test return empty list of storage resources at invalid site name
	 */
	public void testGetStorageResourcesAtSiteInavlid() {
		List<StorageBean> beans = resourceDao.getStorageResourcesAtSite("-1");
		assertTrue(beans.isEmpty());
	}
	
	/**
	 * Test return list of storage resources at named site
	 */
	public void testGetStorageResourcesAtSite() {
		ComputeBean bean = resourceDao.getComputeResource(TEST_SYSTEMNAME);
		List<StorageBean> beans = resourceDao.getStorageResourcesAtSite(bean.getSite().getName());
		assertNotNull(beans);
		assertFalse(beans.isEmpty());
	}
	
	/**
	 * Test every site has a storage resource
	 */
	public void testGetStorageResourceAtEverySite() {
		List<ComputeBean> resources = resourceDao.getComputeResources();
		
		for (ComputeBean resource: resources) {
			List<StorageBean> beans = resourceDao.getStorageResourcesAtSite(resource.getSite().getName());
			assertNotNull(beans);
			assertFalse(beans.isEmpty());
		}
	}

	/**
	 * Test valid session user had access to null compute resource name
	 */
	public void testUserHasAccessToComputeResourceNull() {
		assertFalse(resourceDao.userHasAccessToComputeResource(null));
	}
	
	/**
	 * Test valid session user had access to empty compute resource name
	 */
	public void testUserHasAccessToComputeResourceEmpty() {
		assertFalse(resourceDao.userHasAccessToComputeResource(""));
	}
	
	/**
	 * Test valid session user had access to invalid compute resource name
	 */
	public void testUserHasAccessToComputeResourceInvalid() {
		assertFalse(resourceDao.userHasAccessToComputeResource("-1"));
	}
	
	/**
	 * Test null session user does not have access to valid compute resource name
	 */
	public void testUserHasAccessToComputeResourceNullUserId() {
		Long id = session.getUserId();
		session.setUserId(null);
		resourceDao = new ResourceDao(session);
		assertFalse(resourceDao.userHasAccessToComputeResource(null));
		session.setUserId(id);
	}
	
	/**
	 * Test invalid session user does not have access to valid compute resource name
	 */
	public void testUserHasAccessToComputeResourceInvalidUserId() {
		Long id = session.getUserId();
		session.setUserId(new Long("-1"));
		resourceDao = new ResourceDao(session);
		assertFalse(resourceDao.userHasAccessToComputeResource(""));
		session.setUserId(id);
	}
	
	/**
	 * Test valid session user had access to valid compute resource name
	 */
	public void testUserHasAccessToComputeResource() {
		assertTrue(resourceDao.userHasAccessToComputeResource(TEST_SYSTEMNAME));
	}

	/**
	 * Test valid session user had access to null storage resource name
	 */
	public void testUserHasAccessToStorageResourceNull() {
		assertFalse(resourceDao.userHasAccessToStorageResource(null));
	}
	
	/**
	 * Test valid session user had access to empty storage resource name
	 */
	public void testUserHasAccessToStorageResourceEmpty() {
		assertFalse(resourceDao.userHasAccessToStorageResource(""));
	}
	
	/**
	 * Test valid session user had access to invalid storage resource name
	 */
	public void testUserHasAccessToStorageResourceInvalid() {
		assertFalse(resourceDao.userHasAccessToStorageResource("-1"));
	}
	
	/**
	 * Test null session user does not have access to valid storage resource name
	 */
	public void testUserHasAccessToStorageResourceNullUserId() {
		Long id = session.getUserId();
		session.setUserId(null);
		resourceDao = new ResourceDao(session);
		assertFalse(resourceDao.userHasAccessToStorageResource(null));
		session.setUserId(id);
	}
	
	/**
	 * Test invalid session user does not have access to valid storage resource name
	 */
	public void testUserHasAccessToStorageResourceInvalidUserId() {
		Long id = session.getUserId();
		session.setUserId(new Long("-1"));
		resourceDao = new ResourceDao(session);
		assertFalse(resourceDao.userHasAccessToStorageResource(""));
		session.setUserId(id);
	}
	
	
	/**
	 * Test valid session user had access to valid storage resource name
	 */
	public void testUserHasAccessToStorageResource() {
		assertTrue(resourceDao.userHasAccessToStorageResource(TEST_STORAGENAME));
	}
	
}
