package com.rabbitmq.api.rabbitmqexample.handler;

import com.rabbitmq.api.rabbitmqexample.notification.domain.Notification;
import com.rabbitmq.api.rabbitmqexample.notification.service.NotificationService;
import com.rabbitmq.api.rabbitmqexample.rabbitmq.response.NotificationResponse;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventHandler implements EventHandler {

    @Override
    public NotificationResponse send(NotificationMessage message, NotificationService notificationService) {
        Notification notification = notificationService.save(message);
        return new NotificationResponse(notification.getMember().getPhone(), notification.getContents());
    }
}
