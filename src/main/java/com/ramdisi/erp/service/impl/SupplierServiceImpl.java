package com.ramdisi.erp.service.impl;

import com.ramdisi.erp.model.dto.SupplierDTO;
import com.ramdisi.erp.model.entity.Supplier;
import com.ramdisi.erp.repository.SupplierRepository;
import com.ramdisi.erp.repository.impl.SupplierRepositoryImpl;
import com.ramdisi.erp.service.SupplierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepo = new SupplierRepositoryImpl();

    @Override
    public void addSupplier(Supplier supplier) throws SQLException{
        String supplierID = getNextSupplierId();
        supplierRepo.insertSupplier(new Supplier(supplierID,supplier.getName(),supplier.getAddress(),supplier.getEmail()));
    }

    private String getNextSupplierId() throws SQLException{
        String lastSupplierId = supplierRepo.getLastSupplierId();
        if (lastSupplierId==null){
            return "S001";
        }else {
            return String.format("S%03d", Integer.parseInt(lastSupplierId.substring(1)) + 1);
        }
    }

    @Override
    public void deleteSupplier(Supplier supplier) throws SQLException{
        supplierRepo.deleteSupplier(new Supplier(supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getEmail()));
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException{
        supplierRepo.updateSupplier(new Supplier(supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getEmail()));
    }

    @Override
    public ObservableList<SupplierDTO> getAllSuppliers() throws SQLException{
        ObservableList<Supplier> supplierObservableList = supplierRepo.getAllSuppliers();
        ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList();
        for (Supplier supplier : supplierObservableList){
            supplierDTOS.add(new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getEmail()));
        }
        return supplierDTOS;
    }
}
