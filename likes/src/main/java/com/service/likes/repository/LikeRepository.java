package com.service.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.likes.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByUserIdAndContentId(Long userId, Long contentId);
}
