package com.ramdisi.erp.repository.impl;

import com.ramdisi.erp.db.DBConection;
import com.ramdisi.erp.model.entity.User;
import com.ramdisi.erp.repository.CashierRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierRepositoryImpl implements CashierRepository {
    @Override
    public String getCashierIdByUserDetails(User user) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("select cashier_id from cashier where user_id=(select user_id from system_user where user_name='" + user.getName() + "')").executeQuery();
        if (resultSet.next()){
            return resultSet.getString("cashier_id");
        }else{
            return null;
        }
    }
}
