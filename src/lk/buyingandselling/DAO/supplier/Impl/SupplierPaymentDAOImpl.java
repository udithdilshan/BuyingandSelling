/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.SupplierPaymentDAO;
import lk.buyingandselling.model.entity.SupplierPayment;

/**
 *
 * @author SLR
 */
public class SupplierPaymentDAOImpl implements SupplierPaymentDAO {

    @Override
    public boolean add(SupplierPayment entity) throws Exception {
        String sql = "INSERT INTO supplierPayment VALUES(?,?,?,?,?,?,?)";

        return CrudOperation.executeUpdate(
                sql,
                entity.getPaymentId(),
                entity.getPurchaseId(),
                entity.getPaidAmount(),
                entity.getTotalAmount(),
                entity.getPaymentMethod(),
                entity.getIssuedChequeId(),
                entity.getPaidDate()
        );

    }

    @Override
    public ArrayList<SupplierPayment> getAll() throws Exception {
        String sql = "SELECT * FROM supplierPayment";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<SupplierPayment> supplierPayments = new ArrayList<>();
        while (rs.next()) {
            SupplierPayment temp = new SupplierPayment(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            );
            supplierPayments.add(temp);
        }
        return supplierPayments;
    }

    @Override
    public boolean delete(SupplierPayment id) throws Exception {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(SupplierPayment entity) throws Exception {
        String sql = "UPDATE supplierPayment SET purchaseId=?, paidAmount=?,totalAmount=?,paymentMethod=?,issuedChequeId=?,paidDate=? WHERE paymentId=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getPurchaseId(),
                entity.getPaidAmount(),
                entity.getTotalAmount(),
                entity.getPaymentMethod(),
                entity.getIssuedChequeId(),
                entity.getPaidDate(),
                entity.getPaymentId()
        );
    }

    @Override
    public ArrayList<SupplierPayment> search(SupplierPayment entity) throws Exception {
        String sql;
        ResultSet rs = null;
        int i = -1;
        if (entity.getPaymentId().isEmpty() && !entity.getPurchaseId().isEmpty()
                && !entity.getPaidDate().isEmpty() && entity.getPaymentMethod().isEmpty()
                && entity.getIssuedChequeId().isEmpty()) {
            i = 0;
            sql = "SELECT * FROM supplierPayment WHERE purchaseId=? AND paidDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getPurchaseId(), entity.getPaidDate());
        } else if (entity.getPaymentId().isEmpty() && !entity.getPurchaseId().isEmpty()
                && entity.getPaidDate().isEmpty() && !entity.getPaymentMethod().isEmpty()
                && entity.getIssuedChequeId().isEmpty()) {
            i = 0;
            sql = "SELECT * FROM supplierPayment WHERE purchaseId=? AND paymentMethod=?";
            rs = CrudOperation.executeQuery(sql, entity.getPurchaseId(), entity.getPaymentMethod());
        } else if (entity.getPaymentId().isEmpty() && !entity.getPurchaseId().isEmpty()
                && entity.getPaidDate().isEmpty() && entity.getPaymentMethod().isEmpty()
                && !entity.getIssuedChequeId().isEmpty()) {
            i = 0;
            sql = "SELECT * FROM supplierPayment WHERE purchaseId=? AND issuedChequeId=?";
            rs = CrudOperation.executeQuery(sql, entity.getPurchaseId(), entity.getIssuedChequeId());
        } else if (entity.getPaymentId().isEmpty() && entity.getPurchaseId().isEmpty()
                && !entity.getPaidDate().isEmpty() && !entity.getPaymentMethod().isEmpty()
                && entity.getIssuedChequeId().isEmpty()) {
            i = 0;
            sql = "SELECT * FROM supplierPayment WHERE paidDate=? AND paymentMethod=?";
            rs = CrudOperation.executeQuery(sql, entity.getPaidDate(), entity.getPaymentMethod());
        }
        if (i == -1) {
            sql = "SELECT * FROM supplierPayment WHERE paymentId =? OR purchaseId=? OR paidDate=? OR paymentMethod=? OR issuedChequeId=?";
            rs = CrudOperation.executeQuery(
                    sql,
                    entity.getPaymentId(),
                    entity.getPurchaseId(),
                    entity.getPaidDate(),
                    entity.getPaymentMethod(),
                    entity.getIssuedChequeId()
            );
        }
        ArrayList<SupplierPayment> supplierPayments = new ArrayList<>();

        while (rs.next()) {
            SupplierPayment temp = new SupplierPayment(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            );
            supplierPayments.add(temp);
        }
        return supplierPayments;
    }

    @Override
    public ArrayList<SupplierPayment> daySpend() throws Exception {
        String sql="SELECT SUM(paidamount),paidDate FROM supplierpayment GROUP BY paiddate";
        ResultSet rs=CrudOperation.executeQuery(sql);
        ArrayList<SupplierPayment> supplierPayments=new ArrayList<>();
        while(rs.next()){
            SupplierPayment payment=new SupplierPayment();
            payment.setPaidAmount(rs.getDouble(1));
            payment.setPaidDate(rs.getString(2));
            supplierPayments.add(payment);
        }
        return supplierPayments;
    }
}
