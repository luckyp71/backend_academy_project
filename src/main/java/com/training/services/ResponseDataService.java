package com.training.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.training.models.Meta;
import com.training.models.ResponseData;

public interface ResponseDataService {
	
	public ResponseEntity<ResponseData> responseError(HttpStatus status, Object data, Meta meta);
	
	public ResponseEntity<ResponseData> responseSuccess(Object data);

}
