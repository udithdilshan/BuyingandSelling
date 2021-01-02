/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import lk.buyingandselling.DBConnection.DBConnection;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class ReportController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private JFXButton btnRegisteredCustomerDetails;

    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRegisteredCustomerDetails.setOnAction(this);
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handle(ActionEvent event) {
//        if (event.getSource() == btnRegisteredCustomerDetails) {
//            try {
//                InputStream inputStream = getClass().getResourceAsStream("/lk/buyingandselling/Report/customer.jasper");
//                HashMap hashMap = new HashMap();
////                JasperReport jasperReport=JasperCompileManager.compileReport(inputStream);
//                @SuppressWarnings("unchecked")
////                JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
//
//                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, hashMap, connection);
//                JasperViewer.viewReport(jasperPrint);
////                hashMap.put("date", String.valueOf(LocalDate.now()));
//
//            } catch (JRException ex) {
//                Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

}
