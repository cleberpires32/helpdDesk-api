package com.cleber.helpDeskapi.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cleber.helpDeskapi.service.exception.ConstraintViolationException;
import com.cleber.helpDeskapi.service.exception.DataIntegrityViolationException;
import com.cleber.helpDeskapi.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourcesExceptionsHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanderError> objectNotFoundExceptions(ObjectNotFoundException ex,
			HttpServletRequest request) {
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Object not found", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StanderError> sqlIntegrityConstraintViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request) {

		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Violação de dados", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StanderError> constraintViolationException(ConstraintViolationException ex,
			HttpServletRequest request){
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Violação de dados durante a persistência", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StanderError> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Validation Error", "Erro na validação dos campos", request.getRequestURI());

		for (FieldError err : ex.getBindingResult().getFieldErrors()) {
			error.addError(err.getField(), err.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
