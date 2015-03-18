/**
 * SiteExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.session;

public class SiteExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.session.ResourceServiceStub.SiteExceptionE faultMessage;

	public SiteExceptionException() {
		super("SiteExceptionException");
	}

	public SiteExceptionException(java.lang.String s) {
		super(s);
	}

	public SiteExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public SiteExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.session.ResourceServiceStub.SiteExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.session.ResourceServiceStub.SiteExceptionE getFaultMessage() {
		return faultMessage;
	}
}
