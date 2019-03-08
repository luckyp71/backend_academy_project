package com.training.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.training.models.Meta;
import com.training.models.ResponseData;
import com.training.services_impl.ResponseDataServiceImpl;

@ControllerAdvice
public class NewsExceptionHandler {

	@Autowired
	ResponseDataServiceImpl responseService;

	private HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
	private HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseData> notFoundException(NotFoundException ex) {
		Meta meta = new Meta(this.notFoundStatus.value(), "error", ex.getMessage());
		return responseService.responseError(HttpStatus.NOT_FOUND, null, meta);
	}
	
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<ResponseData> duplicateException(DuplicateException ex) {
		Meta meta = new Meta(this.badRequestStatus.value(), "error", ex.getMessage());
		return responseService.responseError(HttpStatus.BAD_REQUEST, null, meta);
	}
}
