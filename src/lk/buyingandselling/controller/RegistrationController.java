
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
public class RegistrationController implements Initializable,EventHandler<ActionEvent> {

    @FXML
    private BorderPane display;
    @FXML
    private Button btnCustomer;
    @FXML
    private Button btnSupplier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCustomer.setOnAction(this);
        btnSupplier.setOnAction(this);
    }    
    public void handle(ActionEvent event) {
        try {
            if (event.getSource() == btnCustomer) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerDetailsForm.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
            } else if (event.getSource() == btnSupplier) {
                BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/SupplierDetailsForm.fxml"));
                display.setBackground(Background.EMPTY);
                display.getChildren().setAll(pane);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
