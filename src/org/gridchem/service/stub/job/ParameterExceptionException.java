/**
 * ParameterExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.job;

public class ParameterExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.job.JobServiceStub.ParameterExceptionE faultMessage;

	public ParameterExceptionException() {
		super("ParameterExceptionException");
	}

	public ParameterExceptionException(java.lang.String s) {
		super(s);
	}

	public ParameterExceptionException(java.lang.String s,
			java.lang.Throwable ex) {
		super(s, ex);
	}

	public ParameterExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.job.JobServiceStub.ParameterExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.job.JobServiceStub.ParameterExceptionE getFaultMessage() {
		return faultMessage;
	}
}
