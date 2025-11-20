package com.ramdisi.erp.service.impl;

import com.mysql.cj.util.StringUtils;
import com.ramdisi.erp.db.DBConection;
import com.ramdisi.erp.model.dto.CartDTO;
import com.ramdisi.erp.model.dto.UserDTO;
import com.ramdisi.erp.model.entity.OrderBill;
import com.ramdisi.erp.model.entity.OrderInfo;
import com.ramdisi.erp.model.entity.User;
import com.ramdisi.erp.repository.CashierRepository;
import com.ramdisi.erp.repository.OrderBillRepository;
import com.ramdisi.erp.repository.OrderInfoRepository;
import com.ramdisi.erp.repository.StockRepository;
import com.ramdisi.erp.repository.impl.CashierRepositoryImpl;
import com.ramdisi.erp.repository.impl.OrderBillRepositoryImpl;
import com.ramdisi.erp.repository.impl.OrderInfoRepositoryImpl;
import com.ramdisi.erp.repository.impl.StockRepositoryImpl;
import com.ramdisi.erp.service.CheckoutService;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CheckoutServiceImpl implements CheckoutService {
    OrderBillRepository orderBillRepo = new OrderBillRepositoryImpl();
    OrderInfoRepository orderInfoRepo = new OrderInfoRepositoryImpl();
    CashierRepository cashierRepo = new CashierRepositoryImpl();
    StockRepository stockRepo = new StockRepositoryImpl();

    @Override
    public void insertTransaction(ObservableList<CartDTO> cartDTOs, String custelNo,UserDTO cashierAuth,double discount) throws SQLException {
        Connection connection = DBConection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String orderID = getNextOrderID();
            String cashierID = cashierRepo.getCashierIdByUserDetails(new User(cashierAuth.getName(), cashierAuth.getPassword(), null, null));
            boolean isOrderInfoAdded = orderInfoRepo.addOrderInfo(new OrderInfo(
                    orderID,
                    custelNo,
                    discount,
                    cashierID,
                    LocalDate.now(),
                    LocalTime.now()
            ));
            boolean isOrderBillAdded = false;
            boolean isStockUpdated = false;
            if (isOrderInfoAdded) {
                for (CartDTO item : cartDTOs) {
                    isOrderBillAdded = orderBillRepo.addBillInfo(new OrderBill(orderID, item.getId(), item.getPurchsedQTY()));
                    isStockUpdated = stockRepo.updateQTY(item.getId(),item.getPurchsedQTY());
                    if (!isOrderBillAdded | !isStockUpdated) {
                        break;
                    }
                }
            }
            if (isOrderBillAdded & isStockUpdated) {
                connection.commit();
            }
        }catch (SQLException e){
            connection.rollback();
            throw new SQLException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private String getNextOrderID() throws SQLException{
        String latestID = orderInfoRepo.getLastOrderId();
        if (latestID==null){
            return "O000001";
        }else {
            return String.format("O%06d",Integer.parseInt(latestID.substring(1))+1);
        }
    }
}
