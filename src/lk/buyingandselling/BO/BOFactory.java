/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO;

import lk.buyingandselling.BO.customer.Impl.CardBOImpl;
import lk.buyingandselling.BO.customer.Impl.CustomerBOImpl;
import lk.buyingandselling.BO.customer.Impl.CustomerOrderBOImpl;
import lk.buyingandselling.BO.customer.Impl.CustomerOrderFillingBOImpl;
import lk.buyingandselling.BO.customer.Impl.CustomerPaymentBOImpl;
import lk.buyingandselling.BO.customer.Impl.CustomerPaymentFillingBOImpl;
import lk.buyingandselling.BO.customer.Impl.RecivedChequeBOImpl;
import lk.buyingandselling.BO.supplier.Impl.BatchBOImpl;
import lk.buyingandselling.BO.supplier.Impl.BatchDetailBOImpl;
import lk.buyingandselling.BO.supplier.Impl.ItemBOImpl;
import lk.buyingandselling.BO.supplier.Impl.LocationBOImpl;
import lk.buyingandselling.BO.supplier.Impl.PurchaseStockBOImpl;
import lk.buyingandselling.BO.supplier.Impl.PurchaseStockDetailBOImpl;
import lk.buyingandselling.BO.supplier.Impl.PurchaseStockFillingBOImpl;
import lk.buyingandselling.BO.supplier.Impl.SupplierBOImpl;
import lk.buyingandselling.BO.supplier.Impl.IssuedChequeBOImpl;
import lk.buyingandselling.BO.supplier.Impl.SupplierPaymentBOImpl;
import lk.buyingandselling.BO.supplier.Impl.SupplierPaymentFillingBOImpl;

/**
 *
 * @author SLR
 */
public class BOFactory {

    private static BOFactory bOFactory;

    public enum BOType {
        SUPPLIER, LOCATION, ITEM, PURCHASESTOCK, BATCH, BATCHDETAIL,
        PURCHASESTOCKDETAIL, PURCHASESTOCKFILLING, ISSUEDCHEQUE, SUPPLIERPAYMENT, SUPPLIERPAYMENTFILLING,
        CUSTOMER, CUSTOMERORDER, CUSTOMERPAYMENT, RECIVEDCHEQUE, CARD, CUSTOMERORDERTFILLING, CUSTOMERPAYMENTFILLING
    }

    public static BOFactory getBOFactory() {
        return (bOFactory != null) ? bOFactory : (bOFactory = new BOFactory());
    }

    public SuperBO getSuperBO(BOType bOType) {
        switch (bOType) {
            case SUPPLIER:
                return new SupplierBOImpl();
            case LOCATION:
                return new LocationBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case PURCHASESTOCK:
                return new PurchaseStockBOImpl();
            case BATCH:
                return new BatchBOImpl();
            case BATCHDETAIL:
                return new BatchDetailBOImpl();
            case PURCHASESTOCKDETAIL:
                return new PurchaseStockDetailBOImpl();
            case PURCHASESTOCKFILLING:
                return new PurchaseStockFillingBOImpl();
            case ISSUEDCHEQUE:
                return new IssuedChequeBOImpl();
            case SUPPLIERPAYMENT:
                return new SupplierPaymentBOImpl();
            case SUPPLIERPAYMENTFILLING:
                return new SupplierPaymentFillingBOImpl();
            case CUSTOMERORDER:
                return new CustomerOrderBOImpl();
            case CUSTOMERPAYMENT:
                return new CustomerPaymentBOImpl();
            case RECIVEDCHEQUE:
                return new RecivedChequeBOImpl();
            case CARD:
                return new CardBOImpl();
            case CUSTOMERORDERTFILLING:
                return new CustomerOrderFillingBOImpl();
            case CUSTOMERPAYMENTFILLING:
                return new CustomerPaymentFillingBOImpl();
            default:
                return null;
        }
    }
}
