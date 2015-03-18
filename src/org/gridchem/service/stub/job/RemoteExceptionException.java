/**
 * RemoteExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.job;

public class RemoteExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.job.JobServiceStub.RemoteExceptionE faultMessage;

	public RemoteExceptionException() {
		super("RemoteExceptionException");
	}

	public RemoteExceptionException(java.lang.String s) {
		super(s);
	}

	public RemoteExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public RemoteExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.job.JobServiceStub.RemoteExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.job.JobServiceStub.RemoteExceptionE getFaultMessage() {
		return faultMessage;
	}
}
