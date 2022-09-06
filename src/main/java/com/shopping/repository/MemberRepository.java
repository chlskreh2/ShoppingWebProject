package com.shopping.repository;

import com.shopping.domain.Member;

import java.util.List;

public interface MemberRepository {

    public Long save(Member member);
    public Member findById(Long id);
    public List<Member> findAll();
    public List<String> findByUserId(String userId);

}
