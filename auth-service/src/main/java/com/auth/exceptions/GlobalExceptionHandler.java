package com.auth.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

   
    
    @ExceptionHandler(AuthorizationDeniedException.class) //Exception handler for un authorized requests
    public ResponseEntity<String> handleAuthorizationDenideException(Exception ex) {
    	System.err.println(ex.getClass().toString());
        return ResponseEntity.status(403).body(ex.getMessage());
        		
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
    	System.err.println(ex.getClass().toString());
        return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
 
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
 
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
