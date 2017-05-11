package com.mk.coffee.requestbody;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class RequestCreateOrder {
    public long memberId;
    public String cardId;
    public String encryptCode;
    public int eNum = 0;
    public int[] shoppingCartsItemIds;
}
