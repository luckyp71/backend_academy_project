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

import com.training.exceptions.AuthFailedException;
import com.training.exceptions.DuplicateException;
import com.training.exceptions.NewsExceptionHandler;
import com.training.exceptions.NotFoundException;
import com.training.models.NewsUserDTO;
import com.training.models.ResponseData;
import com.training.services_impl.NewsUserServiceImpl;
import com.training.services_impl.ResponseDataServiceImpl;

@RestController
@RequestMapping("/users")
public class NewsUserController {

	@Autowired
	NewsUserServiceImpl userService;

	@Autowired
	ResponseDataServiceImpl responseService;

	@Autowired
	NewsExceptionHandler newsException;

	@GetMapping(value = "")
	public ResponseEntity<ResponseData> getUser(@RequestParam(defaultValue = "", required = false) String id,
			@RequestParam(defaultValue = "", required = false) String username) {
		try {
			NewsUserDTO user;
			if (!username.isEmpty()) {
				user = userService.getUserByUsername(username);
				if (user == null)
					throw new NotFoundException("sory.... user not found");
				
				return responseService.responseSuccess(user);
			}
			user = userService.getUserProfile(Long.parseLong(id));
			return responseService.responseSuccess(user);
		} catch (NotFoundException ex) {
			return newsException.notFoundException(ex);
		}
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> registerUser(@RequestBody NewsUserDTO userDTO) {
		try {
			if (userService.register(userDTO) == null)
				throw new DuplicateException();

			return responseService.responseSuccess(userDTO);
		} catch (DuplicateException ex) {
			return newsException.duplicateException(ex);
		}

	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> login(@RequestBody NewsUserDTO userDTO) {
		try {
			char status = userService.login(userDTO);
			if (status == 'n')
				throw new NotFoundException();
			else if (status == 'a')
				throw new AuthFailedException();
			return responseService.responseSuccess(null);
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		} catch (AuthFailedException ae) {
			return newsException.authFailedException(ae);
		}
	}
}
