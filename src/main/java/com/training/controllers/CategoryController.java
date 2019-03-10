package com.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "")
	public ResponseEntity<ResponseData> getCategories() {
		return categoryService.getCategories();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseData> getCategoryById(@PathVariable("id")long id) {
		return categoryService.getCategoryById(id);
	}	
	
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> addCategory(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.addCategory(categoryDTO);
	}

	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseData> updateCategory(@PathVariable("id")long id, @RequestBody CategoryDTO categoryDTO) {
		return categoryService.updateCategory(id, categoryDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseData> deleteCategoryById(@PathVariable("id")long id) {
		return categoryService.deleteCategory(id);
	}	
}
