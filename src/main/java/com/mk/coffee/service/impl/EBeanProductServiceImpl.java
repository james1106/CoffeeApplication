package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.EbeanProductMapper;
import com.mk.coffee.model.EbeanProduct;
import com.mk.coffee.service.EBeanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
@Service
public class EBeanProductServiceImpl implements EBeanProductService {
    @Autowired
    private EbeanProductMapper mapper;

    @Override
    public List<EbeanProduct> getList() {
        return mapper.selectByExample(null);
    }

    @Override
    public EbeanProduct getItem(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(EbeanProduct ebeanProduct) {
        return mapper.updateByPrimaryKeySelective(ebeanProduct) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(EbeanProduct ebeanProduct) {
        return mapper.insert(ebeanProduct) > 0;
    }
}
