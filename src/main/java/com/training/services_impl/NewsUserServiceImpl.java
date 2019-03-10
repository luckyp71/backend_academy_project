package com.training.services_impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.training.entities.NewsUser;
import com.training.exceptions.AuthFailedException;
import com.training.exceptions.DuplicateException;
import com.training.exceptions.NewsExceptionHandler;
import com.training.exceptions.NotFoundException;
import com.training.models.NewsUserDTO;
import com.training.models.ResponseData;
import com.training.repositories.NewsUserServiceRepo;
import com.training.services.NewsUserService;

@Service
public class NewsUserServiceImpl implements NewsUserService {

	@Autowired
	NewsUserServiceRepo userRepo;

	@Autowired
	ResponseDataServiceImpl responseService;

	@Autowired
	NewsExceptionHandler newsException;

	@Override
	public ResponseEntity<ResponseData> register(NewsUserDTO userDTO) {
		try {
			NewsUser existingUser = userRepo.findByUsername(userDTO.getUsername()).orElse(null);
			if (existingUser != null)
				throw new DuplicateException();
			String password = userDTO.getPassword();
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			userDTO.setPassword(hashedPassword);
			NewsUser user = new NewsUser();
			user.setUsername(userDTO.getUsername());
			user.setPassword(hashedPassword);
			userRepo.save(user);
			return responseService.responseSuccess(userDTO);
		} catch (DuplicateException ex) {
			return newsException.duplicateException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> login(NewsUserDTO user) {
		try {
			NewsUser persistedUser = userRepo.findByUsername(user.getUsername()).orElse(null);
			if (persistedUser == null) {
				throw new NotFoundException();
			}
			boolean authenticatedStatus = BCrypt.checkpw(user.getPassword(), persistedUser.getPassword());
			if (!authenticatedStatus) {
				throw new AuthFailedException();
			}
			return responseService.responseSuccess(null);
		} catch (NotFoundException ex) {
			return newsException.notFoundException();
		} catch (AuthFailedException ex) {
			return newsException.authFailedException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> logout() {
		return null;
	}

	@Override
	public ResponseEntity<ResponseData> getUserProfile(long id) {
		try {
			NewsUser user = userRepo.findById(id).orElse(null);
			if (user != null) {
				String password = user.getPassword();
				char[] maskingPass = new char[password.length() - 4];
				for (int i = 0; i < (password.length() - 4); i++) {
					maskingPass[i] = '*';
				}
				String maskingPassword = password.replace(password.substring(0, password.length() - 4),
						String.valueOf(maskingPass));
				user.setPassword(maskingPassword);
				return responseService.responseSuccess(user);
			}
			throw new NotFoundException("data not found");
		} catch (NotFoundException ex) {
			return newsException.notFoundException();
		}
	}

	@Override
	public ResponseEntity<ResponseData> getUserByUsername(String username) {
		try {
			Optional<NewsUser> user = userRepo.findByUsername(username);
			if (user.isPresent()) {
				NewsUser newsUser = user.get();
				String password = newsUser.getPassword();
				char[] maskingPass = new char[password.length() - 4];
				for (int i = 0; i < (password.length() - 4); i++) {
					maskingPass[i] = '*';
				}
				String maskingPassword = password.replace(password.substring(0, password.length() - 4),
						String.valueOf(maskingPass));
				newsUser.setPassword(maskingPassword);
				return responseService.responseSuccess(newsUser);
			}
			throw new NotFoundException("data not found");
		} catch (NotFoundException ex) {
			return newsException.notFoundException();
		}
	}
}
