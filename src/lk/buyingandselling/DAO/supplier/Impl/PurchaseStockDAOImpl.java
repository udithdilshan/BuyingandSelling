/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.PurchaseStockDAO;
import lk.buyingandselling.model.entity.PurchaseStock;

/**
 *
 * @author SLR
 */
public class PurchaseStockDAOImpl implements PurchaseStockDAO {

    @Override
    public boolean add(PurchaseStock entity) throws Exception {
        String sql = "INSERT INTO purchaseStock VALUES (?,?,?)";
        return CrudOperation.executeUpdate(sql, entity.getPurchaseId(), entity.getSupplierId(), entity.getPurchaseDate());
    }

    @Override
    public ArrayList<PurchaseStock> getAll() throws Exception {
        String sql = "SELECT * FROM purchaseStock";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<PurchaseStock> purchaseStocks = new ArrayList<>();
        while (rs.next()) {
            PurchaseStock temp = new PurchaseStock(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
            );
            purchaseStocks.add(temp);
        }
        return purchaseStocks;
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(PurchaseStock entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PurchaseStock> search(PurchaseStock entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PurchaseStock> getNoOfOrdersForDate() throws Exception {
        String sql = "SELECT COUNT(purchaseId), purchaseDate FROM purchaseStock GROUP BY purchaseDate";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<PurchaseStock> purchaseStock = new ArrayList<>();
        while (rs.next()) {
            PurchaseStock purchase = new PurchaseStock();
            purchase.setPurchaseId(rs.getString(1));
            purchase.setPurchaseDate(rs.getString(2));
            purchaseStock.add(purchase);
        }
        return purchaseStock;
    }

}
