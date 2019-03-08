package com.training.services;

import com.training.entities.NewsUser;

public interface NewsUserService {

	public boolean register(NewsUser user);
	
	public boolean login(NewsUser user);
	
	public boolean logout();
	
	public NewsUser getUserProfile(long id);
	
	public NewsUser getUserByUsername(String username);
}
