/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.BatchDAO;
import lk.buyingandselling.model.entity.Batch;

/**
 *
 * @author SLR
 */
public class BatchDAOImpl implements BatchDAO{

    @Override
    public boolean add(Batch entity) throws Exception {
        String sql="INSERT INTO batch VALUES(?)";
        return CrudOperation.executeUpdate(sql, entity.getBatchNo());
    }

    @Override
    public ArrayList<Batch> getAll() throws Exception {
        String sql="SELECT * FROM batch";
        ResultSet rs=CrudOperation.executeQuery(sql);
        ArrayList<Batch> allBatch=new ArrayList<>();
        while(rs.next()){
            Batch temp= new Batch(rs.getString(1));
            allBatch.add(temp);
        }
        return allBatch;
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql= "DELETE FROM batch WHERE batchNo=?";
        return CrudOperation.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Batch entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Batch> search(Batch entity) throws Exception {
        String sql="SELECT * FROM batch WHERE batchNo LIKE ?";
        ResultSet rs=CrudOperation.executeQuery(sql, "%"+entity.getBatchNo()+"%");
        ArrayList<Batch> search=new ArrayList<>();
        while(rs.next()){
            Batch temp=new Batch(rs.getString(0));
            search.add(temp);
        }
        return search;
    }
    
}
