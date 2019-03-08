package com.training.services;

import java.util.List;

import com.training.entities.Category;
import com.training.models.CategoryDTO;

public interface CategoryService {
	
	public List<Category> getCategories();
	
	public boolean addCategory(Category category);
	
	public boolean updateCategory(long id, Category category);
	
	public boolean deleteCategory(long id);

}
