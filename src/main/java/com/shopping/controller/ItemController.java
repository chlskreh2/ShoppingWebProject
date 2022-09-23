package com.shopping.controller;

import com.shopping.domain.Book;
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

    @GetMapping("/items/sort/book")
    public String home(@SessionAttribute(name = LOGIN_SESSION, required = false) Member loginMember, Model model) {
        if (loginMember == null) {
            List<SortViewBookDto> bookSortView = itemService.findBookSortView();
            model.addAttribute("books", bookSortView);
            return "item/itemsBook";
        }

        List<SortViewBookDto> bookSortView = itemService.findBookSortView();
        model.addAttribute("books", bookSortView);
        return "item/loginItemsBook";
    }

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

    @ResponseBody
    @GetMapping("/items/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + filePath+filename);
    }



}
