/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.customer.CustomerOrderBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.CustomerOrderDAO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;
import lk.buyingandselling.model.entity.CustomerOrder;

/**
 *
 * @author SLR
 */
public class CustomerOrderBOImpl implements CustomerOrderBO {

    private CustomerOrderDAO customerOrderDAO;

    public CustomerOrderBOImpl() {
        customerOrderDAO = (CustomerOrderDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMERORDER);
    }

    @Override
    public boolean addCustomerOrder(CustomerOrderDTO customerOrderDTO) throws Exception {
        return customerOrderDAO.add(makeCustomerOrder(customerOrderDTO));
    }

    @Override
    public boolean updateCustomerOrder(CustomerOrderDTO customerOrderDTO) throws Exception {
        return customerOrderDAO.update(makeCustomerOrder(customerOrderDTO));
    }

    @Override
    public boolean deleteCustomerOrder(CustomerOrderDTO customerOrderDTO) throws Exception {
        return customerOrderDAO.delete(makeCustomerOrder(customerOrderDTO));
    }

    @Override
    public ArrayList<CustomerOrderDTO> getAllCustomerOrders() throws Exception {
        ArrayList<CustomerOrder> customerOrders = customerOrderDAO.getAll();
        ArrayList<CustomerOrderDTO> customerOrderDTOs = new ArrayList<>();
        for (CustomerOrder temp : customerOrders) {
            customerOrderDTOs.add(makeCustomerOrderDTO(temp));
        }
        return customerOrderDTOs;
    }

    @Override
    public ArrayList<CustomerOrderDTO> searchCustomerOrders(CustomerOrderDTO customerOrderDTO) throws Exception {
        ArrayList<CustomerOrder> customerOrders = customerOrderDAO.getAll();
        ArrayList<CustomerOrderDTO> customerOrderDTOs = new ArrayList<>();
        for (CustomerOrder temp : customerOrders) {
            customerOrderDTOs.add(makeCustomerOrderDTO(temp));
        }
        return customerOrderDTOs;
    }

    private CustomerOrder makeCustomerOrder(CustomerOrderDTO customerOrderDTO) {
        return new CustomerOrder(
                customerOrderDTO.getOrderId(),
                customerOrderDTO.getCustomerId(),
                customerOrderDTO.getOrderedDate(),
                customerOrderDTO.getOrderedTime()
        );
    }

    private CustomerOrderDTO makeCustomerOrderDTO(CustomerOrder customerOrder) {
        return new CustomerOrderDTO(
                customerOrder.getOrderId(),
                customerOrder.getCustomerId(),
                customerOrder.getOrderedDate(),
                customerOrder.getOrderedTime()
        );
    }

    @Override
    public ArrayList<CustomerOrderDTO> getNoOfOrders() throws Exception {
        ArrayList<CustomerOrder> customerOrders=customerOrderDAO.getNoOfOrders();
        ArrayList<CustomerOrderDTO> customerOrderDTOs=new ArrayList<>();
        for(CustomerOrder customerOrder: customerOrders){
            CustomerOrderDTO customerOrderDTO=new CustomerOrderDTO();
            customerOrderDTO.setCustomerId(customerOrder.getCustomerId());
            customerOrderDTO.setOrderedDate(customerOrder.getOrderedDate());
            customerOrderDTOs.add(customerOrderDTO);
        }
        return customerOrderDTOs;
    }
}
