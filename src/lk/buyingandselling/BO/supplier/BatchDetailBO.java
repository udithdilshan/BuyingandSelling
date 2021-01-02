/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.BatchDetailDTO;

/**
 *
 * @author SLR
 */
public interface BatchDetailBO extends SuperBO {

    public boolean addBatchDetail(BatchDetailDTO batchDetailDTO) throws Exception;

    public boolean deleteBatchDetail(BatchDetailDTO batchDetailDTO) throws Exception;

    public boolean updateBatchDetail(BatchDetailDTO batchDetailDTO) throws Exception;

    public ArrayList<BatchDetailDTO> getAllBatchDetails() throws Exception;

    public ArrayList<BatchDetailDTO> searchBatchDetails(BatchDetailDTO batchDetailDTO) throws Exception;

    public double qtyOnHandForItem(BatchDetailDTO batchDetailDTO)throws Exception;
    
    public ArrayList<BatchDetailDTO> qtyOnHandForBatchNo(BatchDetailDTO batchDetailDTO)throws Exception;
    
    public ArrayList<BatchDetailDTO> availableBatchDetails(BatchDetailDTO batchDetailDTO) throws Exception;
}
