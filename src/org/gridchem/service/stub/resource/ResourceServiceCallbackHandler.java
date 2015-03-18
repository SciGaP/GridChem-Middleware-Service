/**
 * ResourceServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.resource;

/**
 * ResourceServiceCallbackHandler Callback class, Users can extend this class
 * and implement their own receiveResult and receiveError methods.
 */
public abstract class ResourceServiceCallbackHandler {

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
	public ResourceServiceCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public ResourceServiceCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getComputeResourcesForSite
	 * method override this method for handling normal response from
	 * getComputeResourcesForSite operation
	 */
	public void receiveResultgetComputeResourcesForSite(
			org.gridchem.service.stub.resource.ResourceServiceStub.GetComputeResourcesForSiteResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getComputeResourcesForSite operation
	 */
	public void receiveErrorgetComputeResourcesForSite(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getStorageResourcesForSite
	 * method override this method for handling normal response from
	 * getStorageResourcesForSite operation
	 */
	public void receiveResultgetStorageResourcesForSite(
			org.gridchem.service.stub.resource.ResourceServiceStub.GetStorageResourcesForSiteResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getStorageResourcesForSite operation
	 */
	public void receiveErrorgetStorageResourcesForSite(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getStorageResources method
	 * override this method for handling normal response from
	 * getStorageResources operation
	 */
	public void receiveResultgetStorageResources(
			org.gridchem.service.stub.resource.ResourceServiceStub.GetStorageResourcesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getStorageResources operation
	 */
	public void receiveErrorgetStorageResources(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getResources method override
	 * this method for handling normal response from getResources operation
	 */
	public void receiveResultgetResources(
			org.gridchem.service.stub.resource.ResourceServiceStub.GetResourcesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getResources operation
	 */
	public void receiveErrorgetResources(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getComputeResources method
	 * override this method for handling normal response from
	 * getComputeResources operation
	 */
	public void receiveResultgetComputeResources(
			org.gridchem.service.stub.resource.ResourceServiceStub.GetComputeResourcesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getComputeResources operation
	 */
	public void receiveErrorgetComputeResources(java.lang.Exception e) {
	}

}
