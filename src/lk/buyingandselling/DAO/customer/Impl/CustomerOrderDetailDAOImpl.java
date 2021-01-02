/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.customer.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.customer.CustomerOrderDetailDAO;
import lk.buyingandselling.model.entity.CustomerOrderDetail;

/**
 *
 * @author SLR
 */
public class CustomerOrderDetailDAOImpl implements CustomerOrderDetailDAO {

    @Override
    public boolean add(CustomerOrderDetail entity) throws Exception {
        String sql = "INSERT INTO customerOrderDetail VALUES (?,?,?,?)";
        return CrudOperation.executeUpdate(sql, entity.getOrderId(), entity.getBatchNo(), entity.getItemCode(), entity.getQTY());
    }

    @Override
    public ArrayList<CustomerOrderDetail> getAll() throws Exception {
        String sql = "SELECT * FROM customerOrderDetail";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<CustomerOrderDetail> customerOrderDetails = new ArrayList<>();
        while (rs.next()) {
            CustomerOrderDetail customerOrderDetail = new CustomerOrderDetail(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4)
            );
            customerOrderDetails.add(customerOrderDetail);
        }
        return customerOrderDetails;
    }

    @Override
    public boolean delete(CustomerOrderDetail id) throws Exception {
        String sql = "DELETE FROM customerOrderDetail WHERE orderId=?";
        return CrudOperation.executeUpdate(sql, id.getOrderId());
    }

    @Override
    public boolean update(CustomerOrderDetail entity) throws Exception {
        String sql = "UPDATE customerOrderDetail SET batchNo=? ,itemCode =? ,QTY=? WHERE orderId=?";
        return CrudOperation.executeUpdate(sql, entity.getBatchNo(), entity.getItemCode(), entity.getQTY(), entity.getOrderId());
    }

    @Override
    public ArrayList<CustomerOrderDetail> search(CustomerOrderDetail entity) throws Exception {
        String sql;
        ResultSet rs = null;
        if (entity.getOrderId().isEmpty() && entity.getBatchNo().isEmpty() && !entity.getItemCode().isEmpty()) {
            sql = "SELECT * FROM customerOrderDetail WHERE batchNo=?";
            rs = CrudOperation.executeQuery(sql, entity.getBatchNo());
        } else if (entity.getOrderId().isEmpty() && !entity.getBatchNo().isEmpty() && entity.getItemCode().isEmpty()) {
            sql = "SELECT * FROM customerOrderDetail WHERE itemCode=?";
            rs = CrudOperation.executeQuery(sql, entity.getItemCode());
        } else if (entity.getOrderId().isEmpty() && !entity.getBatchNo().isEmpty() && !entity.getItemCode().isEmpty()) {
            sql = "SELECT * FROM customerOrderDetail WHERE batchNo=?,itemCode=?";
            rs = CrudOperation.executeQuery(sql, entity.getBatchNo(), entity.getItemCode());
        } else {
            sql = "SELECT * FROM customerOrderDetail WHERE orderId =? ,batchNo=?,itemCode =? ";
            rs = CrudOperation.executeQuery(sql, entity.getOrderId(), entity.getBatchNo(), entity.getItemCode());
        }
        ArrayList<CustomerOrderDetail> customerOrderDetails = new ArrayList<>();
        while (rs.next()) {
            CustomerOrderDetail customerOrderDetail = new CustomerOrderDetail(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4)
            );
            customerOrderDetails.add(customerOrderDetail);
        }
        return customerOrderDetails;
    }

}
