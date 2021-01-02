/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.SupplierPaymentBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.SupplierPaymentDAO;
import lk.buyingandselling.model.DTO.SupplierPaymentDTO;
import lk.buyingandselling.model.entity.SupplierPayment;

/**
 *
 * @author SLR
 */
public class SupplierPaymentBOImpl implements SupplierPaymentBO {

    private SupplierPaymentDAO supplierPaymentDAO;

    public SupplierPaymentBOImpl() {
        supplierPaymentDAO = (SupplierPaymentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.SUPPLIERPAYMENT);
    }

    @Override
    public boolean addSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception {
        return supplierPaymentDAO.add(makeSupplierPayment(supplierPaymentDTO));
    }

    @Override
    public boolean updateSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception {
        return supplierPaymentDAO.update(makeSupplierPayment(supplierPaymentDTO));
    }

    @Override
    public ArrayList<SupplierPaymentDTO> searchSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception {
        ArrayList<SupplierPayment> supplierPayments = supplierPaymentDAO.search(makeSupplierPayment(supplierPaymentDTO));
        ArrayList<SupplierPaymentDTO> supplierPaymentDTOs = new ArrayList<>();
        for (SupplierPayment temp : supplierPayments) {
            SupplierPaymentDTO paymentDTO = new SupplierPaymentDTO(
                    temp.getPaymentId(),
                    temp.getPurchaseId(),
                    temp.getPaidAmount(),
                    temp.getTotalAmount(),
                    temp.getPaymentMethod(),
                    temp.getIssuedChequeId(),
                    temp.getPaidDate()
            );
            supplierPaymentDTOs.add(paymentDTO);
        }
        return supplierPaymentDTOs;
    }

    @Override
    public ArrayList<SupplierPaymentDTO> getAllSupplierPayment() throws Exception {
        ArrayList<SupplierPayment> supplierPayments = supplierPaymentDAO.getAll();
        ArrayList<SupplierPaymentDTO> supplierPaymentDTOs = new ArrayList<>();
        for (SupplierPayment temp : supplierPayments) {
            SupplierPaymentDTO paymentDTO = new SupplierPaymentDTO(
                    temp.getPaymentId(),
                    temp.getPurchaseId(),
                    temp.getPaidAmount(),
                    temp.getTotalAmount(),
                    temp.getPaymentMethod(),
                    temp.getIssuedChequeId(),
                    temp.getPaidDate()
            );
            supplierPaymentDTOs.add(paymentDTO);
        }
        return supplierPaymentDTOs;
    }

    private SupplierPayment makeSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) {
        return new SupplierPayment(
                supplierPaymentDTO.getPaymentId(),
                supplierPaymentDTO.getPurchaseId(),
                supplierPaymentDTO.getPaidAmount(),
                supplierPaymentDTO.getTotalAmount(),
                supplierPaymentDTO.getPaymentMethod(),
                supplierPaymentDTO.getIssuedChequeId(),
                supplierPaymentDTO.getPaidDate()
        );
    }

    @Override
    public ArrayList<SupplierPaymentDTO> daySpend() throws Exception {
        ArrayList<SupplierPayment> supplierPayments=supplierPaymentDAO.daySpend();
        ArrayList<SupplierPaymentDTO> supplierpayment=new ArrayList<>();
        for(SupplierPayment temp: supplierPayments){
            SupplierPaymentDTO payment=new SupplierPaymentDTO();
            payment.setPaidAmount(temp.getPaidAmount());
            payment.setPaidDate(temp.getPaidDate());
            supplierpayment.add(payment);
        }
        return supplierpayment;
    }

}
