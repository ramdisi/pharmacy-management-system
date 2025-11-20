package com.ramdisi.erp.service;

import com.ramdisi.erp.model.dto.UserDTO;

import java.sql.SQLException;

public interface LoginService {
    int userValidation(UserDTO userDetails) throws SQLException;
}
