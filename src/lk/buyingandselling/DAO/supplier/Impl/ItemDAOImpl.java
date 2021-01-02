/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.ItemDAO;
import lk.buyingandselling.model.entity.Item;

/**
 *
 * @author SLR
 */
public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> getAllCategory() throws Exception {
        String sql = "describe item category";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<Item> allCategory = new ArrayList<>();
        while (rs.next()) {
            Item temp = new Item();
            temp.setCategory(rs.getString(2));
            allCategory.add(temp);
        }
        return allCategory;
    }

    @Override
    public boolean addNewCategory(Item entity) throws Exception {
        String sql = "ALTER TABLE item MODIFY COLUMN category ENUM(" + entity.getCategory() + ") NOT NULL";
        return CrudOperation.executeUpdate(sql);
    }

    @Override
    public boolean add(Item entity) throws Exception {
        String sql = "INSERT INTO item VALUES(?,?,?,?)";
        return CrudOperation.executeUpdate(
                sql,
                entity.getItemCode(),
                entity.getLocationId(),
                entity.getDescription(),
                entity.getCategory()
        );
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        String sql = "SELECT * FROM item";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<Item> allItems = new ArrayList<>();
        while (rs.next()) {
            Item temp = new Item(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
            allItems.add(temp);
        }
        return allItems;
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "DELETE FROM item WHERE itemCode=?";
        return CrudOperation.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Item entity) throws Exception {
        String sql = "UPDATE item SET locationId=?,description=?,category=? WHERE itemCode=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getLocationId(),
                entity.getDescription(),
                entity.getCategory(),
                entity.getItemCode()
        );
    }

    @Override
    public ArrayList<Item> search(Item entity) throws Exception {
        String sql = "SELECT * FROM item WHERE itemCode = ? OR locationId = ? OR description = ? OR Category = ?";
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rs = CrudOperation.executeQuery(sql,
                entity.getItemCode(),
                entity.getLocationId(),
                entity.getDescription(),
                entity.getCategory()
        );
        while(rs.next()){
            Item temp=new Item(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            items.add(temp);
        }
        return items;
    }

}
