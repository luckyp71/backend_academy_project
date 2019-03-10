package com.training.services;

import com.training.entities.News;
import com.training.models.ResponseData;

import org.springframework.http.ResponseEntity;

public interface NewsService {

	public ResponseEntity<ResponseData> getNews();
	
	public ResponseEntity<ResponseData> getNews(long id);
	
	public ResponseEntity<ResponseData> insertNews(News news);
	
	public ResponseEntity<ResponseData> updateNews(News news);
	
	public ResponseEntity<ResponseData> deleteNews(long id);

}
