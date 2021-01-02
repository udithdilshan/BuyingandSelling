/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.PurchaseStockDetailDTO;

/**
 *
 * @author SLR
 */
public interface PurchaseStockDetailBO extends SuperBO {

    public boolean addPurchaseStockDetails(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception;

    public boolean deletePuchaseStockDetail(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception;

    public boolean updatePurchaseStockDetail(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception;

    public ArrayList<PurchaseStockDetailDTO> getAllPurchaseStockDetails() throws Exception;

    public ArrayList<PurchaseStockDetailDTO> searchPurchaseStockDetails(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception;
}
