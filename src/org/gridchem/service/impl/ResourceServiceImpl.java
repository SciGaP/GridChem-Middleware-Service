package org.gridchem.service.impl;

import org.gridchem.service.ResourceService;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SiteException;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;

/**
 * POJO to handle operations for resources.
 * 
 * @author dooley
 * 
 */
public class ResourceServiceImpl implements ResourceService {

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ResourceService#getResources(java.lang.String)
	 */
	public String getResources(String sessionId) throws ResourceException,
			PermissionException, SessionException, ProviderException {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getResourceDao(
				manager.getSession()).getAllSessionResources());
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ResourceService#getComputeResources(java.lang.String)
	 */
	public String getComputeResources(String sessionId)
			throws ResourceException, PermissionException, SessionException,
			ProviderException {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getResourceDao(
				manager.getSession()).getComputeResources());
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ResourceService#getComputeResourcesForSite(java.lang.String, java.lang.String)
	 */
	public String getComputeResourcesForSite(String sessionId, String siteName)
	throws ResourceException, PermissionException, SessionException,
			ProviderException, SiteException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		if (!ServiceUtil.isValid(siteName)) {
			throw new SiteException("No site specified");
		}
		
		if (!ResourceDao.isSiteValid(siteName)) {
			throw new SiteException("Invalid site name");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getResourceDao(
				manager.getSession()).getComputeResourcesAtSite(siteName));
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ResourceService#getStorageResources(java.lang.String)
	 */
	public String getStorageResources(String sessionId)
			throws ResourceException, PermissionException, SessionException,
			ProviderException {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getResourceDao(
				manager.getSession()).getStorageResources());
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.ResourceService#getStorageResourcesForSite(java.lang.String, java.lang.String)
	 */
	public String getStorageResourcesForSite(String sessionId, String siteName)
	throws ResourceException, SessionException,
			ProviderException, SiteException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}
		
		if (!ServiceUtil.isValid(siteName)) {
			throw new SiteException("No site specified");
		}
		
		if (!ResourceDao.isSiteValid(siteName)) {
			throw new SiteException("Invalid site name");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getResourceDao(
				manager.getSession()).getStorageResourcesAtSite(siteName));
	}
}
