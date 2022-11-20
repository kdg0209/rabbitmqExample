package com.rabbitmq.api.rabbitmqexample.member.repository;


import com.rabbitmq.api.rabbitmqexample.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
