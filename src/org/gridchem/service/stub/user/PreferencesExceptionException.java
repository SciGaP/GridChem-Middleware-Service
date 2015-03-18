/**
 * PreferencesExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.user;

public class PreferencesExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.user.UserServiceStub.PreferencesExceptionE faultMessage;

	public PreferencesExceptionException() {
		super("PreferencesExceptionException");
	}

	public PreferencesExceptionException(java.lang.String s) {
		super(s);
	}

	public PreferencesExceptionException(java.lang.String s,
			java.lang.Throwable ex) {
		super(s, ex);
	}

	public PreferencesExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.user.UserServiceStub.PreferencesExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.user.UserServiceStub.PreferencesExceptionE getFaultMessage() {
		return faultMessage;
	}
}
