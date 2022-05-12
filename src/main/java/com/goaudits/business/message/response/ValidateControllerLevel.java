package com.goaudits.business.message.response;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
class ValidateControllerLevel {

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		String segments[] = e.getMessage().split(": ");
		return new ResponseEntity<>
    (
    		
    		new com.goaudits.business.util.GoAuditsException(segments[segments.length - 1]),
			HttpStatus.CONFLICT);
  }

}