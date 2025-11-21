package com.ramdisi.erp.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class StockController {

    @FXML
    private JFXComboBox<?> cmb_category;

    @FXML
    private JFXComboBox<?> cmb_supplier;

    @FXML
    private TableColumn<?, ?> col_availability;

    @FXML
    private TableColumn<?, ?> col_batchId;

    @FXML
    private TableColumn<?, ?> col_buyingPrice;

    @FXML
    private TableColumn<?, ?> col_category;

    @FXML
    private TableColumn<?, ?> col_exp;

    @FXML
    private TableColumn<?, ?> col_mfd;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_sellingPrice;

    @FXML
    private TableColumn<?, ?> col_shelfNo;

    @FXML
    private TableColumn<?, ?> col_supplier;

    @FXML
    private DatePicker date_exp;

    @FXML
    private DatePicker date_mfd;

    @FXML
    private Label lbl_availability;

    @FXML
    private Label lbl_time;

    @FXML
    private TableView<?> tbl_stock;

    @FXML
    private JFXTextField txt_buyingRate;

    @FXML
    private JFXTextField txt_handOnQTY;

    @FXML
    private JFXTextField txt_purchasedQTY;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private JFXTextField txt_sellingRate;

    @FXML
    private JFXTextField txt_shelfNo;

    @FXML
    void btn_onAction_addStock(ActionEvent event) {

    }

    @FXML
    void btn_onAction_clearSearch(ActionEvent event) {

    }

    @FXML
    void btn_onAction_recallStock(ActionEvent event) {

    }

    @FXML
    void btn_onAction_removeStock(ActionEvent event) {

    }

    @FXML
    void btn_onAction_updateStock(ActionEvent event) {

    }

    @FXML
    void img_onClick_back(MouseEvent event) {

    }

    @FXML
    void tbl_onClick_selectStock(MouseEvent event) {

    }

    @FXML
    void txt_name(ActionEvent event) {

    }

    @FXML
    void txt_onKeyPressed_search(KeyEvent event) {

    }

}
