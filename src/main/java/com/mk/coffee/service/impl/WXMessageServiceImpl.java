package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.WxMessageMapper;
import com.mk.coffee.model.WxMessage;
import com.mk.coffee.service.WXMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信消息服务
 * Created by Administrator on 2017/5/22 0022.
 */
@Service
public class WXMessageServiceImpl implements WXMessageService {
    @Autowired
    private WxMessageMapper wxMessageMapper;

    @Override
    public List<WxMessage> getList() {
        return wxMessageMapper.selectByExample(null);
    }

    @Override
    public WxMessage getItem(int id) {
        return wxMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(WxMessage wxMessage) {
        return wxMessageMapper.updateByPrimaryKey(wxMessage) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return wxMessageMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(WxMessage wxMessage) {
        return wxMessageMapper.insert(wxMessage) > 0;
    }

    @Override
    public List<WxMessage> searchList(String keyword) {
        return wxMessageMapper.searchList(keyword);
    }
}
