/**
 * 
 */
package org.gridchem.service.exceptions;

/**
 * @author dooley
 *
 */
@SuppressWarnings("serial")
public class ParameterException extends Exception {

	/**
	 * 
	 */
	public ParameterException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ParameterException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ParameterException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ParameterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
