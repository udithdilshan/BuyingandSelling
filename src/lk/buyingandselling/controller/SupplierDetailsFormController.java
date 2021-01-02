/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.supplier.SupplierBO;
import lk.buyingandselling.model.DTO.SupplierDTO;
import lk.buyingandselling.model.TM.SupplierTM;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class SupplierDetailsFormController implements Initializable {

    @FXML
    private Button btnAddCustomer;
    @FXML
    private Button btnRemoveSupplier;
    @FXML
    private Button btnEditSupplier;
    @FXML
    private TableView<SupplierTM> tblSupplierDetails;
    @FXML
    private TableColumn<SupplierTM, String> SupplierID;
    @FXML
    private TableColumn<SupplierTM, String> SupplierName;
    @FXML
    private TableColumn<SupplierTM, String> NIC;
    @FXML
    private TableColumn<SupplierTM, String> gender;
    @FXML
    private TableColumn<SupplierTM, String> companyName;
    @FXML
    private TableColumn<SupplierTM, String> address;
    @FXML
    private TableColumn<SupplierTM, String> city;
    @FXML
    private TableColumn<SupplierTM, Integer> mobileNumber;
    @FXML
    private TableColumn<SupplierTM, String> postalCode;
    @FXML
    private TableColumn<SupplierTM, String> email;
    @FXML
    private TableColumn<SupplierTM, String> addedDate;

    private ObservableList<SupplierTM> allSupplierDetails = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeSupplierDetailsTabel();
        loadAllSupplierDetails();
        btnEditSupplier.setDisable(true);
        btnRemoveSupplier.setDisable(true);
        tblSupplierDetails.getSelectionModel().selectedItemProperty().addListener((ob, oldSelect, newSelect) -> {
            if (newSelect != null) {
                btnEditSupplier.setDisable(false);
                btnRemoveSupplier.setDisable(false);
            } else {
                btnEditSupplier.setDisable(true);
                btnRemoveSupplier.setDisable(true);
            }
        });
    }

    @FXML
    private void showAddSupplierForm(ActionEvent event) {
         try {
            BorderPane customerRegistrationForm = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/SupplierRegistrationForm.fxml"));
            Scene supplierRegistrationScene = new Scene(customerRegistrationForm);
            Stage supplierRegistrationStage = new Stage();
            supplierRegistrationStage.setScene(supplierRegistrationScene);
            supplierRegistrationStage.initModality(Modality.APPLICATION_MODAL);
            supplierRegistrationStage.showAndWait();
            loadAllSupplierDetails();
        } catch (IOException ex) {
            Logger.getLogger(CustomerDetailsFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void removeSelectedSupplier(ActionEvent event) {
        if (tblSupplierDetails.getSelectionModel().getSelectedItem() != null) {
            SupplierTM selectedSupplierTM = tblSupplierDetails.getSelectionModel().getSelectedItem();

            if (showSupplierDetails(selectedSupplierTM)) {
                try {
                    tblSupplierDetails.getItems().remove(selectedSupplierTM);
                    SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIER);
                    boolean supplierDeleted = supplierBO.deleteSupplier(selectedSupplierTM.getSupplierId());

                    if (supplierDeleted) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Delete Supplier");
                        alert.setHeaderText(null);
                        alert.setContentText("Supplier Deleted Successfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Delete Supplier");
                        alert.setHeaderText(null);
                        alert.setContentText("Supplier Delete Failed!");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CustomerDetailsFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void editSelectedSupplier(ActionEvent event) {
        if (tblSupplierDetails.getSelectionModel().getSelectedItem() != null) {
            SupplierRegistrationFormController.setEditCustomerDetails(tblSupplierDetails.getSelectionModel().getSelectedItem());
            showAddSupplierForm(event);
            CustomerRegistrationFormController.setEditCustomerDetails(null);
        }
    }

    private void makeSupplierDetailsTabel() {
        SupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        SupplierName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        NIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        companyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        addedDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        tblSupplierDetails.setItems(allSupplierDetails);

    }

    private void loadAllSupplierDetails() {
        try {
            allSupplierDetails.clear();
            SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIER);
            List<SupplierDTO> allSuppliers = supplierBO.getAllSuppliers();
            for (SupplierDTO supplier : allSuppliers) {
                allSupplierDetails.add(makeSupplierTM(supplier));
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierDetailsFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private SupplierTM makeSupplierTM(SupplierDTO supplierDTO) {
        SupplierTM supplierTM = new SupplierTM(supplierDTO.getSupplierId(),
                supplierDTO.getFirstname(), supplierDTO.getLastname(),
                supplierDTO.getAddress(), supplierDTO.getCompanyName(),
                supplierDTO.getNIC(), supplierDTO.getGender(),
                supplierDTO.getCity(), supplierDTO.getMobileNumber(),
                supplierDTO.getEmail(), supplierDTO.getPostalCode(),
                supplierDTO.getAddedDate());
        supplierTM.setFirstname(supplierTM.getFirstname().concat(" ").concat(supplierTM.getLastname()));
        return supplierTM;
    }

    private boolean showSupplierDetails(SupplierTM selectedSupplierTM) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Supplier");
        alert.setHeaderText("Are sure you want to delete this supplier ?");
        alert.setContentText("Customer ID : " + selectedSupplierTM.getSupplierId()
                + "\nCustomer Name : " + selectedSupplierTM.getFirstname()
                + "\nCompany Name : " + selectedSupplierTM.getCompanyName()
                + "\nNIC : " + selectedSupplierTM.getNIC()
                + "\nGender : " + selectedSupplierTM.getGender()
                + "\nContact No : " + selectedSupplierTM.getMobileNumber()
                + "\nAddress : " + selectedSupplierTM.getAddress()
                + "\nCity : " + selectedSupplierTM.getCity()
                + "\nPostal Code : " + selectedSupplierTM.getPostalCode()
                + "\nEmail : " + selectedSupplierTM.getEmail()
                + "\nAdded Date : " + selectedSupplierTM.getAddedDate()
        );
        ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnCancel, btnYes);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == btnYes); 
    }

}
