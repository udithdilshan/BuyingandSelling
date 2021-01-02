/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.customer.CustomerBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.CustomerDAO;
import lk.buyingandselling.model.DTO.CustomerDTO;
import lk.buyingandselling.model.entity.Customer;

/**
 *
 * @author SLR
 */
public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO;

    public CustomerBOImpl() {
        customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.add(makeCustomer(customerDTO));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOs.add(makeCustomerDTO(c));
        }
        return customerDTOs;
    }

    private Customer makeCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getFirstname(),
                customerDTO.getLastname(),
                customerDTO.getAddress(),
                customerDTO.getCompanyName(),
                customerDTO.getNIC(),
                customerDTO.getGender(),
                customerDTO.getCity(),
                customerDTO.getMobile(),
                customerDTO.getEmail(),
                customerDTO.getPostalCode(),
                customerDTO.getAddedDate()
        );
        return customer;
    }

    private CustomerDTO makeCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getAddress(),
                customer.getCompanyName(),
                customer.getNIC(),
                customer.getGender(),
                customer.getCity(),
                customer.getMobile(),
                customer.getEmail(),
                customer.getPostalCode(),
                customer.getAddedDate()
        );

    }

    @Override
    public ArrayList<CustomerDTO> searchCustomer(CustomerDTO customerDTO) throws Exception {
        ArrayList<Customer> customers = customerDAO.search(makeCustomer(customerDTO));
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOs.add(makeCustomerDTO(c));
        }
        return customerDTOs;
    }

    @Override
    public boolean deleteCustomer(String customerId) throws Exception {
        return customerDAO.delete(customerId);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.update(makeCustomer(customerDTO));
    }
}
