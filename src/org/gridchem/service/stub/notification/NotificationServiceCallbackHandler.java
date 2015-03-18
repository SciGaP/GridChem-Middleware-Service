/**
 * NotificationServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.notification;

/**
 * NotificationServiceCallbackHandler Callback class, Users can extend this
 * class and implement their own receiveResult and receiveError methods.
 */
public abstract class NotificationServiceCallbackHandler {

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
	public NotificationServiceCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public NotificationServiceCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for registerDefault method override
	 * this method for handling normal response from registerDefault operation
	 */
	public void receiveResultregisterDefault(
			org.gridchem.service.stub.notification.NotificationServiceStub.RegisterDefaultResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from registerDefault operation
	 */
	public void receiveErrorregisterDefault(java.lang.Exception e) {
	}

	// No methods generated for meps other than in-out

	// No methods generated for meps other than in-out

	// No methods generated for meps other than in-out

	/**
	 * auto generated Axis2 call back method for getNotifications method
	 * override this method for handling normal response from getNotifications
	 * operation
	 */
	public void receiveResultgetNotifications(
			org.gridchem.service.stub.notification.NotificationServiceStub.GetNotificationsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getNotifications operation
	 */
	public void receiveErrorgetNotifications(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for register method override this
	 * method for handling normal response from register operation
	 */
	public void receiveResultregister(
			org.gridchem.service.stub.notification.NotificationServiceStub.RegisterResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from register operation
	 */
	public void receiveErrorregister(java.lang.Exception e) {
	}

}
