package com.rabbitmq.api.rabbitmqexample.handler;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rabbitmq.api.rabbitmqexample.notification.service.NotificationService;
import com.rabbitmq.api.rabbitmqexample.rabbitmq.response.NotificationResponse;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NotificationEventHandler.class, name = "notification"),
        @JsonSubTypes.Type(value = MemberCouponEventHandler.class, name = "memberCoupon"),
})
public interface EventHandler {

    NotificationResponse send(NotificationMessage message, NotificationService notificationService);
}
