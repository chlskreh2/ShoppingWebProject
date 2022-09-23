package com.shopping.service;

import com.shopping.domain.Member;
import com.shopping.exception.SameUserIdException;
import com.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        List<String> userIds = memberRepository.findByUserId(member.getUserId());
        if (!userIds.isEmpty()) {
            throw new SameUserIdException("중복된 아이디가 있습니다.");
        }
        return memberRepository.save(member);
    }

    public List<Member> findMemberByUserId(String userId) {
        return memberRepository.findMemberByUserId(userId);
    }

    public Member findUser(Long id) {
        return memberRepository.findById(id);
    }
}
