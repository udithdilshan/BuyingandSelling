/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buyingandselling.BO.supplier.PurchaseStockFillingBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.BatchDAO;
import lk.buyingandselling.DAO.supplier.BatchDetailDAO;
import lk.buyingandselling.DAO.supplier.PurchaseStockDAO;
import lk.buyingandselling.DAO.supplier.PurchaseStockDetailDAO;
import lk.buyingandselling.DBConnection.DBConnection;
import lk.buyingandselling.model.DTO.BatchDTO;
import lk.buyingandselling.model.DTO.BatchDetailDTO;
import lk.buyingandselling.model.DTO.PurchaseStockDTO;
import lk.buyingandselling.model.DTO.PurchaseStockDetailDTO;
import lk.buyingandselling.model.entity.Batch;
import lk.buyingandselling.model.entity.BatchDetail;
import lk.buyingandselling.model.entity.PurchaseStock;
import lk.buyingandselling.model.entity.PurchaseStockDetail;

/**
 *
 * @author SLR
 */
public class PurchaseStockFillingBOImpl implements PurchaseStockFillingBO {

    private PurchaseStockDAO purchaseStockDAO;
    private BatchDAO batchDAO;
    private BatchDetailDAO batchDetailDAO;
    private PurchaseStockDetailDAO purchaseStockDetailDAO;
    private Connection connection;

    public PurchaseStockFillingBOImpl() {
        purchaseStockDAO = (PurchaseStockDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.PURCHASESTOCK);
        batchDAO = (BatchDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.BATCH);
        batchDetailDAO = (BatchDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.BATCHDETAIL);
        purchaseStockDetailDAO = (PurchaseStockDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.PURCHASESTOCKDETAIL);
    }

    @Override
    public boolean savePurchaseStockDetails(PurchaseStockDTO purchaseStock,
            ArrayList<BatchDTO> batchDTOs,
            ArrayList<BatchDetailDTO> addBatchDetail,
            ArrayList<BatchDetailDTO> updateBatchDetail,
            ArrayList<PurchaseStockDetailDTO> purchaseStockDetailDTO) {

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean purchaseStockIsAdded = purchaseStockDAO.add(makePurchaseStock(purchaseStock));
            if (!purchaseStockIsAdded) {
                connection.rollback();
                return false;
            }
            boolean batchIsAdded = false;
            if (purchaseStockIsAdded && !batchDTOs.isEmpty()) {
                boolean batchIsAdd = true;
                ArrayList<Batch> batchs = new ArrayList<>();
                for (BatchDTO batchDTO : batchDTOs) {
                    batchs.add(makeBatch(batchDTO));
                }

                for (Batch batch : batchs) {
                    if (batchIsAdd && !batch.getBatchNo().isEmpty()) {
                        batchIsAdd = batchDAO.add(batch);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
                if (!batchIsAdd) {
                    connection.rollback();
                    return false;
                }
                batchIsAdded = true;
            }
            if (batchDTOs.isEmpty()) {
                batchIsAdded=true;
            }
            boolean addBatchDetailsIsAdded = false;
            if (batchIsAdded && !addBatchDetail.isEmpty()) {
                boolean addBatchDetailsIsAdd = true;
                ArrayList<BatchDetail> addBatchDetails = new ArrayList<>();
                for (BatchDetailDTO temp : addBatchDetail) {
                    addBatchDetails.add(makeBatchDetail(temp));
                }

                for (BatchDetail batchDetail : addBatchDetails) {
                    if (addBatchDetailsIsAdd) {
                        addBatchDetailsIsAdd = batchDetailDAO.add(batchDetail);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
                if (!addBatchDetailsIsAdd) {
                    connection.rollback();
                    return false;
                }
                addBatchDetailsIsAdded = true;
            }
            if (addBatchDetail.isEmpty()) {
                addBatchDetailsIsAdded = true;
            }
            boolean updateBatchDetailsIsAdded = false;
            if (addBatchDetailsIsAdded && !updateBatchDetail.isEmpty()) {
                boolean updateBatchDetailsIsAdd = true;
                ArrayList<BatchDetail> updateBatchDetails = new ArrayList<>();
                for (BatchDetailDTO batchDetailDTO : updateBatchDetail) {
                    updateBatchDetails.add(makeBatchDetail(batchDetailDTO));
                }
                for (BatchDetail batchDetail : updateBatchDetails) {
                    if (updateBatchDetailsIsAdd) {
                        updateBatchDetailsIsAdd = batchDetailDAO.update(batchDetail);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
                if (!updateBatchDetailsIsAdd) {
                    connection.rollback();
                    return false;
                }
                updateBatchDetailsIsAdded = true;
            }
            if (updateBatchDetail.isEmpty()) {
                updateBatchDetailsIsAdded = true;
            }
            boolean purchseStockDetailIsAdded = false;
            if (updateBatchDetailsIsAdded) {
                boolean purchseStockDetailIsAdd = true;
                ArrayList<PurchaseStockDetail> purchaseStockDetails = new ArrayList<>();
                for (PurchaseStockDetailDTO temp : purchaseStockDetailDTO) {
                    purchaseStockDetails.add(makePurchaseStockDetail(temp));
                }
                for (PurchaseStockDetail temp : purchaseStockDetails) {
                    if (purchseStockDetailIsAdd) {
                        purchseStockDetailIsAdd = purchaseStockDetailDAO.add(temp);
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
                if (!purchseStockDetailIsAdd) {
                    connection.rollback();
                    return false;
                }
                purchseStockDetailIsAdded = true;
            }
            if (purchseStockDetailIsAdded) {
                connection.commit();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(PurchaseStockFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PurchaseStockFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private PurchaseStock makePurchaseStock(PurchaseStockDTO purchaseStockDTO) {
        return new PurchaseStock(purchaseStockDTO.getPurchaseId(), purchaseStockDTO.getSupplierId(), purchaseStockDTO.getPurchaseDate());
    }

    private Batch makeBatch(BatchDTO batchDTO) {
        return new Batch(batchDTO.getBatchNo());
    }

    private BatchDetail makeBatchDetail(BatchDetailDTO batchDetail) {
        return new BatchDetail(
                batchDetail.getBatchNo(),
                batchDetail.getItemCode(),
                batchDetail.getUnitPrice(),
                batchDetail.getSellingPrice(),
                batchDetail.getQTY(),
                batchDetail.getQtyOnHand(),
                batchDetail.getEXD(),
                batchDetail.getMFD()
        );
    }

    private PurchaseStockDetail makePurchaseStockDetail(PurchaseStockDetailDTO temp) {
        return new PurchaseStockDetail(temp.getPurchaseId(), temp.getBatchNo(),temp.getQTY());
    }
}
