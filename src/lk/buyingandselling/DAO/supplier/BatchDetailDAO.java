/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudDAO;
import lk.buyingandselling.model.entity.BatchDetail;

/**
 *
 * @author SLR
 */
public interface BatchDetailDAO extends CrudDAO<BatchDetail, BatchDetail> {

    public ArrayList<BatchDetail> qtyOnHandForBatch(BatchDetail batchDetail) throws Exception;

    public double qtyOnHandForItem(BatchDetail batchDetail) throws Exception;
    
    public ArrayList<BatchDetail> availableBatchDetails(BatchDetail batchDetail) throws Exception;
    
    public boolean updateQTYOnHand(BatchDetail batchDetail) throws Exception;
}
