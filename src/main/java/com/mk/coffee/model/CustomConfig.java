package com.mk.coffee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomConfig {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column custom_config.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column custom_config.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column custom_config.coffee
     *
     * @mbg.generated
     */
    private Integer coffee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column custom_config.milk
     *
     * @mbg.generated
     */
    private Integer milk;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column custom_config.sugar
     *
     * @mbg.generated
     */
    private Integer sugar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column custom_config.is_recommend
     *
     * @mbg.generated
     */
    private Boolean isRecommend;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column custom_config.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom_config
     *
     * @mbg.generated
     */
    public CustomConfig(Integer id, String name, Integer coffee, Integer milk, Integer sugar, Boolean isRecommend, Date createDate) {
        this.id = id;
        this.name = name;
        this.coffee = coffee;
        this.milk = milk;
        this.sugar = sugar;
        this.isRecommend = isRecommend;
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table custom_config
     *
     * @mbg.generated
     */
    public CustomConfig() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column custom_config.id
     *
     * @return the value of custom_config.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column custom_config.id
     *
     * @param id the value for custom_config.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column custom_config.name
     *
     * @return the value of custom_config.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column custom_config.name
     *
     * @param name the value for custom_config.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column custom_config.coffee
     *
     * @return the value of custom_config.coffee
     *
     * @mbg.generated
     */
    public Integer getCoffee() {
        return coffee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column custom_config.coffee
     *
     * @param coffee the value for custom_config.coffee
     *
     * @mbg.generated
     */
    public void setCoffee(Integer coffee) {
        this.coffee = coffee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column custom_config.milk
     *
     * @return the value of custom_config.milk
     *
     * @mbg.generated
     */
    public Integer getMilk() {
        return milk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column custom_config.milk
     *
     * @param milk the value for custom_config.milk
     *
     * @mbg.generated
     */
    public void setMilk(Integer milk) {
        this.milk = milk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column custom_config.sugar
     *
     * @return the value of custom_config.sugar
     *
     * @mbg.generated
     */
    public Integer getSugar() {
        return sugar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column custom_config.sugar
     *
     * @param sugar the value for custom_config.sugar
     *
     * @mbg.generated
     */
    public void setSugar(Integer sugar) {
        this.sugar = sugar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column custom_config.is_recommend
     *
     * @return the value of custom_config.is_recommend
     *
     * @mbg.generated
     */
    public Boolean getIsRecommend() {
        return isRecommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column custom_config.is_recommend
     *
     * @param isRecommend the value for custom_config.is_recommend
     *
     * @mbg.generated
     */
    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column custom_config.create_date
     *
     * @return the value of custom_config.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column custom_config.create_date
     *
     * @param createDate the value for custom_config.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}