/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.customer.CustomerPaymentBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.CustomerPaymentDAO;
import lk.buyingandselling.model.DTO.CustomerPaymentDTO;
import lk.buyingandselling.model.entity.CustomerPayment;

/**
 *
 * @author SLR
 */
public class CustomerPaymentBOImpl implements CustomerPaymentBO{

    private CustomerPaymentDAO customerPaymentDAO;
    
    public CustomerPaymentBOImpl() {
        customerPaymentDAO=(CustomerPaymentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMERPAYMENT);
    }

    
    @Override
    public boolean addCustomerPayment(CustomerPaymentDTO customerPaymentDTO) throws Exception {
        return customerPaymentDAO.add(makeCustomerPayment(customerPaymentDTO));
        
    }

    @Override
    public boolean updateCustomerPayment(CustomerPaymentDTO customerPaymentDTO) throws Exception {
        return customerPaymentDAO.update(makeCustomerPayment(customerPaymentDTO));
    }

    @Override
    public ArrayList<CustomerPaymentDTO> searchCustomerPayment(CustomerPaymentDTO customerPaymentDTO) throws Exception {
        ArrayList<CustomerPayment> customerPayments=customerPaymentDAO.search(makeCustomerPayment(customerPaymentDTO));
        ArrayList<CustomerPaymentDTO> customerPaymentDTOs=new ArrayList<>();
        for(CustomerPayment customerPayment:customerPayments){
            customerPaymentDTOs.add(makCustomerPaymentDTO(customerPayment));
        }
        return customerPaymentDTOs;
    }

    @Override
    public ArrayList<CustomerPaymentDTO> getAllCustomerPayment() throws Exception {
        ArrayList<CustomerPayment> customerPayments=customerPaymentDAO.getAll();
        ArrayList<CustomerPaymentDTO> customerPaymentDTOs=new ArrayList<>();
        for(CustomerPayment customerPayment : customerPayments){
            customerPaymentDTOs.add(makCustomerPaymentDTO(customerPayment));
        }
        return customerPaymentDTOs;
    }
    private CustomerPayment makeCustomerPayment(CustomerPaymentDTO customerPaymentDTO){
        return new CustomerPayment(
                customerPaymentDTO.getPaymentId(),
                customerPaymentDTO.getOrderId(), 
                customerPaymentDTO.getAmount(),
                customerPaymentDTO.getPaymentMethod(),
                customerPaymentDTO.getRecivedChaqueId(), 
                customerPaymentDTO.getCardNo(),
                customerPaymentDTO.getPaidDate(), 
                customerPaymentDTO.getPaidTime()
        );
    }
    private CustomerPaymentDTO makCustomerPaymentDTO(CustomerPayment customerPayment){
        return new  CustomerPaymentDTO(
                customerPayment.getPaymentId(),
                customerPayment.getOrderId(), 
                customerPayment.getAmount(), 
                customerPayment.getPaymentMethod(), 
                customerPayment.getRecivedChaqueId(),
                customerPayment.getCardNo(),
                customerPayment.getPaidDate(), 
                customerPayment.getPaidTime()
        );
    }

    @Override
    public ArrayList<CustomerPaymentDTO> dayIncome() throws Exception {
        ArrayList<CustomerPayment> customerPayments=customerPaymentDAO.dayIncome();
        ArrayList<CustomerPaymentDTO> customerPaymentDTOs=new ArrayList<>();
        for(CustomerPayment temp: customerPayments){
            CustomerPaymentDTO customerPaymentDTO=new CustomerPaymentDTO();
            customerPaymentDTO.setAmount(temp.getAmount());
            customerPaymentDTO.setPaidDate(temp.getPaidDate());
            customerPaymentDTOs.add(customerPaymentDTO);
        }
        return customerPaymentDTOs;
    }
}

