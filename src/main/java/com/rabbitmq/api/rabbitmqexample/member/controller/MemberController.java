package com.rabbitmq.api.rabbitmqexample.member.controller;

import com.rabbitmq.api.rabbitmqexample.member.dto.MemberCreateDTO;
import com.rabbitmq.api.rabbitmqexample.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void create(@RequestBody MemberCreateDTO request) {
        memberService.create(request);
    }
}
