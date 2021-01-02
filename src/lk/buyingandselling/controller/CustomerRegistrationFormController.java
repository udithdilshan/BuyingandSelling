/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.customer.CustomerBO;
import lk.buyingandselling.model.DTO.CustomerDTO;
import lk.buyingandselling.model.TM.CustomerTM;
import lk.buyingandselling.util.IDGenerator;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class CustomerRegistrationFormController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtCustomerID;
    @FXML
    private JFXDatePicker dtAddedDate;
    @FXML
    private TextField txtF_Name;
    @FXML
    private TextField txtL_Name;
    @FXML
    private TextField txtNIC;
    @FXML
    private RadioButton rabMale;
    @FXML
    private RadioButton rabFemale;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtMobileNumber;
    @FXML
    private TextField txtEmail;

    private static CustomerTM editCustomerTM = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtCustomerID.setEditable(false);
        getNextCustometID();
        dtAddedDate.setDisable(true);
        dtAddedDate.setValue(LocalDate.now());
        setToggleGroup();
        setEditCustomerProperties();
        setTextFieldValidate();
    }

    private void getNextCustometID() {
        try {
            txtCustomerID.setText(new IDGenerator(IDGenerator.TableName.CUSTOMER).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(CustomerRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setToggleGroup() {
        ToggleGroup tgGender = new ToggleGroup();
        rabMale.setToggleGroup(tgGender);
        rabFemale.setToggleGroup(tgGender);
    }

    @SuppressWarnings("empty-statement")
    public void clear() {
        txtCustomerID.clear();;
        txtF_Name.clear();
        txtL_Name.clear();
        txtNIC.clear();
        txtCompanyName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtPostalCode.clear();
        txtMobileNumber.clear();
        rabFemale.setSelected(false);
        rabMale.setSelected(false);
        txtEmail.clear();
        dtAddedDate.setValue(LocalDate.now());
        getNextCustometID();
    }

    private CustomerDTO setCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        if (rabFemale.isSelected()) {
            customerDTO.setGender("Female");
        }
        if (rabMale.isSelected()) {
            customerDTO.setGender("Male");
        }
        customerDTO.setCustomerId(txtCustomerID.getText());
        customerDTO.setFirstname(txtF_Name.getText());
        customerDTO.setLastname(txtL_Name.getText());
        customerDTO.setAddedDate(dtAddedDate.getValue().toString());
        customerDTO.setCity(txtCity.getText());
        customerDTO.setCompanyName(txtCompanyName.getText());
        customerDTO.setNIC(txtNIC.getText());
        customerDTO.setAddress(txtAddress.getText());
        customerDTO.setEmail(txtEmail.getText());
        customerDTO.setPostalCode(txtPostalCode.getText());
        customerDTO.setMobile(Integer.parseInt(txtMobileNumber.getText()));
        return customerDTO;
    }

    public static void setEditCustomerDetails(CustomerTM customerTM) {
        editCustomerTM = customerTM;
    }

    private void setEditCustomerProperties() {
        if (editCustomerTM != null) {
            btnSave.setText("Update Customer");
            txtCustomerID.setText(editCustomerTM.getCustomerId());
            txtF_Name.setText(editCustomerTM.getFirstname().split(" ")[0]);
            txtL_Name.setText(editCustomerTM.getLastname());
            txtNIC.setText(editCustomerTM.getNIC());
            txtCompanyName.setText(editCustomerTM.getCompanyName());
            txtAddress.setText(editCustomerTM.getAddress());
            txtCity.setText(editCustomerTM.getCity());
            txtPostalCode.setText(editCustomerTM.getPostalCode());
            txtMobileNumber.setText(String.valueOf(editCustomerTM.getMobile()));
            txtEmail.setText(editCustomerTM.getEmail());
            if (editCustomerTM.getGender().compareTo("Male") == 0) {
                rabMale.setSelected(true);
            } else {
                rabFemale.setSelected(true);
            }
        }
    }

    @FXML
    private void cancelCustomer(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    private void saveAndUpdateCustomer(ActionEvent event) {
        if (btnSave.getText().compareToIgnoreCase("Save Customer") == 0) {
            try {
                CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMER);
                boolean status = customerBO.addCustomer(setCustomerDTO());
                if (status) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Customer");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer Added successfully");
                    alert.showAndWait();
                    clear();
                }
            } catch (Exception ex) {
                Logger.getLogger(CustomerRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (btnSave.getText().compareToIgnoreCase("Update Customer") == 0) {
            try {
                CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMER);
                boolean status = customerBO.updateCustomer(setCustomerDTO());
                if (status) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Customer");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer Updated successfully");
                    alert.showAndWait();
                    clear();
                    closeWindow(event);
                }
            } catch (Exception ex) {
                Logger.getLogger(CustomerRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setTextFieldValidate() {
        txtNIC.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputNIC(observable, oldValue, newValue);
        });
        txtMobileNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputMobileNumber(observable, oldValue, newValue);
        });
    }

    public void validateInputNIC(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.matches("[0-9]")) {
            if (newValue.length() < 10) {
                txtNIC.setText(newValue.replaceAll("[^0-9]", ""));
            } else if (newValue.length() <= 10 && (newValue.charAt(newValue.length() - 1) == 'v'
                    || newValue.charAt(newValue.length() - 1) == 'V')) {
                txtNIC.setText(newValue.replaceAll("[^0-9,v,V]", ""));
            } else if (newValue.length() == 10 && (newValue.charAt(newValue.length() - 1) != 'v'
                    || newValue.charAt(newValue.length() - 1) != 'V')) {
                txtNIC.setText(newValue.replaceAll("[^0-9]", ""));
            } else if (newValue.length() > 10 && newValue.length() < 13) {
                txtNIC.setText(newValue.replaceAll("[^0-9]", ""));
            } else {
                txtNIC.setText(oldValue);
            }
        }
    }

    public void validateInputMobileNumber(ObservableValue observable, String oldValue, String newValue) {
        if (!newValue.matches("[0-9]")) {
            if (newValue.length() < 11) {
                txtMobileNumber.setText(newValue.replaceAll("[^0-9]", ""));
            } else {
                txtMobileNumber.setText(oldValue);
            }
        }
    }
}
