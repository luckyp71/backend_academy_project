package com.training.repositories;

import org.springframework.stereotype.Repository;

import com.training.entities.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String name);
}
