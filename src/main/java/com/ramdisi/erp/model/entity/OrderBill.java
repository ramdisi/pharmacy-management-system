package com.ramdisi.erp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderBill {
    private String id;
    private String purchasedStockId;
    private int purchasedQTY;
}
