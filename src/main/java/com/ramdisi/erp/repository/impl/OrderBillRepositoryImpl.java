package com.ramdisi.erp.repository.impl;

import com.ramdisi.erp.db.DBConection;
import com.ramdisi.erp.model.entity.OrderBill;

import com.ramdisi.erp.repository.OrderBillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderBillRepositoryImpl implements OrderBillRepository {
    @Override
    public boolean addBillInfo(OrderBill orderBill) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into order_bill (order_id,purchased_stock_id,purchased_qty)values(?,?,?)");
        preparedStatement.setString(1,orderBill.getId());
        preparedStatement.setString(2,orderBill.getPurchasedStockId());
        preparedStatement.setInt(3,orderBill.getPurchasedQTY());
        return preparedStatement.executeUpdate()>0;
    }
}
