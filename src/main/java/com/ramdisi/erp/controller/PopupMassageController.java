package com.ramdisi.erp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class PopupMassageController{

    @FXML
    private Label lbl_massageArea;

    private static Stage stage = new Stage();

    private static PopupMassageController popupMassageController;

    @FXML
    void btn_onAction_ok(ActionEvent event) {
        stage.close();
    }

    public void setWindow(String massage) {
        if(popupMassageController==null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/popup-massage.fxml"));
                Parent root = fxmlLoader.load();
                popupMassageController = fxmlLoader.getController();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setAlwaysOnTop(true);
            } catch (IOException e) {
                Platform.exit();
            }
        }
        popupMassageController.lbl_massageArea.setText(massage);
        stage.show();
    }
}
