package com.mk.coffee.model;

import java.io.Serializable;

/**
 * 购物车金额信息
 * Created by Administrator on 2017/4/25 0025.
 */
public class ShoppingCartTotal implements Serializable {
    private float totalMoney;//总金额
    private float discountMoney;//优惠后的金额
    private float deductionMoney;//减免的金额
    private int count;//总数量

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public float getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(float discountMoney) {
        this.discountMoney = discountMoney;
    }

    public float getDeductionMoney() {
        return deductionMoney;
    }

    public void setDeductionMoney(float deductionMoney) {
        this.deductionMoney = deductionMoney;
    }
}
