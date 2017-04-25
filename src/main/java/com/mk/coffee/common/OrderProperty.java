package com.mk.coffee.common;

/**
 * 要排序的字段枚举
 * Created by Administrator on 2017/3/1 0001.
 */
public enum OrderProperty {
    SALES("sales"),//销量
    CREATE_DATE("create_date"),//时间
    PRICE("price");//价格
    private String property;

    OrderProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
