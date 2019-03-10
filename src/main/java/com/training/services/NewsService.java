package com.training.services;

import com.training.models.NewsDTO;
import com.training.models.ResponseData;

import org.springframework.http.ResponseEntity;

public interface NewsService {

	public ResponseEntity<ResponseData> getNews();
	
	public ResponseEntity<ResponseData> getNewsById(long id);
	
	public ResponseEntity<ResponseData> insertNews(NewsDTO newsDTO);
	
	public ResponseEntity<ResponseData> updateNews(long id, NewsDTO newsDTO);
	
	public ResponseEntity<ResponseData> deleteNews(long id);

}
