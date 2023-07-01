package com.service.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.likes.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>{
    
}
