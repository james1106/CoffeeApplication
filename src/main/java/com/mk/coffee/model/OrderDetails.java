package com.mk.coffee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetails {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.members_id
     *
     * @mbg.generated
     */
    private Long membersId;

    private Members members;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.money
     *
     * @mbg.generated
     */
    private Float money;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.discount_money
     *
     * @mbg.generated
     */
    private Float discountMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.coupon_id
     *
     * @mbg.generated
     */
    private Integer couponId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.wx_card_code
     *
     * @mbg.generated
     */
    private String wxCardCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.bean
     *
     * @mbg.generated
     */
    private Integer bean;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.custom_id
     *
     * @mbg.generated
     */
    private Integer customId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.notes
     *
     * @mbg.generated
     */
    private String notes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.pay_state
     *
     * @mbg.generated
     */
    private Integer payState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.end_date
     *
     * @mbg.generated
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.total
     *
     * @mbg.generated
     */
    private Integer total;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_details.order_details
     *
     * @mbg.generated
     */
    private String orderDetails;


    private List<ShoppingCart> shoppingCarts;

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_details
     *
     * @mbg.generated
     */
    public OrderDetails(String id, Long membersId, Float money, Float discountMoney, Integer couponId, String wxCardCode, Integer bean, Integer customId, String notes, Date createDate, Integer payState, Date endDate, Integer total, String orderDetails) {
        this.id = id;
        this.membersId = membersId;
        this.money = money;
        this.discountMoney = discountMoney;
        this.couponId = couponId;
        this.wxCardCode = wxCardCode;
        this.bean = bean;
        this.customId = customId;
        this.notes = notes;
        this.createDate = createDate;
        this.payState = payState;
        this.endDate = endDate;
        this.total = total;
        this.orderDetails = orderDetails;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_details
     *
     * @mbg.generated
     */
    public OrderDetails() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.id
     *
     * @return the value of order_details.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.id
     *
     * @param id the value for order_details.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.members_id
     *
     * @return the value of order_details.members_id
     *
     * @mbg.generated
     */
    public Long getMembersId() {
        return membersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.members_id
     *
     * @param membersId the value for order_details.members_id
     *
     * @mbg.generated
     */
    public void setMembersId(Long membersId) {
        this.membersId = membersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.money
     *
     * @return the value of order_details.money
     *
     * @mbg.generated
     */
    public Float getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.money
     *
     * @param money the value for order_details.money
     *
     * @mbg.generated
     */
    public void setMoney(Float money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.discount_money
     *
     * @return the value of order_details.discount_money
     *
     * @mbg.generated
     */
    public Float getDiscountMoney() {
        return discountMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.discount_money
     *
     * @param discountMoney the value for order_details.discount_money
     *
     * @mbg.generated
     */
    public void setDiscountMoney(Float discountMoney) {
        this.discountMoney = discountMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.coupon_id
     *
     * @return the value of order_details.coupon_id
     *
     * @mbg.generated
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.coupon_id
     *
     * @param couponId the value for order_details.coupon_id
     *
     * @mbg.generated
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.wx_card_code
     *
     * @return the value of order_details.wx_card_code
     *
     * @mbg.generated
     */
    public String getWxCardCode() {
        return wxCardCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.wx_card_code
     *
     * @param wxCardCode the value for order_details.wx_card_code
     *
     * @mbg.generated
     */
    public void setWxCardCode(String wxCardCode) {
        this.wxCardCode = wxCardCode == null ? null : wxCardCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.bean
     *
     * @return the value of order_details.bean
     *
     * @mbg.generated
     */
    public Integer getBean() {
        return bean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.bean
     *
     * @param bean the value for order_details.bean
     *
     * @mbg.generated
     */
    public void setBean(Integer bean) {
        this.bean = bean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.custom_id
     *
     * @return the value of order_details.custom_id
     *
     * @mbg.generated
     */
    public Integer getCustomId() {
        return customId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.custom_id
     *
     * @param customId the value for order_details.custom_id
     *
     * @mbg.generated
     */
    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.notes
     *
     * @return the value of order_details.notes
     *
     * @mbg.generated
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.notes
     *
     * @param notes the value for order_details.notes
     *
     * @mbg.generated
     */
    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.create_date
     *
     * @return the value of order_details.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.create_date
     *
     * @param createDate the value for order_details.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.pay_state
     *
     * @return the value of order_details.pay_state
     *
     * @mbg.generated
     */
    public Integer getPayState() {
        return payState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.pay_state
     *
     * @param payState the value for order_details.pay_state
     *
     * @mbg.generated
     */
    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.end_date
     *
     * @return the value of order_details.end_date
     *
     * @mbg.generated
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.end_date
     *
     * @param endDate the value for order_details.end_date
     *
     * @mbg.generated
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.total
     *
     * @return the value of order_details.total
     *
     * @mbg.generated
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.total
     *
     * @param total the value for order_details.total
     *
     * @mbg.generated
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_details.order_details
     *
     * @return the value of order_details.order_details
     *
     * @mbg.generated
     */
    public String getOrderDetails() {
        return orderDetails;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_details.order_details
     *
     * @param orderDetails the value for order_details.order_details
     *
     * @mbg.generated
     */
    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails == null ? null : orderDetails.trim();
    }
}