package com.ramdisi.erp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

public class CashierController {

    @FXML
    private JFXButton btn_addToCart;

    @FXML
    private JFXButton btn_cancelOrder;

    @FXML
    private JFXButton btn_checkout;

    @FXML
    private TableColumn<?, ?> col_availability;

    @FXML
    private TableColumn<?, ?> col_batchId;

    @FXML
    private TableColumn<?, ?> col_exp;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_pricePerItem;

    @FXML
    private TableColumn<?, ?> col_shelfNo;

    @FXML
    private Label lbl_cashierName;

    @FXML
    private Label lbl_time;

    @FXML
    private TableView<?> table_Items;

    @FXML
    private JFXTextField txt_qty;

    @FXML
    private JFXTextField txt_search;

    @FXML
    void btn_onAction_addToCart(ActionEvent event) {

    }

    @FXML
    void btn_onAction_cancelOrder(ActionEvent event) {

    }

    @FXML
    void btn_onAction_checkout(ActionEvent event) {

    }

    @FXML
    void btn_onAction_clearSearch(ActionEvent event) {

    }

    @FXML
    void img_onClick_logout(MouseEvent event) {

    }

    @FXML
    void table_onClick_selectItem(MouseEvent event) {

    }

    @FXML
    void txt_onTextChange_search(InputMethodEvent event) {

    }

}
