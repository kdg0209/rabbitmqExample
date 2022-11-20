package com.rabbitmq.api.rabbitmqexample.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.api.rabbitmqexample.handler.NotificationMessage;
import com.rabbitmq.api.rabbitmqexample.member.domain.Member;
import com.rabbitmq.api.rabbitmqexample.notification.domain.Notification;
import com.rabbitmq.api.rabbitmqexample.notification.domain.NotificationType;
import com.rabbitmq.api.rabbitmqexample.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final ObjectMapper objectMapper;
    private final NotificationRepository notificationRepository;

    public Notification save(NotificationMessage message) {
        Member member = objectMapper.convertValue(message.getData(), Member.class);
        String contents = messageContents(message.getNotificationType(), member.getName());

        Notification notification = Notification.builder()
                                .notificationType(message.getNotificationType())
                                .contents(contents)
                                .member(member)
                                .build();
        notificationRepository.save(notification);
        return notification;
    }

    private String messageContents(NotificationType notificationType, String name) {
        switch (notificationType) {
            case JOIN:
                return name + "님 가입을 축하드립니다.";
            case COUPON:
                return name + "님 쿠폰을 보내드립니다.";
            default:
                throw new IllegalArgumentException("처히할 수 없는 매개변수 입니다.");
        }
    }
}
