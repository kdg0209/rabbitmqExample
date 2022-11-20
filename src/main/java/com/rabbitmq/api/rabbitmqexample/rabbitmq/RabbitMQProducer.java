package com.rabbitmq.api.rabbitmqexample.rabbitmq;

import com.rabbitmq.api.rabbitmqexample.handler.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "fanoutExchange";

    @TransactionalEventListener
    public void eventHandler(NotificationMessage notificationMessage) {
        sendMessage(notificationMessage);
    }

    private void sendMessage(NotificationMessage response) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", response);
    }
}
