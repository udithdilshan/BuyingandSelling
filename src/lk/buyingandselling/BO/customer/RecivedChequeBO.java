/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.RecivedChequeDTO;

/**
 *
 * @author SLR
 */
public interface RecivedChequeBO extends SuperBO {

    public boolean addRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception;

    public boolean deleteRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception;

    public boolean updateRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception;

    public ArrayList<RecivedChequeDTO> getAll() throws Exception;

    public ArrayList<RecivedChequeDTO> searchRecivedCheque(RecivedChequeDTO recivedChequeDTO) throws Exception;

}
