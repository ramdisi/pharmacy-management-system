package com.ramdisi.erp.repository;

import com.ramdisi.erp.model.entity.User;

import java.sql.SQLException;

public interface CashierRepository {
    String getCashierIdByUserDetails(User user) throws SQLException;
}
