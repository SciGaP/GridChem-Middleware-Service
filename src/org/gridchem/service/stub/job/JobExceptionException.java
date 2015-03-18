/**
 * JobExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.job;

public class JobExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.job.JobServiceStub.JobExceptionE faultMessage;

	public JobExceptionException() {
		super("JobExceptionException");
	}

	public JobExceptionException(java.lang.String s) {
		super(s);
	}

	public JobExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public JobExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.job.JobServiceStub.JobExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.job.JobServiceStub.JobExceptionE getFaultMessage() {
		return faultMessage;
	}
}
