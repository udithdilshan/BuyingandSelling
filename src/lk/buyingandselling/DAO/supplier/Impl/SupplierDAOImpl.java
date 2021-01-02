/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.SupplierDAO;
import lk.buyingandselling.model.entity.Supplier;

/**
 *
 * @author SLR
 */
public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean add(Supplier supplier) throws Exception {
        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        return CrudOperation.executeUpdate(sql,
                supplier.getSupplierId(),
                supplier.getFirstname(),
                supplier.getLastname(),
                supplier.getAddress(),
                supplier.getCompanyName(),
                supplier.getNIC(),
                supplier.getGender(),
                supplier.getCity(),
                supplier.getMobileNumber(),
                supplier.getEmail(),
                supplier.getPostalCode(),
                supplier.getAddedDate()
        );
    }

    @Override
    public ArrayList<Supplier> getAll() throws Exception {
        String sql = "SELECT * FROM supplier";
        ResultSet rst = CrudOperation.executeQuery(sql);
        ArrayList<Supplier> allSuppliers = new ArrayList<>();
        while (rst.next()) {
            allSuppliers.add(new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12))
            );
        }
        return allSuppliers;
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "DELETE FROM supplier WHERE supplierId=?";
        return CrudOperation.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Supplier entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Supplier> search(Supplier entity) throws Exception {
        String sql = "SELECT * FROM supplier WHERE supplierID LIKE ? OR firstname LIKE ? OR  lastname LIKE ? "
                + "OR companyname LIKE ? OR nic LIKE ? OR gender LIKE ? OR city LIKE ? ";
        ResultSet rs = CrudOperation.executeQuery(
                sql,
                entity.getSupplierId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getCompanyName(),
                entity.getNIC(),
                entity.getGender(),
                entity.getCity()
        );
        ArrayList<Supplier> result = new ArrayList<>();
        while (rs.next()) {
            Supplier temp = new Supplier(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getInt(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12)
            );
            result.add(temp);
        }
        return result;
    }
}
