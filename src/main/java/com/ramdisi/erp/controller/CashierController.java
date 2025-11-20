package com.ramdisi.erp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.ramdisi.erp.model.dto.CartDTO;
import com.ramdisi.erp.model.dto.CashierStockDTO;
import com.ramdisi.erp.model.dto.UserDTO;
import com.ramdisi.erp.service.CashierService;
import com.ramdisi.erp.service.impl.CashierServiceImpl;
import com.ramdisi.erp.validation.Validator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CashierController implements Initializable {

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
    private TableColumn<?, ?> col_availableQTY;

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
    private TableView<CashierStockDTO> table_Items;

    @FXML
    private JFXTextField txt_qty;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private Label lbl_selectedItem;

    private static Stage currentStage;

    private CashierService cashierService = new CashierServiceImpl();

    private ObservableList<CashierStockDTO> stockDTOS = FXCollections.observableArrayList();

    private ObservableList<CashierStockDTO> matchingStockDTOS = FXCollections.observableArrayList();

    private PopupMassageController popup=new PopupMassageController();

    private CashierStockDTO selectedItem;

    private static ObservableList<CartDTO> cartList = FXCollections.observableArrayList();

    private static UserDTO cashierAuth;

    private Stage checkoutStage = new Stage();

    @FXML
    private void btn_onAction_addToCart(ActionEvent event) {
        if (txt_qty.getText()!=null && selectedItem!=null) {
            switch (Validator.checkEligabilityToOrder(selectedItem, Integer.parseInt(txt_qty.getText()))) {
                case 0:
                    int indexOfExistingItem=-1;
                    int index=0;
                    for (CartDTO cartItem : cartList){
                        if (cartItem.getId().equals(selectedItem.getId())){
                            indexOfExistingItem = index;
                            break;
                        }
                        index++;
                    }
                    if (indexOfExistingItem == -1) {
                        popup.setWindow(selectedItem.getName()+" : "+txt_qty.getText()+"\nadded to cart succesfully \uD83D\uDE0A");
                        cartList.add(new CartDTO(
                                selectedItem.getId(),
                                selectedItem.getBatchid(),
                                selectedItem.getName(),
                                selectedItem.getPricePerItem(),
                                Integer.parseInt(txt_qty.getText()),
                                selectedItem.getPricePerItem()*Integer.parseInt(txt_qty.getText())
                        ));
                    }else {
                        popup.setWindow("Already added "+selectedItem.getName()+" updated Quntity to "+txt_qty.getText()+" \uD83D\uDE0A");
                        cartList.get(indexOfExistingItem).setPurchsedQTY(Integer.parseInt(txt_qty.getText()));
                    }
                    txt_search.setText(null);
                    txt_qty.setText(null);
                    loadTable();
                    break;
                case 1:
                    popup.setWindow("Ordered Quantity exceeds the Stock Capacity ! Try again with lower quantity");
                    txt_qty.setText(null);
                    break;
                case 2:
                    popup.setWindow("Item is not Available \uD83D\uDE1F");
                    txt_search.setText(null);
                    txt_qty.setText(null);
                    loadTable();
            }
        }else {
            popup.setWindow("Please Select Item and enter Quantity before add to Cart");
        }
    }

    @FXML
    private void btn_onAction_cancelOrder(ActionEvent event) {
        cartList.clear();
        txt_search.setText(null);
        txt_qty.setText(null);
        loadTable();
    }

    @FXML
    private void btn_onAction_checkout(ActionEvent event) {
        try {
            if (cartList.size()>0) {
                CheckoutController.saveDetails(checkoutStage, cartList, cashierAuth);
                checkoutStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/checkout.fxml"))));
                checkoutStage.show();
                currentStage.close();
            }
        } catch (IOException e) {
            popup.setWindow("Oops something went wrong \n Error id : 004");
        }
    }

    @FXML
    private void btn_onAction_clearSearch(ActionEvent event) {
        txt_search.setText(null);
    }

    @FXML
    private void img_onClick_logout(MouseEvent event) {
        currentStage.close();
        LoginController.showCurrentStage();
    }

    @FXML
    void table_onClick_selectItem(MouseEvent event) {
        selectedItem = table_Items.getSelectionModel().getSelectedItem();
        lbl_selectedItem.setText("Selected Item : "+selectedItem.getName());
    }

    @FXML
    void txt_onKeyPressed_search(KeyEvent event) {
        String searchTerm = txt_search.getText();
        if (searchTerm==null){
            loadTable();
        }else {
            searchTerm = searchTerm.toLowerCase();
            matchingStockDTOS.clear();
            for (CashierStockDTO stock : stockDTOS){
                boolean matchingName = stock.getName().toLowerCase().contains(searchTerm);
                boolean matchingBatchId = stock.getBatchid().toLowerCase().contains(searchTerm);
                boolean matchingCategory = stock.getBatchid().toLowerCase().contains(searchTerm);
                if (matchingName || matchingCategory || matchingBatchId){
                    matchingStockDTOS.add(stock);
                }
            }
            col_availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
            col_availableQTY.setCellValueFactory(new PropertyValueFactory<>("availableQTY"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_batchId.setCellValueFactory(new PropertyValueFactory<>("batchid"));
            col_shelfNo.setCellValueFactory(new PropertyValueFactory<>("shelf_no"));
            col_pricePerItem.setCellValueFactory(new PropertyValueFactory<>("pricePerItem"));
            table_Items.setItems(matchingStockDTOS);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runClock();
        loadTable();
        lbl_cashierName.setText("Cashier : "+cashierAuth.getName().toUpperCase());
    }

    private void loadTable() {
        lbl_selectedItem.setText("Selected Item : None");
        selectedItem = null;
        stockDTOS.clear();
        try {
            stockDTOS = cashierService.getAllStockDetails();
            col_availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
            col_availableQTY.setCellValueFactory(new PropertyValueFactory<>("availableQTY"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_batchId.setCellValueFactory(new PropertyValueFactory<>("batchid"));
            col_shelfNo.setCellValueFactory(new PropertyValueFactory<>("shelf_no"));
            col_pricePerItem.setCellValueFactory(new PropertyValueFactory<>("pricePerItem"));
            table_Items.setItems(stockDTOS);
        } catch (SQLException e) {
            popup.setWindow("Oops Cant load Table \n Error id : 003");
        }
    }

    private void runClock() {
        lbl_time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh : mm  a")));
        Timeline clockJob = new Timeline(new KeyFrame(Duration.minutes(1), e-> lbl_time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh : mm  a")))));
        clockJob.setCycleCount(Animation.INDEFINITE);
        clockJob.play();
    }
    public static void saveCurrentStage(Stage stage){
        currentStage=stage;
    }
    public static void setCashierDetails(UserDTO user){
        cashierAuth = user;
    }
    public static void showCurrentStage(){
        currentStage.show();
    }
    public static void cancelOrder(){
        cartList.clear();
    }
}
