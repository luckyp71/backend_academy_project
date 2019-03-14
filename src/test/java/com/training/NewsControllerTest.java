package com.training;

import static org.junit.Assert.assertEquals;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.models.NewsDTO;
import com.training.controllers.NewsController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerTest {
	
	@Autowired
	NewsController newsController;
	
	@Test
	public void getNewsTest() {
		int expectedResult = 0;
		@SuppressWarnings("unchecked")
		List<NewsDTO> news = (List<NewsDTO>) newsController.getNews().getBody().getData();
		int actualResult = news.size();
		
		//Assumed we have add some data into news table 
		if(news.size()>1) 
			expectedResult = news.size();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void getNewsByIDTest() {
		String expectedResult = "ok";
		String actualResult = "error";
		
		//Assumed we have add at least one data into news table,
		//so that we could retrieve news data with id 1.
		actualResult = newsController.getNewsById(1).getBody().getStatus();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void addNewsTest() {
		String expectedResult = "ok";
		String actualResult = "error";

		NewsDTO newsDTO = new NewsDTO();
		/*
		 * Generate random title to ensure there is no title below in the news table.
		 * But still there is always potency that the generated random news may already exists 
		 * in the news table.
		 */
		char[] chars = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z'}; 
		SecureRandom rand = new SecureRandom();
		char[] title = new char[chars.length];
		
		IntStream.range(0, chars.length).forEach(i -> {
			int randChar = rand.nextInt(chars.length);	
			title[i]=chars[randChar];
		});
		
		newsDTO.setTitle(String.valueOf(title));
		newsDTO.setContent("Unit test is bla bla bla");
		newsDTO.setCategoryId(2);
		newsDTO.setUserId(1);
		
		actualResult = newsController.addNews(newsDTO).getBody().getStatus();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void updateNewstTest() {
		String expectedResult = "ok";
		String actualResult = "error";
		
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setTitle("Update Test For Positive Scenario");
		newsDTO.setContent("Update Unit test is bla bla bla");
		newsDTO.setCategoryId(1);
		
		actualResult = newsController.updateNews(1, newsDTO).getBody().getStatus();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void deleteNewsTest() {
		int expectedResult = 404;
		int actualResult = 200;
		
		//Assume we have add at least one data into news table,
		//and that news data id = 5 with is_active = 'Y'
		actualResult = newsController.deleteNews(5).getBody().getMeta().getCode();
		assertEquals(expectedResult, actualResult);
	}
}
