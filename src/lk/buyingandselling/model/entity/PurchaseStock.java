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
public class PurchaseStock {
    private String purchaseId;
    private String supplierId;
    private String purchaseDate;

    public PurchaseStock() {
    }

    public PurchaseStock(String purchaseId, String supplierId, String purchaseDate) {
        this.purchaseId = purchaseId;
        this.supplierId = supplierId;
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the purchaseDate
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @return the purchaseId
     */
    public String getPurchaseId() {
        return purchaseId;
    }

    /**
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
}
