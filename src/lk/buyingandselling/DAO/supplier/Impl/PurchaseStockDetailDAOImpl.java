/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.PurchaseStockDetailDAO;
import lk.buyingandselling.model.entity.PurchaseStockDetail;

/**
 *
 * @author SLR
 */
public class PurchaseStockDetailDAOImpl implements PurchaseStockDetailDAO {

    @Override
    public boolean add(PurchaseStockDetail entity) throws Exception {
        String sql = "INSERT INTO purchaseStockDetail VALUES(?,?,?)";
        return CrudOperation.executeUpdate(sql, entity.getPurchaseId(), entity.getBatchNo(),entity.getQTY());
    }

    @Override
    public ArrayList<PurchaseStockDetail> getAll() throws Exception {
        String sql = "SELECT * FROM purchseStockDetail";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<PurchaseStockDetail> allPurchaseStockDetails = new ArrayList<>();
        while (rs.next()) {
            PurchaseStockDetail temp = new PurchaseStockDetail(rs.getString(1), rs.getString(2),rs.getDouble(3));
            allPurchaseStockDetails.add(temp);
        }
        return allPurchaseStockDetails;
    }

    @Override
    public boolean delete(PurchaseStockDetail id) throws Exception {
        String sql = "DELETE FROM purchaseStockDetail WHERE purchaseId=? AND batchNo=?";
        return CrudOperation.executeUpdate(sql);
    }

    @Override
    public boolean update(PurchaseStockDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PurchaseStockDetail> search(PurchaseStockDetail entity) throws Exception {
        String sql;
        ResultSet rs;
        if (entity.getPurchaseId().isEmpty()) {
            sql = "SELECT * FROM purchaseStockDetail WHERE batchNo=?";
            rs = CrudOperation.executeQuery(sql, entity.getBatchNo());

        } else if (entity.getBatchNo().isEmpty()) {
            sql = "SELECT * FROM purchaseStockDetail WHERE purchaseId=?";
            rs = CrudOperation.executeQuery(sql, entity.getPurchaseId());
        } else {
            sql = "SELECT * FROM purchaseStockDetail WHERE purchaseId=? AND batchNo=?";
            rs = CrudOperation.executeQuery(sql, entity.getPurchaseId(), entity.getBatchNo());
        }
        ArrayList<PurchaseStockDetail> search = new ArrayList<>();
        while (rs.next()) {
            PurchaseStockDetail temp = new PurchaseStockDetail(rs.getString(1), rs.getString(2),rs.getDouble(3));
            search.add(temp);
        }
        return search;
    }

}
