package org.gridchem.service;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ServiceContext;

public interface FileService {

	/* 
	 * Needed to set up the persistant variables in the axis2 session
	 * 
	 * @see org.apache.axis2.service.Lifecycle#init(org.apache.axis2.context.ServiceContext)
	 */
	public abstract void init(ServiceContext serviceContext) throws AxisFault;

	/* 
	 * Needed to terminate persistant variables in the axis2 session
	 * 
	 * @see org.apache.axis2.service.Lifecycle#destroy(org.apache.axis2.context.ServiceContext)
	 */
	public abstract void destroy(ServiceContext serviceContext);

	/**
	 * List the cached input files on the server for a particular user at a particular path.
	 * The path argument is necessary because the cached files are stored in a hierarchy 
	 * relative to the user's username as follows:
	 * 
	 *   $TEMP/<username>/<experiment>/<job_name>/<file_name>
	 *   
	 * @param sessionId
	 * @param path
	 * @return serialized FileBean
	 * @throws Exception
	 */
	public abstract String listCachedInputFiles(String sessionId, String path)
			throws Exception;

	/**
	 * List the output files for a user's job. For community users, 
	 * @param sessionId
	 * @param sJobId
	 * @return serialized FileBean
	 * @throws Exception
	 */
	public abstract String listOutputFilesForJob(String sessionId, String sJobId)
			throws Exception;

	/**
	 * List a user's cached input files for a particular job on the server.
	 * 
	 *   $TEMP/<username>/<experiment>/<job_name>/<file_name>
	 *   
	 * @param sessionId
	 * @param path
	 * @return serialized FileBean
	 * @throws Exception
	 */
	public abstract String listCachedInputFilesForJob(String sessionId,
			String experimentName, String jobName) throws Exception;

	/**
	 * Allows users to download cached input files referenced by their logical id.
	 * 
	 * @param sessionId
	 * @param logicalFileId
	 * @return
	 * @throws Exception
	 */
	public abstract DataHandler getCachedInputFile(String sessionId,
			String sFileId) throws Exception;

	//	public List<FileBean> listNames(String sessionId, String path) throws Exception {
	//		loadSessionFileManager(sessionId);
	//		return fileManager.list(path);
	//	}
	//	
	/**
	 * Get a set of FileBean objects representing the result of a ls -l of the given URI
	 * 
	 * @param sessionId - valid user session
	 * @param path - remote uri
	 * @return serialized
	 * @throws Exception 
	 */
	public abstract String list(String sessionId, String path) throws Exception;

	/**
	 * Creates a new folder at the given url with the given name.
	 * 
	 * @param sessionId
	 * @param path
	 * @param name
	 * @throws Exception
	 */
	public abstract String mkdir(String sessionId, String path) throws Exception;

	/**
	 * Deletes the directory given by path.
	 * 
	 * @param sessionId
	 * @param path
	 * @throws Exception
	 */
	public abstract String deleteDir(String sessionId, String path)
			throws Exception;

	/**
	 * Deletes the directory given by path.
	 * 
	 * @param sessionId
	 * @param path
	 * @throws Exception
	 */
	public abstract String deleteFile(String sessionId, String path)
			throws Exception;

	/**
	 * Renames the file at the source url.
	 * 
	 * @param sessionId
	 * @param path
	 * @param name
	 * @throws Exception
	 */
	public abstract String rename(String sessionId, String path, String name)
			throws Exception;

	/**
	 * Streams a file from a remote host to the requesting client using MTOM.
	 * 
	 * @param sessionId
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public abstract DataHandler getRemoteFile(String sessionId, String host, String path)
			throws Exception;

	/**
	 * Uploads and saves a from from the client to the server and creates a reference in the db for
	 * reference by jobs later on.
	 * 
	 * @param sessionId
	 * @param experimentName
	 * @param jobName
	 * @param fileName
	 * @param data
	 * @returns logicalFile a serialized logical file record.
	 * @throws Exception
	 */
	public abstract String putCachedFile(String sessionId, String experimentName,
			String jobName, String fileName, DataHandler data) throws Exception;

}