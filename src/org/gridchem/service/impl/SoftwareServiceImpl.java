package org.gridchem.service.impl;

import org.gridchem.service.SoftwareService;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.dao.SoftwareDao;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.exceptions.SoftwareException;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;

/**
 * POJO to handle operations for software.
 * 
 * @author dooley
 * 
 */
public class SoftwareServiceImpl implements SoftwareService {

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SoftwareService#getAllSoftware(java.lang.String)
	 */
	public String getAllSoftware(String sessionId) throws PermissionException,
			SessionException, ProviderException {
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(new SoftwareDao(
				manager.getSession()).get());

	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SoftwareService#getSoftwareInstallationsForResource(java.lang.String, java.lang.String)
	 */
	public String getSoftwareInstallationsForResource(String sessionId,
			String sResource) throws ResourceException, PermissionException,
			SessionException, ProviderException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		if (!ServiceUtil.isValid(sResource)) {
			throw new ResourceException("Invalid resource name");
		}
		
		if (!ResourceDao.isResourceValid(sResource)) {
			throw new ResourceException("Invalid resource name");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getSoftwareDao(
				manager.getSession()).getInstallationsForResource(sResource));
		
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SoftwareService#getSoftwareInstallations(java.lang.String)
	 */
	public String getSoftwareInstallations(String sessionId)
			throws ResourceException, PermissionException, SessionException, ProviderException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getSoftwareDao(
				manager.getSession()).getInstallations());
	}

	
	/* (non-Javadoc)
	 * @see org.gridchem.service.impl.SoftwareService#getSoftwareInstallationsForSoftware(java.lang.String, java.lang.String)
	 */
	public String getSoftwareInstallationsForSoftware(String sessionId,
			String sSoftware) throws SoftwareException, PermissionException,
			SessionException, ProviderException, ParameterException {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Invalid session id");
		}

		if (!ServiceUtil.isValid(sSoftware)) {
			throw new SoftwareException("No software specified");
		}
		
		if (!SoftwareDao.isSoftwareValid(sSoftware)) {
			throw new SoftwareException("Invalid software name");
		}
		
		SessionManager manager = new SessionManager(sessionId);
		
		return ServiceUtil.xstream.toXML(DaoFactory.getSoftwareDao(
				manager.getSession()).getInstallationsForSoftware(sSoftware));
	}
}
