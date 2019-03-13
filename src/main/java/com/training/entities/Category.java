package com.training.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="category")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="categorySequence", 
			allocationSize=1, 
			initialValue =1, 
			sequenceName="categorySequence1")
	
	@Id
	@GeneratedValue(generator="categorySequence")
	@Column(name="category_id")
	private long categoryId;
	
	@Column(name="name", columnDefinition="VARCHAR(80) NOT NULL")
	private String name;
	
	@CreationTimestamp
	@Column(name="created_at")
	private Timestamp categoryCreatedAt;

	@UpdateTimestamp
	@Column(name="updated_at",columnDefinition= "timestamp NULL default NULL on update current_timestamp")
	private Timestamp categoryUpdatedAt;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<News> news;
	
	@Column(name="is_active", columnDefinition="CHAR(1) NOT NULL DEFAULT('Y')")
	private char isActive;
	
	//Default constructor
	public Category() {
		this.isActive = 'Y';
	}
	
	public Category(String name) {
		this.name = name;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCategoryCreatedAt() {
		return categoryCreatedAt;
	}

	public void setCategoryCreatedAt(Timestamp categoryCreatedAt) {
		this.categoryCreatedAt = categoryCreatedAt;
	}

	public Timestamp getCategoryUpdatedAt() {
		return categoryUpdatedAt;
	}

	public void setCategoryUpdatedAt(Timestamp categoryUpdatedAt) {
		this.categoryUpdatedAt = categoryUpdatedAt;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
}
