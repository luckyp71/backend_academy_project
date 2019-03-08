package com.training.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Timestamp;

import java.io.Serializable;

@Entity
@Table(name="news")
public class News  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="news_id")
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="author")
	private String author;
	
	@CreationTimestamp
	@Column(name="created_at")
	private Timestamp newsCreatedAt;

	@UpdateTimestamp
	@Column(name="updated_at",columnDefinition= "timestamp NULL default NULL on update current_timestamp")
	private Timestamp newsUpdatedAt;
	
	@Column(name="is_active")
	private boolean isActive;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	@JsonIgnoreProperties("newsUser")
	public NewsUser newsUser;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	@JsonIgnoreProperties("category")
	private Category category;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
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
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Timestamp getNewsCreatedAt() {
		return newsCreatedAt;
	}

	public void setNewsCreatedAt(Timestamp newsCreatedAt) {
		this.newsCreatedAt = newsCreatedAt;
	}

	public Timestamp getNewsUpdatedAt() {
		return newsUpdatedAt;
	}

	public void setNewsUpdatedAt(Timestamp newsUpdatedAt) {
		this.newsUpdatedAt = newsUpdatedAt;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public NewsUser getNewsUser() {
		return newsUser;
	}
	
	public void setNewsUser(NewsUser newsUser) {
		this.newsUser = newsUser;
	}

	public Category getCattegory() {
		return category;
	}

	public void setCattegory(Category category) {
		this.category = category;
	}
}
