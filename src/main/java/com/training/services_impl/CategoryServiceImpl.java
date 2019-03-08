package com.training.services_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.entities.Category;
import com.training.repositories.CategoryRepo;
import com.training.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public boolean addCategory() {
		return true;
	}

	@Override
	public boolean updateCategory(long id) {
		return true;
	}

	@Override
	public boolean deleteCategory(long id) {
		return true;
	}

}
