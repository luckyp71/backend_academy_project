package com.training.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entities.News;

@Repository
public interface NewsServiceRepo extends JpaRepository<News, Long>{

}
