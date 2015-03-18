/**
 * 
 */
package org.gridchem.service.exceptions;

/**
 * @author dooley
 *
 */
@SuppressWarnings("serial")
public class ProviderException extends RuntimeException {

	/**
	 * 
	 */
	public ProviderException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ProviderException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ProviderException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ProviderException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
