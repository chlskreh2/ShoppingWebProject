package com.shopping.controller;

import com.shopping.domain.Book;
import com.shopping.domain.Item;
import com.shopping.domain.Member;
import com.shopping.dto.item.SaveBookDto;
import com.shopping.dto.item.SortViewBookDto;
import com.shopping.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

import static com.shopping.controller.MemberSession.LOGIN_SESSION;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Value("${file.path}")
    private String filePath;

    @GetMapping("/items/book")
    public String saveBookForm(@ModelAttribute(name = "form") SaveBookDto form) {
        return "item/saveForm";
    }

    @PostMapping("/items/book")
    public String saveBook(@ModelAttribute(name = "form") SaveBookDto form) {
        Book book = form.saveBook();
        itemService.save(book);
        return "item/saveForm";
    }

    @GetMapping("/items/sort/book")
    public String sortBook(@SessionAttribute(name = LOGIN_SESSION, required = false) Member loginMember, Model model) {
        List<SortViewBookDto> bookSortView = itemService.findBookSortView();
        model.addAttribute("books", bookSortView);
        if (loginMember == null) {
            return "item/itemsBook";
        }
        return "item/loginItemsBook";
    }

    //미래에 도서 이외의 다른 상품 추가 할 수 있으니 model 에 변수명을 item 으로 추가
    @GetMapping("/items/book/{bookId}")
    public String detailBook(@SessionAttribute(name = LOGIN_SESSION, required = false) Member loginMember,
                                @PathVariable Long bookId, Model model) {
        Item item = itemService.showItem(bookId);
        model.addAttribute("item", item);
        if (loginMember == null) {
            return "item/detailBook";
        }
        return "item/loginDetailBook";
    }

    @GetMapping("/items/buy/book/{bookId}")
    public String buyBook(@PathVariable Long bookId, @RequestParam("orderCount") Integer orderCount, Model model) {
        Item item = itemService.showItem(bookId);
        model.addAttribute("item", item);
        if (item.getStock() <= 0) {
            return "item/loginDetailBook";
        }
        int itemsPrice = item.getPrice() * orderCount;
        int discountPrice = itemsPrice * item.getDiscountPercent() / 100;
        int totalPrice = itemsPrice - discountPrice + item.getDeliveryPrice();
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("discountPrice", discountPrice);
        model.addAttribute("totalPrice", totalPrice);
        return "item/buyBook";
    }

    @ResponseBody
    @GetMapping("/items/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + filePath + filename);
    }



}
