package com.ramdisi.erp.repository;

import com.ramdisi.erp.model.entity.OrderBill;

import java.sql.SQLException;

public interface OrderBillRepository {
    boolean addBillInfo(OrderBill orderBill) throws SQLException;
}
