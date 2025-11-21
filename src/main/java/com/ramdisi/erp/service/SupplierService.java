package com.ramdisi.erp.service;

import com.ramdisi.erp.model.dto.SupplierDTO;
import com.ramdisi.erp.model.entity.Supplier;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SupplierService {
    void addSupplier(Supplier supplier) throws SQLException;
    void deleteSupplier(Supplier supplier) throws SQLException;
    void updateSupplier(Supplier supplier) throws SQLException;
    ObservableList<SupplierDTO> getAllSuppliers() throws SQLException;
}
