package com.user.handler;

import org.springframework.stereotype.Component;

@Component
public class BadRequestException extends RuntimeException {

	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException() {
		super();
	}
	
	

	
}
