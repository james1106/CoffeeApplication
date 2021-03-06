package com.mk.coffee.model;

import java.util.Date;

public class CooperativePartnerCoffeeMachine {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cooperative_partner_coffee_machine.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cooperative_partner_coffee_machine.cooperativer_parther_id
     *
     * @mbg.generated
     */
    private Integer cooperativerPartherId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cooperative_partner_coffee_machine.coffee_machine_id
     *
     * @mbg.generated
     */
    private Integer coffeeMachineId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cooperative_partner_coffee_machine.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cooperative_partner_coffee_machine
     *
     * @mbg.generated
     */
    public CooperativePartnerCoffeeMachine(Integer id, Integer cooperativerPartherId, Integer coffeeMachineId, Date createDate) {
        this.id = id;
        this.cooperativerPartherId = cooperativerPartherId;
        this.coffeeMachineId = coffeeMachineId;
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cooperative_partner_coffee_machine
     *
     * @mbg.generated
     */
    public CooperativePartnerCoffeeMachine() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cooperative_partner_coffee_machine.id
     *
     * @return the value of cooperative_partner_coffee_machine.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cooperative_partner_coffee_machine.id
     *
     * @param id the value for cooperative_partner_coffee_machine.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cooperative_partner_coffee_machine.cooperativer_parther_id
     *
     * @return the value of cooperative_partner_coffee_machine.cooperativer_parther_id
     *
     * @mbg.generated
     */
    public Integer getCooperativerPartherId() {
        return cooperativerPartherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cooperative_partner_coffee_machine.cooperativer_parther_id
     *
     * @param cooperativerPartherId the value for cooperative_partner_coffee_machine.cooperativer_parther_id
     *
     * @mbg.generated
     */
    public void setCooperativerPartherId(Integer cooperativerPartherId) {
        this.cooperativerPartherId = cooperativerPartherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cooperative_partner_coffee_machine.coffee_machine_id
     *
     * @return the value of cooperative_partner_coffee_machine.coffee_machine_id
     *
     * @mbg.generated
     */
    public Integer getCoffeeMachineId() {
        return coffeeMachineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cooperative_partner_coffee_machine.coffee_machine_id
     *
     * @param coffeeMachineId the value for cooperative_partner_coffee_machine.coffee_machine_id
     *
     * @mbg.generated
     */
    public void setCoffeeMachineId(Integer coffeeMachineId) {
        this.coffeeMachineId = coffeeMachineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cooperative_partner_coffee_machine.create_date
     *
     * @return the value of cooperative_partner_coffee_machine.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cooperative_partner_coffee_machine.create_date
     *
     * @param createDate the value for cooperative_partner_coffee_machine.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}