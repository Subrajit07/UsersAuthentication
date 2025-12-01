package com.user.handler;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class PasswordMismatchException extends RuntimeException {

	public PasswordMismatchException(String msg) {
		super(msg);
	}

	
}
