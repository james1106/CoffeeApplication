package com.mk.coffee.controller.rest;

import com.github.binarywang.wxpay.bean.WxPayOrderNotifyResponse;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayBaseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.conf.weixin.WechatMpProperties;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.service.MembersService;
import com.mk.coffee.service.OrderDetailsService;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.CalendarUtil;
import com.mk.coffee.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6 0006.
 */
@Api("支付接口")
@RestController
@RequestMapping(value = "pay")
public class WeixinPayController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private MembersService membersService;

    @Autowired
    private WechatMpProperties wechatMpProperties;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    protected WxMpConfigStorage configStorage;

    @Autowired
    private ProductConversionCodeService productConversionCodeService;

    @GetMapping("/getWXJsPayInfo")
    @ApiOperation(value = "得到微信预支付信息", notes = "根据订单号返回预支付信息")
    public RestResult getWXJsPayInfo(@RequestParam("orderId") String orderId, HttpServletRequest request) {
        try {
            //得到订单详情
            OrderDetails orderDetails = orderDetailsService.getOrderDetail(orderId);
            //得到会员信息
            Members members = membersService.getMember(orderDetails.getMembersId());

            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody(wechatMpProperties.getBody());
            //订单
            orderRequest.setOutTradeNo(orderDetails.getId());
            orderRequest.setTradeType("JSAPI");

            if (orderDetails.getDiscountMoney() == null) {
                orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(orderDetails.getMoney() + ""));//元转成分
            } else {
                orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(orderDetails.getDiscountMoney() + ""));//元转成分
            }
            orderRequest.setOpenid(members.getOpenId());
            orderRequest.setSpbillCreateIp(request.getRemoteAddr());
            orderRequest.setTimeStart(CalendarUtil.formatChineseYearMonthDayMinuteSecond(new Date()));
            //orderRequest.setTimeExpire(DateUtils.getgetMinutesLaterStr(wechatMpProperties.getIntervalTime()));//设置过期时间


            Map<String, String> result = wxPayService.getPayInfo(orderRequest);
            return RestResultGenerator.genSuccessResult(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("微信支付失败！订单号：{},原因:{}", orderId, e.getMessage());
            throw AppException.getException(ErrorCode.Pay_Fail.getCode());
        }
    }


    @GetMapping("/getWXJsPayInfoByOpenId")
    @ApiOperation(value = "根据openId得到微信预支付信息", notes = "根据订单号返回预支付信息")
    public RestResult getWXJsPayInfo(@RequestParam("orderId") String orderId, @RequestParam("openId") String openId, HttpServletRequest request) {
        try {
            //得到订单详情
            OrderDetails orderDetails = orderDetailsService.getOrderDetail(orderId);
            //得到会员信息
            Members members = membersService.getMember(orderDetails.getMembersId());

            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody(wechatMpProperties.getBody());
            //订单
            orderRequest.setOutTradeNo(orderDetails.getId());
            orderRequest.setTradeType("JSAPI");

            if (orderDetails.getDiscountMoney() == null) {
                orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(orderDetails.getMoney() + ""));//元转成分
            } else {
                orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(orderDetails.getDiscountMoney() + ""));//元转成分
            }
            orderRequest.setOpenid(openId);
            orderRequest.setSpbillCreateIp(request.getRemoteAddr());
            orderRequest.setTimeStart(CalendarUtil.formatChineseYearMonthDayMinuteSecond(new Date()));
            //orderRequest.setTimeExpire(DateUtils.getgetMinutesLaterStr(wechatMpProperties.getIntervalTime()));//设置过期时间

            Map<String, String> result = wxPayService.getPayInfo(orderRequest);
            return RestResultGenerator.genSuccessResult(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("微信支付失败！订单号：{},原因:{}", orderId, e.getMessage());
            throw AppException.getException(ErrorCode.Pay_Fail.getCode());
        }
    }


    @GetMapping("/getWXJsPayInfoTest")
    @ApiOperation(value = "得到微信预支付信息,用于测试，所有订单返回1分钱", notes = "根据订单号返回预支付信息")
    public RestResult getWXJsPayInfoTest(@RequestParam("orderId") String orderId, HttpServletRequest request) {
        try {
            //得到订单详情
            OrderDetails orderDetails = orderDetailsService.getOrderDetail(orderId);
            //得到会员信息
            Members members = membersService.getMember(orderDetails.getMembersId());

            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody(wechatMpProperties.getBody());
            //订单
            orderRequest.setOutTradeNo(orderDetails.getId());
            orderRequest.setTradeType("JSAPI");
            orderRequest.setTotalFee(1);//元转成分

            orderRequest.setOpenid(members.getOpenId());
            orderRequest.setSpbillCreateIp(request.getRemoteAddr());
            orderRequest.setTimeStart(CalendarUtil.formatChineseYearMonthDayMinuteSecond(new Date()));
            //orderRequest.setTimeExpire(DateUtils.getgetMinutesLaterStr(wechatMpProperties.getIntervalTime()));//设置过期时间

            Map<String, String> result = wxPayService.getPayInfo(orderRequest);
            return RestResultGenerator.genSuccessResult(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("微信支付失败！订单号：{},原因:{}", orderId, e.getMessage());
            throw AppException.getException(ErrorCode.Pay_Fail.getCode());
        }
    }


    /**
     * 微信通知支付结果的回调地址，notify_url
     */
    @RequestMapping("/notify")
    @Transactional
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        OrderDetails orderDetails = null;
        String orderId = null;
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result =wxPayService.getOrderNotifyResult(xmlResult);
            // 结果正确
            orderId = result.getOutTradeNo();
            String tradeNo = result.getTransactionId();
            String totalFee = WxPayBaseResult.feeToYuan(result.getTotalFee());
            //自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            orderDetails = orderDetailsService.getOrderDetail(orderId);
            if (orderDetails == null) {
                throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
            }
            if (orderDetails.getPayState() == 1) {//已支付过，通知
                WxPayOrderNotifyResponse.success("处理成功!");
                //已支付
                throw AppException.getException(ErrorCode.Already_Pay.getCode());
            }
            //更新支付状态
            orderDetails.setPayState(1);
            orderDetails.setEndDate(new Date());
            orderDetails.setId(orderId);
            orderDetailsService.updatePayState(orderDetails);
            //核销微信卡券
            if (orderDetails.getWxCardCode() != null && !orderDetails.getWxCardCode().equals("")) {
                wxMpService.getCardService().consumeCardCode(orderDetails.getWxCardCode());
                //将所有使用该微信卡券的订单重置金额
                orderDetailsService.updateOrderDetailsByWxCardCode(orderDetails.getWxCardCode());
            }
            //生成商品兑换码
            productConversionCodeService.createProductConversionCodeService(orderDetails);

            //发送模板消息到微信
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(result.getOpenid())
                    .templateId(wechatMpProperties.getPaySucceedTemplateId())
                    .url(wechatMpProperties.getPaySucceedTemplateUrl())
                    .build();

            templateMessage.addWxMpTemplateData(
                    new WxMpTemplateData("first", "您下单的商品支付成功", "#7CBC3B"));
            templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword1", totalFee));//金额
            templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword2", "E乐饮咖啡"));//商品详情
            templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword3", "微信支付"));//支付方式
            templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword4", orderDetails.getId()));//交易单号
            templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword5", DateUtils.getDate2LStr2(new Date())));//交易时间
            templateMessage.addWxMpTemplateData(
                    new WxMpTemplateData("remark", "请到附近的E乐饮咖啡机扫码制作咖啡自取", "#7CBC3B"));
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);

            return WxPayOrderNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            logger.error("微信回调结果异常,异常原因{}", e.getMessage());
            //支付异常
            if (orderDetails != null) {
                orderDetails.setPayState(2);
                orderDetails.setEndDate(new Date());
                orderDetails.setId(orderId);
                orderDetailsService.updatePayState(orderDetails);
            }
            return WxPayOrderNotifyResponse.fail(e.getMessage());
        }
    }


}
