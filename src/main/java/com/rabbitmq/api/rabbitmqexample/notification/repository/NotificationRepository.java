package com.rabbitmq.api.rabbitmqexample.notification.repository;

import com.rabbitmq.api.rabbitmqexample.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
