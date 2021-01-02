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
public class CustomerOrderDTO {

    private String orderId;
    private String customerId;
    private String orderedDate;
    private String orderedTime;

    public CustomerOrderDTO(String orderId, String customerId, String orderedDate, String orderedTime) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedDate = orderedDate;
        this.orderedTime = orderedTime;
    }

    public CustomerOrderDTO() {
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @return the orderedDate
     */
    public String getOrderedDate() {
        return orderedDate;
    }

    /**
     * @return the orderedTime
     */
    public String getOrderedTime() {
        return orderedTime;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @param orderedDate the orderedDate to set
     */
    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    /**
     * @param orderedTime the orderedTime to set
     */
    public void setOrderedTime(String orderedTime) {
        this.orderedTime = orderedTime;
    }
}
