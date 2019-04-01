package com.xyinc.handlers;

public class AlreadyExistsError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyExistsError(String message) {
		super(message);
	}

	public AlreadyExistsError(String message, Throwable cause) {
		super(message, cause);
	}
}
