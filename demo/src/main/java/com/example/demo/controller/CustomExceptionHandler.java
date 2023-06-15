package com.example.demo.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.errors.ExceptionResponse;
import com.example.demo.errors.ResourceNotFound;
import com.example.demo.errors.ResourceUnavalaible;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
				                                                    ex.getMessage(),
				                                                    request.getDescription(false));
	return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFound ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                                                                    ex.getMessage(),
                                                                    request.getDescription(false));
		
		return handleExceptionInternal(ex,exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(ResourceUnavalaible.class)
	public final ResponseEntity<Object> handleResourceUnavalaibleException(ResourceUnavalaible ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                                                                    ex.getMessage(),
                                                                    request.getDescription(false));
		
		return handleExceptionInternal(ex,exceptionResponse, new HttpHeaders(), HttpStatus.GONE, request);
	}
}
