package com.training;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.training.models.CategoryDTO;
import com.training.services_impl.CategoryServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	CategoryServiceImpl categoryService;
	
	@Test
	public void getCategoriesTest() {
		String expectedResult =	"ok";

		// Assumed we have add some data in category table
		String actualResult = categoryService.getCategories().getBody().getStatus();	
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void addCategoryTest() {
		String expectedResult = "ok";
		
		CategoryDTO category = new CategoryDTO();
		category.setName("Programming");
		
		String actualResult = categoryService.addCategory(category).getBody().getStatus();
		assertEquals(expectedResult, actualResult);
	}
	
}
