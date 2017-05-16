package com.mk.coffee.service;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.entity.WxCard;
import com.mk.coffee.entity.WxCardExt;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.model.OrderDetails;
import com.mk.coffee.model.WXCard;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
public interface WXInfoService {
    WXCard getWXCart(String cardId, String encryptCode);

    String getAuthorizationUrl(String url);

    RestResult<Map<String, String>> getEBeanRechargeWXJsPayInfo(EbeanRecord ebeanRecord, String address);

    RestResult<Map<String, String>> getEBeanRechargeWXJsPayInfoTest(EbeanRecord ebeanRecord, String address);

    //结账
    boolean orderCheckout(OrderDetails orderDetails) throws WxErrorException;

    List<WxCard> getCardList(long memberId) throws WxErrorException;

    List<WXCard> getCardIdList(int page, int size) throws WxErrorException;

    WxCardExt createWxCardExt(String... optionalSignParam) throws WxErrorException;


}
