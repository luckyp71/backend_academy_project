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
import com.training.exceptions.NotFoundException;
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
	ResponseDataServiceImpl categoryService;
	
	@Autowired
	NewsExceptionHandler newsException;
	
	@Override
	public ResponseEntity<ResponseData> getNews() {
		List<NewsDTO> news = new ArrayList<>();
		newsRepo.findAll().stream().forEach(n-> {	
			NewsDTO newsDTO = new NewsDTO();
			newsDTO.setTitle(n.getTitle());
			newsDTO.setContent(n.getContent());
			newsDTO.setCategoryId(n.getCategory().getCategoryId());
			newsDTO.setUserId(n.getNewsUser().getId());;
			news.add(newsDTO);			
		});	
		return responseService.responseSuccess(news);
	}

	@Override
	public ResponseEntity<ResponseData> getNewsById(long id) {
		
		return null;
	}

	@Override
	public ResponseEntity<ResponseData> addNews(NewsDTO newsDTO) {
		try {
		//Title must unique?
		News existingNews = newsRepo.findNewsByTitle(newsDTO.getTitle()).orElse(null);
		if(existingNews != null && existingNews.getIsActive()=='Y') 
			throw new DuplicateException();

		Category existingCategory = categoryRepo.findById(newsDTO.getCategoryId()).orElse(null);
		if(existingCategory == null || existingCategory.getIsActive()=='N') 
			throw new NotFoundException();
		
		NewsUser existingUser = userRepo.findById(newsDTO.getUserId()).orElse(null);
		if(existingUser == null || existingUser.getIsActive()=='N') 
			throw new NotFoundException();
		
		News news = new News();
		news.setNewsUser(existingUser);
		news.setTitle(newsDTO.getTitle());
		news.setContent(newsDTO.getContent());
		news.setCategory(existingCategory);
		newsRepo.save(news);
		return responseService.responseSuccess(newsDTO);
		} catch(DuplicateException de) {
			return newsException.duplicateException();
		} catch(NotFoundException ne) {
			return newsException.notFoundException();
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
