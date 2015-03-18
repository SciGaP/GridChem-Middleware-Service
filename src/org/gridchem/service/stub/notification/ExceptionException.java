/**
 * ExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.notification;

public class ExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.notification.NotificationServiceStub.ExceptionE faultMessage;

	public ExceptionException() {
		super("ExceptionException");
	}

	public ExceptionException(java.lang.String s) {
		super(s);
	}

	public ExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public ExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.notification.NotificationServiceStub.ExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.notification.NotificationServiceStub.ExceptionE getFaultMessage() {
		return faultMessage;
	}
}
