package com.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.models.NewsUserDTO;
import com.training.models.ResponseData;
import com.training.services_impl.NewsUserServiceImpl;

@RestController
@RequestMapping("/users")
public class NewsUserController {

	@Autowired
	NewsUserServiceImpl userService;

	@GetMapping(value = "")
	public ResponseEntity<ResponseData> getUser(@RequestParam(defaultValue="",required=false) String id,
			@RequestParam(defaultValue="",required=false) String username) {
		if (!username.isEmpty()) {
			return userService.getUserByUsername(username);			
		}
		return userService.getUserProfile(Long.parseLong(id));
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> registerUser(@RequestBody NewsUserDTO userDTO) {
		return userService.register(userDTO);
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> login(@RequestBody NewsUserDTO userDTO) {
		return userService.login(userDTO);
	}
}
