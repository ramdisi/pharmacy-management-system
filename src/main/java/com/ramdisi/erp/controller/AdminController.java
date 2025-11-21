package com.ramdisi.erp.controller;

import com.ramdisi.erp.model.dto.UserDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Label lbl_numberOfNotifications;

    @FXML
    private Label lbl_time;

    private static Stage currentStage;

    private Stage featureStage = new Stage();

    private static UserDTO adminAuth;

    private PopupMassageController popup =new PopupMassageController();

    @FXML
    void btn_onAction_promotionCampaign(ActionEvent event) {

    }

    @FXML
    void btn_onAction_salesStatics(ActionEvent event) {

    }

    @FXML
    void btn_onAction_stockManagement(ActionEvent event) {

    }

    @FXML
    void btn_onAction_supplierManagement(ActionEvent event) {
        try {
            SupplierController.saveCurrentStage(featureStage);
            featureStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/suppliers.fxml"))));
            featureStage.show();
            currentStage.close();
        } catch (IOException e) {
            popup.setWindow("Oops something went wrong \n Error ID : 006");
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btn_onAction_systemUserManagement(ActionEvent event) {

    }

    @FXML
    void img_onClick_logout(MouseEvent event) {
        currentStage.close();
        LoginController.showCurrentStage();
    }

    @FXML
    void img_onClick_viewNotifications(MouseEvent event) {

    }

    public static void saveCurrentStage(Stage stage){
        currentStage=stage;
    }

    private void runClock() {
        lbl_time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh : mm  a")));
        Timeline clockJob = new Timeline(new KeyFrame(Duration.minutes(1), e-> lbl_time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh : mm  a")))));
        clockJob.setCycleCount(Animation.INDEFINITE);
        clockJob.play();
    }
    public static void setAdminDetails(UserDTO user){
        adminAuth = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runClock();
    }

    public static void showCurrentStage(){
        currentStage.show();
    }
}
