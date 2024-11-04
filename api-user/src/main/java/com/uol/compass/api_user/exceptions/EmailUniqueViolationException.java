package com.uol.compass.api_user.exceptions;


public class EmailUniqueViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmailUniqueViolationException(String msg) {
		super(msg);
	}
	
}
