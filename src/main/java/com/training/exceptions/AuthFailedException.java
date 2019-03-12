package com.training.exceptions;

public class AuthFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//Default constructor
	public AuthFailedException() {
		super("authentication failed");
	}

	public AuthFailedException(String message) {
		super(message);
	}

	public AuthFailedException(Throwable cause) {
		super(cause);
	}

	public AuthFailedException(String message, Throwable cause) {
		super(message, cause);
	}

}
