package com.shopping.service;

import com.shopping.domain.Address;
import com.shopping.domain.Member;
import com.shopping.dto.member.SaveMemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    private MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    void save() {
        SaveMemberDto saveMemberDto = new SaveMemberDto("java", "1234", 01011111111,20001212,
                "남", new Address("서울특별시 중랑구", "자바로 1길 23", "아파트 1층 101호"));
        Member member = saveMemberDto.saveMember();
        Long saveId = memberService.join(member);
        Member findMember = memberService.findUser(saveId);

        Assertions.assertEquals(member, findMember);
    }

}