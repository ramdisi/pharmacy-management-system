package com.ramdisi.erp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    private static DBConection instance;
    private Connection connection;
    private DBConection() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy_db?createDatabaseIfNotExist=true","root","1234");
    }
    public static DBConection getInstance() throws SQLException{
        return instance==null?new DBConection():instance;
    }
    public Connection getConnection() throws SQLException{
        return connection;
    }
}
