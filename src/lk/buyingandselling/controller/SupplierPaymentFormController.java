/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.supplier.SupplierPaymentFillingBO;
import lk.buyingandselling.model.DTO.IssuedChequeDTO;
import lk.buyingandselling.model.DTO.SupplierPaymentDTO;
import lk.buyingandselling.model.TM.PurchaseStockTM;
import lk.buyingandselling.util.IDGenerator;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class SupplierPaymentFormController implements Initializable, EventHandler<ActionEvent> {

    private static ObservableList<PurchaseStockTM> purchaseStockDetails = FXCollections.observableArrayList();
    @FXML
    private TextField txtPaymentID;
    @FXML
    private TextField txtPurchaseID;
    @FXML
    private TextField txtSupplierID;
    @FXML
    private JFXDatePicker dtPurchaseDate;
    @FXML
    private TableView<PurchaseStockTM> tblStockDetails;
    @FXML
    private TableColumn<PurchaseStockTM, String> ItemCode;
    @FXML
    private TableColumn<PurchaseStockTM, String> BatchNo;
    @FXML
    private TableColumn<PurchaseStockTM, String> Description;
    @FXML
    private TableColumn<PurchaseStockTM, String> EXP;
    @FXML
    private TableColumn<PurchaseStockTM, String> MFD;
    @FXML
    private TableColumn<PurchaseStockTM, String> LocationID;
    @FXML
    private TableColumn<PurchaseStockTM, String> RackNo;
    @FXML
    private TableColumn<PurchaseStockTM, String> UnitPrice;
    @FXML
    private TableColumn<PurchaseStockTM, String> SellingPrice;
    @FXML
    private TableColumn<PurchaseStockTM, String> QTY;
    @FXML
    private TableColumn<PurchaseStockTM, String> Total;
    @FXML
    private JFXToggleButton rbnCash;
    @FXML
    private JFXToggleButton rbnCheque;
    @FXML
    private Label lblTotalPrice;
    @FXML
    private Tab tabCash;
    @FXML
    private Tab tabCheque;
    @FXML
    private JFXButton btnPayOldPurchase;
    @FXML
    private JFXTextField txtChequeNo;
    @FXML
    private JFXTextField txtBankName;
    @FXML
    private JFXTextField txtStatus;
    @FXML
    private JFXButton btnSubmitPayment;
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField txtAmountCash;
    @FXML
    private TextField txtAmountCheque;

    private static String supplierID = "";

    private static String purchaseID = "";

    private ToggleGroup tgPaymentMethod = new ToggleGroup();
    @FXML
    private BorderPane display;
    @FXML
    private Label lblDebt;
    @FXML
    private JFXButton btnSkipPayment;
    @FXML
    private JFXDatePicker dtPaidDate;
    @FXML
    private JFXDatePicker dtIssuedDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPayOldPurchase.setOnAction(this);
        nextSupplierPaymentID();
        dtIssuedDate.setValue(LocalDate.now());
        dtPaidDate.setValue(LocalDate.now());
        dtPurchaseDate.setValue(LocalDate.now());
        setToggelButton();
        txtSupplierID.setText(supplierID);
        txtPurchaseID.setText(purchaseID);
        rbnCash.setSelected(true);
        setPurchaseStockDetail();
        btnSkipPayment.setOnAction(this);
        listner();
        btnSubmitPayment.setOnAction(this);
    }

    private void setToggelButton() {

        rbnCash.setToggleGroup(tgPaymentMethod);
        rbnCheque.setToggleGroup(tgPaymentMethod);
        rbnStateChange();
    }

    private void nextSupplierPaymentID() {
        try {
            txtPaymentID.setText(new IDGenerator(IDGenerator.TableName.SUPPLIERPAYMENT).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(SupplierPaymentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setPurchaseStockDetails(ObservableList<PurchaseStockTM> purchasedStock,
            String purchase, String supplier) {
        purchaseStockDetails = purchasedStock;
        purchaseID = purchase;
        supplierID = supplier;
    }

    private void listner() {
        txtAmountCash.textProperty().addListener((ob, old, newValue) -> {
            if (newValue.isEmpty()) {
                lblDebt.setText(lblTotalPrice.getText());
            } else if (!newValue.isEmpty() && Double.parseDouble(newValue) < Double.parseDouble(lblTotalPrice.getText())) {
                lblDebt.setText(String.valueOf(Double.parseDouble(lblTotalPrice.getText()) - Double.parseDouble(newValue)));
            } else if (!newValue.isEmpty() && Double.parseDouble(newValue) > Double.parseDouble(lblTotalPrice.getText())) {
                lblDebt.setText("0.00");
            }

        });
    }

    private void setPurchaseStockDetail() {
        ItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        BatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        Description.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
        LocationID.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        EXP.setCellValueFactory(new PropertyValueFactory<>("EXP"));
        MFD.setCellValueFactory(new PropertyValueFactory<>("MFD"));
        RackNo.setCellValueFactory(new PropertyValueFactory<>("rackNo"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        SellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        QTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        tblStockDetails.setItems(purchaseStockDetails);
        setTotalPrice();
    }

    private void setTotalPrice() {
        double total = 0;
        for (PurchaseStockTM temp : purchaseStockDetails) {
            total += temp.getTotal();
        }
        lblTotalPrice.setText(String.valueOf(total));
        txtAmountCash.setText(String.valueOf(total));
        txtAmountCheque.setText(String.valueOf(total));

    }

    private void rbnStateChange() {
        tgPaymentMethod.selectedToggleProperty().addListener((ob, old, newValue) -> {
            if (newValue != null && newValue.equals(rbnCash)) {
                tabPane.getSelectionModel().select(tabCash);
                tabCash.setDisable(false);
                tabCheque.setDisable(true);
            } else if (newValue != null && newValue.equals(rbnCheque)) {
                tabPane.getSelectionModel().select(tabCheque);
                tabCheque.setDisable(false);
                tabCash.setDisable(true);

            }
            if (newValue == null) {
                rbnCash.setSelected(true);
                tabPane.getSelectionModel().select(tabCash);
                tabCash.setDisable(false);
                tabCheque.setDisable(true);
            }
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnSkipPayment) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Purchase Stock Payment");
            alert.setHeaderText("Do you want to skip payment");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnYes, btnCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btnYes) {
                try {
                    BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/PurchaseStock.fxml"));
                    display.setBackground(Background.EMPTY);
                    display.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(SupplierPaymentFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (event.getSource() == btnSubmitPayment) {
            if (checkValues()) {
                SupplierPaymentDTO supplierPaymentDTO = makeSupplierPaymentDTO();
                IssuedChequeDTO issuedChequeDTO = null;
                if (rbnCheque.isSelected()) {
                    issuedChequeDTO = makeIssuedCheque();
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Submit Payment");
                alert.setHeaderText("You want be able to change Payments\\n Make sure everything is OK!");
                ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(btnYes, btnCancel);
                Optional<ButtonType> result = alert.showAndWait();
                boolean supplierPaymentIsAdded = true;
                if (result.get() == btnYes) {
                    SupplierPaymentFillingBO supplierPaymentFillingBO
                            = (SupplierPaymentFillingBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIERPAYMENTFILLING);
                    supplierPaymentIsAdded = supplierPaymentFillingBO.saveSupplierPayment(supplierPaymentDTO, issuedChequeDTO);

                }
                if (supplierPaymentIsAdded) {
                    Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
                    saveAlert.setTitle("Submit Payment");
                    saveAlert.setHeaderText("Payment Successful!");
                    saveAlert.showAndWait();
                    clear();

                }
            }
        }
        if (event.getSource() == btnPayOldPurchase) {

        }
    }

    private boolean checkValues() {
        if (!txtPurchaseID.getText().isEmpty() && !txtPaymentID.getText().isEmpty() && !txtSupplierID.getText().isEmpty()) {
            if (rbnCheque.isSelected()) {
                if (!txtAmountCheque.getText().isEmpty()
                        && !txtBankName.getText().isEmpty()
                        && dtIssuedDate.getValue() != null
                        && !txtChequeNo.getText().isEmpty()) {
                    return true;
                }
            }
            if (rbnCash.isSelected()) {
                if (!txtAmountCash.getText().isEmpty() && dtPaidDate.getValue() != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private SupplierPaymentDTO makeSupplierPaymentDTO() {
        SupplierPaymentDTO supplierPaymentDTO = new SupplierPaymentDTO();
        if (rbnCash.isSelected()) {
            supplierPaymentDTO.setPaidAmount(Double.parseDouble(txtAmountCash.getText()));
            supplierPaymentDTO.setPaymentMethod(rbnCash.getText());
            supplierPaymentDTO.setPaidDate(String.valueOf(dtPaidDate.getValue()));
            supplierPaymentDTO.setIssuedChequeId(null);
        }
        if (rbnCheque.isSelected()) {
            supplierPaymentDTO.setPaidAmount(Double.parseDouble(txtAmountCheque.getText()));
            supplierPaymentDTO.setIssuedChequeId(txtChequeNo.getText());
            supplierPaymentDTO.setPaidDate(String.valueOf(dtIssuedDate.getValue()));
            supplierPaymentDTO.setPaymentMethod(rbnCheque.getText());
        }
        supplierPaymentDTO.setPurchaseId(txtPurchaseID.getText());
        supplierPaymentDTO.setPaymentId(txtPaymentID.getText());
        supplierPaymentDTO.setTotalAmount(Double.parseDouble(lblTotalPrice.getText()));
        return supplierPaymentDTO;
    }

    private IssuedChequeDTO makeIssuedCheque() {
        IssuedChequeDTO issuedCheque = new IssuedChequeDTO();
        issuedCheque.setIssuedChequeId(txtChequeNo.getText());
        issuedCheque.setBankName(txtBankName.getText());
        issuedCheque.setAmount(Double.parseDouble(txtAmountCheque.getText()));
        issuedCheque.setStatus(txtStatus.getText());
        issuedCheque.setIssuedDate(String.valueOf(dtIssuedDate.getValue()));
        return issuedCheque;
    }

    private void clear() {
        txtAmountCash.clear();
        txtAmountCheque.clear();
        txtBankName.clear();
        txtChequeNo.clear();
        txtPaymentID.clear();
        txtPurchaseID.clear();
        txtStatus.clear();
        txtSupplierID.clear();
        dtIssuedDate.setValue(LocalDate.now());
        dtPaidDate.setValue(LocalDate.now());
        dtPurchaseDate.setValue(LocalDate.now());
        tblStockDetails.getItems().clear();
        lblDebt.setText("0.00");
        lblTotalPrice.setText("0.00");
    }
    
}
