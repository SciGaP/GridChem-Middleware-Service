
/**
 * FileServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.gridchem.service.stub.file;

    /**
     *  FileServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class FileServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public FileServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public FileServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for listOutputFilesForJob method
            * override this method for handling normal response from listOutputFilesForJob operation
            */
           public void receiveResultlistOutputFilesForJob(
                    org.gridchem.service.stub.file.FileServiceStub.ListOutputFilesForJobResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listOutputFilesForJob operation
           */
            public void receiveErrorlistOutputFilesForJob(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for putCachedFile method
            * override this method for handling normal response from putCachedFile operation
            */
           public void receiveResultputCachedFile(
                    org.gridchem.service.stub.file.FileServiceStub.PutCachedFileResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from putCachedFile operation
           */
            public void receiveErrorputCachedFile(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for list method
            * override this method for handling normal response from list operation
            */
           public void receiveResultlist(
                    org.gridchem.service.stub.file.FileServiceStub.ListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from list operation
           */
            public void receiveErrorlist(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getRemoteFile method
            * override this method for handling normal response from getRemoteFile operation
            */
           public void receiveResultgetRemoteFile(
                    org.gridchem.service.stub.file.FileServiceStub.GetRemoteFileResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRemoteFile operation
           */
            public void receiveErrorgetRemoteFile(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for listCachedInputFiles method
            * override this method for handling normal response from listCachedInputFiles operation
            */
           public void receiveResultlistCachedInputFiles(
                    org.gridchem.service.stub.file.FileServiceStub.ListCachedInputFilesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listCachedInputFiles operation
           */
            public void receiveErrorlistCachedInputFiles(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for listCachedInputFilesForJob method
            * override this method for handling normal response from listCachedInputFilesForJob operation
            */
           public void receiveResultlistCachedInputFilesForJob(
                    org.gridchem.service.stub.file.FileServiceStub.ListCachedInputFilesForJobResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listCachedInputFilesForJob operation
           */
            public void receiveErrorlistCachedInputFilesForJob(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCachedInputFile method
            * override this method for handling normal response from getCachedInputFile operation
            */
           public void receiveResultgetCachedInputFile(
                    org.gridchem.service.stub.file.FileServiceStub.GetCachedInputFileResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCachedInputFile operation
           */
            public void receiveErrorgetCachedInputFile(java.lang.Exception e) {
            }
                


    }
    