package com.service.likes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.likes.model.User;
import com.service.likes.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }
}
