package com.qk.usermgt.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qk.usermgt.response.Response;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(String message) {
		Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
		return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<Response> handleUserException(RuntimeException runTimeException) {
		Response response = new Response(HttpStatus.BAD_REQUEST.value(), runTimeException.getMessage(), null);
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}

}
