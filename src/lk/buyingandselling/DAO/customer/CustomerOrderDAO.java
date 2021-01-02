/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.customer;

import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudDAO;
import lk.buyingandselling.model.entity.CustomerOrder;

/**
 *
 * @author SLR
 */
public interface CustomerOrderDAO extends CrudDAO<CustomerOrder, CustomerOrder> {

    public ArrayList<CustomerOrder> getNoOfOrders() throws Exception;
}
