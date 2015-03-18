/**
 * UserExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.user;

public class UserExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.user.UserServiceStub.UserExceptionE faultMessage;

	public UserExceptionException() {
		super("UserExceptionException");
	}

	public UserExceptionException(java.lang.String s) {
		super(s);
	}

	public UserExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public UserExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.user.UserServiceStub.UserExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.user.UserServiceStub.UserExceptionE getFaultMessage() {
		return faultMessage;
	}
}
