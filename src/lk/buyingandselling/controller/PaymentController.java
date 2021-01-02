/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class PaymentController implements Initializable, EventHandler<ActionEvent> {
    
    @FXML
    private Button btnSupplier;
    @FXML
    private BorderPane display;
    @FXML
    private Button btnCustomerPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnCustomerPayment.setOnAction(this);
        btnSupplier.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        try {

            if (event.getSource() == btnCustomerPayment) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerPaymentForm.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
            }
            if (event.getSource() == btnSupplier) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/SupplierPaymentForm.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
            }

        } catch (IOException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
