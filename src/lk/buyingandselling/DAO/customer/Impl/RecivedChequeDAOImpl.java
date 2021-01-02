/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.customer.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.customer.RecivedChequeDAO;
import lk.buyingandselling.model.entity.RecivedCheque;

/**
 *
 * @author SLR
 */
public class RecivedChequeDAOImpl implements RecivedChequeDAO {

    @Override
    public boolean add(RecivedCheque entity) throws Exception {
        String sql = "INSERT INTO recivedCheque VALUES(?,?,?,?,?,?)";
        return CrudOperation.executeUpdate(
                sql,
                entity.getRecivedChaqueId(),
                entity.getBankName(),
                entity.getStatus(),
                entity.getAmount(),
                entity.getRealizationDate(),
                entity.getRecivedDate()
        );
    }

    @Override
    public ArrayList<RecivedCheque> getAll() throws Exception {
        String sql="SELECT * FROM recivedCheque";
        ResultSet rs=CrudOperation.executeQuery(sql);
        ArrayList<RecivedCheque> recivedCheques=new ArrayList<>();
        while(rs.next()){
            RecivedCheque cheque=new RecivedCheque(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            recivedCheques.add(cheque);
        }
        return recivedCheques;
    }

    @Override
    public boolean delete(RecivedCheque id) throws Exception {
        String sql = "DELETE FROM recivedCheque WEHERE recivedChequeId=?";
        return CrudOperation.executeUpdate(sql, id.getRecivedChaqueId());
    }

    @Override
    public boolean update(RecivedCheque entity) throws Exception {
        String sql = "UPDATE recivedCheque SET bankName=?,status=?,amount =?,realizationDate=?,recivedDate=? WHERE recivedChequeId=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getBankName(),
                entity.getStatus(),
                entity.getAmount(),
                entity.getRealizationDate(),
                entity.getRecivedDate(),
                entity.getRecivedChaqueId()
        );

    }

    @Override
    public ArrayList<RecivedCheque> search(RecivedCheque entity) throws Exception {
        String sql;
        int i = -1;
        ResultSet rs = null;
        if (entity.getRecivedChaqueId().isEmpty() && entity.getBankName().isEmpty() && !entity.getRealizationDate().isEmpty()) {
            sql = "SELECT * FROM recivedCheque WHERE realizationDate=? ";
            i = 0;
            rs = CrudOperation.executeQuery(sql, entity.getRealizationDate());
        } else if (entity.getRecivedChaqueId().isEmpty() && !entity.getBankName().isEmpty() && entity.getRealizationDate().isEmpty()) {
            sql = "SELECT * FROM recivedCheque WHERE bankName=?";
            rs = CrudOperation.executeQuery(sql, entity.getBankName());
            i = 0;
        } else if (entity.getRecivedChaqueId().isEmpty() && !entity.getBankName().isEmpty() && !entity.getRealizationDate().isEmpty()) {
            sql = "SELECT * FROM recivedCheque WHERE bankName=? AND realizationDate=?";
            i = 0;
            rs = CrudOperation.executeQuery(sql, entity.getBankName(), entity.getRealizationDate());
        }
        if (i == -1) {
            sql = "SELECT * FROM recivedCheque WHERE recivedChequeId=? OR bankName=? OR realizationDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getRecivedChaqueId(), entity.getBankName(), entity.getRealizationDate());
        }
        ArrayList<RecivedCheque> recivedCheques = new ArrayList<>();
        while (rs.next()) {
            RecivedCheque recivedCheque = new RecivedCheque(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            recivedCheques.add(recivedCheque);
        }
        return recivedCheques;
    }

}
