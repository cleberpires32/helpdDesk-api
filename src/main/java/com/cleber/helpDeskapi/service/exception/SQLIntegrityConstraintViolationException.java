package com.cleber.helpDeskapi.service.exception;

public class SQLIntegrityConstraintViolationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SQLIntegrityConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SQLIntegrityConstraintViolationException(String message) {
		super(message);
	}

	
}
