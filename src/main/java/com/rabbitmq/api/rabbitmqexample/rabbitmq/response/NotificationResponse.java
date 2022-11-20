package com.rabbitmq.api.rabbitmqexample.rabbitmq.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    @JsonIgnore
    private String routingKey;
    private String contents;
}
