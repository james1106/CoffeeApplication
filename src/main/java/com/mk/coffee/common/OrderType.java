package com.mk.coffee.common;

/**
 * 排序枚举
 * Created by Administrator on 2017/3/1 0001.
 */
public enum OrderType {
    ASC("ASC"), DESC("DESC");
    private String value;

    public String getValue() {
        return value;
    }

    OrderType(String value) {
        this.value = value;
    }


}
