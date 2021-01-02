/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.IssuedChequeDTO;
import lk.buyingandselling.model.DTO.SupplierPaymentDTO;

/**
 *
 * @author SLR
 */
public interface SupplierPaymentFillingBO extends SuperBO{
    
    public boolean saveSupplierPayment(SupplierPaymentDTO supplierPaymentDTO,IssuedChequeDTO issuedChequeDTO);
}
