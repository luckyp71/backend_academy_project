package com.training.exceptions;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//Default constructor
	public NotFoundException() {}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
