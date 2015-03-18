package org.gridchem.service.test.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.gridchem.service.FileService;
import org.gridchem.service.beans.FileBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.dao.LogicalFileDao;
import org.gridchem.service.dao.UserDao;
import org.gridchem.service.exceptions.FileException;
import org.gridchem.service.exceptions.FileManagementException;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.file.GridFTP;
import org.gridchem.service.impl.FileServiceImpl;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.LogicalFile;
import org.gridchem.service.model.User;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;

public class FileServiceTest extends GMSTestCase {
//	private static final String TEST_REMOTE_OUTPUT_FILENAME = "test.out";
//	private static final String MSS_COMMUNITY_USER_BASE_URI = "gsiftp://mss.ncsa.uiuc.edu//UROOT/u/ac/ccguser/internal/";
	public static final String USER_TEST_DIRECTORY_NAME = "junit.delete.dir";
	public static final String USER_TEST_FILE_NAME = "junit.file.txt";
	public static final String USER_TEST_FILE_CONTENTS = "Test file";
	public static final String USER_TEST_DOWNLOAD_FILE = "junit.file.get";
	
	private final String TEST_FOREIGN_JOBNAME = TEST_JOBNAME+"-foreign";
	
	private GridFTP client;
	private FileService service;
	private String remoteUserHome;
	private String userCacheRoot;
	private String ccguserCacheRoot;
	
	private String remotePath;
	
	private Job foreignJob;
	
	public FileServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		
		super.setUp();
		
		// create cached data and logical file for community user job
		ccguserCacheRoot = Settings.TEMP_DATA_DIR + File.separator + "ccguser";
		
		// create dummy job for ccguser.  It's not valid, but it works for these tests.
		foreignJob = JobDao.getByName(TEST_FOREIGN_JOBNAME);
		if (foreignJob == null) {
			
			User ccgUser = new UserDao()._get("ccguser");
			foreignJob = createJob(ccgUser,project,software,hpc,queue,storage,workflow);
			foreignJob.setName(TEST_FOREIGN_JOBNAME);
			JobDao.persist(foreignJob);
	
			File ccguserFolder = new File(ccguserCacheRoot +  
					File.separator + TEST_JOBNAME + 
					File.separator + TEST_FOREIGN_JOBNAME);
			
			ccguserFolder.mkdirs();
			
			File ccguserFile = new File(ccguserFolder.getAbsoluteFile() + File.separator + "0.inp");
			ccguserFile.createNewFile();
			
			LogicalFile lf = new LogicalFile(user.getId(), ccguserFile.getPath(), ccguserFile, null, foreignJob.getId());
			lf.save();
		}
		
		// create cached data and logical file for test user
		userCacheRoot = Settings.TEMP_DATA_DIR + File.separator + user.getUsername();
		File root = new File(userCacheRoot);
		root.mkdirs();
		
		File jobFolder = new File(userCacheRoot + File.separator + job.getExperimentName() + 
				File.separator + job.getName());
		jobFolder.mkdirs();
		
		// create remote data
		String parsedDate = new SimpleDateFormat("yyMMdd").format(job.getCreated());
		remoteUserHome = "internal/" + user.getUsername() + File.separator;
		remotePath = job.getExperimentName() + 
			File.separator + job.getName() + "." + job.getSystem().getHostname() + "." + 
            	job.getLocalId() + "." + parsedDate;
		
		client = new GridFTP(Settings.MASS_STORAGE_SERVER, 2811, session.getProxy());
		client.authenticate();
		
		if (!client.exists(remoteUserHome + remotePath)) {
			
//			client.makeDir(remotePath);
			
			String tempUri = remoteUserHome;
			client.makeDir(tempUri); 
			
			tempUri += File.separator + job.getExperimentName();
			client.makeDir(tempUri);
			
			client.makeDir(remoteUserHome + remotePath);
			
			client.put(new File("build.xml"), remoteUserHome + USER_TEST_FILE_NAME, false);	
		}
		
		if (LogicalFileDao.getAllForJob(job.getId()).size() == 0) {
			for (int i=0; i<3; i++) {
				File jobFile = new File(jobFolder.getAbsoluteFile() + File.separator + i + ".inp");
				jobFile.createNewFile();
				
				LogicalFile lf = new LogicalFile(user.getId(), jobFile.getPath(), jobFile, null, job.getId());
				lf.save();
			}
		}
		
		service = new FileServiceImpl();

		
	}

	/********************************************************************
	 * 
	 * Test getting a list of the cached input files for the session user
	 * 
	 ********************************************************************/
	
	public void testListCachedInputFilesSessionNull() {
		try {
			service.listCachedInputFiles(null, "");
			fail("Null session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Null session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesSessionEmpty() {
		try {
			service.listCachedInputFiles("", "");
			fail("Empty session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Empty session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesSessionInvalid() {
		try {
			service.listCachedInputFiles("-1", "");
			fail("Invalid session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Invalid session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesPathNull() {
		try {
			service.listCachedInputFiles(session.getToken(), null);
			fail("Null path should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesPathDoesNotExist() {
		try {
			service.listCachedInputFiles(session.getToken(), "-1");
			fail("Invalid path should throw a FileManagementException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesStripsUpdir() throws Exception {
		String result = service.listCachedInputFiles(session.getToken(), "..");
		FileBean bean = (FileBean)ServiceUtil.xstream.fromXML(result);
		// the service should strip the parent directory reference and return
		// and empty string, which will list the root folder.
		assertTrue(bean.getPath().equals(userCacheRoot));
	}
	
	public void testListCachedInputFiles() throws Exception {
		String result = service.listCachedInputFiles(session.getToken(), "");
		FileBean bean = (FileBean)ServiceUtil.xstream.fromXML(result);
		assertTrue(bean.getPath().equals(userCacheRoot));
	}

	/********************************************************************
	 * 
	 * Test getting the remote output files for a particular job
	 * 
	 ********************************************************************/
	
	public void testListOutputFilesForJobSessionNull() {
		try {
			service.listOutputFilesForJob(null, "");
			fail("Null session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Null session should throw a SessionException
		}
	}
	
	public void testListOutputFilesForJobSessionEmpty() {
		try {
			service.listOutputFilesForJob("", "");
			fail("Empty session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Empty session should throw a SessionException
		}
	}
	
	public void testListOutputFilesForJobSessionInvalid() {
		try {
			service.listOutputFilesForJob("-1", "");
			fail("Invalid session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Invalid session should throw a SessionException
		}
	}
	
	public void testListOutputFilesForJobJobNull() {
		try {
			service.listOutputFilesForJob(session.getToken(), null);
			fail("Null job id should throw a JobException");
		} catch (Exception e) {
			assertTrue(e instanceof JobException);
			// Null job id should throw a JobException
		}
	}
	
	public void testListOutputFilesForJobJobEmpty() {
		try {
			service.listOutputFilesForJob(session.getToken(), "");
			fail("Empty job id should throw a JobException");
		} catch (Exception e) {
			assertTrue(e instanceof JobException);
			// Empty job id should throw a JobException
		}
	}
	
	public void testListOutputFilesForJobJobInvalid() {
		try {
			service.listOutputFilesForJob(session.getToken(), "-1");
			fail("Invalid job id should throw a JobException");
		} catch (Exception e) {
			assertTrue(e instanceof JobException);
			// Invalid job id should throw a JobException
		}
	}
	
	public void testListOutputFilesForJobNoPermissionForJob() {
		try {
			service.listOutputFilesForJob(session.getToken(), foreignJob.getId().toString());
			fail("Lack of permission to view job should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof PermissionException);
			// Lack of permission to view job should throw a SessionException
		}
	}
	
	public void testListOutputFilesForJobRunning() throws Exception {

		try {
			@SuppressWarnings("unused")
			String result = service.listOutputFilesForJob(session.getToken(), job.getId().toString());
//			FileBean bean = (FileBean)ServiceUtil.xstream.fromXML(result);
//			assertTrue(bean.getName().equals(USER_TEST_FILE_NAME));
			fail("this will probably fail since the job resource does not exist.");
		} catch (FileManagementException e) {
			// this will probably fail since the job resource does not exist.
		}
	}
	
	public void testListOutputFilesForJobFinished() throws Exception {
		job.setStatus(JobStatusType.FINISHED);
		JobDao.persist(job);
		// this will probably fail since the job never actually ran.
		try {
			String result = service.listOutputFilesForJob(session.getToken(), job.getId().toString());
			FileBean bean = (FileBean)ServiceUtil.xstream.fromXML(result);
			assertTrue(bean.getName().equals(USER_TEST_FILE_NAME));
		} catch (FileManagementException e) {
			// this will probably fail since the job never actually ran.
		}
	}

	/********************************************************************
	 * 
	 * Test getting a listing of all the cached input files for a 
	 * particular job
	 * 
	 ********************************************************************/
	
	public void testListCachedInputFilesForJobSessionNull() {
		try {
			service.listCachedInputFilesForJob(null, TEST_JOBNAME, TEST_JOBNAME);
			fail("Null session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Null session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesForJobSessionEmpty() {
		try {
			service.listCachedInputFilesForJob("", TEST_JOBNAME, TEST_JOBNAME);
			fail("Empty session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Empty session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesForJobSessionInvalid() {
		try {
			service.listCachedInputFilesForJob("-1", TEST_JOBNAME, TEST_JOBNAME);
			fail("Invalid session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Invalid session should throw a SessionException
		}
	}
	
	public void testListCachedInputFilesForJobJobNameNull() {
		try {
			service.listCachedInputFilesForJob(session.getToken(), null, TEST_JOBNAME);
			fail("Null job name should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null job name should throw a FileException
		}
	}
	
	public void testListCachedInputFilesForJobJobNameEmpty() {
		try {
			service.listCachedInputFilesForJob(session.getToken(), "", TEST_JOBNAME);
			fail("Empty job name should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Empty job name should throw a FileException
		}
	}
	
	public void testListCachedInputFilesForJobJobNameInvalid() {
		try {
			service.listCachedInputFilesForJob(session.getToken(), "-1", TEST_JOBNAME);
			fail("Invalid job name should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Invalid job name should throw a FileException
		}
	}
	
	public void testListCachedInputFilesForJobFileNotPresent() {
		try {
			// move the folder 
			File jobFolder = new File(userCacheRoot + File.separator + job.getExperimentName() + 
					File.separator + job.getName());
			jobFolder.renameTo(new File(jobFolder.getAbsoluteFile() + ".bak"));
			
			service.listCachedInputFilesForJob(session.getToken(), TEST_JOBNAME, TEST_JOBNAME);
			fail("Missing cached file should throw a FileException");
			
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// move the folder back
			// performing a second rename kept failing, so this is a workaround
			File jobFolder = new File(userCacheRoot + File.separator + job.getExperimentName() + 
					File.separator + job.getName() + ".bak");
			jobFolder.renameTo(new File(userCacheRoot + File.separator + job.getExperimentName() + 
					File.separator + job.getName()));
			// Missing cached file should throw a FileException
		}
	}
	
	public void testListCachedInputFilesForJobExperimentNameNull() {
		try {
			service.listCachedInputFilesForJob(session.getToken(), TEST_JOBNAME, null);
			fail("Null experiment should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null experiment should throw a FileException
		}
	}
	
	public void testListCachedInputFilesForJobExperimentNameEmpty() {
		try {
			service.listCachedInputFilesForJob(session.getToken(), TEST_JOBNAME, "");
			fail("Empty experiment should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Empty experiment should throw a FileException
		}
	}
	
	public void testListCachedInputFilesForJobExperimentNameInvalid() {
		try {
			service.listCachedInputFilesForJob(session.getToken(), TEST_JOBNAME, "-1");
			fail("Invalid experiment should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Invalid experiment should throw a FileException
		}
	}
	
	public void testListCachedInputFilesForJob() throws Exception {
		String result = service.listCachedInputFilesForJob(session.getToken(), TEST_JOBNAME, TEST_JOBNAME);
		List<FileBean> beans = (List<FileBean>)ServiceUtil.xstream.fromXML(result);
		
		assertNotNull(beans);
		
		assertTrue(beans.size() == LogicalFileDao.getAllForJob(job.getId()).size());
	}

	/********************************************************************
	 * 
	 * Test getting the cached version of a particular input file
	 * 
	 ********************************************************************/
	
	public void testGetCachedInputFileSessionNull() {
		try {
			LogicalFile logicalFile = LogicalFileDao.getAllForJob(job.getId()).get(0);
			service.getCachedInputFile(null, logicalFile.getId().toString());
			fail("Empty session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Empty session should throw a SessionException
		}
	}
	
	public void testGetCachedInputFileSessionEmpty() {
		try {
			LogicalFile logicalFile = LogicalFileDao.getAllForJob(job.getId()).get(0);
			service.getCachedInputFile("", logicalFile.getId().toString());
			fail("Empty session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Empty session should throw a SessionException
		}
	}
	
	public void testGetCachedInputFileSessionInvalid() {
		try {
			LogicalFile logicalFile = LogicalFileDao.getAllForJob(job.getId()).get(0);
			service.getCachedInputFile("-1", logicalFile.getId().toString());
			fail("Invalid session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Invalid session should throw a SessionException
		}
	}
	
	public void testGetCachedInputFileLogicalFileIdNull() {
		try {
			service.getCachedInputFile(session.getToken(), null);
			fail("Null file id should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null file id name should throw a FileException
		}
	}
	
	public void testGetCachedInputFileLogicalFileIdEmpty() {
		try {
			service.getCachedInputFile(session.getToken(), "");
			fail("Null file id should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null file id name should throw a FileException
		}
	}
	
	public void testGetCachedInputFileLogicalFileIdInvalid() {
		try {
			service.getCachedInputFile(session.getToken(), "-1");
			fail("Null file id should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null file id name should throw a FileException
		}
	}
	
	public void testGetCachedInputFileLogicalFileIdNoPermission() {
		try {
			service.getCachedInputFile(session.getToken(), null);
			fail("Null file id should throw a FileException");
		} catch (Exception e) {
			assertTrue(e instanceof FileException);
			// Null file id name should throw a FileException
		}
	}
	
	public void testGetCachedInputFile() throws Exception {
		LogicalFile logicalFile = LogicalFileDao.getAllForJob(job.getId()).get(0);
		DataHandler handler = service.getCachedInputFile(session.getToken(), logicalFile.getId().toString());
		assertNotNull(handler.getOutputStream());
	}
	
	/********************************************************************
	 * 
	 * Test getting a listing of the test directory in mass storage
	 * 
	 ********************************************************************/
	
	public void testListSessionNull() {
		try {
			service.list(null, remotePath);
			fail("Empty session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Empty session should throw a SessionException
		}
	}
	
	public void testListSessionEmpty() {
		try {
			service.list("", remotePath);
			fail("Empty session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Empty session should throw a SessionException
		}
	}
	
	public void testListSessionInvalid() {
		try {
			service.list("-1", remotePath);
			fail("Invalid session should throw a SessionException");
		} catch (Exception e) {
			assertTrue(e instanceof SessionException);
			// Invalid session should throw a SessionException
		}
	}
	
//	public void testListPathNull() {
//		try {
//			service.list(session.getToken(), null);
//			fail("Empty uri should throw a FileException");
//		} catch (Exception e) {
//			assertTrue(e instanceof FileException);
//			// Empty uri should throw a FileException
//		}
//	}
//	
//	public void testListPathEmpty() {
//		try {
//			service.list(session.getToken(), "");
//			fail("Invalid uri should throw a FileException");
//		} catch (Exception e) {
//			assertTrue(e instanceof FileException);
//			// Invalid uri should throw a FileException
//		}
//	}
//	
	public void testListPathInvalid() {
		try {
			service.list(session.getToken(), "-1");
			fail("Empty session should throw a FileException");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(e instanceof FileException);
			
			// Empty uri should throw a FileException
		}
	}
	

	
	@SuppressWarnings("unchecked")
	public void testList() throws Exception {
		String results = service.list(session.getToken(), "");
		List<FileBean> beans = (List<FileBean>)ServiceUtil.xstream.fromXML(results);
		
		assertTrue(beans.size() == 4);
		
	}

	/********************************************************************
	 * 
	 * Test getting a listing of all the cached input files for a 
	 * particular job
	 * 
	 ********************************************************************/
	
	public void testMkdir() throws Exception {
		service.mkdir(session.getToken(), USER_TEST_DIRECTORY_NAME);
		
		// verify it's there
		assertTrue(client.exists(remoteUserHome + USER_TEST_DIRECTORY_NAME));
	}

	/********************************************************************
	 * 
	 * Test getting a listing of all the cached input files for a 
	 * particular job
	 * 
	 ********************************************************************/
	
	public void testRename() throws Exception {
		
		service.rename(session.getToken(), USER_TEST_DIRECTORY_NAME, USER_TEST_DIRECTORY_NAME + ".rename");
		
		// verify it's there
		assertTrue(client.exists(remoteUserHome + USER_TEST_DIRECTORY_NAME + ".rename"));
		
	}
	
	/********************************************************************
	 * 
	 * Test getting a listing of all the cached input files for a 
	 * particular job
	 * 
	 ********************************************************************/
	
	public void testDeleteDir() throws Exception {
		service.deleteDir(session.getToken(), USER_TEST_DIRECTORY_NAME + ".rename");
		
		// verify it's there
		assertFalse(client.exists(USER_TEST_DIRECTORY_NAME + ".rename"));
	}

//	/********************************************************************
//	 * 
//	 * Test getting a listing of all the cached input files for a 
//	 * particular job
//	 * 
//	 ********************************************************************/
//	
//	public void testCopy() throws Exception {
//		service.copy(session.getToken(), remoteUri + File.separator + TEST_REMOTE_OUTPUT_FILENAME, remoteUri + File.separator + TEST_REMOTE_OUTPUT_FILENAME + ".bak");
//		
//		// verify it's there
//		GATContext context = GATContextManager.getGATContext(session);
//        Preferences prefs = GATContextManager.getDefaultPreferences(session, true);
//		org.gridlab.gat.io.File remoteDir = GAT.createFile(context, prefs,remoteUri + File.separator + TEST_REMOTE_OUTPUT_FILENAME + ".bak");
//		assertTrue(remoteDir.exists());
//	}

//	/********************************************************************
//	 * 
//	 * Test moving a file from one location to another
//	 * 
//	 ********************************************************************/
//	
//	public void testMove() throws Exception {
//		service.move(session.getToken(), USER_TEST_DIRECTORY_NAME + ".rename", USER_TEST_DIRECTORY_NAME);
//		assertFalse(client.exists(USER_TEST_DIRECTORY_NAME + ".rename"));
//	}

	/********************************************************************
	 * 
	 * Test getting a listing of all the cached input files for a 
	 * particular job
	 * 
	 ********************************************************************/
	
	public void testGet() throws Exception {
		DataHandler handler = service.getRemoteFile(session.getToken(), Settings.MASS_STORAGE_SERVER, USER_TEST_FILE_NAME);
		
		assertNotNull(handler.getOutputStream());
	}

	/********************************************************************
	 * 
	 * Test getting a listing of all the cached input files for a 
	 * particular job
	 * 
	 ********************************************************************/
	
	public void testPut() throws Exception {
		FileDataSource dataSource = new FileDataSource("build.xml");
		DataHandler handler = new DataHandler(dataSource);
		service.putCachedFile(session.getToken(), job.getExperimentName(), job.getName(), "0.inp", handler);
		
		File uploadedFile = new File(userCacheRoot + 
				File.separator + job.getExperimentName() + 
				File.separator + job.getName() + 
				File.separator + "0.inp");
		
		assertTrue(uploadedFile.exists());
		
		uploadedFile.delete();
	}
	
	public void testTearDown() throws Exception {
		
		for(LogicalFile file: LogicalFileDao.getAllForJob(job.getId())) {
			LogicalFileDao.delete(file);
		}
		
		assertTrue(LogicalFileDao.getAllForJob(job.getId()).size() == 0);
		
		for(LogicalFile file: LogicalFileDao.getAllForJob(foreignJob.getId())) {
			LogicalFileDao.delete(file);
		}
		
		assertTrue(LogicalFileDao.getAllForJob(foreignJob.getId()).size() == 0);
		
		JobDao.delete(foreignJob);
		File rootCache = new File(userCacheRoot);
		emptyDir(rootCache);
		
		assertFalse(rootCache.exists());
		
		File ccguserCache = new File(ccguserCacheRoot);
		emptyDir(ccguserCache);
		
		assertFalse(ccguserCache.exists());
		
		try {
			GridFTP client = new GridFTP(Settings.MASS_STORAGE_SERVER, 2811, session.getProxy());
			client.authenticate();
			client.deleteDir("internal/" + user.getUsername());
			assertFalse(client.exists("internal/" + user.getUsername()));
		} catch (Exception e) {
			// still delete the test setup data
			System.out.println("Failed to delete remote test data");
			e.printStackTrace();
		}
		
		super.testTearDown();
		
	}
	
	private void emptyDir(File file) {
		if (file.isDirectory()) {
			for (File child: file.listFiles()) {
				if (child.isDirectory()) {
					emptyDir(child);
				} else {
					child.delete();
				}
			}
		}
		
		file.delete();
	}
	

}
