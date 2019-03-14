package com.training.services_impl;


import java.security.SecureRandom;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.training.entities.NewsUser;
import com.training.models.NewsUserDTO;
import com.training.repositories.NewsUserRepo;
import com.training.services.NewsUserService;

@Service
public class NewsUserServiceImpl implements NewsUserService {

	@Autowired
	NewsUserRepo userRepo;
	
	@Override
	public NewsUserDTO register(NewsUserDTO userDTO) {
		NewsUser existingUser = userRepo.findByUsername(userDTO.getUsername()).orElse(null);
		if (existingUser != null && existingUser.getIsActive() == 'Y')
			return null;
		
		NewsUser existingEmail = userRepo.findByEmail(userDTO.getEmail()).orElse(null);
		if (existingEmail != null && existingEmail.getIsActive() == 'Y')
			return null;

		String password = userDTO.getPassword();
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		userDTO.setPassword(hashedPassword);
		NewsUser user = new NewsUser();
		user.setUsername(userDTO.getUsername());
		user.setPassword(hashedPassword);
		user.setEmail(userDTO.getEmail());
		userRepo.save(user);
		return userDTO;
	}

	@Override
	public char login(NewsUserDTO user) {
		NewsUser existingUser = userRepo.findByUsername(user.getUsername()).orElse(null);
		if (existingUser == null || existingUser.getIsActive() == 'N')
			return 'n';
		
		boolean authenticatedStatus = BCrypt.checkpw(user.getPassword(), existingUser.getPassword());
		if (!authenticatedStatus)
			return 'a';
		
		return 'y';
	}

	@Override
	public boolean logout() {
		return true;
	}

	@Override
	public NewsUserDTO getUserProfile(long id) {
		NewsUser existingUser = userRepo.findById(id).orElse(null);
		if (existingUser != null && existingUser.getIsActive() == 'Y') {
			String password = existingUser.getPassword();
			char[] maskingPass = new char[password.length() - 4];
			for (int i = 0; i < (password.length() - 4); i++) {
				maskingPass[i] = '*';
			}
			String maskingPassword = password.replace(password.substring(0, password.length() - 4),
					String.valueOf(maskingPass));
			existingUser.setPassword(maskingPassword);
			NewsUserDTO userDTO = new NewsUserDTO();
			userDTO.setUsername(existingUser.getUsername());
			userDTO.setPassword(maskingPassword);
			userDTO.setEmail(existingUser.getEmail());
			return userDTO;
		}
		return null;
	}

	@Override
	public NewsUserDTO getUserByUsername(String username) {
		Optional<NewsUser> user = userRepo.findByUsername(username);
		if (user.isPresent() && user.get().getIsActive() == 'Y') {
			NewsUser newsUser = user.get();
			String password = newsUser.getPassword();
			char[] maskingPass = new char[password.length() - 4];
			for (int i = 0; i < (password.length() - 4); i++) {
				maskingPass[i] = '*';
			}
			String maskingPassword = password.replace(password.substring(0, password.length() - 4),
					String.valueOf(maskingPass));
			NewsUserDTO userDTO = new NewsUserDTO();
			userDTO.setUsername(newsUser.getUsername());
			userDTO.setPassword(maskingPassword);
			userDTO.setEmail(newsUser.getEmail());
			return userDTO;
		}
		return null;
	}

	@Override
	public boolean forgotPassword(NewsUserDTO user) {
		NewsUser existingUser = userRepo.findByEmail(user.getEmail()).orElse(null);
		if(existingUser == null || existingUser.getIsActive() == 'N')
			return false;
		
		//Generate random password with 8 characters length		 
		char[] chars = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9'}; 
		SecureRandom rand = new SecureRandom();
		char[] newPassword = new char[chars.length];
		
		IntStream.range(0, 8).forEach(i -> {
			int randChar = rand.nextInt(chars.length);	
			newPassword[i]=chars[randChar];
		});
		
		String password = String.valueOf(newPassword).trim();
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		
		//Sending email
		user.setPassword(password);
		Thread thread = new EmailServiceImpl(user);
		thread.start();
	
		//Save new password
		existingUser.setPassword(hashedPassword);
		userRepo.save(existingUser);
		return true;
	}

	@Override
	public char changePassword(NewsUserDTO user) {
		NewsUser existingUser = userRepo.findByUsername(user.getUsername()).orElse(null);
		if (existingUser == null || existingUser.getIsActive() == 'N')
			return 'n';
		
		boolean authenticatedStatus = BCrypt.checkpw(user.getPassword(), existingUser.getPassword());
		if (!authenticatedStatus)
			return 'a';
		
		String password = user.getNewPassword().trim();
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		existingUser.setPassword(hashedPassword);
		userRepo.save(existingUser);
		return 'y';
	}
}
