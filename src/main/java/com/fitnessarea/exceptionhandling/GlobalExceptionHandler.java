package com.fitnessarea.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundExcption.class)
	public ResponseEntity<String> resourceNotFoundException (ResourceNotFoundExcption ex) {
		String message = ex.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
	}
	

}
