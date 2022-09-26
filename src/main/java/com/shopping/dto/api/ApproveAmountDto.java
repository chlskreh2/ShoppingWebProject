package com.shopping.dto.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveAmountDto {

    private Integer total;
    private Integer tax_free;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer green_deposit;

}
