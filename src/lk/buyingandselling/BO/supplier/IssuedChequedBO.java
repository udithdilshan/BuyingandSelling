/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.IssuedChequeDTO;

/**
 *
 * @author SLR
 */
public interface IssuedChequedBO extends SuperBO {

    public boolean addIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception;

    public boolean deleteIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception;

    public boolean updateIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception;

    public ArrayList<IssuedChequeDTO> searchIssuedCheque(IssuedChequeDTO issuedChequeDTO) throws Exception;
}
