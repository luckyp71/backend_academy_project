package com.training.services;

import org.springframework.http.ResponseEntity;

import com.training.models.NewsUserDTO;
import com.training.models.ResponseData;

public interface NewsUserService {

	public ResponseEntity<ResponseData> register(NewsUserDTO user);
	
	public ResponseEntity<ResponseData> login(NewsUserDTO user);
	
	public ResponseEntity<ResponseData> logout();
	
	public ResponseEntity<ResponseData> getUserProfile(long id);
	
	public ResponseEntity<ResponseData> getUserByUsername(String username);
}
