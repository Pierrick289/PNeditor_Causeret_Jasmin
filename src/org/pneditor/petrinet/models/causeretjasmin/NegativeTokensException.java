package org.pneditor.petrinet.models.causeretjasmin;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public class NegativeTokensException extends UnsupportedOperationException {
	
	/*
	 * extends UnsupportedOperationException because it allows us to not consider try catch every time then
	 */

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with an argument
	 * 
	 * @param s String
	 */
	public NegativeTokensException(String s) {
		super(s);
	}
}
