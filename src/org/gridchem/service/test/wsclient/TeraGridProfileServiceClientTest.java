package org.gridchem.service.test.wsclient;

import java.util.List;

import org.gridchem.service.sync.iis.beans.*;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.wsclients.iis.TeraGridProfileServiceClient;

public class TeraGridProfileServiceClientTest extends GMSTestCase {

	TeraGridProfileServiceClient client;
	public TeraGridProfileServiceClientTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		client = new TeraGridProfileServiceClient(TEST_MYPROXY_USERNAME, TEST_MYPROXY_PASSWORD);
	}

	public void testGetResources() {
		List<SystemDTO> resources = client.getResources();
		assertFalse(resources.isEmpty());
		for (SystemDTO system: resources) {
			System.out.println(system.toString());
		}
	}

	public void testGetResource() {
		SystemDTO resource = client.getResource("ranger.tacc.teragrid.org");
		assertNotNull(resource);
	}

	public void testGetProjects() {
		List<Allocation> allocs = client.getProjects();
		assertFalse(allocs.isEmpty());
		for (Allocation alloc: allocs) {
			System.out.println(alloc.toString());
		}
	}

	public void testGetProject() {
		Allocation project = client.getProjects().get(0);
		Allocation alloc = client.getProject(project.getProjectId());
		assertNotNull(alloc);
	}

	public void testGetCollaborators() {
		List<Collaborator> collabs = client.getCollaborators();
		assertFalse(collabs.isEmpty());
		for (Collaborator collab: collabs) {
			System.out.println(collab.toString());
		}
	}

	public void testGetCollaboratorsForProject() {
		List<Collaborator> collabs = client.getCollaboratorsForProject(client.getProjects().get(0).getProjectId());
		assertFalse(collabs.isEmpty());
		for (Collaborator collab: collabs) {
			System.out.println(collab.toString());
		}
	}

	public void testGetUser() {
		Profile profile = client.getUser();
		assertNotNull(profile);
	}

}
