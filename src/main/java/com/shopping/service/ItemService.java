package com.shopping.service;

import com.shopping.domain.Item;
import com.shopping.dto.item.SortViewClothDto;
import com.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<SortViewClothDto> findClothSortView() {
        return itemRepository.findClothSortView();
    }

}
