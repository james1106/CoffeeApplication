package com.mk.coffee.model;

import java.util.Date;

public class Address {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.contacts
     *
     * @mbg.generated
     */
    private String contacts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.province
     *
     * @mbg.generated
     */
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.city
     *
     * @mbg.generated
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.area
     *
     * @mbg.generated
     */
    private String area;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.longitude
     *
     * @mbg.generated
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.latitude
     *
     * @mbg.generated
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    public Address(Integer id, String contacts, String phone, String province, String city, String area, String address, Double longitude, Double latitude, Date createDate) {
        this.id = id;
        this.contacts = contacts;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    public Address() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.id
     *
     * @return the value of address.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.id
     *
     * @param id the value for address.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.contacts
     *
     * @return the value of address.contacts
     *
     * @mbg.generated
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.contacts
     *
     * @param contacts the value for address.contacts
     *
     * @mbg.generated
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.phone
     *
     * @return the value of address.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.phone
     *
     * @param phone the value for address.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.province
     *
     * @return the value of address.province
     *
     * @mbg.generated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.province
     *
     * @param province the value for address.province
     *
     * @mbg.generated
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.city
     *
     * @return the value of address.city
     *
     * @mbg.generated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.city
     *
     * @param city the value for address.city
     *
     * @mbg.generated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.area
     *
     * @return the value of address.area
     *
     * @mbg.generated
     */
    public String getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.area
     *
     * @param area the value for address.area
     *
     * @mbg.generated
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.address
     *
     * @return the value of address.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.address
     *
     * @param address the value for address.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.longitude
     *
     * @return the value of address.longitude
     *
     * @mbg.generated
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.longitude
     *
     * @param longitude the value for address.longitude
     *
     * @mbg.generated
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.latitude
     *
     * @return the value of address.latitude
     *
     * @mbg.generated
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.latitude
     *
     * @param latitude the value for address.latitude
     *
     * @mbg.generated
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.create_date
     *
     * @return the value of address.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.create_date
     *
     * @param createDate the value for address.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}