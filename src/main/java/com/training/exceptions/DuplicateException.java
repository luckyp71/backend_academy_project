package com.training.exceptions;

public class DuplicateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//Default constructor
	public DuplicateException() {}

	public DuplicateException(String message) {
		super(message);
	}

	public DuplicateException(Throwable cause) {
		super(cause);
	}

	public DuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

}
