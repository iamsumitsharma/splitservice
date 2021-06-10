package com.split.splitservice.exception;

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String message;
	private String resolution;


	protected CustomException() {}

	public CustomException(
			String errorCode, String message, String resolution) {
		 super(message);
		this.message = message;

	}
}
