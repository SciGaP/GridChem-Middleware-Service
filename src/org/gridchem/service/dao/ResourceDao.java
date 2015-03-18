package org.gridchem.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.QueueBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.model.CCGResource;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.ProjectResource;
import org.gridchem.service.model.Queue;
import org.gridchem.service.model.Site;
import org.gridchem.service.model.StorageResource;
import org.gridchem.service.model.UserProjectResource;
import org.gridchem.service.model.enumeration.ResourceType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;

public class ResourceDao extends AbstractDao {
	
	public ResourceDao(GMSSession session) {
		super(session);
	}
	
	public String findLocalUsernameForComputeResource(String systemName) {
		if (!ServiceUtil.isValid(systemName)) return null;
		
		if (_getCompute(systemName) == null) return null;
		
		// need to pull this info from $PROFILE or the DB.  Can't catch invalid
		// systemNames without it, really.
		return Settings.COMMUNITY_USERNAME;
	}

//	@SuppressWarnings("unchecked")
//	public List<QueueBean> getAllQueuesForResource(String systemName) {
//		List<QueueBean> beans = new ArrayList<QueueBean>();
//		String hql = "select hpc.queues from ComputeResource hpc " +
//				"where hpc.name = :name";
//		
//		List<Queue> queues = (List<Queue>)hSession.createQuery(hql)
//			.setString("name",systemName)
//			.list();
//
//		for (Queue q: queues) {
//			beans.add(q.toBean());
//		}
//		return beans;
//	}

	@SuppressWarnings("unchecked")
	public List<CCGResource> _getAllResources() {
		
		return (List<CCGResource>)hSession.createQuery("from CCGResource").list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ComputeResource> _getAllCompute() {
		
		return (List<ComputeResource>)hSession.createQuery("from ComputeResource").list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<StorageResource> _getAllStorage() {
		
		return (List<StorageResource>)hSession.createQuery("from StorageResource").list();
		
	}

	@SuppressWarnings("unchecked")
	public List<ResourceBean> getAllSessionResources() {
		
		List<ResourceBean> beans = new ArrayList<ResourceBean>();
		
		String hql = "select upr.resource from UserProjectResource upr " +
				"where upr.id.projectId = :pid " +
				"and upr.id.userId = :uid " + 
				"and upr.enabled = :enabled";
		
		List<CCGResource> systems = (List<CCGResource>)hSession.createQuery(hql)
			.setLong("pid",userSession.getProjectId())
			.setLong("uid",userSession.getUserId())
			.setBoolean("enabled",true)
			.list();
		
		if (systems.isEmpty()) return null;
		
		for (CCGResource system: systems) {
			beans.add(system.toBean());
		}
		
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	public ComputeBean getComputeResource(String systemName) {
		
		String hql = "select pr from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.name = :name " +
				"and pr.resource.type = :type " +
				"and pr.enabled = :enabled";
		
		List<ProjectResource> prs = (List<ProjectResource>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("name",systemName)
			.setString("type",ResourceType.COMPUTE.name())
			.setBoolean("enabled",true)
			.list();
		
		if (prs.isEmpty()) return null;
		
		ComputeBean bean = (ComputeBean)prs.get(0).getResource().toBean();
		
		for (ProjectResource pr: prs) {
			bean.getAllocations().add(pr.getAllocationName());
		}
		
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public ComputeResource _getComputeResource(String systemName) {
		
		String hql = "select distinct pr.resource from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.name = :name " +
				"and pr.resource.type = :type " +
				"and pr.enabled = :enabled";
		
		List<CCGResource> resources = (List<CCGResource>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("name",systemName)
			.setString("type",ResourceType.COMPUTE.name())
			.setBoolean("enabled",true)
			.list();
		
		return (ComputeResource)resources.get(0);
	}

	@SuppressWarnings("unchecked")
	public ComputeBean getComputeResourceByHostname(String hostname) {
		
		String hql = "select pr from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.hostname = :hostname " +
				"and pr.resource.type = :type";
		
		List<ProjectResource> prs = (List<ProjectResource>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("hostname",hostname)
			.setString("type",ResourceType.COMPUTE.name())
			.list();
		
		if (prs.isEmpty()) return null;
		
		ComputeBean bean = (ComputeBean)prs.get(0).getResource().toBean();
		
		for (ProjectResource pr: prs) {
			bean.getAllocations().add(pr.getAllocationName());
		}
		
		return bean;
	}

	@SuppressWarnings("unchecked")
	public List<ComputeBean> getComputeResources() {
		
		List<ComputeBean> beans = new ArrayList<ComputeBean>();
		
		String hql = "select pr from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.type = :type";
		
		hSession.enableFilter("softwareFilter");
		List<ProjectResource> prs = (List<ProjectResource>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("type",ResourceType.COMPUTE.name())
			.list();
		
		if (prs.isEmpty()) return null;
		
		for (ProjectResource pr: prs) {
			CCGResource resource = (CCGResource)pr.getResource();
			if (resource.getType().equals(ResourceType.COMPUTE)) {
				boolean found = false;
				for (ComputeBean bean: beans) {
					if (bean.getName().equals(resource.getName())) {
						found = true;
						if (!bean.getAllocations().contains(pr.getAllocationName())) {
							bean.getAllocations().add(pr.getAllocationName());
						}
					}
				}
				if (!found) {
					if (resource.isValid()) {
						ComputeBean bean = (ComputeBean)resource.toBean();
						bean.getAllocations().add(pr.getAllocationName());
						beans.add(bean);
					}
				}
			}
		}
		
//		for (ComputeResource resource: _getComputeResources()) {
//			beans.add((ComputeBean)resource.toBean());
//		}
		 
		return beans;
	}

	@SuppressWarnings("unchecked")
	public List<ComputeBean> getProjectComputeResources(Long pid) {
		
		if (!ServiceUtil.isValid(pid)) {
			return null;
		}
		
		List<ComputeBean> beans = new ArrayList<ComputeBean>();
		
		String hql = "select pr from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.type = :type";
		
		List<ProjectResource> prs = (List<ProjectResource>)hSession.createQuery(hql)
			.setLong("id",pid)
			.setString("type",ResourceType.COMPUTE.name())
			.list();
		
		if (prs.isEmpty()) return null;
		
		for (ProjectResource pr: prs) {
			CCGResource resource = (CCGResource)pr.getResource();
			if (resource.getType().equals(ResourceType.COMPUTE)) {
				boolean found = false;
				for (ComputeBean bean: beans) {
					if (bean.getName().equals(resource.getName())) {
						found = true;
						if (!bean.getAllocations().contains(pr.getAllocationName())) {
							bean.getAllocations().add(pr.getAllocationName());
						}
					}
				}
				if (!found) {
					ComputeBean bean = (ComputeBean)resource.toBean();
					bean.getAllocations().add(pr.getAllocationName());
					beans.add(bean);
				}
			}
		}
		
//		for (ComputeResource resource: _getComputeResources()) {
//			beans.add((ComputeBean)resource.toBean());
//		}
		 
		return beans;
	}

	@SuppressWarnings("unchecked")
	public List<ComputeResource> _getComputeResources() {
		
		String hql = "select distinct pr.resource from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.type = :type " +
				"and pr.enabled = :enabled";
		
		List<ComputeResource> resources = (List<ComputeResource>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("type",ResourceType.COMPUTE.name())
			.setBoolean("enabled",true)
			.list();
		
		
		return resources;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ComputeBean> getComputeResourcesAtSite(String siteName) {
		
		List<ComputeBean> beans = new ArrayList<ComputeBean>();
		
		String hql = "select pr.resource from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.site.name = :name " +
				"and pr.resource.type = :type " +
				"and pr.enabled = :enabled";
		
		List<ComputeResource> resources = (List<ComputeResource>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("name",siteName)
			.setString("type",ResourceType.COMPUTE.name())
			.setBoolean("enabled",true)
			.list();
		
		for (ComputeResource resource: resources) {
			beans.add((ComputeBean)resource.toBean());
		}
		
		return beans;
	}

	@SuppressWarnings("unchecked")
	public QueueBean getDefaultQueueForResource(String systemName) {
		
		String hql = "select pr.resource.queues from ProjectResource pr " +
				"join pr.resource.queues q " + 
				"where pr.project.id = :id " +
				"and pr.resource.name = :name " +
				"and q.theDefault = :default";
		
		List<Queue> list = hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("name",systemName)
			.setBoolean("default",true)
			.list();
			
		if (list.isEmpty()) return null;
		
		Queue defaultQueue = (Queue)list.get(0);
		
		return defaultQueue.toBean();
		
	}

	public StorageBean getDefaultStorageResourceForComputeResource(
			String systemName) {
		
		StorageResource resource = _getDefaultStorageResourceForComputeResource(systemName);
		
		if (resource == null) return null;
		
		return resource.toBean();
		
	}
	
	@SuppressWarnings("unchecked")
	public StorageResource _getDefaultStorageResourceForComputeResource(
			String systemName) {
		
//		String hql = "from StorageResource as s where s.site = some (" +
//				" select pr.resource.site from ProjectResource pr " +
//				"where pr.project.id = :id " +
//				"and pr.resource.name = :name " +
//				"and pr.resource.type = :type) " +
//				"and s.theDefault = :default";
		String hql = "select upr.userProject.mss from UserProjectResource upr " +
				"where upr.userProject.id.projectId = :id " +
				"and upr.resource.name = :name " +
				"and upr.resource.type = :type " +
				"and upr.enabled = :enabled";
		
		List<StorageResource> list = hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("name",systemName)
			.setString("type",ResourceType.COMPUTE.name())
			.setBoolean("enabled",true)
			.list();
		
		if (list.isEmpty()) return null;
		
		StorageResource resource = (StorageResource)list.get(0);
		
		return resource;
		
	}

	@SuppressWarnings("unchecked")
	public List<QueueBean> getQueuesForResource(String systemName) {
		
		List<QueueBean> beans = new ArrayList<QueueBean>();
		
		String hql = "select pr.resource.queues from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.resource.name = :name";
		
		List<Queue> queues = (List<Queue>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.setString("name",systemName)
			.list();
		
		for (Queue q: queues) {
			beans.add(q.toBean());
		}
		
		return beans;
	}

	@SuppressWarnings("unchecked")
	public StorageResource _getStorageResource(String storageName) {
		String hql = "from StorageResource s where s.name = :name";
		
		List<StorageResource> list = hSession.createQuery(hql)
			.setString("name",storageName)
			.list();
		
		if (list.isEmpty()) return null;
		
		StorageResource resource = (StorageResource)list.get(0);
		
		return resource;
	}
	
	public StorageBean getStorageResource(String storageName) {
		
		StorageResource resource = _getStorageResource(storageName);
		
		if (resource == null) return null;
		
		return resource.toBean();
		
	}

	@SuppressWarnings("unchecked")
	public List<StorageBean> getStorageResources() {
		
		List<StorageBean> beans = new ArrayList<StorageBean>();
		
		String hql = "select distinct(pr.resource.site.resources) " +
				"from ProjectResource pr " +
				"where pr.project.id = :id";
		
		List<CCGResource> resources = (List<CCGResource>)hSession.createQuery(hql)
			.setLong("id",userSession.getProjectId())
			.list();
		
		for (CCGResource resource: resources) {
			if (resource instanceof StorageResource)
				beans.add((StorageBean)resource.toBean());
		}
		
		return beans;
	}

	/**
	 * TODO:  this still needs sorted out in the db schema.  Currently not
	 * every site has as storage resource, so we need to map them differently.
	 * 
	 * @param siteName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StorageBean> getStorageResourcesAtSite(String siteName) {
		
		
		List<StorageBean> beans = new ArrayList<StorageBean>();
		
		if (!ServiceUtil.isValid(siteName)) return beans;
		
		String hql = "select s.resources from Site s " +
				"where s.name = :name";
		
		List<CCGResource> resources = (List<CCGResource>)hSession.createQuery(hql)
//			.setLong("id",userSession.getProjectId())
			.setString("name",siteName)
			.list();
		
		for (CCGResource resource: resources) {
			if (resource instanceof StorageResource)
				beans.add((StorageBean)resource.toBean());
		}
		
		return beans;
	}

	@SuppressWarnings("unchecked")
	public boolean userHasAccessToComputeResource(String systemName) {
		
		if (!ServiceUtil.isValid(systemName)) return false;
		
		String hql = "from UserProjectResource " +
				"where userProject.project.id = :pid " +
				"and userProject.user.id = :uid " +
				"and resource.name = :name " +
				"and resource.type = :type " +
				"and enabled = :enabled";
		
		List results = hSession.createQuery(hql)
			.setLong("pid",userSession.getProjectId())
			.setLong("uid", userSession.getUserId())
			.setString("name",systemName)
			.setString("type",ResourceType.COMPUTE.name())
			.setBoolean("enabled",true)
			.list();
		
		return (!results.isEmpty());

		
	}

	@SuppressWarnings("unchecked")
	public boolean userHasAccessToStorageResource(String storageName) {
		
		if (!ServiceUtil.isValid(storageName)) return false;
		
		String hql = "from UserProjectResource upr " +
				"where upr.userProject.project.id = :pid " +
				"and upr.userProject.user.id = :uid " +
				"and upr.resource.name = :name " +
				"and upr.resource.type = :type";
		
		List results = hSession.createQuery(hql)
			.setLong("pid",userSession.getProjectId())
			.setLong("uid", userSession.getUserId())
			.setString("name",storageName)
			.setString("type",ResourceType.STORAGE.name())
			.list();

		return (!results.isEmpty());

	}
	
	@SuppressWarnings("unchecked")
	public ComputeResource _getCompute(String systemName) throws ResourceException {
		String hql = "from ComputeResource " +
				"where name = :name";
		
		try {
			List<ComputeResource> resources = (List<ComputeResource>)hSession.createQuery(hql)
				.setString("name",systemName)
				.list();
			
			if (resources.isEmpty()) return null;
			
			return resources.get(0);
		} catch (Exception e) {
			throw new ResourceException("Failed to find named system",e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public StorageResource _getStorage(String systemName) throws ResourceException {
		String hql = "from StorageResource " +
				"where name = :name";
		
		try {
			List<StorageResource> resources = (List<StorageResource>)hSession.createQuery(hql)
				.setString("name",systemName)
				.list();
			
			if (resources.isEmpty()) return null;
			
			return resources.get(0);
		} catch (Exception e) {
			throw new ResourceException("Failed to find named system",e);
		}
		
	}

	@SuppressWarnings("unchecked")
	public static boolean isSiteValid(String siteName) {
		
		HibernateUtil.beginTransaction();
		
		String hql = "from Site where name = :name";

		try {
			
			List<Site> resources = (List<Site>)HibernateUtil.getSession().createQuery(hql)
				.setString("name",siteName)
				.list();
			
			return !resources.isEmpty();
			
		} catch (Exception e) {
			throw new ResourceException("Failed to find named site",e);
		}
	}

	@SuppressWarnings("unchecked")
	public static boolean isResourceValid(String sResource) {
		HibernateUtil.beginTransaction();
		
		String hql = "from ComputeResource where name = :name";

		try {
			
			List<ComputeResource> resources = (List<ComputeResource>)HibernateUtil.getSession().createQuery(hql)
				.setString("name",sResource)
				.list();
			
			return !resources.isEmpty();
			
		} catch (Exception e) {
			throw new ResourceException("Failed to find named site",e);
		}
	}
}
