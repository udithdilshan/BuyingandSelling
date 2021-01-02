/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.BatchDTO;

/**
 *
 * @author SLR
 */
public interface BatchBO extends SuperBO{
    
    public boolean addBatch(BatchDTO batchDTO)throws Exception;
    
    public boolean deleteBatch(BatchDTO batchDTO) throws Exception;
    
    public ArrayList<BatchDTO> searchBatch(String batchNo) throws Exception;
    
}
