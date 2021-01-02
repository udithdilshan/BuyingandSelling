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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.supplier.ItemBO;
import lk.buyingandselling.BO.supplier.LocationBO;
import lk.buyingandselling.model.DTO.ItemDTO;
import lk.buyingandselling.model.DTO.LocationDTO;
import lk.buyingandselling.model.TM.ItemTM;
import lk.buyingandselling.util.IDGenerator;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class AddItemFormController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private Button btnAddNewCategory;
    @FXML
    private ComboBox<String> cmbSelectCategory;
    @FXML
    private Button btnAddNewLocation;
    @FXML
    private ComboBox<String> cmbSectionName;
    @FXML
    private ComboBox<String> cmbLocationID;
    @FXML
    private Button btnAddItem;
    @FXML
    private TableView<ItemTM> tblItems;
    @FXML
    private TableColumn<ItemTM, String> itemCode;
    @FXML
    private TableColumn<ItemTM, String> description;
    @FXML
    private TableColumn<ItemTM, String> category;
    @FXML
    private TableColumn<ItemTM, String> sectionName;
    @FXML
    private TableColumn<ItemTM, String> locationID;
    @FXML
    private TableColumn<ItemTM, String> rackNo;
    @FXML
    private Button btnRemoveItem;
    @FXML
    private Button btnEditItem;
    @FXML
    private TextField txtItemCode;
    @FXML
    private TextField txtItemDescription;
    @FXML
    private ComboBox<String> cmbRackNo;
    @FXML
    private Button btnCancel;
    @FXML
    private BorderPane display;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCancel.setVisible(false);
        loadAllItems();
        loadAllLocations();
        loadAllCategorys();
        loadAllSections();
        loadNextItemCode();
        btnAddItem.setOnAction(this);
        btnAddNewCategory.setOnAction(this);
        btnRemoveItem.setOnAction(this);
        btnEditItem.setOnAction(this);
        btnCancel.setOnAction(this);
        btnAddNewLocation.setOnAction(this);
        cmbSectionName.valueProperty().addListener((observerble, oldValue, newValue) -> {
            setForSelection(newValue);
        });
        cmbLocationID.valueProperty().addListener((observerble, oldValue, newValue) -> {
            setForLocation(newValue);
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnAddNewCategory) {
            try {
                String newCategory = getNewCategory();
                ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
                if (newCategory.isEmpty() != true && !itemBO.addNewCategory(setAllCategory(newCategory))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Category");
                    alert.setHeaderText(null);
                    alert.setContentText("New Category Added successfully");
                    alert.showAndWait();
                    cmbSelectCategory.getItems().clear();
                    loadAllCategorys();
                }
            } catch (Exception ex) {
                Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnAddItem && btnAddItem.getText().compareToIgnoreCase("Add Item") == 0) {
            try {
                ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
                if (itemBO.addItem(makeItemDTO())) {
                    loadAllItems();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Location");
                    alert.setHeaderText(null);
                    alert.setContentText("Location Added successfully");
                    alert.showAndWait();
                    clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Faild");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Check the Values");
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnRemoveItem) {
            ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);

            if (tblItems.getSelectionModel().getSelectedItem() != null) {
                ItemTM itemTM = tblItems.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Location");
                alert.setHeaderText("Are you sure ?");
                ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(btnYes, btnCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == btnYes) {
                    try {
                        boolean status = itemBO.deleteItem(itemTM.getItemCode());
                        if (status) {
                            tblItems.getItems().clear();
                            loadAllItems();
                            loadNextItemCode();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (event.getSource() == btnEditItem) {
            if (tblItems.getSelectionModel().getSelectedItem() != null) {
                ItemTM itemTM = tblItems.getSelectionModel().getSelectedItem();
                cmbSelectCategory.getSelectionModel().select(itemTM.getCategory());
                cmbLocationID.getSelectionModel().select(itemTM.getLocationId());
                cmbSectionName.getSelectionModel().select(itemTM.getSectionName());
                cmbRackNo.getSelectionModel().select(itemTM.getRackNo());
                txtItemCode.setText(itemTM.getItemCode());
                txtItemDescription.setText(itemTM.getDescription());
                btnCancel.setVisible(true);
                btnAddItem.setText("Save Item");
            }
        }
        if (event.getSource() == btnAddItem && btnAddItem.getText().compareToIgnoreCase("save Item") == 0) {
            try {
                ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
                ItemDTO itemDTO = new ItemDTO(
                        txtItemCode.getText(),
                        cmbLocationID.getSelectionModel().getSelectedItem(),
                        txtItemDescription.getText(),
                        cmbSelectCategory.getSelectionModel().getSelectedItem()
                );
                boolean status = itemBO.updateItem(itemDTO);
                if (status) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Item");
                    alert.setHeaderText(null);
                    alert.setContentText("Item Saved successfully");
                    alert.showAndWait();
                    btnAddItem.setText("Add Location");
                    clear();
                    tblItems.getItems().clear();
                    loadAllItems();
                    btnCancel.setVisible(false);
                }
            } catch (Exception ex) {
                Logger.getLogger(AddLocationFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (event.getSource() == btnCancel) {
            clear();
            btnAddItem.setText("Add Item");
            btnCancel.setVisible(false);
        }
        if (event.getSource() == btnAddNewLocation) {
            getNewLocation();
            loadAllLocations();
        }
    }

    private void loadAllItems() {
        try {
            ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            addItemTable(allItems);
        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllLocations() {
        try {
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            ArrayList<LocationDTO> locationDTOs = locationBO.getAllLocation();
            for (LocationDTO location : locationDTOs) {
                cmbLocationID.getItems().add(location.getLocationId());
                cmbRackNo.getItems().add(location.getRackNo());
            }
        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllCategorys() {
        try {
            ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.ITEM);
            ArrayList<ItemDTO> allCategorys = itemBO.getAllCategory();
            for (ItemDTO temp : allCategorys) {
                cmbSelectCategory.getItems().add(temp.getCategory());
            }
        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addItemTable(ArrayList<ItemDTO> allItems) {
        ArrayList<ItemTM> allItemTMs = new ArrayList<>();
        for (ItemDTO item : allItems) {
            allItemTMs.add(makeItemTM(item));
        }
        tblItems.setItems(FXCollections.observableArrayList(allItemTMs));
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        sectionName.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
        locationID.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        rackNo.setCellValueFactory(new PropertyValueFactory<>("rackNo"));

    }

    private ItemTM makeItemTM(ItemDTO itemDTO) {
        ItemTM itemTM = null;
        try {
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            LocationDTO locationTemp = new LocationDTO();
            locationTemp.setLocationId(itemDTO.getLocationId());
            ArrayList<LocationDTO> locationDTO = locationBO.searchLocation(locationTemp);
            String sectionName = locationDTO.get(0).getSectionName();
            String rackNo = locationDTO.get(0).getRackNo();
            itemTM = new ItemTM(
                    itemDTO.getItemCode(),
                    itemDTO.getDescription(),
                    itemDTO.getCategory(),
                    sectionName,
                    itemDTO.getLocationId(),
                    rackNo
            );

        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemTM;
    }

    private void loadAllSections() {
        try {
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            ArrayList<LocationDTO> allSections = locationBO.getAllSections();
            for (LocationDTO temp : allSections) {
                cmbSectionName.getItems().add(temp.getSectionName());
            }
        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setForSelection(String newValue) {
        try {
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            LocationDTO locationDTO = new LocationDTO();
            cmbLocationID.getItems().clear();
            cmbRackNo.getItems().clear();
            locationDTO.setSectionName(newValue);
            ArrayList<LocationDTO> locationDTOs = locationBO.searchLocation(locationDTO);

            for (LocationDTO temp : locationDTOs) {
                cmbLocationID.getItems().add(temp.getLocationId());
            }
            cmbLocationID.getSelectionModel().select(0);
        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getNewCategory() {
        TextInputDialog newCategory = new TextInputDialog();
        newCategory.setTitle("Add New Category");
        newCategory.setHeaderText("Enter New Category Name");
        Optional<String> categoryName = newCategory.showAndWait();
        if (categoryName.isPresent()) {
            return categoryName.get();
        }
        return "";
    }

    private ItemDTO setAllCategory(String newCategory) {
        ItemDTO temp = new ItemDTO();
        String allCategory = "";
        for (int i = 0; i < cmbSelectCategory.getItems().size(); i++) {
            allCategory = allCategory.concat("'" + cmbSelectCategory.getItems().get(i) + "',");
        }
        allCategory = allCategory + "'" + newCategory + "'";
        temp.setCategory(allCategory);
        return temp;
    }

    private ItemDTO makeItemDTO() {
        return new ItemDTO(
                txtItemCode.getText(),
                cmbLocationID.getSelectionModel().getSelectedItem(),
                txtItemDescription.getText(),
                cmbSelectCategory.getSelectionModel().getSelectedItem()
        );
    }

    private void clear() {
        loadNextItemCode();
        txtItemDescription.clear();
    }

    private void loadNextItemCode() {
        try {
            txtItemCode.setText(new IDGenerator(IDGenerator.TableName.ITEM).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getNewLocation() {
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/AddLocationForm.fxml"));
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            loadAllLocations();
        } catch (IOException ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setForLocation(String newValue) {
        try {
            LocationBO locationBO = (LocationBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.LOCATION);
            LocationDTO locationDTO = new LocationDTO();
            cmbRackNo.getItems().clear();
            locationDTO.setLocationId(newValue);
            ArrayList<LocationDTO> locationDTOs = locationBO.searchLocation(locationDTO);

            for (LocationDTO temp : locationDTOs) {
                cmbRackNo.getItems().add(temp.getRackNo());
            }
            cmbRackNo.getSelectionModel().select(0);
        } catch (Exception ex) {
            Logger.getLogger(AddItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
