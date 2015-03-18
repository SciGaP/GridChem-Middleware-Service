package org.gridchem.service.authentication;

import static org.gridchem.service.model.enumeration.AccessType.COMMUNITY;
import static org.gridchem.service.model.enumeration.AccessType.TERAGRID;

import org.gridchem.service.authentication.ccg.CCGLoginProvider;
import org.gridchem.service.authentication.myproxy.MyProxyLoginProvider;
import org.gridchem.service.authentication.teragrid.TeraGridLoginProvider;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.model.enumeration.AccessType;

public class LoginFactory {

	public static LoginProvider getLoginProvider(AccessType type) throws PermissionException, ProviderException{
		if (type.equals(TERAGRID)) {
			return new TeraGridLoginProvider();
		} else if (type.equals(COMMUNITY)) {
			return new CCGLoginProvider();
		} else if (type.equals(COMMUNITY)) {
			return new MyProxyLoginProvider();
		} else {
			throw new ProviderException("Unsupported provider type " + type);
		}	
	}
}
