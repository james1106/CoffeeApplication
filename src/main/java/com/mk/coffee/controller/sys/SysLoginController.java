package com.mk.coffee.controller.sys;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.SysUser;
import com.mk.coffee.requestbody.RequestUserAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * 登录相关
 * Created by Administrator on 2017/4/10 0010.
 */
@RestController
@Api("后台登录相关")
@RequestMapping(value = "sys")
public class SysLoginController {


    /**
     * 用户名登录
     *
     * @param requestUserAdmin
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public RestResult<SysUser> login(@RequestBody RequestUserAdmin requestUserAdmin) {
        try {
            Subject subject = SecurityUtils.getSubject();
           /* //sha256加密
            password = new Sha256Hash(password).toHex();*/
            UsernamePasswordToken token = new UsernamePasswordToken(requestUserAdmin.username, requestUserAdmin.password);
            token.setRememberMe(requestUserAdmin.isRememberMe);
            subject.login(token);
        } catch (AuthenticationException e) {
            throw AppException.getException(ErrorCode.User_Not_Exist.getCode(), e.getMessage());
        }
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysUser.setPassword(null);

        return RestResultGenerator.genSuccessResult(sysUser);
    }

    @PostMapping("/logout")
    @ApiOperation("注销")
    public RestResult<Boolean> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return RestResultGenerator.genSuccessResult(true);
    }

    @GetMapping("getCurrentSysUser")
    @ApiOperation("得到当前登录的用户")
    public RestResult<SysUser> getSysUser() {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) {
            throw AppException.getException(ErrorCode.Current_User_Not_Exist);
        }
        sysUser.setPassword(null);
        return RestResultGenerator.genSuccessResult(sysUser);
    }

}
