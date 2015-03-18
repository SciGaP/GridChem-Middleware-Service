package org.gridchem.service.test.service;

import java.util.List;

import org.gridchem.service.SoftwareService;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.SoftwareBean;
import org.gridchem.service.beans.SoftwareInstallationBean;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SoftwareException;
import org.gridchem.service.impl.SoftwareServiceImpl;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

public class SoftwareServiceTest extends GMSTestCase {

	private SoftwareService service;
	
	public SoftwareServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		service = new SoftwareServiceImpl();
	}

	public void testGetAllSoftwareNullToken() {
		try {
			service.getAllSoftware(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetAllSoftwareEmptyToken() {
		try {
			service.getAllSoftware("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetAllSoftwareInvalidToken() {
		try {
			service.getAllSoftware("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetAllSoftware() {
		String sSoftware = service.getAllSoftware(TEST_SESSIONTOKEN);
		
		assertNotNull(sSoftware);
		
		assertFalse(((List<SoftwareBean>)ServiceUtil.xstream.fromXML(sSoftware)).isEmpty());
	}

	
	public void testGetSoftwareInstallationsForResourceNullToken() {
		try {
			service.getSoftwareInstallationsForResource(null,TEST_SYSTEMNAME);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForResourceEmptyToken() {
		try {
			service.getSoftwareInstallationsForResource("",TEST_SYSTEMNAME);
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForResourceInvalidToken() {
		try {
			service.getSoftwareInstallationsForResource("-1",TEST_SYSTEMNAME);
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForResourceNullSystem() {
		try {
			service.getSoftwareInstallationsForResource(TEST_SESSIONTOKEN,null);
			fail("Null resource should throw a session exception");
		} catch (ResourceException e) {
		} catch (Exception e) {
			fail("Null resource should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForResourceEmptySystem() {
		try {
			service.getSoftwareInstallationsForResource(TEST_SESSIONTOKEN,"");
			fail("Empty resource should throw a session exception");
		} catch (ResourceException e) {
		} catch (Exception e) {
			fail("Empty resource should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForResourceInvalidSystem() {
		try {
			service.getSoftwareInstallationsForResource(TEST_SESSIONTOKEN,"-1");
			fail("Invalid resource should throw a session exception");
		} catch (ResourceException e) {
		} catch (Exception e) {
			fail("Invalid resource should throw a session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetSoftwareInstallationsForResource() throws ResourceException, PermissionException, SessionException, ProviderException, ParameterException {
		String sInstalls = service.getSoftwareInstallationsForResource(TEST_SESSIONTOKEN,TEST_SYSTEMNAME);
		
		assertNotNull(sInstalls);
		
		assertTrue(((List<SoftwareInstallationBean>)
				ServiceUtil.xstream.fromXML(sInstalls)).size() == 1);
	}

	public void testGetSoftwareInstallationsNullToken() {
		try {
			service.getSoftwareInstallations(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetSoftwareInstallationsEmptyToken() {
		try {
			service.getSoftwareInstallations("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testGetSoftwareInstallationsInvalidToken() {
		try {
			service.getSoftwareInstallations("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetSoftwareInstallations() {
		String sInstalls = service.getSoftwareInstallations(TEST_SESSIONTOKEN);
		
		assertNotNull(sInstalls);
		
		assertFalse(((List<SoftwareInstallationBean>)
				ServiceUtil.xstream.fromXML(sInstalls)).isEmpty());
	}

	
	public void testGetSoftwareInstallationsForSoftwareNullToken() {
		try {
			service.getSoftwareInstallationsForSoftware(null,TEST_SOFTWARENAME);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForSoftwareEmptyToken() {
		try {
			service.getSoftwareInstallationsForSoftware("",TEST_SOFTWARENAME);
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForSoftwareInvalidToken() {
		try {
			service.getSoftwareInstallationsForSoftware("-1",TEST_SOFTWARENAME);
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForSoftwareNullSystem() {
		try {
			service.getSoftwareInstallationsForSoftware(TEST_SESSIONTOKEN,null);
			fail("Null resource should throw a session exception");
		} catch (SoftwareException e) {
		} catch (Exception e) {
			fail("Null resource should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForSoftwareEmptySystem() {
		try {
			service.getSoftwareInstallationsForSoftware(TEST_SESSIONTOKEN,"");
			fail("Empty resource should throw a session exception");
		} catch (SoftwareException e) {
		} catch (Exception e) {
			fail("Empty resource should throw a session exception");
		}
	}
	
	public void testGetSoftwareInstallationsForSoftwareInvalidSystem() {
		try {
			service.getSoftwareInstallationsForSoftware(TEST_SESSIONTOKEN,"-1");
			fail("Invalid resource should throw a session exception");
		} catch (SoftwareException e) {
		} catch (Exception e) {
			fail("Invalid resource should throw a session exception");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetSoftwareInstallationsForSoftware() throws PermissionException, SessionException, ProviderException, SoftwareException, ParameterException {
		String sResources = service.getSoftwareInstallationsForSoftware(TEST_SESSIONTOKEN,TEST_SOFTWARENAME);
		
		assertNotNull(sResources);
		
		assertTrue(((List<ResourceBean>)
				ServiceUtil.xstream.fromXML(sResources)).size() == 1);
	}
	
	
}
