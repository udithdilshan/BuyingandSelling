/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.customer.RecivedChequeBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.RecivedChequeDAO;
import lk.buyingandselling.model.DTO.RecivedChequeDTO;
import lk.buyingandselling.model.entity.RecivedCheque;

/**
 *
 * @author SLR
 */
public class RecivedChequeBOImpl implements RecivedChequeBO{
    
    private RecivedChequeDAO recivedChequeDAO;
    
    public RecivedChequeBOImpl() {
        recivedChequeDAO=(RecivedChequeDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.RECIVEDCHEQUE);
    }

    @Override
    public boolean addRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception {
        return recivedChequeDAO.add(makeRecivedCheque(recivedChequeDTO));
    }

    @Override
    public boolean deleteRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception {
        return recivedChequeDAO.delete(makeRecivedCheque(recivedChequeDTO));
    }

    @Override
    public boolean updateRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception {
        return recivedChequeDAO.update(makeRecivedCheque(recivedChequeDTO));
    }

    @Override
    public ArrayList<RecivedChequeDTO> getAll() throws Exception {
        ArrayList<RecivedCheque> recivedCheques=recivedChequeDAO.getAll();
        ArrayList<RecivedChequeDTO> recivedChequeDTOs=new ArrayList<>();
        for(RecivedCheque temp: recivedCheques){
            RecivedChequeDTO chequeDTO=new RecivedChequeDTO(
                    temp.getRecivedChaqueId(),
                    temp.getBankName(),
                    temp.getStatus(),
                    temp.getAmount(),
                    temp.getRealizationDate(), 
                    temp.getRecivedDate()
            );
            recivedChequeDTOs.add(chequeDTO);
        }
        return recivedChequeDTOs;
    }

    @Override
    public ArrayList<RecivedChequeDTO> searchRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception {
         ArrayList<RecivedCheque> recivedCheques=recivedChequeDAO.getAll();
        ArrayList<RecivedChequeDTO> recivedChequeDTOs=new ArrayList<>();
        for(RecivedCheque temp: recivedCheques){
            RecivedChequeDTO chequeDTO=new RecivedChequeDTO(
                    temp.getRecivedChaqueId(),
                    temp.getBankName(),
                    temp.getStatus(),
                    temp.getAmount(),
                    temp.getRealizationDate(), 
                    temp.getRecivedDate()
            );
            recivedChequeDTOs.add(chequeDTO);
        }
        return recivedChequeDTOs;
    }
    
    private RecivedCheque makeRecivedCheque(RecivedChequeDTO recivedChequeDTO){
       return new RecivedCheque(
               recivedChequeDTO.getRecivedChaqueId(),
               recivedChequeDTO.getBankName(), 
               recivedChequeDTO.getStatus(), 
               recivedChequeDTO.getAmount(), 
               recivedChequeDTO.getRealizationDate(),
               recivedChequeDTO.getRecivedDate()
       );
    }
}
