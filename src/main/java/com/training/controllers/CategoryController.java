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

import com.training.exceptions.DuplicateException;
import com.training.exceptions.NewsExceptionHandler;
import com.training.exceptions.NotFoundException;
import com.training.models.CategoryDTO;
import com.training.models.ResponseData;
import com.training.services_impl.CategoryServiceImpl;
import com.training.services_impl.ResponseDataServiceImpl;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryServiceImpl categoryService;

	@Autowired
	ResponseDataServiceImpl responseService;

	@Autowired
	NewsExceptionHandler newsException;

	@GetMapping(value = "")
	public ResponseEntity<ResponseData> getCategories() {
		return responseService.responseSuccess(categoryService.getCategories());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseData> getCategoryById(@PathVariable("id") long id) {
		try {
			if (categoryService.getCategoryById(id) == null)
				throw new NotFoundException();
			return responseService.responseSuccess(categoryService.getCategoryById(id));
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		}
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> addCategory(@RequestBody CategoryDTO categoryDTO) {
		try {
			if (!categoryService.addCategory(categoryDTO))
				throw new DuplicateException("sorry.. category already exists");
			return responseService.responseSuccess(categoryDTO);
		} catch (DuplicateException ex) {
			return newsException.duplicateException(ex);
		}
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> updateCategory(@PathVariable("id") long id,
			@RequestBody CategoryDTO categoryDTO) {
		try {
			if (!categoryService.updateCategory(id, categoryDTO))
				throw new NotFoundException();
			return responseService.responseSuccess(categoryDTO);
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseData> deleteCategoryById(@PathVariable("id") long id) {
		try {
			if(!categoryService.deleteCategory(id))
				throw new NotFoundException();
			return responseService.responseSuccess(null);
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		}
	}
}
