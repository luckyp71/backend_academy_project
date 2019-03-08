package com.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.entities.NewsUser;
import com.training.exceptions.DuplicateException;
import com.training.exceptions.NewsExceptionHandler;
import com.training.exceptions.NotFoundException;
import com.training.models.NewsUserDTO;
import com.training.models.ResponseData;
import com.training.services_impl.NewsUserServiceImpl;
import com.training.services_impl.ResponseDataServiceImpl;

@RestController
@RequestMapping("/news")
public class NewsUserController {

	@Autowired
	NewsUserServiceImpl userService;

	@Autowired
	ResponseDataServiceImpl responseService;

	@Autowired
	NewsExceptionHandler newsException;

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<ResponseData> getUserById(@PathVariable("id") long id) {
		try {
			NewsUser user = userService.getUserProfile(id);
			if (user != null)
				return responseService.responseSuccess(user);
			throw new NotFoundException("data not found");
		} catch (NotFoundException ex) {
			return newsException.notFoundException(ex);
		}
	}

	@GetMapping(value = "/users/username/{username}")
	public ResponseEntity<ResponseData> getUserByUsername(@PathVariable("username") String username) {
		try {
			NewsUser user = userService.getUserByUsername(username);
			if (user != null)
				return responseService.responseSuccess(user);
			throw new NotFoundException("data not found");
		} catch (NotFoundException ex) {
			return newsException.notFoundException(ex);
		}
	}

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> registerUser(@RequestBody NewsUserDTO userDTO) {
		try {
			NewsUser persistedUser = userService.getUserByUsername(userDTO.getUsername());
			if (persistedUser != null)
				throw new DuplicateException("data already exists");
			NewsUser user = new NewsUser();
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			userService.register(user);
			return responseService.responseSuccess(userDTO);
		} catch (DuplicateException ex) {
			return newsException.duplicateException(ex);
		}
	}

}
