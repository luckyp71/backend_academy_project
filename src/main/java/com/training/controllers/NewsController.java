package com.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entities.Category;
import com.training.entities.NewsUser;
import com.training.models.CategoryDTO;
import com.training.models.NewsDTO;
import com.training.models.NewsUserDTO;
import com.training.models.ResponseData;
import com.training.services_impl.NewsServiceImpl;

@RestController
public class NewsController {

	@Autowired
	NewsServiceImpl newsService;
	
	@GetMapping(value = "")
	public ResponseEntity<ResponseData> getNews() {
		return newsService.getNews();
	}	
	
	@PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> addCategory(@RequestBody NewsDTO newsDTO) {		
		return newsService.addNews(newsDTO);
	}
	
}
