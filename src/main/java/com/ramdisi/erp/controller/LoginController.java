package com.ramdisi.erp.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.ramdisi.erp.model.dto.UserDTO;
import com.ramdisi.erp.service.LoginService;
import com.ramdisi.erp.service.impl.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    private Stage stage = new Stage();

    private PopupMassageController popup= new PopupMassageController();

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private JFXTextField txt_username;

    private LoginService loginService = new LoginServiceImpl();

    public void btn_onAction_login(ActionEvent actionEvent) {
        try {
            int response = loginService.userValidation(new UserDTO(txt_username.getText(),txt_password.getText()));
            switch (response){
                case 1:
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin.fxml"))));
                    stage.show();
                    //update this after creating super admin UI
                    break;
                case 2:
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin.fxml"))));
                    stage.show();
                    break;
                case 3:
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier.fxml"))));
                    stage.show();
                    break;
                default:
                    popup.setWindow("Invalid Username or Password \nPlease Try again");
            }
        } catch (IOException e) {
            popup.setWindow("Oops something went wrong \nError id : 001");
        } catch (SQLException e) {
            popup.setWindow("Oops something went wrong \nError id : 002");
        }finally {
            txt_password.setText(null);
            txt_username.setText(null);
        }
    }
}
