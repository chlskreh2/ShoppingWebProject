package com.shopping.controller;

import com.shopping.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

}
