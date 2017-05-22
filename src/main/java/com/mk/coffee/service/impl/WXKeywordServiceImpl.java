package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.WxKeywordMapper;
import com.mk.coffee.model.WxKeyword;
import com.mk.coffee.service.WXKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关键字保存消息配置服务
 * Created by Administrator on 2017/5/22 0022.
 */
@Service
public class WXKeywordServiceImpl implements WXKeywordService {
    @Autowired
    private WxKeywordMapper wxKeywordMapper;

    @Override
    public List<WxKeyword> getList() {
        return wxKeywordMapper.selectByExample(null);
    }

    @Override
    public WxKeyword getItem(int id) {
        return wxKeywordMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(WxKeyword wxKeyword) {
        return wxKeywordMapper.updateByPrimaryKey(wxKeyword) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return wxKeywordMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(WxKeyword wxKeyword) {
        return wxKeywordMapper.insert(wxKeyword) > 0;
    }

    @Override
    public List<WxKeyword> getWxKeywordByKeyword(String keyword) {
        return wxKeywordMapper.getWxKeywordByKeyword(keyword);
    }
}
