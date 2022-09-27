package com.shopping.service;

import com.shopping.domain.Item;
import com.shopping.dto.item.SortViewBookDto;
import com.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    @Transactional
    public Long save(Item item) {
        return itemRepository.save(item);
    }

    public Item showItem(Long id) {
        return itemRepository.findById(id);
    }

    public List<SortViewBookDto> findBookSortView() {
        return itemRepository.findBookSortView();
    }

}
