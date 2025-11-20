package com.ramdisi.erp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Stock {
    private String id;
    private String batchid;
    private String name;
    private String catagory;
    private String availability;
    private int qtyPurchased,qtyOnHand;
    private String shelf_no;
    private String adminId;
    private LocalDate mfd,exp,purchasedDate;
    private Double buyingRate,selling_rate;
}
