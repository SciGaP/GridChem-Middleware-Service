/**
 * InfrastructureExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.user;

public class InfrastructureExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.user.UserServiceStub.InfrastructureExceptionE faultMessage;

	public InfrastructureExceptionException() {
		super("InfrastructureExceptionException");
	}

	public InfrastructureExceptionException(java.lang.String s) {
		super(s);
	}

	public InfrastructureExceptionException(java.lang.String s,
			java.lang.Throwable ex) {
		super(s, ex);
	}

	public InfrastructureExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.user.UserServiceStub.InfrastructureExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.user.UserServiceStub.InfrastructureExceptionE getFaultMessage() {
		return faultMessage;
	}
}
