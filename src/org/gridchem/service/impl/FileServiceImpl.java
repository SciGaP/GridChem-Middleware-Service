package org.gridchem.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.FileTypeMap;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.service.Lifecycle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.globus.ftp.exception.FTPException;
import org.gridchem.service.FileService;
import org.gridchem.service.beans.FileBean;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.LogicalFileDao;
import org.gridchem.service.dao.UserProjectResourceDao;
import org.gridchem.service.exceptions.FileException;
import org.gridchem.service.exceptions.FileManagementException;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.file.FileManager;
import org.gridchem.service.job.JobManager;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.LogicalFile;
import org.gridchem.service.model.UserProjectResource;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.session.SessionManager;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;
import org.teragrid.service.util.ResourceCache;


/**
 * POJO to handle operations for users.
 * 
 * @author dooley
 * 
 */
public class FileServiceImpl implements Lifecycle, FileService {
	
	private static Logger logger = LogManager.getLogger(FileServiceImpl.class);
	
	public static String MANAGERS_KEY = "file.managers";
	
	public static final String fileName = "path to the file /outputfile.jpg";  
	
	private FileManager fileManager = null;
	
	/* 
	 * Needed to set up the persistant variables in the axis2 session
	 * 
	 * @see org.apache.axis2.service.Lifecycle#init(org.apache.axis2.context.ServiceContext)
	 */
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#init(org.apache.axis2.context.ServiceContext)
	 */
	public void init(ServiceContext serviceContext) throws AxisFault {
		System.out.println("Initializing the service context");
        // initialize the count to zero
		serviceContext.setProperty(MANAGERS_KEY, new Hashtable<String,FileManager>());
	}
	
	/* 
	 * Needed to terminate persistant variables in the axis2 session
	 * 
	 * @see org.apache.axis2.service.Lifecycle#destroy(org.apache.axis2.context.ServiceContext)
	 */
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#destroy(org.apache.axis2.context.ServiceContext)
	 */
	public void destroy(ServiceContext serviceContext) {
		 System.out.println("Destroying the service context");
		
	}
	
	/**
	 * Retrieves the saved FileManager from the session memory.  If one is not present, it
	 * initializes it.
	 * 
	 * @param sessionId
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void loadSessionFileManager(String sessionId) throws Exception {
		
		SessionManager sessionManager = new SessionManager(sessionId);
		
//		String uri;
//		try {
//			uri = resolvePath(sessionManager.getSession(), new URI(
//					((path == null)?"":path))).toString();
//		} catch (URISyntaxException e) {
//			throw new FileException("Invalid uri",e);
//		}
		
		Hashtable<String,FileManager> managersTable = new Hashtable<String, FileManager>();
		
		MessageContext messageContext = MessageContext.getCurrentMessageContext();
		if (messageContext != null) {
			ServiceContext serviceContext = messageContext.getServiceContext();
			managersTable = (Hashtable<String,FileManager>) serviceContext.getProperty(MANAGERS_KEY);
		}
		
		fileManager = managersTable.get(sessionId);
		
		if (fileManager == null) {
			//fileManager = new FileManager(sessionManager.getSession(), "gridchem-mw.ncsa.illinois.edu", 2811);
			fileManager = new FileManager(sessionManager.getSession(), "gridchem.uits.iu.edu", 2811);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#listCachedInputFiles(java.lang.String, java.lang.String)
	 */
	public String listCachedInputFiles(String sessionId, String path) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (path == null) {
			throw new FileException("Path cannot be null");
		}
		
		SessionManager sessionManager = new SessionManager(sessionId);
		
		// we use relative paths so user's can't view other people's data
		String userCacheRootPath = Settings.TEMP_DATA_DIR + File.separator + sessionManager.getSessionUser().getUserName();
		
		// disallow the sneaky, sneaky trick of using updirs.
		path = path.replaceAll("\\.\\.", "");
		
		java.io.File userCacheRoot = new java.io.File(userCacheRootPath + File.separator + path);
		
		// make sure the path is valid
		if (!userCacheRoot.exists()) {
			throw new FileException("Invalid path");
		}
		
		List<FileBean> beans = new ArrayList<FileBean>();
		for (File file: userCacheRoot.listFiles()) {
			beans.add(FileManager.getFileBean(file));
		}
		
		return ServiceUtil.xstream.toXML(FileManager.getFileBean(userCacheRoot));
		
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#listOutputFilesForJob(java.lang.String, java.lang.String)
	 */
	public String listOutputFilesForJob(String sessionId, String sJobId) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		// load the user session
		SessionManager sessionManager = new SessionManager(sessionId);
		
		Long jobId = null;
		
		try {
			jobId = new Long(sJobId);
		} catch (Exception e) {
			throw new JobException("Invalid job id");
		}
		
		// validate the user owns the job they're requesting data for
		Job job = JobDao.getById(new Long(jobId));
		
		if (job == null) {
			throw new JobException("Invalid job id");
		}
		
		if (!job.getUser().getUsername().equals(sessionManager.getSessionUser().getUserName())) {
			throw new PermissionException("Permission denied");
		}
        
		// determine the path components for the remote file
        String path = null;
        String separator = File.separator;
        String dot = ".";
        String parsedDate = new SimpleDateFormat("yyMMdd").format(job.getCreated());
        
        String relativePath = job.getExperimentName() + separator + 
	        job.getName() + dot + job.getSystem().getHostname() + dot + 
	        job.getLocalId() + dot + parsedDate + separator + 
	        job.getName() + ".out";
        
        AccessType jobType = job.getProject().getType();
        
        if (jobType.equals(AccessType.COMMUNITY)) {
          
        	if (JobManager.isRunning(jobId)) {
        		String gridFtpEndpoint = null;
        		try {
        			// batch scratch on the submit resource
        			gridFtpEndpoint = ResourceCache.getResourceByName(job.getSystem().getName()).getGridftpHostname();
        			System.out.println(gridFtpEndpoint);
        			fileManager = new FileManager(sessionManager.getSession(), gridFtpEndpoint, 2811);
        			
        			path = job.getSystem().getScratchDirectory().replaceFirst("_USER_","ccguser") + 
        				separator + relativePath;
	                  
        			logger.debug("Checking submit resource for community user " + 
        					"output file: " + path);
	                  
        			if (fileManager.exists(path)) {
                    	return ServiceUtil.xstream.toXML(fileManager.list(path));
                    } else {
                    	throw new FileNotFoundException("No listing found for " + 
    	                  		fileManager.getHost() + separator + path);
                    }
        			
	              } catch (Exception e) {
	            	  throw new FileManagementException("Failed to retrieve listing for " + 
	            			  gridFtpEndpoint + separator + path, e);
	              }
        	} else {
        	
        		try {
	                
        			fileManager = new FileManager(sessionManager.getSession(), Settings.MASS_STORAGE_SERVER, 2811);
        			
        			logger.debug("Checking mass storage for community user " + 
	                        "output file: " + relativePath);
	                
        			if (fileManager.exists(path)) {
        				
        				return ServiceUtil.xstream.toXML(fileManager.list(relativePath));
        				
        			} else {
                    	throw new FileNotFoundException("No listing found for " + 
                    			fileManager.getHost() + "/internal/" + job.getUser().getUsername() + 
    	                		separator + relativePath);
                    }
        			
	            } catch (Exception e) {
	            	throw new FileManagementException("Failed to retrieve listing for " + 
	                		fileManager.getHost() + "/internal/" + job.getUser().getUsername() + 
	                		separator + relativePath, e);
	            }
        	}
            
        } else { // external user
        	String gridFtpEndpoint = null;
            try {// batch scratch on the submit resource
                
                // for external users we cannot use the gridchem username, we must use their login name.
                // here we look up all the login names they may have for a given UPR combination and try
                // them each in turn.  if any of them work, we break the loop and return.
                for (UserProjectResource upr: UserProjectResourceDao.getAllForUserProjectResource(
                        job.getUser().getUsername(), job.getProject().getId(), job.getSystem().getName())) {
                    
                    String loginName = upr.getLoginName();
                    
                    gridFtpEndpoint = ResourceCache.getResourceByName(job.getSystem().getName()).getGridftpHostname();
        			System.out.println(gridFtpEndpoint);
        			
                    fileManager = new FileManager(sessionManager.getSession(), gridFtpEndpoint, 2811);
                    
                    path = job.getSystem().getScratchDirectory() .replaceFirst("_USER_",loginName) + separator + relativePath;
                
                    logger.info("Checking submit resource batch scratch for external user " + 
                            "output file: " + path);
                    
                    if (fileManager.exists(path)) {
                    	return ServiceUtil.xstream.toXML(fileManager.list(path));
                    }
                }
                
            } catch (Exception e) {
                throw new FileManagementException("Failed to retrieve listing for " + 
                		gridFtpEndpoint + separator + path, e);
            }
           
            
            try {// mass storage
                
            	fileManager = new FileManager(sessionManager.getSession(), Settings.MASS_STORAGE_SERVER, 2811);
                
                // here we dont' need to use their local login name since their 
                // home directory is a relative path in the derived URL below.
                path = "external" + separator + job.getUser().getUsername() + separator + relativePath;
                
                logger.info("Checking mass storage for external user " + 
                        "output file: " + path);
                
                return ServiceUtil.xstream.toXML(fileManager.list(path));
                
            } catch (Exception e) {
            	throw new FileManagementException("Failed to retrieve listing for " + 
                		fileManager.getHost() + "/internal/" + job.getUser().getUsername() + 
                		separator + path, e);
            }
        } 
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#listCachedInputFilesForJob(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String listCachedInputFilesForJob(String sessionId, String experimentName, String jobName) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (!ServiceUtil.isValid(experimentName)) {
			throw new FileException("Experiment name cannot be null");
		} 
		
		if (!ServiceUtil.isValid(jobName)) {
			throw new FileException("Job name cannot be null");
		} 
		
		SessionManager sessionManager = new SessionManager(sessionId);
		
		// we use relative paths so user's can't view other people's data
		String path = Settings.TEMP_DATA_DIR + File.separator + 
			sessionManager.getSessionUser().getUserName() + 
			File.separator + experimentName + 
			File.separator + jobName;
		
		File jobCacheDir = new File(path);
		
		// make sure the path is valid
		if (!jobCacheDir.exists()) {
			throw new FileException("Invalid path");
		} 
		
		List<FileBean> beans = new ArrayList<FileBean>();
		for (File file: jobCacheDir.listFiles()) {
			beans.add(FileManager.getFileBean(file));
		}
		
		return ServiceUtil.xstream.toXML(beans);
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#getCachedInputFile(java.lang.String, java.lang.String)
	 */
	public DataHandler getCachedInputFile(String sessionId, String sFileId) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		Long fileId = null;
		
		if (!ServiceUtil.isValid(sFileId)) {
			throw new FileException("Logical file id cannot be null");
		} else {
			try {
				fileId = Long.valueOf(sFileId);
			} catch (Exception e) {
				throw new FileException("Invalid project id");
			}
		}
		
		SessionManager sessionManager = new SessionManager(sessionId);
		
		LogicalFile logicalFile = LogicalFileDao.get(fileId);
		
		if (!logicalFile.getUserId().equals(sessionManager.getSession().getUserId())) {
			throw new PermissionException("Permission denied!");
		}
		
		File cachedFile = new File(logicalFile.getLocalPath());
		
		FileDataSource dataSource = new FileDataSource(cachedFile);
		DataHandler fileHandler = new DataHandler(dataSource);
		
		return fileHandler;
	}
	
//	public List<FileBean> listNames(String sessionId, String path) throws Exception {
//		loadSessionFileManager(sessionId);
//		return fileManager.list(path);
//	}
//	
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#list(java.lang.String, java.lang.String)
	 */
	public String list(String sessionId, String path) throws Exception {

		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
//		if (!ServiceUtil.isValid(path)) {
//			throw new FileException("Path cannot be null");
//		}
		
		loadSessionFileManager(sessionId);
        
		return ServiceUtil.xstream.toXML(fileManager.list(path));
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#mkdir(java.lang.String, java.lang.String)
	 */
	public String mkdir(String sessionId, String path)
			throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (path == null) {
			throw new FileException("Path cannot be null");
		}
		
		loadSessionFileManager(sessionId);
		
		fileManager.mkdir(path);
		
		return "success";
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#deleteDir(java.lang.String, java.lang.String)
	 */
	public String deleteDir(String sessionId, String path) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (path == null) {
			throw new FileException("Path cannot be null");
		}
		
		loadSessionFileManager(sessionId);
		
		fileManager.deleteDir(path);
		
		return "success";
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#deleteFile(java.lang.String, java.lang.String)
	 */
	public String deleteFile(String sessionId, String path) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (path == null) {
			throw new FileException("Path cannot be null");
		}
		
		loadSessionFileManager(sessionId);
		
		fileManager.deleteFile(path);
		
		return "success";
	}
	
	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#rename(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String rename(String sessionId, String path, String name) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (!ServiceUtil.isValid(path)) {
			throw new FileException("URI cannot be null");
		}
		
		if (!ServiceUtil.isValid(name)) {
			throw new FileException("Name cannot be null");
		}

		loadSessionFileManager(sessionId);
		
		fileManager.rename(path, name);
		
		return "success";
	}

//	/**
//	 * Copies a file from one url to another.
//	 * 
//	 * @param sessionId
//	 * @param sFromUrl
//	 * @param sToUrl
//	 * @throws Exception
//	 */
//	public void copy(String sessionId, String sFromUrl, String sToUrl)
//			throws Exception {
//		
//		if (!ServiceUtil.isValid(sessionId)) {
//			throw new SessionException("Session id cannot be null");
//		}
//		
//		if (!ServiceUtil.isValid(sFromUrl)) {
//			throw new FileException("Source location cannot be null");
//		}
//		
//		if (!ServiceUtil.isValid(sToUrl)) {
//			throw new FileException("Source location cannot be null");
//		}
//		
//		FileManager manager = loadSessionFileManager(sessionId, sFromUrl);
//		
//		SessionManager sessionManager = new SessionManager(sessionId);
//		
//		URI source = resolvePath(sessionManager.getSession(), new URI(((sFromUrl == null)?"":sFromUrl)));
//		
//		URI dest = resolvePath(sessionManager.getSession(), new URI(((sToUrl == null)?"":sToUrl)));
//		
//		manager.copy(source.toString(), dest.toString());
//		
//	}

//	/**
//	 * Copies a file and deletes the source.
//	 * 
//	 * @param sessionId
//	 * @param sFromUrl
//	 * @param sToUrl
//	 * @throws Exception
//	 */
//	public void move(String sessionId, String sFromPath, String sToPath)
//			throws Exception {
//		
//		if (!ServiceUtil.isValid(sessionId)) {
//			throw new SessionException("Session id cannot be null");
//		}
//		
//		if (!ServiceUtil.isValid(sFromPath)) {
//			throw new FileException("Source location cannot be null");
//		}
//		
//		if (!ServiceUtil.isValid(sToUrl)) {
//			throw new FileException("Source location cannot be null");
//		}
//		
//		loadSessionFileManager(sessionId);
//		
//		fileManager.copy(sFromPath, sToPath);
//		
//		fileManager.deleteDir(sFromPath);
//		
//	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#get(java.lang.String, java.lang.String, java.lang.String)
	 */
	public DataHandler getRemoteFile(String sessionId, String host, String path) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (host == null) {
			throw new FileException("Host cannot be null");
		}
		
		if (path == null) {
			throw new FileException("Path cannot be null");
		}
		
		SessionManager sessionManager = new SessionManager(sessionId);
		
		fileManager = new FileManager(sessionManager.getSession(), host, 2811);
				
		GridFTPFileDataSource dataSource = new GridFTPFileDataSource(fileManager, path);
		
		DataHandler fileHandler = new DataHandler(dataSource);
		
		if (fileHandler != null) {
			logger.info("FileHandler to return is NOT null");
		} else {
			logger.error("FileHander is NULL");
		}
		
		return fileHandler;
	}

	/* (non-Javadoc)
	 * @see org.gridchem.service.FileServiceInterface#put(java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.activation.DataHandler)
	 */
	public String putCachedFile(String sessionId, String experimentName, String jobName, String fileName, DataHandler data) throws Exception {
		
		if (!ServiceUtil.isValid(sessionId)) {
			throw new SessionException("Session id cannot be null");
		}
		
		if (!ServiceUtil.isValid(experimentName)) {
			throw new JobException("Experiment name cannot be null");
		}
		
		if (!ServiceUtil.isValid(jobName)) {
			throw new JobException("Job name cannot be null");
		}
		
		if (!ServiceUtil.isValid(fileName)) {
			throw new FileException("File name cannot be null");
		}
		SessionManager sessionManager = new SessionManager(sessionId);
		
		try {              
			
			// Process the input stream
	        File uploadedFile = new java.io.File(Settings.TEMP_DATA_DIR + 
	              File.separator + sessionManager.getSessionUser().getUserName() +
	              File.separator  + experimentName + 
	              File.separator + jobName + 
	              File.separator + fileName);
	        
	        uploadedFile.getParentFile().mkdirs();
	    
	        System.out.println("putCachedFile: Trying to save " + fileName + " to " + uploadedFile.getAbsolutePath());
	        if (!uploadedFile.exists()) {
	        	uploadedFile.createNewFile();
	        }
	        
	        // create a reference to the uploaded file in the db.
	        LogicalFile temp = new LogicalFile(sessionManager.getSession().getUserId(),fileName,uploadedFile,"",-1);
	        temp.save();
	    
	        System.out.println("Saving " + fileName + " to " + uploadedFile.getAbsolutePath());
	        
			FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);                 
			data.writeTo(fileOutputStream);              
			fileOutputStream.flush();              
			fileOutputStream.close();  
			
			return ServiceUtil.xstream.toXML(temp.toBean());
			
		} catch (IOException e) {              
			throw new FileException("Failed to save uploaded file.",e);        
		}
	}

	@SuppressWarnings("unused")
	private static URI resolvePath(GMSSession session, URI uri) throws URISyntaxException {
        
//		String home = Settings.MASS_STORAGE_SERVER + "//UROOT/u/ac/ccguser";
        
		String basePath = "/home/ccguser/mss/" + 
            ((session.getType().equals(AccessType.COMMUNITY))
                    ?"internal/":"external/");
        
		String scheme = uri.getScheme();
        String host = uri.getHost();
        String path = uri.getPath();
        
        // make sure their username is present in every path
        
        // validate the scheme
        if (scheme == null || scheme.equals("null")) {
            scheme = "gsiftp";
        } else if (!scheme.equals("gsiftp")) {
            throw new FileManagementException("Unsupported scheme.  Please specify all URI with 'gsiftp'");
        }
        
        // validate the host (only mss right now)
        if (host == null || host.equals("null") || host.equals("mss.ncsa.uiuc.edu")) {
            //host = "gridchem-mw.ncsa.illinois.edu";
            host = "gridchem.uits.iu.edu";
        } else if (!host.equals("mss.ncsa.uiuc.edu")) {
            throw new FileManagementException("Unsupported host. Browsing is only permitted to NCSA's mass storage server.");
        }
        
        // if community user, specify the path.  else, use the relative path
        // of the user's home directory on the remote resource
        
        if ((session.getType().equals(AccessType.EXTERNAL))) {
            if (path.equals("")) {
//                logger.debug("Defaulting to external user's relative path");
                return new URI(scheme + "://" + host + "/~/");
            } else if (path.startsWith("/")) {
                return new URI(scheme + "://" + host + "/" + path);
            } else {
                return new URI(scheme + "://" + host + "/~/" + path);
            }
        } else {
            UserBean user = session.getUser();
            
            if (path.equals("")) {
                path = "/" + basePath + user.getUserName();
                return new URI(scheme + "://" + host + path);
            } else {
                // return valid absolute paths
                
                if (path.startsWith(basePath + user.getUserName())) {
                    return new URI(scheme + "://" + host + "/" + path);
                } else if (path.startsWith("/" + basePath + user.getUserName())) {
                    return new URI(scheme + "://" + host + path);
                } else if (path.startsWith(basePath)) {
                    // they are trying to access a directory to which they do not have access
                    throw new PermissionException("Permission denied!!");
                } else {
                    // path must be relative to the user's home directory.
                    if (path.startsWith("/")) {
                        path = "/" + basePath + user.getUserName() + path;
                    } else {
                        path = "/" + basePath + user.getUserName() + "/" + path;
                    }
                }
            }
        }
        
        return new URI(scheme + "://" + host + path);
    }
	
	class GridFTPFileDataSource implements DataSource {
		
		
		private FileManager manager;
		private String path;
		
		public GridFTPFileDataSource(FileManager manager, String path) {
			this.manager = manager;
			this.path = path;
		}
		
		public String getContentType() {
			return FileTypeMap.getDefaultFileTypeMap().getContentType(new File(path).getName());
		}

		public InputStream getInputStream() throws IOException {
			try {
				return manager.getFileInputStream(path);
			} catch (FTPException e) {
				throw new IOException("Failed to open input stream to file");
			}
		}

		public String getName() {
			return path;
		}

		public OutputStream getOutputStream() throws IOException {
			try {
				return manager.getFileOutputStream(path);
			} catch (FTPException e) {
				throw new IOException("Failed to open output stream to file");
			}
		}
	}

}
