package com.shopping.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApprovePaymentDto {
    private String aid;
    private String tid;
    private String cid;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private String item_name;
    private Integer quantity;
    private ApproveAmountDto amount;
    private LocalDateTime created_at;
    private LocalDateTime approved_at;
}
