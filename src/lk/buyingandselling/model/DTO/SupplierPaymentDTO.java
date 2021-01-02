/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.model.DTO;

/**
 *
 * @author SLR
 */
public class SupplierPaymentDTO {

    private String paymentId;
    private String purchaseId;
    private double paidAmount;
    private double totalAmount;
    private String paymentMethod;
    private String issuedChequeId;
    private String paidDate;

    public SupplierPaymentDTO(String paymentId, String purchaseId, double paidAmount, double totalAmount, String paymentMethod, String issuedChequeId, String paidDate) {
        this.paymentId = paymentId;
        this.purchaseId = purchaseId;
        this.paidAmount = paidAmount;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.issuedChequeId = issuedChequeId;
        this.paidDate = paidDate;
    }

    public SupplierPaymentDTO() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIssuedChequeId() {
        return issuedChequeId;
    }

    public void setIssuedChequeId(String issuedChequeId) {
        this.issuedChequeId = issuedChequeId;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

}
