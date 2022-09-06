package com.shopping.dto.member;

import com.shopping.domain.Address;
import com.shopping.domain.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveMemberDto {

    private String userId;
    private String password;
    private Integer phone;
    private Integer birth;
    private String gender;
    private Address address;

    public Member saveMember() {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(password);
        member.setPhone(phone);
        member.setBirth(birth);
        member.setGender(gender);
        member.setAddress(address);
        return member;
    }


}
