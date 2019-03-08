package com.training.services_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entities.Category;
import com.training.models.CategoryDTO;
import com.training.repositories.CategoryRepo;
import com.training.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public List<Category> getCategories() {
//		return categoryRepo.findAll();
		return categoryRepo.findAll();
	}

	@Override
	public boolean addCategory(Category category) {
		return true;
	}

	@Override
	public boolean updateCategory(long id, Category category) {
		return true;
	}

	@Override
	public boolean deleteCategory(long id) {
		return true;
	}

}
