package com.ramdisi.erp.service.impl;

import com.ramdisi.erp.model.dto.CashierStockDTO;
import com.ramdisi.erp.model.entity.Stock;
import com.ramdisi.erp.repository.StockRepository;
import com.ramdisi.erp.repository.impl.StockRepositoryImpl;
import com.ramdisi.erp.service.CashierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashierServiceImpl implements CashierService {
    private StockRepository stockRepository = new StockRepositoryImpl();
    @Override
    public ObservableList<CashierStockDTO> getAllStockDetails() throws SQLException {
        List<Stock> stockList=stockRepository.getAll();
        ObservableList<CashierStockDTO> stockDTOS = FXCollections.observableArrayList();
        for(Stock stock : stockList){
            stockDTOS.add(new CashierStockDTO(
                    stock.getId(),
                    stock.getBatchid(),
                    stock.getName(),
                    stock.getCatagory(),
                    stock.getAvailability(),
                    stock.getQtyOnHand(),
                    stock.getShelf_no(),
                    stock.getSelling_rate()
            ));
        }
        return stockDTOS;
    }
}
