/**
 * PermissionExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.user;

public class PermissionExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.user.UserServiceStub.PermissionExceptionE faultMessage;

	public PermissionExceptionException() {
		super("PermissionExceptionException");
	}

	public PermissionExceptionException(java.lang.String s) {
		super(s);
	}

	public PermissionExceptionException(java.lang.String s,
			java.lang.Throwable ex) {
		super(s, ex);
	}

	public PermissionExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.user.UserServiceStub.PermissionExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.user.UserServiceStub.PermissionExceptionE getFaultMessage() {
		return faultMessage;
	}
}
