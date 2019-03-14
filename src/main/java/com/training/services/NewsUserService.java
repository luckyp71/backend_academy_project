package com.training.services;

import com.training.models.NewsUserDTO;

public interface NewsUserService {

	public NewsUserDTO register(NewsUserDTO user);
	
	public char login(NewsUserDTO user);
	
	public boolean forgotPassword(NewsUserDTO user);
	
	public char changePassword(NewsUserDTO user);
	
	public boolean logout();
	
	public NewsUserDTO getUserProfile(long id);
	
	public NewsUserDTO getUserByUsername(String username);
}
