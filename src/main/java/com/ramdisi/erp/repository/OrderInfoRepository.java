package com.ramdisi.erp.repository;

import com.ramdisi.erp.model.entity.OrderInfo;

import java.sql.SQLException;

public interface OrderInfoRepository {
    boolean addOrderInfo(OrderInfo orderInfo) throws SQLException;

    String getLastOrderId() throws SQLException;
}
