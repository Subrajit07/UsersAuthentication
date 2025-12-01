package com.user.handler;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class MailServiceException extends RuntimeException {

	public MailServiceException(String msg){
		super(msg);
	}
	
	
}
