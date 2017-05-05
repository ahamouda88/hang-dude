package com.hangdude.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hangdude.model.api.ErrorResponse;

/**
 * This controller handles the global exceptions thrown from the application
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> runTimeExceptionHandler(RuntimeException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(status.value(), ex.getMessage());
		return new ResponseEntity<>(error, status);
	}
}