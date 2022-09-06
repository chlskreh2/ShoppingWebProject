package com.shopping.service;

import com.shopping.domain.Member;
import com.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        return memberRepository.save(member);
    }

    public Member findUser(Long id) {
        return memberRepository.findById(id);
    }
}
