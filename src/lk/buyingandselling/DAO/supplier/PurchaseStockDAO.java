/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudDAO;
import lk.buyingandselling.model.entity.PurchaseStock;

/**
 *
 * @author SLR
 */
public interface PurchaseStockDAO extends CrudDAO<PurchaseStock, String> {

    public ArrayList<PurchaseStock> getNoOfOrdersForDate() throws Exception;
}
