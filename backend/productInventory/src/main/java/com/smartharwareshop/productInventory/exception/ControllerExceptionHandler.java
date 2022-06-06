package com.smartharwareshop.productInventory.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@CrossOrigin(origins = "*")
	@ExceptionHandler(value	= {
			InvalidQuantityException.class,
      InvalidProductDescriptionException.class,
      InvalidSearchRegex.class,
			ProductNotFoundInCartException.class})
	protected ResponseEntity<Object> handleConflict(
			RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();

		ResponseEntity<Object> responseObj = handleExceptionInternal(ex, bodyOfResponse,
				new HttpHeaders(), HttpStatus.FORBIDDEN, request);
		if (ex.getMessage().contains("Invalid")) {
			responseObj = handleExceptionInternal(ex, bodyOfResponse,
					new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		} else if (ex.getMessage().contains("product not found")) {
      responseObj = handleExceptionInternal(ex, bodyOfResponse,
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
		return responseObj;
	}
}
