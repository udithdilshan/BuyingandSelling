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
public class BatchDetailDTO {

    private String batchNo;
    private String itemCode;
    private double unitPrice;
    private double sellingPrice;
    private double QTY;
    private double qtyOnHand;
    private String EXD;
    private String MFD;

    public BatchDetailDTO() {
    }

    public BatchDetailDTO(String batchNo, String itemCode, double unitPrice, double sellingPrice, double QTY, double qtyOnHand, String EXD, String MFD) {
        this.batchNo = batchNo;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.QTY = QTY;
        this.qtyOnHand = qtyOnHand;
        this.EXD = EXD;
        this.MFD = MFD;
    }

    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @return the EXD
     */
    public String getEXD() {
        return EXD;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @return the MFD
     */
    public String getMFD() {
        return MFD;
    }

    /**
     * @return the QTY
     */
    public double getQTY() {
        return QTY;
    }

    /**
     * @return the qtyOnHand
     */
    public double getQtyOnHand() {
        return qtyOnHand;
    }

    /**
     * @return the sellingPrice
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param batchNo the batchNo to set
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * @param EXD the EXD to set
     */
    public void setEXD(String EXD) {
        this.EXD = EXD;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @param MFD the MFD to set
     */
    public void setMFD(String MFD) {
        this.MFD = MFD;
    }

    /**
     * @param QTY the QTY to set
     */
    public void setQTY(double QTY) {
        this.QTY = QTY;
    }

    /**
     * @param qtyOnHand the qtyOnHand to set
     */
    public void setQtyOnHand(double qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
