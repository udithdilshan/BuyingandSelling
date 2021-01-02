/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.IssuedChequedBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.model.DTO.IssuedChequeDTO;
import lk.buyingandselling.model.entity.IssuedCheque;
import lk.buyingandselling.DAO.supplier.IssuedChequeDAO;

/**
 *
 * @author SLR
 */
public class IssuedChequeBOImpl implements IssuedChequedBO {

    private IssuedChequeDAO issueChequeDAO;

    public IssuedChequeBOImpl() {
        issueChequeDAO = (IssuedChequeDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.ISSUEDCHEQUE);
    }

    @Override
    public boolean addIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception {
        return issueChequeDAO.add(makeIssuedCheque(issuedChequeDTO));
    }

    @Override
    public boolean updateIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception {
        return issueChequeDAO.update(makeIssuedCheque(issuedChequeDTO));
    }

    @Override
    public ArrayList<IssuedChequeDTO> searchIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception {
        ArrayList<IssuedCheque> issuedCheques=issueChequeDAO.search(makeIssuedCheque(issuedChequeDTO));
        ArrayList<IssuedChequeDTO> issuedChequeDTOs=new ArrayList<>();
        for(IssuedCheque temp: issuedCheques){
            IssuedChequeDTO issuedCheque=new IssuedChequeDTO(
                    temp.getIssuedChequeId(), 
                    temp.getBankName(),
                    temp.getAmount(), 
                    temp.getStatus(), 
                    temp.getIssuedDate()
            );
            issuedChequeDTOs.add(issuedCheque);
        }
        return issuedChequeDTOs;
    }

    @Override
    public boolean deleteIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception {
        return issueChequeDAO.delete(makeIssuedCheque(issuedChequeDTO));
    }

    private IssuedCheque makeIssuedCheque(IssuedChequeDTO issuedChequeDTO) {
        return new IssuedCheque(
                issuedChequeDTO.getIssuedChequeId(),
                issuedChequeDTO.getBankName(),
                issuedChequeDTO.getAmount(),
                issuedChequeDTO.getStatus(),
                issuedChequeDTO.getIssuedDate()
        );
    }
}
