package com.auth.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	 @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
	        return ResponseEntity.status(404).body(ex.getMessage());
	    }
	    
	    @ExceptionHandler(UserAlreadyExistException.class)
	    public ResponseEntity<String> handleUserAlreadyExistFound(UserAlreadyExistException ex) {
	        return ResponseEntity.status(200).body(ex.getMessage());
	    }

}
