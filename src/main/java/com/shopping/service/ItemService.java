package com.shopping.service;

import com.shopping.dto.item.SortViewBookDto;
import com.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<SortViewBookDto> findBookSortView() {
        return itemRepository.findBookSortView();
    }

}
