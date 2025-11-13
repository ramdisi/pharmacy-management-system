package com.ramdisi.erp.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupMassageController {

    @FXML
    private Label lbl_massageArea;

    private static Stage stage = new Stage();

    @FXML
    void btn_onAction_ok(ActionEvent event) {
        stage.close();
    }
    public void setWindow(String massage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popup-massage.fxml"));
            Parent root = fxmlLoader.load();
            PopupMassageController popupMassageController = fxmlLoader.getController();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            popupMassageController.lbl_massageArea.setText(massage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
