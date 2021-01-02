/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buyingandselling.BO.supplier.SupplierPaymentFillingBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.IssuedChequeDAO;
import lk.buyingandselling.DAO.supplier.SupplierPaymentDAO;
import lk.buyingandselling.DBConnection.DBConnection;
import lk.buyingandselling.model.DTO.IssuedChequeDTO;
import lk.buyingandselling.model.DTO.SupplierPaymentDTO;
import lk.buyingandselling.model.entity.IssuedCheque;
import lk.buyingandselling.model.entity.SupplierPayment;

/**
 *
 * @author SLR
 */
public class SupplierPaymentFillingBOImpl implements SupplierPaymentFillingBO {

    private SupplierPaymentDAO supplierPaymentDAO;
    private IssuedChequeDAO issuedChequeDAO;
    private Connection connection;

    public SupplierPaymentFillingBOImpl() {

        supplierPaymentDAO = (SupplierPaymentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.SUPPLIERPAYMENT);
        issuedChequeDAO = (IssuedChequeDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.ISSUEDCHEQUE);
    }

    @Override
    public boolean saveSupplierPayment(SupplierPaymentDTO supplierPaymentDTO, IssuedChequeDTO issuedChequeDTO){
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            SupplierPayment supplierPayment = new SupplierPayment(
                    supplierPaymentDTO.getPaymentId(),
                    supplierPaymentDTO.getPurchaseId(),
                    supplierPaymentDTO.getPaidAmount(),
                    supplierPaymentDTO.getTotalAmount(),
                    supplierPaymentDTO.getPaymentMethod(),
                    supplierPaymentDTO.getIssuedChequeId(),
                    supplierPaymentDTO.getPaidDate()
            );
            
            if (issuedChequeDTO == null) {
                supplierPayment.setIssuedChequeId(null);
                boolean supplierPaymentIsAdded = supplierPaymentDAO.add(supplierPayment);
                if (supplierPaymentIsAdded) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
            
            if (issuedChequeDTO != null) {
                IssuedCheque issuedCheque = new IssuedCheque(
                        issuedChequeDTO.getIssuedChequeId(),
                        issuedChequeDTO.getBankName(),
                        issuedChequeDTO.getAmount(),
                        issuedChequeDTO.getStatus(),
                        issuedChequeDTO.getIssuedDate()
                );
                boolean issuedChequeIsAdded = issuedChequeDAO.add(issuedCheque);
                if (issuedChequeIsAdded) {
                    boolean supplierPaymentIsAdded = supplierPaymentDAO.add(supplierPayment);
                    if (supplierPaymentIsAdded) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(SupplierPaymentFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SupplierPaymentFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
