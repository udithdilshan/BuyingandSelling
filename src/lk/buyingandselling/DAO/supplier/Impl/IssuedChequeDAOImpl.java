/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.model.entity.IssuedCheque;
import lk.buyingandselling.DAO.supplier.IssuedChequeDAO;

/**
 *
 * @author SLR
 */
public class IssuedChequeDAOImpl implements IssuedChequeDAO {

    @Override
    public boolean add(IssuedCheque entity) throws Exception {
        String sql = "INSERT INTO issuedCheque VALUES(?,?,?,?,?)";
        return CrudOperation.executeUpdate(sql, entity.getIssuedChequeId(), entity.getBankName(), entity.getAmount(), entity.getStatus(), entity.getIssuedDate());
    }

    @Override
    public ArrayList<IssuedCheque> getAll() throws Exception {
        String sql = "SELECT * FROM issuedCheque";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<IssuedCheque> allIssuedCheques = new ArrayList<>();
        while (rs.next()) {
            IssuedCheque temp = new IssuedCheque(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            allIssuedCheques.add(temp);
        }
        return allIssuedCheques;
    }

    @Override
    public boolean delete(IssuedCheque id) throws Exception {
        String sql = "DELETE FROM issuedCheque WHERE issuedChequeId=?";
        return CrudOperation.executeUpdate(sql, id.getIssuedChequeId());
    }

    @Override
    public boolean update(IssuedCheque entity) throws Exception {
        String sql = "UPDATE issuedCheque SET bankName=?,amount=?,status=?,issuedDate=? WHERE issuedChequeId=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getBankName(),
                entity.getAmount(),
                entity.getStatus(),
                entity.getIssuedDate(),
                entity.getIssuedChequeId()
        );
    }

    @Override
    public ArrayList<IssuedCheque> search(IssuedCheque entity) throws Exception {
        ResultSet rs = null;
        int i = -1;
        String sql = "SELECT * FROM issuedCheque WHERE issuedChequeId=? OR issuedDate=? OR bankName=?";
        if (entity.getIssuedChequeId().isEmpty() && !entity.getBankName().isEmpty() && !entity.getIssuedDate().isEmpty()) {
            i = 0;
            sql = "SELECT * FROM issuedCheque WHERE  issuedDate=? AND bankName=?";
            rs = CrudOperation.executeQuery(sql, entity.getIssuedDate(), entity.getBankName());
        }
        ArrayList<IssuedCheque> issuedCheques = new ArrayList<>();
        if (i == -1) {
            rs = CrudOperation.executeQuery(sql, entity.getIssuedChequeId(), entity.getIssuedDate(), entity.getBankName());
        }
        while (rs.next()) {
            IssuedCheque temp = new IssuedCheque(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5));
            issuedCheques.add(temp);
        }
        return issuedCheques;
    }

}
