/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.PurchaseStockDetailBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.PurchaseStockDetailDAO;
import lk.buyingandselling.model.DTO.PurchaseStockDetailDTO;
import lk.buyingandselling.model.entity.PurchaseStockDetail;

/**
 *
 * @author SLR
 */
public class PurchaseStockDetailBOImpl implements PurchaseStockDetailBO {

    private PurchaseStockDetailDAO purchaseStockDetailDAO;

    public PurchaseStockDetailBOImpl() {
        purchaseStockDetailDAO = (PurchaseStockDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.PURCHASESTOCKDETAIL);
    }

    @Override
    public boolean addPurchaseStockDetails(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception {
        return purchaseStockDetailDAO.add(makePurchaseStockDetail(purchaseStockDetailDTO));
    }

    @Override
    public boolean deletePuchaseStockDetail(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePurchaseStockDetail(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PurchaseStockDetailDTO> getAllPurchaseStockDetails() throws Exception {
        ArrayList<PurchaseStockDetail> allPurchaseStockDetails = purchaseStockDetailDAO.getAll();
        ArrayList<PurchaseStockDetailDTO> allPurchaseStockDetailDTOs = new ArrayList<>();
        for (PurchaseStockDetail temp : allPurchaseStockDetails) {
            PurchaseStockDetailDTO t = new PurchaseStockDetailDTO(temp.getPurchaseId(), temp.getBatchNo(),temp.getQTY());
            allPurchaseStockDetailDTOs.add(t);
        }
        return allPurchaseStockDetailDTOs;
    }

    @Override
    public ArrayList<PurchaseStockDetailDTO> searchPurchaseStockDetails(PurchaseStockDetailDTO purchaseStockDetailDTO) throws Exception {
         ArrayList<PurchaseStockDetail> allPurchaseStockDetails = purchaseStockDetailDAO.search(makePurchaseStockDetail(purchaseStockDetailDTO));
        ArrayList<PurchaseStockDetailDTO> allPurchaseStockDetailDTOs = new ArrayList<>();
        for (PurchaseStockDetail temp : allPurchaseStockDetails) {
            PurchaseStockDetailDTO t = new PurchaseStockDetailDTO(temp.getPurchaseId(), temp.getBatchNo(),temp.getQTY());
            allPurchaseStockDetailDTOs.add(t);
        }
        return allPurchaseStockDetailDTOs;
    }

    private PurchaseStockDetail makePurchaseStockDetail(PurchaseStockDetailDTO purchaseStockDetailDTO) {
        PurchaseStockDetail detail = new PurchaseStockDetail(
                purchaseStockDetailDTO.getPurchaseId(), 
                purchaseStockDetailDTO.getBatchNo(),
                purchaseStockDetailDTO.getQTY()
        );
        return detail;
    }
}
