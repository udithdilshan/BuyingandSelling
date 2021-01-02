/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buyingandselling.BO.customer.CustomerPaymentFillingBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.CardDAO;
import lk.buyingandselling.DAO.customer.CustomerPaymentDAO;
import lk.buyingandselling.DAO.customer.RecivedChequeDAO;
import lk.buyingandselling.DBConnection.DBConnection;
import lk.buyingandselling.model.DTO.CardDTO;
import lk.buyingandselling.model.DTO.CustomerPaymentDTO;
import lk.buyingandselling.model.DTO.RecivedChequeDTO;
import lk.buyingandselling.model.entity.Card;
import lk.buyingandselling.model.entity.CustomerPayment;
import lk.buyingandselling.model.entity.RecivedCheque;

/**
 *
 * @author SLR
 */
public class CustomerPaymentFillingBOImpl implements CustomerPaymentFillingBO {

    private CustomerPaymentDAO customerPaymentDAO;
    private RecivedChequeDAO recivedChequeDAO;
    private CardDAO cardDAO;
    private Connection connection;

    public CustomerPaymentFillingBOImpl() {
        customerPaymentDAO = (CustomerPaymentDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMERPAYMENT);
        recivedChequeDAO = (RecivedChequeDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.RECIVEDCHEQUE);
        cardDAO = (CardDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CARD);
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(CustomerPaymentFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean saveCustomerPayments(CustomerPaymentDTO customerPaymentDTO, RecivedChequeDTO recivedChequeDTO, CardDTO cardDTO) {
        try {
            if (recivedChequeDTO == null && cardDTO == null) {
                customerPaymentDAO.add(makeCustomerPayment(customerPaymentDTO));
                return true;
            } else {
                connection.setAutoCommit(false);
                boolean paymentMethodAdded = false;
                if (recivedChequeDTO != null) {
                    paymentMethodAdded = recivedChequeDAO.add(makeRecivedCheque(recivedChequeDTO));
                }
                if (cardDTO != null) {
                    paymentMethodAdded = cardDAO.add(makeCard(cardDTO));
                }
                if (paymentMethodAdded) {
                    boolean customerPaymentIsAdded = customerPaymentDAO.add(makeCustomerPayment(customerPaymentDTO));
                    if (customerPaymentIsAdded) {
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
        } catch (Exception ex) {
            Logger.getLogger(CustomerPaymentFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerPaymentFillingBOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private CustomerPayment makeCustomerPayment(CustomerPaymentDTO paymentDTO) {
        return new CustomerPayment(
                paymentDTO.getPaymentId(),
                paymentDTO.getOrderId(),
                paymentDTO.getAmount(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getRecivedChaqueId(),
                paymentDTO.getCardNo(),
                paymentDTO.getPaidDate(),
                paymentDTO.getPaidTime()
        );
    }

    private Card makeCard(CardDTO cardDTO) {
        return new Card(
                cardDTO.getCardNo(),
                cardDTO.getValidDate(),
                cardDTO.getCVV(),
                cardDTO.getCardType(),
                cardDTO.getAmount(),
                cardDTO.getRecivedDate()
        );
    }

    private RecivedCheque makeRecivedCheque(RecivedChequeDTO recivedChequeDTO) {
        return new RecivedCheque(recivedChequeDTO.getRecivedChaqueId(),
                recivedChequeDTO.getBankName(),
                recivedChequeDTO.getStatus(),
                recivedChequeDTO.getAmount(),
                recivedChequeDTO.getRealizationDate(),
                recivedChequeDTO.getRecivedDate()
        );
    }
}
