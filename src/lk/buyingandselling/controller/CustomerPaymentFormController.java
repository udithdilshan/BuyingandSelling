/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import lk.buyingandselling.BO.BOFactory;
import lk.buyingandselling.BO.customer.CustomerBO;
import lk.buyingandselling.BO.customer.CustomerOrderFillingBO;
import lk.buyingandselling.BO.customer.CustomerPaymentFillingBO;
import lk.buyingandselling.model.DTO.BatchDetailDTO;
import lk.buyingandselling.model.DTO.CardDTO;
import lk.buyingandselling.model.DTO.CustomerDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDetailDTO;
import lk.buyingandselling.model.DTO.CustomerPaymentDTO;
import lk.buyingandselling.model.DTO.RecivedChequeDTO;
import lk.buyingandselling.model.TM.CustomerOrderTM;
import lk.buyingandselling.util.IDGenerator;

/**
 * FXML Controller class
 *
 * @author SLR
 */
public class CustomerPaymentFormController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private JFXButton btnPayOldOrder;
    @FXML
    private TextField txtPaymentID;
    @FXML
    private TextField txtOrderID;
    @FXML
    private TextField txtCustomerID;
    @FXML
    private JFXDatePicker dtOrderedDate;
    @FXML
    private JFXButton btnSubmitPayment;
    @FXML
    private TableView<CustomerOrderTM> tblOrderDetail;
    @FXML
    private TableColumn<CustomerOrderTM, String> itemCode;
    @FXML
    private TableColumn<CustomerOrderTM, String> batchNo;
    @FXML
    private TableColumn<CustomerOrderTM, String> description;
    @FXML
    private TableColumn<CustomerOrderTM, String> EXP;
    @FXML
    private TableColumn<CustomerOrderTM, String> MFD;
    @FXML
    private TableColumn<CustomerOrderTM, String> sectionName;
    @FXML
    private TableColumn<CustomerOrderTM, String> locationID;
    @FXML
    private TableColumn<CustomerOrderTM, String> RackNo;
    @FXML
    private TableColumn<CustomerOrderTM, Double> UnitPrice;
    @FXML
    private TableColumn<CustomerOrderTM, Double> QTY;
    @FXML
    private TableColumn<CustomerOrderTM, Double> total;
    @FXML
    private JFXToggleButton tgCash;
    @FXML
    private JFXToggleButton tgCard;
    @FXML
    private JFXToggleButton tgCheque;
    @FXML
    private Label lblNetTotal;
    @FXML
    private Tab tabCash;
    @FXML
    private JFXTextField txtAmountCash;
    @FXML
    private Label lblBalance;
    @FXML
    private Tab tabDebitCard;
    @FXML
    private JFXToggleButton tgVisaCard;
    @FXML
    private JFXToggleButton tgMasterCard;
    @FXML
    private JFXToggleButton tgAmericanExpress;
    @FXML
    private JFXTextField txtCardNo;
    @FXML
    private JFXTextField txtValidDate;
    @FXML
    private JFXTextField txtCVV;
    @FXML
    private JFXTextField txtAmountCard;
    @FXML
    private Tab tabCheque;
    @FXML
    private JFXTextField txtChequeNo;
    @FXML
    private JFXTextField txtAmountCheque;
    @FXML
    private JFXTextField txtBankName;
    @FXML
    private JFXTextField txtStatus;

    private final ToggleGroup tgPaymentMethod = new ToggleGroup();
    @FXML
    private TabPane tabPane;

    private static ObservableList<CustomerOrderTM> orderDetails = FXCollections.observableArrayList();

    private static CustomerOrderDTO customerOrderDTO=null;

    private static CustomerDTO customerDTO = null;

    @FXML
    private JFXDatePicker dtPaidDate;
    @FXML
    private JFXDatePicker dtRealization;
    @FXML
    private BorderPane display;
    @FXML
    private JFXButton btnSkipPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getNextPaymentID();
        setPaymentMethodToggle();
        tgStateChange();
        tgCash.setSelected(true);
        setNetTotal();
        dtPaidDate.setValue(LocalDate.now());
       makeTable();
        setOrderDetail();
        setCardPaymentToggle();
        btnSubmitPayment.setOnAction(this);
        tgVisaCard.setSelected(true);
    }

    private void getNextPaymentID() {
        try {
            txtPaymentID.setText(new IDGenerator(IDGenerator.TableName.CUSTOMERPAYMENT).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(CustomerPaymentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setOrderDetails(ObservableList<CustomerOrderTM> orderDetail) {
        orderDetails = orderDetail;
    }

    public static void setOrderID(CustomerOrderDTO orderId) {
        customerOrderDTO = orderId;
    }

    public static void setCustomerId(CustomerDTO customer) {
        customerDTO = customer;
    }

    private void setPaymentMethodToggle() {
        tgCard.setToggleGroup(tgPaymentMethod);
        tgCash.setToggleGroup(tgPaymentMethod);
        tgCheque.setToggleGroup(tgPaymentMethod);
    }

    private void tgStateChange() {
        tgPaymentMethod.selectedToggleProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue != null && newValue == tgCash) {
                tabPane.getSelectionModel().select(tabCash);
                tabCash.setDisable(false);
                tabCheque.setDisable(true);
                tabDebitCard.setDisable(true);

            }
            if (newValue != null && newValue == tgCard) {
                tabPane.getSelectionModel().select(tabDebitCard);
                tabDebitCard.setDisable(false);
                tabCheque.setDisable(true);
                tabCash.setDisable(true);
            }
            if (newValue != null && newValue == tgCheque) {
                tabPane.getSelectionModel().select(tabCheque);
                tabDebitCard.setDisable(true);
                tabCheque.setDisable(false);
                tabCash.setDisable(true);
            }
            if (newValue == null) {
                tgCash.setSelected(true);
                tabPane.getSelectionModel().select(tabCash);
                tabCash.setDisable(false);
                tabCheque.setDisable(true);
                tabDebitCard.setDisable(true);
            }

        });
    }

    private void setCardPaymentToggle() {
        ToggleGroup tgCardType = new ToggleGroup();
        tgAmericanExpress.setToggleGroup(tgCardType);
        tgMasterCard.setToggleGroup(tgCardType);
        tgVisaCard.setToggleGroup(tgCardType);
        tgCardType.selectedToggleProperty().addListener((ob, oldValue, newValue) -> {
            if (newValue == null) {
                tgVisaCard.setSelected(true);
            }

        });
    }

    private void makeTable() {
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        batchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationID.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        EXP.setCellValueFactory(new PropertyValueFactory<>("EXP"));
        MFD.setCellValueFactory(new PropertyValueFactory<>("MFD"));
        RackNo.setCellValueFactory(new PropertyValueFactory<>("rackNo"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        QTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        sectionName.setCellValueFactory(new PropertyValueFactory<>("sectionName"));
        if (!orderDetails.isEmpty()) {
            tblOrderDetail.setItems(orderDetails);

        }

    }

    private void setNetTotal() {
        double netTotal = 0;
        for (CustomerOrderTM customerOrderTM : orderDetails) {
            netTotal += customerOrderTM.getTotal();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
        txtAmountCard.setText(String.valueOf(netTotal));
        txtAmountCash.setText(String.valueOf(netTotal));
        txtAmountCheque.setText(String.valueOf(netTotal));
    }

    private boolean checkValue() {
        if (tgCash.isSelected()) {
            if (!txtAmountCash.getText().isEmpty() && dtPaidDate.getValue() != null) {
                return true;
            }
        } else if (tgCard.isSelected()) {
            if (!txtCardNo.getText().isEmpty() && !txtValidDate.getText().isEmpty()
                    && !txtCVV.getText().isEmpty()
                    && (tgAmericanExpress.isSelected() || tgMasterCard.isSelected() || tgVisaCard.isSelected())
                    && !txtAmountCard.getText().isEmpty()
                    && dtPaidDate.getValue() != null) {
                return true;
            }
        } else if (tgCheque.isSelected()) {
            if (!txtChequeNo.getText().isEmpty() && !txtBankName.getText().isEmpty()
                    && !txtAmountCheque.getText().isEmpty()
                    && dtPaidDate.getValue() != null && dtRealization.getValue() != null) {
                return true;
            }
        }
        return false;
    }

    private void setOrderDetail() {
        if (customerOrderDTO!=null) {
            txtOrderID.setText(customerOrderDTO.getOrderId());
            txtCustomerID.setText(customerOrderDTO.getCustomerId());
            dtOrderedDate.setValue(LocalDate.parse(customerOrderDTO.getOrderedDate()));
        }
    }

    private void SaveOrderANDPayment() {

        try {
            if (txtCustomerID.getText().equals(new IDGenerator(IDGenerator.TableName.CUSTOMER).getNextID())) {
                CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMER);
                customerBO.addCustomer(makeCustomerDTO());
            }

            CustomerOrderDTO customerOrderDTO = CustomerPaymentFormController.customerOrderDTO;

            ArrayList<CustomerOrderDetailDTO> customerOrderDetailDTOs = makeCustomerOrderDetails();

            ArrayList<BatchDetailDTO> batchDetailDTOs = makeBatchDetailDTOs(customerOrderDetailDTOs);

            CardDTO cardDTO = null;

            RecivedChequeDTO recivedChequeDTO = null;

            if (tgCard.isSelected()) {
                cardDTO = makeCardDTO();
            }
            if (tgCheque.isSelected()) {
                recivedChequeDTO = makeRecivedChequeDTO();
            }

            CustomerPaymentDTO customerPaymentDTO = makeCustomerPaymentDTO(cardDTO, recivedChequeDTO);

            CustomerOrderFillingBO customerOrderFillingBO = (CustomerOrderFillingBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMERORDERTFILLING);
            boolean customerOrderIsAdded = customerOrderFillingBO.saveCustomerOrder(customerOrderDTO, customerOrderDetailDTOs, batchDetailDTOs);
            if (customerOrderIsAdded) {
                CustomerPaymentFillingBO customerPaymentFillingBO = (CustomerPaymentFillingBO) BOFactory.getBOFactory().getSuperBO(BOFactory.BOType.CUSTOMERPAYMENTFILLING);
                boolean customerPaymentIsAdded = customerPaymentFillingBO.saveCustomerPayments(customerPaymentDTO, recivedChequeDTO, cardDTO);
                if (customerPaymentIsAdded) {
                    Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
                    saveAlert.setTitle("Submit Payment");
                    saveAlert.setHeaderText("Payment Successful!");
                    saveAlert.showAndWait();
                    BorderPane pane = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/CustomerOrderForm.fxml"));
                    display.setBackground(Background.EMPTY);
                    display.getChildren().setAll(pane);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(CustomerPaymentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ArrayList<CustomerOrderDetailDTO> makeCustomerOrderDetails() {
        ArrayList<CustomerOrderDetailDTO> customerOrderDetailDTOs = new ArrayList<>();
        for (CustomerOrderTM customerOrderTM : orderDetails) {
            CustomerOrderDetailDTO customerOrderDetailDTO = new CustomerOrderDetailDTO(
                    customerOrderDTO.getOrderId(),
                    customerOrderTM.getBatchNo(),
                    customerOrderTM.getItemCode(),
                    customerOrderTM.getQTY()
            );
            customerOrderDetailDTOs.add(customerOrderDetailDTO);
        }
        return customerOrderDetailDTOs;
    }

    private ArrayList<BatchDetailDTO> makeBatchDetailDTOs(ArrayList<CustomerOrderDetailDTO> customerOrderDetailDTOs) {
        ArrayList<BatchDetailDTO> batchDetailDTOs = new ArrayList<>();
        for (CustomerOrderDetailDTO temp : customerOrderDetailDTOs) {
            BatchDetailDTO batchDetailDTO = new BatchDetailDTO();
            batchDetailDTO.setBatchNo(temp.getBatchNo());
            batchDetailDTO.setItemCode(temp.getItemCode());
            batchDetailDTO.setQTY(temp.getQTY());
            batchDetailDTOs.add(batchDetailDTO);
        }
        return batchDetailDTOs;
    }

    private RecivedChequeDTO makeRecivedChequeDTO() {
        RecivedChequeDTO recivedChequeDTO = new RecivedChequeDTO(
                txtChequeNo.getText(),
                txtBankName.getText(),
                null,
                Double.parseDouble(txtAmountCheque.getText()),
                String.valueOf(dtRealization.getValue()),
                String.valueOf(dtPaidDate.getValue())
        );
        if (txtStatus.getText() != null && !txtStatus.getText().isEmpty()) {
            recivedChequeDTO.setStatus(txtStatus.getText());
        }
        return recivedChequeDTO;
    }

    private CardDTO makeCardDTO() {
        CardDTO cardDTO = new CardDTO(
                Long.parseLong(txtCardNo.getText()),
                txtValidDate.getText(),
                Integer.parseInt(txtCVV.getText()),
                "",
                Double.parseDouble(txtAmountCard.getText()),
                String.valueOf(dtPaidDate.getValue())
        );
        if (tgAmericanExpress.isSelected()) {
            cardDTO.setCardType("AMERICAN EXPRESS");
        }
        if (tgVisaCard.isSelected()) {
            cardDTO.setCardType("VISA");
        }
        if (tgMasterCard.isSelected()) {
            cardDTO.setCardType("MASTER CARD");
        }
        return cardDTO;
    }

    private CustomerPaymentDTO makeCustomerPaymentDTO(CardDTO cardDTO, RecivedChequeDTO recivedChequeDTO) {
        CustomerPaymentDTO customerPaymentDTO = new CustomerPaymentDTO(txtPaymentID.getText(), customerOrderDTO.getOrderId(), 0, null, null, 0, String.valueOf(dtPaidDate.getValue()), String.valueOf(LocalTime.now()));
        if (tgCard.isSelected()) {
            customerPaymentDTO.setPaymentMethod("Card");
            customerPaymentDTO.setCardNo(cardDTO.getCardNo());
            customerPaymentDTO.setRecivedChaqueId(null);
            customerPaymentDTO.setAmount(Double.parseDouble(txtAmountCard.getText()));
        }
        if (tgCheque.isSelected()) {
            customerPaymentDTO.setPaymentMethod("Cheque");
            customerPaymentDTO.setCardNo(0);
            customerPaymentDTO.setRecivedChaqueId(recivedChequeDTO.getRecivedChaqueId());
            customerPaymentDTO.setAmount(Double.parseDouble(txtAmountCheque.getText()));
        }
        if (tgCash.isSelected()) {
            customerPaymentDTO.setCardNo(0);
            customerPaymentDTO.setRecivedChaqueId(null);
            customerPaymentDTO.setAmount(Double.parseDouble(txtAmountCash.getText()));
            customerPaymentDTO.setPaymentMethod("Cash");
        }
        return customerPaymentDTO;
    }

    private CustomerDTO makeCustomerDTO() {
        CustomerDTO customer = new CustomerDTO();

        try {
            customer.setAddedDate(String.valueOf(LocalDate.now()));
            customer.setCustomerId(new IDGenerator(IDGenerator.TableName.CUSTOMER).getNextID());
        } catch (Exception ex) {
            Logger.getLogger(CustomerPaymentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnSubmitPayment) {
            if (checkValue()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Submit Payment");
                alert.setHeaderText("You want be able to change Payments\\n Make sure everything is OK!");
                ButtonType btnYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(btnYes, btnCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == btnYes) {
                    SaveOrderANDPayment();
                }
            }
        }
    }

}
