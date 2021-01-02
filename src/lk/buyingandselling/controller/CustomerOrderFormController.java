/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javax.swing.Timer;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.supplier.BatchDetailBO;
import lk.buyingandselling.BO.supplier.ItemBO;
import lk.buyingandselling.BO.supplier.LocationBO;
import lk.buyingandselling.model.DTO.BatchDetailDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;
import lk.buyingandselling.model.DTO.ItemDTO;
import lk.buyingandselling.model.DTO.LocationDTO;
import lk.buyingandselling.model.TM.CustomerOrderTM;
import lk.buyingandselling.util.IDGenerator;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class CustomerOrderFormController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private Label lblNoOfItems;
    @FXML
    private TextField txtOrderID;
    @FXML
    private JFXDatePicker dtOrderDate;
    @FXML
    private JFXTimePicker tpOrderTime;
    @FXML
    private JFXComboBox<String> cmbItemDescription;
    @FXML
    private JFXButton btnAddToCart;
    @FXML
    private TableView<CustomerOrderTM> tblOrderDetail;
    @FXML
    private TableColumn<CustomerOrderTM, String> itemCode;
    @FXML
    private TableColumn<CustomerOrderTM, String> batchNo;
    @FXML
    private TableColumn<CustomerOrderTM, String> description;
    @FXML
    private TableColumn<CustomerOrderTM, String> EXP;
    @FXML
    private TableColumn<CustomerOrderTM, String> MFD;
    @FXML
    private TableColumn<CustomerOrderTM, String> locationID;
    @FXML
    private TableColumn<CustomerOrderTM, Double> unitPrice;
    @FXML
    private TableColumn<CustomerOrderTM, Double> QTY;
    @FXML
    private TableColumn<CustomerOrderTM, Double> total;
    @FXML
    private TableColumn<CustomerOrderTM, String> rackNo;
    @FXML
    private TableColumn<CustomerOrderTM, String> sectionName;
    @FXML
    private JFXButton btnRemoveItem;
    @FXML
    private JFXButton btnEditItem;
    @FXML
    private Label lblNetTotal;
    @FXML
    private JFXButton btnMakePayment;
    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXComboBox<String> cmbBatchNo;
    @FXML
    private JFXDatePicker dtEXP;
    @FXML
    private JFXDatePicker dtMFD;
    @FXML
    private JFXTextField txtQTYOnHand;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private TextField txtQTY;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXTextField txtLocationID;
    @FXML
    private JFXTextField txtSectionName;
    @FXML
    private JFXTextField txtRackNo;
    @FXML
    private JFXTextField txtCategory;

    ArrayList<BatchDetailDTO> selectedItemBatchDetail = new ArrayList<>();

    ObservableList<CustomerOrderTM> orderDetail = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnCancel;

    private int editRow = -1;
    @FXML
    private BorderPane display;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getNextOrderID();
        loadAllItems();
        dateTimeListner();
        TextFields.bindAutoCompletion(cmbItemDescription.getEditor(), cmbItemDescription.getItems());
        setDateTime();
        cmbItemDescription.setOnAction(this);
        cmbBatchNo.getSelectionModel().selectedIndexProperty().addListener((ob, oldValue, newValue) -> {
            batchDetails(newValue);
        });
        txtQTY.textProperty().addListener((ob, oldValue, newValue) -> {
            txtQTYListiner(newValue);
        });
        orderDetail.addListener((Change<? extends CustomerOrderTM> c) -> {
            lblNoOfItems.setText(String.valueOf(orderDetail.size()));
            lblNetTotal.setText("0.00");
            double netTotal = 0;
            for (CustomerOrderTM temp : orderDetail) {
                netTotal += temp.getTotal();
            }
            lblNetTotal.setText(String.valueOf(netTotal));
        });
        makeTable();
        btnAddToCart.setOnAction(this);
        btnEditItem.setOnAction(this);
        btnCancel.setOnAction(this);
        btnRemoveItem.setOnAction(this);
        btnMakePayment.setOnAction(this);
        setTimer();
    }

    private void getNextOrderID() {
        try {
            txtOrderID.setText(new IDGenerator(IDGenerator.TableName.CUSTOMERORDER).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(CustomerOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDateTime() {
        dtOrderDate.setValue(LocalDate.now());
        tpOrderTime.setValue(LocalTime.now());
    }

    private void dateTimeListner() {
        dtOrderDate.getEditor().textProperty().addListener((ob, oldValue, newValue) -> {
            dtOrderDate.setValue(LocalDate.now());
        });
        tpOrderTime.getEditor().textProperty().addListener((ob, oldValue, newValue) -> {
            if (!newValue.equals(LocalTime.now())) {
                tpOrderTime.setValue(LocalTime.now());
            }
        });
    }

    private void loadAllItems() {
        try {
            ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            for (ItemDTO temp : allItems) {
                cmbItemDescription.getItems().add(temp.getDescription());
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setItemDetails() {
        try {
            ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setDescription(cmbItemDescription.getEditor().getText());
            ArrayList<ItemDTO> itemDTOs = itemBO.searchItem(itemDTO);
            for (ItemDTO temp : itemDTOs) {
                txtItemCode.setText(temp.getItemCode());
                txtLocationID.setText(temp.getLocationId());
                txtCategory.setText(temp.getCategory());
            }
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocationId(txtLocationID.getText());
            ArrayList<LocationDTO> itemLocationDTOs = locationBO.searchLocation(locationDTO);
            for (LocationDTO temp : itemLocationDTOs) {
                txtSectionName.setText(temp.getSectionName());
                txtRackNo.setText(temp.getRackNo());
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getQTYOnHandAndSellingPrice() {
        try {
            BatchDetailBO batchDetailBO = (BatchDetailBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.BATCHDETAIL);
            BatchDetailDTO batchDetailDTO = new BatchDetailDTO();
            batchDetailDTO.setItemCode(txtItemCode.getText());
            ArrayList<BatchDetailDTO> batchDetailDTOs = batchDetailBO.availableBatchDetails(batchDetailDTO);
            cmbBatchNo.getItems().clear();
            selectedItemBatchDetail.clear();
            for (BatchDetailDTO temp : batchDetailDTOs) {
                cmbBatchNo.getItems().add(temp.getBatchNo());
                selectedItemBatchDetail.add(temp);
            }
            if (batchDetailDTOs.isEmpty()) {
                cmbBatchNo.getItems().add("Batch Not Found");
                BatchDetailDTO temp = new BatchDetailDTO();
                temp.setQtyOnHand(0.0);
                temp.setSellingPrice(0.0);
                temp.setEXD(null);
                temp.setMFD(null);
                selectedItemBatchDetail.add(temp);
            }
            existOnTableQTYFix();
            cmbBatchNo.getSelectionModel().selectFirst();
        } catch (Exception ex) {
            Logger.getLogger(CustomerOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (!cmbItemDescription.getEditor().getText().isEmpty() && event.getSource() == cmbItemDescription) {
            setItemDetails();
            getQTYOnHandAndSellingPrice();
        }
        if (btnAddToCart.getText().equalsIgnoreCase("Add to Cart") && event.getSource() == btnAddToCart) {
            if (checkValues()) {
                CustomerOrderTM customerOrderTM = makeCustomerOrderTM();
                if (itemExistInTable(customerOrderTM)) {
                } else {
                    orderDetail.add(customerOrderTM);
                    tblOrderDetail.setItems(orderDetail);
                }
                tblOrderDetail.refresh();
                existOnTableQTYFix();
                clear();
            }
        }
        if (event.getSource() == btnEditItem) {
            btnCancel.setVisible(true);
            btnAddToCart.setText("Update");
            editRow = tblOrderDetail.getSelectionModel().getSelectedIndex();
            CustomerOrderTM customerOrderTM = tblOrderDetail.getSelectionModel().getSelectedItem();
            cmbItemDescription.getSelectionModel().select(customerOrderTM.getDescription());
            for (BatchDetailDTO temp : selectedItemBatchDetail) {
                if (temp.getBatchNo().equalsIgnoreCase(customerOrderTM.getBatchNo())) {
                    temp.setQtyOnHand(temp.getQtyOnHand() + customerOrderTM.getQTY());
                    txtQTYOnHand.setText(String.valueOf(temp.getQtyOnHand()));
                }
            }
            cmbBatchNo.getSelectionModel().select(customerOrderTM.getBatchNo());
            txtQTY.setText(String.valueOf(customerOrderTM.getQTY()));
        }
        if (btnAddToCart.getText().equalsIgnoreCase("Update") && event.getSource() == btnAddToCart) {
            if (checkValues()) {
                CustomerOrderTM customerOrderTM = makeCustomerOrderTM();
                if (customerOrderTM.getBatchNo().equalsIgnoreCase(orderDetail.get(editRow).getBatchNo())) {
                    orderDetail.remove(editRow);
                    orderDetail.add(editRow, customerOrderTM);
                } else {
                    boolean existFound = false;
                    for (CustomerOrderTM temp : orderDetail) {
                        if (customerOrderTM.getBatchNo().equalsIgnoreCase(temp.getBatchNo())) {
                            temp.setQTY(customerOrderTM.getQTY() + temp.getQTY());
                            temp.setTotal(temp.getTotal() + customerOrderTM.getTotal());
                            existFound = true;
                        }
                    }
                    if (existFound == false) {
                        orderDetail.remove(editRow);
                        orderDetail.add(editRow, customerOrderTM);
                    } else {
                        orderDetail.remove(editRow);
                    }
                }
                tblOrderDetail.refresh();
                clear();
                btnCancel.setVisible(false);
                btnAddToCart.setText("Add To Cart");
            }
        }
        if (event.getSource() == btnCancel) {
            clear();
            btnAddToCart.setText("Add To Cart");
            btnCancel.setVisible(false);
        }
        if (event.getSource() == btnRemoveItem) {
            if (tblOrderDetail.getSelectionModel().getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Item");
                alert.setHeaderText("Are you sure ?");
                ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(btnYes, btnCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == btnYes) {
                    CustomerOrderTM customerOrderTM = tblOrderDetail.getSelectionModel().getSelectedItem();
                    orderDetail.remove(customerOrderTM);
                    tblOrderDetail.refresh();
                }
            }
        }
        if (event.getSource() == btnMakePayment) {
            if (tblOrderDetail.getItems().size() > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Do you want to add this Stock");
                alert.setHeaderText("You want be able to Order Items\n Make sure everything is OK!");
                ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(btnYes, btnCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == btnYes) {
                    CustomerDetailsController.setCustomerOrderTM(makeCustomerOrderDTO());
                    CustomerPaymentFormController.setOrderDetails(orderDetail);
                    try {
                        BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerDetails.fxml"));
                        display.setBackground(Background.EMPTY);
                        display.getChildren().setAll(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Make Payment");
                alert.setHeaderText("Please add an Item !");
                alert.showAndWait();
            }
        }

    }

    private void batchDetails(Number newValue) {
        BatchDetailDTO batchDetailDTO = selectedItemBatchDetail.get(0);
        txtQTYOnHand.setText(String.valueOf(batchDetailDTO.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(batchDetailDTO.getSellingPrice()));
        if (batchDetailDTO.getEXD() != null) {
            dtEXP.setValue(LocalDate.parse(batchDetailDTO.getEXD()));
            if (LocalDate.now().isAfter(dtEXP.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
        if (batchDetailDTO.getMFD() != null) {
            dtMFD.setValue(LocalDate.parse(batchDetailDTO.getMFD()));
        }

    }

    private void clear() {
        cmbBatchNo.getSelectionModel().select(null);
        txtQTY.clear();
        txtCategory.clear();
        txtItemCode.clear();
        txtLocationID.clear();
        txtQTYOnHand.clear();
        txtRackNo.clear();
        dtEXP.setValue(null);
        dtMFD.setValue(null);
        txtUnitPrice.clear();
        txtTotal.clear();
        txtSectionName.clear();
        cmbItemDescription.getSelectionModel().select(null);
    }

    private void makeTable() {
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        batchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationID.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        EXP.setCellValueFactory(new PropertyValueFactory<>("EXP"));
        MFD.setCellValueFactory(new PropertyValueFactory<>("MFD"));
        rackNo.setCellValueFactory(new PropertyValueFactory<>("rackNo"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        QTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        sectionName.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
    }

    private void txtQTYListiner(String newValue) {
        double qtyOnHand = 0;
        double qty = 0;
        if (!txtQTY.getText().isEmpty() && txtQTYOnHand.getText() != null && !txtQTYOnHand.getText().isEmpty()) {
            qtyOnHand = Double.parseDouble(txtQTYOnHand.getText());
            qty = Double.parseDouble(newValue);
        }

        if (qty > 0 && qty <= qtyOnHand) {
            txtTotal.setText(String.valueOf(qty * Double.parseDouble(txtUnitPrice.getText())));
        } else if (qty > qtyOnHand) {
            txtQTY.setText(String.valueOf(qtyOnHand));
        }
    }

    private void existOnTableQTYFix() {
        for (CustomerOrderTM temp : orderDetail) {
            for (BatchDetailDTO batchDetail : selectedItemBatchDetail) {
                if (temp.getBatchNo().equalsIgnoreCase(batchDetail.getBatchNo())) {
                    batchDetail.setQtyOnHand(batchDetail.getQtyOnHand() - temp.getQTY());
                }
            }
        }
    }

    private boolean checkValues() {
        if (!cmbBatchNo.getEditor().getText().equalsIgnoreCase("Batch Not Found")
                && txtTotal.getText().compareToIgnoreCase("0.0") != 0
                && !txtQTY.getText().isEmpty()
                && !txtQTYOnHand.getText().equalsIgnoreCase("0.0")
                && !txtUnitPrice.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private CustomerOrderTM makeCustomerOrderTM() {
        CustomerOrderTM customerOrderTM = new CustomerOrderTM();
        customerOrderTM.setItemCode(txtItemCode.getText());
        customerOrderTM.setBatchNo(cmbBatchNo.getSelectionModel().getSelectedItem());
        customerOrderTM.setDescription(cmbItemDescription.getSelectionModel().getSelectedItem());
        if (dtEXP.getValue() == null) {
            customerOrderTM.setEXP("");
        } else {
            customerOrderTM.setEXP(String.valueOf(dtEXP.getValue()));
        }
        if (dtMFD.getValue() == null) {
            customerOrderTM.setMFD("");
        } else {
            customerOrderTM.setMFD(String.valueOf(dtMFD.getValue()));
        }
        customerOrderTM.setSectionName(txtSectionName.getText());
        customerOrderTM.setLocationId(txtLocationID.getText());
        customerOrderTM.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
        customerOrderTM.setQTY(Double.parseDouble(txtQTY.getText()));
        customerOrderTM.setTotal(Double.parseDouble(txtTotal.getText()));
        customerOrderTM.setRackNo(txtRackNo.getText());
        return customerOrderTM;
    }

    private boolean itemExistInTable(CustomerOrderTM customerOrderTM) {
        for (CustomerOrderTM temp : orderDetail) {
            if (temp.getBatchNo().equalsIgnoreCase(customerOrderTM.getBatchNo())) {
                temp.setQTY(temp.getQTY() + customerOrderTM.getQTY());
                temp.setTotal(temp.getTotal() + customerOrderTM.getTotal());
                lblNetTotal.setText("0.00");
                double netTotal = 0;
                for (CustomerOrderTM t : orderDetail) {
                    netTotal += t.getTotal();
                }
                lblNetTotal.setText(String.valueOf(netTotal));
                return true;
            }
        }
        return false;
    }

    private CustomerOrderDTO makeCustomerOrderDTO() {
        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();
        customerOrderDTO.setOrderId(txtOrderID.getText());
        customerOrderDTO.setOrderedDate(String.valueOf(LocalDate.now()));
        customerOrderDTO.setOrderedTime(String.valueOf(LocalTime.now()));
        return customerOrderDTO;
    }

    private void setTimer() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Date date = new Date();
                String s = new SimpleDateFormat("hh:mm:ss").format(date);
                tpOrderTime.setValue(LocalTime.parse(s));
            }
        };
        Timer time = new Timer(1000, actionListener);
        time.setInitialDelay(0);
        time.start();
    }
}
