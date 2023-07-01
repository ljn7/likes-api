package com.service.likes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.likes.repository.ContentRepository;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Long getTotalLike(long contentId) {
        
        return contentRepository.findByContentId(contentId).getTotalLikes();
    }
    
    
}
