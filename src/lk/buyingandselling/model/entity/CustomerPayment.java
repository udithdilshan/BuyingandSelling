/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.model.entity;

/**
 *
 * @author SLR
 */
public class CustomerPayment {
    private String paymentId;
    private String orderId;
    private double amount;
    private String paymentMethod;
    private String recivedChaqueId;
    private long cardNo;
    private String paidDate;
    private String paidTime;

    public CustomerPayment() {
    }

    public CustomerPayment(String paymentId, String orderId, double amount, String paymentMethod, String recivedChaqueId, long cardNo, String paidDate, String paidTime) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.recivedChaqueId = recivedChaqueId;
        this.cardNo = cardNo;
        this.paidDate = paidDate;
        this.paidTime = paidTime;
    }

    
    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

   
    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @return the paidDate
     */
    public String getPaidDate() {
        return paidDate;
    }

    /**
     * @return the paidTime
     */
    public String getPaidTime() {
        return paidTime;
    }

    /**
     * @return the paymentId
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @return the recivedChaqueId
     */
    public String getRecivedChaqueId() {
        return recivedChaqueId;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @param paidDate the paidDate to set
     */
    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    /**
     * @param paidTime the paidTime to set
     */
    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @param recivedChaqueId the recivedChaqueId to set
     */
    public void setRecivedChaqueId(String recivedChaqueId) {
        this.recivedChaqueId = recivedChaqueId;
    }

    /**
     * @return the cardNo
     */
    public long getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo the cardNo to set
     */
    public void setCardNo(long cardNo) {
        this.cardNo = cardNo;
    }
}
