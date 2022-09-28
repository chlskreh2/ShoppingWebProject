package com.shopping.service;

import com.shopping.domain.*;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.OrderItemRepository;
import com.shopping.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long save(Member member, Long itemId, Integer orderCount, Address address) {
        Delivery delivery = new Delivery(address, DeliveryStatus.READY);
        Order order = new Order(member, delivery);
        orderRepository.save(order);

        Item item = itemRepository.findById(itemId);
        int discount = item.getPrice() * orderCount * item.getDiscountPercent() / 100;
        int orderPrice = item.getPrice() * orderCount - discount + item.getDeliveryPrice();
        item.setStock(item.getStock() - orderCount);

        OrderItem orderItem = new OrderItem(orderPrice, orderCount, order, item);
        orderItemRepository.save(orderItem);

        return order.getId();
    }

    @Transactional
    public void cancel(Long orderId) {
        orderRepository.delete(orderId);
    }
}
