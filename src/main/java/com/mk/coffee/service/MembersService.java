package com.mk.coffee.service;

import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.LoginInfo;
import com.mk.coffee.model.Members;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
public interface MembersService {

    //注册
    boolean register(Members members, String code) throws AppException;


    //手机号与open_id绑定
    boolean registerPhoneByCodeBindOpenId(String phone, String code, String openId);

    //通过关注公众号注册
    boolean registerByWxMpUser(WxMpUser wxMpUser);

    //登录
    LoginInfo login(String phone, String code) throws AppException;


    //游客登录
    LoginInfo touristsLogin(String phone, String code) throws AppException;

    //通过open_id登录
    LoginInfo loginByOpenId(String openID) throws IOException;


    //得到会员信息
    Members getMember(long memberId) throws AppException;


    //修改会员信息
    boolean updateMember(Members members) throws AppException;


    boolean inertMemberByOpenId(Members members);

    //通过code登录，即是将获取open_id+通过open_id登录结合
    LoginInfo loginByCode(String code);

}
