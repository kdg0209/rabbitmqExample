package com.rabbitmq.api.rabbitmqexample.handler;

import com.rabbitmq.api.rabbitmqexample.notification.domain.NotificationType;
import com.rabbitmq.api.rabbitmqexample.notification.service.NotificationService;
import com.rabbitmq.api.rabbitmqexample.rabbitmq.response.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage<T> {

    private EventHandler eventHandler;
    private NotificationType notificationType;
    private T data;

    public NotificationResponse notificationSend(NotificationService notificationService) {
        return eventHandler.send(this, notificationService);
    }
}
