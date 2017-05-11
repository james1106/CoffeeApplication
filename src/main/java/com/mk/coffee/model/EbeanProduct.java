package com.mk.coffee.model;

import java.util.Date;

public class EbeanProduct {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean_product.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean_product.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean_product.e_num
     *
     * @mbg.generated
     */
    private Integer eNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean_product.giving_num
     *
     * @mbg.generated
     */
    private Integer givingNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean_product.money
     *
     * @mbg.generated
     */
    private Float money;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ebean_product.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebean_product
     *
     * @mbg.generated
     */
    public EbeanProduct(Integer id, String title, Integer eNum, Integer givingNum, Float money, Date createDate) {
        this.id = id;
        this.title = title;
        this.eNum = eNum;
        this.givingNum = givingNum;
        this.money = money;
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ebean_product
     *
     * @mbg.generated
     */
    public EbeanProduct() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean_product.id
     *
     * @return the value of ebean_product.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean_product.id
     *
     * @param id the value for ebean_product.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean_product.title
     *
     * @return the value of ebean_product.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean_product.title
     *
     * @param title the value for ebean_product.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean_product.e_num
     *
     * @return the value of ebean_product.e_num
     *
     * @mbg.generated
     */
    public Integer geteNum() {
        return eNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean_product.e_num
     *
     * @param eNum the value for ebean_product.e_num
     *
     * @mbg.generated
     */
    public void seteNum(Integer eNum) {
        this.eNum = eNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean_product.giving_num
     *
     * @return the value of ebean_product.giving_num
     *
     * @mbg.generated
     */
    public Integer getGivingNum() {
        return givingNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean_product.giving_num
     *
     * @param givingNum the value for ebean_product.giving_num
     *
     * @mbg.generated
     */
    public void setGivingNum(Integer givingNum) {
        this.givingNum = givingNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean_product.money
     *
     * @return the value of ebean_product.money
     *
     * @mbg.generated
     */
    public Float getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean_product.money
     *
     * @param money the value for ebean_product.money
     *
     * @mbg.generated
     */
    public void setMoney(Float money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ebean_product.create_date
     *
     * @return the value of ebean_product.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ebean_product.create_date
     *
     * @param createDate the value for ebean_product.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}