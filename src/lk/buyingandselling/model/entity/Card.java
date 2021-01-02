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
public class Card {
    
    private long cardNo;
    private String validDate;
    private int CVV;
    private String cardType;
    private double amount;
    private String recivedDate;

    public Card() {
    }

    public Card(long cardNo, String validDate, int CVV, String cardType, double amount, String recivedDate) {
        this.cardNo = cardNo;
        this.validDate = validDate;
        this.CVV = CVV;
        this.cardType = cardType;
        this.amount = amount;
        this.recivedDate = recivedDate;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the CVV
     */
    public int getCVV() {
        return CVV;
    }

    /**
     * @return the cardNo
     */
    public long getCardNo() {
        return cardNo;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @return the recivedDate
     */
    public String getRecivedDate() {
        return recivedDate;
    }

    /**
     * @return the validDate
     */
    public String getValidDate() {
        return validDate;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @param CVV the CVV to set
     */
    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    /**
     * @param cardNo the cardNo to set
     */
    public void setCardNo(long cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * @param recivedDate the recivedDate to set
     */
    public void setRecivedDate(String recivedDate) {
        this.recivedDate = recivedDate;
    }

    /**
     * @param validDate the validDate to set
     */
    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
}
