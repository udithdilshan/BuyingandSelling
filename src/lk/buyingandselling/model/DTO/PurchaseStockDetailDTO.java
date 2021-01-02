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
public class PurchaseStockDetailDTO {

    private String purchaseId;
    private String batchNo;
    private double QTY;

    public PurchaseStockDetailDTO(String purchaseId, String batchNo, double QTY) {
        this.purchaseId = purchaseId;
        this.batchNo = batchNo;
        this.QTY = QTY;
    }

    public PurchaseStockDetailDTO() {
    }


    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @return the purchaseId
     */
    public String getPurchaseId() {
        return purchaseId;
    }

    /**
     * @param batchNo the batchNo to set
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * @return the QTY
     */
    public double getQTY() {
        return QTY;
    }

    /**
     * @param QTY the QTY to set
     */
    public void setQTY(double QTY) {
        this.QTY = QTY;
    }
}
