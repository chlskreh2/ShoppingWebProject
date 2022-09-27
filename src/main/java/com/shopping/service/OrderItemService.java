package com.shopping.service;

import com.shopping.domain.OrderItem;
import com.shopping.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Long save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

}
