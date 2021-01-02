/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.customer.CustomerOrderDetailBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.CustomerOrderDetailDAO;
import lk.buyingandselling.model.DTO.CustomerOrderDetailDTO;
import lk.buyingandselling.model.entity.CustomerOrderDetail;

/**
 *
 * @author SLR
 */
public class CustomerOrderDetailBOImpl implements CustomerOrderDetailBO {

    private CustomerOrderDetailDAO customerOrderDetailDAO;

    public CustomerOrderDetailBOImpl() {
        customerOrderDetailDAO = (CustomerOrderDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMERORDERDETAIL);
    }

    @Override
    public boolean addCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception {
        return customerOrderDetailDAO.add(makeCustomerOrderDetail(customerOrderDetailDTO));
    }

    @Override
    public boolean updateCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception {
        return customerOrderDetailDAO.update(makeCustomerOrderDetail(customerOrderDetailDTO));
    }

    @Override
    public boolean deleteCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception {
        return customerOrderDetailDAO.delete(makeCustomerOrderDetail(customerOrderDetailDTO));
    }

    @Override
    public ArrayList<CustomerOrderDetailDTO> getAllCustomerOrderDetail() throws Exception {
        ArrayList<CustomerOrderDetail> customerOrderDetails = customerOrderDetailDAO.getAll();
        ArrayList<CustomerOrderDetailDTO> customerOrderDetailDTOs = new ArrayList<>();
        for (CustomerOrderDetail temp : customerOrderDetails) {
            CustomerOrderDetailDTO customerOrderDetailDTO = new CustomerOrderDetailDTO(
                    temp.getOrderId(),
                    temp.getBatchNo(),
                    temp.getItemCode(),
                    temp.getQTY()
            );
            customerOrderDetailDTOs.add(customerOrderDetailDTO);
        }
        return customerOrderDetailDTOs;
    }

    @Override
    public ArrayList<CustomerOrderDetailDTO> searchCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) throws Exception {
        ArrayList<CustomerOrderDetail> customerOrderDetails = customerOrderDetailDAO.getAll();
        ArrayList<CustomerOrderDetailDTO> customerOrderDetailDTOs = new ArrayList<>();
        for (CustomerOrderDetail temp : customerOrderDetails) {
            CustomerOrderDetailDTO customer = new CustomerOrderDetailDTO(
                    temp.getOrderId(),
                    temp.getBatchNo(),
                    temp.getItemCode(),
                    temp.getQTY()
            );
            customerOrderDetailDTOs.add(customer);
        }
        return customerOrderDetailDTOs;
    }

    private CustomerOrderDetail makeCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) {
        return new CustomerOrderDetail(
                customerOrderDetailDTO.getOrderId(),
                customerOrderDetailDTO.getBatchNo(),
                customerOrderDetailDTO.getItemCode(),
                customerOrderDetailDTO.getQTY()
        );
    }
}
