package com.mk.coffee.model;

import java.util.Date;

public class Activity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.sub_title
     *
     * @mbg.generated
     */
    private String subTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.type
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.wx_card_id
     *
     * @mbg.generated
     */
    private String wxCardId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.start_time
     *
     * @mbg.generated
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.end_time
     *
     * @mbg.generated
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.picture_url
     *
     * @mbg.generated
     */
    private String pictureUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column activity.enter_url
     *
     * @mbg.generated
     */
    private String enterUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table activity
     *
     * @mbg.generated
     */
    public Activity(Integer id, String title, String subTitle, String content, Integer type, String wxCardId, Date createDate, Date startTime, Date endTime, String pictureUrl, String enterUrl) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.type = type;
        this.wxCardId = wxCardId;
        this.createDate = createDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pictureUrl = pictureUrl;
        this.enterUrl = enterUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table activity
     *
     * @mbg.generated
     */
    public Activity() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.id
     *
     * @return the value of activity.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.id
     *
     * @param id the value for activity.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.title
     *
     * @return the value of activity.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.title
     *
     * @param title the value for activity.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.sub_title
     *
     * @return the value of activity.sub_title
     *
     * @mbg.generated
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.sub_title
     *
     * @param subTitle the value for activity.sub_title
     *
     * @mbg.generated
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.content
     *
     * @return the value of activity.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.content
     *
     * @param content the value for activity.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.type
     *
     * @return the value of activity.type
     *
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.type
     *
     * @param type the value for activity.type
     *
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.wx_card_id
     *
     * @return the value of activity.wx_card_id
     *
     * @mbg.generated
     */
    public String getWxCardId() {
        return wxCardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.wx_card_id
     *
     * @param wxCardId the value for activity.wx_card_id
     *
     * @mbg.generated
     */
    public void setWxCardId(String wxCardId) {
        this.wxCardId = wxCardId == null ? null : wxCardId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.create_date
     *
     * @return the value of activity.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.create_date
     *
     * @param createDate the value for activity.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.start_time
     *
     * @return the value of activity.start_time
     *
     * @mbg.generated
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.start_time
     *
     * @param startTime the value for activity.start_time
     *
     * @mbg.generated
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.end_time
     *
     * @return the value of activity.end_time
     *
     * @mbg.generated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.end_time
     *
     * @param endTime the value for activity.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.picture_url
     *
     * @return the value of activity.picture_url
     *
     * @mbg.generated
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.picture_url
     *
     * @param pictureUrl the value for activity.picture_url
     *
     * @mbg.generated
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column activity.enter_url
     *
     * @return the value of activity.enter_url
     *
     * @mbg.generated
     */
    public String getEnterUrl() {
        return enterUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column activity.enter_url
     *
     * @param enterUrl the value for activity.enter_url
     *
     * @mbg.generated
     */
    public void setEnterUrl(String enterUrl) {
        this.enterUrl = enterUrl == null ? null : enterUrl.trim();
    }
}