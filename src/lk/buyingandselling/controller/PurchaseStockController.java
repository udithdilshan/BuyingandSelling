package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import lk.buyingandselling.BO.supplier.SupplierBO;
import lk.buyingandselling.model.DTO.SupplierDTO;

public class PurchaseStockController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private BorderPane display;
    @FXML
    private ScrollPane scrDiaplay;
    @FXML
    private VBox vbLoader;

    private ArrayList<SupplierDTO> allSuppliersDTO;

    private ArrayList<VBox> allSuppliersVBoxs = new ArrayList<VBox>();

    private SupplierDTO supplierDTO = new SupplierDTO();

    private ArrayList<VBox> searchSuppliersVBoxs = new ArrayList<VBox>();

    @FXML
    private Button btnNext;
    @FXML
    private TextField txtSupplierID;
    @FXML
    private TextField txtSupplierName;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtNIC;
    @FXML
    private TextField txtCity;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAddNewSupplier;
    @FXML
    private JFXToggleButton tgMale;
    @FXML
    private JFXToggleButton tgFemale;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        supplierDetails();
        setToggleGroup();
        displayAllSuppliers(allSuppliersDTO);
        btnNext.setOnAction(this);
        btnSearch.setOnAction(this);
        btnCancel.setOnAction(this);
        txtSupplierName.textProperty().addListener((observer, oldValue, newValue) -> {
            search();
        });
        txtSupplierID.textProperty().addListener((observer, oldValue, newValue) -> {
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
            if (tgMale.isSelected()) {
                search();
            }

        });
        tgFemale.selectedProperty().addListener((observer, oldValue, newValue) -> {
            if (tgFemale.isSelected()) {
                search();
            }

        });
    }

    public void setToggleGroup() {
        ToggleGroup tgGender = new ToggleGroup();
        tgMale.setToggleGroup(tgGender);
        tgFemale.setToggleGroup(tgGender);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnSearch) {
            search();
        }
        if (event.getSource() == btnCancel) {
            vbLoader.getChildren().clear();
            clear();
        }
        try {
            if (supplierDTO.getSupplierId() != null && event.getSource() == btnNext) {
                PurchaseStockFormController.setSupplierID(supplierDTO.getSupplierId());
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/PurchaseStockForm.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
            } else if (event.getSource() == btnNext) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Selet Supplier");
                alert.setHeaderText(null);
                alert.setContentText("Please selecet a supplier");
                alert.showAndWait();
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    private void supplierDetails() {
        try {
            SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIER);
            allSuppliersDTO = supplierBO.getAllSuppliers();
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void displayAllSuppliers(ArrayList<SupplierDTO> allSuppliersDTO) {
        if (allSuppliersDTO.isEmpty() != true) {
            vbLoader.getChildren().clear();

            for (SupplierDTO supplierDTO : allSuppliersDTO) {
                allSuppliersVBoxs.add(make(supplierDTO));
                vbLoader.getChildren().add(allSuppliersVBoxs.get(allSuppliersVBoxs.size() - 1));
            }
        }

    }

    private VBox make(SupplierDTO supplier) {
        Font lblFont = Font.font("TimesNewRoman", FontWeight.BOLD, FontPosture.REGULAR, 14);
        Color lblColor = Color.web("#d91e18");

        Pane hb1 = new Pane();
        Label sId = new Label("Supplier ID");
        sId.setTextFill(lblColor);
        sId.setFont(lblFont);
        sId.setLayoutX(10);
        Label lblSupplierID = new Label(supplier.getSupplierId());
        lblSupplierID.setFont(lblFont);
        lblSupplierID.setLayoutX(140);
        hb1.getChildren().addAll(sId, lblSupplierID);

        Pane hb2 = new Pane();
        Label name = new Label("First Name");
        name.setLayoutX(10);
        Label lblFirstname = new Label(supplier.getFirstname());
        lblFirstname.setLayoutX(140);
        Label name2 = new Label("Last Name");
        name2.setLayoutX(400);
        Label lblLastname = new Label(supplier.getLastname());
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
        Label lblCompanyName = new Label(supplier.getCompanyName());
        lblCompanyName.setLayoutX(140);
        company.setTextFill(lblColor);
        company.setFont(lblFont);
        lblCompanyName.setFont(lblFont);

        hb3.getChildren().addAll(company, lblCompanyName);

        Pane hb4 = new Pane();
        Label NIC = new Label("NIC");
        NIC.setLayoutX(10);
        Label lblNIC = new Label(supplier.getNIC());
        lblNIC.setLayoutX(140);
        Label gender = new Label("Gender");
        gender.setLayoutX(400);
        Label lblGender = new Label(supplier.getGender());
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
        Label lblMobielNumber = new Label(String.valueOf(supplier.getMobileNumber()));
        lblMobielNumber.setId("lblMobileNumber");
        lblMobielNumber.setLayoutX(140);
        Label Email = new Label("Email");
        Email.setLayoutX(400);
        Label lblEmail = new Label(supplier.getEmail());
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
        Label lblAddress = new Label(supplier.getAddress());
        lblAddress.setLayoutX(140);
        address.setTextFill(lblColor);
        address.setFont(lblFont);
        lblAddress.setFont(lblFont);
        hb6.getChildren().addAll(address, lblAddress);

        Pane hb7 = new Pane();
        Label city = new Label("City");
        city.setLayoutX(10);
        Label lblCity = new Label(supplier.getCity());
        lblCity.setLayoutX(140);
        Label postalCode = new Label("Postal Code");
        postalCode.setLayoutX(400);
        Label lblPostalCode = new Label(supplier.getPostalCode());
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
            supplierDTO.setSupplierId(lblSupplierID.getText());
            setSelectedSupplier(temp);
        });
        return temp;
    }

    private void setSelectedSupplier(VBox nowSelected) {
        for (VBox vBox : allSuppliersVBoxs) {
            if (vBox == nowSelected) {
                nowSelected.setStyle("-fx-background-color:#9E9E9E");
            } else {
                vBox.setStyle("-fx-background-color:#8BC34A");
            }
        }
        for (VBox vBox : searchSuppliersVBoxs) {
            if (vBox == nowSelected) {
                nowSelected.setStyle("-fx-background-color:#9E9E9E");
            } else {
                vBox.setStyle("-fx-background-color:#8BC34A");
            }
        }
    }

    private void search() {
        SupplierDTO supplierDTO = new SupplierDTO();

        try {
            if (!txtSupplierID.getText().isEmpty()) {
                supplierDTO.setSupplierId("%" + txtSupplierID.getText() + "%");

            } else {
                supplierDTO.setSupplierId("");
            }
            if (!txtSupplierName.getText().isEmpty()) {
                String[] name = txtSupplierName.getText().split("\\s");
                if (!name[0].isEmpty()) {
                    supplierDTO.setFirstname(name[0] + "%");
                    String k = "";
                    for (int i = 1; i < name.length; i++) {
                        k = k.concat(name[i]);
                    }
                    if (!k.isEmpty()) {
                        supplierDTO.setLastname(k + "%");
                    } else {
                        supplierDTO.setLastname(name[0] + "%");
                    }
                }
            } else {
                supplierDTO.setLastname("");
                supplierDTO.setFirstname("");
            }
            if (!txtCompanyName.getText().isEmpty()) {
                supplierDTO.setCompanyName(txtCompanyName.getText() + "%");
            } else {
                supplierDTO.setCompanyName("");
            }

            if (!txtNIC.getText().isEmpty()) {
                supplierDTO.setNIC(txtNIC.getText() + "%");
            } else {
                supplierDTO.setNIC("");
            }

            if (!txtCity.getText().isEmpty()) {
                supplierDTO.setCity(txtCity.getText() + "%");
            } else {
                supplierDTO.setCity("");
            }

            if (tgMale.isSelected()) {
                supplierDTO.setGender("Male");
            } else if (tgFemale.isSelected()) {
                supplierDTO.setGender("Female");
            } else {
                supplierDTO.setGender("");
            }
            SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIER);
            ArrayList<SupplierDTO> allSearchSuppliersDTO = supplierBO.searchSupplier(supplierDTO);
            displayAllSearchSuppliers(allSearchSuppliersDTO);

        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayAllSearchSuppliers(ArrayList<SupplierDTO> allSearchSuppliersDTO) {
        for (VBox temp : allSuppliersVBoxs) {
            vbLoader.getChildren().removeAll(temp);
        }
        for (VBox temp : searchSuppliersVBoxs) {
            vbLoader.getChildren().removeAll(temp);
        }
        if (allSearchSuppliersDTO.isEmpty() != true) {
            for (SupplierDTO supplierDTO : allSearchSuppliersDTO) {
                searchSuppliersVBoxs.add(make(supplierDTO));
                vbLoader.getChildren().add(searchSuppliersVBoxs.get(searchSuppliersVBoxs.size() - 1));
            }
        }
    }

    private void clear() {
        txtSupplierID.clear();
        txtCity.clear();
        txtCompanyName.clear();
        txtNIC.clear();
        txtSupplierName.clear();
        tgFemale.setSelected(false);
        tgMale.setSelected(false);
        supplierDetails();
        displayAllSuppliers(allSuppliersDTO);
    }

    @FXML
    private void addNewSupplier(ActionEvent event) {
        try {
            BorderPane borderPane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/SupplierRegistrationForm.fxml"));
            Scene scene = new Scene(borderPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(PurchaseStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
