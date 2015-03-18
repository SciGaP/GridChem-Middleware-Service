/**
 * NotificationExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.notification;

public class NotificationExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.notification.NotificationServiceStub.NotificationExceptionE faultMessage;

	public NotificationExceptionException() {
		super("NotificationExceptionException");
	}

	public NotificationExceptionException(java.lang.String s) {
		super(s);
	}

	public NotificationExceptionException(java.lang.String s,
			java.lang.Throwable ex) {
		super(s, ex);
	}

	public NotificationExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.notification.NotificationServiceStub.NotificationExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.notification.NotificationServiceStub.NotificationExceptionE getFaultMessage() {
		return faultMessage;
	}
}
