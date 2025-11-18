package com.ramdisi.erp.repository.impl;

import com.ramdisi.erp.db.DBConection;
import com.ramdisi.erp.model.entity.Stock;
import com.ramdisi.erp.repository.StockRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockRepositoryImpl implements StockRepository {

    @Override
    public List<Stock> getAll() throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement("select stock_id,batch_id,name,category_name,buying_rate,selling_rate,availability,purchase_date,qty_purchased,qty_on_hand,shelf_no,admin_id,mfd,exp from stock as s,category as c where c.category_id=s.category_id").executeQuery();
        List<Stock> stockList = new ArrayList<>();
        while (resultSet.next()){
            stockList.add(new Stock(
                    resultSet.getString("stock_id"),
                    resultSet.getString("batch_id"),
                    resultSet.getString("name"),
                    resultSet.getString("category_name"),
                    resultSet.getString("availability"),
                    resultSet.getInt("qty_purchased"),
                    resultSet.getInt("qty_on_hand"),
                    resultSet.getString("shelf_no"),
                    resultSet.getString("admin_id"),
                    resultSet.getDate("mfd").toLocalDate(),
                    resultSet.getDate("exp").toLocalDate(),
                    resultSet.getDate("purchase_date").toLocalDate(),
                    resultSet.getDouble("buying_rate"),
                    resultSet.getDouble("selling_rate")
            ));
        }
        return stockList;
    }
}
