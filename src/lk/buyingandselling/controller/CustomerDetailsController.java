/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.customer.CustomerBO;
import lk.buyingandselling.model.DTO.CustomerDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;
import lk.buyingandselling.util.IDGenerator;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class CustomerDetailsController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private BorderPane display;
    @FXML
    private ScrollPane scrDiaplay;
    @FXML
    private VBox vbLoader;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtNIC;
    @FXML
    private TextField txtCity;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtCustomerID;
    @FXML
    private TextField txtSupplierName;
    @FXML
    private JFXToggleButton tgMale;
    @FXML
    private JFXToggleButton tgFemale;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnMakePayment;

    private ArrayList<VBox> allCustomersVBoxs = new ArrayList<VBox>();

    private ArrayList<VBox> searchCustomerVBoxs = new ArrayList<VBox>();

    private ArrayList<CustomerDTO> allCustomerDTOs = new ArrayList<>();

    private static CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();

    private CustomerDTO customerDTO = new CustomerDTO();
    @FXML
    private JFXButton btnSkipToPayment;
    @FXML
    private JFXButton btnAddNewCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllCustomers();
        displayAllCustomers(allCustomerDTOs);
        txtSupplierName.textProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        txtCustomerID.textProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        txtCompanyName.textProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        txtNIC.textProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        txtCity.textProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        tgMale.selectedProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        tgFemale.selectedProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        btnClear.setOnAction(this);
        btnMakePayment.setOnAction(this);
        btnSearch.setOnAction(this);
        btnSkipToPayment.setOnAction(this);
        btnMakePayment.setVisible(false);

    }

    private VBox make(CustomerDTO customer) {
        Font lblFont = Font.font("TimesNewRoman", FontWeight.BOLD, FontPosture.REGULAR, 14);
        Color lblColor = Color.web("#d91e18");

        Pane hb1 = new Pane();
        Label cId = new Label("Supplier ID");
        cId.setTextFill(lblColor);
        cId.setFont(lblFont);
        cId.setLayoutX(10);
        Label lblCustomerId = new Label(customer.getCustomerId());
        lblCustomerId.setFont(lblFont);
        lblCustomerId.setLayoutX(140);
        hb1.getChildren().addAll(cId, lblCustomerId);

        Pane hb2 = new Pane();
        Label name = new Label("First Name");
        name.setLayoutX(10);
        Label lblFirstname = new Label(customer.getFirstname());
        lblFirstname.setLayoutX(140);
        Label name2 = new Label("Last Name");
        name2.setLayoutX(400);
        Label lblLastname = new Label(customer.getLastname());
        lblLastname.setLayoutX(490);
        name.setTextFill(lblColor);
        name.setFont(lblFont);
        name2.setTextFill(lblColor);
        name2.setFont(lblFont);
        lblFirstname.setFont(lblFont);
        lblLastname.setFont(lblFont);

        hb2.getChildren().addAll(name, lblFirstname, name2, lblLastname);

        Pane hb3 = new Pane();
        Label company = new Label("Company Name");
        company.setLayoutX(10);
        Label lblCompanyName = new Label(customer.getCompanyName());
        lblCompanyName.setLayoutX(140);
        company.setTextFill(lblColor);
        company.setFont(lblFont);
        lblCompanyName.setFont(lblFont);

        hb3.getChildren().addAll(company, lblCompanyName);

        Pane hb4 = new Pane();
        Label NIC = new Label("NIC");
        NIC.setLayoutX(10);
        Label lblNIC = new Label(customer.getNIC());
        lblNIC.setLayoutX(140);
        Label gender = new Label("Gender");
        gender.setLayoutX(400);
        Label lblGender = new Label(customer.getGender());
        lblGender.setLayoutX(490);
        NIC.setTextFill(lblColor);
        NIC.setFont(lblFont);
        gender.setTextFill(lblColor);
        gender.setFont(lblFont);
        lblNIC.setFont(lblFont);
        lblGender.setFont(lblFont);

        hb4.getChildren().addAll(NIC, lblNIC, gender, lblGender);

        Pane hb5 = new Pane();
        Label mobile = new Label("Mobile Number");
        mobile.setLayoutX(10);
        Label lblMobielNumber = new Label(String.valueOf(customer.getMobile()));
        lblMobielNumber.setId("lblMobileNumber");
        lblMobielNumber.setLayoutX(140);
        Label Email = new Label("Email");
        Email.setLayoutX(400);
        Label lblEmail = new Label(customer.getEmail());
        lblEmail.setLayoutX(490);
        mobile.setTextFill(lblColor);
        mobile.setFont(lblFont);
        Email.setTextFill(lblColor);
        Email.setFont(lblFont);
        lblMobielNumber.setFont(lblFont);
        lblEmail.setFont(lblFont);
        hb5.getChildren().addAll(mobile, lblMobielNumber, Email, lblEmail);

        Pane hb6 = new Pane();
        Label address = new Label("Address");
        address.setLayoutX(10);
        Label lblAddress = new Label(customer.getAddress());
        lblAddress.setLayoutX(140);
        address.setTextFill(lblColor);
        address.setFont(lblFont);
        lblAddress.setFont(lblFont);
        hb6.getChildren().addAll(address, lblAddress);

        Pane hb7 = new Pane();
        Label city = new Label("City");
        city.setLayoutX(10);
        Label lblCity = new Label(customer.getCity());
        lblCity.setLayoutX(140);
        Label postalCode = new Label("Postal Code");
        postalCode.setLayoutX(400);
        Label lblPostalCode = new Label(customer.getPostalCode());
        lblPostalCode.setLayoutX(490);
        city.setTextFill(lblColor);
        city.setFont(lblFont);
        postalCode.setTextFill(lblColor);
        postalCode.setFont(lblFont);
        lblCity.setFont(lblFont);
        lblPostalCode.setFont(lblFont);

        hb7.getChildren().addAll(city, lblCity, postalCode, lblPostalCode);

        VBox temp = new VBox(hb1, hb2, hb3, hb4, hb5, hb6, hb7);
        temp.setStyle("-fx-border-color: blue");
        temp.setStyle(" -fx-background-color: #8BC34A");
        temp.setSpacing(4);
        temp.setOnMouseClicked(e -> {
            customerDTO.setCustomerId(lblCustomerId.getText());
            btnMakePayment.setVisible(true);
            setSelectedSupplier(temp);
        });
        return temp;
    }

    private void setSelectedSupplier(VBox nowSelected) {
        for (VBox vBox : allCustomersVBoxs) {
            if (vBox == nowSelected) {
                nowSelected.setStyle("-fx-background-color:#9E9E9E");
            } else {
                vBox.setStyle("-fx-background-color:#8BC34A");
            }
        }
        for (VBox vBox : searchCustomerVBoxs) {
            if (vBox == nowSelected) {
                nowSelected.setStyle("-fx-background-color:#9E9E9E");
            } else {
                vBox.setStyle("-fx-background-color:#8BC34A");
            }
        }
    }

    private void loadAllCustomers() {
        try {
            CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMER);
            allCustomerDTOs = null;
            allCustomerDTOs = customerBO.getAllCustomers();
        } catch (Exception ex) {
            Logger.getLogger(CustomerDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayAllCustomers(ArrayList<CustomerDTO> allCustomerDTOs) {
        if (!allCustomerDTOs.isEmpty()) {
            vbLoader.getChildren().clear();
            for (CustomerDTO temp : allCustomerDTOs) {
                if (temp.getFirstname() != null) {
                    allCustomersVBoxs.add(make(temp));

                    vbLoader.getChildren().add(allCustomersVBoxs.get(allCustomersVBoxs.size() - 1));
                }

            }
        }
    }

    private void search() {
        CustomerDTO customerDTO = new CustomerDTO();

        try {
            if (!txtCustomerID.getText().isEmpty()) {
                customerDTO.setCustomerId("%" + txtCustomerID.getText() + "%");

            } else {
                customerDTO.setCustomerId("");
            }
            if (!txtSupplierName.getText().isEmpty()) {
                String[] name = txtSupplierName.getText().split("\\s");
                if (!name[0].isEmpty()) {
                    customerDTO.setFirstname(name[0] + "%");
                    String k = "";
                    for (int i = 1; i < name.length; i++) {
                        k = k.concat(name[i]);
                    }
                    if (!k.isEmpty()) {
                        customerDTO.setLastname(k + "%");
                    } else {
                        customerDTO.setLastname(name[0] + "%");
                    }
                }
            } else {
                customerDTO.setLastname("");
                customerDTO.setFirstname("");
            }
            if (!txtCompanyName.getText().isEmpty()) {
                customerDTO.setCompanyName(txtCompanyName.getText() + "%");
            } else {
                customerDTO.setCompanyName("");
            }

            if (!txtNIC.getText().isEmpty()) {
                customerDTO.setNIC(txtNIC.getText() + "%");
            } else {
                customerDTO.setNIC("");
            }

            if (!txtCity.getText().isEmpty()) {
                customerDTO.setCity(txtCity.getText() + "%");
            } else {
                customerDTO.setCity("");
            }

            if (tgMale.isSelected()) {
                customerDTO.setGender("Male");
            } else if (tgFemale.isSelected()) {
                customerDTO.setGender("Female");
            } else {
                customerDTO.setGender("");
            }
            CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMER);
            ArrayList<CustomerDTO> allsearchCustomerDTOs = customerBO.searchCustomer(customerDTO);
            allsearchCustomerDTOs = filterRegisteredCustomers(allsearchCustomerDTOs);
            displayAllSearchCustomers(allsearchCustomerDTOs);

        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayAllSearchCustomers(ArrayList<CustomerDTO> allSearchCustomersDTO) {
        for (VBox temp : allCustomersVBoxs) {
            vbLoader.getChildren().removeAll(temp);
        }
        for (VBox temp : searchCustomerVBoxs) {
            vbLoader.getChildren().removeAll(temp);
        }
        if (allSearchCustomersDTO.isEmpty() != true) {
            for (CustomerDTO supplierDTO : allSearchCustomersDTO) {
                searchCustomerVBoxs.add(make(supplierDTO));
                vbLoader.getChildren().add(searchCustomerVBoxs.get(searchCustomerVBoxs.size() - 1));
            }
        }
    }

    private void clear() {
        txtCustomerID.clear();
        txtCity.clear();
        txtCompanyName.clear();
        txtNIC.clear();
        txtSupplierName.clear();
        tgFemale.setSelected(false);
        tgMale.setSelected(false);
        loadAllCustomers();
        displayAllCustomers(allCustomerDTOs);
        btnMakePayment.setVisible(false);
        btnSkipToPayment.setVisible(true);

    }

    public static void setCustomerOrderTM(CustomerOrderDTO order) {
        customerOrderDTO = order;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnClear) {
            clear();
        }
        if (event.getSource() == btnSearch) {
            search();
        }
        if (event.getSource() == btnMakePayment) {
            customerOrderDTO.setCustomerId(customerDTO.getCustomerId());
            CustomerPaymentFormController.setOrderID(customerOrderDTO);
            setDisplay();
        }
        if (event.getSource() == btnSkipToPayment) {
            try {
                String cutomerId = new IDGenerator(IDGenerator.TableName.CUSTOMER).getNextID();
                customerDTO = null;
                customerDTO = new CustomerDTO();
                customerDTO.setCustomerId(cutomerId);
                customerDTO.setAddedDate(String.valueOf(LocalDate.now()));

                customerOrderDTO.setCustomerId(customerDTO.getCustomerId());

                CustomerPaymentFormController.setOrderID(customerOrderDTO);
                CustomerPaymentFormController.setCustomerId(customerDTO);
                setDisplay();

            } catch (Exception ex) {
                Logger.getLogger(CustomerDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setDisplay() {
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerPaymentForm.fxml"));
            display.setBackground(Background.EMPTY);
            display.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CustomerDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addNewCustomer(ActionEvent event) {
        try {
            BorderPane borderPane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerRegistrationForm.fxml"));
            Scene scene = new Scene(borderPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<CustomerDTO> filterRegisteredCustomers(ArrayList<CustomerDTO> allsearchCustomerDTOs) {
        ArrayList<CustomerDTO> filteredCustomerDTOs = new ArrayList();
        for (CustomerDTO customer : allsearchCustomerDTOs) {
            if (customer.getFirstname() != null) {
                filteredCustomerDTOs.add(customer);
            }
        }
        return filteredCustomerDTOs;
    }
}
