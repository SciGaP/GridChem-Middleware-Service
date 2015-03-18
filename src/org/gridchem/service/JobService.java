package org.gridchem.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;

public interface JobService {

	/**
	 * Get the last N jobs the user has submitted where N is defined in the
	 * service settings.
	 * 
	 * @param sessionId
	 * @return
	 */
	public abstract String list(String sessionId);

	/**
	 * Get all the user's jobs. Note that this may result in a service timeout
	 * on the client side while all the results are aggregated and returned.
	 * 
	 * @param sessionId
	 * @return
	 */
	public abstract String listAll(String sessionId);

	/**
	 * Search for a job given the search parameters.
	 * 
	 * @param sessionId
	 * @param params
	 * @return
	 * @throws ParameterException
	 */
	public abstract String search(String sessionId, String sJob)
			throws JobException, SessionException;

	/**
	 * 
	 * @param sessionId
	 * @param sJobBean
	 * @return
	 * @throws JobException
	 * @throws PermissionException
	 * @throws SessionException
	 */
	public abstract String createJob(String sessionId, String sJobBean)
			throws JobException, PermissionException, SessionException;

	public abstract String uploadInputFile(String sessionId, String sJobId,
			String sInputStream) throws JobException, PermissionException,
			SessionException;

	public abstract String downloadInputFile(String sessionId, String sJobId)
			throws JobException, PermissionException, SessionException;

	/**
	 * Submit the given job description.
	 * 
	 * @param sessionId
	 * @param sJob
	 * @return
	 * @throws Exception
	 */
	public abstract Long submit(String sessionId, String sJob) throws Exception;

	/**
	 * Kill the jobs corresponding the the semicolon delimited list of ccg job
	 * id's.
	 * 
	 * @param sessionId
	 * @param sJobIds
	 * @throws Exception
	 */
	public abstract String kill(String sessionId, String sJobIds)
			throws Exception;

	/**
	 * Kill the given job if running, delete associated data, and mark as
	 * deleted in the database.
	 * 
	 * @param sessionId
	 * @param sJobIds
	 * @throws Exception
	 */
	public abstract String delete(String sessionId, String sJobIds)
			throws Exception;

	/**
	 * Hide the jobs corresponding to the ccg job ids.
	 * 
	 * @param sessionId
	 * @param sJobIds
	 * @return
	 * @throws Exception
	 */
	public abstract void hide(String sessionId, String sJobIds)
			throws Exception;

	/**
	 * Unhide the jobs corresponding to the ccg job ids.
	 * 
	 * @param sessionId
	 * @param sJobId
	 * @return
	 * @throws Exception
	 */
	public abstract String unhide(String sessionId, String sJobIds)
			throws Exception;

	/**
	 * Unhide all jobs for the session user.
	 * 
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public abstract String unhideAll(String sessionId) throws Exception;

	public abstract String resubmitJob(String sessionId, String sJobId)
			throws JobException, PermissionException, SessionException;

	/**
	 * Find the resource with the shortest predicted wait time to run the given
	 * job description.
	 * 
	 * @param sessionId
	 * @param sJob
	 * @return QbetsPrediction serialized to xml
	 *
	 * @throws SessionException
	 * @throws ServiceException
	 * @throws ProviderException
	 * @throws RemoteException
	 * @throws ParameterException
	 */
	public abstract String predictStartTime(String sessionId, String sJob)
			throws RemoteException, ProviderException, ServiceException,
			ParameterException;

}