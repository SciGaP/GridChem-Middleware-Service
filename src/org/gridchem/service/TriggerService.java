package org.gridchem.service;

import org.gridchem.service.exceptions.JobException;

public interface TriggerService {

	/**
	 * Simple update method that can be called via batch submit scripts.  It takes 
	 * a static api key.
	 * 
	 * @param sJobId
	 * @param sStatus
	 * @throws JobException
	 */
	public abstract String update(String apiKey, String sJobId, String sStatus)
			throws JobException;

}