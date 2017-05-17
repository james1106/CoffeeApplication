package com.mk.coffee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mk.coffee.entity.SkuEntity;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WXCard {
    private String cardType;
    private String title;
    private float discount;
    private float reduceCost;
    private String code;
    private String cardId;

    private SkuEntity sku;


    public WXCard() {
    }

    public WXCard(String code, String cardId) {
        this.code = code;
        this.cardId = cardId;
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

    public SkuEntity getSku() {
        return sku;
    }

    public void setSku(SkuEntity sku) {
        this.sku = sku;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
