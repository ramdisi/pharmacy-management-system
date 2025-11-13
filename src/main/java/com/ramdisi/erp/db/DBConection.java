package com.ramdisi.erp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    private static DBConection instance;
    private Connection connection;
    private DBConection() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy_management_system","root","1234");
    }
    public static DBConection getInstance() throws SQLException{
        return instance==null?instance = new DBConection():instance;
    }
    public Connection getConnection() throws SQLException{
        return connection;
    }
}
