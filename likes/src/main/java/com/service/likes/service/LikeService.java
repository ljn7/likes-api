package com.service.likes.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.service.likes.model.Like;

public interface LikeService {
    public Map<Like, HttpStatus> likeContent(long userId, long contentId);
    public Map<Like, HttpStatus> isLiked(long userId, long contentId);
}
