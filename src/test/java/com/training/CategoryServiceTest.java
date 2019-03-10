package com.training;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.entities.Category;
import com.training.services_impl.CategoryServiceImpl;

/*
 * Most of unit tests below will be modified later
 *
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CategoryServiceTest {

//	@Autowired
//	CategoryServiceImpl categoryService;
//	
//	private Category category = new Category();
//	List<Category> categories = new ArrayList<>();
//	
//	@Before
//	public void init() {
//		categories.add(new Category("Programming"));
//		categories.add(new Category("Data Science"));
//		categories.add(new Category("Machine Learning"));
//		
//		category.setName("Unit Test");
//	}
//	
//	@Test
//	public void getCategoriesTest() {
//		this.categories.removeAll(this.categories);
//		
//		int expectedResult = this.categories.size();
//		
//		//Assumed there are no data in category table 
//		int actualResult = categoryService.getCategories().size();
//		
//		assertEquals(expectedResult,actualResult);
//	}
//	
//	@Test
//	public void addCategoryTest() {
//		boolean expectedResult = true;
//		boolean actualResult = categoryService.addCategory(new Category("test"));
//		assertEquals(expectedResult, actualResult);
//	}
//	
//	@Test 
//	public void updateCategoryTest() {
//		boolean expectedResult = true;
//		boolean actualResult = categoryService.updateCategory(1,new Category("unit test"));
//		assertEquals(expectedResult, actualResult);
//	}
//	
//	@Test
//	public void deleteCategoryTest() {
//		boolean expectedResult = true;
//		boolean actualResult = categoryService.deleteCategory(1);
//		assertEquals(expectedResult, actualResult);
//	}
//	
//	@After
//	public void destroy() {
//		categories.removeAll(categories);
//		category = null;
//	}
}
