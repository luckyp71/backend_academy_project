package com.training.services_impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entities.Category;
import com.training.entities.News;
import com.training.entities.NewsUser;
import com.training.models.NewsDTO;
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
	
	@Override
	public List<NewsDTO> getNews() {
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
		return news;
	}

	@Override
	public NewsDTO getNewsById(long id) {
		News news = newsRepo.findById(id).orElse(null);
		if (news == null || news.getIsActive() == 'N')
			return null;
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setTitle(news.getTitle());
		newsDTO.setContent(news.getContent());
		newsDTO.setCategoryId(news.getCategory().getCategoryId());
		newsDTO.setUserId(news.getNewsUser().getId());
		return newsDTO;
	}

	@Override
	public char addNews(NewsDTO newsDTO) {
		// Title must be unique?
		News existingNews = newsRepo.findNewsByTitle(newsDTO.getTitle()).orElse(null);
		if (existingNews != null && existingNews.getIsActive() == 'Y')
			return 'd';

		Category existingCategory = categoryRepo.findById(newsDTO.getCategoryId()).orElse(null);
		if (existingCategory == null || existingCategory.getIsActive() == 'N')
			return 'n';

		NewsUser existingUser = userRepo.findById(newsDTO.getUserId()).orElse(null);
		if (existingUser == null || existingUser.getIsActive() == 'N')
			return 'n';

		News news = new News();
		news.setNewsUser(existingUser);
		news.setTitle(newsDTO.getTitle());
		news.setContent(newsDTO.getContent());
		news.setCategory(existingCategory);
		newsRepo.save(news);
		return 'y';
	}

	@Override
	public boolean updateNews(long id, NewsDTO newsDTO) {
		News existingNews = newsRepo.findById(id).orElse(null);
		if (existingNews == null || existingNews.getIsActive() == 'N')
			return false;

		Category existingCategory = categoryRepo.findById(newsDTO.getCategoryId()).orElse(null);
		if (existingCategory == null || existingCategory.getIsActive() == 'N')
			return false;
		
		existingNews.setTitle(newsDTO.getTitle());
		existingNews.setContent(newsDTO.getContent());
		existingNews.setCategory(existingCategory);
		newsRepo.save(existingNews);
		return true;
	}

	@Override
	public boolean deleteNews(long id) {
		News existingNews = newsRepo.findById(id).orElse(null);
		if (existingNews == null || existingNews.getIsActive() == 'N')
			return false;
		
		existingNews.setIsActive('N');
		newsRepo.save(existingNews);
		return true;
	}
}
