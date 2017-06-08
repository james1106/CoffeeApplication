package com.mk.coffee.requestbody;

/**
 * 生成外卖订单
 * Created by Administrator on 2017/3/31 0031.
 */
public class RequestCreateTakeOutOrder {
    public long memberId;
    public String cardId;
    public String encryptCode;
    public int eNum = 0;
    public int[] shoppingCartsItemIds;
    public int coffeeMachineId;//咖啡机器Id
    public int addressId;

}
