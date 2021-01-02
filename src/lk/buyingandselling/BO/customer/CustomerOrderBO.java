/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;

/**
 *
 * @author SLR
 */
public interface CustomerOrderBO extends SuperBO {

    public boolean addCustomerOrder(CustomerOrderDTO customerOrderDTO) throws Exception;

    public boolean updateCustomerOrder(CustomerOrderDTO customerOrderDTO) throws Exception;

    public boolean deleteCustomerOrder(CustomerOrderDTO customerOrderDTO) throws Exception;

    public ArrayList<CustomerOrderDTO> getAllCustomerOrders() throws Exception;

    public ArrayList<CustomerOrderDTO> searchCustomerOrders(CustomerOrderDTO customerOrderDTO) throws Exception;
    
    public ArrayList<CustomerOrderDTO> getNoOfOrders() throws Exception;
}
