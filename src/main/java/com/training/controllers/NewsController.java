	package com.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.training.models.NewsDTO;
import com.training.models.ResponseData;
import com.training.services_impl.NewsServiceImpl;

@RestController
@RequestMapping("/data")
public class NewsController {

	@Autowired
	NewsServiceImpl newsService;
	
	@GetMapping(value = "")
	public ResponseEntity<ResponseData> getNews() {
		return newsService.getNews();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ResponseData> getNewsById(@PathVariable("id")long id) {
		return newsService.getNewsById(id);
	}
	
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> addNews(@RequestBody NewsDTO newsDTO) {		
		return newsService.addNews(newsDTO);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> updateNews(@PathVariable("id")long id,
			@RequestBody NewsDTO newsDTO) {		
		return newsService.updateNews(id, newsDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseData> deleteNews(@PathVariable("id")long id) {		
		return newsService.deleteNews(id);
	}
	
}
