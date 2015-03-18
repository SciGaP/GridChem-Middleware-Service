/**
 * ProviderExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.job;

public class ProviderExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.job.JobServiceStub.ProviderExceptionE faultMessage;

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
			org.gridchem.service.stub.job.JobServiceStub.ProviderExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.job.JobServiceStub.ProviderExceptionE getFaultMessage() {
		return faultMessage;
	}
}
