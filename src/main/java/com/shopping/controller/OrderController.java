package com.shopping.controller;

import com.shopping.service.OrderItemService;
import com.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/items/pay/fail/{orderId}")
    public String payFail(@PathVariable Long orderId) {
        orderItemService.cancel(orderId);
        orderService.cancel(orderId);
        return "loginHome";
    }

    @GetMapping("/items/pay/cancel/{orderId}")
    public String payCancel(@PathVariable Long orderId) {
        orderItemService.cancel(orderId);
        orderService.cancel(orderId);
        return "loginHome";
    }


}
