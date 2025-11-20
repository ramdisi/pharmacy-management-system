package com.ramdisi.erp.service.impl;

import com.ramdisi.erp.model.dto.UserDTO;
import com.ramdisi.erp.model.entity.User;
import com.ramdisi.erp.repository.SystemUserRepository;
import com.ramdisi.erp.repository.impl.SystemUserRepositoryImpl;
import com.ramdisi.erp.service.LoginService;

import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {
    private SystemUserRepository userRepository = new SystemUserRepositoryImpl();

    @Override
    public int userValidation(UserDTO userDetails) throws SQLException {
        User user = userRepository.getUserDetails(userDetails.getName());
        if (user!=null) {
            if (user.getPassword().equals(userDetails.getPassword())) {
                switch (user.getRole()) {
                    case "super_admin":
                        return 1;
                    case "admin":
                        return 2;
                    case "cashier":
                        return 3;
                }
            }
        }
        return -1;
    }
}
