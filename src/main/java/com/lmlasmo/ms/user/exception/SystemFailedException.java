package com.lmlasmo.ms.user.exception;

public class SystemFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SystemFailedException(String message) {
		super(message);
	}

}
