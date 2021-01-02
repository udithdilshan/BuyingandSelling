/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.model.TM;

/**
 *
 * @author SLR
 */
public class CustomerOrderTM {

    private String itemCode;
    private String batchNo;
    private String description;
    private String EXP;
    private String MFD;
    private String locationId;
    private String rackNo;
    private double unitPrice;
    private double QTY;
    private double Total;
    private String sectionName;

    public CustomerOrderTM(String itemCode, String batchNo, String description, String EXP, String MFD, String locationId, String rackNo, double unitPrice, double QTY, double Total, String sectionName) {
        this.itemCode = itemCode;
        this.batchNo = batchNo;
        this.description = description;
        this.EXP = EXP;
        this.MFD = MFD;
        this.locationId = locationId;
        this.rackNo = rackNo;
        this.unitPrice = unitPrice;
        this.QTY = QTY;
        this.Total = Total;
        this.sectionName = sectionName;
    }

    public CustomerOrderTM() {
    }

    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
