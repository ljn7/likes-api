package com.service.likes.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.service.likes.model.Like;
import com.service.likes.repository.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;
    
    @Override
    public Map<Like, HttpStatus> likeContent(long userId, long contentId) {

        try {
            if (likeRepository.findByUserIdAndContentId(userId, contentId) != null)
                return new HashMap<Like, HttpStatus>() {{
                    put(null, HttpStatus.CONFLICT);
                }};

            Like like = new Like();
            like.setUserId(userId);
            like.setContentId(contentId);
            
            final Like result = likeRepository.save(like);
            
            
            return new HashMap<Like, HttpStatus>() {{
                put(result, HttpStatus.CREATED);
            }};
            

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<Like, HttpStatus>() {{
                put(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }};
        }

        
    }

    @Override
    public Map<Like, HttpStatus> isLiked(long userId, long contentId) {
        
        try {

            final Like like = likeRepository.findByUserIdAndContentId(userId, contentId);
            HttpStatus status;
            if (like != null)
                status = HttpStatus.FOUND;
            else 
                status = HttpStatus.NOT_FOUND;
            return new HashMap<Like, HttpStatus>() {{
                    put(like, status);
                }};
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<Like, HttpStatus>() {{
                    put(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }};

        }

    }
    

}
