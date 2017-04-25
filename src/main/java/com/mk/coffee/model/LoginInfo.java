package com.mk.coffee.model;

import java.io.Serializable;

/**
 * 登录返回的信息模型
 */
public class LoginInfo implements Serializable {
    long memberId;//会员Id
    String token;//认证令牌

    public LoginInfo(long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    public LoginInfo() {
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "memberId=" + memberId +
                ", token='" + token + '\'' +
                '}';
    }
}
