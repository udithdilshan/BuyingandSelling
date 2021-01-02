/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.customer.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.customer.CustomerOrderDAO;
import lk.buyingandselling.model.entity.CustomerOrder;

/**
 *
 * @author SLR
 */
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

    public CustomerOrderDAOImpl() {
    }

    @Override
    public boolean add(CustomerOrder entity) throws Exception {
        String sql = "INSERT INTO customerOrder VALUES(?,?,?,?)";
        return CrudOperation.executeUpdate(sql, entity.getOrderId(), entity.getCustomerId(), entity.getOrderedDate(), entity.getOrderedTime());
    }

    @Override
    public ArrayList<CustomerOrder> getAll() throws Exception {
        String sql = "SELECT * FROM customerOrder";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
        while (rs.next()) {
            CustomerOrder order = new CustomerOrder(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
            customerOrders.add(order);
        }
        return customerOrders;
    }

    @Override
    public boolean delete(CustomerOrder id) throws Exception {
        String sql = "DELETE FROM customerOrder WHERE orderId=?";
        return CrudOperation.executeUpdate(sql, id.getOrderId());
    }

    @Override
    public boolean update(CustomerOrder entity) throws Exception {
        String sql = "UPDATE customerOrder SET customerId=? WHERE orderId=?";
        return CrudOperation.executeUpdate(sql, entity.getCustomerId(), entity.getOrderId());
    }

    @Override
    public ArrayList<CustomerOrder> search(CustomerOrder entity) throws Exception {
        String sql;
        ResultSet rs = null;
        if (entity.getOrderId().isEmpty() && !entity.getCustomerId().isEmpty() && entity.getOrderedDate().isEmpty()) {
            sql = "SELECT * FROM customerOrder WHERE customerId=?";
            rs = CrudOperation.executeQuery(sql, entity.getCustomerId());
        } else if (entity.getOrderId().isEmpty() && !entity.getCustomerId().isEmpty() && !entity.getOrderedDate().isEmpty()) {
            sql = "SELECT * FROM customerOrder WHERE customerId=? AND orderedDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getCustomerId(), entity.getOrderedDate());
        } else if (entity.getOrderId().isEmpty() && entity.getCustomerId().isEmpty() && !entity.getOrderedDate().isEmpty()) {
            sql = "SELECT * FROM customerOrder WHERE orderedDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getOrderedDate());
        } else {
            sql = "SELECT * FROM customerOrder WHERE customerId=? OR orderId=? OR orderedDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getCustomerId(), entity.getOrderId(), entity.getOrderedDate());
        }
        ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
        while (rs.next()) {
            CustomerOrder customerOrder = new CustomerOrder(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            customerOrders.add(customerOrder);
        }
        return customerOrders;
    }

    @Override
    public ArrayList<CustomerOrder> getNoOfOrders() throws Exception {
        String sql="SELECT COUNT(orderId),orderedDate FROM customerOrder GROUP BY orderedDate";
        ResultSet rs=CrudOperation.executeQuery(sql);
        ArrayList<CustomerOrder> customerOrders=new ArrayList<>();
        while (rs.next()){
            CustomerOrder customerOrder=new CustomerOrder();
            customerOrder.setCustomerId(rs.getString(1));
            customerOrder.setOrderedDate(rs.getString(2));
            customerOrders.add(customerOrder);
        }
        return customerOrders;
    }

}
