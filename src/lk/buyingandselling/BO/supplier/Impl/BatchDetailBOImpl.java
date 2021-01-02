/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.BatchDetailBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.BatchDetailDAO;
import lk.buyingandselling.model.DTO.BatchDetailDTO;
import lk.buyingandselling.model.entity.BatchDetail;

/**
 *
 * @author SLR
 */
public class BatchDetailBOImpl implements BatchDetailBO {

    private BatchDetailDAO batchDetailDAO;

    public BatchDetailBOImpl() {
        batchDetailDAO = (BatchDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.BATCHDETAIL);
    }

    @Override
    public boolean addBatchDetail(BatchDetailDTO batchDetailDTO) throws Exception {
        return batchDetailDAO.add(makeBatchDetail(batchDetailDTO));
    }

    @Override
    public boolean deleteBatchDetail(BatchDetailDTO batchDetailDTO) throws Exception {
        return batchDetailDAO.delete(makeBatchDetail(batchDetailDTO));
    }

    @Override
    public boolean updateBatchDetail(BatchDetailDTO batchDetailDTO) throws Exception {
        return batchDetailDAO.update(makeBatchDetail(batchDetailDTO));
    }

    @Override
    public ArrayList<BatchDetailDTO> getAllBatchDetails() throws Exception {
        ArrayList<BatchDetail> batchDetails = new ArrayList<>();
        ArrayList<BatchDetailDTO> batchDetailDTOs = new ArrayList<>();
        for (BatchDetail batchDetail : batchDetails) {
            BatchDetailDTO batchDetailDTO = new BatchDetailDTO(
                    batchDetail.getBatchNo(),
                    batchDetail.getItemCode(),
                    batchDetail.getUnitPrice(),
                    batchDetail.getSellingPrice(),
                    batchDetail.getQTY(),
                    batchDetail.getQtyOnHand(),
                    batchDetail.getEXD(),
                    batchDetail.getMFD()
            );
            batchDetailDTOs.add(batchDetailDTO);
        }
        return batchDetailDTOs;
    }

    @Override
    public double qtyOnHandForItem(BatchDetailDTO batchDetailDTO) throws Exception {
        return batchDetailDAO.qtyOnHandForItem(makeBatchDetail(batchDetailDTO));
    }

    private BatchDetail makeBatchDetail(BatchDetailDTO batchDetailDTO) {
        BatchDetail batchDetail = new BatchDetail(
                batchDetailDTO.getBatchNo(),
                batchDetailDTO.getItemCode(),
                batchDetailDTO.getUnitPrice(),
                batchDetailDTO.getSellingPrice(),
                batchDetailDTO.getQTY(),
                batchDetailDTO.getQtyOnHand(),
                batchDetailDTO.getEXD(),
                batchDetailDTO.getMFD()
        );
        return batchDetail;
    }

    @Override
    public ArrayList<BatchDetailDTO> searchBatchDetails(BatchDetailDTO batchDetailDTO) throws Exception {
        ArrayList<BatchDetail> batchDetails = batchDetailDAO.search(makeBatchDetail(batchDetailDTO));
        ArrayList<BatchDetailDTO> batchDetailDTOs = new ArrayList<>();
        for (BatchDetail batchDetail : batchDetails) {
            BatchDetailDTO temp = new BatchDetailDTO(
                    batchDetail.getBatchNo(),
                    batchDetail.getItemCode(),
                    batchDetail.getUnitPrice(),
                    batchDetail.getSellingPrice(),
                    batchDetail.getQTY(),
                    batchDetail.getQtyOnHand(),
                    batchDetail.getEXD(),
                    batchDetail.getMFD()
            );
            batchDetailDTOs.add(temp);
        }
        return batchDetailDTOs;
    }

    @Override
    public ArrayList<BatchDetailDTO> qtyOnHandForBatchNo(BatchDetailDTO batchDetailDTO) throws Exception {
        ArrayList<BatchDetail> batch= batchDetailDAO.qtyOnHandForBatch(makeBatchDetail(batchDetailDTO));
        ArrayList<BatchDetailDTO> batchDetailDTOs=new ArrayList<>();
        for(BatchDetail temp: batch){
            BatchDetailDTO batchDetail=new BatchDetailDTO();
            batchDetail.setQTY(temp.getQTY());
            batchDetail.setQtyOnHand(temp.getQtyOnHand());
            batchDetailDTOs.add(batchDetail);
        }
        return batchDetailDTOs;
    }

    @Override
    public ArrayList<BatchDetailDTO> availableBatchDetails(BatchDetailDTO batchDetailDTO) throws Exception {
        ArrayList<BatchDetail> batchs=batchDetailDAO.availableBatchDetails(makeBatchDetail(batchDetailDTO));
        ArrayList<BatchDetailDTO> batchDetailDTOs=new ArrayList<>();
        for(BatchDetail temp: batchs){
            BatchDetailDTO batchDetail=new BatchDetailDTO();
            batchDetail.setBatchNo(temp.getBatchNo());
            batchDetail.setQtyOnHand(temp.getQtyOnHand());
            batchDetail.setSellingPrice(temp.getSellingPrice());
            batchDetail.setEXD(temp.getEXD());
            batchDetail.setMFD(temp.getMFD());
            batchDetailDTOs.add(batchDetail);
        }
        return batchDetailDTOs;
    }
}
