package com.mk.coffee.utils;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.OrderProperty;
import com.mk.coffee.common.OrderType;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.LocalAuthMapper;
import com.mk.coffee.mapper.OauthMapper;
import com.mk.coffee.model.LocalAuth;
import com.mk.coffee.model.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
public class VerifyUtils {

    public static boolean verifyPhoneNum(String phone) throws AppException {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        if (!p.matcher(phone).matches()) {
            throw AppException.getException(ErrorCode.Phone_Illegal.getCode());
        }
        return true;
    }

    /**
     * 生成四位随机数
     *
     * @return
     */
    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }
        return fourRandom;
    }

    /**
     * 生成五位随机数
     *
     * @return
     */
    public static String getFiveRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(100000) + "";
        int randLength = fourRandom.length();
        if (randLength < 5) {
            for (int i = 1; i <= 5 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }

        return fourRandom;
    }


    /**
     * 生成八位随机数
     *
     * @return
     */
    public static String getEightRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(100000000) + "";
        int randLength = fourRandom.length();
        if (randLength < 8) {
            for (int i = 1; i <= 8 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }

        return fourRandom;
    }

    /**
     * 验证验证码
     *
     * @param phone
     * @param code
     * @param olderVerificationCode
     * @return
     */
    public static boolean verificationCode(String phone, String code, VerificationCode olderVerificationCode) {
        //验证码不存在
        if (olderVerificationCode == null) {
            throw AppException.getException(ErrorCode.Verification_Code_Not_Exist.getCode());
        }
        //验证码不正确
        if (!olderVerificationCode.getCode().equals(code) || !phone.equals(olderVerificationCode.getPhone())) {
            throw AppException.getException(ErrorCode.Verification_Code_Error.getCode());
        }
        //验证码已失效
        if (System.currentTimeMillis() - olderVerificationCode.getCreateDate().getTime() > olderVerificationCode.getValidityMinute() * 1000 * 60) {
            throw AppException.getException(ErrorCode.Verification_Code_Invalid.getCode());
        }

        return true;
    }


    /**
     * 验证Token
     *
     * @param token
     * @param localAuth
     * @return
     */
    public static boolean verificationToken(String token, LocalAuth localAuth) {

        //Token不存在
        if (localAuth.getToken() == null || localAuth.getToken().equals("")) {
            throw AppException.getException(ErrorCode.Token_Not_Exist.getCode());
        }
        //Token不正确
        if (!token.equals(localAuth.getToken())) {
            throw AppException.getException(ErrorCode.Token_Error.getCode());
        }
        //Token已失效
        if (System.currentTimeMillis() / 1000 - localAuth.getCreateDate().getTime() / 1000 > localAuth.getValidityDay() * 86400) {
            throw AppException.getException(ErrorCode.Token_Invalid.getCode());
        }

        return true;
    }

    /**
     * 检查排序的值是否合法
     *
     * @param orderProperty
     * @return
     */
    public static OrderProperty verificationOrderProperty(String orderProperty) {
        OrderProperty[] orderPropertyList = OrderProperty.values();
        for (OrderProperty orderProperty1 : orderPropertyList) {
            if (orderProperty.trim().equals(orderProperty1.getProperty())) {
                return orderProperty1;
            }
        }
        throw AppException.getException(ErrorCode.ILLEGAL_PARAMS.getCode());
    }

    public static OrderType verificationOrderType(String orderType) {
        OrderType[] orderTypes = OrderType.values();
        for (OrderType orderType1 : orderTypes) {
            if (orderType.trim().equals(orderType1.getValue())) {
                return orderType1;
            }
        }
        throw AppException.getException(ErrorCode.ILLEGAL_PARAMS.getCode());
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().equals("");
    }


    // 判断一个字符串是否都为数字
    public static boolean isDigit(String strNum) {
        return strNum.matches("\\d*");
    }
}
