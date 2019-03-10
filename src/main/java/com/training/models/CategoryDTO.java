package com.training.models;

import java.sql.Timestamp;

public class CategoryDTO {
	
	private String categoryName;

	private Timestamp categoryCreatedAt;

	private Timestamp categoryUpdatedAt;
	
	//Default constructor
	public CategoryDTO() {
		//Leave it blank
	}
	
	public CategoryDTO(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return categoryName;
	}

	public void setName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getCreatedAt() {
		return categoryCreatedAt;
	}

	public void setCreatedAt(Timestamp categoryCreatedAt) {
		this.categoryCreatedAt = categoryCreatedAt;
	}

	public Timestamp getUpdatedAt() {
		return categoryUpdatedAt;
	}

	public void setUpdatedAt(Timestamp categoryUpdatedAt) {
		this.categoryUpdatedAt = categoryUpdatedAt;
	}
}
