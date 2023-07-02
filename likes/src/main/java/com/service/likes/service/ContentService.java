package com.service.likes.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.service.likes.model.Content;

public interface ContentService {
    
    public Map<Long, HttpStatus> getTotalLike(long contentId);
    public Content findByContentId(Long contentId);
    public Content findByUserId(Long userId);
    public Content saveContent(Content content);
}
