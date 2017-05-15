package com.mk.coffee.entity;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public class WxCard {
    private String cardId;
    private String code;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public WxCard() {
    }

    public WxCard(String cardId, String code) {
        this.cardId = cardId;
        this.code = code;
    }
}
