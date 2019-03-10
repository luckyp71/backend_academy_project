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
public class NewsServiceImpl implements NewsService {

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
		newsRepo.findAll().stream().forEach(n -> {
			if (n.getIsActive() == 'Y') {
				NewsDTO newsDTO = new NewsDTO();
				newsDTO.setTitle(n.getTitle());
				newsDTO.setContent(n.getContent());
				newsDTO.setCategoryId(n.getCategory().getCategoryId());
				newsDTO.setUserId(n.getNewsUser().getId());
				news.add(newsDTO);
			}
		});
		return responseService.responseSuccess(news);
	}

	@Override
	public ResponseEntity<ResponseData> getNewsById(long id) {
		try {
			News news = newsRepo.findById(id).orElse(null);
			if (news == null || news.getIsActive() == 'N')
				throw new NotFoundException();

			NewsDTO newsDTO = new NewsDTO();
			newsDTO.setTitle(news.getTitle());
			newsDTO.setContent(news.getContent());
			newsDTO.setCategoryId(news.getCategory().getCategoryId());
			newsDTO.setUserId(news.getNewsUser().getId());
			return responseService.responseSuccess(newsDTO);
		} catch (NotFoundException ne) {
			return newsException.notFoundException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> addNews(NewsDTO newsDTO) {
		try {
			// Title must unique?
			News existingNews = newsRepo.findNewsByTitle(newsDTO.getTitle()).orElse(null);
			if (existingNews != null && existingNews.getIsActive() == 'Y')
				throw new DuplicateException();

			Category existingCategory = categoryRepo.findById(newsDTO.getCategoryId()).orElse(null);
			if (existingCategory == null || existingCategory.getIsActive() == 'N')
				throw new NotFoundException();

			NewsUser existingUser = userRepo.findById(newsDTO.getUserId()).orElse(null);
			if (existingUser == null || existingUser.getIsActive() == 'N')
				throw new NotFoundException();

			News news = new News();
			news.setNewsUser(existingUser);
			news.setTitle(newsDTO.getTitle());
			news.setContent(newsDTO.getContent());
			news.setCategory(existingCategory);
			newsRepo.save(news);
			return responseService.responseSuccess(newsDTO);
		} catch (DuplicateException de) {
			return newsException.duplicateException();
		} catch (NotFoundException ne) {
			return newsException.notFoundException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> updateNews(long id, NewsDTO newsDTO) {
		try {
			News existingNews = newsRepo.findById(id).orElse(null);
			if (existingNews == null || existingNews.getIsActive() == 'N') {
				throw new NotFoundException();
			}

			Category existingCategory = categoryRepo.findById(newsDTO.getCategoryId()).orElse(null);
			if (existingCategory == null || existingCategory.getIsActive() == 'N')
				throw new NotFoundException();

			existingNews.setTitle(newsDTO.getTitle());
			existingNews.setContent(newsDTO.getContent());
			existingNews.setCategory(existingCategory);
			newsRepo.save(existingNews);
			return responseService.responseSuccess(newsDTO);
		} catch (NotFoundException ne) {
			return newsException.notFoundException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> deleteNews(long id) {
		try {
			News existingNews = newsRepo.findById(id).orElse(null);
			if (existingNews == null || existingNews.getIsActive() == 'N')
				throw new NotFoundException();

			existingNews.setIsActive('N');
			newsRepo.save(existingNews);

			return responseService.responseSuccess(null);
		} catch (NotFoundException ne) {
			return newsException.notFoundException();
		}
	}

}
