package com.training.services_impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.entities.Category;
import com.training.entities.News;
import com.training.entities.NewsUser;
import com.training.exceptions.DuplicateException;
import com.training.exceptions.NewsExceptionHandler;
import com.training.models.NewsDTO;
import com.training.models.ResponseData;
import com.training.repositories.CategoryRepo;
import com.training.repositories.NewsRepo;
import com.training.repositories.NewsUserRepo;
import com.training.services.NewsService;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	NewsRepo newsRepo;

	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	NewsUserRepo userRepo;
	
	@Autowired
	ResponseDataServiceImpl responseService;
	
	@Autowired
	NewsExceptionHandler newsException;
	
	@Override
	public ResponseEntity<ResponseData> getNews() {
		List<NewsDTO> news = new ArrayList<>();
		newsRepo.findAll().stream().forEach(n-> {
			NewsDTO newsDTO = new NewsDTO();
			newsDTO.setTitle(n.getTitle());
			newsDTO.setTitle(n.getAuthor());
			newsDTO.setContent(n.getContent());
			newsDTO.setCategory(n.getCategory());
			news.add(newsDTO);			
		});	
		return responseService.responseSuccess(news);
	}

	@Override
	public ResponseEntity<ResponseData> getNewsById(long id) {
		
		return null;
	}

	@Override
	public ResponseEntity<ResponseData> insertNews(NewsDTO newsDTO) {
		try {
		//Title must unique?
		News existingNews = newsRepo.findNewsByTitle(newsDTO.getTitle()).orElse(null);
		if(existingNews != null && existingNews.getIsActive()=='Y') 
			throw new DuplicateException();
		Category category = categoryRepo.findById(newsDTO.getCategory().getCategoryId()).orElse(null);
		NewsUser user = userRepo.findById(newsDTO.getUser().getId()).orElse(null);
		
		existingNews.setNewsUser(newsDTO.getUser());
		existingNews.setTitle(newsDTO.getTitle());
		existingNews.setContent(newsDTO.getContent());
		existingNews.setCategory(category);
		existingNews.setNewsUser(user);
		newsRepo.save(existingNews);
		return responseService.responseSuccess(existingNews);
		} catch(DuplicateException de) {
			return newsException.duplicateException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> updateNews(long id, NewsDTO newsDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseData> deleteNews(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
