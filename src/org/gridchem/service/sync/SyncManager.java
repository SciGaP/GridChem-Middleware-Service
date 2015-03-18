package org.gridchem.service.sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.StorageResource;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.sync.gpir.GpirDiscoveryAdaptor;
import org.hibernate.criterion.Restrictions;

/**
 * Manager class to synchronize a list of ResourceBeans
 * with their matching entries in the db.
 * 
 * @author dooley
 *
 */
public class SyncManager {
	public static Logger log = Logger.getLogger(SyncManager.class.getName());
	
	public static void main(String[] args) {
		List<ResourceDiscoveryAdaptor> discoveryAdaptors = new ArrayList<ResourceDiscoveryAdaptor>();
        discoveryAdaptors.add(new GpirDiscoveryAdaptor());
        discoveryAdaptors.add(new IISDiscoveryAdaptor());
        
        for (ResourceDiscoveryAdaptor adaptor: discoveryAdaptors) {
        	System.out.println(adaptor.getClass().getName());
        	SyncManager.sync(adaptor.retrieveResources());
        }

//        log.debug("Successfully synced resources.");
	}
	/**
	 * Sync the resource beans with the corresponding
	 * entry in the central db. Currently only compute
	 * and storage resources will be upated.
	 * 
	 * @param beans
	 */
	public static void sync(List<ResourceBean> beans) {
		HibernateUtil.beginTransaction();
		
		for (ResourceBean bean: beans) {
			if (bean instanceof ComputeBean) {
				syncComputeResource((ComputeBean)bean);
			} else if (bean instanceof StorageBean) {
				syncStorageResource((StorageBean)bean);
//			} else if (beans instanceof ComputeBean) {
//				syncComputeResource((ComputeBean)bean);
//			} else if (beans instanceof ComputeBean) {
//				syncComputeResource((ComputeBean)bean);
			}
		}
	}

	/**
	 * Sync the storage bean with the matching resource in the db
	 * @param bean
	 * TODO: Sort out namespace issues between tg and gpir
	 * TGPOPS name = gpir name.
	 */
	@SuppressWarnings("unchecked")
	private static void syncStorageResource(StorageBean bean) {
		HibernateUtil.beginTransaction();
		List<StorageResource> stores = (List<StorageResource>)HibernateUtil.getSession()
				.createCriteria(StorageResource.class)
				.add(Restrictions.eq("name",bean.getName()))
				.list();
		if (stores == null || stores.isEmpty()) {
			return;
		} 
		
		StorageResource storage = stores.get(0);
		
		// if we can't resolve the bean, let it be.
		if (storage == null) 
		
		log.debug("Updating storage resource " + bean.getName() + "...");
		
		Date timestamp = new Date();
		if (bean.getStatus() != null) {
			if (bean.getStatus().equals(ResourceStatusType.DOWN)) {
				storage.setLastDownTime(timestamp);
			}
			storage.setStatus(bean.getStatus());
		}
		
		if (bean.getQuota() > -1) {
			storage.setQuota(bean.getQuota());
			storage.setFree(bean.getFree());
			storage.setTotal(bean.getTotal());
		}		
		
		storage.setLastUpdated(timestamp);
		
		HibernateUtil.getSession().saveOrUpdate(storage);
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		
		System.out.println("Successfully updated " + bean.getName() + ", " + bean.getStatus());
	}

	/**
	 * Sync the compute bean with the matching resource in the db
	 * @param bean
	 * TODO: Sort out namespace issues between tg and gpir
	 * TGPOPS name = gpir name.
	 */
	@SuppressWarnings("unchecked")
	private static void syncComputeResource(ComputeBean bean) {
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
		if (resources == null || resources.isEmpty()) {
			return;
		} 
		
		ComputeResource compute = resources.get(0);
		
		System.out.println("Updating compute resource " + bean.getName() + "...");
		
		Date timestamp = new Date();
		if (bean.getStatus() != null) {
			if (bean.getStatus().equals(ResourceStatusType.DOWN)) {
				compute.setLastDownTime(timestamp);
			}
			compute.setStatus(bean.getStatus());
		}
		
		if (bean.getLoad() != null) {
			compute.getLoad().setJobsRunning(bean.getLoad().getJobsRunning());
			compute.getLoad().setJobsQueued(bean.getLoad().getJobsQueued());
			compute.getLoad().setJobsOther(bean.getLoad().getJobsOther());
			compute.getLoad().setCpu(bean.getLoad().getCpu());
			compute.getLoad().setDisk(bean.getLoad().getDisk());
			compute.getLoad().setMemory(bean.getLoad().getMemory());
			compute.getLoad().setQueue(bean.getLoad().getQueue());
		}
		
		compute.setLastUpdated(timestamp);
		
		HibernateUtil.getSession().saveOrUpdate(compute);
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		
		System.out.println("Successfully updated " + bean.getName() + " " + bean.getLoad() + ", " + bean.getStatus());
	}

	/**
	 * Sync the viz bean with the matching resource in the db
	 * @param bean
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private static void syncVizResource(ComputeBean bean) {}
	
	/**
	 * Sync the network bean with the matching resource in the db
	 * @param bean
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private static void syncNetworkResource(ComputeBean bean) {}
	
}
