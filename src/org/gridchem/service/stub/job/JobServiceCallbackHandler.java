/**
 * JobServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.job;

/**
 * JobServiceCallbackHandler Callback class, Users can extend this class and
 * implement their own receiveResult and receiveError methods.
 */
public abstract class JobServiceCallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public JobServiceCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public JobServiceCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for submit method override this
	 * method for handling normal response from submit operation
	 */
	public void receiveResultsubmit(
			org.gridchem.service.stub.job.JobServiceStub.SubmitResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from submit operation
	 */
	public void receiveErrorsubmit(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for uploadInputFile method override
	 * this method for handling normal response from uploadInputFile operation
	 */
	public void receiveResultuploadInputFile(
			org.gridchem.service.stub.job.JobServiceStub.UploadInputFileResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from uploadInputFile operation
	 */
	public void receiveErroruploadInputFile(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for downloadInputFile method
	 * override this method for handling normal response from downloadInputFile
	 * operation
	 */
	public void receiveResultdownloadInputFile(
			org.gridchem.service.stub.job.JobServiceStub.DownloadInputFileResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from downloadInputFile operation
	 */
	public void receiveErrordownloadInputFile(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for predictStartTime method
	 * override this method for handling normal response from predictStartTime
	 * operation
	 */
	public void receiveResultpredictStartTime(
			org.gridchem.service.stub.job.JobServiceStub.PredictStartTimeResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from predictStartTime operation
	 */
	public void receiveErrorpredictStartTime(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for listAll method override this
	 * method for handling normal response from listAll operation
	 */
	public void receiveResultlistAll(
			org.gridchem.service.stub.job.JobServiceStub.ListAllResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from listAll operation
	 */
	public void receiveErrorlistAll(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for list method override this
	 * method for handling normal response from list operation
	 */
	public void receiveResultlist(
			org.gridchem.service.stub.job.JobServiceStub.ListResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from list operation
	 */
	public void receiveErrorlist(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for createJob method override this
	 * method for handling normal response from createJob operation
	 */
	public void receiveResultcreateJob(
			org.gridchem.service.stub.job.JobServiceStub.CreateJobResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createJob operation
	 */
	public void receiveErrorcreateJob(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for resubmitJob method override
	 * this method for handling normal response from resubmitJob operation
	 */
	public void receiveResultresubmitJob(
			org.gridchem.service.stub.job.JobServiceStub.ResubmitJobResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from resubmitJob operation
	 */
	public void receiveErrorresubmitJob(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for search method override this
	 * method for handling normal response from search operation
	 */
	public void receiveResultsearch(
			org.gridchem.service.stub.job.JobServiceStub.SearchResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from search operation
	 */
	public void receiveErrorsearch(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

}
