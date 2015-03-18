/**
 * ProviderExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.resource;

public class ProviderExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.resource.ResourceServiceStub.ProviderExceptionE faultMessage;

	public ProviderExceptionException() {
		super("ProviderExceptionException");
	}

	public ProviderExceptionException(java.lang.String s) {
		super(s);
	}

	public ProviderExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public ProviderExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.resource.ResourceServiceStub.ProviderExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.resource.ResourceServiceStub.ProviderExceptionE getFaultMessage() {
		return faultMessage;
	}
}
