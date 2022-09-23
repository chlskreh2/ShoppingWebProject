package com.shopping.controller;

import com.shopping.domain.Member;
import com.shopping.dto.member.LoginMemberDto;
import com.shopping.dto.member.SaveMemberDto;
import com.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static com.shopping.controller.MemberSession.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = LOGIN_SESSION, required = false) Member loginMember, Model model) {
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/members/login")
    public String memberLoginForm(@ModelAttribute(name = "form") LoginMemberDto form) {
        return "form/login/loginForm";
    }

    @PostMapping("/members/login")
    public String memberLogin(@Valid @ModelAttribute(name = "form") LoginMemberDto form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "form/login/loginForm";
        }

        List<Member> member = memberService.findMemberByUserId(form.getUserId());
        if (member.isEmpty() || !form.getPassword().equals(member.get(0).getPassword())) {
            return "form/login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_SESSION, member.get(0));
        return "redirect:/";
    }

    @GetMapping("/members/logout")
    public String memberLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        return "redirect:/";
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
