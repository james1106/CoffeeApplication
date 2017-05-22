package com.mk.coffee.service;

import com.mk.coffee.model.WxMessage;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
public interface WXMessageService extends IBaseService<WxMessage> {
    List<WxMessage> searchList(String keyword);
}
