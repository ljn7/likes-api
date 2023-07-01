package com.service.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.likes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
