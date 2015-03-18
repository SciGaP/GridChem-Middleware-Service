/**
 * 
 */
package org.gridchem.service.exceptions;

/**
 * @author dooley
 *
 */
@SuppressWarnings("serial")
public class BlackListException extends RuntimeException {

	/**
	 * 
	 */
	public BlackListException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public BlackListException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public BlackListException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BlackListException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
