package com.training.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.training.entities.NewsUser;

@Repository
public interface NewsUserRepo extends JpaRepository<NewsUser, Long> {
	Optional<NewsUser> findByUsername(String username);
	Optional<NewsUser> findByEmail(String email);
}
