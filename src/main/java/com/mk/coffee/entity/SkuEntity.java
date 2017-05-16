package com.mk.coffee.entity;

/**
 * 库存信息
 * Created by Administrator on 2017/5/16 0016.
 */
public class SkuEntity {
    private int quantity;
    private int total_quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }
}
