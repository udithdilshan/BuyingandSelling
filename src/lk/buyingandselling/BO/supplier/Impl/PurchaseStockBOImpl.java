/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.PurchaseStockBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.PurchaseStockDAO;
import lk.buyingandselling.model.DTO.PurchaseStockDTO;
import lk.buyingandselling.model.entity.PurchaseStock;

/**
 *
 * @author SLR
 */
public class PurchaseStockBOImpl implements PurchaseStockBO {

    private PurchaseStockDAO purchaseStockDAO;

    public PurchaseStockBOImpl() {
        purchaseStockDAO = (PurchaseStockDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.PURCHASESTOCK);
    }

    @Override
    public boolean addPurchaseStock(PurchaseStockDTO purchaseStockDTO) throws Exception {
        return purchaseStockDAO.add(makePurchaseStock(purchaseStockDTO));
    }

    private PurchaseStock makePurchaseStock(PurchaseStockDTO purchaseStockDTO) {
        return new PurchaseStock(
                purchaseStockDTO.getPurchaseId(),
                purchaseStockDTO.getSupplierId(),
                purchaseStockDTO.getPurchaseDate()
        );
    }

    @Override
    public ArrayList<PurchaseStockDTO> getAllPurchaseStockDTOs() throws Exception {
        ArrayList<PurchaseStockDTO> allPurchaseStockDTOs = new ArrayList<>();
        ArrayList<PurchaseStock> allPurchaseStocks = purchaseStockDAO.getAll();
        for (PurchaseStock purchaseStock : allPurchaseStocks) {
            PurchaseStockDTO temp = new PurchaseStockDTO(
                    purchaseStock.getPurchaseId(),
                    purchaseStock.getSupplierId(),
                    purchaseStock.getPurchaseDate()
            );
            allPurchaseStockDTOs.add(temp);
        }
        return allPurchaseStockDTOs;
    }

    @Override
    public ArrayList<PurchaseStockDTO> getNoOfOrdersForDate() throws Exception {
        ArrayList<PurchaseStock> purchaseStocks = purchaseStockDAO.getNoOfOrdersForDate();
        ArrayList<PurchaseStockDTO> purchaseStockDTOs = new ArrayList<>();
        for (PurchaseStock stock : purchaseStocks) {
            PurchaseStockDTO purchaseStockDTO = new PurchaseStockDTO();
            purchaseStockDTO.setPurchaseId(stock.getPurchaseId());
            purchaseStockDTO.setPurchaseDate(stock.getPurchaseDate());
            purchaseStockDTOs.add(purchaseStockDTO);
        }
        return purchaseStockDTOs;
    }

}
