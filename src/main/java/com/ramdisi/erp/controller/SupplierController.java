package com.ramdisi.erp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.ramdisi.erp.model.dto.SupplierDTO;
import com.ramdisi.erp.model.entity.Supplier;
import com.ramdisi.erp.service.SupplierService;
import com.ramdisi.erp.service.impl.SupplierServiceImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    @FXML
    private JFXButton btn_addSupplier;

    @FXML
    private JFXButton btn_removeSupplier;

    @FXML
    private JFXButton btn_updateInfo;

    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_email;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private Label lbl_time;

    @FXML
    private TableView<SupplierDTO> tbl_view_suppliers;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_supplierName;

    private static Stage currentStage;

    private SupplierService supplierService = new SupplierServiceImpl();

    private SupplierDTO selectedSupplier;

    private ObservableList<SupplierDTO> supplierList = FXCollections.observableArrayList();

    @FXML
    void btn_onAction_clearSelection(ActionEvent event) {
        selectedSupplier=null;
        txt_address.setText(null);
        txt_email.setText(null);
        txt_supplierName.setText(null);
    }

    @FXML
    void btn_onAction_addSupplier(ActionEvent event) {
        if (selectedSupplier==null){
            try {
                supplierService.addSupplier(new Supplier(
                        null,
                        txt_supplierName.getText(),
                        txt_address.getText(),
                        txt_email.getText()
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                txt_supplierName.setText(null);
                txt_address.setText(null);
                txt_email.setText(null);
                loadTable();
            }
        }
    }

    @FXML
    void btn_onAction_removeSupplier(ActionEvent event) {
        if (selectedSupplier!=null){
            try {
                supplierService.deleteSupplier(new Supplier(
                        selectedSupplier.getId(),
                        selectedSupplier.getName(),
                        selectedSupplier.getAddress(),
                        selectedSupplier.getEmail()
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                txt_supplierName.setText(null);
                txt_address.setText(null);
                txt_email.setText(null);
                loadTable();
            }
        }
    }

    @FXML
    void btn_onAction_updateInfo(ActionEvent event) {
        if (selectedSupplier!=null){
            try {
                supplierService.updateSupplier(new Supplier(
                        selectedSupplier.getId(),
                        txt_supplierName.getText(),
                        txt_address.getText(),
                        txt_email.getText()
                ));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                txt_supplierName.setText(null);
                txt_address.setText(null);
                txt_email.setText(null);
                loadTable();
            }
        }
    }

    @FXML
    void img_onClick_backToMainWindow(MouseEvent event) {
        currentStage.close();
        AdminController.showCurrentStage();
    }

    @FXML
    void tbl_onClick_selectSupplier(MouseEvent event) {
        TableView.TableViewSelectionModel<SupplierDTO> selectionModel = tbl_view_suppliers.getSelectionModel();
        if (!selectionModel.isEmpty()){
            selectedSupplier =selectionModel.getSelectedItem();
            txt_email.setText(selectedSupplier.getEmail());
            txt_address.setText(selectedSupplier.getAddress());
            txt_supplierName.setText(selectedSupplier.getName());
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        runClock();
    }

    private void loadTable() {
        selectedSupplier=null;
        supplierList.clear();
        try {
            supplierList = supplierService.getAllSuppliers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_view_suppliers.setItems(supplierList);
    }
}
