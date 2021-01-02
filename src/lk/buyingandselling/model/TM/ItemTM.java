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
public class ItemTM {

    private String itemCode;
    private String description;
    private String category;
    private String sectionName;
    private String locationId;
    private String rackNo;

    public ItemTM() {
    }

    public ItemTM(String itemCode, String description, String Category, String SectionName, String locationId, String rackNo) {
        this.itemCode = itemCode;
        this.description = description;
        this.category = Category;
        this.sectionName = SectionName;
        this.locationId = locationId;
        this.rackNo = rackNo;
    }

    /**
     * @return the Category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * @return the rackNo
     */
    public String getRackNo() {
        return rackNo;
    }

    /**
     * @return the SectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @param Category the Category to set
     */
    public void setCategory(String Category) {
        this.category = Category;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @param rackNo the rackNo to set
     */
    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    /**
     * @param SectionName the SectionName to set
     */
    public void setSectionName(String SectionName) {
        this.sectionName = SectionName;
    }

}
