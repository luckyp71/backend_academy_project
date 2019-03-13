package com.training;

import static org.junit.Assert.assertEquals;

import java.security.SecureRandom;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.controllers.CategoryController;
import com.training.models.CategoryDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {

	@Autowired
	CategoryController categoryController;
	
	@Test
	public void getCategoriesTest() {
		String expectedResult =	"ok";

		// Assumed we have add some data in category table
		String actualResult = categoryController.getCategories().getBody().getStatus();	
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void getCategoryById() {
		int expectedResult = 200;
		int actualResult = 404;
		
		actualResult = categoryController.getCategoryById(1).getBody().getMeta().getCode();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void addCategoryTest() {
		String expectedResult = "ok";
		
		CategoryDTO category = new CategoryDTO();
		/*
		 * Generate random category_name to ensure there is no category_name below in the category table.
		 * But still there is always potency that the generated random category_name may already exists 
		 * in the category table.
		 */
		char[] chars = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z'}; 
		SecureRandom rand = new SecureRandom();
		char[] categoryName = new char[chars.length];
		
		IntStream.range(0, chars.length).forEach(i -> {
			int randChar = rand.nextInt(chars.length);	
			categoryName[i]=chars[randChar];
		});
		
		category.setName(String.valueOf(categoryName));
		
		String actualResult = categoryController.addCategory(category).getBody().getStatus();
		assertEquals(expectedResult, actualResult);
	}
	
}
