/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.CustomerDTO;

/**
 *
 * @author SLR
 */
public interface CustomerBO extends SuperBO{

    public boolean addCustomer(CustomerDTO customerDTO) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomers() throws Exception;
    
    public ArrayList<CustomerDTO> searchCustomer(CustomerDTO customerDTO) throws Exception;
    
    public boolean deleteCustomer(String customerId) throws Exception;
    
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;
    
}
