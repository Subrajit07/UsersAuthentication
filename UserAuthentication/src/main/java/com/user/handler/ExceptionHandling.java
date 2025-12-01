package com.user.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(UserNameExistException.class)
	@ResponseStatus(HttpStatus.FOUND)
	public Map<String, String> handleUserNameExistexception(UserNameExistException exception) {
		Map<String, String> exp = new HashMap<>();
		exp.put("error", exception.getMessage());
		exp.put("status", HttpStatus.FOUND.toString());
		return exp;
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handlePasswordMismatchedException(PasswordMismatchException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequestException(BadRequestException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(MailServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMailServiceException(MailServiceException exception) {
		return exception.getMessage();
	}
}
