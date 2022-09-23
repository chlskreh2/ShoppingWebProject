package com.shopping.repository;

import com.shopping.domain.Member;

import java.util.List;

public interface MemberRepository {

    Long save(Member member);
    Member findById(Long id);
    List<Member> findAll();
    List<String> findByUserId(String userId);
    List<Member> findMemberByUserId(String userId);
}
