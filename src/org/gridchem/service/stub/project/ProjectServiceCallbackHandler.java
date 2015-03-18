
/**
 * ProjectServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.gridchem.service.stub.project;

    /**
     *  ProjectServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ProjectServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ProjectServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ProjectServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getCollaborators method
            * override this method for handling normal response from getCollaborators operation
            */
           public void receiveResultgetCollaborators(
                    org.gridchem.service.stub.project.ProjectServiceStub.GetCollaboratorsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCollaborators operation
           */
            public void receiveErrorgetCollaborators(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCurrentProject method
            * override this method for handling normal response from getCurrentProject operation
            */
           public void receiveResultgetCurrentProject(
                    org.gridchem.service.stub.project.ProjectServiceStub.GetCurrentProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCurrentProject operation
           */
            public void receiveErrorgetCurrentProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getProjectCollaborators method
            * override this method for handling normal response from getProjectCollaborators operation
            */
           public void receiveResultgetProjectCollaborators(
                    org.gridchem.service.stub.project.ProjectServiceStub.GetProjectCollaboratorsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getProjectCollaborators operation
           */
            public void receiveErrorgetProjectCollaborators(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getProjects method
            * override this method for handling normal response from getProjects operation
            */
           public void receiveResultgetProjects(
                    org.gridchem.service.stub.project.ProjectServiceStub.GetProjectsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getProjects operation
           */
            public void receiveErrorgetProjects(java.lang.Exception e) {
            }
                


    }
    