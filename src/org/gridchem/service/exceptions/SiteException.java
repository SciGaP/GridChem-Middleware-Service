/**
 * 
 */
package org.gridchem.service.exceptions;

/**
 * @author dooley
 *
 */
@SuppressWarnings("serial")
public class SiteException extends Exception {

	/**
	 * 
	 */
	public SiteException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public SiteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public SiteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SiteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
