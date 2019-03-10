package com.training.models;

public class NewsDTO {

	private String newsTitle;
	private String newsContent;
	private String newsAuthor;
	private boolean newsIsActive;
	private String newsCategory;

	public String getTitle() {
		return newsTitle;
	}

	public void setTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getContent() {
		return newsContent;
	}

	public void setContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getAuthor() {
		return newsAuthor;
	}

	public void setAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public boolean isActive() {
		return newsIsActive;
	}

	public void setActive(boolean newsIsActive) {
		this.newsIsActive = newsIsActive;
	}

	public String getCategory() {
		return newsCategory;
	}

	public void setCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}
}
