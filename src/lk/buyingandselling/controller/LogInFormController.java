/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import static com.sun.javafx.application.PlatformImpl.exit;
import lk.buyingandselling.main.BuyingandSelling;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 *
 * @author SLR
 */
public class LogInFormController implements Initializable {
    
    BuyingandSelling s=new BuyingandSelling();
    
    
    @FXML
    private Button btnSignIn;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button btnExit;
    @FXML
    private void SignIn(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/UserDashbord.fxml"));
        rootpane.setBackground(Background.EMPTY);
        rootpane.getChildren().setAll(pane); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    public void EndClose(ActionEvent event){
            exit();
    }
}
