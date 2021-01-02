/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
import lk.buyingandselling.BO.customer.CustomerBO;
import lk.buyingandselling.model.DTO.CustomerDTO;
import lk.buyingandselling.model.TM.CustomerTM;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class CustomerDetailsFormController implements Initializable {

    @FXML
    private Button btnAddCustomer;
    @FXML
    private Button btnRemoveCustomer;
    @FXML
    private Button btnEditCustomer;
    @FXML
    private TableView<CustomerTM> tblCustomerDetails;
    @FXML
    private TableColumn<CustomerTM, String> customerID;
    @FXML
    private TableColumn<CustomerTM, String> customerName;
    @FXML
    private TableColumn<CustomerTM, String> NIC;
    @FXML
    private TableColumn<CustomerTM, String> gender;
    @FXML
    private TableColumn<CustomerTM, String> companyName;
    @FXML
    private TableColumn<CustomerTM, String> address;
    @FXML
    private TableColumn<CustomerTM, String> city;
    @FXML
    private TableColumn<CustomerTM, String> postalCode;
    @FXML
    private TableColumn<CustomerTM, String> email;
    @FXML
    private TableColumn<CustomerTM, Integer> mobileNumber;
    @FXML
    private TableColumn<CustomerTM, String> addedDate;

    private ObservableList<CustomerTM> allCustomerDetails = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set Customer Details Tabel Properties
        makeCustomerDetailsTabel();
        //load Customer Details
        loadAllCustomerDetails();
        btnEditCustomer.setDisable(true);
        btnRemoveCustomer.setDisable(true);
        tblCustomerDetails.getSelectionModel().selectedItemProperty().addListener((ob, oldSelect, newSelect) -> {
            if (newSelect != null) {
                btnEditCustomer.setDisable(false);
                btnRemoveCustomer.setDisable(false);
            } else {
                btnEditCustomer.setDisable(true);
                btnRemoveCustomer.setDisable(true);
            }
        });
    }

    private void loadAllCustomerDetails() {
        try {
            CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMER);
            ArrayList<CustomerDTO> customerDetails = customerBO.getAllCustomers();
            allCustomerDetails.clear();
            for (CustomerDTO customerDetailDTO : customerDetails) {
                if (customerDetailDTO.getFirstname() != null) {
                    allCustomerDetails.add(makeCustomerTM(customerDetailDTO));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerDetailsFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeCustomerDetailsTabel() {
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        NIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        companyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addedDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        tblCustomerDetails.setItems(allCustomerDetails);

    }

    private CustomerTM makeCustomerTM(CustomerDTO customerDTO) {
        CustomerTM customerTM = new CustomerTM(customerDTO.getCustomerId(),
                customerDTO.getFirstname(), customerDTO.getLastname(),
                customerDTO.getAddress(), customerDTO.getCompanyName(),
                customerDTO.getNIC(), customerDTO.getGender(),
                customerDTO.getCity(), customerDTO.getMobile(),
                customerDTO.getEmail(), customerDTO.getPostalCode(),
                customerDTO.getAddedDate());
        customerTM.setFirstname(customerTM.getFirstname().concat(" ").concat(customerTM.getLastname()));
        return customerTM;
    }

    @FXML
    private void showAddCustomerForm(ActionEvent event) {
        try {
            BorderPane customerRegistrationForm = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerRegistrationForm.fxml"));
            Scene customerRegistrationScene = new Scene(customerRegistrationForm);
            Stage customerRegistrationStage = new Stage();
            customerRegistrationStage.setScene(customerRegistrationScene);
            customerRegistrationStage.initModality(Modality.APPLICATION_MODAL);
            customerRegistrationStage.showAndWait();
            loadAllCustomerDetails();
        } catch (IOException ex) {
            Logger.getLogger(CustomerDetailsFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void removeSelectedCustomer(ActionEvent event) {
        if (tblCustomerDetails.getSelectionModel().getSelectedItem() != null) {
            CustomerTM selectedCustomerTM = tblCustomerDetails.getSelectionModel().getSelectedItem();

            if (showCustomerDetails(selectedCustomerTM)) {
                try {
                    tblCustomerDetails.getItems().remove(selectedCustomerTM);
                    CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMER);
                    boolean customerDeleted = customerBO.deleteCustomer(selectedCustomerTM.getCustomerId());

                    if (customerDeleted) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Delete Customer");
                        alert.setHeaderText(null);
                        alert.setContentText("Customer Deleted Successfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Delete Customer");
                        alert.setHeaderText(null);
                        alert.setContentText("Customer Delete Failed!");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CustomerDetailsFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void editSelectedCustomer(ActionEvent event) {
        if (tblCustomerDetails.getSelectionModel().getSelectedItem() != null) {
            CustomerRegistrationFormController.setEditCustomerDetails(tblCustomerDetails.getSelectionModel().getSelectedItem());
            showAddCustomerForm(event);
            CustomerRegistrationFormController.setEditCustomerDetails(null);
        }
    }

    private boolean showCustomerDetails(CustomerTM selectedCustomerTM) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setHeaderText("Are sure you want to delete this customer ?");
        alert.setContentText("Customer ID : " + selectedCustomerTM.getCustomerId()
                + "\nCustomer Name : " + selectedCustomerTM.getFirstname()
                + "\nCompany Name : " + selectedCustomerTM.getCompanyName()
                + "\nNIC : " + selectedCustomerTM.getNIC()
                + "\nGender : " + selectedCustomerTM.getGender()
                + "\nContact No : " + selectedCustomerTM.getMobile()
                + "\nAddress : " + selectedCustomerTM.getAddress()
                + "\nCity : " + selectedCustomerTM.getCity()
                + "\nPostal Code : " + selectedCustomerTM.getPostalCode()
                + "\nEmail : " + selectedCustomerTM.getEmail()
                + "\nAdded Date : " + selectedCustomerTM.getAddedDate()
        );
        ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnCancel, btnYes);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == btnYes);
    }

}
