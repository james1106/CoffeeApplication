package com.mk.coffee.model;

import java.util.Date;

public class VerificationCode {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verification_code.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verification_code.code
     *
     * @mbg.generated
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verification_code.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verification_code.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verification_code.validity_minute
     *
     * @mbg.generated
     */
    private Integer validityMinute;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column verification_code.verify_state
     *
     * @mbg.generated
     */
    private Boolean verifyState;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table verification_code
     *
     * @mbg.generated
     */
    public VerificationCode(Integer id, String code, String phone, Date createDate, Integer validityMinute, Boolean verifyState) {
        this.id = id;
        this.code = code;
        this.phone = phone;
        this.createDate = createDate;
        this.validityMinute = validityMinute;
        this.verifyState = verifyState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table verification_code
     *
     * @mbg.generated
     */
    public VerificationCode() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verification_code.id
     *
     * @return the value of verification_code.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verification_code.id
     *
     * @param id the value for verification_code.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verification_code.code
     *
     * @return the value of verification_code.code
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verification_code.code
     *
     * @param code the value for verification_code.code
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verification_code.phone
     *
     * @return the value of verification_code.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verification_code.phone
     *
     * @param phone the value for verification_code.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verification_code.create_date
     *
     * @return the value of verification_code.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verification_code.create_date
     *
     * @param createDate the value for verification_code.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verification_code.validity_minute
     *
     * @return the value of verification_code.validity_minute
     *
     * @mbg.generated
     */
    public Integer getValidityMinute() {
        return validityMinute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verification_code.validity_minute
     *
     * @param validityMinute the value for verification_code.validity_minute
     *
     * @mbg.generated
     */
    public void setValidityMinute(Integer validityMinute) {
        this.validityMinute = validityMinute;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column verification_code.verify_state
     *
     * @return the value of verification_code.verify_state
     *
     * @mbg.generated
     */
    public Boolean getVerifyState() {
        return verifyState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column verification_code.verify_state
     *
     * @param verifyState the value for verification_code.verify_state
     *
     * @mbg.generated
     */
    public void setVerifyState(Boolean verifyState) {
        this.verifyState = verifyState;
    }
}