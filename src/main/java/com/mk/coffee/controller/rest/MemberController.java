package com.mk.coffee.controller.rest;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.*;
import com.mk.coffee.model.LoginInfo;
import com.mk.coffee.model.Members;
import com.mk.coffee.requestbody.RequestMember;
import com.mk.coffee.requestbody.RequestPhoneAndCode;
import com.mk.coffee.service.MembersService;
import com.mk.coffee.utils.VerifyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
@Api("会员相关接口")
@RestController
public class MemberController {


    @Autowired
    MembersService membersService;

    @Autowired
    private WxMpService wxMpService;

    @ApiOperation(notes = "注册，返回成功失败", value = "注册", httpMethod = "POST")
    @PostMapping("/register/{phone}/{code}")
    public RestResult<Boolean> register(@RequestBody RequestPhoneAndCode phoneAndCode) throws AppException {
        VerifyUtils.verifyPhoneNum(phoneAndCode.phone);
        Members members = new Members();
        members.setId(System.currentTimeMillis());
        members.setPhone(phoneAndCode.phone);
        members.setCreateDate(new Date());
        return RestResultGenerator.genSuccessResult(membersService.register(members, phoneAndCode.code));
    }

    @ApiOperation(notes = "通过发送短信验证码登录，返回会员token和会员ID，token作为请求头，作用在需要验证的接口上", value = "登录", httpMethod = "POST")
    @PostMapping("/login")
    public RestResult<LoginInfo> login(@RequestBody RequestPhoneAndCode phoneAndCode) throws AppException {
        return RestResultGenerator.genSuccessResult(membersService.login(phoneAndCode.phone, phoneAndCode.code));
    }

    @ApiOperation(notes = "通过发送短信验证码游客登录，返回会员token和会员ID，token作为请求头，作用在需要验证的接口上", value = "游客登录", httpMethod = "POST")
    @PostMapping("/login/tourists")
    public RestResult<LoginInfo> touristsLogin(@RequestBody RequestPhoneAndCode phoneAndCode) {
        return RestResultGenerator.genSuccessResult(membersService.touristsLogin(phoneAndCode.phone, phoneAndCode.code));
    }

    @ApiOperation(notes = "通过微信openId登录，返回会员token和会员ID，token作为请求头，作用在需要验证的接口上", value = "微信OpenId登录", httpMethod = "POST")
    @PostMapping("/loginByOpenId")
    public RestResult<LoginInfo> loginByOpenId(@RequestParam("openId") String openId) throws IOException {
        return RestResultGenerator.genSuccessResult(membersService.loginByOpenId(openId));
    }

    @ApiOperation(value = "通过获取code登录", notes = "通过code来获取open_id，再通过open_id登录", httpMethod = "POST")
    @PostMapping("/loginByCode")
    public RestResult<LoginInfo> loginByCode(@RequestParam("code") String code) throws IOException {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            return RestResultGenerator.genSuccessResult(membersService.loginByOpenId(wxMpOAuth2AccessToken.getOpenId()));
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Get_AccessToken_Fail.getCode(), e.getMessage());
        }
    }

    @ApiOperation(notes = "通过短信验证码绑定手机号和微信openId，如果同时存在两者纪录，优先取手机的数据，丢失微信部分的数据.返回成功或失败", value = "绑定手机号与open_id", httpMethod = "POST")
    @PostMapping("/registerPhoneByCodeBindOpenId")
    public RestResult<Boolean> registerPhoneByCodeBindOpenId(@RequestBody RequestPhoneAndCode phoneAndCode, @RequestParam("openId") String openId) {
        return RestResultGenerator.genSuccessResult(membersService.registerPhoneByCodeBindOpenId(phoneAndCode.phone, phoneAndCode.code, openId));
    }


    //得到会员资料
    @ApiOperation(notes = "得到会员资料，返回会员资料", value = "得到会员资料", httpMethod = "GET")
    @GetMapping("/member/{memberId}")
    public RestResult<Members> getMember(@PathVariable("memberId") long id) throws AppException {

        return RestResultGenerator.genSuccessResult(membersService.getMember(id));
    }

    //修改会员资料
    @ApiOperation(notes = "修改会员资料,返回成功失败", value = "修改会员资料", httpMethod = "PUL")
    @PutMapping("/member")
    public RestResult<Boolean> updateMember(@RequestBody RequestMember requestMember) throws AppException {
        Members members = new Members();
        members.setId(requestMember.id);
        members.setName(requestMember.name);
        members.setEmail(requestMember.email);
        members.setPhone(requestMember.phone);
        members.setSex(requestMember.sex);
        return RestResultGenerator.genSuccessResult(membersService.updateMember(members));
    }


}
