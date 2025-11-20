package com.ramdisi.erp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OrderInfo {
    private String id;
    private String custTelNo;
    private Double discount;
    private String cashierId;
    private LocalDate orderDate;
    private LocalTime orderTime;
}
