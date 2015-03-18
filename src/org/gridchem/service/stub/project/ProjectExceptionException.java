
/**
 * ProjectExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.gridchem.service.stub.project;

public class ProjectExceptionException extends java.lang.Exception{
    
    private org.gridchem.service.stub.project.ProjectServiceStub.ProjectExceptionE faultMessage;

    
        public ProjectExceptionException() {
            super("ProjectExceptionException");
        }

        public ProjectExceptionException(java.lang.String s) {
           super(s);
        }

        public ProjectExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ProjectExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.gridchem.service.stub.project.ProjectServiceStub.ProjectExceptionE msg){
       faultMessage = msg;
    }
    
    public org.gridchem.service.stub.project.ProjectServiceStub.ProjectExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    