/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO;

import lk.buyingandselling.DAO.customer.Impl.CardDAOImpl;
import lk.buyingandselling.DAO.customer.Impl.CustomerDAOImpl;
import lk.buyingandselling.DAO.customer.Impl.CustomerOrderDAOImpl;
import lk.buyingandselling.DAO.customer.Impl.CustomerOrderDetailDAOImpl;
import lk.buyingandselling.DAO.customer.Impl.CustomerPaymentDAOImpl;
import lk.buyingandselling.DAO.customer.Impl.RecivedChequeDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.BatchDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.BatchDetailDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.IssuedChequeDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.ItemDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.LocationDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.PurchaseStockDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.PurchaseStockDetailDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.SupplierDAOImpl;
import lk.buyingandselling.DAO.supplier.Impl.SupplierPaymentDAOImpl;

/**
 *
 * @author SLR
 */
public class DAOFactory {

    public enum DAOType {
        SUPPLIER, LOCATION, ITEM, PURCHASESTOCK, BATCH, BATCHDETAIL, PURCHASESTOCKDETAIL, ISSUEDCHEQUE, SUPPLIERPAYMENT,
        CUSTOMER, CUSTOMERORDER, CUSTOMERORDERDETAIL, CUSTOMERPAYMENT, RECIVEDCHEQUE, CARD
    }
    private static DAOFactory dAOFactory;

    public static DAOFactory getDAOFactory() {
        return (dAOFactory != null) ? dAOFactory : (dAOFactory = new DAOFactory());
    }

    public SuperDAO getSuperDAO(DAOType dAOType) {
        switch (dAOType) {
            case SUPPLIER:
                return new SupplierDAOImpl();
            case LOCATION:
                return new LocationDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case PURCHASESTOCK:
                return new PurchaseStockDAOImpl();
            case BATCH:
                return new BatchDAOImpl();
            case BATCHDETAIL:
                return new BatchDetailDAOImpl();
            case PURCHASESTOCKDETAIL:
                return new PurchaseStockDetailDAOImpl();
            case ISSUEDCHEQUE:
                return new IssuedChequeDAOImpl();
            case SUPPLIERPAYMENT:
                return new SupplierPaymentDAOImpl();
            case CUSTOMERORDER:
                return new CustomerOrderDAOImpl();
            case CUSTOMERORDERDETAIL:
                return new CustomerOrderDetailDAOImpl();
            case CUSTOMERPAYMENT:
                return new CustomerPaymentDAOImpl();
            case RECIVEDCHEQUE:
                return new RecivedChequeDAOImpl();
            case CARD:
                return new CardDAOImpl();

            default:
                return null;
        }
    }
}
