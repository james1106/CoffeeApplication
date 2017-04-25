package com.mk.coffee.service;

import com.mk.coffee.exception.AppException;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
public interface VerificationCodeService {


    //得到验证码
    boolean getVerificationCode(String phone, int validityMinute) throws AppException;

    //验证
    boolean verificationByCode(int code);
}
