package com.xyinc.handlers;

public class CommonError extends Error {

	private static final long serialVersionUID = 1L;
	
	private String path;
	private String message;

	public CommonError(String message) {
		this.message = message;
	}

	public CommonError(String message, String path) {
		this.message = message;
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
}