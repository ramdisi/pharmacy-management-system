package com.ramdisi.erp.repository.impl;

import com.ramdisi.erp.db.DBConection;
import com.ramdisi.erp.model.entity.User;
import com.ramdisi.erp.repository.SystemUserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemUserRepositoryImpl implements SystemUserRepository {

    @Override
    public User getUserDetails(String userName) throws SQLException{
        Connection connection = DBConection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("select user_id,password,role from system_user where user_name='" + userName + "'").executeQuery();
        return resultSet.next()?new User(userName,resultSet.getString("password"),resultSet.getString("user_id"),resultSet.getString("role")):null;
    }
}
