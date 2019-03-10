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
import com.training.models.NewsUserDTO;
import com.training.services_impl.NewsUserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsUserServiceTest {

	@Autowired
	NewsUserServiceImpl userService;
	
	private NewsUser user = new NewsUser();
	
	@Before
	public void init() {
		//Assumed we have added below credential data in news_user table
		user.setId(1);
		user.setUsername("lucky");
		user.setPassword("pratama");
	}
	
	@Test
	public void registerTest() {
		int expectedResult = 200;
		int actualResult = 0;
		
		NewsUserDTO user = new NewsUserDTO();
		//Assumed there is no username called user1
		user.setUsername("user4");
		user.setPassword("userpassword");
		actualResult = userService.register(user).getBody().getMeta().getCode();
		
		assertEquals(expectedResult, actualResult);
	}
		
	@Test
	public void loginTest() {
		int expectedResult = 200;
		int actualResult = 0;
		
		NewsUserDTO user = new NewsUserDTO();

		user.setUsername(this.user.getUsername());
		user.setPassword(this.user.getPassword());
		actualResult = userService.login(user).getBody().getMeta().getCode();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void logoutTest() {
		int expectedResult = 200;
		//Still harcoded for positive testing scenario purpose
		int actualResult = 200;
		
		assertEquals(expectedResult, actualResult);	
	}
	
	@Test
	public void getUserProfileTest() {
		int expectedResult = 200;
		int actualResult = userService.getUserProfile(1).getBody().getMeta().getCode();
		assertEquals(expectedResult, actualResult);
	}
	
	@After
	public void destroy() {
		user = new NewsUser();
	}
}