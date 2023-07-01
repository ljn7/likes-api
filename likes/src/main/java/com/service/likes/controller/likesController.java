package com.service.likes.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.likes.model.Like;
import com.service.likes.model.User;
import com.service.likes.repository.UserRepository;
import com.service.likes.service.LikeService;

@RestController
public class likesController {
    
    @Autowired
    private LikeService likeService;

    @GetMapping(path = "/")
    public String hello () {
        return "Hello";
    }

    @PostMapping(path = "/like")
    public ResponseEntity<Like> likeContent(@RequestBody Map<String, Long> requestBody) {

        Long userId = requestBody.get("userId");
        Long contentId = requestBody.get("contentId");

        if (userId != null && contentId != null) {

            Map<Like, HttpStatus> result = likeService.likeContent(userId, contentId);
            Like like = result.keySet().iterator().next();
            HttpStatus statusCode = result.get(like);

            return new ResponseEntity<>(like, statusCode);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/isliked")
    public ResponseEntity<Like> liked(@RequestBody Map<String, Long> requestBody) {

        Long userId = requestBody.get("userId");
        Long contentId = requestBody.get("contentId");

        if (userId != null && contentId != null) {

            Map<Like, HttpStatus> result = likeService.isLiked(userId, contentId);
            Like like = result.keySet().iterator().next();
            HttpStatus statusCode = result.get(like);

            return new ResponseEntity<>(like, statusCode);
        }
        return ResponseEntity.badRequest().build();
    }

    }
}
