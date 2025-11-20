package com.ramdisi.erp.service;

import com.ramdisi.erp.model.dto.CashierStockDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CashierService {
    ObservableList<CashierStockDTO> getAllStockDetails() throws SQLException;
}
