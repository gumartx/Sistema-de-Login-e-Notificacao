package com.uol.compass.api_user.exceptions;

public class PasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PasswordException(String msg) {
		super(msg);
	}
}
