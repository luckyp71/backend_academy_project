package com.training.services_impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entities.NewsUser;
import com.training.exceptions.DuplicateException;
import com.training.repositories.NewsUserServiceRepo;
import com.training.services.NewsUserService;

@Service
public class NewsUserServiceImpl implements NewsUserService {

	@Autowired
	NewsUserServiceRepo userRepo;

	@Override
	public boolean register(NewsUser user) throws DuplicateException{
		userRepo.save(user);
		return true;
	}

	@Override
	public boolean login(NewsUser user) {
		return true;
	}

	@Override
	public boolean logout() {
		return true;
	}

	@Override
	public NewsUser getUserProfile(long id) {
		Optional<NewsUser> user = userRepo.findById(id);
		return user.orElse(null);
	}

	@Override
	public NewsUser getUserByUsername(String username) {
		Optional<NewsUser> user = userRepo.findByUsername(username);
		return user.orElse(null);
	}

}
