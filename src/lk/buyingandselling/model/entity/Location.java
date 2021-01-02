package lk.buyingandselling.model.entity;

public class Location {

    private String locationId;
    private String rackNo;
    private String SectionName;

    /**
     * @return the locationId
     */
    public String getLocationId() {
        return locationId;
    }

    public Location(String locationId, String rackNo, String SectionName) {
        this.locationId = locationId;
        this.rackNo = rackNo;
        this.SectionName = SectionName;
    }

    public Location() {
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
