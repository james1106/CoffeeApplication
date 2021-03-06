package com.mk.coffee.model;

import java.util.Date;

public class WxMessage {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_message.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_message.from_user
     *
     * @mbg.generated
     */
    private String fromUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_message.to_user
     *
     * @mbg.generated
     */
    private String toUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_message.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_message.msg_type
     *
     * @mbg.generated
     */
    private String msgType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_message.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_message
     *
     * @mbg.generated
     */
    public WxMessage(Integer id, String fromUser, String toUser, Date createDate, String msgType, String content) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.createDate = createDate;
        this.msgType = msgType;
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_message
     *
     * @mbg.generated
     */
    public WxMessage() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_message.id
     *
     * @return the value of wx_message.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_message.id
     *
     * @param id the value for wx_message.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_message.from_user
     *
     * @return the value of wx_message.from_user
     *
     * @mbg.generated
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_message.from_user
     *
     * @param fromUser the value for wx_message.from_user
     *
     * @mbg.generated
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser == null ? null : fromUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_message.to_user
     *
     * @return the value of wx_message.to_user
     *
     * @mbg.generated
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_message.to_user
     *
     * @param toUser the value for wx_message.to_user
     *
     * @mbg.generated
     */
    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_message.create_date
     *
     * @return the value of wx_message.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_message.create_date
     *
     * @param createDate the value for wx_message.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_message.msg_type
     *
     * @return the value of wx_message.msg_type
     *
     * @mbg.generated
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_message.msg_type
     *
     * @param msgType the value for wx_message.msg_type
     *
     * @mbg.generated
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_message.content
     *
     * @return the value of wx_message.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_message.content
     *
     * @param content the value for wx_message.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}