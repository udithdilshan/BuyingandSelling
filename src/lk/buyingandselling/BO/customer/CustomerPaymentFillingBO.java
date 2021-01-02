/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.CardDTO;
import lk.buyingandselling.model.DTO.CustomerPaymentDTO;
import lk.buyingandselling.model.DTO.RecivedChequeDTO;

/**
 *
 * @author SLR
 */
public interface CustomerPaymentFillingBO extends SuperBO {

    public boolean saveCustomerPayments(CustomerPaymentDTO customerPaymentDTO, RecivedChequeDTO recivedChequeDTO, CardDTO cardDTO);
}
