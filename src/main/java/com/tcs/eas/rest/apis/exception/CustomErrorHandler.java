package com.tcs.eas.rest.apis.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author 44745
 *
 */
@ControllerAdvice
@RestController
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), "Internal Error", "Internal Error");
		return new ResponseEntity(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(CustomerNotFound.class)
	public ResponseEntity<Object> customerNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<Object> customerNotFoundException1(Exception ex, WebRequest request) throws Exception {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), "Bad request", request.getDescription(false));
		return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> errors = bindingResult.getAllErrors();
		String errorMessage = errors.size()>0 ? errors.get(0).getDefaultMessage(): "error message not found";
		ErrorResponse errorResponse = new ErrorResponse(new Date(), "Input validation failed",errorMessage);
		return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
	}

}
