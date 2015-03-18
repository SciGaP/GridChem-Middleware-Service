/*
 * Portions of this file Copyright 2004-2007 Shanghai Jiaotong University
 * 
 * This file or a portion of this file is licensed under the
 * terms of the Globus Toolkit Public License, found at
 * http://www.globus.org/toolkit/legal/4.0/
 * If you redistribute this file, with or without
 * modifications, you must include this notice in the file.
 */

package org.gridchem.service.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.globus.ftp.DataSink;
import org.globus.ftp.DataSource;
import org.globus.ftp.FileRandomIO;
import org.globus.ftp.GridFTPClient;
import org.globus.ftp.GridFTPSession;
import org.globus.ftp.Marker;
import org.globus.ftp.MarkerListener;
import org.globus.ftp.MlsxEntry;
import org.globus.ftp.RetrieveOptions;
import org.globus.ftp.exception.ClientException;
import org.globus.ftp.exception.ServerException;
import org.globus.ftp.vanilla.Reply;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.X509Credential;
import org.globus.gsi.GlobusCredentialException;
import org.globus.gsi.CredentialException;
import org.globus.gsi.gssapi.GlobusGSSCredentialImpl;
import org.gridchem.service.exceptions.CredentialManagementException;
import org.gridforum.jgss.ExtendedGSSCredential;
import org.gridforum.jgss.ExtendedGSSManager;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;

public class GridFTP extends GridFTPClient {
	public static final Logger logger = LogManager.getLogger(GridFTP.class);
	
	public static String Prefix = "gsiftp://";

	protected GSSCredential gssCred = null;

	protected X509Credential globusCred = null;
	//protected GlobusCredential globusCred = null;

	protected int nParallel = 1;

	//public GridFTP(String sHost, int nPort, GlobusCredential cred)
	public GridFTP(String sHost, int nPort, X509Credential cred)
	throws IOException, ServerException {
		
		super(sHost, nPort);
		this.globusCred = cred;
	}
	
	public GridFTP(String sHost, int nPort, String sCred)
	throws IOException, ServerException, GlobusCredentialException {
		
		super(sHost, nPort);
		try {
		
		ByteArrayInputStream bisProxyString = new ByteArrayInputStream(
				sCred.getBytes());
		//this.globusCred = new GlobusCredential(bisProxyString);
		this.globusCred = new X509Credential(bisProxyString);
		
 		} catch (CredentialException e){ e.printStackTrace();} 
	}
	
	//public void setCredential(GlobusCredential cred) {
	public void setCredential(X509Credential cred) {
		this.globusCred = cred;
	}

	public void setCredential(java.io.File proxyFile) throws GSSException, IOException {

		byte[] data = new byte[(int) proxyFile.length()];
		
		FileInputStream in = new FileInputStream(proxyFile);
		// read in the credential data
		in.read(data);
		in.close();
		ExtendedGSSManager manager = (ExtendedGSSManager) ExtendedGSSManager
				.getInstance();
		GSSCredential proxy = manager.createCredential(data,
				ExtendedGSSCredential.IMPEXP_OPAQUE,
				GSSCredential.DEFAULT_LIFETIME, null, // use default mechanism -
				// GSI
				GSSCredential.ACCEPT_ONLY);

		if (proxy instanceof GlobusGSSCredentialImpl) {
			globusCred = ((GlobusGSSCredentialImpl) proxy).getX509Credential();
			//globusCred = ((GlobusGSSCredentialImpl) proxy).getGlobusCredential(); //JGlobus-2.0.6 deprecation
		} else {
			throw new CredentialManagementException ("Invalid proxy type");
		}
	}

	public void authenticate() throws GSSException, ServerException, IOException, ClientException {
//		logger.debug("Authenticating with remote resource.");
		// convert a GlobusCredentail to a GSSCredntial required byFTP
		this.gssCred = new GlobusGSSCredentialImpl(globusCred,
				GSSCredential.DEFAULT_LIFETIME);
		
		authenticate(this.gssCred);
//		setClientWaitParams(20000, 100);
//		setParellel(1);
		setPassiveMode(true);
//		setPassive();
//		setLocalActive();
//		setMode(GridFTPSession.MODE_EBLOCK);
//		setType(GridFTPSession.TYPE_IMAGE);
		
		
		// setProtectionBufferSize(16384);//buffersize
		// setType(GridFTPSession.TYPE_IMAGE); //transfertype
		// setMode(GridFTPSession.MODE_EBLOCK); //transfermode
		// setDataChannelAuthentication(DataChannelAuthentication.SELF);
		// setDataChannelProtection(GridFTPSession.PROTECTION_SAFE);
	}

	public void changeDir(String sDir) throws IOException, ServerException {
		if (sDir.equals("..")) {
			sDir = getCurrentDir();
			int nIndex = sDir.lastIndexOf("/");
			if (nIndex <= 0) {
				nIndex++;
			}
			sDir = sDir.substring(0, nIndex);
		}
		super.changeDir(sDir);
	}

	
	public void deleteDir(String sDir) throws ServerException, IOException {
		try {
			List<MlsxEntry> entries = list(sDir);
			for (MlsxEntry entry: entries) {
				String name = entry.getFileName();
				if (name.equals(".") || name.equals("..")) {
					continue;
				}
				if (entry.get(MlsxEntry.TYPE).contains("dir")) {
					deleteDir(sDir + File.separator + name);
				} else {
//					logger.debug("Deleting: " + sDir + File.separator + name );
					deleteFile(sDir + File.separator + name);
				}
			}
//			logger.debug("Deleting: " + sDir);
			super.deleteDir(sDir);
		} catch (ClientException e) {
			throw new ServerException(ServerException.UNSUPPORTED_FEATURE, e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public Vector<MlsxEntry> list(String path) throws ServerException, ClientException, IOException{
		setPassiveMode(true);
		return (Vector<MlsxEntry>)super.mlsd(path);
	}

//	public Vector<FileInfo> list() throws IOException, ServerException,
//			ClientException {
//		// setLocalNoDataChannelAuthentication();
//		Vector<FileInfo> v = new Vector<FileInfo>();
//		// InputStreamDataSink iSink = new InputStreamDataSink(); //This will
//		// cause some dirs cannot be listed
//		OutputStream o = new ByteArrayOutputStream();
//		DataSinkStream iSink = new DataSinkStream(o);
//		super.list("", "", iSink); // Cannot use any filter
//		String[] sLines = o.toString().split("\r\n");
//		for (int i = 0, l = sLines.length; i < l; i++) {
//			String listing = "";
//			if (super.getHost().contains("mss.ncsa")) {
//				int j=0;
//				String[] tokens = sLines[i].split("[\\s]+");
//				for (String token: tokens) {
//					if (j != 3 && j != 5) {
//						listing += token + " ";
//					}
//					j++;
//				}
//				listing = listing.trim();
////				listing = listing.replaceAll("root DK", "")
////				  .replaceAll("root AR", "")
////				  .replaceAll("ac DK", "")
////				  .replaceAll("ac AR", "");
//			} else {
//				listing = sLines[i];
//			}
//			try {
//				FileInfo f = new FileInfo(listing);
//				v.add(f);
//			} catch (FTPException ex) {
//				logger.error("failed to create file info",ex);
//			}
//		}
//		return v;
//		// return super.list(sFilter); //This cannot work in GridFTP of GT
//		// higher than 3.9
//	}

	public void get(String sRemoteFile, String sLocalFile) throws IOException,
			ServerException, ClientException {
		long size = getSize(sRemoteFile);
		DataSink sink = null;
		setMode(GridFTPSession.MODE_EBLOCK);
		setType(GridFTPSession.TYPE_IMAGE);
		sink = new FileRandomIO(new RandomAccessFile(sLocalFile, "rw"));
		setLocalPassive();
		setActive();
		MarkerListener ml = new MarkerListener() {

			public void markerArrived(Marker m) {
				System.out.println(m.toString());
			}
			
		};
		extendedGet(sRemoteFile, size, sink, ml);
	}

	public void put(String sLocalFile, String sRemoteFile,
			MarkerListener listener, boolean bAppend) throws IOException,
			ServerException, ClientException {
		DataSource source = null;
		source = new FileRandomIO(new RandomAccessFile(sLocalFile, "rw"));
		setPassive();
		setLocalActive();
		extendedPut(sRemoteFile, source, listener);
		
	}

	public void setParellel(int nParallel) throws IOException, ServerException {
		gSession.parallel = nParallel;
		if (nParallel > 1) {
			setMode(GridFTPSession.MODE_EBLOCK);
			setOptions(new RetrieveOptions(nParallel));
		} else {
			setMode(GridFTPSession.MODE_STREAM);
		}
	}
	
	@Override
	public Reply site(String arg) throws IOException, ServerException {
		String[] args = arg.split(" ");
		return super.site("chmod " + args[1] + " " + args[2]);
	}

	//public GlobusCredential getGlobusCred() {
	//	return this.globusCred;
	//}
        public X509Credential getX509Cred() {
                return this.globusCred;
        }

	
	public String getParentPath() throws ServerException, IOException {
		String parentDir = getCurrentDir();
		int nIndex = parentDir.lastIndexOf("/");
		if (nIndex <= 0) {
			nIndex++;
		}
		parentDir = parentDir.substring(0, nIndex);
		
		return parentDir;
	}
}
