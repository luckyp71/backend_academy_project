package com.training.services_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.entities.Category;
import com.training.exceptions.DuplicateException;
import com.training.exceptions.NewsExceptionHandler;
import com.training.exceptions.NotFoundException;
import com.training.models.CategoryDTO;
import com.training.models.ResponseData;
import com.training.repositories.CategoryRepo;
import com.training.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ResponseDataServiceImpl responseService;

	@Autowired
	NewsExceptionHandler newsException;

	@Override
	public ResponseEntity<ResponseData> getCategories() {
		return responseService.responseSuccess(categoryRepo.findAll());
	}

	@Override
	public ResponseEntity<ResponseData> getCategoryId(long id) {
		try {
			Category existingCategory = categoryRepo.findById(id).orElse(null);
			if (existingCategory == null) {
				throw new NotFoundException();
			}
			return responseService.responseSuccess(existingCategory);
		} catch (NotFoundException ne) {
			return newsException.notFoundException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> addCategory(CategoryDTO categoryDTO) {
		try {
			Category existingCategory = categoryRepo.findByName(categoryDTO.getName()).orElse(null);
			if (existingCategory != null) {
				throw new DuplicateException();
			}
			Category category = new Category();
			category.setName(categoryDTO.getName());
			categoryRepo.save(category);
			return responseService.responseSuccess(categoryDTO);
		} catch (DuplicateException ex) {
			return newsException.duplicateException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> updateCategory(long id, CategoryDTO categoryDTO) {
		try {
			Category existingCategory = categoryRepo.findById(id).orElse(null);
			if (existingCategory == null) {
				throw new NotFoundException();
			}
			Category category = new Category();
			category.setName(categoryDTO.getName());
			return responseService.responseSuccess(categoryDTO);
		} catch (NotFoundException ne) {
			return newsException.notFoundException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> deleteCategory(long id) {
		try {
			Category existingCategory = categoryRepo.findById(id).orElse(null);
			if (existingCategory == null) {
				throw new NotFoundException();
			}
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryRepo.delete(existingCategory);
			return responseService.responseSuccess(categoryDTO);
		} catch (NotFoundException ne) {
			return newsException.notFoundException();
		}
	}

}
