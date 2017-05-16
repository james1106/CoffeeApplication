package com.mk.coffee.entity;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class WxCardExt {
    private String code = "";
    private String openid = "";
    private long timestamp;
    private String signature;

    public WxCardExt() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
