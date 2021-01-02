/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.BatchBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.BatchDAO;
import lk.buyingandselling.model.DTO.BatchDTO;
import lk.buyingandselling.model.entity.Batch;

/**
 *
 * @author SLR
 */
public class BatchBOImpl implements BatchBO {

    private BatchDAO batchDAO;

    public BatchBOImpl() {
        batchDAO = (BatchDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.BATCH);
    }

    @Override
    public boolean addBatch(BatchDTO batchDTO) throws Exception {
        return batchDAO.add(makeBatchEntity(batchDTO));
    }

    private Batch makeBatchEntity(BatchDTO batchDTO) {
        return new Batch(batchDTO.getBatchNo());

    }

    @Override
    public boolean deleteBatch(BatchDTO batchDTO) throws Exception {
        return batchDAO.delete(batchDTO.getBatchNo());
    }

    @Override
    public ArrayList<BatchDTO> searchBatch(String batchNo) throws Exception {
        ArrayList<Batch> search = batchDAO.search(makeBatchEntity(new BatchDTO(batchNo)));
        ArrayList<BatchDTO> result = new ArrayList<>();
        for (Batch temp : search) {
            BatchDTO t = new BatchDTO(temp.getBatchNo());
            result.add(t);
        }
        return result;
    }

}
