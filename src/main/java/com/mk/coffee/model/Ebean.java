package com.mk.coffee.model;

import java.util.Date;

public class Ebean extends EbeanKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean.e_num
     *
     * @mbg.generated
     */
    private Integer eNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean.giving_num
     *
     * @mbg.generated
     */
    private Integer givingNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean.total_num
     *
     * @mbg.generated
     */
    private Integer totalNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebean
     *
     * @mbg.generated
     */
    public Ebean(Integer id, Long memberId, Integer eNum, Integer givingNum, Integer totalNum, Date createDate, Date updateDate) {
        super(id, memberId);
        this.eNum = eNum;
        this.givingNum = givingNum;
        this.totalNum = totalNum;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebean
     *
     * @mbg.generated
     */
    public Ebean() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean.e_num
     *
     * @return the value of ebean.e_num
     *
     * @mbg.generated
     */
    public Integer geteNum() {
        return eNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean.e_num
     *
     * @param eNum the value for ebean.e_num
     *
     * @mbg.generated
     */
    public void seteNum(Integer eNum) {
        this.eNum = eNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean.giving_num
     *
     * @return the value of ebean.giving_num
     *
     * @mbg.generated
     */
    public Integer getGivingNum() {
        return givingNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean.giving_num
     *
     * @param givingNum the value for ebean.giving_num
     *
     * @mbg.generated
     */
    public void setGivingNum(Integer givingNum) {
        this.givingNum = givingNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean.total_num
     *
     * @return the value of ebean.total_num
     *
     * @mbg.generated
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean.total_num
     *
     * @param totalNum the value for ebean.total_num
     *
     * @mbg.generated
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean.create_date
     *
     * @return the value of ebean.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean.create_date
     *
     * @param createDate the value for ebean.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean.update_date
     *
     * @return the value of ebean.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean.update_date
     *
     * @param updateDate the value for ebean.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}