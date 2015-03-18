/**
 * 
 */
package org.gridchem.service.user;

import java.util.Map;

import org.gridchem.service.dao.PreferencesDao;
import org.gridchem.service.exceptions.PreferencesException;

/**
 * @author dooley
 *
 */
public class PreferenceManager {
	
	@SuppressWarnings("unchecked")
	public static Map getPreferencesMapForUser(Long userId) throws PreferencesException {
		return PreferencesDao.getForUser(userId).getPreferencesMap();
	}
	
	public static String getPreferencesForUser(Long userId) throws PreferencesException {
		return PreferencesDao.getForUser(userId).getPreferences();
	}
}
