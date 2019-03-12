package com.training.models;

import java.io.Serializable;

public class NewsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;

	private String content;
	
	private long categoryId;
	
	private long userId;

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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
