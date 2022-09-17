package com.shopping.controller;

import com.shopping.domain.Item;
import com.shopping.dto.item.SortViewClothDto;
import com.shopping.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Value("${file.path}")
    private final String filePath;

    @GetMapping("/items/cloth")
    public String home(Model model) {
        List<SortViewClothDto> clothSortView = itemService.findClothSortView();
        model.addAttribute("clothes", clothSortView);
        return "item/itemsCloth";
    }

    @ResponseBody
    @GetMapping("/items/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + filePath+filename);
    }



}
