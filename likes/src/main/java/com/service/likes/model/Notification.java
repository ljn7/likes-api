package com.service.likes.model;


import java.time.LocalDateTime;

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
@Entity(name = "notifications")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(unique=true, name="notification_id")
    private Long notificationId;

    @Column(name="user_id")
    private Long userId;

    @Column(name = "message")
    private String message;

    @Column(name="is_read")
    private boolean isRead = false;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
