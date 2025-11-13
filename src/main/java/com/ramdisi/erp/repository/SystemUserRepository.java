package com.ramdisi.erp.repository;

import com.ramdisi.erp.model.entity.User;

import java.sql.SQLException;

public interface SystemUserRepository {
    User getUserDetails(String userName) throws SQLException;
}
