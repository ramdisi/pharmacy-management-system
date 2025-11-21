package com.ramdisi.erp.repository.impl;

import com.ramdisi.erp.db.DBConection;
import com.ramdisi.erp.model.entity.Supplier;
import com.ramdisi.erp.repository.SupplierRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRepositoryImpl implements SupplierRepository {
    @Override
    public void insertSupplier(Supplier supplier) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into supplier (supplier_id,name,email,address)values(?,?,?,?)");
        preparedStatement.setString(1,supplier.getId());
        preparedStatement.setString(2,supplier.getName());
        preparedStatement.setString(3,supplier.getEmail());
        preparedStatement.setString(4,supplier.getAddress());
        preparedStatement.execute();
    }

    @Override
    public String getLastSupplierId() throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("select supplier_id from supplier order by supplier_id desc limit 1").executeQuery();
        if (resultSet.next()){
            return resultSet.getString("supplier_id");
        }else {
            return null;
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update supplier set email=?,address=?,name=? where supplier_id=?");
        preparedStatement.setString(4,supplier.getId());
        preparedStatement.setString(3,supplier.getName());
        preparedStatement.setString(1,supplier.getEmail());
        preparedStatement.setString(2,supplier.getAddress());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteSupplier(Supplier supplier) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        connection.prepareStatement("delete from supplier where supplier_id='"+supplier.getId()+"'").execute();
    }

    @Override
    public ObservableList<Supplier> getAllSuppliers() throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("select * from supplier").executeQuery();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        while (resultSet.next()){
            suppliers.add(new Supplier(
                    resultSet.getString("supplier_id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("email")
            ));
        }
        return suppliers;
    }
}
