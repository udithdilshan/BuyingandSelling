/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.customer.CustomerOrderBO;
import lk.buyingandselling.BO.customer.CustomerPaymentBO;
import lk.buyingandselling.BO.supplier.PurchaseStockBO;
import lk.buyingandselling.BO.supplier.SupplierPaymentBO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;
import lk.buyingandselling.model.DTO.CustomerPaymentDTO;
import lk.buyingandselling.model.DTO.PurchaseStockDTO;
import lk.buyingandselling.model.DTO.SupplierPaymentDTO;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Integer> brNoOfSalles;
    @FXML
    private Label nodeDisplay;
    @FXML
    private LineChart<String, Double> lineChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpBarChart();
        setUpLineChart();
    }

    private void setUpBarChart() {
        try {
            XYChart.Series<String, Integer> purchaseStock = new XYChart.Series<>();
            XYChart.Series<String, Integer> customerOrder = new XYChart.Series<>();

            PurchaseStockBO purchaseStockBO = (PurchaseStockBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.PURCHASESTOCK);
            ArrayList<PurchaseStockDTO> purchaseStockDTOs = purchaseStockBO.getNoOfOrdersForDate();
            for (PurchaseStockDTO purchaseStockDTO : purchaseStockDTOs) {
                purchaseStock.getData()
                        .add(new XYChart.Data<>(purchaseStockDTO.getPurchaseDate(),
                                Integer.parseInt(purchaseStockDTO.getPurchaseId())));
            }
            purchaseStock.setName("Purchase Stock");
            brNoOfSalles.getData().add(purchaseStock);

            CustomerOrderBO customerOrderBO = (CustomerOrderBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMERORDER);
            ArrayList<CustomerOrderDTO> customerOrderDTOs = customerOrderBO.getNoOfOrders();
            for (CustomerOrderDTO customerOrderDTO : customerOrderDTOs) {
                customerOrder.getData()
                        .add(new XYChart.Data<>(customerOrderDTO.getOrderedDate(),
                                Integer.parseInt(customerOrderDTO.getCustomerId())));
            }
            customerOrder.setName("Customer Order");
            brNoOfSalles.getData().add(customerOrder);
            System.out.println(brNoOfSalles.getMaxHeight());
        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setUpLineChart() {
        try {
            XYChart.Series<String, Double> supplierPayment = new XYChart.Series<>();
            XYChart.Series<String, Double> customerPayment = new XYChart.Series<>();
            
            SupplierPaymentBO supplierPaymentBO = (SupplierPaymentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.SUPPLIERPAYMENT);
            ArrayList<SupplierPaymentDTO> supplierPaymentDTOs = supplierPaymentBO.daySpend();
            for (SupplierPaymentDTO supplierPaymentDTO : supplierPaymentDTOs) {
                supplierPayment.getData()
                        .add(new XYChart.Data<>(supplierPaymentDTO.getPaidDate(),
                                supplierPaymentDTO.getPaidAmount()));
            }
            supplierPayment.setName("Paid");
            lineChart.getData().add(supplierPayment);
            
            CustomerPaymentBO customerPaymentBO = (CustomerPaymentBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMERPAYMENT);
            ArrayList<CustomerPaymentDTO> customerPaymentDTOs = customerPaymentBO.dayIncome();
            for (CustomerPaymentDTO customerPaymentDTO : customerPaymentDTOs) {
                customerPayment.getData()
                        .add(new XYChart.Data<>(customerPaymentDTO.getPaidDate(),
                                customerPaymentDTO.getAmount()));
            }
            customerPayment.setName("Income");
            lineChart.getData().add(customerPayment);
        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
