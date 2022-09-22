package com.shopping.service;

import com.shopping.domain.Address;
import com.shopping.domain.Member;
import com.shopping.dto.member.SaveMemberDto;
import com.shopping.exception.SameUserIdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    private MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    void save() {
        SaveMemberDto saveMemberDto = new SaveMemberDto("java", "1234", "01011111111",20001212,
                "남", new Address("서울특별시 중랑구", "아파트 1층 101호"));
        Member member = saveMemberDto.saveMember();
        Long saveId = memberService.join(member);
        Member findMember = memberService.findUser(saveId);

        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void duplicateUserId() {
        SaveMemberDto saveMemberDto = new SaveMemberDto("java", "1234", "01011111111",20001212,
                "남", new Address("서울특별시 중랑구", "아파트 1층 101호"));
        Member member = saveMemberDto.saveMember();
        Long saveId = memberService.join(member);

        SaveMemberDto saveMemberDto2 = new SaveMemberDto("java", "1234", "01011111111",20001212,
                "남", new Address("서울특별시 중랑구", "아파트 1층 101호"));
        Member member2 = saveMemberDto2.saveMember();

        Assertions.assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(SameUserIdException.class);
    }

}