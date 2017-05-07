package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.CooperativePartnerMapper;
import com.mk.coffee.model.CooperativePartner;
import com.mk.coffee.service.CooperativePartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合作伙伴服务
 * Created by Administrator on 2017/5/7 0007.
 */
@Service
public class CooperativePartnerServiceImpl implements CooperativePartnerService {
    @Autowired
    private CooperativePartnerMapper cooperativePartnerMapper;

    @Override
    public List<CooperativePartner> getList() {
        return cooperativePartnerMapper.selectByExample(null);
    }

    @Override
    public CooperativePartner getItem(int id) {
        return cooperativePartnerMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(CooperativePartner cooperativePartner) {
        return cooperativePartnerMapper.updateByPrimaryKey(cooperativePartner) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return cooperativePartnerMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(CooperativePartner cooperativePartner) {
        return cooperativePartnerMapper.insert(cooperativePartner) > 0;
    }
}
