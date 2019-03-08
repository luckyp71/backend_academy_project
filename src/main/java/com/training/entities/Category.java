package com.training.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="category")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="category_id")
	private long categoryId;
	
	@Column(name="name")
	private String name;
	
	@CreationTimestamp
	@Column(name="created_at")
	private Timestamp categoryCreatedAt;

	@UpdateTimestamp
	@Column(name="updated_at",columnDefinition= "timestamp NULL default NULL on update current_timestamp")
	private Timestamp categoryUpdatedAt;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"category"})
	private List<News> news;
	
	//Default constructor
	public Category() {
		//Leave it blank
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
}
