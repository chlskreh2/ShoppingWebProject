package com.shopping.controller;

import com.shopping.domain.Basket;
import com.shopping.domain.Item;
import com.shopping.domain.Member;
import com.shopping.repository.BasketRepository;
import com.shopping.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BasketController {

    private final BasketRepository basketRepository;
    private final ItemService itemService;

    @GetMapping("/items/mybag/book/{bookId}")
    public String putMyBag(@PathVariable Long bookId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(MemberSession.LOGIN_SESSION);
        Item item = itemService.showItem(bookId);
        Basket basket = new Basket(member, item);
        basketRepository.save(basket);

        return "redirect:/items/book/" + bookId;
    }

    @GetMapping("/mybag")
    public String showMyBag(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(MemberSession.LOGIN_SESSION);
        List<Basket> baskets = basketRepository.findByMemberId(member.getId());
        model.addAttribute("baskets", baskets);
        return "showMyBag";
    }

}
