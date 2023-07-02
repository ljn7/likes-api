package com.service.likes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.service.likes.model.Content;
import com.service.likes.repository.ContentRepository;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Map<Long, HttpStatus> getTotalLike(long contentId) {

        try {
            contentRepository.findByContentId(contentId);
            Content content = contentRepository.findByContentId(contentId);

            if (content != null) {
                Long res = content.getTotalLikes();
                return new HashMap<Long, HttpStatus>() {
                    {
                        put(res, HttpStatus.valueOf(200));
                    }
                };
            }

            return new HashMap<Long, HttpStatus>() {
                {
                    put(null, HttpStatus.NOT_FOUND);
                }
            };

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<Long, HttpStatus>() {
                {
                    put(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            };
        }
    }

    @Override
    public Content findByContentId(Long contentId) {
        return contentRepository.findByContentId(contentId);
    }

    @Override
    public Content findByUserId(Long userId) {
        return contentRepository.findByUserId(userId);
    }

    @Override
    public Content saveContent(Content content) {
     
        return contentRepository.save(content);
    }

}
