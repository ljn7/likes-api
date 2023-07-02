package com.service.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.likes.model.Notification;
import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public List<Notification> findByUserId(Long userId);
}
