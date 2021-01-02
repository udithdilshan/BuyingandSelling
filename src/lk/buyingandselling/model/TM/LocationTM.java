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
public class LocationTM {

    private String locationId;
    private String rackNo;
    private String SectionName;

    public LocationTM() {
    }

    public LocationTM(String locationId, String rackNo, String SectionName) {
        this.locationId = locationId;
        this.rackNo = rackNo;
        this.SectionName = SectionName;
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
        return SectionName;
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
        this.SectionName = SectionName;
    }
}
