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
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@Entity
@Table(name = "news_user")
public class NewsUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", columnDefinition = "timestamp NULL default NULL on update current_timestamp")
	private Timestamp updatedAt;

	@OneToMany(mappedBy = "newsUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "newsUser" })
	private List<News> news;

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

}
