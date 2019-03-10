package com.training.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entities.News;

@Repository
public interface NewsRepo extends JpaRepository<News, Long>{
	Optional<News> findNewsByTitle(String title);
}
