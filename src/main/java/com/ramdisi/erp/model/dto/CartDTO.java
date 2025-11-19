package com.ramdisi.erp.model.dto;

import lombok.*;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class CartDTO {
    private CashierStockDTO orderedItem;
    private Integer itemQTY;
}
