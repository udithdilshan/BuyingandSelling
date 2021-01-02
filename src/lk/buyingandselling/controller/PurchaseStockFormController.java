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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.supplier.BatchDetailBO;
import lk.buyingandselling.BO.supplier.ItemBO;
import lk.buyingandselling.BO.supplier.LocationBO;
import lk.buyingandselling.BO.supplier.PurchaseStockFillingBO;
import lk.buyingandselling.model.DTO.BatchDTO;
import lk.buyingandselling.model.DTO.BatchDetailDTO;
import lk.buyingandselling.model.DTO.ItemDTO;
import lk.buyingandselling.model.DTO.LocationDTO;
import lk.buyingandselling.model.DTO.PurchaseStockDTO;
import lk.buyingandselling.model.DTO.PurchaseStockDetailDTO;
import lk.buyingandselling.model.TM.PurchaseStockTM;
import lk.buyingandselling.util.IDGenerator;
import org.controlsfx.control.textfield.TextFields;

public class PurchaseStockFormController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private TextField txtSupplierID;
    @FXML
    private TextField txtPurchaseID;
    @FXML
    private DatePicker dtAddedDate;
    @FXML
    private Label txtTotalPrice;
    @FXML
    private Button btnPayment;
    @FXML
    private Button btnAddNewItem;
    @FXML
    private TextField txtQTY;
    @FXML
    private JFXDatePicker dtMFD;
    @FXML
    private JFXDatePicker dtEXP;
    @FXML
    private Button btnAddToCart;

    private static String supplierID;
    @FXML
    private TableView<PurchaseStockTM> tblStockDetails;
    @FXML
    private TableColumn<PurchaseStockTM, String> itemCode;
    @FXML
    private TableColumn<PurchaseStockTM, String> EXP;
    @FXML
    private TableColumn<PurchaseStockTM, String> MFD;
    @FXML
    private TableColumn<PurchaseStockTM, String> locationID;
    @FXML
    private TableColumn<PurchaseStockTM, String> rackNo;
    @FXML
    private TableColumn<PurchaseStockTM, Double> unitPrice;
    @FXML
    private TableColumn<PurchaseStockTM, String> QTY;
    @FXML
    private TableColumn<PurchaseStockTM, Double> Total;
    @FXML
    private TableColumn<PurchaseStockTM, String> batchNo;
    @FXML
    private TableColumn<PurchaseStockTM, Double> sellingPrice;

    private ArrayList<ItemDTO> itemDTOs = new ArrayList<>();

    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtCategory;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXTextField txtLocationID;
    @FXML
    private JFXTextField txtSectionName;
    @FXML
    private JFXTextField txtRackNo;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXButton btnRemoveItem;
    @FXML
    private JFXTextField txtSellingPrice;
    @FXML
    private JFXComboBox<String> cmbItemDescription;
    @FXML
    private JFXTextField txtBatchNo;

    private ObservableList<PurchaseStockTM> purchaseStockDetails = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnEditItem;
    @FXML
    private TableColumn<PurchaseStockTM, String> itemdescription;

    @FXML
    private Label lblNoOfItems;

    private int editRow = -1;

    private ArrayList<String> batchList = new ArrayList<>();
    @FXML
    private BorderPane display;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtBatchNo.setVisible(false);
        dtAddedDate.setValue(LocalDate.now());
        txtSupplierID.setText(supplierID);
        nextPurchaseID();
        btnAddNewItem.setOnAction(this);
        loadAllItems();
        cmbItemDescription.setOnAction(this);
        btnCancel.setOnAction(this);
        TextFields.bindAutoCompletion(cmbItemDescription.getEditor(), cmbItemDescription.getItems());
        btnAddToCart.setOnAction(this);
        btnRemoveItem.setOnAction(this);
        listiners();
        btnEditItem.setOnAction(this);
        purchaseStockDetails.addListener((Change<? extends PurchaseStockTM> c) -> {
            lblNoOfItems.setText(String.valueOf(purchaseStockDetails.size()));
        });
        btnPayment.setOnAction(this);
        getNextBatchID();
        btnPayment.setOnAction(this);
        makeTable();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAddNewItem) {
            try {
                BorderPane borderPane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/AddItemForm.fxml"));
                Scene scene = new Scene(borderPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                loadAllItems();
            } catch (IOException ex) {
                Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!cmbItemDescription.getEditor().getText().isEmpty() && event.getSource() == cmbItemDescription) {
            try {

                ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setDescription(cmbItemDescription.getEditor().getText());
                ArrayList<ItemDTO> item = itemBO.searchItem(itemDTO);
                for (ItemDTO temp : item) {
                    txtItemCode.setText(temp.getItemCode());
                    txtLocationID.setText(temp.getLocationId());
                    txtCategory.setText(temp.getCategory());
                }
                LocationDTO locationDTO = new LocationDTO();
                locationDTO.setLocationId(txtLocationID.getText());
                LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
                ArrayList<LocationDTO> location = locationBO.searchLocation(locationDTO);
                for (LocationDTO temp : location) {
                    txtSectionName.setText(temp.getSectionName());
                    txtRackNo.setText(temp.getRackNo());
                }
                getBatchDetaisForItemCode();
            } catch (Exception ex) {
                Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnAddToCart && btnAddToCart.getText().equals("Add To Cart")) {
            getBatchID();
            if (checkValues()) {

                addItemToTable(makePurchaseStockTM());
            }
        }
        if (event.getSource() == btnEditItem) {
            if (tblStockDetails.getSelectionModel().getSelectedItem() != null) {
                txtBatchNo.setVisible(true);
                PurchaseStockTM purchaseStockTM = tblStockDetails.getSelectionModel().getSelectedItem();
                cmbItemDescription.getSelectionModel().select(purchaseStockTM.getItemdescription());
                txtBatchNo.setText(purchaseStockTM.getBatchNo());
                txtQTY.setText(purchaseStockTM.getQTY() + "");
                txtUnitPrice.setText(purchaseStockTM.getUnitPrice() + "");
                txtSellingPrice.setText(purchaseStockTM.getSellingPrice() + "");
                if (purchaseStockTM.getEXP() != null) {
                    dtEXP.setValue(LocalDate.parse(purchaseStockTM.getEXP()));
                }
                if (purchaseStockTM.getMFD() != null) {
                    dtMFD.setValue(LocalDate.parse(purchaseStockTM.getMFD()));
                }

                btnCancel.setVisible(true);
                btnAddToCart.setText("Update");
                editRow = tblStockDetails.getSelectionModel().getSelectedIndex();
            }
        }
        if (event.getSource() == btnAddToCart && btnAddToCart.getText().equals("Update")) {
            if (checkValues()) {
                updateItemToTable(makePurchaseStockTM());
                clear();
                txtBatchNo.setVisible(false);
                btnCancel.setVisible(false);
                btnAddToCart.setText("Add To Cart");
            }
        }
        if (event.getSource() == btnCancel) {
            btnAddToCart.setText("Add To Cart");
            clear();
            btnCancel.setVisible(false);
        }
        if (event.getSource() == btnRemoveItem) {
            if (tblStockDetails.getSelectionModel().getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Item");
                alert.setHeaderText("Are you sure ?");
                ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(btnYes, btnCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == btnYes) {
                    double value = tblStockDetails.getSelectionModel().getSelectedItem().getTotal();
                    double netValue = Double.parseDouble(txtTotalPrice.getText());
                    txtTotalPrice.setText(String.valueOf(netValue - value));
                    purchaseStockDetails.remove(tblStockDetails.getSelectionModel().getSelectedItem());
                    tblStockDetails.setItems(purchaseStockDetails);
                }
            }
        }
        if (event.getSource() == btnPayment && !purchaseStockDetails.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Do you want to add this Stock");
            alert.setHeaderText("You want be able to change Stock Items\n Make sure everything is OK!");
            ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnYes, btnCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btnYes) {
                savePurchaseStock();
                try {
                    SupplierPaymentFormController.setPurchaseStockDetails(purchaseStockDetails, txtPurchaseID.getText(), txtSupplierID.getText());
                    BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/SupplierPaymentForm.fxml"));
                    display.setBackground(Background.EMPTY);
                    display.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (event.getSource() == btnPayment) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Make Payment");
            alert.setHeaderText("Please add an Item !");
            alert.showAndWait();
        }
    }

    private void listiners() {
        txtSellingPrice.textProperty().addListener((ob, old, newValue) -> {
            if (!newValue.isEmpty() && !txtUnitPrice.getText().isEmpty()) {

            }
        });
        txtUnitPrice.textProperty().addListener((ob, old, newValue) -> {
            if (!newValue.isEmpty() && !txtQTY.getText().isEmpty()) {
                double price = Double.parseDouble(txtUnitPrice.getText());
                double amount = Double.parseDouble(txtQTY.getText());
                txtTotal.setText((price * amount) + "");
            }
            if (newValue.isEmpty()) {
                txtTotal.setText("");
            }
        });
        txtQTY.textProperty().addListener((ob, old, newValue) -> {
            if (!newValue.isEmpty() && !txtUnitPrice.getText().isEmpty()) {
                double price = Double.parseDouble(txtUnitPrice.getText());
                double amount = Double.parseDouble(txtQTY.getText());
                txtTotal.setText((price * amount) + "");
            }
            if (newValue.isEmpty()) {
                txtTotal.setText("");
            }
        });

    }

    private void getNextBatchID() {
        try {
            batchList.add(new IDGenerator(IDGenerator.TableName.BATCH).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void setSupplierID(String SupplierID) {
        PurchaseStockFormController.supplierID = SupplierID;
    }

    private void nextPurchaseID() {
        try {
            txtPurchaseID.setText(new IDGenerator(IDGenerator.TableName.PURCHASESTOCK).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllItems() {
        try {
            ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
            itemDTOs = itemBO.getAllItems();
            for (ItemDTO temp : itemDTOs) {
                cmbItemDescription.getItems().add(temp.getDescription());
            }
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkValues() {

        if (!txtItemCode.getText().isEmpty() && !txtQTY.getText().isEmpty()
                && !txtSellingPrice.getText().isEmpty() && !txtBatchNo.getText().isEmpty()
                && !txtLocationID.getText().isEmpty() && !txtUnitPrice.getText().isEmpty()) {
            return true;
        }

        return false;
    }

    private void addItemToTable(PurchaseStockTM purchaseStockTM) {
        double value = purchaseStockTM.getTotal();
        double nowValue = Double.parseDouble(txtTotalPrice.getText());
        txtTotalPrice.setText(String.valueOf((nowValue + value)));
        if (exist(purchaseStockTM)) {

        } else {
            purchaseStockDetails.add(purchaseStockTM);
            tblStockDetails.setItems(purchaseStockDetails);
        }
        clear();
    }

    private PurchaseStockTM makePurchaseStockTM() {

        String EXPdate;
        String MFDdate;
        if (dtEXP.getValue() == null) {
            EXPdate = null;
        } else {
            EXPdate = dtEXP.getValue().toString();
        }
        if (dtMFD.getValue() == null) {
            MFDdate = null;
        } else {
            MFDdate = dtMFD.getValue().toString();
        }
        String dectcription = cmbItemDescription.getSelectionModel().getSelectedItem();
        PurchaseStockTM purchaseStockTM = new PurchaseStockTM(
                txtItemCode.getText(),
                txtBatchNo.getText(),
                dectcription,
                txtLocationID.getText(),
                EXPdate,
                MFDdate,
                txtRackNo.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Double.parseDouble(txtSellingPrice.getText()),
                Double.parseDouble(txtQTY.getText()),
                Double.parseDouble(txtTotal.getText()),
                txtSectionName.getText()
        );
        return purchaseStockTM;
    }

    private void clear() {
        txtBatchNo.clear();
        txtCategory.clear();
        txtItemCode.clear();
        txtLocationID.clear();
        txtQTY.clear();
        txtQtyOnHand.clear();
        txtRackNo.clear();
        txtSectionName.clear();
        txtSellingPrice.clear();
        txtUnitPrice.clear();
        txtTotal.clear();
        dtEXP.setValue(null);
        dtMFD.setValue(null);
        cmbItemDescription.getSelectionModel().select(null);
    }

    private void makeTable() {
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        batchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        itemdescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
        locationID.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        EXP.setCellValueFactory(new PropertyValueFactory<>("EXP"));
        MFD.setCellValueFactory(new PropertyValueFactory<>("MFD"));
        rackNo.setCellValueFactory(new PropertyValueFactory<>("rackNo"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        sellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        QTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        tblStockDetails.setItems(purchaseStockDetails);
    }

    private void updateItemToTable(PurchaseStockTM makePurchaseStockTM) {
        double oldValue = tblStockDetails.getItems().get(editRow).getTotal();
        double newValue = makePurchaseStockTM.getTotal();
        double nowValue = Double.parseDouble(txtTotalPrice.getText());
        txtTotalPrice.setText(String.valueOf((nowValue - oldValue) + newValue));
        PurchaseStockTM temp = purchaseStockDetails.get(editRow);

        if (makePurchaseStockTM.getItemCode().compareToIgnoreCase(temp.getItemCode()) != 0) {

            if (exist(makePurchaseStockTM)) {
                purchaseStockDetails.remove(temp);
                tblStockDetails.setItems(purchaseStockDetails);
                tblStockDetails.refresh();
            } else {

            }

        } else {
            purchaseStockDetails.remove(tblStockDetails.getItems().get(editRow));
            purchaseStockDetails.add(editRow, makePurchaseStockTM);
            tblStockDetails.setItems(purchaseStockDetails);
            tblStockDetails.refresh();
        }
    }

    private void getBatchDetaisForItemCode() {
        try {
            BatchDetailDTO batchDetailDTO = new BatchDetailDTO();
            batchDetailDTO.setItemCode(txtItemCode.getText());
            BatchDetailBO batchDetailBO = (BatchDetailBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.BATCHDETAIL);
            double qty = batchDetailBO.qtyOnHandForItem(batchDetailDTO);
            txtQtyOnHand.setText(String.valueOf(qty));
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getBatchID() {
        try {
            BatchDetailDTO batchDetailDTO = new BatchDetailDTO();
            batchDetailDTO.setItemCode(txtItemCode.getText());
            BatchDetailBO batchDetailBO = (BatchDetailBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.BATCHDETAIL);
            ArrayList<BatchDetailDTO> batchDetailDTOs = batchDetailBO.searchBatchDetails(batchDetailDTO);
            for (BatchDetailDTO temp : batchDetailDTOs) {
                if (temp.getQtyOnHand() > 0
                        && temp.getUnitPrice() == Double.parseDouble(txtUnitPrice.getText())
                        && temp.getSellingPrice() == Double.parseDouble(txtSellingPrice.getText())) {
                    if (temp.getEXD() == null && temp.getEXD() == null && dtMFD.getValue() == null && dtEXP.getValue() == null) {
                        txtBatchNo.setText(temp.getBatchNo());
                    } else if (temp.getMFD() == null && temp.getEXD() != null
                            && dtMFD.getValue() == null && dtEXP.getValue() != null
                            && temp.getEXD().equalsIgnoreCase(dtEXP.getValue().toString())) {
                        txtBatchNo.setText(temp.getBatchNo());
                    } else if (temp.getMFD() != null && temp.getEXD() == null
                            && dtMFD.getValue() != null && dtEXP.getValue() == null
                            && temp.getMFD().equalsIgnoreCase(dtMFD.getValue().toString())) {
                        txtBatchNo.setText(temp.getBatchNo());
                    }

                }
            }
            if (txtBatchNo.getText().isEmpty()) {
                txtBatchNo.setText(makeNextBatchID());
            }
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean exist(PurchaseStockTM purchaseStockTM) {
        for (int i = 0; i < purchaseStockDetails.size(); i++) {
            if (purchaseStockDetails.get(i).getItemCode().compareToIgnoreCase(purchaseStockTM.getItemCode()) == 0
                    && purchaseStockDetails.get(i).getUnitPrice() == purchaseStockTM.getUnitPrice()
                    && purchaseStockDetails.get(i).getSellingPrice() == purchaseStockTM.getSellingPrice()) {

                boolean exist = false;

                if (purchaseStockTM.getEXP() == null && purchaseStockTM.getMFD() == null
                        && purchaseStockDetails.get(i).getMFD() == null && purchaseStockDetails.get(i).getEXP() == null) {
                    exist = true;
                } else if (purchaseStockTM.getMFD() != null && purchaseStockDetails.get(i).getMFD() == null
                        && purchaseStockTM.getEXP() != null && purchaseStockDetails.get(i).getEXP() == null) {
                    exist = true;
                } else if (purchaseStockTM.getMFD() == null && purchaseStockDetails.get(i).getMFD() == null
                        && purchaseStockTM.getEXP() != null && purchaseStockDetails.get(i).getEXP() != null
                        && purchaseStockDetails.get(i).getEXP().equalsIgnoreCase(purchaseStockTM.getEXP())) {
                    exist = true;
                } else if (purchaseStockTM.getMFD() != null && purchaseStockDetails.get(i).getMFD() != null
                        && purchaseStockTM.getEXP() == null && purchaseStockDetails.get(i).getEXP() != null
                        && purchaseStockDetails.get(i).getMFD().equalsIgnoreCase(purchaseStockTM.getMFD())) {
                    exist = true;
                } else if (purchaseStockTM.getMFD() != null && purchaseStockDetails.get(i).getMFD() != null
                        && purchaseStockTM.getEXP() != null && purchaseStockDetails.get(i).getEXP() != null
                        && purchaseStockDetails.get(i).getEXP().equalsIgnoreCase(purchaseStockTM.getEXP())
                        && purchaseStockDetails.get(i).getMFD().equalsIgnoreCase(purchaseStockTM.getMFD())) {
                    exist = true;
                }
                if (exist) {
                    purchaseStockDetails.get(i).setEXP(purchaseStockTM.getEXP());
                    purchaseStockDetails.get(i).setMFD(purchaseStockTM.getMFD());
                    purchaseStockDetails.get(i).setQTY(purchaseStockDetails.get(i).getQTY() + purchaseStockTM.getQTY());
                    purchaseStockDetails.get(i).setTotal(purchaseStockDetails.get(i).getTotal() + purchaseStockTM.getTotal());
                    PurchaseStockTM n = purchaseStockDetails.get(i);
                    purchaseStockDetails.remove(purchaseStockDetails.get(i));
                    purchaseStockDetails.add(i, n);
                    tblStockDetails.setItems(purchaseStockDetails);
                    tblStockDetails.refresh();
                    batchList.remove(batchList.size() - 1);
                    return true;
                }
            }
        }
        return false;
    }

    private String makeNextBatchID() {
        if (purchaseStockDetails.isEmpty()) {
            return batchList.get(0);
        } else {
            String prifix = "B";
            String l = batchList.get(batchList.size() - 1);
            String arr[] = l.split(prifix);
            String batch;
            int i = Integer.parseInt(arr[1]) + 1;
            if (i < 10) {
                batch = prifix + "00" + i;
            } else if (i < 100) {
                batch = prifix + "0" + i;
            } else {
                batch = prifix + i;
            }
            batchList.add(batch);
            return batch;
        }
    }

    private boolean savePurchaseStock() {

        ArrayList<BatchDTO> batchDTOs = new ArrayList<>();
        for (String temp : batchList) {
            BatchDTO batchDTO = makeBatchDTO(temp);
            if (batchDTO != null) {
                batchDTOs.add(makeBatchDTO(temp));
            }
        }
        ArrayList<BatchDetailDTO> updateBatchDetail = new ArrayList<>();

        ArrayList<BatchDetailDTO> addBatchDetail = new ArrayList<>();

        for (PurchaseStockTM temp : purchaseStockDetails) {
            boolean update = true;
            for (String batch : batchList) {
                if (temp.getBatchNo().equalsIgnoreCase(batch)) {
                    update = false;
                    addBatchDetail.add(makeAddBatchDetailDTO(temp));
                }
            }
            if (update) {
                updateBatchDetail.add(makeUpateBatchDetailDTO(temp));
            }
        }

        PurchaseStockDTO purchaseStock = makePurchaseStockDTO();

        ArrayList<PurchaseStockDetailDTO> purchaseStockDetailDTOs = new ArrayList<>();
        for(PurchaseStockTM temp: purchaseStockDetails){
            purchaseStockDetailDTOs.add(makePurchaseStockDetail(temp, purchaseStock));
        }
        PurchaseStockFillingBO purchaseStockFillingBO = (PurchaseStockFillingBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.PURCHASESTOCKFILLING);
        return purchaseStockFillingBO.savePurchaseStockDetails(purchaseStock, batchDTOs, addBatchDetail, updateBatchDetail, purchaseStockDetailDTOs);
    }

    private PurchaseStockDTO makePurchaseStockDTO() {
        return new PurchaseStockDTO(
                txtPurchaseID.getText(),
                txtSupplierID.getText(),
                String.valueOf(dtAddedDate.getValue())
        );
    }

    private BatchDTO makeBatchDTO(String temp) {
        for (PurchaseStockTM pstm : purchaseStockDetails) {
            if (pstm.getBatchNo().equalsIgnoreCase(temp)) {
                return new BatchDTO(temp);
            }
        }
        return null;
    }

    private BatchDetailDTO makeAddBatchDetailDTO(PurchaseStockTM temp) {
        return new BatchDetailDTO(
                temp.getBatchNo(),
                temp.getItemCode(),
                temp.getUnitPrice(),
                temp.getSellingPrice(),
                temp.getQTY(),
                temp.getQTY(),
                temp.getEXP(),
                temp.getMFD()
        );

    }

    private BatchDetailDTO makeUpateBatchDetailDTO(PurchaseStockTM temp) {
        BatchDetailDTO batchDetailDTO = new BatchDetailDTO();
        BatchDetailDTO qtyDetail = getQTYDetail(temp.getBatchNo());
        batchDetailDTO.setEXD(temp.getEXP());
        batchDetailDTO.setMFD(temp.getMFD());
        batchDetailDTO.setQTY(qtyDetail.getQTY() + temp.getQTY());
        batchDetailDTO.setQtyOnHand(qtyDetail.getQtyOnHand() + temp.getQTY());
        batchDetailDTO.setBatchNo(temp.getBatchNo());
        return batchDetailDTO;
    }

    private BatchDetailDTO getQTYDetail(String BatchNo) {
        BatchDetailDTO batchDetailDTO = new BatchDetailDTO();
        batchDetailDTO.setBatchNo(BatchNo);
        BatchDetailBO batchDetailBO = (BatchDetailBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.BATCHDETAIL);
        ArrayList<BatchDetailDTO> batchDetail = new ArrayList<>();
        try {
            batchDetail = batchDetailBO.qtyOnHandForBatchNo(batchDetailDTO);
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (batchDetail.isEmpty()) {
            return null;
        }
        return batchDetail.get(0);
    }

    private PurchaseStockDetailDTO makePurchaseStockDetail(PurchaseStockTM temp, PurchaseStockDTO purchaseStock) {
        return new PurchaseStockDetailDTO(purchaseStock.getPurchaseId(), temp.getBatchNo(),temp.getQTY());
    }

}
