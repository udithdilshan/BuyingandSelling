/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.model.TM;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 *
 * @author SLR
 */
public class PurchaseStockTM extends RecursiveTreeObject<PurchaseStockTM>{

    private String itemCode;
    private String batchNo;
    private String itemdescription;
    private String locationId;
    private String EXP;
    private String MFD;
    private String rackNo;
    private double unitPrice;
    private double sellingPrice;
    private double QTY;
    private double Total;
    private String sectionName;

    public PurchaseStockTM() {
    }

    public PurchaseStockTM(String itemCode, String batchNo, String itemdescription, String locationId, String EXP, String MFD, String rackNo, double unitPrice, double sellingPrice, double QTY, double Total, String sectionName) {
        this.itemCode = itemCode;
        this.batchNo = batchNo;
        this.itemdescription = itemdescription;
        this.locationId = locationId;
        this.EXP = EXP;
        this.MFD = MFD;
        this.rackNo = rackNo;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.QTY = QTY;
        this.Total = Total;
        this.sectionName = sectionName;
    }

    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @return the EXP
     */
    public String getEXP() {
        return EXP;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @return the itemdescription
     */
    public String getItemdescription() {
        return itemdescription;
    }

    /**
     * @return the locationId
     */
    public String getLocationId() {
        return locationId;
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
     * @return the rackNo
     */
    public String getRackNo() {
        return rackNo;
    }

    /**
     * @return the sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @return the sellingPrice
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @return the Total
     */
    public double getTotal() {
        return Total;
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
     * @param EXP the EXP to set
     */
    public void setEXP(String EXP) {
        this.EXP = EXP;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @param itemdescription the itemdescription to set
     */
    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
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
     * @param rackNo the rackNo to set
     */
    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    /**
     * @param sectionName the sectionName to set
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(double Total) {
        this.Total = Total;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

  
    
}
