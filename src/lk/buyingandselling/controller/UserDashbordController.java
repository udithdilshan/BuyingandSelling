package lk.buyingandselling.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class UserDashbordController implements Initializable, EventHandler<ActionEvent> {
    
    @FXML
    private Button btndashboard;
    @FXML
    private Button btnRegistration;
    @FXML
    private Button btnCustomerOrder;
    @FXML
    private Button btnPurchaseStock;
    @FXML
    private Button btnInventory;
    @FXML
    private Button btnPayment;
    @FXML
    private Button btnReport;
    @FXML
    private BorderPane main;
    @FXML
    private BorderPane display;

    ArrayList<Button> btnList = new ArrayList<>();


//    private ObservableList
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            btnList.add(btndashboard);
            btnList.add(btnRegistration);
            btnList.add(btnCustomerOrder);
            btnList.add(btnPurchaseStock);
            btnList.add(btnInventory);
            btnList.add(btnPayment);
            btnList.add(btnReport);
            
            btndashboard.setOnAction(this);
            btnRegistration.setOnAction(this);
            btnCustomerOrder.setOnAction(this);
            btnPurchaseStock.setOnAction(this);
            btnInventory.setOnAction(this);
            btnPayment.setOnAction(this);
            btnReport.setOnAction(this);
            
            BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/Dashboard.fxml"));
            display.setBackground(Background.EMPTY);
            display.getChildren().setAll(pane);
            btnSelected(btndashboard);
        } catch (IOException ex) {
            Logger.getLogger(UserDashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            if (event.getSource() == btnRegistration) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/Registration.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
                btnSelected(btnRegistration);
            } else if (event.getSource() == btnCustomerOrder) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerOrderForm.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
                btnSelected(btnCustomerOrder);
            } else if (event.getSource() == btnPurchaseStock) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/PurchaseStock.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
                btnSelected(btnPurchaseStock);
            } else if (event.getSource() == btnPayment) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/Payment.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
                btnSelected(btnPayment);
            } else if (event.getSource() == btnInventory) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/Inventory.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
                btnSelected(btnInventory);
            }else if (event.getSource()==btndashboard) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/Dashboard.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
                btnSelected(btndashboard);
            }else if (event.getSource()==btnReport) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/Report.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
                btnSelected(btnReport);
            }
        } catch (IOException ex) {
            Logger.getLogger(UserDashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnSelected(Button btnSelected) {

        for (Button button : btnList) {

            if (button == btnSelected) {
                btnSelected.setStyle("-fx-background-color:#00A2D3");
            } else {
                button.setStyle("-fx-background-color: transparent");
            }

        }
    }
    
    private void initbrNoOfSalles(){
//        XYChart.Series set=new XYChart.Series<>(data)
    }
    
    
}
