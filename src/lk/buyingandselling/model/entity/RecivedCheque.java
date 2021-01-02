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
public class RecivedCheque {
     private String recivedChaqueId;
    private String bankName;
    private String status;
    private double amount;
    private String realizationDate;
    private String recivedDate;

    public RecivedCheque() {
    }

    public RecivedCheque(String recivedChaqueId, String bankName, String status, double amount, String realizationDate, String recivedDate) {
        this.recivedChaqueId = recivedChaqueId;
        this.bankName = bankName;
        this.status = status;
        this.amount = amount;
        this.realizationDate = realizationDate;
        this.recivedDate = recivedDate;
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
     * @return the realizationDate
     */
    public String getRealizationDate() {
        return realizationDate;
    }

    /**
     * @return the recivedChaqueId
     */
    public String getRecivedChaqueId() {
        return recivedChaqueId;
    }

    /**
     * @return the recivedDate
     */
    public String getRecivedDate() {
        return recivedDate;
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
     * @param realizationDate the realizationDate to set
     */
    public void setRealizationDate(String realizationDate) {
        this.realizationDate = realizationDate;
    }

    /**
     * @param recivedChaqueId the recivedChaqueId to set
     */
    public void setRecivedChaqueId(String recivedChaqueId) {
        this.recivedChaqueId = recivedChaqueId;
    }

    /**
     * @param recivedDate the recivedDate to set
     */
    public void setRecivedDate(String recivedDate) {
        this.recivedDate = recivedDate;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
