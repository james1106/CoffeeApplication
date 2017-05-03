package com.mk.coffee.controller.rest;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.conf.coffees.MakeCoffeesProperties;
import com.mk.coffee.conf.weixin.WechatMpProperties;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.CoffeeMachine;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.ProductConversionCode;
import com.mk.coffee.requestbody.RequestMakeCoffees;
import com.mk.coffee.service.CoffeeMachineService;
import com.mk.coffee.service.MakeCoffeesService;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.DateUtils;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
@Api("制作咖啡接口")
@RestController
public class MakeCoffeesController {
    @Autowired
    private ProductConversionCodeService productConversionCodeService;

    @Autowired
    private MakeCoffeesService makeCoffeesService;

    @Autowired
    private MakeCoffeesProperties makeCoffeesProperties;

    @Autowired
    private WechatMpProperties wechatMpProperties;

    @Autowired
    private CoffeeMachineService coffeeMachineService;

    @Autowired
    private WxMpService wxMpService;

    @ApiOperation(value = "获取咖啡", notes = "根据兑换码，咖啡机器码（通过扫一扫二维码获取）获取咖啡")
    @PostMapping("/makeCoffeesByConversionCode")
    public RestResult<Boolean> makeCoffees(@RequestParam("conversionCode") String conversionCode, @RequestParam("vmc") String vmc) {
        ProductConversionCode productConversionCode = new ProductConversionCode();
        productConversionCode.setConversionCode(conversionCode);
        productConversionCode.setConversionState(0);
        //查询
        ProductConversionCode productConversionCode1 = productConversionCodeService.getProductConversionCodeByConversionCode(productConversionCode);
        if (productConversionCode1 == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        try {
            return RestResultGenerator.genSuccessResult(makeCoffeesService.makeCoffees(productConversionCode1, vmc));
        } catch (IOException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
        }
    }

    @ApiOperation(value = "获取咖啡", notes = "根据兑换码ID，咖啡机器码（通过扫一扫二维码获取）获取咖啡")
    @PostMapping("/makeCoffeesByConversionCodeId")
    public RestResult<Boolean> makeCoffeesByConversionCodeId(@RequestParam("id") int id, @RequestParam("vmc") String vmc) {
        ProductConversionCode productConversionCode = productConversionCodeService.getProductConversionCodeById(id);
        if (productConversionCode == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        if (productConversionCode.getConversionState() != 0) {
            throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
        }
        try {
            return RestResultGenerator.genSuccessResult(makeCoffeesService.makeCoffees(productConversionCode, vmc));
        } catch (IOException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
        }

    }

    @ApiOperation(value = "获取咖啡", notes = "根据兑换码ID或兑换码、制作咖啡机器码（通过扫一扫二维码获取）、自定义配方(id,name可不填)、获取咖啡")
    @PostMapping("/makeCoffeesByCustomConfig")
    public RestResult<Boolean> makeCoffeesByCustomConfig(@RequestBody RequestMakeCoffees requestMakeCoffees) {
        ProductConversionCode productConversionCode;
        if (requestMakeCoffees.id != 0) {
            ProductConversionCode productConversionCode1 = new ProductConversionCode();
            productConversionCode1.setConversionCode(requestMakeCoffees.conversionCode);
            productConversionCode1.setConversionState(0);
            //查询
            productConversionCode = productConversionCodeService.getProductConversionCodeByConversionCode(productConversionCode1);
        } else if (EmptyUtils.isEmpty(requestMakeCoffees.conversionCode)) {
            productConversionCode = productConversionCodeService.getProductConversionCodeById(requestMakeCoffees.id);
        } else {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }

        if (productConversionCode.getConversionState() != 0) {
            throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
        }
        return RestResultGenerator.genSuccessResult(makeCoffeesService.makeCoffeesByCustomConfigure(productConversionCode,
                requestMakeCoffees.vmc, requestMakeCoffees.customConfig));

    }


    @ApiOperation(value = "外卖", notes = "根据兑换码ID，咖啡机器码（通过后台指定机器码）获取咖啡")
    @PostMapping("/makeCoffeesTakeOutByConversionCodeId")
    public RestResult<Boolean> makeCoffeesByConversionCodeId(@RequestParam("id") int id) {
        ProductConversionCode productConversionCode = productConversionCodeService.getProductConversionCodeById(id);
        CoffeeMachine coffeeMachine = coffeeMachineService.getCoffeeMachine(1);
        if (productConversionCode == null || coffeeMachine == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        if (productConversionCode.getConversionState() != 0) {
            throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
        }
        try {
            return RestResultGenerator.genSuccessResult(makeCoffeesService.makeCoffees(productConversionCode, coffeeMachine.getCode()));
        } catch (IOException e) {
            e.printStackTrace();
            throw AppException.getException(ErrorCode.Make_Coffees_Fail.getCode());
        }

    }


    @ApiOperation(value = "制作咖啡通知回调", notes = "咖啡机后台通过调用该接口（ID:订单号，PID：商品ID，VMC:机器码，MAC:校验）" +
            "回调通知，返回成功则更新兑换码为已领取")
    @RequestMapping("/makeCoffeesNotify/PRODUCT")
    public RestResult<Boolean> makeCoffeesNotify(@RequestParam("ID") String ID, @RequestParam("PID") String PID,
                                                 @RequestParam("VMC") String VMC, @RequestParam("MAC") String MAC) {
        //ID=000005&PID=810&VMC=33333&USER=eleyin
        StringBuffer sb = new StringBuffer();
        sb.append("ID").append("=").append(ID).append("&")
                .append("PID").append("=").append(PID).append("&")
                .append("VMC").append("=").append(VMC).append("&")
                .append("USER").append("=").append(makeCoffeesProperties.getName());
        String md5Verify = MD5Util.md5Encode(sb.toString(), null);
        if (!md5Verify.equals(MAC)) {
            throw AppException.getException(ErrorCode.Make_Coffees_Verify_Fail.getCode());
        }
        //待领取更新为已领取
        boolean flag = productConversionCodeService.updateProductConversionState(ID, PID);

        Members members = productConversionCodeService.getMembersByProductConversionCode(ID, Integer.valueOf(PID));
        if (members != null) {
            //发送模板消息到微信
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(members.getOpenId())
                    .templateId(wechatMpProperties.getMakeCoffeesTemplateId())
                    .url(wechatMpProperties.getMakeCoffeesTemplateUrl())
                    .build();
            templateMessage.addWxMpTemplateData(
                    new WxMpTemplateData("first", "您制作的咖啡已完成", "#7CBC3B"));
            templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword1", VMC));//设备编号
            templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword2", "咖啡制作成功"));//制作结果
            new WxMpTemplateData("remark",
                    String.format("您在%s领取咖啡成功，兑换码ID为%s，欢迎再次使用", DateUtils.getDate2LStr2(new Date()), ID));
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return RestResultGenerator.genSuccessResult(flag);
    }

}
