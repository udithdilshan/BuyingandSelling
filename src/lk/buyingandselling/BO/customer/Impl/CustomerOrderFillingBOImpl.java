/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buyingandselling.BO.customer.CustomerOrderFillingBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.CustomerOrderDAO;
import lk.buyingandselling.DAO.customer.CustomerOrderDetailDAO;
import lk.buyingandselling.DAO.supplier.BatchDetailDAO;
import lk.buyingandselling.DBConnection.DBConnection;
import lk.buyingandselling.model.DTO.BatchDetailDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDetailDTO;
import lk.buyingandselling.model.entity.BatchDetail;
import lk.buyingandselling.model.entity.CustomerOrder;
import lk.buyingandselling.model.entity.CustomerOrderDetail;

/**
 *
 * @author SLR
 */
public class CustomerOrderFillingBOImpl implements CustomerOrderFillingBO {

    private CustomerOrderDAO customerOrderDAO;
    private CustomerOrderDetailDAO customerOrderDetailDAO;
    private BatchDetailDAO batchDetailDAO;
    private Connection connection;

    public CustomerOrderFillingBOImpl() {
        customerOrderDAO = (CustomerOrderDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMERORDER);
        customerOrderDetailDAO = (CustomerOrderDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMERORDERDETAIL);
        batchDetailDAO = (BatchDetailDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.BATCHDETAIL);

    }

    @Override
    public boolean saveCustomerOrder(CustomerOrderDTO customerOrderDTO, ArrayList<CustomerOrderDetailDTO> customerOrderDetailDTOs, ArrayList<BatchDetailDTO> batchDetailDTOs) {

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean customerOrderIsAdded = customerOrderDAO.add(makeCustomerOrder(customerOrderDTO));
            if (customerOrderIsAdded) {
                boolean customerOrderDetailIsAdded = false;
                for (CustomerOrderDetailDTO customerOrderDetailDTO : customerOrderDetailDTOs) {

                    customerOrderDetailIsAdded = customerOrderDetailDAO.add(makeCustomerOrderDetail(customerOrderDetailDTO));
                    if (customerOrderDetailIsAdded == false) {
                        connection.rollback();
                        return false;
                    }
                }
                if (customerOrderDetailIsAdded) {
                    boolean batchDetailIsAdded = false;
                    for (BatchDetailDTO batchDetailDTO : batchDetailDTOs) {
                        batchDetailIsAdded = batchDetailDAO.updateQTYOnHand(makeBatchDetail(batchDetailDTO));
                        if (batchDetailIsAdded == false) {
                            connection.rollback();
                            return false;
                        }
                    }
                    if (batchDetailIsAdded) {
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

            } else {
                connection.rollback();
                return false;
            }

        } catch (Exception ex) {
            Logger.getLogger(CustomerOrderFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerOrderFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    private CustomerOrder makeCustomerOrder(CustomerOrderDTO customerOrderDTO) {
        return new CustomerOrder(
                customerOrderDTO.getOrderId(),
                customerOrderDTO.getCustomerId(),
                customerOrderDTO.getOrderedDate(),
                customerOrderDTO.getOrderedTime()
        );
    }

    private CustomerOrderDetail makeCustomerOrderDetail(CustomerOrderDetailDTO customerOrderDetailDTO) {
        return new CustomerOrderDetail(
                customerOrderDetailDTO.getOrderId(),
                customerOrderDetailDTO.getBatchNo(),
                customerOrderDetailDTO.getItemCode(),
                customerOrderDetailDTO.getQTY()
        );
    }

    private BatchDetail makeBatchDetail(BatchDetailDTO batchDetailDTO) {
        BatchDetail batchDetail = new BatchDetail();
        batchDetail.setBatchNo(batchDetailDTO.getBatchNo());
        batchDetail.setItemCode(batchDetailDTO.getItemCode());
        batchDetail.setQTY(batchDetailDTO.getQTY());
        return batchDetail;
    }
}
