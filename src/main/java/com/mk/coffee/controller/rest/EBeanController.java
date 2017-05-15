package com.mk.coffee.controller.rest;

import com.github.binarywang.wxpay.bean.WxPayOrderNotifyResponse;
import com.github.binarywang.wxpay.bean.result.WxPayBaseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.Ebean;
import com.mk.coffee.model.EbeanProduct;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.requestbody.RequestEBeanRecharge;
import com.mk.coffee.service.EBeanProductService;
import com.mk.coffee.service.EBeanRecordService;
import com.mk.coffee.service.EBeanServie;
import com.mk.coffee.service.WXInfoService;
import com.mk.coffee.utils.CommonUtils;
import com.mk.coffee.utils.VerifyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
@Api("E豆接口")
@RestController
public class EBeanController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EBeanProductService eBeanProductService;

    @Autowired
    private EBeanRecordService eBeanRecordService;

    @Autowired
    private EBeanServie eBeanServie;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WXInfoService wxInfoService;

    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private CommonUtils commonUtils;


    @ApiOperation(value = "e豆充值优惠产品列表", httpMethod = "GET")
    @GetMapping("/getEBeanProductList")
    public RestResult<List<EbeanProduct>> getEBeanProductList() {
        return RestResultGenerator.genSuccessResult(eBeanProductService.getList());
    }


    @ApiOperation(value = "e豆充值", notes = "ebeanProductId不为空，则根据ebeanProductId充值，如果money不为空,则根据money充值，" +
            "返回e豆充值预支付信息",
            httpMethod = "POST")
    @PostMapping("/ebeanRecharge")
    public RestResult<Map<String, String>> ebeanRecharge(@RequestBody RequestEBeanRecharge recharge, HttpServletRequest request) {
        EbeanRecord ebeanRecord;
        if (recharge.ebeanProductId != null) {
            EbeanProduct ebeanProduct = eBeanProductService.getItem(recharge.ebeanProductId);
            if (ebeanProduct == null) {
                throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
            }
            //插入e豆充值纪录
            ebeanRecord = commonUtils.createEbeanRecord(recharge.memberId, ebeanProduct);
        } else {
            ebeanRecord = commonUtils.createEbeanRecordByMoney(recharge.memberId, recharge.money);
        }

        eBeanRecordService.addItem(ebeanRecord);
        return wxInfoService.getEBeanRechargeWXJsPayInfo(ebeanRecord, request.getRemoteAddr());
    }

    /**
     * 微信通知支付结果的回调地址，notify_url
     */
    @RequestMapping("/ebeanRechargeNotify")
    @Transactional
    public String ebeanRechargeNotify(HttpServletRequest request, HttpServletResponse response) {
        EbeanRecord ebeanRecord = null;

        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.getOrderNotifyResult(xmlResult);
            // 结果正确
            ebeanRecord = eBeanRecordService.getItem(Long.valueOf(result.getOutTradeNo()));
            if (ebeanRecord.getPayState() == 1) {
                return WxPayOrderNotifyResponse.success("处理成功!");
            }
            //得到成员的拥有的e豆
            Ebean ebean = eBeanServie.getEbeanByMemberId(ebeanRecord.getMemberId());
            if (ebean == null) {
                commonUtils.createEbean(ebeanRecord);
            } else {
                commonUtils.updateEbean(ebeanRecord, ebean);
            }
            //更新已支付
            ebeanRecord.setPayState(1);
            ebeanRecord.setCreateDate(new Date());
            eBeanRecordService.updateItem(ebeanRecord);
            return WxPayOrderNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("微信回调结果异常,异常原因{}", e.getMessage());
            //支付异常
            if (ebeanRecord != null) {
                ebeanRecord.setPayState(2);
                eBeanRecordService.updateItem(ebeanRecord);
            }
            return WxPayOrderNotifyResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getEBeanByMemberId")
    @ApiOperation(value = "得到会员E豆信息", notes = "根据memberId得到会员E豆信息", httpMethod = "GET")
    public RestResult<Ebean> getEBeanByMemberId(@RequestParam("memberId") long memberId) {
        return RestResultGenerator.genSuccessResult(eBeanServie.getEbeanByMemberId(memberId));
    }

}
