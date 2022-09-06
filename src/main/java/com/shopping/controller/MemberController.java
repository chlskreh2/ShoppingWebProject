package com.shopping.controller;

import com.shopping.domain.Member;
import com.shopping.dto.member.SaveMemberDto;
import com.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/join")
    public String memberJoinForm(@ModelAttribute SaveMemberDto form) {
        return "loginForm";
    }

    @PostMapping("/members/join")
    public String memberJoin(@Valid @ModelAttribute SaveMemberDto form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "loginForm";
        }

        Member member = form.saveMember();
        memberService.join(member);
        return "redirect:/";
    }
}
