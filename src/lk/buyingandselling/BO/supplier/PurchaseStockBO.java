/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.PurchaseStockDTO;

/**
 *
 * @author SLR
 */
public interface PurchaseStockBO extends SuperBO{
        
        public boolean addPurchaseStock(PurchaseStockDTO purchaseStockDTO) throws Exception;

        public ArrayList<PurchaseStockDTO> getAllPurchaseStockDTOs() throws Exception;
        
        public ArrayList<PurchaseStockDTO> getNoOfOrdersForDate()throws Exception;
}
