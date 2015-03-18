package org.gridchem.service.dao;

import java.io.File;
import java.util.List;

import org.gridchem.service.model.LogicalFile;
import org.gridchem.service.test.GMSTestCase;

public class LogicalFileDaoTest extends GMSTestCase {
	
	private static final String TEST_FILE_NAME = "temp.inp";
	
	public LogicalFileDaoTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testPersist() throws Exception {
		File file = new File(TEST_FILE_NAME);
		file.createNewFile();
		
		LogicalFile logicalFile = new LogicalFile(user.getId(), TEST_FILE_NAME, file, null, job.getId());
		LogicalFileDao.persist(logicalFile);
	}

	public void testGetAllForJob() {
		List<LogicalFile> files = LogicalFileDao.getAllForJob(job.getId());
		assertNotNull(files);
		assertTrue(files.size() == 1);
	}

	public void testGet() {
		List<LogicalFile> files = LogicalFileDao.getAllForJob(job.getId());
		
		Long fileId = files.get(0).getId();
		
		LogicalFile tmp = LogicalFileDao.get(fileId);
		
		assertNotNull(tmp);
	}
	
	public void testDelete() {
		
		for (LogicalFile file: LogicalFileDao.getAllForJob(job.getId())) {
			LogicalFileDao.delete(file);
		}
		
		assertTrue(LogicalFileDao.getAllForJob(job.getId()).size() == 0);
	}
	
	public void testTearDown() throws Exception {
		
		super.testTearDown();
		
		File file = new File(TEST_FILE_NAME);
		file.delete();
	}

	


}
