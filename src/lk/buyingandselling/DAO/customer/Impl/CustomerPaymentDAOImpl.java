/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.customer.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.customer.CustomerPaymentDAO;
import lk.buyingandselling.model.entity.CustomerPayment;

/**
 *
 * @author SLR
 */
public class CustomerPaymentDAOImpl implements CustomerPaymentDAO {

    @Override
    public boolean add(CustomerPayment entity) throws Exception {
        String sql = "INSERT INTO customerPayment VALUES(?,?,?,?,?,?,?,?)";
        if (entity.getCardNo() == 0) {
            return CrudOperation.executeUpdate(
                    sql,
                    entity.getPaymentId(),
                    entity.getOrderId(),
                    entity.getAmount(),
                    entity.getPaymentMethod(),
                    entity.getRecivedChaqueId(),
                    null,
                    entity.getPaidDate(),
                    entity.getPaidTime()
            );
        } else if (entity.getRecivedChaqueId() == null) {
            return CrudOperation.executeUpdate(
                    sql,
                    entity.getPaymentId(),
                    entity.getOrderId(),
                    entity.getAmount(),
                    entity.getPaymentMethod(),
                    null,
                    entity.getCardNo(),
                    entity.getPaidDate(),
                    entity.getPaidTime()
            );

        } else {
            return CrudOperation.executeUpdate(
                    sql,
                    entity.getPaymentId(),
                    entity.getOrderId(),
                    entity.getAmount(),
                    entity.getPaymentMethod(),
                    entity.getRecivedChaqueId(),
                    entity.getCardNo(),
                    entity.getPaidDate(),
                    entity.getPaidTime()
            );
        }

    }

    @Override
    public ArrayList<CustomerPayment> getAll() throws Exception {
        ArrayList<CustomerPayment> allCustomerPayments = new ArrayList<>();
        String sql = "SELECT * FROM customerPayment";
        ResultSet rs = CrudOperation.executeQuery(sql);
        while (rs.next()) {
            CustomerPayment customerPayment = new CustomerPayment(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getLong(6),
                    rs.getString(7),
                    rs.getString(8)
            );
            allCustomerPayments.add(customerPayment);
        }
        return allCustomerPayments;
    }

    @Override
    public boolean delete(CustomerPayment id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CustomerPayment entity) throws Exception {
        String sql = "UPDATE customerPayment SET orderId=?,amount=?,paymentMethod=?,"
                + "recivedChaqueId=?,cardNo=?,paidDate=?,paidTime=? WHERE paymentId=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getOrderId(),
                entity.getAmount(),
                entity.getPaymentMethod(),
                entity.getRecivedChaqueId(),
                entity.getCardNo(),
                entity.getPaidDate(),
                entity.getPaidTime(),
                entity.getPaymentId()
        );
    }

    @Override
    public ArrayList<CustomerPayment> search(CustomerPayment entity) throws Exception {
        String sql;
        int i = -1;
        ResultSet rs = null;
        if (entity.getPaymentId().isEmpty() && !entity.getOrderId().isEmpty()) {
            sql = "SELECT * FROM customerPayment WHERE orderId=?";
            rs = CrudOperation.executeQuery(sql, entity.getOrderId());
            i = 0;
        } else if (entity.getPaymentId().isEmpty() && entity.getOrderId().isEmpty()
                && !entity.getPaymentMethod().isEmpty() && entity.getPaidDate().isEmpty()) {
            sql = "SELECT * FROM customerPayment WHERE paymentMethod=?";
            rs = CrudOperation.executeQuery(sql, entity.getPaymentMethod());
            i = 0;
        } else if (entity.getPaymentId().isEmpty() && entity.getOrderId().isEmpty()
                && !entity.getPaymentMethod().isEmpty() && !entity.getPaidDate().isEmpty()) {
            sql = "SELECT * FROM customerPayment WHERE paymentMethod=? AND paidDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getPaymentMethod(), entity.getPaidDate());
            i = 0;
        } else if (entity.getPaymentId().isEmpty() && entity.getOrderId().isEmpty()
                && entity.getPaymentMethod().isEmpty() && !entity.getRecivedChaqueId().isEmpty()
                && entity.getCardNo() == 0) {
            sql = "SELECT * FROM customerPayment WHERE recivedChaquId=?";
            rs = CrudOperation.executeQuery(sql, entity.getRecivedChaqueId());
            i = 0;
        } else if (entity.getPaymentId().isEmpty() && entity.getOrderId().isEmpty()
                && entity.getPaymentMethod().isEmpty() && entity.getRecivedChaqueId().isEmpty()
                && entity.getCardNo() != 0 && entity.getPaidDate().isEmpty()) {
            sql = "SELECT * FROM customerPayment WHERE cardNo=?";
            rs = CrudOperation.executeQuery(sql, entity.getCardNo());
            i = 0;
        } else if (entity.getPaymentId().isEmpty() && entity.getOrderId().isEmpty()
                && entity.getPaymentMethod().isEmpty() && entity.getRecivedChaqueId().isEmpty()
                && entity.getCardNo() != 0 && !entity.getPaidDate().isEmpty()) {
            sql = "SELECT * FROM customerPayment WHERE cardNo=? AND paidDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getCardNo(), entity.getPaidDate());
            i = 0;
        }
        if (i == -1) {
            sql = "SELECT * FROM customerPayment WHERE paymentId=? OR orderId=? OR paidDate=? OR paymentMethod=? OR recivedChaqueId=? OR cardNo=?";
            rs = CrudOperation.executeQuery(
                    sql,
                    entity.getPaymentId(),
                    entity.getOrderId(),
                    entity.getPaidDate(),
                    entity.getPaymentMethod(),
                    entity.getRecivedChaqueId(),
                    entity.getCardNo()
            );
        }
        ArrayList<CustomerPayment> customerPayments = new ArrayList<>();
        while (rs.next()) {
            CustomerPayment customerPayment = new CustomerPayment(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getLong(6),
                    rs.getString(7),
                    rs.getString(8)
            );
            customerPayments.add(customerPayment);
        }
        return customerPayments;
    }

    @Override
    public ArrayList<CustomerPayment> dayIncome() throws Exception {
        String sql = "SELECT SUM(amount),paidDate FROM customerpayment GROUP BY paiddate";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<CustomerPayment> customerPayments = new ArrayList<>();
        while (rs.next()) {
            CustomerPayment customerPayment = new CustomerPayment();
            customerPayment.setAmount(rs.getDouble(1));
            customerPayment.setPaidDate(rs.getString(2));
            customerPayments.add(customerPayment);
        }
        return customerPayments;
    }

}
