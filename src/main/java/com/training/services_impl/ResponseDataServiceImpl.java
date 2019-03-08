package com.training.services_impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.models.Meta;
import com.training.models.ResponseData;
import com.training.services.ResponseDataService;

@Service
public class ResponseDataServiceImpl implements ResponseDataService{
	
	@Override
	public ResponseEntity<ResponseData> responseError(HttpStatus status, Object data, Meta meta) {
		return ResponseEntity.status(status).body(
				new ResponseData(status.getReasonPhrase().toLowerCase(), 
						data, meta));
	}

	@Override
	public ResponseEntity<ResponseData> responseSuccess(Object data) {
		Meta meta = new Meta();
		return ResponseEntity.status(HttpStatus.OK).
				body(new ResponseData(HttpStatus.OK.getReasonPhrase().toLowerCase()
						,data, meta));
	}

}
