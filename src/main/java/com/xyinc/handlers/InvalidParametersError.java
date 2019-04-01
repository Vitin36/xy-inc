package com.xyinc.handlers;

public class InvalidParametersError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String campo;

	public InvalidParametersError(String message) {
		super(message);
	}
	
	public InvalidParametersError(String message,String campo) {
		super(message);
		this.campo = campo;
	}

	public InvalidParametersError(String message, Throwable cause) {
		super(message, cause);
	}

	public String getCampo() {
		return campo;
	}
	
}
