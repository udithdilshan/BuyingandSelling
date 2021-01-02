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
public class SupplierPayment {
    private String paymentId;
    private String purchaseId;
    private double paidAmount;
    private double totalAmount;
    private String paymentMethod;
    private String issuedChequeId;
    private String paidDate;

    public SupplierPayment() {
    }

    public SupplierPayment(String paymentId, String purchaseId, double paidAmount, double totalAmount, String paymentMethod, String issuedChequeId, String paidDate) {
        this.paymentId = paymentId;
        this.purchaseId = purchaseId;
        this.paidAmount = paidAmount;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.issuedChequeId = issuedChequeId;
        this.paidDate = paidDate;
    }

    /**
     * @return the issuedChequeId
     */
    public String getIssuedChequeId() {
        return issuedChequeId;
    }

    /**
     * @return the paidAmount
     */
    public double getPaidAmount() {
        return paidAmount;
    }

    /**
     * @return the paidDate
     */
    public String getPaidDate() {
        return paidDate;
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
     * @return the purchaseId
     */
    public String getPurchaseId() {
        return purchaseId;
    }

    /**
     * @return the totalAmount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param issuedChequeId the issuedChequeId to set
     */
    public void setIssuedChequeId(String issuedChequeId) {
        this.issuedChequeId = issuedChequeId;
    }

    /**
     * @param paidAmount the paidAmount to set
     */
    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     * @param paidDate the paidDate to set
     */
    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
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
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
