package com.mk.coffee.task;

import com.mk.coffee.model.Members;
import com.mk.coffee.model.ProductConversionCode;
import com.mk.coffee.service.ProductConversionCodeService;
import com.mk.coffee.utils.TemplateMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Timer;
import java.util.TimerTask;
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
    private TemplateMessageUtils templateMessageUtils;

    @Async("makeCoffeesAsync")
    @Transactional
    public void doTask(ProductConversionCode productConversionCode, String vmc) throws InterruptedException {
        logger.info("Task1 started.");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                logger.info("-------设定指定任务：更新领取兑换码状态--------");
                ProductConversionCode productConversionCode1 = productConversionCodeService
                        .getProductConversionCodeById(productConversionCode.getId());
                if (productConversionCode1.getConversionState() == 2) {
                    //领取失败
                    boolean flag = productConversionCodeService.updateProductConversionStateById(productConversionCode.getId());
                    logger.info("领取状态：" + flag);
                    if (flag) {
                        Members members = productConversionCodeService.getMembersByProductConversionCode(productConversionCode
                                .getOrderNum(), productConversionCode.getProductId());
                        //发送模板消息到微信
                        templateMessageUtils.sendMakeCoffeeFail(members.getOpenId(), vmc, productConversionCode.getOrderNum());
                    }
                }
            }
        }, 1000 * 90);// 设定指定的时间time,此处为2000毫秒

        logger.info("Task1 end.");
    }

    //有返回值
    @Async
    public void doTask1(int productConverstionId) throws InterruptedException {
        logger.info("Task1 started.");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                logger.info("Task1 执行." + productConverstionId);
            }
        }, 1000 * 90);// 设定指定的时间time,此处为2000毫秒

        logger.info("Task1 end.");


    }
}
