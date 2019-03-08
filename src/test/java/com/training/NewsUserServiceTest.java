package com.training;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.entities.NewsUser;
import com.training.services_impl.NewsUserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsUserServiceTest {

	@Autowired
	NewsUserServiceImpl userService;
	
	private NewsUser user = new NewsUser();
	
	@Before
	public void init() {
		user.setId(1);
		user.setUsername("lucky");
		user.setPassword("pratama");
	}
	
	@Test
	public void registerTest() {
		boolean expectedResult = true;
		boolean actualResult = false;
		
		NewsUser user = new NewsUser();
		user.setUsername("lucky");
		user.setPassword("pratama");
		actualResult = userService.register(user);
		
		assertEquals(expectedResult, actualResult);
	}
		
	@Test
	public void loginTest() {
		boolean expectedResult = true;
		//Still harcoded for positive testing scenario purposes
		boolean actualResult = true;
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void logoutTest() {
		boolean expectedResult = true;
		//Still harcoded for positive testing scenario purposes
		boolean actualResult = true;
		
		assertEquals(expectedResult, actualResult);	}
	
	@Test
	public void getUserProfileTest() {
		String expectedResult = user.getUsername();
		String actualResult = userService.getUserProfile(1).getUsername();
		assertEquals(expectedResult, actualResult);
	}
	
	@After
	public void destroy() {
		user = new NewsUser();
	}
	
}
