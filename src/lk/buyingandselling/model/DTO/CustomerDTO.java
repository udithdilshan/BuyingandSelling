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
public class CustomerDTO {

    private String customerId;
    private String firstname;
    private String lastname;
    private String address;
    private String companyName;
    private String NIC;
    private String gender;
    private String city;
    private int mobile;
    private String email;
    private String postalCode;
    private String addedDate;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String firstname, String lastname, String address, String companyName, String NIC, String gender, String city, int mobile, String email, String postalCode, String addedDate) {
        this.customerId = customerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.companyName = companyName;
        this.NIC = NIC;
        this.gender = gender;
        this.city = city;
        this.mobile = mobile;
        this.email = email;
        this.postalCode = postalCode;
        this.addedDate = addedDate;
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
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
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
        return firstname;
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
        return lastname;
    }

    /**
     * @return the mobile
     */
    public int getMobile() {
        return mobile;
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
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
        this.firstname = firstname;
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
        this.lastname = lastname;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(int mobile) {
        this.mobile = mobile;
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
}
