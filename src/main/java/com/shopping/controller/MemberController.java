package com.shopping.controller;

import com.shopping.domain.Member;
import com.shopping.dto.member.SaveMemberDto;
import com.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/members/join")
    public String memberJoinForm(@ModelAttribute(name = "form") SaveMemberDto form) {
        return "form/login/joinForm";
    }

    @PostMapping("/members/join")
    public String memberJoin(@Valid @ModelAttribute(name = "form") SaveMemberDto form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form/login/joinForm";
        }

        Member member = form.saveMember();
        memberService.join(member);
        return "redirect:/";
    }
}
