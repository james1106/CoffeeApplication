package com.mk.coffee.model;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
public class WXCard {
    private String cardType;
    private String title;
    private float discount;
    private float reduceCost;
    private String code;


    public WXCard() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getReduceCost() {
        return reduceCost;
    }

    public void setReduceCost(float reduceCost) {
        this.reduceCost = reduceCost;
    }
}
