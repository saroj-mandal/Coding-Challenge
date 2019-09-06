package com.github.sarojmandal.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is for handling application exception
 * 
 * @author Saroj Mandal
 *
 */
@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method is for handling invalid request body
	 * 
	 * @param ex
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(InvalidRequestException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidRequestBody(InvalidRequestException ex,
			WebRequest webRequest) {
		ErrorResponse errResp = new ErrorResponse(LocalDateTime.now(), ex.getMessage(),
				webRequest.getDescription(true));
		return new ResponseEntity<>(errResp, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is for handling resource not found exception
	 * 
	 * @param ex
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest webRequest) {
		ErrorResponse errResp = new ErrorResponse(LocalDateTime.now(), ex.getMessage(),
				webRequest.getDescription(true));
		return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
	}
}
