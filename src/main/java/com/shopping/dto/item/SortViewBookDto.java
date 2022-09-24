package com.shopping.dto.item;

import com.shopping.domain.FileImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SortViewBookDto {

    private Long id;
    private String itemName;
    private Integer price;
    private Long viewCount;
    private FileImage fileImage;

}
