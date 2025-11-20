package com.ramdisi.erp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.ramdisi.erp.model.dto.CartDTO;
import com.ramdisi.erp.model.dto.UserDTO;
import com.ramdisi.erp.service.CheckoutService;
import com.ramdisi.erp.service.impl.CheckoutServiceImpl;
import com.ramdisi.erp.validation.Validator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {

    @FXML
    private JFXButton btn_addAnotherItemToBill;

    @FXML
    private JFXButton btn_cancelOrder;

    @FXML
    private JFXButton btn_newOrder;

    @FXML
    private JFXButton btn_proceedPayment;

    @FXML
    private JFXButton btn_removeSelectedFromCart;

    @FXML
    private TableColumn<?, ?> col_batchId;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_pricePerItem;

    @FXML
    private TableColumn<?, ?> col_quantity;

    @FXML
    private TableColumn<?, ?> col_total;

    @FXML
    private Label lbl_amountAfterDiscount;

    @FXML
    private Label lbl_cashierName;

    @FXML
    private Label lbl_time;

    @FXML
    private TableView<CartDTO> tblView_bill;

    @FXML
    private JFXTextField txt_payment;

    @FXML
    private JFXTextField txt_telNo;

    @FXML
    private Label lbl_balance;

    private static Stage currenStage;

    private static UserDTO cashierAuth;

    private static ObservableList<CartDTO> cartDTOs;

    private Double total;

    private CartDTO selectedItem;

    private PopupMassageController popup = new PopupMassageController();

    private CheckoutService checkoutService = new CheckoutServiceImpl();

    private double discount = 0.0;

    @FXML
    void btn_onAction_addAnotherItemToBill(ActionEvent event) {
        currenStage.close();
        CashierController.showCurrentStage();
    }

    @FXML
    void btn_onAction_cancelOrder(ActionEvent event) {
        txt_payment.setText(null);
        txt_telNo.setText(null);
        lbl_balance.setText("Balance ");
        currenStage.close();
        CashierController.showCurrentStage();
        CashierController.cancelOrder();
    }

    @FXML
    void btn_onAction_newOrder(ActionEvent event) {
        txt_payment.setText(null);
        txt_telNo.setText(null);
        btn_proceedPayment.setDisable(false);
        btn_cancelOrder.setDisable(false);
        btn_addAnotherItemToBill.setDisable(false);
        btn_removeSelectedFromCart.setDisable(false);
        lbl_balance.setText("Balance ");
        currenStage.close();
        CashierController.showCurrentStage();
        CashierController.cancelOrder();
    }

    @FXML
    void btn_onAction_proceedPayment(ActionEvent event) {
        switch (Validator.checkEligabilityToPay(txt_telNo.getText(),txt_payment.getText(),total)){
            case 0:
                popup.setWindow("Enter a Correct Telephone Number Again!");
                txt_telNo.setText(null);
                break;
            case 1:
                popup.setWindow("Enter payment in Rupees.cents format");
                txt_payment.setText(null);
                break;
            case 2:
                popup.setWindow("Payment is Lower than Amount \n Try again!");
                txt_payment.setText(null);
                break;
            case 3:
                try {
                    checkoutService.insertTransaction(cartDTOs,txt_telNo.getText(),cashierAuth,discount);
                    popup.setWindow("Order Placed Successfully");
                    lbl_balance.setText(String.format("Balance Rs.%.2f",Double.parseDouble(txt_payment.getText())-total));
                    txt_telNo.setText(null);
                    txt_payment.setText(null);
                    btn_proceedPayment.setDisable(true);
                    btn_cancelOrder.setDisable(true);
                    btn_addAnotherItemToBill.setDisable(true);
                    btn_removeSelectedFromCart.setDisable(true);
                } catch (SQLException e) {
                    popup.setWindow("Something went wrong.Order didn't Placed.Try Again with new Order.\nError Id:005");
                    throw new RuntimeException(e);
                }
        }
    }

    @FXML
    void btn_onAction_removeSelectedFromCart(ActionEvent event) {
        cartDTOs.remove(selectedItem);
        loadTable();
    }

    @FXML
    void tbl_onClick_selectOrderedItem(MouseEvent event) {
        selectedItem=tblView_bill.getSelectionModel().getSelectedItem();
    }

    public static void saveDetails(Stage stage, ObservableList<CartDTO> cart, UserDTO cashierDetails){
        currenStage = stage;
        cashierAuth = cashierDetails;
        cartDTOs = cart;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        runClock();
        lbl_cashierName.setText(cashierAuth.getName().toUpperCase());
    }

    private void countAmount() {
        total=0.00;
        for (CartDTO item: cartDTOs){
            total+= item.getTotal();
        }
        total*=(1-discount);
        lbl_amountAfterDiscount.setText(String.format("Amount after Discounts  Rs.%.2f",total));
    }

    private void loadTable() {
        countAmount();
        col_batchId.setCellValueFactory(new PropertyValueFactory<>("batchid"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("purchsedQTY"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_pricePerItem.setCellValueFactory(new PropertyValueFactory<>("pricePerItem"));
        tblView_bill.setItems(cartDTOs);
    }
    private void runClock() {
        lbl_time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh : mm  a")));
        Timeline clockJob = new Timeline(new KeyFrame(Duration.minutes(1), e-> lbl_time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh : mm  a")))));
        clockJob.setCycleCount(Animation.INDEFINITE);
        clockJob.play();
    }
}

