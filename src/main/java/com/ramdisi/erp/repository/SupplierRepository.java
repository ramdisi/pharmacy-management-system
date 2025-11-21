package com.ramdisi.erp.repository;

import com.ramdisi.erp.model.entity.Supplier;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SupplierRepository {
    void insertSupplier(Supplier supplier) throws SQLException;
    String getLastSupplierId() throws SQLException;
    void updateSupplier(Supplier supplier) throws SQLException;
    void deleteSupplier(Supplier supplier) throws SQLException;
    ObservableList<Supplier> getAllSuppliers() throws SQLException;
}
