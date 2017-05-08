package com.mk.coffee.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.conf.weixin.WechatMpProperties;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.model.Members;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.model.WXCard;
import com.mk.coffee.service.MembersService;
import com.mk.coffee.service.WXInfoService;
import com.mk.coffee.utils.CalendarUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpCardResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
@Service
public class WXInfoServiceImpl implements WXInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatMpProperties wechatMpProperties;

    @Autowired
    private MembersService membersService;

    @Autowired
    private WxPayService wxPayService;

    @Override
    public WXCard getWXCart(String cardId, String encryptCode) {
        WXCard wxCard = new WXCard();
        String code;
        try {
            code = wxMpService.getCardService().decryptCardCode(encryptCode);
            wxCard.setCode(code);
            WxMpCardResult wxMpCardResult = wxMpService.getCardService().queryCardCode(cardId, code, true);
            //微信卡券不可用
            if (!wxMpCardResult.getErrorMsg().equals("ok")) {
                throw AppException.getException(wxMpCardResult.getErrorCode(), wxMpCardResult.getErrorMsg());
            }
            //占用
            //wxMpService.getCardService().markCardCode(code, cardId, members.getOpenId(), true);
            String cardDetail = wxMpService.getCardService().getCardDetail(cardId);

            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject) jsonParser.parse(cardDetail);
            if (jo.get("errcode").getAsInt() == 0) {
                JsonObject card = jo.getAsJsonObject("card");
                if (card.get("card_type").getAsString().equals("CASH")) { //代金券
                    wxCard.setCardType("CASH");
                    JsonObject cashJsonObject = card.getAsJsonObject("cash");
                    JsonObject baseInfoJsonObject = cashJsonObject.getAsJsonObject("base_info");
                    wxCard.setTitle(baseInfoJsonObject.get("title").getAsString());
                    float reductCost = (float) (cashJsonObject.get("reduce_cost").getAsFloat() * 0.01);
                    wxCard.setReduceCost(reductCost);
                } else if (card.get("card_type").getAsString().equals("DISCOUNT")) {//折扣券
                    wxCard.setCardType("DISCOUNT");
                    JsonObject discountJsonObject = card.getAsJsonObject("discount");
                    JsonObject baseInfoJsonObject = discountJsonObject.getAsJsonObject("base_info");
                    wxCard.setTitle(baseInfoJsonObject.get("title").getAsString());
                    float discount = (float) ((100 - discountJsonObject.get("discount").getAsInt()) * 0.01);
                    wxCard.setDiscount(discount);
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new AppException(e.getError().getErrorCode() + "", e.getMessage());
        }
        return wxCard;
    }

    @Override
    public String getAuthorizationUrl(String redirectUri) {
        return wxMpService.oauth2buildAuthorizationUrl(redirectUri, WxConsts.OAUTH2_SCOPE_BASE, null);
    }


    /**
     * 得到e豆充值的预支付信息
     *
     * @param ebeanRecord e豆充值纪录
     * @param address     IP
     * @return
     */
    @Override
    public RestResult<Map<String,String>> getEBeanRechargeWXJsPayInfo(EbeanRecord ebeanRecord, String address) {
        Members members = membersService.getMember(ebeanRecord.getMemberId());
        try {
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody(wechatMpProperties.getBody());
            //订单
            orderRequest.setOutTradeNo(ebeanRecord.getId() + "");
            orderRequest.setTradeType("JSAPI");
            orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(ebeanRecord.getMoney() + ""));//元转成分
            orderRequest.setNotifyURL("");
            orderRequest.setOpenid(members.getOpenId());
            orderRequest.setSpbillCreateIp(address);
            orderRequest.setTimeStart(CalendarUtil.formatChineseYearMonthDayMinuteSecond(new Date()));
            //orderRequest.setTimeExpire(DateUtils.getgetMinutesLaterStr(wechatMpProperties.getIntervalTime()));//设置过期时间

            Map<String, String> result = wxPayService.getPayInfo(orderRequest);
            return RestResultGenerator.genSuccessResult(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("微信支付失败！订单号：{},原因:{}", ebeanRecord.getId(), e.getMessage());
            throw AppException.getException(ErrorCode.Pay_Fail.getCode());
        }
    }


}
