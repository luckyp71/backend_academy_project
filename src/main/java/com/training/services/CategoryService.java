package com.training.services;

import java.util.List;

import com.training.entities.Category;

public interface CategoryService {
	
	public List<Category> getCategories();
	
	public boolean addCategory();
	
	public boolean updateCategory(long id);
	
	public boolean deleteCategory(long id);

}
