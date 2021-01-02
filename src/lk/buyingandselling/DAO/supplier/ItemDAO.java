/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudDAO;
import lk.buyingandselling.model.entity.Item;

/**
 *
 * @author SLR
 */
public interface ItemDAO extends CrudDAO<Item, String> {

    public ArrayList<Item> getAllCategory() throws Exception;

    public boolean addNewCategory(Item entity) throws Exception;
}
