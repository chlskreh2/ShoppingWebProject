package com.shopping.service;

import com.shopping.domain.Address;
import com.shopping.domain.Delivery;
import com.shopping.domain.Order;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long save(Long memberId, Long itemId, Integer orderCount, Address address) {
        Delivery delivery = new Delivery();
        Order order = new Order();


        return orderRepository.save(order);
    }
}
