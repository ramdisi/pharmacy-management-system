package com.ramdisi.erp.model.dto;

import lombok.*;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class CartDTO {
    private String id;
    private String batchid;
    private String name;
    private Double pricePerItem;
    private Integer purchsedQTY;
    private Double total;
}
