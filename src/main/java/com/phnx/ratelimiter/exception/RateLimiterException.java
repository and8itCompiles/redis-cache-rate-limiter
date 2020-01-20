package com.phnx.ratelimiter.exception;

public class RateLimiterException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8400937395945854302L;
	
	/** The error msg. */
	private final String errorMsg;
	
	/**
	 * Instantiates a new document db exception.
	 *
	 * @param code the code
	 */
	public RateLimiterException(String errorMsg) {
	    this.errorMsg = errorMsg;
	  }

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public String getErrorMsg() {
	    return errorMsg;
	  }

}
