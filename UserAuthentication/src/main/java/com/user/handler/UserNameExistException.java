package com.user.handler;

import org.springframework.stereotype.Component;

@Component
public class UserNameExistException extends RuntimeException {

	public UserNameExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNameExistException(String message) {
		super(message);
		
	}
}
