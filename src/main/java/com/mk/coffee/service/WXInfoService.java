package com.mk.coffee.service;

import com.mk.coffee.common.RestResult;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.model.WXCard;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
public interface WXInfoService {
    WXCard getWXCart(String cardId, String encryptCode);

    String getAuthorizationUrl(String url);

    RestResult<Map<String, String>> getEBeanRechargeWXJsPayInfo(EbeanRecord ebeanRecord, String address);
}
