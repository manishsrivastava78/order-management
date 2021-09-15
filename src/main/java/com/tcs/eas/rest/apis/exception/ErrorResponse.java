package com.tcs.eas.rest.apis.exception;

import java.util.Date;

/**
 * 
 * @author 44745
 *
 */
public class ErrorResponse {
	/**
	 * 
	 */
	private Date timestamp;
	
	/**
	 * 
	 */
	
	private String message;
	/**
	 * 
	 */
	private String details;
	
	/**
	 * 
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ErrorResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	
}
