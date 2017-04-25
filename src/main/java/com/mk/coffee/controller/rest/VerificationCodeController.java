package com.mk.coffee.controller.rest;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.service.VerificationCodeService;
import com.mk.coffee.utils.VerifyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/2/27 0027.
 */
@Api("验证码接口")
@RestController
public class VerificationCodeController {
    @Autowired
    VerificationCodeService verificationCodeServie;

    @ApiOperation(notes = "得到验证码,返回成功失败，validityMinute为有效期（分钟）,默认五分钟，可以不传", value = "得到验证码,返回成功失败", httpMethod = "POST")
    @PostMapping("/verificationCode/{phone}")
    public RestResult<Boolean> getVerificationCode(@PathVariable("phone") String phone, @RequestParam(value = "validityMinute", required = false, defaultValue = "5") int validityMinute) throws AppException {
        VerifyUtils.verifyPhoneNum(phone);
        return RestResultGenerator.genSuccessResult(verificationCodeServie.getVerificationCode(phone, validityMinute));
    }
}
