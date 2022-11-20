package com.rabbitmq.api.rabbitmqexample.member.service;

import com.rabbitmq.api.rabbitmqexample.handler.MemberCouponEventHandler;
import com.rabbitmq.api.rabbitmqexample.handler.NotificationEventHandler;
import com.rabbitmq.api.rabbitmqexample.handler.NotificationMessage;
import com.rabbitmq.api.rabbitmqexample.member.domain.Member;
import com.rabbitmq.api.rabbitmqexample.member.dto.MemberCreateDTO;
import com.rabbitmq.api.rabbitmqexample.member.repository.MemberRepository;
import com.rabbitmq.api.rabbitmqexample.notification.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void create(MemberCreateDTO request) {
        Member member = Member.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .build();

        memberRepository.save(member); // 회원가입 완료
        System.out.println(request.getName() + " 회원가입 완료");
        applicationEventPublisher.publishEvent(new NotificationMessage(new NotificationEventHandler(), NotificationType.JOIN, member));
        applicationEventPublisher.publishEvent(new NotificationMessage<>(new MemberCouponEventHandler(), NotificationType.COUPON, member));
        System.out.println(request.getName() + "님에게 메시지를 전송했습니다.");
    }
}
