package com.ramdisi.erp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class CashierStockDTO {
    private String id;
    private String batchid;
    private String name;
    private String catagory;
    private String availability;
    private int availableQTY;
    private String shelf_no;
    private Double pricePerItem;
}
