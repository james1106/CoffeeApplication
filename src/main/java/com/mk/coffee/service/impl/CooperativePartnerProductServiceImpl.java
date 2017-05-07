package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.CooperativePartnerProductMapper;
import com.mk.coffee.model.CooperativePartnerProduct;
import com.mk.coffee.service.CooperativePartnerProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
@Service
public class CooperativePartnerProductServiceImpl implements CooperativePartnerProductService {
    private CooperativePartnerProductMapper cooperativePartnerProductMapper;

    @Override
    public List<CooperativePartnerProduct> getList() {
        return cooperativePartnerProductMapper.selectByExample(null);
    }

    @Override
    public CooperativePartnerProduct getItem(int id) {
        return cooperativePartnerProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(CooperativePartnerProduct cooperativePartnerProduct) {
        return cooperativePartnerProductMapper.updateByPrimaryKey(cooperativePartnerProduct) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return cooperativePartnerProductMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(CooperativePartnerProduct cooperativePartnerProduct) {
        return cooperativePartnerProductMapper.insert(cooperativePartnerProduct) > 0;
    }
}
