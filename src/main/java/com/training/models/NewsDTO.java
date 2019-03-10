package com.training.models;

import com.training.entities.Category;
import com.training.entities.NewsUser;

public class NewsDTO {

	private String title;

	private String content;
	
	private Category category;
	
	private NewsUser user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public NewsUser getUser() {
		return user;
	}

	public void setUser(NewsUser user) {
		this.user = user;
	}
}
