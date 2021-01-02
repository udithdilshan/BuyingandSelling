/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.CustomerOrderDetailDTO;

/**
 *
 * @author SLR
 */
public interface CustomerOrderDetailBO extends SuperBO {

    public boolean addCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception;

    public boolean updateCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception;

    public boolean deleteCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception;

    public ArrayList<CustomerOrderDetailDTO> getAllCustomerOrderDetail() throws Exception;

    public ArrayList<CustomerOrderDetailDTO> searchCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception;
}
