package com.service.likes.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
@Data
@Entity(name = "contents")
public class Content {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "content_id", unique = true, insertable = false, updatable = false)
    private Long contentId;
    
    @Column(name="total_likes")
    private Long totalLikes;

    @Column(name="belongs_to")
    private Long userId;
    
    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
