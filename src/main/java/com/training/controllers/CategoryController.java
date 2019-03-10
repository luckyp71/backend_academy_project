package com.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.models.CategoryDTO;
import com.training.models.ResponseData;
import com.training.services_impl.CategoryServiceImpl;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryServiceImpl categoryService;
	
	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> registerUser(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.addCategory(categoryDTO);
	}
	
}
