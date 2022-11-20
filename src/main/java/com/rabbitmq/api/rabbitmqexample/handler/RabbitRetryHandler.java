package com.rabbitmq.api.rabbitmqexample.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "RabbitRetryHandler")
public class RabbitRetryHandler implements RabbitListenerErrorHandler {

    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        if (amqpMessage.getMessageProperties().isRedelivered()) {
            log.debug("error data => ", message.getPayload());
            throw new AmqpRejectAndDontRequeueException(exception);
        } else {
            throw exception;
        }
    }
}
