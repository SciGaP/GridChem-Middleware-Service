package org.gridchem.service.test.service;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.gridchem.service.JobService;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.ParameterException;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.exceptions.SessionException;
import org.gridchem.service.impl.JobServiceImpl;
import org.gridchem.service.job.prediction.QbetsPrediction;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.test.GMSTestCase;
import org.gridchem.service.util.ServiceUtil;

public class JobServiceTest extends GMSTestCase {

	private JobService service;
	
	public JobServiceTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		service = new JobServiceImpl();
	}

	/*********************************************************
	 * 
	 * 		Test Listing
	 *  
	 *********************************************************/
	
	public void testListNullSessionToken() {
		try {
			service.list(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testListEmptySessionToken() {
		try {
			service.list("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testListInvalidSessionToken() {
		try {
			service.list("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testList() {
		String sBeans = service.list(TEST_SESSIONTOKEN);
		
		assertNotNull(sBeans);
		
		assertTrue(((List<JobBean>)ServiceUtil.xstream.fromXML(sBeans)).size() == 1);
	}

	/*********************************************************
	 * 
	 * 		Test Listing All
	 *  
	 *********************************************************/
	
	public void testListAllNullSessionToken() {
		try {
			service.listAll(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testListAllEmptySessionToken() {
		try {
			service.listAll("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testListAllInvalidSessionToken() {
		try {
			service.listAll("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testListAll() {
		String sBeans = service.listAll(TEST_SESSIONTOKEN);
		
		assertNotNull(sBeans);
		
		assertTrue(((List<JobBean>)ServiceUtil.xstream.fromXML(sBeans)).size() == 1);
	}

	
	/*********************************************************
	 * 
	 * 		Test Search
	 *  
	 *********************************************************/
	
	public void testSearchNullSessionToken() {
		try {
			service.search(null, ServiceUtil.xstream.toXML(job.toBean()));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testSearchEmptySessionToken() {
		try {
			service.search("", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testSearchInvalidSessionToken() {
		try {
			service.search("-1", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testSearchNullJobString() {
		try {
			service.search(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {}
	}
	
	public void testSearchEmptyJobString() {
		try {
			service.search(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {}
	}
	
	public void testSearchInvalidJobString() {
		try {
			service.search(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void testSearch() {
		String sBeans = service.search(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(job.toBean()));
		
		assertNotNull(sBeans);
		
		assertTrue(((List<JobBean>)ServiceUtil.xstream.fromXML(sBeans)).size() == 1);
	}

	/*********************************************************
	 * 
	 * 		Test Create Job
	 *  
	 *********************************************************/
	
	public void testCreateJobNullSessionToken() {
		try {
			service.createJob(null, ServiceUtil.xstream.toXML(job.toBean()));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testCreateJobEmptySessionToken() {
		try {
			service.createJob("", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testCreateJobInvalidSessionToken() {
		try {
			service.createJob("-1", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {}
	}
	
	public void testCreateJobNullJobString() {
		try {
			service.createJob(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {}
	}
	
	public void testCreateJobEmptyJobString() {
		try {
			service.createJob(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {}
	}
	
	public void testCreateJobInvalidJobString() {
		try {
			service.createJob(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {}
	}
	
	public void testCreateJob() {
		String result = service.createJob(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(job.toBean()));
		assertNotNull(result);
	}

	
	/*********************************************************
	 * 
	 * 		Test File Upload
	 *  
	 *********************************************************/
	
	public void testUploadInputFile() {
		fail("Not yet implemented");
	}

	/*********************************************************
	 * 
	 * 		Test File Download
	 *  
	 *********************************************************/
	
	public void testDownloadInputFile() {
		fail("Not yet implemented");
	}

	/*********************************************************
	 * 
	 * 		Test Submit
	 *  
	 *********************************************************/
	
	public void testSubmitNullSessionToken() {
		try {
			service.submit(null, ServiceUtil.xstream.toXML(job.toBean()));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testSubmitEmptySessionToken() {
		try {
			service.submit("", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testSubmitInvalidSessionToken() {
		try {
			service.submit("-1", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testSubmitNullJobString() {
		try {
			service.submit(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null serialized job should throw a job exception");
		}
	}
	
	public void testSubmitEmptyJobString() {
		try {
			service.submit(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty serialized job should throw a job exception");
		}
	}
	
	public void testSubmitInvalidJobString() {
		try {
			service.submit(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid serialized job should throw a job exception");
		}
	}
	
	public void testSubmit() throws Exception {
		Long result = service.submit(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(job.toBean()));
		assertNotNull(result);
	}

	/*********************************************************
	 * 
	 * 		Test Kill
	 *  
	 *********************************************************/
	
	public void testKillNullSessionToken() {
		try {
			service.kill(null, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testKillEmptySessionToken() {
		try {
			service.kill("", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testKillInvalidSessionToken() {
		try {
			service.kill("-1", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testKillNullJobString() {
		try {
			service.kill(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null serialized job should throw a job exception");
		}
	}
	
	public void testKillEmptyJobString() {
		try {
			service.kill(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty serialized job should throw a job exception");
		}
	}
	
	public void testKillInvalidJobString() {
		try {
			service.kill(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid serialized job should throw a job exception");
		}
	}
	
	public void testKill() throws Exception {
		service.kill(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
		
		Job vJob = JobDao.getById(job.getId());
		assertTrue(vJob.getStatus().equals(JobStatusType.STOPPED));
		
		vJob.setStatus(JobStatusType.RUNNING);
		JobDao.persist(vJob);
	}
	
	/*********************************************************
	 * 
	 * 		Test Delete
	 *  
	 *********************************************************/
	public void testDeleteNullSessionToken() {
		try {
			service.delete(null, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testDeleteEmptySessionToken() {
		try {
			service.delete("", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testDeleteInvalidSessionToken() {
		try {
			service.delete("-1", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testDeleteNullJobString() {
		try {
			service.delete(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null serialized job should throw a job exception");
		}
	}
	
	public void testDeleteEmptyJobString() {
		try {
			service.delete(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty serialized job should throw a job exception");
		}
	}
	
	public void testDeleteInvalidJobString() {
		try {
			service.delete(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid serialized job should throw a job exception");
		}
	}
	
	public void testDelete() throws Exception {
		service.delete(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
		
		Job vJob = JobDao.getById(job.getId());
		assertTrue(vJob.isDeleted());
		
		vJob.setDeleted(false);
		JobDao.persist(vJob);
	}

	/*********************************************************
	 * 
	 * 		Test Hide
	 *  
	 *********************************************************/
	
	public void testHideNullSessionToken() {
		try {
			service.hide(null, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testHideEmptySessionToken() {
		try {
			service.hide("", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testHideInvalidSessionToken() {
		try {
			service.hide("-1", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testHideNullJobString() {
		try {
			service.hide(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null serialized job should throw a job exception");
		}
	}
	
	public void testHideEmptyJobString() {
		try {
			service.hide(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty serialized job should throw a job exception");
		}
	}
	
	public void testHideInvalidJobString() {
		try {
			service.hide(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid serialized job should throw a job exception");
		}
	}
	
	public void testHide() throws Exception {
		service.hide(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
		
		Job vJob = JobDao.getById(job.getId());
		assertTrue(vJob.isHidden());
		
		vJob.setHidden(false);
		JobDao.persist(vJob);
	}

	/*********************************************************
	 * 
	 * 		Test Unhide
	 *  
	 *********************************************************/
	
	public void testUnhideNullSessionToken() {
		try {
			service.unhide(null, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testUnhideEmptySessionToken() {
		try {
			service.unhide("", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testUnhideInvalidSessionToken() {
		try {
			service.unhide("-1", ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testUnhideNullJobString() {
		try {
			service.unhide(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null serialized job should throw a job exception");
		}
	}
	
	public void testUnhideEmptyJobString() {
		try {
			service.unhide(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty serialized job should throw a job exception");
		}
	}
	
	public void testUnhideInvalidJobString() {
		try {
			service.unhide(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid serialized job should throw a job exception");
		}
	}
	
	public void testUnhide() throws Exception {
		
		job.setHidden(true);
		JobDao.persist(job);
		
		service.unhide(TEST_SESSIONTOKEN, ServiceUtil.xstream.toXML(Arrays.asList(job.getId())));
		
		Job vJob = JobDao.getById(job.getId());
		assertFalse(vJob.isHidden());
	}

	/*********************************************************
	 * 
	 * 		Test Unhide All
	 *  
	 *********************************************************/
	
	public void testUnhideAllNullSessionToken() {
		try {
			service.unhideAll(null);
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testUnhideAllEmptySessionToken() {
		try {
			service.unhideAll("");
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testUnhideAllInvalidSessionToken() {
		try {
			service.unhideAll("-1");
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testUnhideAll() throws Exception {
		job.setHidden(true);
		JobDao.persist(job);
		
		service.unhideAll(TEST_SESSIONTOKEN);
		
		Job vJob = JobDao.getById(job.getId());
		assertFalse(vJob.isHidden());
	}

	/*********************************************************
	 * 
	 * 		Test Resubmit Job
	 *  
	 *********************************************************/
	
	public void testResubmitNullSessionToken() {
		try {
			service.resubmitJob(null, job.getId().toString());
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testResubmitEmptySessionToken() {
		try {
			service.resubmitJob("", job.getId().toString());
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testResubmitInvalidSessionToken() {
		try {
			service.resubmitJob("-1", job.getId().toString());
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testResubmitNullJobString() {
		try {
			service.resubmitJob(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null serialized job should throw a job exception");
		}
	}
	
	public void testResubmitEmptyJobString() {
		try {
			service.resubmitJob(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty serialized job should throw a job exception");
		}
	}
	
	public void testResubmitInvalidJobString() {
		try {
			service.resubmitJob(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid serialized job should throw a job exception");
		}
	}
	
	public void testResubmitJob() {
		String sResult = service.resubmitJob(TEST_SESSIONTOKEN, job.getId().toString());
		
		assertNotNull(sResult);
	}

	/*********************************************************
	 * 
	 * 		Test Predict Start Time
	 *  
	 *********************************************************/
	
	public void testPredictStartTimeNullSessionToken() {
		try {
			service.predictStartTime(null, ServiceUtil.xstream.toXML(job.toBean()));
			fail("Null token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Null token should throw a session exception");
		}
	}
	
	public void testPredictStartTimeEmptySessionToken() {
		try {
			service.predictStartTime("", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Empty token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Empty token should throw a session exception");
		}
	}
	
	public void testPredictStartTimeInvalidSessionToken() {
		try {
			service.predictStartTime("-1", ServiceUtil.xstream.toXML(job.toBean()));
			fail("Invalid token should throw a session exception");
		} catch (SessionException e) {
		} catch (Exception e) {
			fail("Invalid token should throw a session exception");
		}
	}
	
	public void testPredictStartTimeNullJobString() {
		try {
			service.predictStartTime(TEST_SESSIONTOKEN, null);
			fail("Null serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Null serialized job should throw a job exception");
		}
	}
	
	public void testPredictStartTimeEmptyJobString() {
		try {
			service.predictStartTime(TEST_SESSIONTOKEN, "");
			fail("Empty serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Empty serialized job should throw a job exception");
		}
	}
	
	public void testPredictStartTimeInvalidJobString() {
		try {
			service.predictStartTime(TEST_SESSIONTOKEN, "-1");
			fail("Invalid serialized job should throw a job exception");
		} catch (JobException e) {
		} catch (Exception e) {
			fail("Invalid serialized job should throw a job exception");
		}
	}
	
	public void testPredictStartTime() throws RemoteException, ProviderException, ServiceException, ParameterException {
		String sResult = service.predictStartTime(TEST_SESSIONTOKEN, job.getId().toString());
		
		assertNotNull(sResult);
		
		QbetsPrediction p = (QbetsPrediction)ServiceUtil.xstream.fromXML(sResult);
		
		assertTrue(p.getConfidenceInterval() > 0);
	}

}
