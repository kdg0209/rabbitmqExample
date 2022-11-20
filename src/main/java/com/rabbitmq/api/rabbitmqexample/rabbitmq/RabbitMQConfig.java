package com.rabbitmq.api.rabbitmqexample.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue() {
        return QueueBuilder.durable("server-queue")
                .deadLetterExchange("deadLetterExchange")
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable("dead-letter-queue").build();
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange("deadLetterExchange");
    }

    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange());
    }

    @Bean
    Binding deadLetterQueueBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(deadLetterExchange());
    }

    @Bean("rabbitTemplate")
    RabbitTemplate rabbitTemplate(@Qualifier("RabbitConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Primary
    @Bean("RabbitConnectionFactory")
    @ConfigurationProperties("spring.rabbitmq")
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        return connectionFactory;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}