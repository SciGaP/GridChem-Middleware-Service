package org.gridchem.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.gridchem.service.beans.CollaboratorBean;
import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.exceptions.ProjectException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Project;
import org.gridchem.service.model.UserProject;
import org.gridchem.service.model.UserProjectResource;
import org.gridchem.service.model.enumeration.ProjectStatusType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.criterion.Restrictions;

public class ProjectDao extends AbstractDao {
	
	public ProjectDao(GMSSession session) {
		super(session);
	}
	
	public ProjectDao() {
		super();
	}
	
	public Long _add(Project project) throws ProjectException {
		
		try {
			
			hSession.save(project);
			
			hSession.getTransaction().commit();
			
			return project.getId();
			
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			throw new ProjectException("Failed to add project",e);
		}
	}
	
	public void _remove(Project project) throws ProjectException {
		
		try {
			
			hSession.delete(project);
			
			hSession.getTransaction().commit();
			
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			throw new ProjectException("Failed to add project",e);
		}
	}
	
	public void _scrub(String projectName) throws ProjectException {
		
		if (!ServiceUtil.isValid(projectName)) throw new ProjectException("Invalid project name");
		
		try {
			Project p = _get(projectName);
			
			if (p == null) throw new ProjectException("Invalid project name");
			
			hSession.createSQLQuery("delete p from ProjectResource p where projectId = :id").setLong("id", p.getId()).executeUpdate();
			
			hSession.createSQLQuery("delete p from UserProjectResource p where projectId = :id").setLong("id", p.getId()).executeUpdate();
			
			hSession.createSQLQuery("delete p from UserProject p where projectId = :id").setLong("id", p.getId()).executeUpdate();
			
			hSession.createSQLQuery("delete p from Projects p where projectID = :id").setLong("id", p.getId()).executeUpdate();
			
			hSession.getTransaction().commit();
			
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			throw new ProjectException("Failed to scrub project",e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectBean> getAll() {
		List<ProjectBean> beans = new ArrayList<ProjectBean>();
		
		String hql = "select up.project from UserProject up where up.user.id = :id";
		
		List<Project> projects = (List<Project>)hSession.createQuery(hql)
				.setLong("id", userSession.getUserId()).list();
		
		for (Project proj: projects) {
			ProjectBean bean = proj.toBean();
			if (userSession.getProjectId() != null) {
				bean.setCurrent(proj.getId().equals(userSession.getProjectId()));
			}
			beans.add(bean);
		}
		
		return beans;
	}
	
	public ProjectBean get() {
		
		ProjectBean bean = get(userSession.getProjectId());
		
		if (bean != null) {
			bean.setSystems(new ResourceDao(userSession).getComputeResources());
		}
		
		return bean;
	}
	
	public Project _get() {
		
		return _get(userSession.getProjectId());
	}

	public List<Project> _getAll() {
		
		String hql = "select up.project from UserProject up where up.user.id= :id";

		List<Project> projects = (List<Project>)hSession.createQuery(hql)
			.setLong("id", userSession.getUserId()).list();

		return projects;
	}
	
	public ProjectBean get(Long projectId) {

		Project project = _get(projectId);
		
		if (project == null) return null;
		
		ProjectBean bean = project.toBean();
		
		/*if (bean != null) {
			bean.setSystems(new ResourceDao(userSession).getProjectComputeResources(projectId));
		}*/
		
		return bean;
	}
	
	public Project _get(Long projectId) {
		
		if (projectId == null) return null;
		
		return (Project)hSession.get(Project.class, projectId);
	}
	
	public ProjectBean get(String projectName) {
		
		Project project = _get(projectName);
		
		if (project == null) return null;
		
		ProjectBean bean = project.toBean();
		
		if (bean != null) {
			bean.setSystems(new ResourceDao(userSession).getProjectComputeResources(project.getId()));
		}
		
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public Project _get(String projectName) {
		
		List<Project> projects = hSession.createCriteria(Project.class)
			.add(Restrictions.eq("name",projectName))
			.list();
		
		if (projects == null || projects.isEmpty()) return null;
		
		return projects.get(0);
	}
	
	public List<CollaboratorBean> getCollaborators() {
		
		return getCollaborators(userSession.getProjectId());
	}
	
	@SuppressWarnings("unchecked")
	public List<CollaboratorBean> getCollaborators(Long projectId) {
		
		List<CollaboratorBean> beans = new ArrayList<CollaboratorBean>();
		
		if (projectId == null) return beans;
		
		String hql = "select up from UserProject up where up.project.id = :id";
		
		List<UserProject> ups = (List<UserProject>)hSession.createQuery(hql)
				.setLong("id", projectId).list();
		
		for (UserProject up: ups) {
			CollaboratorBean collab = new CollaboratorBean(up.getUser().toBean());
			collab.setTotalUsage(up.getUsage().toBean());
			
			hql = "from UserProjectResource upr where upr.userProject.id.userId = :uid and upr.userProject.id.projectId = :pid";
			List<UserProjectResource> uprs = (List<UserProjectResource>)hSession.createQuery(hql)
				.setLong("uid", up.getUser().getId())
				.setLong("pid", up.getProject().getId()).list();
			
			for (UserProjectResource upr: uprs) {
				// populate with resource usage info
				collab.addUsageRecord(upr.getResource().getHostname(), upr.getUsage().toBean());
			}
			
			beans.add(collab);
		}
		
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isUserProject(Long projectId) {
		
		if (projectId == null) return false;
		
		String hql = "select up.project from UserProject up " +
			"where up.user.id = :uid " +
			"and up.project.id = :pid";
		
		List<Project> projects = (List<Project>)hSession.createQuery(hql)
				.setLong("uid", userSession.getUserId())
				.setLong("pid", projectId)
				.list();
		
		return !projects.isEmpty();
	}

	public String getDefaultAllocation(String systemName) {
		
		return getDefaultAllocation(userSession.getProjectId(), systemName);
	}
	
	@SuppressWarnings("unchecked")
	public String getDefaultAllocation(Long projectId, String systemName) {
		
		if (projectId == null || systemName == null) return null;
		
		String hql = "select pr.allocationName " +
			"from ProjectResource pr " +
			"where pr.project.id = :id " +
			"and pr.resource.name = :name " +
			"and pr.enabled = :enabled";

		List<String> list = hSession.createQuery(hql)
			.setLong("id", projectId)
			.setString("name",systemName)
			.setBoolean("enabled",true)
			.list();
		
		if (list.isEmpty()) return null;
		
		String name = (String)list.get(0);
	
		if (name == null) {
			throw new ProjectException("No valid allocations for project " + projectId 
					+ " on resource " + systemName);
		}
		
		return name;
	}

	public boolean isAllocationValid(String allocationName) {
		
		return isAllocationValid(userSession.getProjectId(), allocationName);
	}
	
	@SuppressWarnings("unchecked")
	public boolean isAllocationValid(Long projectId,
			String allocationName) {
		
		String hql = "select COUNT(distinct pr.project) " +
				"from ProjectResource pr " +
				"where pr.project.id = :id " +
				"and pr.allocationName = :name " +
				"and pr.enabled = :enabled";
		
		List<Long> list = hSession.createQuery(hql)
				.setLong("id", projectId)
				.setString("name",allocationName)
				.setBoolean("enabled",true)
				.list();
		
		if (list.isEmpty()) return false;
		
		Long count = (Long)list.get(0);
		
		return count.longValue() > 0;
	}

	public boolean isProjectValid() {
		
		return isProjectValid(userSession.getProjectId());

	}
	
	@SuppressWarnings("unchecked")
	public boolean isProjectValid(Long projectId) {

		if (projectId == null) return false;
		
		String hql = "select up.project from UserProject up " +
			"where up.user.id = :uid " +
			"and up.project.id = :pid " +
			"and up.project.status = :status " +
			"and up.enabled = :enabled";
	
		List<Project> projects = (List<Project>)hSession.createQuery(hql)
			.setLong("uid", userSession.getUserId())
			.setLong("pid", projectId)
			.setString("status", ProjectStatusType.ACTIVE.name())
			.setBoolean("enabled", true)
			.list();
		
		return !projects.isEmpty();
//		
//		Project project = (Project)hSession.get(Project.class, userSession.getProjectId());
//		
//		return (project.getStatus().equals(ProjectStatusType.ACTIVE));
	}

}
