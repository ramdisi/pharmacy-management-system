package com.ramdisi.erp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class Supplier {
    private String id;
    private String name;
    private String address;
    private String email;
}
