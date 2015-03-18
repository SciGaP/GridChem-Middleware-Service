/**
 * SessionServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
package org.gridchem.service.stub.session;

/*
 *  SessionServiceStub java implementation
 */

public class SessionServiceStub extends org.apache.axis2.client.Stub {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private static int counter = 0;

	private static synchronized java.lang.String getUniqueSuffix() {
		// reset the counter if it is greater than 99999
		if (counter > 99999) {
			counter = 0;
		}
		counter = counter + 1;
		return java.lang.Long.toString(System.currentTimeMillis()) + "_"
				+ counter;
	}

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService(
				"SessionService" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[6];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "createSession"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

		__operation = new org.apache.axis2.description.RobustOutOnlyAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "destroySession"));
		_service.addOperation(__operation);

		_operations[1] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "renewSession"));
		_service.addOperation(__operation);

		_operations[2] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "getRemainingTime"));
		_service.addOperation(__operation);

		_operations[3] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "getAuthenticationTypes"));
		_service.addOperation(__operation);

		_operations[4] = __operation;

		__operation = new org.apache.axis2.description.RobustOutOnlyAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "setSessionProject"));
		_service.addOperation(__operation);

		_operations[5] = __operation;

	}

	// populates the faults
	private void populateFaults() {

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ParameterException"),
				"org.gridchem.service.stub.ParameterExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ParameterException"),
				"org.gridchem.service.stub.ParameterExceptionException");
		faultMessageMap
				.put(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"ParameterException"),
						"org.gridchem.service.stub.SessionServiceStub$ParameterExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultMessageMap
				.put(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"PermissionException"),
						"org.gridchem.service.stub.SessionServiceStub$PermissionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultMessageMap
				.put(
						new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"SessionException"),
						"org.gridchem.service.stub.SessionServiceStub$SessionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ProviderException"),
				"org.gridchem.service.stub.ProviderExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ProviderException"),
				"org.gridchem.service.stub.ProviderExceptionException");
		faultMessageMap
				.put(
						new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"ProviderException"),
						"org.gridchem.service.stub.SessionServiceStub$ProviderExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultMessageMap
				.put(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"PermissionException"),
						"org.gridchem.service.stub.SessionServiceStub$PermissionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultMessageMap
				.put(
						new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"SessionException"),
						"org.gridchem.service.stub.SessionServiceStub$SessionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultMessageMap
				.put(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"PermissionException"),
						"org.gridchem.service.stub.SessionServiceStub$PermissionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultMessageMap
				.put(
						new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"SessionException"),
						"org.gridchem.service.stub.SessionServiceStub$SessionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultMessageMap
				.put(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"PermissionException"),
						"org.gridchem.service.stub.SessionServiceStub$PermissionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultMessageMap
				.put(
						new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"SessionException"),
						"org.gridchem.service.stub.SessionServiceStub$SessionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException"),
				"org.gridchem.service.stub.PermissionExceptionException");
		faultMessageMap
				.put(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"PermissionException"),
						"org.gridchem.service.stub.SessionServiceStub$PermissionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException"),
				"org.gridchem.service.stub.SessionExceptionException");
		faultMessageMap
				.put(
						new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"SessionException"),
						"org.gridchem.service.stub.SessionServiceStub$SessionExceptionE");

		faultExceptionNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ProviderException"),
				"org.gridchem.service.stub.ProviderExceptionException");
		faultExceptionClassNameMap.put(new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ProviderException"),
				"org.gridchem.service.stub.ProviderExceptionException");
		faultMessageMap
				.put(
						new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"ProviderException"),
						"org.gridchem.service.stub.SessionServiceStub$ProviderExceptionE");

	}

	/**
	 *Constructor that takes in a configContext
	 */

	public SessionServiceStub(
			org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public SessionServiceStub(
			org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener)
			throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(
				configurationContext, _service);

		_serviceClient.getOptions().setTo(
				new org.apache.axis2.addressing.EndpointReference(
						targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

		// Set the soap version
		_serviceClient
				.getOptions()
				.setSoapVersionURI(
						org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);

	}

	/**
	 * Default Constructor
	 */
	public SessionServiceStub(
			org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(
				configurationContext,
				"http://localhost:8080/axis2/services/SessionService.SessionServiceHttpSoap12Endpoint/");

	}

	/**
	 * Default Constructor
	 */
	public SessionServiceStub() throws org.apache.axis2.AxisFault {

		this(
				"http://localhost:8080/axis2/services/SessionService.SessionServiceHttpSoap12Endpoint/");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public SessionServiceStub(java.lang.String targetEndpoint)
			throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see org.gridchem.service.stub.SessionService#createSession
	 * @param createSession1
	 * 
	 * @throws org.gridchem.service.stub.session.ParameterExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.PermissionExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.SessionExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.ProviderExceptionException
	 *             :
	 */

	public org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse createSession(

			org.gridchem.service.stub.session.SessionServiceStub.CreateSession createSession1)

	throws java.rmi.RemoteException

	, org.gridchem.service.stub.session.ParameterExceptionException,
			org.gridchem.service.stub.session.PermissionExceptionException,
			org.gridchem.service.stub.session.SessionExceptionException,
			org.gridchem.service.stub.session.ProviderExceptionException {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction("urn:createSession");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(
					getFactory(_operationClient.getOptions()
							.getSoapVersionURI()),
					createSession1,
					optimizeContent(new javax.xml.namespace.QName(
							"http://impl.service.gridchem.org", "createSession")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
					.getEnvelope();

			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement(),
					org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof org.gridchem.service.stub.session.ParameterExceptionException) {
							throw (org.gridchem.service.stub.session.ParameterExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
							throw (org.gridchem.service.stub.session.PermissionExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
							throw (org.gridchem.service.stub.session.SessionExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.ProviderExceptionException) {
							throw (org.gridchem.service.stub.session.ProviderExceptionException) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see org.gridchem.service.stub.SessionService#startcreateSession
	 * @param createSession1
	 */
	public void startcreateSession(

			org.gridchem.service.stub.session.SessionServiceStub.CreateSession createSession1,

			final org.gridchem.service.stub.session.SessionServiceCallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[0].getName());
		_operationClient.getOptions().setAction("urn:createSession");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(
				_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
				"&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions()
				.getSoapVersionURI()), createSession1,
				optimizeContent(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "createSession")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient
				.setCallback(new org.apache.axis2.client.async.AxisCallback() {
					public void onMessage(
							org.apache.axis2.context.MessageContext resultContext) {
						try {
							org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext
									.getEnvelope();

							java.lang.Object object = fromOM(
									resultEnv.getBody().getFirstElement(),
									org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse.class,
									getEnvelopeNamespaces(resultEnv));
							callback
									.receiveResultcreateSession((org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse) object);

						} catch (org.apache.axis2.AxisFault e) {
							callback.receiveErrorcreateSession(e);
						}
					}

					public void onError(java.lang.Exception error) {
						if (error instanceof org.apache.axis2.AxisFault) {
							org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
							org.apache.axiom.om.OMElement faultElt = f
									.getDetail();
							if (faultElt != null) {
								if (faultExceptionNameMap.containsKey(faultElt
										.getQName())) {
									// make the fault by reflection
									try {
										java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
												.get(faultElt.getQName());
										java.lang.Class exceptionClass = java.lang.Class
												.forName(exceptionClassName);
										java.lang.Exception ex = (java.lang.Exception) exceptionClass
												.newInstance();
										// message class
										java.lang.String messageClassName = (java.lang.String) faultMessageMap
												.get(faultElt.getQName());
										java.lang.Class messageClass = java.lang.Class
												.forName(messageClassName);
										java.lang.Object messageObject = fromOM(
												faultElt, messageClass, null);
										java.lang.reflect.Method m = exceptionClass
												.getMethod(
														"setFaultMessage",
														new java.lang.Class[] { messageClass });
										m
												.invoke(
														ex,
														new java.lang.Object[] { messageObject });

										if (ex instanceof org.gridchem.service.stub.session.ParameterExceptionException) {
											callback
													.receiveErrorcreateSession((org.gridchem.service.stub.session.ParameterExceptionException) ex);
											return;
										}

										if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
											callback
													.receiveErrorcreateSession((org.gridchem.service.stub.session.PermissionExceptionException) ex);
											return;
										}

										if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
											callback
													.receiveErrorcreateSession((org.gridchem.service.stub.session.SessionExceptionException) ex);
											return;
										}

										if (ex instanceof org.gridchem.service.stub.session.ProviderExceptionException) {
											callback
													.receiveErrorcreateSession((org.gridchem.service.stub.session.ProviderExceptionException) ex);
											return;
										}

										callback
												.receiveErrorcreateSession(new java.rmi.RemoteException(
														ex.getMessage(), ex));
									} catch (java.lang.ClassCastException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorcreateSession(f);
									} catch (java.lang.ClassNotFoundException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorcreateSession(f);
									} catch (java.lang.NoSuchMethodException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorcreateSession(f);
									} catch (java.lang.reflect.InvocationTargetException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorcreateSession(f);
									} catch (java.lang.IllegalAccessException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorcreateSession(f);
									} catch (java.lang.InstantiationException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorcreateSession(f);
									} catch (org.apache.axis2.AxisFault e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorcreateSession(f);
									}
								} else {
									callback.receiveErrorcreateSession(f);
								}
							} else {
								callback.receiveErrorcreateSession(f);
							}
						} else {
							callback.receiveErrorcreateSession(error);
						}
					}

					public void onFault(
							org.apache.axis2.context.MessageContext faultContext) {
						org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
								.getInboundFaultFromMessageContext(faultContext);
						onError(fault);
					}

					public void onComplete() {
						try {
							_messageContext.getTransportOut().getSender()
									.cleanup(_messageContext);
						} catch (org.apache.axis2.AxisFault axisFault) {
							callback.receiveErrorcreateSession(axisFault);
						}
					}
				});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[0].getMessageReceiver() == null
				&& _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[0].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @throws org.gridchem.service.stub.session.PermissionExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.SessionExceptionException
	 *             :
	 */
	public void destroySession(
			org.gridchem.service.stub.session.SessionServiceStub.DestroySession destroySession3

	) throws java.rmi.RemoteException

	, org.gridchem.service.stub.session.PermissionExceptionException,
			org.gridchem.service.stub.session.SessionExceptionException {
		org.apache.axis2.context.MessageContext _messageContext = null;

		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[1].getName());
			_operationClient.getOptions().setAction("urn:destroySession");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			org.apache.axiom.soap.SOAPEnvelope env = null;
			_messageContext = new org.apache.axis2.context.MessageContext();

			// Style is Doc.

			env = toEnvelope(getFactory(_operationClient.getOptions()
					.getSoapVersionURI()), destroySession3,
					optimizeContent(new javax.xml.namespace.QName(
							"http://impl.service.gridchem.org",
							"destroySession")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// create message context with that soap envelope

			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			_operationClient.execute(true);

		} catch (org.apache.axis2.AxisFault f) {
			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
							throw (org.gridchem.service.stub.session.PermissionExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
							throw (org.gridchem.service.stub.session.SessionExceptionException) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}

		return;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see org.gridchem.service.stub.SessionService#renewSession
	 * @param renewSession4
	 * 
	 * @throws org.gridchem.service.stub.session.PermissionExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.SessionExceptionException
	 *             :
	 */

	public org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse renewSession(

			org.gridchem.service.stub.session.SessionServiceStub.RenewSession renewSession4)

	throws java.rmi.RemoteException

	, org.gridchem.service.stub.session.PermissionExceptionException,
			org.gridchem.service.stub.session.SessionExceptionException {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[2].getName());
			_operationClient.getOptions().setAction("urn:renewSession");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(
					getFactory(_operationClient.getOptions()
							.getSoapVersionURI()),
					renewSession4,
					optimizeContent(new javax.xml.namespace.QName(
							"http://impl.service.gridchem.org", "renewSession")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
					.getEnvelope();

			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement(),
					org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
							throw (org.gridchem.service.stub.session.PermissionExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
							throw (org.gridchem.service.stub.session.SessionExceptionException) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see org.gridchem.service.stub.SessionService#startrenewSession
	 * @param renewSession4
	 */
	public void startrenewSession(

			org.gridchem.service.stub.session.SessionServiceStub.RenewSession renewSession4,

			final org.gridchem.service.stub.session.SessionServiceCallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[2].getName());
		_operationClient.getOptions().setAction("urn:renewSession");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(
				_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
				"&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions()
				.getSoapVersionURI()), renewSession4,
				optimizeContent(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "renewSession")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient
				.setCallback(new org.apache.axis2.client.async.AxisCallback() {
					public void onMessage(
							org.apache.axis2.context.MessageContext resultContext) {
						try {
							org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext
									.getEnvelope();

							java.lang.Object object = fromOM(
									resultEnv.getBody().getFirstElement(),
									org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse.class,
									getEnvelopeNamespaces(resultEnv));
							callback
									.receiveResultrenewSession((org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse) object);

						} catch (org.apache.axis2.AxisFault e) {
							callback.receiveErrorrenewSession(e);
						}
					}

					public void onError(java.lang.Exception error) {
						if (error instanceof org.apache.axis2.AxisFault) {
							org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
							org.apache.axiom.om.OMElement faultElt = f
									.getDetail();
							if (faultElt != null) {
								if (faultExceptionNameMap.containsKey(faultElt
										.getQName())) {
									// make the fault by reflection
									try {
										java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
												.get(faultElt.getQName());
										java.lang.Class exceptionClass = java.lang.Class
												.forName(exceptionClassName);
										java.lang.Exception ex = (java.lang.Exception) exceptionClass
												.newInstance();
										// message class
										java.lang.String messageClassName = (java.lang.String) faultMessageMap
												.get(faultElt.getQName());
										java.lang.Class messageClass = java.lang.Class
												.forName(messageClassName);
										java.lang.Object messageObject = fromOM(
												faultElt, messageClass, null);
										java.lang.reflect.Method m = exceptionClass
												.getMethod(
														"setFaultMessage",
														new java.lang.Class[] { messageClass });
										m
												.invoke(
														ex,
														new java.lang.Object[] { messageObject });

										if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
											callback
													.receiveErrorrenewSession((org.gridchem.service.stub.session.PermissionExceptionException) ex);
											return;
										}

										if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
											callback
													.receiveErrorrenewSession((org.gridchem.service.stub.session.SessionExceptionException) ex);
											return;
										}

										callback
												.receiveErrorrenewSession(new java.rmi.RemoteException(
														ex.getMessage(), ex));
									} catch (java.lang.ClassCastException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorrenewSession(f);
									} catch (java.lang.ClassNotFoundException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorrenewSession(f);
									} catch (java.lang.NoSuchMethodException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorrenewSession(f);
									} catch (java.lang.reflect.InvocationTargetException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorrenewSession(f);
									} catch (java.lang.IllegalAccessException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorrenewSession(f);
									} catch (java.lang.InstantiationException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorrenewSession(f);
									} catch (org.apache.axis2.AxisFault e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorrenewSession(f);
									}
								} else {
									callback.receiveErrorrenewSession(f);
								}
							} else {
								callback.receiveErrorrenewSession(f);
							}
						} else {
							callback.receiveErrorrenewSession(error);
						}
					}

					public void onFault(
							org.apache.axis2.context.MessageContext faultContext) {
						org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
								.getInboundFaultFromMessageContext(faultContext);
						onError(fault);
					}

					public void onComplete() {
						try {
							_messageContext.getTransportOut().getSender()
									.cleanup(_messageContext);
						} catch (org.apache.axis2.AxisFault axisFault) {
							callback.receiveErrorrenewSession(axisFault);
						}
					}
				});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[2].getMessageReceiver() == null
				&& _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[2].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @see org.gridchem.service.stub.SessionService#getRemainingTime
	 * @param getRemainingTime6
	 * 
	 * @throws org.gridchem.service.stub.session.PermissionExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.SessionExceptionException
	 *             :
	 */

	public org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse getRemainingTime(

			org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime getRemainingTime6)

	throws java.rmi.RemoteException

	, org.gridchem.service.stub.session.PermissionExceptionException,
			org.gridchem.service.stub.session.SessionExceptionException {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[3].getName());
			_operationClient.getOptions().setAction("urn:getRemainingTime");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions()
					.getSoapVersionURI()), getRemainingTime6,
					optimizeContent(new javax.xml.namespace.QName(
							"http://impl.service.gridchem.org",
							"getRemainingTime")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
					.getEnvelope();

			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement(),
					org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
							throw (org.gridchem.service.stub.session.PermissionExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
							throw (org.gridchem.service.stub.session.SessionExceptionException) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see org.gridchem.service.stub.SessionService#startgetRemainingTime
	 * @param getRemainingTime6
	 */
	public void startgetRemainingTime(

			org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime getRemainingTime6,

			final org.gridchem.service.stub.session.SessionServiceCallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[3].getName());
		_operationClient.getOptions().setAction("urn:getRemainingTime");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(
				_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
				"&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(
				getFactory(_operationClient.getOptions().getSoapVersionURI()),
				getRemainingTime6,
				optimizeContent(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "getRemainingTime")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient
				.setCallback(new org.apache.axis2.client.async.AxisCallback() {
					public void onMessage(
							org.apache.axis2.context.MessageContext resultContext) {
						try {
							org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext
									.getEnvelope();

							java.lang.Object object = fromOM(
									resultEnv.getBody().getFirstElement(),
									org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse.class,
									getEnvelopeNamespaces(resultEnv));
							callback
									.receiveResultgetRemainingTime((org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse) object);

						} catch (org.apache.axis2.AxisFault e) {
							callback.receiveErrorgetRemainingTime(e);
						}
					}

					public void onError(java.lang.Exception error) {
						if (error instanceof org.apache.axis2.AxisFault) {
							org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
							org.apache.axiom.om.OMElement faultElt = f
									.getDetail();
							if (faultElt != null) {
								if (faultExceptionNameMap.containsKey(faultElt
										.getQName())) {
									// make the fault by reflection
									try {
										java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
												.get(faultElt.getQName());
										java.lang.Class exceptionClass = java.lang.Class
												.forName(exceptionClassName);
										java.lang.Exception ex = (java.lang.Exception) exceptionClass
												.newInstance();
										// message class
										java.lang.String messageClassName = (java.lang.String) faultMessageMap
												.get(faultElt.getQName());
										java.lang.Class messageClass = java.lang.Class
												.forName(messageClassName);
										java.lang.Object messageObject = fromOM(
												faultElt, messageClass, null);
										java.lang.reflect.Method m = exceptionClass
												.getMethod(
														"setFaultMessage",
														new java.lang.Class[] { messageClass });
										m
												.invoke(
														ex,
														new java.lang.Object[] { messageObject });

										if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
											callback
													.receiveErrorgetRemainingTime((org.gridchem.service.stub.session.PermissionExceptionException) ex);
											return;
										}

										if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
											callback
													.receiveErrorgetRemainingTime((org.gridchem.service.stub.session.SessionExceptionException) ex);
											return;
										}

										callback
												.receiveErrorgetRemainingTime(new java.rmi.RemoteException(
														ex.getMessage(), ex));
									} catch (java.lang.ClassCastException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetRemainingTime(f);
									} catch (java.lang.ClassNotFoundException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetRemainingTime(f);
									} catch (java.lang.NoSuchMethodException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetRemainingTime(f);
									} catch (java.lang.reflect.InvocationTargetException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetRemainingTime(f);
									} catch (java.lang.IllegalAccessException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetRemainingTime(f);
									} catch (java.lang.InstantiationException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetRemainingTime(f);
									} catch (org.apache.axis2.AxisFault e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetRemainingTime(f);
									}
								} else {
									callback.receiveErrorgetRemainingTime(f);
								}
							} else {
								callback.receiveErrorgetRemainingTime(f);
							}
						} else {
							callback.receiveErrorgetRemainingTime(error);
						}
					}

					public void onFault(
							org.apache.axis2.context.MessageContext faultContext) {
						org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
								.getInboundFaultFromMessageContext(faultContext);
						onError(fault);
					}

					public void onComplete() {
						try {
							_messageContext.getTransportOut().getSender()
									.cleanup(_messageContext);
						} catch (org.apache.axis2.AxisFault axisFault) {
							callback.receiveErrorgetRemainingTime(axisFault);
						}
					}
				});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[3].getMessageReceiver() == null
				&& _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[3].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @see org.gridchem.service.stub.SessionService#getAuthenticationTypes
	 */

	public org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse getAuthenticationTypes(

	)

	throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[4].getName());
			_operationClient.getOptions().setAction(
					"urn:getAuthenticationTypes");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			// Style is taken to be "document". No input parameters
			// according to the WS-Basic profile in this case we have to send an
			// empty soap message
			org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient
					.getOptions().getSoapVersionURI());
			env = factory.getDefaultEnvelope();

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
					.getEnvelope();

			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement(),
					org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see org.gridchem.service.stub.SessionService#startgetAuthenticationTypes
	 */
	public void startgetAuthenticationTypes(

			final org.gridchem.service.stub.session.SessionServiceCallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[4].getName());
		_operationClient.getOptions().setAction("urn:getAuthenticationTypes");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(
				_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
				"&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is taken to be "document". No input parameters
		// according to the WS-Basic profile in this case we have to send an
		// empty soap message
		org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient
				.getOptions().getSoapVersionURI());
		env = factory.getDefaultEnvelope();

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient
				.setCallback(new org.apache.axis2.client.async.AxisCallback() {
					public void onMessage(
							org.apache.axis2.context.MessageContext resultContext) {
						try {
							org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext
									.getEnvelope();

							java.lang.Object object = fromOM(
									resultEnv.getBody().getFirstElement(),
									org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse.class,
									getEnvelopeNamespaces(resultEnv));
							callback
									.receiveResultgetAuthenticationTypes((org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse) object);

						} catch (org.apache.axis2.AxisFault e) {
							callback.receiveErrorgetAuthenticationTypes(e);
						}
					}

					public void onError(java.lang.Exception error) {
						if (error instanceof org.apache.axis2.AxisFault) {
							org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
							org.apache.axiom.om.OMElement faultElt = f
									.getDetail();
							if (faultElt != null) {
								if (faultExceptionNameMap.containsKey(faultElt
										.getQName())) {
									// make the fault by reflection
									try {
										java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
												.get(faultElt.getQName());
										java.lang.Class exceptionClass = java.lang.Class
												.forName(exceptionClassName);
										java.lang.Exception ex = (java.lang.Exception) exceptionClass
												.newInstance();
										// message class
										java.lang.String messageClassName = (java.lang.String) faultMessageMap
												.get(faultElt.getQName());
										java.lang.Class messageClass = java.lang.Class
												.forName(messageClassName);
										java.lang.Object messageObject = fromOM(
												faultElt, messageClass, null);
										java.lang.reflect.Method m = exceptionClass
												.getMethod(
														"setFaultMessage",
														new java.lang.Class[] { messageClass });
										m
												.invoke(
														ex,
														new java.lang.Object[] { messageObject });

										callback
												.receiveErrorgetAuthenticationTypes(new java.rmi.RemoteException(
														ex.getMessage(), ex));
									} catch (java.lang.ClassCastException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetAuthenticationTypes(f);
									} catch (java.lang.ClassNotFoundException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetAuthenticationTypes(f);
									} catch (java.lang.NoSuchMethodException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetAuthenticationTypes(f);
									} catch (java.lang.reflect.InvocationTargetException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetAuthenticationTypes(f);
									} catch (java.lang.IllegalAccessException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetAuthenticationTypes(f);
									} catch (java.lang.InstantiationException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetAuthenticationTypes(f);
									} catch (org.apache.axis2.AxisFault e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback
												.receiveErrorgetAuthenticationTypes(f);
									}
								} else {
									callback
											.receiveErrorgetAuthenticationTypes(f);
								}
							} else {
								callback.receiveErrorgetAuthenticationTypes(f);
							}
						} else {
							callback.receiveErrorgetAuthenticationTypes(error);
						}
					}

					public void onFault(
							org.apache.axis2.context.MessageContext faultContext) {
						org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
								.getInboundFaultFromMessageContext(faultContext);
						onError(fault);
					}

					public void onComplete() {
						try {
							_messageContext.getTransportOut().getSender()
									.cleanup(_messageContext);
						} catch (org.apache.axis2.AxisFault axisFault) {
							callback
									.receiveErrorgetAuthenticationTypes(axisFault);
						}
					}
				});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[4].getMessageReceiver() == null
				&& _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[4].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @throws org.gridchem.service.stub.session.PermissionExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.SessionExceptionException
	 *             :
	 * @throws org.gridchem.service.stub.session.ProviderExceptionException
	 *             :
	 */
	public void setSessionProject(
			org.gridchem.service.stub.session.SessionServiceStub.SetSessionProject setSessionProject10

	) throws java.rmi.RemoteException

	, org.gridchem.service.stub.session.PermissionExceptionException,
			org.gridchem.service.stub.session.SessionExceptionException,
			org.gridchem.service.stub.session.ProviderExceptionException {
		org.apache.axis2.context.MessageContext _messageContext = null;

		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[5].getName());
			_operationClient.getOptions().setAction("urn:setSessionProject");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			org.apache.axiom.soap.SOAPEnvelope env = null;
			_messageContext = new org.apache.axis2.context.MessageContext();

			// Style is Doc.

			env = toEnvelope(getFactory(_operationClient.getOptions()
					.getSoapVersionURI()), setSessionProject10,
					optimizeContent(new javax.xml.namespace.QName(
							"http://impl.service.gridchem.org",
							"setSessionProject")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// create message context with that soap envelope

			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			_operationClient.execute(true);

		} catch (org.apache.axis2.AxisFault f) {
			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof org.gridchem.service.stub.session.PermissionExceptionException) {
							throw (org.gridchem.service.stub.session.PermissionExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.SessionExceptionException) {
							throw (org.gridchem.service.stub.session.SessionExceptionException) ex;
						}

						if (ex instanceof org.gridchem.service.stub.session.ProviderExceptionException) {
							throw (org.gridchem.service.stub.session.ProviderExceptionException) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}

		return;
	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(
			org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator
					.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// http://localhost:8080/axis2/services/SessionService.SessionServiceHttpSoap12Endpoint/
	public static class ParameterExceptionE implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ParameterException", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for ParameterException
		 */

		protected ParameterException localParameterException;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localParameterExceptionTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return ParameterException
		 */
		public ParameterException getParameterException() {
			return localParameterException;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ParameterException
		 */
		public void setParameterException(ParameterException param) {

			if (param != null) {
				// update the setting tracker
				localParameterExceptionTracker = true;
			} else {
				localParameterExceptionTracker = true;

			}

			this.localParameterException = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ParameterExceptionE.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":ParameterException",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "ParameterException", xmlWriter);
				}

			}
			if (localParameterExceptionTracker) {
				if (localParameterException == null) {

					java.lang.String namespace2 = "http://impl.service.gridchem.org";

					if (!namespace2.equals("")) {
						java.lang.String prefix2 = xmlWriter
								.getPrefix(namespace2);

						if (prefix2 == null) {
							prefix2 = generatePrefix(namespace2);

							xmlWriter.writeStartElement(prefix2,
									"ParameterException", namespace2);
							xmlWriter.writeNamespace(prefix2, namespace2);
							xmlWriter.setPrefix(prefix2, namespace2);

						} else {
							xmlWriter.writeStartElement(namespace2,
									"ParameterException");
						}

					} else {
						xmlWriter.writeStartElement("ParameterException");
					}

					// write the nil attribute
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);
					xmlWriter.writeEndElement();
				} else {
					localParameterException.serialize(
							new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"ParameterException"), factory, xmlWriter);
				}
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localParameterExceptionTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"ParameterException"));

				elementList.add(localParameterException == null ? null
						: localParameterException);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static ParameterExceptionE parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				ParameterExceptionE object = new ParameterExceptionE();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						// Skip the element and report the null value. It cannot
						// have subelements.
						while (!reader.isEndElement())
							reader.next();

						return null;

					}

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"ParameterException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (ParameterExceptionE) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"ParameterException").equals(reader
									.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if ("true".equals(nillableValue)
								|| "1".equals(nillableValue)) {
							object.setParameterException(null);
							reader.next();

							reader.next();

						} else {

							object
									.setParameterException(ParameterException.Factory
											.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RenewSession implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "renewSession", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for Args0
		 */

		protected java.lang.String localArgs0;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs0() {
			return localArgs0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args0
		 */
		public void setArgs0(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs0Tracker = true;
			} else {
				localArgs0Tracker = true;

			}

			this.localArgs0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RenewSession.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":renewSession",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "renewSession", xmlWriter);
				}

			}
			if (localArgs0Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args0", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args0");
					}

				} else {
					xmlWriter.writeStartElement("args0");
				}

				if (localArgs0 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs0);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArgs0Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args0"));

				elementList.add(localArgs0 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs0));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static RenewSession parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				RenewSession object = new RenewSession();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"renewSession".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (RenewSession) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args0")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs0(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RenewSessionResponse implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "renewSessionResponse",
				"ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for _return
		 */

		protected long local_return;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean local_returnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long get_return() {
			return local_return;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _return
		 */
		public void set_return(long param) {

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE) {
				local_returnTracker = false;

			} else {
				local_returnTracker = true;
			}

			this.local_return = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RenewSessionResponse.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":renewSessionResponse",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "renewSessionResponse", xmlWriter);
				}

			}
			if (local_returnTracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter
								.writeStartElement(prefix, "return", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "return");
					}

				} else {
					xmlWriter.writeStartElement("return");
				}

				if (local_return == java.lang.Long.MIN_VALUE) {

					throw new org.apache.axis2.databinding.ADBException(
							"return cannot be null!!");

				} else {
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(local_return));
				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (local_returnTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "return"));

				elementList
						.add(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(local_return));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static RenewSessionResponse parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				RenewSessionResponse object = new RenewSessionResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"renewSessionResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (RenewSessionResponse) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"return").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object
								.set_return(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else {

						object.set_return(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SetSessionProject implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "setSessionProject", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for Args0
		 */

		protected java.lang.String localArgs0;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs0() {
			return localArgs0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args0
		 */
		public void setArgs0(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs0Tracker = true;
			} else {
				localArgs0Tracker = true;

			}

			this.localArgs0 = param;

		}

		/**
		 * field for Args1
		 */

		protected java.lang.String localArgs1;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs1Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs1() {
			return localArgs1;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args1
		 */
		public void setArgs1(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs1Tracker = true;
			} else {
				localArgs1Tracker = true;

			}

			this.localArgs1 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SetSessionProject.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":setSessionProject",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "setSessionProject", xmlWriter);
				}

			}
			if (localArgs0Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args0", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args0");
					}

				} else {
					xmlWriter.writeStartElement("args0");
				}

				if (localArgs0 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs0);

				}

				xmlWriter.writeEndElement();
			}
			if (localArgs1Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args1", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args1");
					}

				} else {
					xmlWriter.writeStartElement("args1");
				}

				if (localArgs1 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs1);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArgs0Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args0"));

				elementList.add(localArgs0 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs0));
			}
			if (localArgs1Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args1"));

				elementList.add(localArgs1 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs1));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static SetSessionProject parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				SetSessionProject object = new SetSessionProject();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"setSessionProject".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (SetSessionProject) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args0")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs0(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args1")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs1(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class PermissionException implements
			org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * PermissionException Namespace URI =
		 * http://exceptions.service.gridchem.org/xsd Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://exceptions.service.gridchem.org/xsd")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, parentQName) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					PermissionException.this.serialize(parentQName, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://exceptions.service.gridchem.org/xsd");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":PermissionException",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "PermissionException", xmlWriter);
				}

			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static PermissionException parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				PermissionException object = new PermissionException();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"PermissionException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (PermissionException) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SessionExceptionE implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "SessionException", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for SessionException
		 */

		protected SessionException localSessionException;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localSessionExceptionTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return SessionException
		 */
		public SessionException getSessionException() {
			return localSessionException;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SessionException
		 */
		public void setSessionException(SessionException param) {

			if (param != null) {
				// update the setting tracker
				localSessionExceptionTracker = true;
			} else {
				localSessionExceptionTracker = true;

			}

			this.localSessionException = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SessionExceptionE.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":SessionException",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "SessionException", xmlWriter);
				}

			}
			if (localSessionExceptionTracker) {
				if (localSessionException == null) {

					java.lang.String namespace2 = "http://impl.service.gridchem.org";

					if (!namespace2.equals("")) {
						java.lang.String prefix2 = xmlWriter
								.getPrefix(namespace2);

						if (prefix2 == null) {
							prefix2 = generatePrefix(namespace2);

							xmlWriter.writeStartElement(prefix2,
									"SessionException", namespace2);
							xmlWriter.writeNamespace(prefix2, namespace2);
							xmlWriter.setPrefix(prefix2, namespace2);

						} else {
							xmlWriter.writeStartElement(namespace2,
									"SessionException");
						}

					} else {
						xmlWriter.writeStartElement("SessionException");
					}

					// write the nil attribute
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);
					xmlWriter.writeEndElement();
				} else {
					localSessionException.serialize(
							new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"SessionException"), factory, xmlWriter);
				}
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localSessionExceptionTracker) {
				elementList
						.add(new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"SessionException"));

				elementList.add(localSessionException == null ? null
						: localSessionException);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static SessionExceptionE parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				SessionExceptionE object = new SessionExceptionE();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						// Skip the element and report the null value. It cannot
						// have subelements.
						while (!reader.isEndElement())
							reader.next();

						return null;

					}

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"SessionException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (SessionExceptionE) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"SessionException")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if ("true".equals(nillableValue)
								|| "1".equals(nillableValue)) {
							object.setSessionException(null);
							reader.next();

							reader.next();

						} else {

							object.setSessionException(SessionException.Factory
									.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class CreateSession implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "createSession", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for Args0
		 */

		protected java.lang.String localArgs0;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs0() {
			return localArgs0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args0
		 */
		public void setArgs0(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs0Tracker = true;
			} else {
				localArgs0Tracker = true;

			}

			this.localArgs0 = param;

		}

		/**
		 * field for Args1
		 */

		protected java.lang.String localArgs1;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs1Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs1() {
			return localArgs1;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args1
		 */
		public void setArgs1(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs1Tracker = true;
			} else {
				localArgs1Tracker = true;

			}

			this.localArgs1 = param;

		}

		/**
		 * field for Args2
		 */

		protected java.lang.String localArgs2;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs2Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs2() {
			return localArgs2;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args2
		 */
		public void setArgs2(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs2Tracker = true;
			} else {
				localArgs2Tracker = true;

			}

			this.localArgs2 = param;

		}

		/**
		 * field for Args3
		 */

		protected java.lang.String localArgs3;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs3Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs3() {
			return localArgs3;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args3
		 */
		public void setArgs3(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs3Tracker = true;
			} else {
				localArgs3Tracker = true;

			}

			this.localArgs3 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					CreateSession.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":createSession",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "createSession", xmlWriter);
				}

			}
			if (localArgs0Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args0", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args0");
					}

				} else {
					xmlWriter.writeStartElement("args0");
				}

				if (localArgs0 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs0);

				}

				xmlWriter.writeEndElement();
			}
			if (localArgs1Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args1", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args1");
					}

				} else {
					xmlWriter.writeStartElement("args1");
				}

				if (localArgs1 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs1);

				}

				xmlWriter.writeEndElement();
			}
			if (localArgs2Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args2", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args2");
					}

				} else {
					xmlWriter.writeStartElement("args2");
				}

				if (localArgs2 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs2);

				}

				xmlWriter.writeEndElement();
			}
			if (localArgs3Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args3", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args3");
					}

				} else {
					xmlWriter.writeStartElement("args3");
				}

				if (localArgs3 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs3);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArgs0Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args0"));

				elementList.add(localArgs0 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs0));
			}
			if (localArgs1Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args1"));

				elementList.add(localArgs1 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs1));
			}
			if (localArgs2Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args2"));

				elementList.add(localArgs2 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs2));
			}
			if (localArgs3Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args3"));

				elementList.add(localArgs3 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs3));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static CreateSession parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				CreateSession object = new CreateSession();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"createSession".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (CreateSession) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args0")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs0(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args1")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs1(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args2")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs2(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args3")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs3(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ExtensionMapper {

		public static java.lang.Object getTypeObject(
				java.lang.String namespaceURI, java.lang.String typeName,
				javax.xml.stream.XMLStreamReader reader)
				throws java.lang.Exception {

			if ("http://impl.service.gridchem.org".equals(namespaceURI)
					&& "Exception".equals(typeName)) {

				return Exception.Factory.parse(reader);

			}

			if ("http://exceptions.service.gridchem.org/xsd"
					.equals(namespaceURI)
					&& "SessionException".equals(typeName)) {

				return SessionException.Factory.parse(reader);

			}

			if ("http://exceptions.service.gridchem.org/xsd"
					.equals(namespaceURI)
					&& "PermissionException".equals(typeName)) {

				return PermissionException.Factory.parse(reader);

			}

			if ("http://exceptions.service.gridchem.org/xsd"
					.equals(namespaceURI)
					&& "ParameterException".equals(typeName)) {

				return ParameterException.Factory.parse(reader);

			}

			if ("http://exceptions.service.gridchem.org/xsd"
					.equals(namespaceURI)
					&& "ProviderException".equals(typeName)) {

				return ProviderException.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException(
					"Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class SessionException implements
			org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * SessionException Namespace URI =
		 * http://exceptions.service.gridchem.org/xsd Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://exceptions.service.gridchem.org/xsd")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, parentQName) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SessionException.this.serialize(parentQName, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://exceptions.service.gridchem.org/xsd");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":SessionException",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "SessionException", xmlWriter);
				}

			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static SessionException parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				SessionException object = new SessionException();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"SessionException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (SessionException) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class Exception implements
			org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * Exception Namespace URI = http://impl.service.gridchem.org Namespace
		 * Prefix = ns2
		 */

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for Exception
		 */

		protected java.lang.Object localException;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localExceptionTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.Object
		 */
		public java.lang.Object getException() {
			return localException;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Exception
		 */
		public void setException(java.lang.Object param) {

			if (param != null) {
				// update the setting tracker
				localExceptionTracker = true;
			} else {
				localExceptionTracker = true;

			}

			this.localException = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, parentQName) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					Exception.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":Exception", xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "Exception", xmlWriter);
				}

			}
			if (localExceptionTracker) {

				if (localException != null) {
					if (localException instanceof org.apache.axis2.databinding.ADBBean) {
						((org.apache.axis2.databinding.ADBBean) localException)
								.serialize(new javax.xml.namespace.QName(
										"http://impl.service.gridchem.org",
										"Exception"), factory, xmlWriter, true);
					} else {
						java.lang.String namespace2 = "http://impl.service.gridchem.org";
						if (!namespace2.equals("")) {
							java.lang.String prefix2 = xmlWriter
									.getPrefix(namespace2);

							if (prefix2 == null) {
								prefix2 = generatePrefix(namespace2);

								xmlWriter.writeStartElement(prefix2,
										"Exception", namespace2);
								xmlWriter.writeNamespace(prefix2, namespace2);
								xmlWriter.setPrefix(prefix2, namespace2);

							} else {
								xmlWriter.writeStartElement(namespace2,
										"Exception");
							}

						} else {
							xmlWriter.writeStartElement("Exception");
						}
						org.apache.axis2.databinding.utils.ConverterUtil
								.serializeAnyType(localException, xmlWriter);
						xmlWriter.writeEndElement();
					}
				} else {

					// write null attribute
					java.lang.String namespace2 = "http://impl.service.gridchem.org";
					if (!namespace2.equals("")) {
						java.lang.String prefix2 = xmlWriter
								.getPrefix(namespace2);

						if (prefix2 == null) {
							prefix2 = generatePrefix(namespace2);

							xmlWriter.writeStartElement(prefix2, "Exception",
									namespace2);
							xmlWriter.writeNamespace(prefix2, namespace2);
							xmlWriter.setPrefix(prefix2, namespace2);

						} else {
							xmlWriter
									.writeStartElement(namespace2, "Exception");
						}

					} else {
						xmlWriter.writeStartElement("Exception");
					}

					// write the nil attribute
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);
					xmlWriter.writeEndElement();

				}

			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localExceptionTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "Exception"));

				elementList.add(localException == null ? null : localException);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static Exception parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				Exception object = new Exception();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"Exception".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (Exception) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"Exception").equals(reader.getName())) {

						object
								.setException(org.apache.axis2.databinding.utils.ConverterUtil
										.getAnyTypeObject(reader,
												ExtensionMapper.class));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class CreateSessionResponse implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "createSessionResponse",
				"ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for _return
		 */

		protected java.lang.String local_return;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean local_returnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String get_return() {
			return local_return;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _return
		 */
		public void set_return(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				local_returnTracker = true;
			} else {
				local_returnTracker = true;

			}

			this.local_return = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					CreateSessionResponse.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":createSessionResponse",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "createSessionResponse", xmlWriter);
				}

			}
			if (local_returnTracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter
								.writeStartElement(prefix, "return", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "return");
					}

				} else {
					xmlWriter.writeStartElement("return");
				}

				if (local_return == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(local_return);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (local_returnTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "return"));

				elementList.add(local_return == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(local_return));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static CreateSessionResponse parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				CreateSessionResponse object = new CreateSessionResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"createSessionResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (CreateSessionResponse) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"return").equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.set_return(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ProviderExceptionE implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "ProviderException", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for ProviderException
		 */

		protected ProviderException localProviderException;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localProviderExceptionTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return ProviderException
		 */
		public ProviderException getProviderException() {
			return localProviderException;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProviderException
		 */
		public void setProviderException(ProviderException param) {

			if (param != null) {
				// update the setting tracker
				localProviderExceptionTracker = true;
			} else {
				localProviderExceptionTracker = true;

			}

			this.localProviderException = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ProviderExceptionE.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":ProviderException",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "ProviderException", xmlWriter);
				}

			}
			if (localProviderExceptionTracker) {
				if (localProviderException == null) {

					java.lang.String namespace2 = "http://impl.service.gridchem.org";

					if (!namespace2.equals("")) {
						java.lang.String prefix2 = xmlWriter
								.getPrefix(namespace2);

						if (prefix2 == null) {
							prefix2 = generatePrefix(namespace2);

							xmlWriter.writeStartElement(prefix2,
									"ProviderException", namespace2);
							xmlWriter.writeNamespace(prefix2, namespace2);
							xmlWriter.setPrefix(prefix2, namespace2);

						} else {
							xmlWriter.writeStartElement(namespace2,
									"ProviderException");
						}

					} else {
						xmlWriter.writeStartElement("ProviderException");
					}

					// write the nil attribute
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);
					xmlWriter.writeEndElement();
				} else {
					localProviderException.serialize(
							new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"ProviderException"), factory, xmlWriter);
				}
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localProviderExceptionTracker) {
				elementList
						.add(new javax.xml.namespace.QName(
								"http://impl.service.gridchem.org",
								"ProviderException"));

				elementList.add(localProviderException == null ? null
						: localProviderException);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static ProviderExceptionE parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				ProviderExceptionE object = new ProviderExceptionE();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						// Skip the element and report the null value. It cannot
						// have subelements.
						while (!reader.isEndElement())
							reader.next();

						return null;

					}

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"ProviderException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (ProviderExceptionE) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"ProviderException").equals(reader
									.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if ("true".equals(nillableValue)
								|| "1".equals(nillableValue)) {
							object.setProviderException(null);
							reader.next();

							reader.next();

						} else {

							object
									.setProviderException(ProviderException.Factory
											.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class GetRemainingTime implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "getRemainingTime", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for Args0
		 */

		protected java.lang.String localArgs0;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs0() {
			return localArgs0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args0
		 */
		public void setArgs0(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs0Tracker = true;
			} else {
				localArgs0Tracker = true;

			}

			this.localArgs0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					GetRemainingTime.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":getRemainingTime",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "getRemainingTime", xmlWriter);
				}

			}
			if (localArgs0Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args0", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args0");
					}

				} else {
					xmlWriter.writeStartElement("args0");
				}

				if (localArgs0 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs0);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArgs0Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args0"));

				elementList.add(localArgs0 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs0));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static GetRemainingTime parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				GetRemainingTime object = new GetRemainingTime();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"getRemainingTime".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (GetRemainingTime) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args0")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs0(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class GetRemainingTimeResponse implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "getRemainingTimeResponse",
				"ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for _return
		 */

		protected long local_return;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean local_returnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return long
		 */
		public long get_return() {
			return local_return;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _return
		 */
		public void set_return(long param) {

			// setting primitive attribute tracker to true

			if (param == java.lang.Long.MIN_VALUE) {
				local_returnTracker = false;

			} else {
				local_returnTracker = true;
			}

			this.local_return = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					GetRemainingTimeResponse.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix
									+ ":getRemainingTimeResponse", xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "getRemainingTimeResponse", xmlWriter);
				}

			}
			if (local_returnTracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter
								.writeStartElement(prefix, "return", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "return");
					}

				} else {
					xmlWriter.writeStartElement("return");
				}

				if (local_return == java.lang.Long.MIN_VALUE) {

					throw new org.apache.axis2.databinding.ADBException(
							"return cannot be null!!");

				} else {
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(local_return));
				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (local_returnTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "return"));

				elementList
						.add(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(local_return));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static GetRemainingTimeResponse parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				GetRemainingTimeResponse object = new GetRemainingTimeResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"getRemainingTimeResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (GetRemainingTimeResponse) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"return").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object
								.set_return(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToLong(content));

						reader.next();

					} // End of if for expected property start element

					else {

						object.set_return(java.lang.Long.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DestroySession implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "destroySession", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for Args0
		 */

		protected java.lang.String localArgs0;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localArgs0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getArgs0() {
			return localArgs0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Args0
		 */
		public void setArgs0(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localArgs0Tracker = true;
			} else {
				localArgs0Tracker = true;

			}

			this.localArgs0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					DestroySession.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":destroySession",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "destroySession", xmlWriter);
				}

			}
			if (localArgs0Tracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "args0", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "args0");
					}

				} else {
					xmlWriter.writeStartElement("args0");
				}

				if (localArgs0 == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localArgs0);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArgs0Tracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "args0"));

				elementList.add(localArgs0 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localArgs0));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static DestroySession parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				DestroySession object = new DestroySession();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"destroySession".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (DestroySession) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org", "args0")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.setArgs0(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ParameterException extends Exception implements
			org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * ParameterException Namespace URI =
		 * http://exceptions.service.gridchem.org/xsd Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://exceptions.service.gridchem.org/xsd")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, parentQName) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ParameterException.this.serialize(parentQName, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			java.lang.String namespacePrefix = registerPrefix(xmlWriter,
					"http://exceptions.service.gridchem.org/xsd");
			if ((namespacePrefix != null)
					&& (namespacePrefix.trim().length() > 0)) {
				writeAttribute("xsi",
						"http://www.w3.org/2001/XMLSchema-instance", "type",
						namespacePrefix + ":ParameterException", xmlWriter);
			} else {
				writeAttribute("xsi",
						"http://www.w3.org/2001/XMLSchema-instance", "type",
						"ParameterException", xmlWriter);
			}

			if (localExceptionTracker) {

				if (localException != null) {
					if (localException instanceof org.apache.axis2.databinding.ADBBean) {
						((org.apache.axis2.databinding.ADBBean) localException)
								.serialize(new javax.xml.namespace.QName(
										"http://impl.service.gridchem.org",
										"Exception"), factory, xmlWriter, true);
					} else {
						java.lang.String namespace2 = "http://impl.service.gridchem.org";
						if (!namespace2.equals("")) {
							java.lang.String prefix2 = xmlWriter
									.getPrefix(namespace2);

							if (prefix2 == null) {
								prefix2 = generatePrefix(namespace2);

								xmlWriter.writeStartElement(prefix2,
										"Exception", namespace2);
								xmlWriter.writeNamespace(prefix2, namespace2);
								xmlWriter.setPrefix(prefix2, namespace2);

							} else {
								xmlWriter.writeStartElement(namespace2,
										"Exception");
							}

						} else {
							xmlWriter.writeStartElement("Exception");
						}
						org.apache.axis2.databinding.utils.ConverterUtil
								.serializeAnyType(localException, xmlWriter);
						xmlWriter.writeEndElement();
					}
				} else {

					// write null attribute
					java.lang.String namespace2 = "http://impl.service.gridchem.org";
					if (!namespace2.equals("")) {
						java.lang.String prefix2 = xmlWriter
								.getPrefix(namespace2);

						if (prefix2 == null) {
							prefix2 = generatePrefix(namespace2);

							xmlWriter.writeStartElement(prefix2, "Exception",
									namespace2);
							xmlWriter.writeNamespace(prefix2, namespace2);
							xmlWriter.setPrefix(prefix2, namespace2);

						} else {
							xmlWriter
									.writeStartElement(namespace2, "Exception");
						}

					} else {
						xmlWriter.writeStartElement("Exception");
					}

					// write the nil attribute
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);
					xmlWriter.writeEndElement();

				}

			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			attribList.add(new javax.xml.namespace.QName(
					"http://www.w3.org/2001/XMLSchema-instance", "type"));
			attribList.add(new javax.xml.namespace.QName(
					"http://exceptions.service.gridchem.org/xsd",
					"ParameterException"));
			if (localExceptionTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "Exception"));

				elementList.add(localException == null ? null : localException);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static ParameterException parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				ParameterException object = new ParameterException();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"ParameterException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (ParameterException) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"Exception").equals(reader.getName())) {

						object
								.setException(org.apache.axis2.databinding.utils.ConverterUtil
										.getAnyTypeObject(reader,
												ExtensionMapper.class));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ProviderException implements
			org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * ProviderException Namespace URI =
		 * http://exceptions.service.gridchem.org/xsd Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://exceptions.service.gridchem.org/xsd")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, parentQName) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ProviderException.this.serialize(parentQName, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					parentQName, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://exceptions.service.gridchem.org/xsd");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":ProviderException",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "ProviderException", xmlWriter);
				}

			}

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static ProviderException parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				ProviderException object = new ProviderException();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"ProviderException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (ProviderException) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class PermissionExceptionE implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org", "PermissionException",
				"ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for PermissionException
		 */

		protected PermissionException localPermissionException;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean localPermissionExceptionTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return PermissionException
		 */
		public PermissionException getPermissionException() {
			return localPermissionException;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PermissionException
		 */
		public void setPermissionException(PermissionException param) {

			if (param != null) {
				// update the setting tracker
				localPermissionExceptionTracker = true;
			} else {
				localPermissionExceptionTracker = true;

			}

			this.localPermissionException = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					PermissionExceptionE.this.serialize(MY_QNAME, factory,
							xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix + ":PermissionException",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "PermissionException", xmlWriter);
				}

			}
			if (localPermissionExceptionTracker) {
				if (localPermissionException == null) {

					java.lang.String namespace2 = "http://impl.service.gridchem.org";

					if (!namespace2.equals("")) {
						java.lang.String prefix2 = xmlWriter
								.getPrefix(namespace2);

						if (prefix2 == null) {
							prefix2 = generatePrefix(namespace2);

							xmlWriter.writeStartElement(prefix2,
									"PermissionException", namespace2);
							xmlWriter.writeNamespace(prefix2, namespace2);
							xmlWriter.setPrefix(prefix2, namespace2);

						} else {
							xmlWriter.writeStartElement(namespace2,
									"PermissionException");
						}

					} else {
						xmlWriter.writeStartElement("PermissionException");
					}

					// write the nil attribute
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);
					xmlWriter.writeEndElement();
				} else {
					localPermissionException.serialize(
							new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"PermissionException"), factory, xmlWriter);
				}
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localPermissionExceptionTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org",
						"PermissionException"));

				elementList.add(localPermissionException == null ? null
						: localPermissionException);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static PermissionExceptionE parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				PermissionExceptionE object = new PermissionExceptionE();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						// Skip the element and report the null value. It cannot
						// have subelements.
						while (!reader.isEndElement())
							reader.next();

						return null;

					}

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"PermissionException".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (PermissionExceptionE) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"PermissionException").equals(reader
									.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if ("true".equals(nillableValue)
								|| "1".equals(nillableValue)) {
							object.setPermissionException(null);
							reader.next();

							reader.next();

						} else {

							object
									.setPermissionException(PermissionException.Factory
											.parse(reader));

							reader.next();
						}
					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class GetAuthenticationTypesResponse implements
			org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://impl.service.gridchem.org",
				"getAuthenticationTypesResponse", "ns2");

		private static java.lang.String generatePrefix(
				java.lang.String namespace) {
			if (namespace.equals("http://impl.service.gridchem.org")) {
				return "ns2";
			}
			return org.apache.axis2.databinding.utils.BeanUtil
					.getUniquePrefix();
		}

		/**
		 * field for _return
		 */

		protected java.lang.String local_return;

		/*
		 * This tracker boolean wil be used to detect whether the user called
		 * the set method for this attribute. It will be used to determine
		 * whether to include this field in the serialized XML
		 */
		protected boolean local_returnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String get_return() {
			return local_return;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _return
		 */
		public void set_return(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				local_returnTracker = true;
			} else {
				local_returnTracker = true;

			}

			this.local_return = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(
				javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader
								.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory)
				throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
					this, MY_QNAME) {

				public void serialize(
						org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					GetAuthenticationTypesResponse.this.serialize(MY_QNAME,
							factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
					MY_QNAME, factory, dataSource);

		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(
				final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
				boolean serializeType)
				throws javax.xml.stream.XMLStreamException,
				org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName
							.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName
							.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter,
						"http://impl.service.gridchem.org");
				if ((namespacePrefix != null)
						&& (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", namespacePrefix
									+ ":getAuthenticationTypesResponse",
							xmlWriter);
				} else {
					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance",
							"type", "getAuthenticationTypesResponse", xmlWriter);
				}

			}
			if (local_returnTracker) {
				namespace = "http://impl.service.gridchem.org";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter
								.writeStartElement(prefix, "return", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "return");
					}

				} else {
					xmlWriter.writeStartElement("return");
				}

				if (local_return == null) {
					// write the nil attribute

					writeAttribute("xsi",
							"http://www.w3.org/2001/XMLSchema-instance", "nil",
							"1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(local_return);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix,
				java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace,
				java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace,
				java.lang.String attName, javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter
					.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix
							+ ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter
							.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qname));
				}

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames,
				javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not
				// possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite
									.append(prefix)
									.append(":")
									.append(
											org.apache.axis2.databinding.utils.ConverterUtil
													.convertToString(qnames[i]));
						} else {
							stringToWrite
									.append(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(
				javax.xml.stream.XMLStreamWriter xmlWriter,
				java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil
							.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(
				javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (local_returnTracker) {
				elementList.add(new javax.xml.namespace.QName(
						"http://impl.service.gridchem.org", "return"));

				elementList.add(local_return == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(local_return));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
					qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object
			 * is an element, the current or next start element starts this
			 * object and any intervening reader events are ignorable If this
			 * object is not an element, it is a complex type and the reader is
			 * at the event just after the outer start element Postcondition: If
			 * this object is an element, the reader is positioned at its end
			 * element If this object is a complex type, the reader is
			 * positioned at the end element of its outer element
			 */
			public static GetAuthenticationTypesResponse parse(
					javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				GetAuthenticationTypesResponse object = new GetAuthenticationTypesResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue(
										"http://www.w3.org/2001/XMLSchema-instance",
										"type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0,
										fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName
									.substring(fullTypeName.indexOf(":") + 1);

							if (!"getAuthenticationTypesResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader
										.getNamespaceContext().getNamespaceURI(
												nsPrefix);
								return (GetAuthenticationTypesResponse) ExtensionMapper
										.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName(
									"http://impl.service.gridchem.org",
									"return").equals(reader.getName())) {

						nillableValue = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil");
						if (!"true".equals(nillableValue)
								&& !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object
									.set_return(org.apache.axis2.databinding.utils.ConverterUtil
											.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement "
										+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.CreateSession param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.CreateSession.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.ParameterExceptionE param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.ParameterExceptionE.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.ProviderExceptionE param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.ProviderExceptionE.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.DestroySession param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.DestroySession.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.RenewSession param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.RenewSession.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			org.gridchem.service.stub.session.SessionServiceStub.SetSessionProject param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							org.gridchem.service.stub.session.SessionServiceStub.SetSessionProject.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			org.gridchem.service.stub.session.SessionServiceStub.CreateSession param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();
			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											org.gridchem.service.stub.session.SessionServiceStub.CreateSession.MY_QNAME,
											factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			org.gridchem.service.stub.session.SessionServiceStub.DestroySession param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();
			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											org.gridchem.service.stub.session.SessionServiceStub.DestroySession.MY_QNAME,
											factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			org.gridchem.service.stub.session.SessionServiceStub.RenewSession param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();
			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											org.gridchem.service.stub.session.SessionServiceStub.RenewSession.MY_QNAME,
											factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();
			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime.MY_QNAME,
											factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			org.gridchem.service.stub.session.SessionServiceStub.SetSessionProject param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();
			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											org.gridchem.service.stub.session.SessionServiceStub.SetSessionProject.MY_QNAME,
											factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
			java.lang.Class type, java.util.Map extraNamespaces)
			throws org.apache.axis2.AxisFault {

		try {

			if (org.gridchem.service.stub.session.SessionServiceStub.CreateSession.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.CreateSession.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.CreateSessionResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.ParameterExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.ParameterExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.ProviderExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.ProviderExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.DestroySession.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.DestroySession.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.RenewSession.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.RenewSession.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.RenewSessionResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTime.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.GetRemainingTimeResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.GetAuthenticationTypesResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.SetSessionProject.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.SetSessionProject.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.PermissionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.SessionExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (org.gridchem.service.stub.session.SessionServiceStub.ProviderExceptionE.class
					.equals(type)) {

				return org.gridchem.service.stub.session.SessionServiceStub.ProviderExceptionE.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
