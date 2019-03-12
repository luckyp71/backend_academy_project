package com.training.services;

import java.util.List;
import com.training.models.CategoryDTO;


public interface CategoryService {
	
	public List<CategoryDTO> getCategories();
	
	public CategoryDTO getCategoryById(long id);
	
	public boolean addCategory(CategoryDTO category);
	
	public boolean updateCategory(long id, CategoryDTO category);
	
	public boolean deleteCategory(long id);

}
