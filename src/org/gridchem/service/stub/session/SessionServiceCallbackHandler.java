/**
 * SessionServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.session;

/**
 * SessionServiceCallbackHandler Callback class, Users can extend this class and
 * implement their own receiveResult and receiveError methods.
 */
public abstract class SessionServiceCallbackHandler {

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
	public SessionServiceCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public SessionServiceCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for createSession method override
	 * this method for handling normal response from createSession operation
	 */
	public void receiveResultcreateSession(
			org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createSession operation
	 */
	public void receiveErrorcreateSession(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for renewSession method override
	 * this method for handling normal response from renewSession operation
	 */
	public void receiveResultrenewSession(
			org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from renewSession operation
	 */
	public void receiveErrorrenewSession(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getRemainingTime method
	 * override this method for handling normal response from getRemainingTime
	 * operation
	 */
	public void receiveResultgetRemainingTime(
			org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getRemainingTime operation
	 */
	public void receiveErrorgetRemainingTime(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getAuthenticationTypes method
	 * override this method for handling normal response from
	 * getAuthenticationTypes operation
	 */
	public void receiveResultgetAuthenticationTypes(
			org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getAuthenticationTypes operation
	 */
	public void receiveErrorgetAuthenticationTypes(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

}
