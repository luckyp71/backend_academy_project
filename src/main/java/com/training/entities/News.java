package com.training.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;


import java.sql.Timestamp;
import java.io.Serializable;

@Entity
@Table(name = "news")
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="newsSequence", 
			allocationSize=1, 
			initialValue =1, 
			sequenceName="newsSequence1")
	
	@Id
	@GeneratedValue(generator="newsSequence")
	@Column(name = "news_id")
	private long id;

	@Column(name = "title", columnDefinition = "VARCHAR(100) NOT NULL")
	private String title;

	@Column(name = "content")
	private String content;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp newsCreatedAt;

	@UpdateTimestamp
	@Column(name = "updated_at", columnDefinition = "timestamp NULL default NULL on update current_timestamp")
	private Timestamp newsUpdatedAt;

	@Column(name = "is_active", columnDefinition = "CHAR(1) NOT NULL DEFAULT('Y')")
	private char isActive;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public NewsUser newsUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	public News() {
		this.isActive = 'Y';
	}

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

	public NewsUser getNewsUser() {
		return newsUser;
	}

	public void setNewsUser(NewsUser newsUser) {
		this.newsUser = newsUser;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
}
