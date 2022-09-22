package com.shopping.dto.item;

import com.shopping.domain.FileImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortViewBookDto {

    private String itemName;
    private Integer price;
    private Long viewCount;
    private FileImage fileImage;

    public SortViewBookDto(String itemName, Integer price, Long viewCount, FileImage fileImage) {
        this.itemName = itemName;
        this.price = price;
        this.viewCount = viewCount;
        this.fileImage = fileImage;
    }
}
