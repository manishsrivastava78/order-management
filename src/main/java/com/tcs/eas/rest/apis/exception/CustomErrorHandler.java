package com.tcs.eas.rest.apis.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.tcs.eas.rest.apis.Constants;
import com.tcs.eas.rest.apis.utility.Utility;

/**
 * 
 * @author 44745
 *
 */
@ControllerAdvice
@RestController
public class CustomErrorHandler extends ResponseEntityExceptionHandler implements Constants {

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		return getResponseEntity(request, HttpStatus.INTERNAL_SERVER_ERROR, ex,null);
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
		return getResponseEntity(request, HttpStatus.NOT_FOUND, ex,null);
	}

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<Object> customerNotFoundException1(Exception ex, WebRequest request) throws Exception {
		return getResponseEntity(request, HttpStatus.BAD_REQUEST, ex,null);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> errors = bindingResult.getAllErrors();
		String errorMessage = errors.size()>0 ? errors.get(0).getDefaultMessage(): "error message not found";
		return getResponseEntity(request, HttpStatus.BAD_REQUEST, ex,errorMessage);
	}

	/**
	 * 
	 * @param request
	 * @param httpStatus
	 * @param ex
	 * @return
	 */
	private ResponseEntity<Object> getResponseEntity(WebRequest request, HttpStatus httpStatus, Exception ex,String customErrorMessage) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(TRANSACTION_ID, request.getHeader(TRANSACTION_ID));
		headers.put(CORRELATION_ID, request.getHeader(CORRELATION_ID));
		ErrorResponse errorResponse = new ErrorResponse(new Date(), (customErrorMessage == null ? ex.getMessage():customErrorMessage), request.getDescription(false));
		return new ResponseEntity<Object>(errorResponse, Utility.getCustomResponseHeaders(headers), httpStatus);
	}
}
