package com.shopping.dto.member;

import com.shopping.domain.Address;
import com.shopping.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveMemberDto {

    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    @NotNull
    private Integer birth;
    @NotBlank
    private String gender;
    @NotNull
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
