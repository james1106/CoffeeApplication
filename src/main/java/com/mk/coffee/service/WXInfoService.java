package com.mk.coffee.service;

import com.mk.coffee.model.WXCard;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
public interface WXInfoService {
    WXCard getWXCart(String cardId,String encryptCode);

    String getAuthorizationUrl(String url);
}
