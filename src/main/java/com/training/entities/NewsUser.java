package com.training.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@Entity
@Table(name = "news_user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="userSequence", 
			allocationSize=1, 
			initialValue =1, 
			sequenceName="userSequence1")
	
	@Id
	@GeneratedValue(generator="userSequence")
	@Column(name = "user_id")
	private long id;

	@Column(name = "username", columnDefinition="VARCHAR(100) NOT NULL")
	private String username;

	@Column(name = "password", columnDefinition="VARCHAR(100) NOT NULL")
	private String password;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", columnDefinition = "timestamp NULL default NULL on update current_timestamp")
	private Timestamp updatedAt;
	
	@Column(name="is_active", columnDefinition="CHAR(1) NOT NULL DEFAULT('Y')")
	private char isActive;

	@OneToMany(mappedBy = "newsUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<News> news;

	//Default constructor
	public NewsUser() {
		this.isActive = 'Y';
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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
