package com.training.services_impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.entities.NewsUser;
import com.training.repositories.NewsUserRepo;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private NewsUserRepo userRepo;
    
    public UserDetailsServiceImpl(NewsUserRepo applicationUserRepository) {
        this.userRepo = applicationUserRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username){
        NewsUser user = userRepo.findByUsername(username).orElse(null);
        if (user == null) 
            throw new UsernameNotFoundException(username);
        
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}