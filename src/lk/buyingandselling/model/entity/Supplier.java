package lk.buyingandselling.model.entity;

import java.util.Date;

public class Supplier {

    private String supplierId;
    private String firstName;
    private String lastName;
    private String address;
    private String companyName;
    private String NIC;
    private String gender;
    private String city;
    private int mobileNumber;
    private String email;
    private String postalCode;
    private String addedDate;

    public Supplier(String supplierId, String firstname, String lastname,
            String address, String companyName, String NIC, String gender,
            String city, int mobile, String email, String postalCode,
            String addedDate) {
        
        this.supplierId = supplierId;
        this.firstName = firstname;
        this.lastName = lastname;
        this.address = address;
        this.companyName = companyName;
        this.NIC = NIC;
        this.gender = gender;
        this.city = city;
        this.mobileNumber = mobile;
        this.email = email;
        this.postalCode = postalCode;
        this.addedDate = addedDate;
    }

    public Supplier() {
    }

    /**
     * @return the addedDate
     */
    public String getAddedDate() {
        return addedDate;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastName;
    }

    /**
     * @return the mobile
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @return the NIC
     */
    public String getNIC() {
        return NIC;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * @param addedDate the addedDate to set
     */
    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobileNumber(int mobile) {
        this.mobileNumber = mobile;
    }

    /**
     * @param NIC the NIC to set
     */
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

}
