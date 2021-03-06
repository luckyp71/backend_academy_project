package com.training.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class NewsUserDTO {
	
	private String username;
	
	private String password;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String newPassword;

	private String email;	
	
	public NewsUserDTO() {
		//Default constructor
	}
	
	public NewsUserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public NewsUserDTO(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
