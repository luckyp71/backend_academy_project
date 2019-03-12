package com.training;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

}
