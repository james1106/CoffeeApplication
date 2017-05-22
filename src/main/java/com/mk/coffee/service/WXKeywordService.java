package com.mk.coffee.service;

import com.mk.coffee.model.WxKeyword;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
public interface WXKeywordService extends IBaseService<WxKeyword> {
    List<WxKeyword> getWxKeywordByKeyword(String keyword);

}
