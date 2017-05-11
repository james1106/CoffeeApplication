package com.mk.coffee.utils;

import com.mk.coffee.conf.weixin.WechatMpProperties;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 模板消息发送
 * Created by Administrator on 2017/5/11 0011.
 */
@Component
public class TemplateMessageUtils {

    @Autowired
    private WechatMpProperties wechatMpProperties;
    @Autowired
    private WxMpService wxMpService;

    //支付成功消息
    public void sendPaySucceedTemplateMessage(String openId, String orderId, String money) throws WxErrorException {
        //发送模板消息到微信
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(wechatMpProperties.getPaySucceedTemplateId())
                .url(wechatMpProperties.getPaySucceedTemplateUrl())
                .build();
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("first", "您下单的商品支付成功", "#7CBC3B"));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword1", money));//金额
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword2", "E乐饮咖啡"));//商品详情
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword3", "微信支付"));//支付方式
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword4", orderId));//交易单号
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword5", DateUtils.getDate2LStr2(new Date())));//交易时间
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("remark", "请到附近的E乐饮咖啡机扫码制作咖啡自取", "#7CBC3B"));
        wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    }

    public void sendMakeCoffeeSucceed(String openId, String VMC, String ID) {
        //发送模板消息到微信
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
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

    public void sendMakeCoffeeFail(String openId, String vmc, String orderNum) {
        //发送模板消息到微信
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(wechatMpProperties.getMakeCoffeesTemplateId())
                .url(wechatMpProperties.getMakeCoffeesTemplateUrl())
                .build();
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("first", "制作咖啡失败", "#7CBC3B"));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword1", vmc));//设备编号
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword2", "咖啡制作失败"));//制作结果
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("remark",
                        String.format("您在%s领取咖啡失败，兑换码ID为%s，对此非常抱歉，请您联系咖啡机管理员电话",
                                DateUtils.getDate2LStr2(new Date()), orderNum)));
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
