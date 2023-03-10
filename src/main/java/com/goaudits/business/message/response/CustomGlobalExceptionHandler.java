package com.goaudits.business.message.response;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

     	String error=ex.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage())
    			.collect(java.util.stream.Collectors.joining("---- "));

    			        return new  ResponseEntity<>(
    							new com.goaudits.business.util.GoAuditsException(
    									error.split("----")[0]),
    							HttpStatus.CONFLICT);
    			        
    			        
    			    }


}