/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.BatchDetailDAO;
import lk.buyingandselling.model.entity.BatchDetail;

/**
 *
 * @author SLR
 */
public class BatchDetailDAOImpl implements BatchDetailDAO {

    @Override
    public double qtyOnHandForItem(BatchDetail entity) throws Exception {
        String sql = "SELECT SUM(qtyOnHand) FROM batchDetail WHERE itemCode=? AND qtyOnHand>0";
        ResultSet rs = CrudOperation.executeQuery(sql, entity.getItemCode());
        while (rs.next()) {
            return rs.getDouble(1);
        }
        return 0;
    }

    @Override
    public boolean add(BatchDetail entity) throws Exception {
        String sql = "INSERT INTO batchdetail VALUES(?,?,?,?,?,?,?,?)";
        return CrudOperation.executeUpdate(
                sql,
                entity.getBatchNo(),
                entity.getItemCode(),
                entity.getUnitPrice(),
                entity.getSellingPrice(),
                entity.getQTY(),
                entity.getQtyOnHand(),
                entity.getEXD(),
                entity.getMFD()
        );
    }

    @Override
    public ArrayList<BatchDetail> getAll() throws Exception {
        String sql = "SELECT * FROM batchDetail";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<BatchDetail> allBatchDetails = new ArrayList<>();
        while (rs.next()) {
            BatchDetail batchDetail = new BatchDetail(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getString(7),
                    rs.getString(8)
            );
            allBatchDetails.add(batchDetail);
        }
        return allBatchDetails;
    }

    @Override
    public boolean update(BatchDetail entity) throws Exception {
        String sql = "UPDATE batchdetail SET qty=?,qtyOnHand=? ,EXP=?,MFD=? WHERE batchNo=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getQTY(),
                entity.getQtyOnHand(),
                entity.getEXD(),
                entity.getMFD(),
                entity.getBatchNo()
        );
    }

    @Override
    public ArrayList<BatchDetail> search(BatchDetail entity) throws Exception {
        String sql = "SELECT * FROM batchDetail WHERE batchNo=? OR itemCode=?";
        ResultSet rs = CrudOperation.executeQuery(sql, entity.getBatchNo(), entity.getItemCode());
        ArrayList<BatchDetail> search = new ArrayList<>();
        while (rs.next()) {
            BatchDetail batchDetail = new BatchDetail(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getString(7),
                    rs.getString(8)
            );
            search.add(batchDetail);
        }
        return search;
    }

    @Override
    public boolean delete(BatchDetail entity) throws Exception {
        String sql = "DELETE FROM batchdetail WHERE batchNo=? AND itemCode=?";
        return CrudOperation.executeUpdate(sql, entity.getBatchNo(), entity.getItemCode());
    }

    @Override
    public ArrayList<BatchDetail> qtyOnHandForBatch(BatchDetail batchDetail) throws Exception {
        String sql = "SELECT QTY, qtyOnHand FROM batchDetail WHERE batchNo=? ";
        ResultSet rs = CrudOperation.executeQuery(sql, batchDetail.getBatchNo());
        ArrayList<BatchDetail> batch = new ArrayList<>();
        while (rs.next()) {
            BatchDetail temp = new BatchDetail();
            temp.setQTY(rs.getDouble(1));
            temp.setQtyOnHand(rs.getDouble(2));
            batch.add(temp);
        }
        return batch;
    }

    @Override
    public ArrayList<BatchDetail> availableBatchDetails(BatchDetail batchDetail) throws Exception {
        String sql = "SELECT batchNo,qtyOnHand,sellingPrice,MFD,EXP FROM batchDetail WHERE itemCode=? AND qtyOnHand>0";
        ResultSet rs = CrudOperation.executeQuery(sql, batchDetail.getItemCode());
        ArrayList<BatchDetail> batch = new ArrayList<>();
        while (rs.next()) {
            BatchDetail temp = new BatchDetail();
            temp.setBatchNo(rs.getString(1));
            temp.setQtyOnHand(rs.getDouble(2));
            temp.setSellingPrice(rs.getDouble(3));
            temp.setMFD(rs.getString(4));
            temp.setEXD(rs.getString(5));
            batch.add(temp);
        }
        return batch;
    }

    @Override
    public boolean updateQTYOnHand(BatchDetail batchDetail) throws Exception {
        String sql = "UPDATE batchdetail,"
                + "(SELECT qtyOnHand as qtyNow FROM batchdetail WHERE batchNo=? AND itemCode=?) as QTYInStock"
                + " SET batchdetail.qtyOnHand=(QTYInStock.qtyNow-?) WHERE batchdetail.batchNo=? AND batchdetail.itemCode=?";
        return CrudOperation.executeUpdate(
                sql,
                batchDetail.getBatchNo(),
                batchDetail.getItemCode(),
                batchDetail.getQTY(),
                batchDetail.getBatchNo(),
                batchDetail.getItemCode()
        );
    }

}
