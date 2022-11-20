package com.rabbitmq.api.rabbitmqexample.member.domain;

import com.rabbitmq.api.rabbitmqexample.notification.domain.Notification;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @OneToMany(mappedBy = "member")
    private List<Notification> notifications = new ArrayList<>();

    @Builder
    public Member(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}