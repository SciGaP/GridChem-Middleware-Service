/**
 * SoftwareExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.software;

public class SoftwareExceptionException extends java.lang.Exception {

	private org.gridchem.service.stub.software.SoftwareServiceStub.SoftwareExceptionE faultMessage;

	public SoftwareExceptionException() {
		super("SoftwareExceptionException");
	}

	public SoftwareExceptionException(java.lang.String s) {
		super(s);
	}

	public SoftwareExceptionException(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public SoftwareExceptionException(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(
			org.gridchem.service.stub.software.SoftwareServiceStub.SoftwareExceptionE msg) {
		faultMessage = msg;
	}

	public org.gridchem.service.stub.software.SoftwareServiceStub.SoftwareExceptionE getFaultMessage() {
		return faultMessage;
	}
}
