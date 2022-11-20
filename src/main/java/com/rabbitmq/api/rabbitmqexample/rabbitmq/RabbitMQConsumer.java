package com.rabbitmq.api.rabbitmqexample.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.api.rabbitmqexample.handler.NotificationMessage;
import com.rabbitmq.api.rabbitmqexample.notification.service.NotificationService;
import com.rabbitmq.api.rabbitmqexample.rabbitmq.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "amq.topic";
    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @RabbitListener(queues = "server-queue")
    public void consume(NotificationMessage notification) {
        NotificationResponse response = notification.notificationSend(notificationService);
        sendMessage(response);
    }

    @RabbitListener(queues = "dead-letter-queue", ackMode = "MANUAL")
    public void deadLetterConsume(NotificationMessage notification) {
        NotificationResponse response = notification.notificationSend(notificationService);
        sendMessage(response);
    }

    private void sendMessage(NotificationResponse response) {
        try {
            String json = objectMapper.writeValueAsString(response);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, response.getRoutingKey(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
