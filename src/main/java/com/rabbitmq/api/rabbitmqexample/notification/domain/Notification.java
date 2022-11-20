package com.rabbitmq.api.rabbitmqexample.notification.domain;

import com.rabbitmq.api.rabbitmqexample.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "contents")
    private String contents;

    private LocalDateTime createdDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Notification(NotificationType notificationType, String contents, Member member) {
        this.notificationType = notificationType;
        this.contents = contents;
        this.member = member;
        this.createdDateTime = LocalDateTime.now();
    }
}
