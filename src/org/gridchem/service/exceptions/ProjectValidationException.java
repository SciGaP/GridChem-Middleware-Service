/**
 * 
 */
package org.gridchem.service.exceptions;

/**
 * @author dooley
 *
 */
@SuppressWarnings("serial")
public class ProjectValidationException extends Exception {

	/**
	 * 
	 */
	public ProjectValidationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ProjectValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ProjectValidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProjectValidationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
