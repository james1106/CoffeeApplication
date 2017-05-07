package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.EbeanMapper;
import com.mk.coffee.model.Ebean;
import com.mk.coffee.service.EBeanServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
@Service
public class EBeanServiceImpl implements EBeanServie {
    @Autowired
    private EbeanMapper mapper;

    @Override
    public List<Ebean> getList() {
        return mapper.selectByExample(null);
    }

    @Override
    public Ebean getItem(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(Ebean ebean) {
        return mapper.updateByPrimaryKey(ebean) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(Ebean ebean) {
        return mapper.insert(ebean) > 0;
    }
}
