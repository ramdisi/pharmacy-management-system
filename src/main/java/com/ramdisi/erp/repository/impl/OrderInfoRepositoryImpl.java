package com.ramdisi.erp.repository.impl;

import com.ramdisi.erp.db.DBConection;
import com.ramdisi.erp.model.entity.OrderInfo;
import com.ramdisi.erp.repository.OrderInfoRepository;

import java.sql.*;

public class OrderInfoRepositoryImpl implements OrderInfoRepository {
    @Override
    public boolean addOrderInfo(OrderInfo orderInfo) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into order_info (order_id,customer_tel_no,discount,cashier_id,order_date,order_time)values(?,?,?,?,?,?)");
        preparedStatement.setString(1,orderInfo.getId());
        preparedStatement.setString(2,orderInfo.getCustTelNo());
        preparedStatement.setDouble(3,orderInfo.getDiscount());
        preparedStatement.setString(4,orderInfo.getCashierId());
        preparedStatement.setDate(5, Date.valueOf(orderInfo.getOrderDate()));
        preparedStatement.setTime(6, Time.valueOf(orderInfo.getOrderTime()));
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public String getLastOrderId() throws SQLException{
        Connection connection = DBConection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("select order_id from order_info order by order_id desc limit 1").executeQuery();
        if (resultSet.next()){
            return resultSet.getString("order_id");
        }else {
            return null;
        }
    }
}
