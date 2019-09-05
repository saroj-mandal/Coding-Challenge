package com.github.sarojmandal.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler()
	public final ResponseEntity<ErrorResponse> handleInvalidRequestBody(InvalidRequestException ex,
			WebRequest webRequest) {
		ErrorResponse errResp = new ErrorResponse(LocalDateTime.now(), ex.getMessage(),
				webRequest.getDescription(true));
		return new ResponseEntity<>(errResp, HttpStatus.BAD_REQUEST);
	}
}
