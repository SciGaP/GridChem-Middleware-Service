package org.gridchem.service.test.service;

import java.util.List;

import org.gridchem.service.ResourceService;
import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SiteException;
import org.gridchem.service.impl.ResourceServiceImpl;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

public class ResourceServiceTest extends GMSTestCase {

	private ResourceService service; 
	
	public ResourceServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		service = new ResourceServiceImpl();
	}

	public void testGetResourcesNullToken() {
		try {
			service.getResources(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetResourcesEmptyToken() {
		try {
			service.getResources("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetResourcesInvalidToken() {
		try {
			service.getResources("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetResources() {
		String sResources = service.getResources(TEST_SESSIONTOKEN);
		
		assertNotNull(sResources);
		
		assertFalse(((List<ResourceBean>)
				ServiceUtil.xstream.fromXML(sResources)).isEmpty());
		
	}

	public void testGetComputeResourcesNullToken() {
		try {
			service.getComputeResources(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetComputeResourcesEmptyToken() {
		try {
			service.getComputeResources("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetComputeResourcesInvalidToken() {
		try {
			service.getComputeResources("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetComputeResources() {
		String sResources = service.getComputeResources(TEST_SESSIONTOKEN);
		
		assertNotNull(sResources);
		
		assertFalse(((List<ComputeBean>)
				ServiceUtil.xstream.fromXML(sResources)).isEmpty());
		
	}

	public void testGetComputeResourcesForSiteNullToken() {
		try {
			service.getComputeResourcesForSite(null,TEST_SITENAME);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testGetComputeResourcesForSiteEmptyToken() {
		try {
			service.getComputeResourcesForSite("",TEST_SITENAME);
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testGetComputeResourcesForSiteInvalidToken() {
		try {
			service.getComputeResourcesForSite("-1",TEST_SITENAME);
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testGetComputeResourcesForSiteNullSite() {
		try {
			service.getComputeResourcesForSite(TEST_SESSIONTOKEN,null);
			fail("Null site should throw a session exception");
		} catch (SiteException e) {
		} catch (Exception e) {
			fail("Null site should throw a session exception");
		}
	}
	
	public void testGetComputeResourcesForSiteEmptySite() {
		try {
			service.getComputeResourcesForSite(TEST_SESSIONTOKEN,"");
			fail("Empty site should throw a session exception");
		} catch (SiteException e) {
		} catch (Exception e) {
			fail("Empty site should throw a session exception");
		}
	}
	
	public void testGetComputeResourcesForSiteInvalidSite() {
		try {
			service.getComputeResourcesForSite(TEST_SESSIONTOKEN,"-1");
			fail("Invalid site should throw a session exception");
		} catch (SiteException e) {
		} catch (Exception e) {
			fail("Invalid site should throw a session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetComputeResourcesForSite() throws ResourceException, SessionException, ProviderException, SiteException, PermissionException, ParameterException {
		
		String sResources = service.getComputeResourcesForSite(TEST_SESSIONTOKEN,TEST_SITENAME);
		
		assertNotNull(sResources);
		
		assertFalse(((List<ComputeBean>)
				ServiceUtil.xstream.fromXML(sResources)).isEmpty());
	}
	
	public void testGetStorageResourcesNullToken() {
		try {
			service.getStorageResources(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetStorageResourcesEmptyToken() {
		try {
			service.getStorageResources("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetStorageResourcesInvalidToken() {
		try {
			service.getStorageResources("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetStorageResources() {
		String sResources = service.getStorageResources(TEST_SESSIONTOKEN);
		
		assertNotNull(sResources);
		
		assertFalse(((List<StorageBean>)
				ServiceUtil.xstream.fromXML(sResources)).isEmpty());
	}

	public void testGetStorageResourcesForSiteNullToken() {
		try {
			service.getStorageResourcesForSite(null,TEST_SITENAME);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testGetStorageResourcesForSiteEmptyToken() {
		try {
			service.getStorageResourcesForSite("",TEST_SITENAME);
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testGetStorageResourcesForSiteInvalidToken() {
		try {
			service.getStorageResourcesForSite("-1",TEST_SITENAME);
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testGetStorageResourcesForSiteNullSite() {
		try {
			service.getStorageResourcesForSite(TEST_SESSIONTOKEN,null);
			fail("Null site should throw a session exception");
		} catch (SiteException e) {
		} catch (Exception e) {
			fail("Null site should throw a session exception");
		}
	}
	
	public void testGetStorageResourcesForSiteEmptySite() {
		try {
			service.getStorageResourcesForSite(TEST_SESSIONTOKEN,"");
			fail("Empty site should throw a session exception");
		} catch (SiteException e) {
		} catch (Exception e) {
			fail("Empty site should throw a session exception");
		}
	}
	
	public void testGetStorageResourcesForSiteInvalidSite() {
		try {
			service.getStorageResourcesForSite(TEST_SESSIONTOKEN,"-1");
			fail("Invalid site should throw a session exception");
		} catch (SiteException e) {
		} catch (Exception e) {
			fail("Invalid site should throw a session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetStorageResourcesForSite() throws ResourceException, SessionException, ProviderException, SiteException {
		String sResources = service.getStorageResourcesForSite(TEST_SESSIONTOKEN,TEST_SITENAME);
		
		assertNotNull(sResources);
		
		assertFalse(((List<StorageBean>)
				ServiceUtil.xstream.fromXML(sResources)).isEmpty());
	}

}
