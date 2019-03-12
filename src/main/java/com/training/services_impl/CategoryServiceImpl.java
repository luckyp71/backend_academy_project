package com.training.services_impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entities.Category;
import com.training.exceptions.DuplicateException;
import com.training.exceptions.NotFoundException;
import com.training.models.CategoryDTO;
import com.training.repositories.CategoryRepo;
import com.training.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public List<CategoryDTO> getCategories() {
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		List<Category> categories = categoryRepo.findAll().stream().filter(i -> i.getIsActive() == 'Y').map(c -> c)
				.collect(Collectors.toList());

		categories.stream().forEach(i -> {
			CategoryDTO cDTO = new CategoryDTO();
			cDTO.setName(i.getName());
			categoriesDTO.add(cDTO);
		});
		return categoriesDTO;
	}

	@Override
	public CategoryDTO getCategoryById(long id) {
			Category existingCategory = categoryRepo.findById(id).orElse(null);
			if (existingCategory == null || existingCategory.getIsActive() == 'N') 
				return null;
			
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setName(existingCategory.getName());
			return categoryDTO;
		}

	@Override
	public boolean addCategory(CategoryDTO categoryDTO) {
		try {
			Category existingCategory = categoryRepo.findByName(categoryDTO.getName()).orElse(null);
			if (existingCategory != null && existingCategory.getIsActive() == 'Y') {
				throw new DuplicateException();
			}

			Category category = new Category();
			category.setName(categoryDTO.getName());
			categoryRepo.save(category);
			return true;
		} catch (DuplicateException ex) {
			return false;
		}
	}

	@Override
	public boolean updateCategory(long id, CategoryDTO categoryDTO) {
		try {
			Category existingCategory = categoryRepo.findById(id).orElse(null);
			if (existingCategory == null || existingCategory.getIsActive() == 'N') 
				throw new NotFoundException();
			
			existingCategory.setName(categoryDTO.getName());
			categoryRepo.save(existingCategory);
			return true;
		} catch (NotFoundException ne) {
			return false;
		}
	}

	@Override
	public boolean deleteCategory(long id) {
		try {
			Category existingCategory = categoryRepo.findById(id).orElse(null);
			if (existingCategory == null || existingCategory.getIsActive() == 'N')
				throw new NotFoundException();
			
			existingCategory.setIsActive('N');
			categoryRepo.save(existingCategory);
			return true;
		} catch (NotFoundException ne) {
			return false;
		}
	}
}
