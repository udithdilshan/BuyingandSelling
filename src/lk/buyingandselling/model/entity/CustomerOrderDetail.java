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
public class CustomerOrderDetail {

    private String orderId;
    private String batchNo;
    private String itemCode;
    private double QTY;

    public CustomerOrderDetail() {
    }

    public CustomerOrderDetail( String orderId, String batchNo, String itemCode, double QTY) {
        this.orderId = orderId;
        this.batchNo = batchNo;
        this.itemCode = itemCode;
        this.QTY = QTY;
    }

    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @return the QTY
     */
    public double getQTY() {
        return QTY;
    }

    /**
     * @param batchNo the batchNo to set
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @param QTY the QTY to set
     */
    public void setQTY(double QTY) {
        this.QTY = QTY;
    }

}
