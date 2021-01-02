/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.SupplierBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.SupplierDAO;
import lk.buyingandselling.model.DTO.SupplierDTO;
import lk.buyingandselling.model.entity.Supplier;

/**
 *
 * @author SLR
 */
public class SupplierBOImpl implements SupplierBO {

    private SupplierDAO supplierDAO;

    public SupplierBOImpl() {
        supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.SUPPLIER);
    }

    @Override
    public boolean addSupplier(SupplierDTO supplierDTO) throws Exception {
        return supplierDAO.add(setSupplier(supplierDTO));
    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception {
        ArrayList<Supplier> allSuppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> allSuppliersDTO = new ArrayList<SupplierDTO>();
        for (Supplier supplier : allSuppliers) {
            allSuppliersDTO.add(new SupplierDTO(
                    supplier.getSupplierId(),
                    supplier.getFirstname(),
                    supplier.getLastname(),
                    supplier.getAddress(),
                    supplier.getCompanyName(),
                    supplier.getNIC(),
                    supplier.getGender(),
                    supplier.getCity(),
                    supplier.getMobileNumber(),
                    supplier.getEmail(),
                    supplier.getPostalCode(),
                    supplier.getAddedDate()
            ));
        }
        return allSuppliersDTO;
    }

    public Supplier setSupplier(SupplierDTO supplierDTO) {
        return new Supplier(
                supplierDTO.getSupplierId(),
                supplierDTO.getFirstname(),
                supplierDTO.getLastname(),
                supplierDTO.getAddress(),
                supplierDTO.getCompanyName(),
                supplierDTO.getNIC(),
                supplierDTO.getGender(),
                supplierDTO.getCity(),
                supplierDTO.getMobileNumber(),
                supplierDTO.getEmail(),
                supplierDTO.getPostalCode(),
                supplierDTO.getAddedDate()
        );
    }

    @Override
    public ArrayList<SupplierDTO> searchSupplier(SupplierDTO supplierDTO) throws Exception {
        ArrayList<Supplier> result=supplierDAO.search(setSupplier(supplierDTO));
        ArrayList<SupplierDTO> allSuppliersDTO = new ArrayList<SupplierDTO>();
        for (Supplier supplier : result) {
            allSuppliersDTO.add(new SupplierDTO(
                    supplier.getSupplierId(),
                    supplier.getFirstname(),
                    supplier.getLastname(),
                    supplier.getAddress(),
                    supplier.getCompanyName(),
                    supplier.getNIC(),
                    supplier.getGender(),
                    supplier.getCity(),
                    supplier.getMobileNumber(),
                    supplier.getEmail(),
                    supplier.getPostalCode(),
                    supplier.getAddedDate()
            ));
        }
        return allSuppliersDTO;
    }

    @Override
    public boolean deleteSupplier(String supplierID) throws Exception {
        return supplierDAO.delete(supplierID);
    }

}
