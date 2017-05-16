package com.mk.coffee.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.conf.weixin.WechatMpProperties;
import com.mk.coffee.entity.*;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.model.*;
import com.mk.coffee.model.WXCard;
import com.mk.coffee.requestbody.RequestCardIdList;
import com.mk.coffee.service.*;
import com.mk.coffee.utils.CalendarUtil;
import com.mk.coffee.utils.CommonUtils;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.JsonUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpCardResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private EBeanServie eBeanServie;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private ProductConversionCodeService productConversionCodeService;

    @Autowired
    private CommonUtils commonUtils;


    @Override
    public WXCard getWXCart(String cardId, String encryptCode) {
        WXCard wxCard;
        String code;
        try {
            code = wxMpService.getCardService().decryptCardCode(encryptCode);
            WxMpCardResult wxMpCardResult = wxMpService.getCardService().queryCardCode(cardId, code, true);
            //微信卡券不可用
            if (!wxMpCardResult.getErrorMsg().equals("ok")) {
                throw AppException.getException(wxMpCardResult.getErrorCode(), wxMpCardResult.getErrorMsg());
            }
            wxCard = createWXCardByCardId(cardId);
            wxCard.setCode(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new AppException(e.getError().getErrorCode() + "", e.getMessage());
        }
        return wxCard;
    }


    public WXCard createWXCardByCardId(String cardId) throws WxErrorException {
        WXCard wxCard = new WXCard();
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
                wxCard.setCardId(baseInfoJsonObject.get("id").getAsString());
                //设置库存信息
                SkuEntity skuEntity = new SkuEntity();
                JsonObject sku = baseInfoJsonObject.get("sku").getAsJsonObject();
                skuEntity.setQuantity(sku.get("quantity").getAsInt());
                skuEntity.setTotal_quantity(sku.get("total_quantity").getAsInt());
                wxCard.setSku(skuEntity);
                float reductCost = (float) (cashJsonObject.get("reduce_cost").getAsFloat() * 0.01);
                wxCard.setReduceCost(reductCost);
            } else if (card.get("card_type").getAsString().equals("DISCOUNT")) {//折扣券
                wxCard.setCardType("DISCOUNT");
                JsonObject discountJsonObject = card.getAsJsonObject("discount");
                JsonObject baseInfoJsonObject = discountJsonObject.getAsJsonObject("base_info");
                wxCard.setTitle(baseInfoJsonObject.get("title").getAsString());
                wxCard.setCardId(baseInfoJsonObject.get("id").getAsString());
                //设置库存信息
                SkuEntity skuEntity = new SkuEntity();
                JsonObject sku = baseInfoJsonObject.get("sku").getAsJsonObject();
                skuEntity.setQuantity(sku.get("quantity").getAsInt());
                skuEntity.setTotal_quantity(sku.get("total_quantity").getAsInt());
                wxCard.setSku(skuEntity);
                float discount = (float) ((100 - discountJsonObject.get("discount").getAsInt()) * 0.01);
                wxCard.setDiscount(discount);
            }
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
    public RestResult<Map<String, String>> getEBeanRechargeWXJsPayInfo(EbeanRecord ebeanRecord, String address) {
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


    /**
     * 测试得到e豆充值的预支付信息
     *
     * @param ebeanRecord e豆充值纪录
     * @param address     IP
     * @return
     */
    @Override
    public RestResult<Map<String, String>> getEBeanRechargeWXJsPayInfoTest(EbeanRecord ebeanRecord, String address) {
        Members members = membersService.getMember(ebeanRecord.getMemberId());
        try {
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody(wechatMpProperties.getBody());
            //订单
            orderRequest.setOutTradeNo(ebeanRecord.getId() + "");
            orderRequest.setTradeType("JSAPI");
            orderRequest.setTotalFee(1);//元转成分
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


    @Override
    @Transactional
    public boolean orderCheckout(OrderDetails orderDetails) throws WxErrorException {
        if (orderDetails.getPayState() == 1) {//已支付过，通知
            throw AppException.getException(ErrorCode.Already_Pay);
        }
        //更新支付状态
        orderDetails.setPayState(1);
        orderDetails.setEndDate(new Date());
        orderDetails.setId(orderDetails.getId());
        orderDetailsService.updatePayState(orderDetails);
        //更新e豆
        if (orderDetails.getBean() != null && orderDetails.getBean() > 0) {
            Ebean ebean = eBeanServie.getEbeanByMemberId(orderDetails.getMembersId());
            eBeanServie.updateItem(commonUtils.useEbean(ebean, orderDetails.getBean()));
        }
        //核销微信卡券
        if (orderDetails.getWxCardCode() != null && !orderDetails.getWxCardCode().equals("")) {
            wxMpService.getCardService().consumeCardCode(orderDetails.getWxCardCode());
            //将所有使用该微信卡券的订单重置金额
            orderDetailsService.updateOrderDetailsByWxCardCode(orderDetails.getWxCardCode());
        }
        //生成商品兑换码
        return productConversionCodeService.createProductConversionCodeService(orderDetails);

    }

    @Override
    public List<WxCard> getCardList(long memberId) throws WxErrorException {
        List<WxCard> list = new ArrayList<>();
        Members members = membersService.getMember(memberId);
        if (members == null) {
            throw AppException.getException(ErrorCode.Member_Not_Exist);
        }

        if (EmptyUtils.isEmpty(members.getOpenId())) {
            throw AppException.getException(ErrorCode.Member_Unbound_WX);
        }

        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/card/user/getcardlist?");
        JsonObject data = new JsonObject();
        data.addProperty("openid", members.getOpenId());
        String respone = wxMpService.post(url.toString(), data.toString());
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(respone);
        if (jo.get("errcode").getAsInt() != 0) {
            throw AppException.getException(ErrorCode.Get_WX_Card_Fail);
        }
        JsonArray card_list = jo.getAsJsonArray("card_list");
        if (card_list == null && card_list.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        for (int i = 0; i < card_list.size(); i++) {
            JsonObject card = card_list.get(i).getAsJsonObject();
            list.add(new WxCard(card.get("card_id").getAsString(), card.get("code").getAsString()));
        }
        return list;
    }

    /**
     * 分页批量查询卡列表
     *
     * @param page
     * @param size
     * @return
     * @throws WxErrorException
     */
    @Override
    public List<WXCard> getCardIdList(int page, int size) throws WxErrorException {
        List<WXCard> cardIdList = new ArrayList<>();
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/card/batchget?");
        int offset = (page - 1) * size;
        List<String> statusList = new ArrayList<>();
        statusList.add("CARD_STATUS_VERIFY_OK");
        statusList.add("CARD_STATUS_DISPATCH");
        RequestCardIdList requestCardIdList = new RequestCardIdList();
        requestCardIdList.count = size;
        requestCardIdList.offset = offset;
        requestCardIdList.status_list = statusList;
        String respone = wxMpService.post(url.toString(), JsonUtils.toJson(requestCardIdList));
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(respone);
        if (jo.get("errcode").getAsInt() != 0) {
            throw AppException.getException(ErrorCode.Get_WX_Card_Fail);
        }
        JsonArray card_id_list = jo.getAsJsonArray("card_id_list");
        for (int i = 0; i < card_id_list.size(); i++) {
            cardIdList.add(createWXCardByCardId(card_id_list.get(i).getAsString()));
        }
        return cardIdList;
    }


    /**
     * 去掉nonceStr，不去掉出现签名错误
     *
     * @param optionalSignParam
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxCardExt createWxCardExt(String... optionalSignParam) throws WxErrorException {
        WxCardExt wxCardExt = new WxCardExt();
        long timestamp = System.currentTimeMillis() / 1000L;
        String cardApiTicket = wxMpService.getCardService().getCardApiTicket(false);
        String[] signParam = (String[]) Arrays.copyOf(optionalSignParam, optionalSignParam.length + 2);
        signParam[optionalSignParam.length] = String.valueOf(timestamp);
        signParam[optionalSignParam.length + 1] = cardApiTicket;
        String signature = SHA1.gen(signParam);
        wxCardExt.setTimestamp(timestamp);
        wxCardExt.setSignature(signature);
        return wxCardExt;
    }

}
