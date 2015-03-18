package org.gridchem.service.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.gridchem.service.beans.SoftwareBean;
import org.gridchem.service.beans.SoftwareInstallationBean;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SoftwareException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Software;
import org.gridchem.service.model.SoftwareInstallation;
import org.gridchem.service.model.enumeration.JobSearchFilterType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.ServiceUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class SoftwareDao extends AbstractDao {
	
	public SoftwareDao(GMSSession session) {
		super(session);
	}

	public SoftwareDao() {
	}

	// should check that the user has access to the project first.
	@SuppressWarnings("unchecked")
	public List<SoftwareBean> get() {
		
		ArrayList<SoftwareBean> beans = new ArrayList<SoftwareBean>();
		
		if (userSession.getProjectId() == null) return beans;
		
		String hql = "select distinct si.software from SoftwareInstallation si, " +
				"ProjectResource pr where si.computeResource = pr.resource " +
				"and pr.project.id = :id and si.valid=1"; 
		
		List<Software> apps = (List<Software>)hSession.createQuery(hql)
				.setLong("id",userSession.getProjectId())
				.list();
		
		for (Software sw: apps) {
			beans.add((SoftwareBean)sw.toBean());
		}
		
        return beans;
	}

	@SuppressWarnings("unchecked")
	public Software _get(String softwareName) {
		
		if (softwareName == null) return null;
		
		String hql = "from Software sw " +
				"where sw.name = :name";
		
		List<Software> apps = (List<Software>)hSession.createQuery(hql)
			.setString("name",softwareName)
			.list();
			
		if (apps.isEmpty()) return null;
		
		return apps.get(0);
	}
	
	public SoftwareBean get(String softwareName) {
		
		Software sw = _get(softwareName);
		
		if (sw == null) return null;
		
		return (SoftwareBean)sw.toBean();
	}
	
	@SuppressWarnings("unchecked")
	public SoftwareInstallation _getInstallation(String software, String system) throws SoftwareException {
		
		if (!ServiceUtil.isValid(software)) throw new SoftwareException("Software name cannot be null");
		if (!ServiceUtil.isValid(system)) throw new SoftwareException("Compute resource name cannot be null");
		
		String hql = "select si from SoftwareInstallation si " +
				"where si.computeResource.name = :system " +
				"and si.software.name = :software"; 
		
		List<SoftwareInstallation> installations = (List<SoftwareInstallation>)hSession.createQuery(hql)
				.setString("system",system)
				.setString("software",software)
				.list();
		
		if (installations.isEmpty()) return null;
		
		return installations.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<SoftwareInstallationBean> getInstallations() {
		
		ArrayList<SoftwareInstallationBean> beans = new ArrayList<SoftwareInstallationBean>();
		
		if (userSession.getProjectId() == null) return beans;
		
		String hql = "select si from SoftwareInstallation si, " +
				"ProjectResource pr where si.computeResource = pr.resource " +
				"and pr.project.id = :id"; 
		
		List<SoftwareInstallation> installations = (List<SoftwareInstallation>)hSession.createQuery(hql)
				.setLong("id",userSession.getProjectId())
				.list();
		
		for (SoftwareInstallation installation: installations) {
			beans.add(installation.toBean());
		}
		
        return beans;
	}

	@SuppressWarnings("unchecked")
	public List<SoftwareInstallationBean> getInstallationsForResource(
			String systemName) {
		
		ArrayList<SoftwareInstallationBean> beans = new ArrayList<SoftwareInstallationBean>();
		
		if (userSession.getProjectId() == null || systemName == null) return beans;
		
		String hql = "select si from SoftwareInstallation si, " +
				"ProjectResource pr where si.computeResource = pr.resource " +
				"and pr.project.id = :id " +
				"and pr.resource.name = :name"; 
		
		List<SoftwareInstallation> installations = (List<SoftwareInstallation>)hSession.createQuery(hql)
				.setLong("id",userSession.getProjectId())
				.setString("name",systemName)
				.list();
		
		for (SoftwareInstallation installation: installations) {
			beans.add(installation.toBean());
		}
		
        return beans;
	}

	@SuppressWarnings("unchecked")
	public List<SoftwareInstallationBean> getInstallationsForSoftware(String softwareName) {
		
		ArrayList<SoftwareInstallationBean> beans = new ArrayList<SoftwareInstallationBean>();
		
		if (userSession.getProjectId() == null || softwareName == null) return beans;
		
		String hql = "select si from SoftwareInstallation si, " +
				"ProjectResource pr where si.computeResource = pr.resource " +
				"and pr.project.id = :id " +
				"and si.software.name = :name"; 
		
		List<SoftwareInstallation> installations = (List<SoftwareInstallation>)hSession.createQuery(hql)
				.setLong("id",userSession.getProjectId())
				.setString("name",softwareName)
				.list();
		
		for (SoftwareInstallation installation: installations) {
			beans.add(installation.toBean());
		}
		
        return beans;
        
	}

	public boolean userHasAccess(String softwareName) {
		return ((getInstallationsForSoftware(softwareName).size() > 0) && 
				BlackListDao.userHasAccessToSoftware(userSession.getUser().getUserName(), softwareName));
	}

	@SuppressWarnings("unchecked")
	public static boolean isSoftwareValid(String sSoftware) throws SoftwareException {
		HibernateUtil.beginTransaction();
		
		String hql = "from Software where name = :name";

		try {
			
			List<Software> resources = (List<Software>)HibernateUtil.getSession().createQuery(hql)
				.setString("name",sSoftware)
				.list();
			
			return !resources.isEmpty();
			
		} catch (Exception e) {
			throw new SoftwareException("Failed to find named site",e);
		}
	}	
}
