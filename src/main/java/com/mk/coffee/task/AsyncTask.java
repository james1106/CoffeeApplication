package com.mk.coffee.task;

import com.mk.coffee.conf.weixin.WechatMpProperties;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.ProductConversionCode;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.DateUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * 异步任务
 * Created by Administrator on 2017/4/12 0012.
 */
@Component
public class AsyncTask {
    @Autowired
    private ProductConversionCodeService productConversionCodeService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WechatMpProperties wechatMpProperties;

    @Autowired
    private WxMpService wxMpService;

    @Async("makeCoffeesAsync")
    @Transactional
    public void doTask(ProductConversionCode productConversionCode, String vmc) throws InterruptedException {
        logger.info("Task1 started.");
        Thread.sleep(1000 * 60);//休眠1分钟
        ProductConversionCode productConversionCode1 = productConversionCodeService.getProductConversionCodeById(productConversionCode.getId());
        if (productConversionCode1.getConversionState() == 2) {
            //领取失败
            boolean flag = productConversionCodeService.updateProductConversionStateById(productConversionCode.getId());
            logger.info("领取状态：" + flag);
            if (flag) {
                Members members = productConversionCodeService.getMembersByProductConversionCode(productConversionCode.getOrderNum(), productConversionCode.getProductId());
                //发送模板消息到微信
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        .toUser(members.getOpenId())
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
                                        DateUtils.getDate2LStr2(new Date()), productConversionCode.getOrderNum())));
                try {
                    wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                    logger.info("Task1 error."+e.getMessage());
                }
            }
        }
        logger.info("Task1 end.");
    }

    //有返回值
    @Async
    public Future<String> doTask1(int productConverstionId) throws InterruptedException {
        logger.info("Task1 started.");
        Thread.sleep(1000 * 60 * 5);//休眠五分钟
        productConversionCodeService.updateProductConversionStateById(productConverstionId);
        logger.info("Task1 end.");

        return new AsyncResult<>("Task1 accomplished!");
    }
}
