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
	public ResponseEntity<ResponseData> notFoundException() {
		NotFoundException notFound = new NotFoundException("data not found");
		Meta meta = new Meta(this.notFoundStatus.value(), "error -> data not found", notFound.getMessage());
		return responseService.responseError(HttpStatus.NOT_FOUND, null, meta);
	}
	
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<ResponseData> duplicateException() {
		DuplicateException duplicate = new DuplicateException("data already exists");
		Meta meta = new Meta(this.badRequestStatus.value(), "error -> data already exists", duplicate.getMessage());
		return responseService.responseError(HttpStatus.BAD_REQUEST, null, meta);
	}
	
	@ExceptionHandler(AuthFailedException.class)
	public ResponseEntity<ResponseData> authFailedException() {
		AuthFailedException authFailed = new AuthFailedException("authentication failed");
		Meta meta = new Meta(this.badRequestStatus.value(), "error -> authentication failed", authFailed.getMessage());
		return responseService.responseError(HttpStatus.BAD_REQUEST, null, meta);
	}
}
