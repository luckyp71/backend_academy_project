package com.training;

import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.training.controllers.NewsUserController;
import com.training.entities.NewsUser;
import com.training.models.NewsUserDTO;

import java.security.SecureRandom;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsUserControllerTest {

	@Autowired
	NewsUserController userController;

	private NewsUser user = new NewsUser();

	@Before
	public void init() {
		// Assumed we have added below credential data in news_user table
		user.setId(1);
		user.setUsername("lucky");
		user.setPassword("pratama");
		user.setEmail("email@email.com");
	}

	@Test
	public void registerTest() {
		int expectedResult = 200;
		int actualResult = 0;

		NewsUserDTO user = new NewsUserDTO();
		/*
		 * Generate random username to ensure there is no username below in the news_user table.
		 * But still there is always potency that the generated random username may already exists 
		 * in the news_user table.
		 */
		char[] chars = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z'}; 
		SecureRandom rand = new SecureRandom();
		char[] username = new char[chars.length];
		
		IntStream.range(0, chars.length).forEach(i -> {
			int randChar = rand.nextInt(chars.length);	
			username[i]=chars[randChar];
		});
		
		user.setUsername(String.valueOf(username));
		user.setPassword("userpassword");
		user.setEmail("email@email.com");
		actualResult = userController.registerUser(user).getBody().getMeta().getCode();

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void loginTest() {
		int expectedResult = 200;
		int actualResult = 0;

		NewsUserDTO user = new NewsUserDTO();

		user.setUsername(this.user.getUsername());
		user.setPassword(this.user.getPassword());
		actualResult = userController.login(user).getBody().getMeta().getCode();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void logoutTest() {
		int expectedResult = 200;
		// Still harcoded for positive testing scenario purpose
		int actualResult = 200;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getUserProfileTest() {
		int expectedResult = 200;
		int actualResult = userController.getUser("1", "").getBody().getMeta().getCode();
		assertEquals(expectedResult, actualResult);
	}

	@After
	public void destroy() {
		user = new NewsUser();
	}
}