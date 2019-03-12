package com.training.services;

import com.training.models.NewsDTO;
import java.util.List;

public interface NewsService {

	public List<NewsDTO> getNews();
	
	public NewsDTO getNewsById(long id);
	
	public char addNews(NewsDTO newsDTO);
	
	public boolean updateNews(long id, NewsDTO newsDTO);
	
	public boolean deleteNews(long id);

}
