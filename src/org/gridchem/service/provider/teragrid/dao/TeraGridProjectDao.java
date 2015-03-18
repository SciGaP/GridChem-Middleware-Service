/**
 * 
 */
package org.gridchem.service.provider.teragrid.dao;

import java.util.ArrayList;
import java.util.List;

import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.InfrastructureException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.User;
import org.gridchem.service.sync.iis.beans.Allocation;
import org.gridchem.service.sync.iis.beans.Collaborator;
import org.gridchem.service.wsclients.iis.TeraGridProfileServiceClient;

/**
 * @author dooley
 *
 */
public class TeraGridProjectDao {

	private GMSSession session = null;
	private TeraGridProfileServiceClient client = null;
	
	public TeraGridProjectDao(GMSSession session) {
		this.session = session;
		User user = new UserDao(session)._get();
		this.client = new TeraGridProfileServiceClient(user.getUsername(), user.getPassword());
	}
	
	public ProjectBean findById(Long projectId) {
		// TODO query for all projects, find the one with allocationid = projectid
		throw new InfrastructureException("Not implemented.");
	}

	public ProjectBean findByProjectName(String projectName) {
		// TODO query for allocation with name
		Allocation allocation = client.getProject(projectName);
		ProjectBean bean = allocation.toBean();
		
//		bean.setId(allocation.getAllocationId().longValue());
//		bean.setName(allocation.getProjectId());
//		bean.setDescription(allocation.getProjectTitle());
//		bean.setStartDate(allocation.getStartDate());
//		bean.setEndDate(allocation.getEndDate());
//		bean.setStatus(ProjectStatusType.valueOf(allocation.getAcctState()));
//		bean.setPi(allocation.getPiFirstName() + " " + allocation.getPiLastName());
//		bean.setStartDate(allocation.getStartDate());
//		bean.setEndDate(allocation.getEndDate());
//		bean.setLastUpdated(new Date());
//		
//		UsageBean usage = new UsageBean();
//		usage.setAllocated(allocation.getBaseAllocation().doubleValue());
//		usage.setUsed(allocation.getUsedAllocation().doubleValue());
//		usage.setBalance(allocation.getRemainingAllocation().doubleValue());
//		bean.setUsage(usage);
		
		return bean;
	}

	public List<UserBean> findCollaboratorsForProjectName(String projectName) {
		// TODO query for users sharing allocationid = projectid
		List<Collaborator> collabs = client.getCollaboratorsForProject(projectName);
		List<UserBean> beans = new ArrayList<UserBean>();
		
		for (Collaborator collab: collabs) {
			beans.add(collab.toBean());	
		}
		return beans;
	}

	public String findDefaultAllocationForProject(Long projectId,
			String systemName) {
		// TODO query for allocation with largest balance 
		throw new InfrastructureException("Not implemented.");
	}

	public List<ProjectBean> findForCurrentUser() {
		// TODO query for all user allocations
		List<Allocation> allocations = client.getProjects();
		List<ProjectBean> beans = new ArrayList<ProjectBean>();
		
		for (Allocation alloc: allocations) {
			ProjectBean bean = alloc.toBean();
			bean.setComment("Imported from the TeraGrid User Profile Service.");
			beans.add(bean);	
		}
		return beans;
	}

	public ProjectBean findSessionProject() {
		// TODO query to get the allocation associated with session.projectid = allocationid
		return client.getProject(session.getProject().getName()).toBean();
	}

	public boolean isProjectAllocationValid(Long projectId,
			String allocationName) {
		// TODO query to see if any user has project with name = allocationname
		Allocation alloc = client.getProject(allocationName);
		return (alloc.getEndDate().getTime() < alloc.getStartDate().getTime());
	}

	public boolean isProjectValid(Long projectId) {
		// TODO query to see if any user allocation has allocationid = projectid since
		// only the active ones are returned.
		List<Allocation> allocations = client.getProjects();
		for (Allocation alloc: allocations) {
			if  (alloc.getEndDate().getTime() > alloc.getStartDate().getTime()) return true;
		}
		return false;
	}

	public boolean isUserProject(Long projectId) {
		// query to see if any user allocation has allocationid = projectid 
		List<Allocation> allocations = client.getProjects();
		for (Allocation alloc: allocations) {
			if (alloc.getAllocationId().intValue() == projectId.intValue()) return true;
		}
		return false;
	}

	public List<UserBean> findCollaboratorsForProject(Long projectId) {
		List<Allocation> allocations = client.getProjects();
		for (Allocation alloc: allocations) {
			if (alloc.getAllocationId().intValue() == projectId.intValue()) {
				List<Collaborator> collabs = client.getCollaboratorsForProject(alloc.getProjectId());
				List<UserBean> beans = new ArrayList<UserBean>();
				
				for (Collaborator collab: collabs) {
					beans.add(collab.toBean());	
				}
				return beans;
			}
		}
		return null;
	}

}
