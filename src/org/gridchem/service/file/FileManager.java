/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jun 12, 2006
 * 
 * Developed by: CCT, Center for Computation and Technology, 
 * 				NCSA, University of Illinois at Urbana-Champaign
 * 				OSC, Ohio Supercomputing Center
 * 				TACC, Texas Advanced Computing Center
 * 				UKy, University of Kentucky
 * 
 * https://www.gridchem.org/
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal with the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom 
 * the Software is furnished to do so, subject to the following conditions:
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimers.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimers in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the names of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the names of its contributors 
 *    may be used to endorse or promote products derived from this Software without 
 *    specific prior written permission.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS WITH THE SOFTWARE.
*/

package org.gridchem.service.file;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.globus.ftp.FileInfo;
import org.globus.ftp.MlsxEntry;
import org.globus.ftp.exception.ClientException;
import org.globus.ftp.exception.FTPException;
import org.globus.ftp.exception.ServerException;
import org.globus.gsi.GlobusCredentialException;
import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.FileBean;
import org.gridchem.service.dao.ResourceDao;
import org.gridchem.service.exceptions.FileException;
import org.gridchem.service.exceptions.FileManagementException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;
import org.ietf.jgss.GSSException;


/**
 * This class brokers interaction with the GridFTP class that extends the JGlobus library. 
 * The main reason for this class is to implement the file access authorization policy 
 * for community users.  
 * 
 * Community users are all managed under a single community account, thus all files are
 * stored in a common user file space. To prevent users from seeing each other's data,
 * we implement a permission layer to isolate data.  This essentially creates a single
 * subfolder for each user and managest their data out of there.  This is true in mass 
 * storage as well as on hpc systems during runtime.
 * 
 * @author dooley
 *
 */
public class FileManager {
    public static Logger log = Logger.getLogger(FileManager.class.getName());
    
    private GMSSession session = null;
    private GridFTP client = null;
//    private String currentUri = null;
    
    /************************ Constructors ************************/
    
    /**
     * Default no-args constructor
     * @throws IOException 
     * @throws GlobusCredentialException 
     * @throws ServerException 
     * @throws GSSException 
     * @throws ClientException 
     */
    public FileManager(GMSSession session, String host, int port) throws ServerException, GlobusCredentialException, IOException, GSSException, ClientException {
    	this.session = session;
    	client = new GridFTP(host, port, session.getProxy());
    	client.authenticate();
    	
    	if (session.getType().equals(AccessType.COMMUNITY) && host.equals(Settings.MASS_STORAGE_SERVER)) {
    		client.changeDir("internal/" + session.getUser().getUserName());
    	}

        //if (session.getType().equals(AccessType.COMMUNITY) && host.equals("gridchem-mw.ncsa.illinois.edu")) {
        if (session.getType().equals(AccessType.COMMUNITY) && host.equals("gridchem.uits.iu.edu")) {
    		client.changeDir("mss/internal/" + session.getUser().getUserName());
    	}
    }
    
    
    /**
     * Given the passed uri, create a gat file object.  This is a referenced
     * object tied to the remote file.
     * 
     * @param path
     * @param context
     * @param prefs
     * @throws IOException 
     * @throws GSSException 
     * @throws ServerException 
     * @throws GlobusCredentialException 
     * @throws ClientException 
     */
    public FileManager(GMSSession session, String host, int port, String path) throws ServerException, GSSException, IOException, GlobusCredentialException, ClientException {
//        this.context = context;
//        this.prefs = prefs;
        this.session = session;
//        this.currentUri = remoteUri;
        if (sessionUserHasPermission(path)) {
        	client = new GridFTP(host, port, session.getProxy());
        	client.authenticate();
        	
        	if (session.getType().equals(AccessType.COMMUNITY) && host.equals(Settings.MASS_STORAGE_SERVER)) {
	    		client.changeDir("internal/" + session.getUser().getUserName());
	    	}

                //if (session.getType().equals(AccessType.COMMUNITY) && host.equals("gridchem-mw.ncsa.illinois.edu")) {
                if (session.getType().equals(AccessType.COMMUNITY) && host.equals("gridchem.uits.iu.edu")) {
                        client.changeDir("mss/internal/" + session.getUser().getUserName());
                }
//            log.debug("User " + prefs.get("user") + " has permission to access " + uri.toString());
//            this.file = GAT.createFile(context, prefs, uri);
//            log.info("Initialized file: " + file.getName() + " " + file.list());
        } else {
            permissionError(path);
        }
    }

    public String getHost() {
    	return client == null ? null : client.getHost();
    }
    
    public String getCurrentDirectory() throws ServerException, IOException {
    	return client == null ? null : client.getCurrentDir();
    }
    
    public void changeHost(String host, int port) throws ServerException, GlobusCredentialException, IOException, GSSException, ClientException {
    	
    	if (!ServiceUtil.isValid(host)) {
    		throw new FileManagementException("Invalid host name");
    	}
    	
    	if (client != null) {
    		client.close();
    	}
    	
    	if (client.getHost().equals(host)) {
	    	client = new GridFTP(host, port, session.getProxy());
	    	client.authenticate();
    	}
    	
    	if (session.getType().equals(AccessType.COMMUNITY) && host.equals(Settings.MASS_STORAGE_SERVER)) {
    		client.changeDir("internal/" + session.getUser().getUserName());
    	}

        //if (session.getType().equals(AccessType.COMMUNITY) && host.equals("gridchem-mw.ncsa.illinois.edu")) {
        if (session.getType().equals(AccessType.COMMUNITY) && host.equals("gridchem.uits.iu.edu")) {
                client.changeDir("mss/internal/" + session.getUser().getUserName());
        }
    	
    }
    
    /************************ Business Methods ************************/
    
//    /**
//     * Refresh the given location.  This is essentially a convenience method
//     * allowing to replace the current referenced file with another one.
//     * 
//     * @param path
//     */
//    public void refresh(String path) {
//    	try {
//            if (isReadable(path)) {
//            	if (client == null) {
//	            	client.changeDir(path);
//            	} else {
//            		client.list();
//            	}
//            } else {
//                permissionError(path);
//            }
//        } catch (PermissionException pe) {
//            throw pe;
//        } catch (Exception e) {}
//    }
    
    /**
     * Load the parent directory in place of the current one.  We need to check
     * permissions before allowing this, however this should be done further up
     * in the call chain.
     * @throws IOException 
     * @throws ServerException 
     * 
     */
    public void upDirectory() throws ServerException, IOException {
    	String parentDir = "../";
    	
//    	java.io.File parent = new java.io.File(client.getCurrentDir());
		if (sessionUserHasPermission(parentDir)) {
        
    		client.changeDir("..");
        	
        } else {
            permissionError(parentDir);
        }
    }
    
    public boolean exists(String path) throws ServerException, IOException {
    	
    	return client.exists(path);
    }
    /**
     * Delete the directory given by path.  This will actually delete the remote
     * file.  If the remote file is a directory, all contents will be destroyed as 
     * well. 
     * @param path
     * @throws IOException 
     * @throws ServerException 
     */
    public void deleteDir(String path) throws ServerException, IOException{
    	
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	if (sessionUserHasPermission(path)) {
    	
    		client.deleteDir(path);
    		
    	}
    	
    }
    
    /**
     * Delete the file represented by the given URI.
     * 
     * @param path
     * @throws IOException 
     * @throws ServerException 
     * @throws Exception
     */
    public void deleteFile(String path) throws ServerException, IOException  {
        
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	if (sessionUserHasPermission(path)) {
            client.deleteFile(path);
        } else {
            permissionError(path);
        }
    }
    
//    /**
//     * Copies the currently referenced file to the destination URI.
//     * 
//     * @param destination
//     * @throws FileManagementException
//     * @throws IOException 
//     * @throws GlobusCredentialException 
//     * @throws ClientException 
//     * @throws ServerException 
//     * @throws GSSException 
//     */
//    public void copyTo(String path, URI destination) throws ServerException, ClientException, GlobusCredentialException, IOException, GSSException {
//    	
//    	if (!ServiceUtil.isValid(path)) {
//    		throw new FileManagementException("Invalid path");
//    	}
//    	
//    	if (!sessionUserHasPermission(path)) {
//			permissionError(path);
//		}
//    	
//    	if (client.getHost().equals(destination.getHost())) {
//    		if (!sessionUserHasPermission(destination.getPath())) {
//    			permissionError(destination.getPath());
//    		}
//    	}
//    	
//    	client.setLocalPassive();
//		client.setActive();
//		client.setMode(GridFTPSession.MODE_EBLOCK);
//		client.setType(GridFTPSession.TYPE_IMAGE);
//		
//    	GridFTP destClient = new GridFTP(destination.getHost(), destination.getPort(),session.getProxy());
//    	destClient.authenticate();
//		destClient.setPassive();
//		destClient.setLocalActive();
//    	destClient.setMode(GridFTPSession.MODE_EBLOCK);
//		destClient.setType(GridFTPSession.TYPE_IMAGE);
//		
//		
//    	client.extendedTransfer(path, destClient, destination.getPath(), null);
//    		
//    }
    
    
//    /**
//     * Copies a file at the source path to the destination path.
//     * 
//     * @param source
//     * @param destination
//     * @throws FileManagementException
//     * @throws IOException 
//     * @throws GlobusCredentialException 
//     * @throws ClientException 
//     * @throws ServerException 
//     * @throws GSSException 
//     */
//    public void copy(String source, String destination) throws ServerException, ClientException, GlobusCredentialException, IOException, GSSException {
//		
//    	if (!ServiceUtil.isValid(source)) {
//    		throw new FileManagementException("Invalid source path");
//    	}
//    	
//    	if (!ServiceUtil.isValid(destination)) {
//    		throw new FileManagementException("Invalid destination path");
//    	}
//    	
//    	if (!sessionUserHasPermission(source)) {
//			permissionError(source);
//		}
//		
//		if (!sessionUserHasPermission(destination)) {
//			permissionError(source);
//		}
//		
//		client.setPassive();
//		client.setLocalActive();
//		
//		client.setMode(GridFTPSession.MODE_STREAM);
//		client.setType(GridFTPSession.TYPE_IMAGE);
//		
//		GridFTP destClient = new GridFTP(client.getHost(), client.getPort(),session.getProxy());
//		destClient.authenticate();
//		destClient.setLocalPassive();
//		destClient.setActive();
//    	
//		destClient.setMode(GridFTPSession.MODE_STREAM);
//		destClient.setType(GridFTPSession.TYPE_IMAGE);
//		
//		
////		client.setMode(GridFTPSession.MODE_EBLOCK);
////		client.setType(GridFTPSession.TYPE_IMAGE);
//		
//		client.transfer(source, destClient, destination, false, null);
//	}
    
    /**
     * Copy the currently referenced file from the remote machine to the server.
     * 
     * caution: no check is made to see if there is enough memory or free disk space 
     * to copy the file.
     * 
     * @return java.io.File object representing the local version of the copied file. 
     * @throws IOException 
     * @throws ClientException 
     * @throws ServerException 
     * @throws GlobusCredentialException 
     * @throws GSSException 
     */
    public File get(String remotePath, String localFile) throws ServerException, ClientException, IOException, GlobusCredentialException, GSSException {
    	
    	if (!ServiceUtil.isValid(remotePath)) {
    		throw new FileManagementException("Invalid remote path");
    	}
    	
    	if (!ServiceUtil.isValid(localFile)) {
    		throw new FileManagementException("Invalid local path");
    	}
    	
    	if (sessionUserHasPermission(remotePath)) {
    		GridFTP gftp = new GridFTP(getHost(), client.getPort(),session.getProxy());
    		gftp.authenticate();
    		gftp.get(remotePath, localFile);
		} else {
			permissionError(remotePath);
		}
    	
    	return new File(localFile);
    }
    
    public void put(File localFile, String remotePath) throws ServerException, ClientException, IOException {
    	if (!ServiceUtil.isValid(remotePath)) {
    		throw new FileManagementException("Invalid remote path");
    	}
    	
    	if (!ServiceUtil.isValid(localFile)) {
    		throw new FileManagementException("Invalid local file");
    	}
    	
    	if (sessionUserHasPermission(remotePath)) {
    		client.put(localFile, remotePath, false);
		} else {
			permissionError(remotePath);
		}
    	
    }
    
    /**
     * Open up an input file stream to the given path for download by a client. 
     * 
     * @param path
     * @return FTPInputStream from remote file
     * @throws FTPException 
     * @throws IOException 
     * @throws GATObjectCreationException
     */
    public FTPInputStream getFileInputStream(String path) throws IOException, FTPException {
    	
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	FTPInputStream stream = null;
    	if (sessionUserHasPermission(path)) {
//    		client.setMode(GridFTPSession.MODE_STREAM);
         log.info("Retrieving file from " + path + " in getFileInputStream");

    		stream = new GridFTPInputStream(client, path, true, null);
    	} else {
    		permissionError(path);
    	}
    	
    	return stream;
    }
    
    /**
     * Open up an output file stream to the given path for upload by a client. 
     * 
     * @param path
     * @return FTPOutputStream to remote file
     * @throws FTPException 
     * @throws IOException 
     * @throws GATObjectCreationException
     */
    public FTPOutputStream getFileOutputStream(String path) throws IOException, FTPException {
    	
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	FTPOutputStream stream = null;
    	if (sessionUserHasPermission(path)) {
//    		client.setMode(GridFTPSession.MODE_STREAM);
    		stream = new GridFTPOutputStream(client, path, true, false);
    	} else {
    		permissionError(path);
    	}
    	
    	return stream;
    }
    
    
    /**
     * Change the current working directory of the file manager to the given relative path.
     * 
     * @param path
     * @throws ServerException
     * @throws PermissionException
     * @throws IOException
     */
    public void changeDir(String path) throws ServerException, PermissionException, IOException {
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	if (sessionUserHasPermission(client.getCurrentDir() + "/" + path)) {
    		client.changeDir(path);
		} else {
			permissionError(client.getCurrentDir() + "/" + path);
		}
    	
    }
    
    /**
     * @return Vector of FileBean objects.
     * @throws ServerException
     * @throws ClientException
     * @throws IOException
     */
//    public List<FileBean> list() throws ServerException, ClientException, IOException {
//    	
//    	List<FileBean> beans = new ArrayList<FileBean>();
//    	String currentDir = client.getCurrentDir();
//    	
//    	for (FileInfo entry: client.list()) {
//    		beans.add(FileManager.getFileBean(currentDir, entry));
//    	}
//    	
//    	return beans;
//    }
    
    public List<FileBean> list(String path) throws ServerException, ClientException, IOException, InterruptedException {
    	
    	List<FileBean> beans = new ArrayList<FileBean>();
    	String currentDir = client.getCurrentDir();
    	
    	if (!ServiceUtil.isValid(path)) {
    		path = ".";
    	}
    	
    	if (!client.exists(path)) {
    		throw new FileException("No such file or directory" + path);
    	}
    
	Thread.sleep(1000);	
    	for (MlsxEntry entry: client.list(path)) {
    		beans.add(FileManager.getFileBean(currentDir, entry));
    	}
    	
//    	for (FileInfo entry: (Vector<FileInfo>)client.list(path)) {
//    		beans.add(FileManager.getFileBean(currentDir, entry));
//    	}
    	
    	return beans;
    }
    
//    /**
//     * Retrieve a copy of the file represented by the given URI to the local 
//     * machine. In most cases, this will be called from the no-arg 
//     * <code>copyItem()</code> method to retrieve the current file.  Just in 
//     * case, though, the method checks for file existence before attempting to
//     * copy to a directory structure on the local machine identical to the one
//     * on the remote machine.
//     * 
//     * caution: since the <code>length()</code> method is broken on the current
//     * GAT, no check is made to see if there is enough memory or free disk space 
//     * to copy the file.
//     * 
//     * @param path
//     */
//    public java.io.File copyItem(String uri) {
//        java.io.File localFile = null;
//        File tmpfile = null;
//        try {
//            if (isReadable(uri)) {
//                if (uri.equals(this.file.toGATURI().toString())) {
//                    tmpfile = this.file;
//                } else {
//                    tmpfile = GAT.createFile(context,prefs,uri);
//                }
//            } else {
//                permissionError(uri);
//            }
//        } catch (PermissionException pe) {
//            throw pe;
//        } catch (Exception e) {}
//
//        final File remotePath = tmpfile;
//        
//        java.io.File lfile = null;
//        String separator = java.io.File.separator;
//        try {
//            log.info("Retrieving file " + remotePath.getAbsolutePath() + 
//                    " to temporary storage " + new Date().toString());
//            
//            // make sure remote directory structure exists on local machine
//            lfile = new java.io.File(Settings.TEMP_DATA_DIR + 
//                    separator + remotePath.getParentFile().getParentFile().getName() + 
//                    separator + remotePath.getParentFile().getName());
//
//            if (!lfile.exists())
//                lfile.mkdirs();
//            
//            URI tmpLocal = new URI("file:///" + Settings.TEMP_DATA_DIR + 
//                    separator + remotePath.getParentFile().getParentFile().getName() + 
//                    separator + remotePath.getParentFile().getName() +
//                    separator + remotePath.getName());
//            
//            // copy the file via the gat
//            remotePath.copy(tmpLocal);
//            
//            log.info("Successfully retrieved " + remotePath.getName() + 
//                    " to " + lfile.getAbsolutePath() + ". Size (" + 
//                    lfile.length() + ") " + new Date().toString());
//            
//        } catch (URISyntaxException except) {
//            String message = "Error Invoking GAT: Invalid parent directory\n";
//            throw new FileManagementException(message,except);
//        } catch (GATInvocationException except) {
//            //suppress useless messages
//            if (except.getMessage().indexOf(
//                    "Custom message: Could not create FileInfo") != -1);
//            else if (except.getMessage().indexOf(
//                    "GSSException: Expired credentials detected") != -1);
//            else {
//                String message = "File copy failed due to GAT Invocation error.";
//                throw new FileManagementException(message,except);
//            }
//        } catch (Exception e) {
//           e.printStackTrace();
//           String message = "File copy failed due to unknown error.";
//           throw new FileManagementException(message,e);
//       }
//        
//        
//        Thread thread = new Thread() {
//            public java.io.File localFile = null;
//            public void run() {
//                String separator = java.io.File.separator;
//                try {
//                    log.info("Retrieving file " + remotePath.getAbsolutePath() + 
//                            " to temporary storage " + new Date().toString());
//                    
//                    // make sure remote directory structure exists on local machine
//                   localFile = new java.io.File(Settings.TEMP_DATA_DIR + 
//                            separator + remotePath.getParentFile().getParentFile().getName() + 
//                            separator + remotePath.getParentFile().getName());
//
//                    if (!localFile.exists())
//                        localFile.mkdirs();
//                    
//                    URI tmpLocal = new URI("file:///" + Settings.TEMP_DATA_DIR + 
//                            separator + remotePath.getParentFile().getParentFile().getName() + 
//                            separator + remotePath.getParentFile().getName() +
//                            separator + remotePath.getName());
//                    
//                    // copy the file via the gat
//                    remotePath.copy(tmpLocal);
//                    
//                    log.info("Successfully retrieved " + remotePath.getName() + 
//                            " to " + localFile.getAbsolutePath() + ". Size (" + 
//                            localFile.length() + ") " + new Date().toString());
//                    
//                } catch (URISyntaxException except) {
//                    String message = "Error Invoking GAT: Invalid parent directory\n";
//                    throw new FileManagementException(message,except);
//                } catch (GATInvocationException except) {
//                    //suppress useless messages
//                    if (except.getMessage().indexOf(
//                            "Custom message: Could not create FileInfo") != -1);
//                    else if (except.getMessage().indexOf(
//                            "GSSException: Expired credentials detected") != -1);
//                    else {
//                        String message = "File copy failed due to GAT Invocation error.";
//                        throw new FileManagementException(message,except);
//                    }
//                } catch (Exception e) {
//                   String message = "File copy failed due to unknown error.";
//                   throw new FileManagementException(message,e);
//               }
//            }
//        };
//
//        thread.setContextClassLoader(this.getClass().getClassLoader());
//        thread.setName("CopyFileThread");
//        thread.start();
//        
//        try {
////            String separator = java.io.File.separator;
//            localFile = new java.io.File(Settings.TEMP_DATA_DIR + 
//                        separator + remotePath.getParentFile().getParentFile().getName() + 
//                        separator + remotePath.getParentFile().getName() +
//                        separator + remotePath.getName());
//            if (!localFile.exists()) {
//                throw new FileManagementException("Copy operation failed. File not present on local disk.");
//            }
//        } catch (FileManagementException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new FileManagementException("Could not complete copy request. Unknown exception.", e);
//        }
//        
//        return localFile;
//    }
    
    /**
     * Create a new subdirectory in the current directory.
     *  
     * @param newDirName
     * @throws IOException 
     * @throws ServerException 
     * @throws FileManagementException
     */
    public void mkdir(String name) throws ServerException, IOException{
    	
    	if (!ServiceUtil.isValid(name)) {
    		throw new FileManagementException("Invalid directory path");
    	}
    	
        if (sessionUserHasPermission(client.getCurrentDir() + File.separator + name)) {
    		client.makeDir(name);
    	}
    }
    
    public void rename(String path, String newName) throws ServerException, IOException { 
    
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	if (!ServiceUtil.isValid(newName)) {
    		throw new FileManagementException("Invalid new directory name");
    	}
    	
    	if (sessionUserHasPermission(path)) {
    		client.rename(path, newName);
    	}
    	
        
    }
    
    /**
     * Read in the file from disk and place in a String.
     * 
     * @param f
     * @return
     * @throws FileNotFoundException
     * @throws GATInvocationException
     * @throws IOException
     */
    public String serializeFile(File file) 
    throws FileNotFoundException, IOException {
        
    	if (!ServiceUtil.isValid(file)) {
    		throw new FileManagementException("Invalid file");
    	}
    	
    	String stringFile = "";
        
        FileReader fr = new FileReader(file);
    
        BufferedReader in = new BufferedReader(fr);
        
        String nextLine = "";
        
        while (nextLine != null) {
            nextLine = in.readLine();
            stringFile += nextLine + "\n";
        }
        
        in.close();
        
        fr.close();
        
        return stringFile;
        
    }
    
    public void disconnect() throws ServerException, IOException {
        client.close();
        client = null;
    }
    
    /************************ Utility Methods ************************/
    
    /**
     * Check to see if the user has rights to manipulate the file at the requested path.
     * This path is always relative to the current gridftp client directory. Initially
     * this check will simple see if the requested file is in the directory structure
     * allotted them. Specifically all user files should appear on any CCG mass storage
     * server in the following location: 
     * 
     * <code>$CCG_HOME/{internal}/${gridchem_username}</code>
     * 
     * or for external users:
     * 
     * <code>$HOME/gridchem}</code>
     * 
     * Any request not satisfying this condition will be denied.
     * 
     * @param path file path relative to the current directory.
     * @return true if the user has permission to access the file. False otherwise
     */
    private boolean sessionUserHasPermission(String path) {
        boolean readable = false;
        
        // all paths are relative
        try {
        	String currentDir = client.getCurrentDir();
        	path = path.replaceAll("//", "/");
        	
            if (session.getType().equals(AccessType.COMMUNITY)) {
            
                // frame absolute path
            	String  requestedPath = getRequestedPath_communityUser ( path, currentDir );
            	String hostName = client.getHost();			
	
                if ( hostName.equals("mss.ncsa.uiuc.edu") ) {
                	
		/*
                	// protect user privacy. make sure they are in their user subdirectory
                	String requestedPath = ""; 
                	// get the current parent dir if the requested path is relative
                	
                	if (path.equals("../")) {
                		requestedPath = client.getParentPath();
                	} else {
	                	
	            		// we do not support random wildcards or updirs in the path
	            		path = path.replaceAll("\\.\\.\\/","");
	                	
	            		requestedPath = currentDir + "/" + path;
	            		requestedPath = requestedPath.replaceAll("//", "/");
                	}
		*/            								
            		if (requestedPath.indexOf("ccguser/internal/" + session.getUser().getUserName()) > -1) {
                        	readable = true;
                    	} else {
                        	readable = false;
                    	}

                } else if ( hostName.equals( "paramchem.ccs.uky.edu" ) ) {	// if check added by narendra kumar
                        if (requestedPath.indexOf("ccguser/users/" + session.getUser().getUserName()) > -1) {
                                readable = true;
                        } else {
                                readable = false;
                        }
		} else {
//uncommented all by nikhil
                    System.out.println("File is on remote resource and path is "+ path);
                    ComputeBean hpc = new ResourceDao(session).getComputeResourceByHostname(client.getHost());
                    log.info("Checking permission to access " + hpc.getDefaultScratchDirectory().replaceFirst("_USER_","ccguser/")
                            + "/" + session.getUser().getUserName());
                    
                    log.info("Checking permission to access " + path + "\nagainst location " + 
                            hpc.getDefaultScratchDirectory().replaceFirst("_USER_","ccguser") + "/" + session.getUser().getUserName());
                    
                    requestedPath = currentDir + "/" + path; 
                    requestedPath = requestedPath.replaceAll("//", "/");
                    
                    if (requestedPath.indexOf(hpc.getDefaultScratchDirectory().replaceFirst("_USER_","ccguser")
                            + "/" + session.getUser().getUserName()) > -1) {
                        readable = true;
                        System.out.println("File has " + hpc.getDefaultScratchDirectory().replaceFirst("_USER_","ccguser")
                                + "/" + session.getUser().getUserName() + " in the path");
                    } else {
                        System.out.println("File is not valid");
                        readable = false;
                    }
                }
            } else {
//                System.out.println("External user. File is valid");
//                if (uri.getHost().equals("mss.ncsa.uiuc.edu")) {
//                    if (uri.toString().indexOf((String)prefs.get("user")) > -1) {
//                        readable = true;
//                    } else {
//                        readable = false;
//                    }
//                } else {
//                    readable = true;
//                }
                readable = true;
            }
        } catch (Exception e) {
            readable = false;
            e.printStackTrace();
        }
        
        return true;
    }


    private String getRequestedPath_communityUser( String path, String currentDir ) throws ServerException, IOException {

    	// protect user privacy. make sure they are in their user subdirectory
        String requestedPath = "";

        // get the current parent dir if the requested path is relative
        if (path.equals("../")) {
        	requestedPath = client.getParentPath();
        } else {

	       	// we do not support random wildcards or updirs in the path
        	path = path.replaceAll("\\.\\.\\/","");

        	requestedPath = currentDir + "/" + path;
        	requestedPath = requestedPath.replaceAll("//", "/");
        }

	return requestedPath;
    }
    
    private void permissionError(String uri) throws PermissionException {
        String message = "User " + session.getUser().getUserName() + " denied access to file " + 
        	uri + " due to lack of access rights.";
        log.error(message);
        
        throw new PermissionException(message);
    }
    
    
    /************************ Accessor Methods ************************/
    
//    public File getFile() {
//        return this.file;
//    }
//    
//    public Preferences getPreferences() {
//        return this.prefs;
//    }
//    
//    public GATContext getContext() {
//        return this.context;
//    }
    
    /**
     * Generate a bean representation of the remote file object.
     * In doing this, we create a full tree representation of the
     * file or the folder.
     * 
     * note: this does not automatically include the children in the
     * FileBean. These need to be added manually.
     * 
     * @param fileInfo
     * @throws GATInvocationException
     */
    public static FileBean getFileBean(String path, FileInfo fileInfo) throws FileException{
        
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	if (fileInfo == null) {
            throw new FileException("Referenced file cannot be null");
        }
        
        FileBean bean = new FileBean();
        
        bean.setName(fileInfo.getName());
        bean.setPath(path);
        bean.setLastModified(null);
        bean.setReadable(fileInfo.userCanRead());
        bean.setWritable(fileInfo.userCanWrite());
        bean.setDirectory(fileInfo.isDirectory());

    	bean.setLength(fileInfo.getSize());
        
        
        return bean;
    }
    
    public static FileBean getFileBean(String path, MlsxEntry entry) throws FileException {
        
    	if (!ServiceUtil.isValid(path)) {
    		throw new FileManagementException("Invalid path");
    	}
    	
    	if (entry == null) {
            throw new FileException("Referenced file cannot be null");
        }
        
        FileBean bean = new FileBean();
        
        bean.setName(entry.getFileName());
        bean.setPath(path);
        
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            bean.setLastModified(formatter.parse(entry.get("modify")));
		} catch (Exception e) {
			bean.setLastModified(null);
		}
        
        String mode = entry.get("unix.mode");
        if (ServiceUtil.isValid(mode)) {
        	int mod = Integer.valueOf(mode.substring(1)).intValue();
        	if (mod >= 400) {
        		bean.setReadable(true);
        	}
        	if (mod >= 600) {
        		bean.setWritable(true);
        	}
        }
        bean.setOwner(entry.get("unix.owner"));
        bean.setDirectory(entry.get(MlsxEntry.TYPE).equals(MlsxEntry.TYPE_DIR));
    	bean.setLength(new Long(entry.get(MlsxEntry.SIZE)).longValue());
        
        
        return bean;
    }
    
    /**
     * Generate a bean representation of a java.io.File object.
     * 
     * note: this does not automatically include the children in the
     * FileBean. These need to be added manually.
     * 
     * @param file
     * @throws GATInvocationException
     */
    public static FileBean getFileBean(File file) throws FileException{
        
    	if (!ServiceUtil.isValid(file)) {
            throw new FileException("Referenced file cannot be null");
        }
        
        FileBean bean = new FileBean();
        
        try {
            bean.setName(file.getName());
            bean.setPath(file.getAbsolutePath());
//            bean.getLastModified().setTimeInMillis(file.lastModified());
            bean.setReadable(file.canRead());
            bean.setWritable(file.canWrite());
            bean.setDirectory(file.isDirectory());
            bean.setLength(file.length());
            
            // populate the current node with information about the kids one level
            // deep so directory listings make sense.
//            if (bean.isDirectory()) { 
//                
//                for(File child: file.listFiles()) {
//                    FileBean childBean = new FileBean();
//                    childBean.setName(child.getName());
//                    childBean.setPath(child.getAbsolutePath());
//                    Date date = new Date();
//                    date.setTime(file.lastModified());
//                    childBean.setLastModified(date.toString());
//                    childBean.setReadable(child.canRead());
//                    childBean.setWritable(child.canWrite());
//                    childBean.setDirectory(child.isDirectory());
//                    if (!child.isDirectory()) {
//                    	childBean.setLength(child.length());
//                    }
//                    
//                    bean.getChildren().add(childBean);
//                }
//            } 
            
        } catch (Exception e) {
            throw new FileException("Failed to create file bean.",e);
        }
        
        return bean;
    }
    
    /**
     * Rather than serialize the entire file, we break it up into blocks.  The 
     * FileDTO passed back as a result of a retrieveFile operation now only
     * contains a file preview of the first FileBlockReference.BLOCK_SIZE bytes
     * of data.  
     *  
     * @param fbr
     */
    public FileBean getFileBean(FileBlockReference fbr) throws FileException {
        
    	if (fbr == null) {
    		throw new FileManagementException("Invalid path");
    	}
    	
        java.io.File file = new java.io.File(fbr.getFileName());
        
        FileBean bean = new FileBean();
        
        try {
            bean.setName(file.getName());
            bean.setPath(file.getAbsolutePath());
            bean.setLastModified(new Date());
            bean.setReadable(file.canRead());
            bean.setWritable(file.canWrite());
            bean.setDirectory(file.isDirectory());
            bean.setLength(file.length());
            
            // populate the current node with information about the kids one level
            // deep so directory listings make sense.
//            if (bean.isDirectory()) { 
//                for(java.io.File child: file.listFiles()) {
//                	
//                    bean.getChildren().add(getFileBean(
//                            new FileBlockReference(child.getAbsolutePath(),0)));
//                    
//                }
//            } 
        } catch (FileException e) {
            throw e;
        } catch (Exception e) {
            throw new FileException("Fail to create file bean",e);
        }
        
        return bean;
    }
}
