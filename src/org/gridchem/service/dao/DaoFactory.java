package org.gridchem.service.dao;

import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.model.GMSSession;

public class DaoFactory {
	
	public static UserDao getUserDao(GMSSession session) throws ProviderException{
//		if (session.getType().equals(TERAGRID)) {
//			return new TeraGridUserDao(session);
//		} else if (session.getType().equals(COMMUNITY)) {
//			return new UserDao(session);
//		} else {
//			throw new ProviderException("Unsupported provider type " + session.getType());
//		}	
		return new UserDao(session);
	}

	public static ResourceDao getResourceDao(GMSSession session) throws ProviderException {
//		if (session.getType().equals(TERAGRID)) {
//			return new TeraGridResourceDao(session);
//		} else if (session.getType().equals(COMMUNITY)) {
//			return new CCGResourceDao(session);
//		} else {
//			throw new ProviderException("Unsupported provider type " + session.getType());
//		}	
		return new ResourceDao(session);
	}
	
	public static SoftwareDao getSoftwareDao(GMSSession session) throws ProviderException {
//		if (session.getType().equals(TERAGRID)) {
//			return new TeraGridSoftwareDao(session);
//		} else if (session.getType().equals(COMMUNITY)) {
//			return new CCGSoftwareDao(session);
//		} else {
//			throw new ProviderException("Unsupported provider type " + session.getType());
//		}	
		return new SoftwareDao(session);
	}

	public static ProjectDao getProjectDao(GMSSession session) throws ProviderException {
//		if (session.getType().equals(TERAGRID)) {
//			return new TeraGridProjectDao(session);
//		} else if (session.getType().equals(COMMUNITY)) {
//			return new CCGProjectDao(session);
//		} else {
//			throw new ProviderException("Unsupported provider type " + session.getType());
//		}
		return new ProjectDao(session);
	}
}
