/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.CustomerPaymentDTO;

/**
 *
 * @author SLR
 */
public interface CustomerPaymentBO extends SuperBO {

    public boolean addCustomerPayment(CustomerPaymentDTO customerPaymentDTO) throws Exception;

    public boolean updateCustomerPayment(CustomerPaymentDTO customerPaymentDTO) throws Exception;

    public ArrayList<CustomerPaymentDTO> searchCustomerPayment(CustomerPaymentDTO customerPaymentDTO) throws Exception;

    public ArrayList<CustomerPaymentDTO> getAllCustomerPayment() throws Exception;
    
    public ArrayList<CustomerPaymentDTO> dayIncome()throws Exception;
    
}
