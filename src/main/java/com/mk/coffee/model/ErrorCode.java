package com.mk.coffee.model;

public class ErrorCode {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_code.id
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_code.code
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column error_code.message
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    private String message;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_code
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public ErrorCode(Integer id, String code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table error_code
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public ErrorCode() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_code.id
     *
     * @return the value of error_code.id
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_code.id
     *
     * @param id the value for error_code.id
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_code.code
     *
     * @return the value of error_code.code
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_code.code
     *
     * @param code the value for error_code.code
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column error_code.message
     *
     * @return the value of error_code.message
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column error_code.message
     *
     * @param message the value for error_code.message
     *
     * @mbg.generated Mon Feb 27 15:34:27 CST 2017
     */
    public void setMessage(String message) {
        this.message = message;
    }
}