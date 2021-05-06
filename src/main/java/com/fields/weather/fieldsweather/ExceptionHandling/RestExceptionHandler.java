package com.fields.weather.fieldsweather.ExceptionHandling;

import org.springframework.core.convert.ConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorInfo> entityNotFoundErrorHandler(EntityNotFoundException e) {
        ErrorInfo body = new ErrorInfo();
		body.setCode(HttpStatus.NOT_FOUND.toString());
		body.setMessage(e.getMessage());
		ResponseEntity<ErrorInfo> responseEntity = new ResponseEntity<ErrorInfo>(body, HttpStatus.NOT_FOUND); 
		return responseEntity;
    }

    @ExceptionHandler(ConversionException.class)
    public ResponseEntity<ErrorInfo> constraintViolationErrorHandler(ConversionException e) {
        ErrorInfo body = new ErrorInfo();
		body.setCode(HttpStatus.BAD_REQUEST.toString());
		body.setMessage(e.getMessage() +
        ". Data Conversion Exception: " + e.getCause());
		ResponseEntity<ErrorInfo> responseEntity = new ResponseEntity<ErrorInfo>(body, HttpStatus.BAD_REQUEST); 
		return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalErrorHandler(Exception e) {
        ErrorInfo body = new ErrorInfo();
		body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		body.setMessage(e.getMessage() + " : " + e.getCause());
		ResponseEntity<ErrorInfo> responseEntity = new ResponseEntity<ErrorInfo>(body, HttpStatus.INTERNAL_SERVER_ERROR); 
		return responseEntity;
        
    }
}
