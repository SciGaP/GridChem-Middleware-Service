/**
 * ServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.job;

public class ServiceExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.job.JobServiceStub.ServiceException faultMessage;

	public ServiceExceptionException() {
		super("ServiceExceptionException");
	}

	public ServiceExceptionException(java.lang.String s) {
		super(s);
	}

	public ServiceExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public ServiceExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.job.JobServiceStub.ServiceException msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.job.JobServiceStub.ServiceException getFaultMessage() {
		return faultMessage;
	}
}
