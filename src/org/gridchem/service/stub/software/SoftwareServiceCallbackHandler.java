/**
 * SoftwareServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.software;

/**
 * SoftwareServiceCallbackHandler Callback class, Users can extend this class
 * and implement their own receiveResult and receiveError methods.
 */
public abstract class SoftwareServiceCallbackHandler {

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
	public SoftwareServiceCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public SoftwareServiceCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for
	 * getSoftwareInstallationsForSoftware method override this method for
	 * handling normal response from getSoftwareInstallationsForSoftware
	 * operation
	 */
	public void receiveResultgetSoftwareInstallationsForSoftware(
			org.gridchem.service.stub.software.SoftwareServiceStub.GetSoftwareInstallationsForSoftwareResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getSoftwareInstallationsForSoftware operation
	 */
	public void receiveErrorgetSoftwareInstallationsForSoftware(
			java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getSoftwareInstallations method
	 * override this method for handling normal response from
	 * getSoftwareInstallations operation
	 */
	public void receiveResultgetSoftwareInstallations(
			org.gridchem.service.stub.software.SoftwareServiceStub.GetSoftwareInstallationsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getSoftwareInstallations operation
	 */
	public void receiveErrorgetSoftwareInstallations(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for
	 * getSoftwareInstallationsForResource method override this method for
	 * handling normal response from getSoftwareInstallationsForResource
	 * operation
	 */
	public void receiveResultgetSoftwareInstallationsForResource(
			org.gridchem.service.stub.software.SoftwareServiceStub.GetSoftwareInstallationsForResourceResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getSoftwareInstallationsForResource operation
	 */
	public void receiveErrorgetSoftwareInstallationsForResource(
			java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getAllSoftware method override
	 * this method for handling normal response from getAllSoftware operation
	 */
	public void receiveResultgetAllSoftware(
			org.gridchem.service.stub.software.SoftwareServiceStub.GetAllSoftwareResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getAllSoftware operation
	 */
	public void receiveErrorgetAllSoftware(java.lang.Exception e) {
	}

}
