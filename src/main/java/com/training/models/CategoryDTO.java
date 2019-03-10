package com.training.models;

public class CategoryDTO {

	private String categoryName;

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

}
