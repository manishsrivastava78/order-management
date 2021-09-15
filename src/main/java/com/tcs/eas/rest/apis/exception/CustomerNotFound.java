package com.tcs.eas.rest.apis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author 44745
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND,reason="Customer does not exist",value=HttpStatus.NOT_FOUND)
public class CustomerNotFound extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6177494240148170519L;

	/**
	 * 
	 * @param message
	 */
	public CustomerNotFound(String message) {
		super(message);
	}
}
