/**
 * SessionExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.user;

public class SessionExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.user.UserServiceStub.SessionExceptionE faultMessage;

	public SessionExceptionException() {
		super("SessionExceptionException");
	}

	public SessionExceptionException(java.lang.String s) {
		super(s);
	}

	public SessionExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public SessionExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.user.UserServiceStub.SessionExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.user.UserServiceStub.SessionExceptionE getFaultMessage() {
		return faultMessage;
	}
}
