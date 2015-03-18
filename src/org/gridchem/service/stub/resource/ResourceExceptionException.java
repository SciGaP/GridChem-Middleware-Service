/**
 * ResourceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.resource;

public class ResourceExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.resource.ResourceServiceStub.ResourceExceptionE faultMessage;

	public ResourceExceptionException() {
		super("ResourceExceptionException");
	}

	public ResourceExceptionException(java.lang.String s) {
		super(s);
	}

	public ResourceExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public ResourceExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.resource.ResourceServiceStub.ResourceExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.resource.ResourceServiceStub.ResourceExceptionE getFaultMessage() {
		return faultMessage;
	}
}
