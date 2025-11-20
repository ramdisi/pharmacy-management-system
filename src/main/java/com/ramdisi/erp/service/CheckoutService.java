package com.ramdisi.erp.service;

import com.ramdisi.erp.model.dto.CartDTO;
import com.ramdisi.erp.model.dto.UserDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CheckoutService {
    void insertTransaction(ObservableList<CartDTO> cartDTOs, String custelNo, UserDTO cashierAuth,double discount) throws SQLException;
}
