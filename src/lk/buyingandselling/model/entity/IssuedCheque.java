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
public class IssuedCheque {

    private String issuedChequeId;
    private String bankName;
    private double amount;
    private String status;
    private String issuedDate;

    public IssuedCheque() {
    }

    public IssuedCheque(String issuedChequeId, String bankName, double amount, String status, String issuedDate) {
        this.issuedChequeId = issuedChequeId;
        this.bankName = bankName;
        this.amount = amount;
        this.status = status;
        this.issuedDate = issuedDate;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @return the issuedChequeId
     */
    public String getIssuedChequeId() {
        return issuedChequeId;
    }

    /**
     * @return the issuedDate
     */
    public String getIssuedDate() {
        return issuedDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @param issuedChequeId the issuedChequeId to set
     */
    public void setIssuedChequeId(String issuedChequeId) {
        this.issuedChequeId = issuedChequeId;
    }

    /**
     * @param issuedDate the issuedDate to set
     */
    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
