package com.mk.coffee.model;

import java.util.Date;

public class CoffeeMachine {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.code
     *
     * @mbg.generated
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.longitude
     *
     * @mbg.generated
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.latitude
     *
     * @mbg.generated
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.is_takeout_status
     *
     * @mbg.generated
     */
    private Boolean isTakeoutStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.start_time
     *
     * @mbg.generated
     */
    private String startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coffee_machine.end_time
     *
     * @mbg.generated
     */
    private String endTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coffee_machine
     *
     * @mbg.generated
     */
    public CoffeeMachine(Integer id, Integer userId, String code, Date createDate, String address, Double longitude, Double latitude, Boolean isTakeoutStatus, String startTime, String endTime) {
        this.id = id;
        this.userId = userId;
        this.code = code;
        this.createDate = createDate;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isTakeoutStatus = isTakeoutStatus;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coffee_machine
     *
     * @mbg.generated
     */
    public CoffeeMachine() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.id
     *
     * @return the value of coffee_machine.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.id
     *
     * @param id the value for coffee_machine.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.user_id
     *
     * @return the value of coffee_machine.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.user_id
     *
     * @param userId the value for coffee_machine.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.code
     *
     * @return the value of coffee_machine.code
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.code
     *
     * @param code the value for coffee_machine.code
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.create_date
     *
     * @return the value of coffee_machine.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.create_date
     *
     * @param createDate the value for coffee_machine.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.address
     *
     * @return the value of coffee_machine.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.address
     *
     * @param address the value for coffee_machine.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.longitude
     *
     * @return the value of coffee_machine.longitude
     *
     * @mbg.generated
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.longitude
     *
     * @param longitude the value for coffee_machine.longitude
     *
     * @mbg.generated
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.latitude
     *
     * @return the value of coffee_machine.latitude
     *
     * @mbg.generated
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.latitude
     *
     * @param latitude the value for coffee_machine.latitude
     *
     * @mbg.generated
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.is_takeout_status
     *
     * @return the value of coffee_machine.is_takeout_status
     *
     * @mbg.generated
     */
    public Boolean getIsTakeoutStatus() {
        return isTakeoutStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.is_takeout_status
     *
     * @param isTakeoutStatus the value for coffee_machine.is_takeout_status
     *
     * @mbg.generated
     */
    public void setIsTakeoutStatus(Boolean isTakeoutStatus) {
        this.isTakeoutStatus = isTakeoutStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.start_time
     *
     * @return the value of coffee_machine.start_time
     *
     * @mbg.generated
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.start_time
     *
     * @param startTime the value for coffee_machine.start_time
     *
     * @mbg.generated
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coffee_machine.end_time
     *
     * @return the value of coffee_machine.end_time
     *
     * @mbg.generated
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coffee_machine.end_time
     *
     * @param endTime the value for coffee_machine.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }
}