package com.training.services;

import org.springframework.http.ResponseEntity;

import com.training.models.CategoryDTO;
import com.training.models.ResponseData;

public interface CategoryService {
	
	public ResponseEntity<ResponseData> getCategories();
	
	public ResponseEntity<ResponseData> getCategoryId(long id);
	
	public ResponseEntity<ResponseData> addCategory(CategoryDTO category);
	
	public ResponseEntity<ResponseData> updateCategory(long id, CategoryDTO category);
	
	public ResponseEntity<ResponseData> deleteCategory(long id);

}
