package com.ramdisi.erp.repository;

import com.ramdisi.erp.model.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockRepository {
    List<Stock> getAll() throws SQLException;
}
