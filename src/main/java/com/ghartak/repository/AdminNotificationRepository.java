package com.ghartak.repository;

import com.ghartak.model.AdminNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminNotificationRepository extends JpaRepository<AdminNotification, Long> {
    List<AdminNotification> findAllByOrderByCreatedAtDesc();
    List<AdminNotification> findByIsReadFalseOrderByCreatedAtDesc();
    long countByIsReadFalse();
}
