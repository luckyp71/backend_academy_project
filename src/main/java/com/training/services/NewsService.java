package com.training.services;

import com.training.entities.News;
import java.util.List;

public interface NewsService {

	public List<News> getNews();
	
	public News getNews(long id);
	
	public boolean insertNews(News news);
	
	public boolean updateNews(News news);
	
	public boolean deleteNews(long id);

}
