/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.supplier.LocationBO;
import lk.buyingandselling.model.DTO.LocationDTO;
import lk.buyingandselling.model.TM.LocationTM;
import lk.buyingandselling.util.IDGenerator;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class AddLocationFormController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private ComboBox<String> cmbSectionName;
    @FXML
    private Button btnAddLocation;
    @FXML
    private Button btnRemoveLocation;
    @FXML
    private TextField txtLocationID;
    @FXML
    private TextField txtRacNo;
    @FXML
    private TableView<LocationTM> tblLocationDetails;
    @FXML
    private TableColumn<LocationTM, String> sectionName;
    @FXML
    private TableColumn<LocationTM, String> locationID;
    @FXML
    private TableColumn<LocationTM, String> rackNo;
    @FXML
    private Button btnAddNewSection;
    @FXML
    private Button btnEditLocation;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCancel.setVisible(false);
        loadNextLocationID();
        loadSectionNames();
        loadAllLocations();
        btnAddLocation.setOnAction(this);
        btnAddNewSection.setOnAction(this);
        btnRemoveLocation.setOnAction(this);
        btnEditLocation.setOnAction(this);
        btnCancel.setOnAction(this);
    }

    private void loadAllLocations() {
        try {
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            ArrayList<LocationDTO> allLocationDTO = locationBO.getAllLocation();
            addLocationTabel(allLocationDTO);
        } catch (Exception ex) {
            Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addLocationTabel(ArrayList<LocationDTO> allLocationDTO) {
        ArrayList<LocationTM> allLocationTMs = makeLocationTable(allLocationDTO);
        tblLocationDetails.setItems(FXCollections.observableArrayList(allLocationTMs));
        locationID.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        sectionName.setCellValueFactory(new PropertyValueFactory<>("SectionName"));
        rackNo.setCellValueFactory(new PropertyValueFactory<>("rackNo"));
    }

    private void addLoctionsToTable() {
        LocationTM temp = new LocationTM(
                txtLocationID.getText(),
                txtRacNo.getText(),
                cmbSectionName.getSelectionModel().getSelectedItem()
        );

        tblLocationDetails.getItems().add(temp);
    }

    private void loadNextLocationID() {
        try {
            txtLocationID.setText(new IDGenerator(IDGenerator.TableName.LOCATION).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSectionNames() {
        try {
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            ArrayList<LocationDTO> allSectionsDTOs = locationBO.getAllSections();
            for (LocationDTO section : allSectionsDTOs) {
                cmbSectionName.getItems().add(section.getSectionName());
            }
        } catch (Exception ex) {
            Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAddLocation && btnAddLocation.getText().compareToIgnoreCase("add location") == 0) {
            try {
                LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
                LocationDTO locationDTO = new LocationDTO(
                        txtLocationID.getText(),
                        txtRacNo.getText(),
                        cmbSectionName.getSelectionModel().getSelectedItem()
                );
                if (locationBO.addLocation(locationDTO)) {
                    addLoctionsToTable();
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Location");
                    alert.setHeaderText(null);
                    alert.setContentText("Location Added successfully");
                    alert.showAndWait();
                    clear();
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Faild");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Check the Values");
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnAddLocation && btnAddLocation.getText().compareToIgnoreCase("save location") == 0) {
            try {
                LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
                LocationDTO locationDTO = new LocationDTO(txtLocationID.getText(),
                        txtRacNo.getText(),
                        cmbSectionName.getSelectionModel().getSelectedItem());
                boolean status = locationBO.updateLocation(locationDTO);
                if (status) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Location");
                    alert.setHeaderText(null);
                    alert.setContentText("Location Saved successfully");
                    alert.showAndWait();
                    btnAddLocation.setText("Add Location");
                    clear();
                    tblLocationDetails.getItems().clear();
                    loadAllLocations();
                    btnCancel.setVisible(false);
                }
            } catch (Exception ex) {
                Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnEditLocation) {
            if (tblLocationDetails.getSelectionModel().getSelectedItem() != null) {
                LocationTM selectedItem = tblLocationDetails.getSelectionModel().getSelectedItem();
                cmbSectionName.getSelectionModel().select(selectedItem.getSectionName());
                txtLocationID.setText(selectedItem.getLocationId());
                txtRacNo.setText(selectedItem.getRackNo());
                btnAddLocation.setText("Save Location");
                btnCancel.setVisible(true);
            }
        }
        if (event.getSource() == btnAddNewSection) {
            try {
                String newSection = getNewSection();
                LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
                if (newSection.isEmpty() != true && !locationBO.addNewSection(setAllSection(newSection))) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Location");
                    alert.setHeaderText(null);
                    alert.setContentText("New Section Added successfully");
                    alert.showAndWait();
                    cmbSectionName.getItems().clear();
                    loadSectionNames();
                }

            } catch (Exception ex) {
                Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnRemoveLocation) {
            try {
                LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);

                if (tblLocationDetails.getSelectionModel().getSelectedItem() != null) {
                    LocationTM selectedLocationTM = tblLocationDetails.getSelectionModel().getSelectedItem();
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Delete Location");
                    alert.setHeaderText("Are you sure ?");
                    ButtonType btnYes = new ButtonType("Yes", ButtonData.YES);
                    ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(btnYes, btnCancel);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == btnYes) {
                        boolean status = locationBO.deleteLocation(selectedLocationTM.getLocationId());
                        if (status) {
                            tblLocationDetails.getItems().clear();
                            loadAllLocations();
                            loadNextLocationID();
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnCancel) {
            clear();
            btnAddLocation.setText("Add Location");
            btnCancel.setVisible(false);
        }

    }

    private ArrayList<LocationTM> makeLocationTable(ArrayList<LocationDTO> allLocationDTO) {
        ArrayList<LocationTM> allLocationTMs = new ArrayList();
        for (LocationDTO location : allLocationDTO) {
            allLocationTMs.add(new LocationTM(
                    location.getLocationId(),
                    location.getRackNo(),
                    location.getSectionName()
            ));
        }
        return allLocationTMs;
    }

    private void clear() {
        txtRacNo.clear();
        cmbSectionName.getSelectionModel().select(null);
        loadNextLocationID();
    }

    private String getNewSection() {
        TextInputDialog newSection = new TextInputDialog();
        newSection.setTitle("Add New Section");
        newSection.setHeaderText("Enter New Section Name");
        Optional<String> sectionName = newSection.showAndWait();
        if (sectionName.isPresent()) {
            return sectionName.get();
        }
        return "";
    }

    private LocationDTO setAllSection(String newSection) {
        LocationDTO temp = new LocationDTO();
        String allSections = "";
        newSection = "'" + newSection + "'";
        for (int i = 0; i < cmbSectionName.getItems().size(); i++) {
            allSections = allSections.concat("'" + cmbSectionName.getItems().get(i) + "',");
        }
        allSections += newSection;
        temp.setSectionName(allSections);
        return temp;

    }

}
