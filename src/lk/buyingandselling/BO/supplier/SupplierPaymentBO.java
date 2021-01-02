/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.SupplierPaymentDTO;

/**
 *
 * @author SLR
 */
public interface SupplierPaymentBO extends SuperBO {

    public boolean addSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception;

    public boolean updateSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception;
    
    public ArrayList<SupplierPaymentDTO> searchSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception;

    public ArrayList<SupplierPaymentDTO> getAllSupplierPayment() throws Exception;

    public ArrayList<SupplierPaymentDTO> daySpend() throws Exception;
}
