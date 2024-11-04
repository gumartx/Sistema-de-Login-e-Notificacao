package com.uol.compass.api_user.controllers.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uol.compass.api_user.exceptions.EmailUniqueViolationException;
import com.uol.compass.api_user.exceptions.PasswordException;
import com.uol.compass.api_user.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> accessDenied(AccessDeniedException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		ErrorMessage err = new ErrorMessage(request, status, e.getMessage());
		
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(err);
	}
	
	@ExceptionHandler(PasswordException.class)
	public ResponseEntity<ErrorMessage> password(RuntimeException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorMessage err = new ErrorMessage(request, status, e.getMessage());
		
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(err);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFound(RuntimeException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorMessage err = new ErrorMessage(request, status, e.getMessage());
		
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(err);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class, EmailUniqueViolationException.class})
	public ResponseEntity<ErrorMessage> uniqueViolation(RuntimeException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ErrorMessage err = new ErrorMessage(request, status, e.getMessage());
		
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request, BindingResult result) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ErrorMessage err = new ErrorMessage(request, status, "Invalid field(s)", result);
		
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(err);
	}
}
