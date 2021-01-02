package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.supplier.SupplierBO;
import lk.buyingandselling.model.DTO.SupplierDTO;
import lk.buyingandselling.model.TM.CustomerTM;
import lk.buyingandselling.model.TM.SupplierTM;
import lk.buyingandselling.util.IDGenerator;

public class SupplierRegistrationFormController implements Initializable{

    @FXML
    private TextField txtSupplierID;
    @FXML
    private JFXDatePicker dtAddedDate;
    @FXML
    private TextField txtF_Name;
    @FXML
    private TextField txtL_Name;
    @FXML
    private TextField txtNIC;
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
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private RadioButton rabMale;
    @FXML
    private RadioButton rabFemale;

    private SupplierDTO baSupplier = new SupplierDTO();

    private ArrayList<TextField> arrFields = new ArrayList();

    private static SupplierTM editsupplierTM=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setToggleGroup();
        setTextFieldValidate();
        txtSupplierID.setEditable(false);
        getNextSupplierID();
        dtAddedDate.setDisable(true);
        dtAddedDate.setValue(LocalDate.now());
        setEditCustomerProperties();
    }

    public void clear() {
        txtSupplierID.clear();;
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
        getNextSupplierID();
    }

    public void getNextSupplierID() {
        try {
            txtSupplierID.setText(new IDGenerator(IDGenerator.TableName.SUPPLIER).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(SupplierRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setToggleGroup() {
        ToggleGroup tgGender = new ToggleGroup();
        rabMale.setToggleGroup(tgGender);
        rabFemale.setToggleGroup(tgGender);
    }

    public void setTextFieldValidate() {
        txtNIC.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputNIC(observable, oldValue, newValue);
        });
        txtMobileNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            validateInputMobileNumber(observable, oldValue, newValue);
        });
    }

    public SupplierDTO setSupplierDetails() {
        dtAddedDate.setValue(LocalDate.now());
        SupplierDTO supplierDetails = new SupplierDTO();
        if (rabMale.isSelected()) {
            supplierDetails.setGender("Male");
        }
        if (rabFemale.isSelected()) {
            supplierDetails.setGender("Female");
        }
        supplierDetails.setSupplierId(txtSupplierID.getText());
        supplierDetails.setFirstname(txtF_Name.getText());
        supplierDetails.setLastname(txtL_Name.getText());
        supplierDetails.setNIC(txtNIC.getText());
        supplierDetails.setCompanyName(txtCompanyName.getText());
        supplierDetails.setAddress(txtAddress.getText());
        supplierDetails.setCity(txtCity.getText());
        supplierDetails.setPostalCode(txtPostalCode.getText());
        supplierDetails.setMobileNumber(Integer.parseInt(txtMobileNumber.getText()));
        supplierDetails.setEmail(txtEmail.getText());
        supplierDetails.setAddedDate(dtAddedDate.getValue().toString());
        return supplierDetails;
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

    private void closeWindow(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static void setEditCustomerDetails(SupplierTM supplierTM) {
        editsupplierTM = supplierTM;
    }
    @FXML
    private void cancelAddSupplier(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    private void saveAndUpdateSupplier(ActionEvent event) {
        try {
            if (btnSave.getText().compareToIgnoreCase("Save Supplier") == 0) {
                SupplierDTO supplierDTO = setSupplierDetails();
                SupplierBO supplerBO = (SupplierBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIER);
                boolean status = supplerBO.addSupplier(supplierDTO);
                if (status) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Supplier");
                    alert.setHeaderText(null);
                    alert.setContentText("Supplier Added successfully");
                    alert.showAndWait();
                    clear();
                } else {
                    JOptionPane.showMessageDialog(null, " Error !");
                }
            }
            if (btnSave.getText().compareToIgnoreCase("Update Supplier") == 0) {
                SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIER);
                if (supplierBO.deleteSupplier(txtSupplierID.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Supplier");
                    alert.setHeaderText(null);
                    alert.setContentText("Supplier Updated successfully");
                    alert.showAndWait();
                    clear();
                    closeWindow(event);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierRegistrationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setEditCustomerProperties() {
        if (editsupplierTM != null) {
            btnSave.setText("Update Supplier");
            txtSupplierID.setText(editsupplierTM.getSupplierId());
            txtF_Name.setText(editsupplierTM.getFirstname().split(" ")[0]);
            txtL_Name.setText(editsupplierTM.getLastname());
            txtNIC.setText(editsupplierTM.getNIC());
            txtCompanyName.setText(editsupplierTM.getCompanyName());
            txtAddress.setText(editsupplierTM.getAddress());
            txtCity.setText(editsupplierTM.getCity());
            txtPostalCode.setText(editsupplierTM.getPostalCode());
            txtMobileNumber.setText(String.valueOf(editsupplierTM.getMobileNumber()));
            txtEmail.setText(editsupplierTM.getEmail());
            if (editsupplierTM.getGender().compareTo("Male") == 0) {
                rabMale.setSelected(true);
            } else {
                rabFemale.setSelected(true);
            }
        }
    }
}
