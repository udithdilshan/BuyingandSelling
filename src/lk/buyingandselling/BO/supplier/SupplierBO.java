/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.SupplierDTO;

/**
 *
 * @author SLR
 */
public interface SupplierBO extends SuperBO {

    public boolean addSupplier(SupplierDTO supplierDTO) throws Exception;

    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception;

    public ArrayList<SupplierDTO> searchSupplier(SupplierDTO supplierDTO) throws Exception;
    
    public boolean deleteSupplier(String supplierID) throws Exception;
}
